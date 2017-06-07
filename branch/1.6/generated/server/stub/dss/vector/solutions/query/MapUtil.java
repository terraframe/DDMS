package dss.vector.solutions.query;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableDecimal;
import com.runwaysdk.query.SelectableDouble;
import com.runwaysdk.query.SelectableFloat;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.ValueQuery;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;

import dss.vector.solutions.geo.DuplicateMapDataException;
import dss.vector.solutions.geo.GeoServerReloadException;
import dss.vector.solutions.geoserver.GeoserverFacade;
import dss.vector.solutions.util.QueryUtil;

public class MapUtil extends MapUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242080109170L;

  public MapUtil()
  {
    super();
  }

  /**
   * Attempts to delete the view with the given name. A database level cascade is also used to remove any dependencies on other views.
   * 
   * @param viewName
   */
  public static void deleteMapView(String viewName)
  {
    List<String> batch = new LinkedList<String>();
    batch.add("DROP VIEW IF EXISTS " + viewName + " CASCADE");
    Database.executeBatch(batch);
  }

  /**
   * Creates database views for each layer provided and returns a JSON string with the correct mapping information for use with OpenLayers/GeoServer.
   * The returned Map maintains insertion order so it can be safely iterated over to get the layers starting with the base layer and up.
   * 
   * @param layers
   * @return
   */
  @Transaction
  public static Map<Layer, ValueQuery> createDBViews(Layer[] layers, boolean infoOnly, MapConfiguration configuration)
  {
    if (!infoOnly && layers.length == 0)
    {
      String error = "A user tried to create a map without layers.";
      throw new NoThematicLayerException(error);
    }

    // Map all Layers to their ValueQuery objects
    Map<String, ValueQuery> layerValueQueries = new HashMap<String, ValueQuery>();
    boolean isClipping = false;
    for (int i = 0; i < layers.length; i++)
    {
      Layer layer = layers[i];

      SavedSearch search = layer.getSavedSearch();
      String xml = search.getQueryXml();
      String config = search.getConfig();
      String queryType = search.getQueryType();

      // QueryBuilder.getValueQuery() takes in the query class for use with
      // reflection.
      // TODO pass in queryType and have getValueQuery deref the class
      String queryClass = QueryConstants.getQueryClass(queryType);

      ValueQuery valueQuery;

      try
      {
        valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, layer, null, null, configuration.getDisease());

        // Format any decimal thematic variable to two decimal places
        if (layer.hasThematicVariable())
        {
          Selectable thematicSel = valueQuery.getSelectableRef(layer.getThematicUserAlias());

          if (thematicSel instanceof SelectableDouble || thematicSel instanceof SelectableFloat || thematicSel instanceof SelectableDecimal)
          {
            SelectableSQLDouble newSel;
            if (thematicSel.isAggregateFunction())
            {
              newSel = valueQuery.aSQLAggregateDouble(thematicSel.getDbColumnName(), "");
            }
            else
            {
              newSel = valueQuery.aSQLDouble(thematicSel.getDbColumnName(), "");
            }
            newSel.setSQL(thematicSel.getSQL() + "::decimal(20,2)");
            newSel.setColumnAlias(thematicSel.getColumnAlias());
            newSel.setUserDefinedAlias(thematicSel.getUserDefinedAlias());

            valueQuery.replaceSelectable(newSel);
          }
        }
      }
      catch (ProgrammingErrorException e)
      {
        String layerName = layer.getLayerName();
        String queryName = layer.getSavedSearch().getQueryName();

        String error = "The map could not be generated while getting the query [" + queryName + "] for layer [" + layerName + "]";

        if (e.getCause() instanceof InvocationTargetException)
        {
          InvocationTargetException ex = (InvocationTargetException) e.getCause();
          if (ex.getCause() instanceof QueryException)
          {
            if (i == 0)
            {
              BaseLayerQueryChangedException changeEx = new BaseLayerQueryChangedException();
              changeEx.setLayerName(layerName);
              changeEx.setQueryName(queryName);
              throw changeEx;
            }
            else
            {
              LayerOmittedQueryChangedInformation info = new LayerOmittedQueryChangedInformation();
              info.setLayerName(layerName);
              info.setQueryName(queryName);
              info.throwIt();

              layers[i] = null;
              continue;
            }
          }
        }

        GeoServerReloadException gsEX = new GeoServerReloadException(error, e.getCause());
        throw gsEX;
      }

      // The base layer must have geo entities or geoserver bombs out
      if (!infoOnly && i == 0 && valueQuery.getCount() == 0)
      {
        String error = "The thematic layer doesn't contain spatial data.";
        throw new NoEntitiesInThematicLayerException(error);
      }

      layerValueQueries.put(layer.getId(), valueQuery);

      if (i != 0 && !isClipping && layer.getClipToBaseLayer())
      {
        isClipping = true;
      }
    }

    Map<Layer, ValueQuery> reloads = new LinkedHashMap<Layer, ValueQuery>();
    String baseView = null;
    for (int i = 0; i < layers.length; i++)
    {
      Layer layer = layers[i];
      if (layer == null)
      {
        continue; // The layer failed the first pass
      }

      // Remove the old layer
      try
      {
        if (!configuration.hasOverride(layer))
        {
          String viewName = layer.getViewName();

          deleteMapView(viewName);

          /*
           * If geoserver has a published layer dependent upon the map view then it will also need to be removed.
           */
          MapUtil.removeLayers(viewName);
        }
      }
      catch (DatabaseException e)
      {
        // View doesn't exist, but that's okay. It may have never
        // been created or a cleanup task has removed it.
      }

      String layerName = layer.getLayerName();

      // Update the view name on the layer for this refresh cycle
      String viewNameNoPrefix = String.valueOf(System.currentTimeMillis());
      String newViewName = Layer.GEO_VIEW_PREFIX + viewNameNoPrefix;

      if (configuration.hasOverride(layer))
      {
        newViewName = configuration.getViewName(layer);
      }

      if (!infoOnly)
      {
        layer.appLock();
      }

      layer.setViewName(newViewName);

      if (!infoOnly)
      {
        layer.apply();
      }

      if (i == 0)
      {
        baseView = newViewName;
      }

      ValueQuery valueQuery = layerValueQueries.get(layer.getId());

      // Any empty non-base layer will be omitted from the map to
      // keep geoserver from acting funky.
      if (i > 0 && valueQuery.getCount() == 0)
      {
        LayerOmittedNoDataInformation info = new LayerOmittedNoDataInformation();
        info.setLayerName(layerName);
        info.throwIt();

        continue;
      }

      String sql;
      if (i != 0 && layer.getClipToBaseLayer())
      {
        List<Selectable> selectables = valueQuery.getSelectableRefs();
        Selectable geoSelectable = null;
        int count = 0;
        int removeInd = 0;
        for (Selectable selectable : selectables)
        {
          if (selectable.getColumnAlias().equals(QueryConstants.GEOMETRY_NAME_COLUMN))
          {
            geoSelectable = selectable;
            removeInd = count;
          }

          count++;
        }

        selectables.remove(removeInd);

        String geoAttr = geoSelectable.getDefiningTableAlias() + "." + geoSelectable.getDbColumnName();

        String clippingAlias = "geoentity_clipping";
        String clippingColumnAlias = "clipping_column";
        String clippingColumn = clippingAlias + "." + clippingColumnAlias;

        SelectableSQLCharacter geoS = valueQuery.aSQLAggregateCharacter(QueryConstants.GEOMETRY_NAME_COLUMN, "collect(intersection(" + geoAttr + ", " + clippingColumn + "))");
        selectables.add(geoS);

        valueQuery.clearSelectClause();
        valueQuery.SELECT(selectables.toArray(new Selectable[selectables.size()]));

        valueQuery.FROM("(SELECT " + QueryConstants.GEOMETRY_NAME_COLUMN + " AS " + clippingColumnAlias + " FROM " + baseView + ")", clippingAlias);
      }

      if (!configuration.hasOverride(layer))
      {
        // Create a new view that will reflect the current state of the query.
        QueryUtil.createViewFromValueQuery(viewNameNoPrefix, Layer.GEO_VIEW_PREFIX, layer.getSavedSearch().getQueryType(), valueQuery, null, true);
      }

      // make sure there are no duplicate geo entities
      String countSQL = "SELECT COUNT(*) " + Database.formatColumnAlias("ct") + " FROM " + newViewName;
      countSQL += " GROUP BY " + QueryConstants.GEO_ID_COLUMN + " HAVING COUNT(*) > 1";

      ResultSet resultSet = Database.query(countSQL);

      try
      {
        if (resultSet.next())
        {
          // We have duplicate data! Throw an exception if this is the base
          // layer,
          // but only omit the layer with info if non-base.
          if (i == 0)
          {
            DuplicateMapDataException ex = new DuplicateMapDataException();
            throw ex;
          }
          else
          {
            LayerOmittedDuplicateDataInformation info = new LayerOmittedDuplicateDataInformation();
            info.setLayerName(layerName);
            info.throwIt();

            continue;
          }
        }
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }

      reloads.put(layer, valueQuery);

    }

    return reloads;
  }

  /**
   * Formats the SLD file (pathing and filename concatenation) for the given layer.
   * 
   * @param layer
   * @return
   */
  public static String formatSLD(Layer layer)
  {
    String fullWebDir = LocalProperties.getWebDirectory();

    if (fullWebDir.endsWith("/"))
    {
      fullWebDir = fullWebDir.substring(0, fullWebDir.length() - 1);
    }
    String webDir = fullWebDir.substring(fullWebDir.lastIndexOf("/"));
    if (webDir.startsWith("/"))
    {
      webDir = webDir.substring(1);
    }
    String filePath = webDir + "/" + QueryConstants.SLD_WEB_DIR + QueryConstants.createSLDName(layer.getId()) + "." + QueryConstants.SLD_EXTENSION;

    // Generated a random query string to force GeoServer to not cache the SLD
    String random = String.valueOf(Math.random());
    random = random.substring(random.length() - 6);

    return filePath + "?a=" + random;
  }

  // public static final String getSLD_URL()
  // {
  // return bundle.getString("geoserver.sld.path");
  // }'

  /**
   * Gets the bounding box of the thematic layer.
   * 
   * @return
   */
  public static JSONArray getThematicBBox(List<BasicLayerIF> layers, MapConfiguration configuration)
  {
    JSONArray bboxArr = new JSONArray();
    if (layers.size() > 0)
    {
      String[] layerNames = new String[layers.size()];
      String sql;
      if (layers.size() == 1)
      {
        BasicLayerIF layer = layers.get(0);
        String viewName = configuration.getViewName(layer);
        layerNames[0] = layer.getLayerName();

        sql = "SELECT ST_AsText(ST_Extent(" + viewName + "." + QueryConstants.GEOMETRY_NAME_COLUMN + ")) AS bbox FROM " + viewName;
      }
      else
      {
        // More than one layer so union the geometry columns
        sql = "SELECT ST_AsText(ST_Extent(geo_v)) AS bbox FROM (\n";

        for (int i = 0; i < layers.size(); i++)
        {
          BasicLayerIF layer = layers.get(i);
          String viewName = configuration.getViewName(layer);
          layerNames[i] = layer.getLayerName();

          sql += "(SELECT " + QueryConstants.GEOMETRY_NAME_COLUMN + " AS geo_v FROM " + viewName + ") \n";

          if (i != layers.size() - 1)
          {
            sql += "UNION \n";
          }
        }

        sql += ") bbox_union";
      }

      ResultSet resultSet = Database.query(sql);

      try
      {
        if (resultSet.next())
        {
          String bbox = resultSet.getString("bbox");
          if (bbox != null)
          {
            Pattern p = Pattern.compile("POLYGON\\(\\((.*)\\)\\)");
            Matcher m = p.matcher(bbox);

            if (m.matches())
            {
              String coordinates = m.group(1);
              List<Coordinate> coords = new LinkedList<Coordinate>();

              for (String c : coordinates.split(","))
              {
                String[] xAndY = c.split(" ");
                double x = Double.valueOf(xAndY[0]);
                double y = Double.valueOf(xAndY[1]);

                coords.add(new Coordinate(x, y));
              }

              Envelope e = new Envelope(coords.get(0), coords.get(2));

              try
              {
                bboxArr.put(e.getMinX());
                bboxArr.put(e.getMinY());
                bboxArr.put(e.getMaxX());
                bboxArr.put(e.getMaxY());
              }
              catch (JSONException ex)
              {
                throw new ProgrammingErrorException(ex);
              }
            }
            else
            {
              // There will not be a match if there is a single point geo
              // entity.
              // In this case, return the x,y coordinates to OpenLayers.

              p = Pattern.compile("POINT\\((.*)\\)");
              m = p.matcher(bbox);
              if (m.matches())
              {
                String c = m.group(1);
                String[] xAndY = c.split(" ");
                double x = Double.valueOf(xAndY[0]);
                double y = Double.valueOf(xAndY[1]);

                try
                {
                  bboxArr.put(x);
                  bboxArr.put(y);
                }
                catch (JSONException ex)
                {
                  throw new ProgrammingErrorException(ex);
                }
              }
              else
              {
                String error = "The database view(s) [" + StringUtils.join(layerNames, ",") + "] could not be used to create a valid bounding box";
                throw new GeoServerReloadException(error);
              }
            }
          }
        }

        return bboxArr;
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }
    }

    // Some problem occured and the bbox couldn't be calculated.
    // Just return the African defaults
    try
    {
      bboxArr.put(36.718452);
      bboxArr.put(-17.700377000000003);
      bboxArr.put(36.938452);
      bboxArr.put(-17.480376999999997);
    }
    catch (JSONException ex)
    {
      throw new ProgrammingErrorException(ex);
    }

    return bboxArr;
  }

  public static void reload(Map<Layer, ValueQuery> reloads, MapConfiguration configuration)
  {
    GeoserverFacade.reload(reloads, configuration);
  }

  public static void removeLayers(String... layerNames)
  {
    for (String layerName : layerNames)
    {
      GeoserverFacade.removeLayer(layerName);
    }
  }

  public static List<String> getLayerNames()
  {
    return GeoserverFacade.getLayers();
  }

  public static String getGeoServerLocalURL()
  {
    return GeoserverFacade.getLocalURL();
  }

  public static String getGeoServerRemoteURL()
  {
    return GeoserverFacade.getRemoteURL();
  }

  public static void generateScaleImageExport(CanvasInformation info, Integer scaleX, Integer scaleY, Graphics mapBaseGraphic)
  {
    double northBound = info.getNorthBound();
    double southBound = info.getSouthBound();
    int savedMapWidth = info.getSavedMapWidth();
    int savedMapHeight = info.getSavedMapHeight();
    int leftOffset = info.getLeftOffset();
    int topOffset = info.getTopOffset();
    int width = info.getWidth();
    int height = info.getHeight();

    int scaleBarHeight = 32;
    int maxScaleBarWidth = 100;
    String topOutUnits = "km";
    String topInUnits = "m";
    String bottomOutUnits = "mi";
    String bottomInUnits = "ft";

    // lookup for unit conversions
    HashMap<String, Double> INCHES_PER_UNIT_MAP = new HashMap<String, Double>();
    INCHES_PER_UNIT_MAP.put("inches", 1.0);
    INCHES_PER_UNIT_MAP.put("ft", 12.0);
    INCHES_PER_UNIT_MAP.put("mi", 63360.0);
    INCHES_PER_UNIT_MAP.put("m", 39.3701);
    INCHES_PER_UNIT_MAP.put("km", 39370.1);
    INCHES_PER_UNIT_MAP.put("dd", 4374754.0);
    INCHES_PER_UNIT_MAP.put("yd", 36.0);

    double north = northBound * Math.PI / 180; // convert from degrees to radians
    double south = southBound * Math.PI / 180; // convert from degrees to radians
    int polarCircumference = 360;
    double lengthNorthSouthInDegrees = ( north - south ) * ( polarCircumference / ( 2 * Math.PI ) );
    double degreePerPixel = lengthNorthSouthInDegrees / height;

    int maxSizeData = (int) Math.round(maxScaleBarWidth * degreePerPixel * (Integer) INCHES_PER_UNIT_MAP.get("dd").intValue());

    String topUnits;
    String bottomUnits;
    if (maxSizeData > 100000)
    {
      topUnits = topOutUnits;
      bottomUnits = bottomOutUnits;
    }
    else
    {
      topUnits = topInUnits;
      bottomUnits = bottomInUnits;
    }

    double topMax = maxSizeData / INCHES_PER_UNIT_MAP.get(topUnits);
    double bottomMax = maxSizeData / INCHES_PER_UNIT_MAP.get(bottomUnits);
    int topRounded = getBarLen(topMax);
    int bottomRounded = getBarLen(bottomMax);
    topMax = (double) ( (double) topRounded / (double) INCHES_PER_UNIT_MAP.get("dd").intValue() * (double) Math.round(INCHES_PER_UNIT_MAP.get(topUnits)) );
    bottomMax = (double) bottomRounded / (double) INCHES_PER_UNIT_MAP.get("dd").intValue() * (double) Math.round(INCHES_PER_UNIT_MAP.get(bottomUnits));
    int topScaleBarLengthInPixels = (int) Math.round(topMax / degreePerPixel);
    int bottomScaleBarLengthInPixels = (int) Math.round(bottomMax / degreePerPixel);

    int scaleXPositionPercentBased = (int) Math.round( ( (double) ( scaleX - leftOffset ) ) / savedMapWidth * width);
    int scaleYPositionPercentBased = (int) Math.round( ( (double) ( scaleY - topOffset ) ) / savedMapHeight * height);

    Graphics2D scaleBarBaseGraphic = null;
    try
    {
      BufferedImage scaleBarBase = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      scaleBarBaseGraphic = scaleBarBase.createGraphics();
      scaleBarBaseGraphic.setColor(Color.black);

      // //
      // draw the top of the scale bar
      // //

      scaleBarBaseGraphic.setStroke(new BasicStroke(2));
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased, scaleYPositionPercentBased, scaleXPositionPercentBased + topScaleBarLengthInPixels, scaleYPositionPercentBased);
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased, scaleYPositionPercentBased - ( scaleBarHeight / 2 ), scaleXPositionPercentBased, scaleYPositionPercentBased);
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased + topScaleBarLengthInPixels, scaleYPositionPercentBased - ( scaleBarHeight / 2 ), scaleXPositionPercentBased + topScaleBarLengthInPixels, scaleYPositionPercentBased);

      String scaleTopText = Integer.toString(topRounded) + " " + topUnits;
      Font scaleTopFont = new Font(scaleTopText, Font.PLAIN, 12);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      scaleBarBaseGraphic.setFont(scaleTopFont);

      FontMetrics fmTop = scaleBarBaseGraphic.getFontMetrics();
      int textWidthTop = (int) fmTop.stringWidth(scaleTopText);
      if (textWidthTop >= topScaleBarLengthInPixels - 4)
      {
        String trimmedText = scaleTopText.replace(" " + topUnits, "");
        int timmedTextWidth = fmTop.stringWidth(trimmedText);

        String trimmedOffUnits = " " + topUnits;
        int trimmedOffUnitsWidth = fmTop.stringWidth(trimmedOffUnits);

        int fontHorizontalPosTopVal = (int) ( (int) scaleXPositionPercentBased + ( ( topScaleBarLengthInPixels / 2 ) - ( timmedTextWidth / 2 ) ) );
        int fontHorizontalPosTopUnits = (int) ( (int) scaleXPositionPercentBased + ( ( topScaleBarLengthInPixels / 2 ) - ( trimmedOffUnitsWidth / 2 ) ) );

        scaleBarBaseGraphic.drawString(trimmedText, fontHorizontalPosTopVal, scaleYPositionPercentBased - fmTop.getHeight() - 2);
        scaleBarBaseGraphic.drawString(trimmedOffUnits, fontHorizontalPosTopUnits, scaleYPositionPercentBased - fmTop.getDescent() - 2);
      }
      else
      {
        int fontHorizontalPosTop = (int) ( (int) scaleXPositionPercentBased + ( ( topScaleBarLengthInPixels / 2 ) - ( textWidthTop / 2 ) ) );
        scaleBarBaseGraphic.drawString(scaleTopText, fontHorizontalPosTop, scaleYPositionPercentBased - fmTop.getDescent() - 2);

      }

      scaleBarBaseGraphic.drawImage(scaleBarBase, 0, 0, null);

      // //
      // draw the bottom of the scale bar
      // //

      scaleBarBaseGraphic.setStroke(new BasicStroke(2));
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased, scaleYPositionPercentBased, scaleXPositionPercentBased + bottomScaleBarLengthInPixels, scaleYPositionPercentBased);
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased, scaleYPositionPercentBased + ( scaleBarHeight / 2 ), scaleXPositionPercentBased, scaleYPositionPercentBased);
      scaleBarBaseGraphic.drawLine(scaleXPositionPercentBased + bottomScaleBarLengthInPixels, scaleYPositionPercentBased + ( scaleBarHeight / 2 ), scaleXPositionPercentBased + bottomScaleBarLengthInPixels, scaleYPositionPercentBased);

      String scaleBottomText = Integer.toString(bottomRounded) + " " + bottomUnits;
      Font scaleBottomFont = new Font(scaleBottomText, Font.PLAIN, 12);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      scaleBarBaseGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      scaleBarBaseGraphic.setFont(scaleBottomFont);

      FontMetrics fmBottom = scaleBarBaseGraphic.getFontMetrics();
      float textWidthBottom = fmBottom.stringWidth(scaleBottomText);

      if (textWidthBottom >= bottomScaleBarLengthInPixels - 4)
      {
        String trimmedTextBottom = scaleTopText.replace(" " + topUnits, "");
        int timmedTextBottomWidth = fmTop.stringWidth(trimmedTextBottom);

        String trimmedOffUnitsBottom = " " + topUnits;
        int trimmedOffUnitsBottomWidth = fmTop.stringWidth(trimmedOffUnitsBottom);

        int fontHorizontalPosBottomVal = (int) ( (int) scaleXPositionPercentBased + ( ( bottomScaleBarLengthInPixels / 2 ) - ( timmedTextBottomWidth / 2 ) ) );
        int fontHorizontalPosBottomUnits = (int) ( (int) scaleXPositionPercentBased + ( ( bottomScaleBarLengthInPixels / 2 ) - ( trimmedOffUnitsBottomWidth / 2 ) ) );

        scaleBarBaseGraphic.drawString(trimmedTextBottom, fontHorizontalPosBottomVal, scaleYPositionPercentBased + fmBottom.getAscent() + fmBottom.getLeading() + 2);
        scaleBarBaseGraphic.drawString(trimmedOffUnitsBottom, fontHorizontalPosBottomUnits, scaleYPositionPercentBased + fmBottom.getHeight() + fmBottom.getAscent() + fmBottom.getLeading());
      }
      else
      {
        int fontHorizontalPosBottom = (int) ( (int) scaleXPositionPercentBased + ( ( bottomScaleBarLengthInPixels / 2 ) - ( textWidthBottom / 2 ) ) );
        scaleBarBaseGraphic.drawString(scaleBottomText, fontHorizontalPosBottom, scaleYPositionPercentBased + fmBottom.getAscent() + fmBottom.getLeading() + 2);
      }

      scaleBarBaseGraphic.drawImage(scaleBarBase, 0, 0, null);

      mapBaseGraphic.drawImage(scaleBarBase, 0, 0, null);
    }
    finally
    {
      if (scaleBarBaseGraphic != null)
      {
        scaleBarBaseGraphic.dispose();
      }
    }
  }

  /*
   * Get the bucket option that is closest to the input value
   */
  public static int getBarLen(double maxLen)
  {
    int digits = (int) ( Math.log(maxLen) / Math.log(10) ); // truncates decimals
    int pow10 = (int) Math.pow(10, digits);
    int firstChar = (int) maxLen / pow10;
    int barLen;
    if (firstChar > 5)
    {
      barLen = 5;
    }
    else if (firstChar > 2)
    {
      barLen = 2;
    }
    else
    {
      barLen = 1;
    }
    return barLen * pow10;
  }

}
