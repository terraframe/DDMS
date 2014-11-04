package dss.vector.solutions.query;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.UserSettings;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.util.ShapefileExporter;

public class SavedMap extends SavedMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID          = 1252823927;

  /**
   * Prefix for the generated map views
   */
  private static final String GENERATED_MAP_VIEW_PREFIX = "m_";

  public SavedMap()
  {
    super();
  }

  @Override
  public String toString()
  {
    return this.getMapName();
  }

  @Override
  @Transaction
  public void delete()
  {
    /*
     * Delete all of the saved images
     */
    this.deleteSavedImages();

    /*
     * Delete the cycle job corresponding to the map
     */
    this.deleteCycleJob();

    /*
     * Delete the generated maps
     */
    this.deleteGeneratedMapsAndView();

    /*
     * Delete the actual map
     */
    super.delete();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && !this.isAppliedToDB())
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public LayerViewQuery getAllLayers()
  {
    QueryFactory f = new QueryFactory();
    LayerViewQuery q = new LayerViewQuery(f, this.getId());

    return q;
  }

  /**
   * Refreshes the map data, including rerunning the queries and creating DB views.
   */
  @Override
  @Authenticate
  public String refreshMap(String currentMapId)
  {
    return this.refreshMap(currentMapId, new MapConfiguration());
  }

  public String refreshMap(String currentMapId, MapConfiguration configuration)
  {
    JSONObject mapData;
    JSONArray layersJSON;
    JSONArray savedImagesJSON;
    JSONArray savedTextJSON;

    Layer[] layers = this.getOrderedLayers();
    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(layers, false, configuration);
    MapUtil.reload(layersVQ, configuration);
    String appName = CommonProperties.getDeployAppName();
    try
    {
      String geoserverPath = MapUtil.getGeoServerRemoteURL();
      String geoserverLocalPath = MapUtil.getGeoServerLocalURL();

      // this is a hack until username, password, and sld path are put in
      // properties files.
      String sldPath = geoserverLocalPath.replace("geoserver", appName) + "/";

      mapData = new JSONObject();
      layersJSON = new JSONArray();
      savedImagesJSON = new JSONArray();
      savedTextJSON = new JSONArray();

      mapData.put("geoserverURL", geoserverPath);
      mapData.put("sldURL", sldPath);
      mapData.put("layers", layersJSON);
      mapData.put("savedImages", savedImagesJSON);
      mapData.put("savedTextElements", savedTextJSON);
    }
    catch (JSONException e)
    {
      String error = "Could not create the mapping data.";
      throw new ProgrammingErrorException(error, e);
    }

    // Create the JSON for the layers that will be passed to OpenLayers
    List<Layer> bboxLayers = new LinkedList<Layer>();
    int count = 0;
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    while (iter.hasNext())
    {
      Layer layer = iter.next();

      if (configuration.includeLayer(layer))
      {
        String namespacedView = appName + ":" + configuration.getViewName(layer);
        String sldFile = MapUtil.formatSLD(layer);

        JSONObject layerJSON = new JSONObject();
        try
        {
          layerJSON.put("view", namespacedView);
          layerJSON.put("sld", sldFile);
          layerJSON.put("opacity", layer.getOpacity());
          layerJSON.put("id", layer.getId());

          // Always add the base layer to the bounding box
          if (count == 0 || layer.getAddToBBox())
          {
            bboxLayers.add(layer);
          }

          // Add the legend if enabled
          if (layer.getEnableLegend())
          {
            JSONObject legend = new JSONObject();
            legend.put("title", layer.getLegendTitle());
            legend.put("showLegendBorder", layer.getShowLegendBorder());
            legend.put("fontTitleFamily", layer.getLegendTitleFontFamily());
            legend.put("fontTitleFill", layer.getLegendTitleFontFill());
            legend.put("fontTitleSize", layer.getLegendTitleFontSize());
            legend.put("fontTitleStyle", layer.getLegendTitleFontStyles().get(0).name().toLowerCase());
            legend.put("fontFamily", layer.getLegendFontFamily());
            legend.put("fontFill", layer.getLegendFontFill());
            legend.put("fontSize", layer.getLegendFontSize());
            legend.put("fontStyle", layer.getLegendFontStyles().get(0).name().toLowerCase());
            legend.put("legendXPosition", layer.getLegendXPosition());
            legend.put("legendYPosition", layer.getLegendYPosition());

            MdAttributeDAO md = (MdAttributeDAO) MdAttributeDAO.get(layer.getValue(Layer.LEGENDCOLOR));
            String colorAttribute = md.definesAttribute();

            if (layer.getCreateRawLegend())
            {
              legend.put("createRawLegend", true);
              legend.put("color", layer.getDefaultStyles().getValue(colorAttribute));
            }
            else
            {
              JSONArray categories = new JSONArray();
              legend.put("categories", categories);

              List<? extends AbstractCategory> cats = layer.getAllHasCategory().getAll();
              CategorySorter.sort(cats);

              for (AbstractCategory cat : cats)
              {
                JSONObject category = new JSONObject();

                if (cat instanceof RangeCategory)
                {
                  category.put("lower", ( (RangeCategory) cat ).getLowerBoundStr());
                  category.put("upper", ( (RangeCategory) cat ).getUpperBoundStr());
                }
                else
                {
                  category.put("exact", ( (NonRangeCategory) cat ).getExactValueStr());
                }
                category.put("color", cat.getStyles().getValue(colorAttribute));
                categories.put(category);
              }
            }
            layerJSON.put("legend", legend);
          }
          else
          {
            layerJSON.put("legend", JSONObject.NULL);
          }
          layersJSON.put(layerJSON);
          count++;
        }
        catch (JSONException e)
        {
          String error = "Could not produce the information for the layer [" + layer.getLayerName() + "]";
          throw new ProgrammingErrorException(error, e);
        }
      }
    }

    // restrict the map bounds to the applicable layers
    // unless a saved map has a saved bounding box
    try
    {
      String mapCenter = this.getMapCenter();
      if (mapCenter != "")
      {
        JSONObject mapCenterObj = new JSONObject(mapCenter);
        JSONArray mapBounds = new JSONArray();
        mapBounds.put(mapCenterObj.getString("left"));
        mapBounds.put(mapCenterObj.getString("bottom"));
        mapBounds.put(mapCenterObj.getString("right"));
        mapBounds.put(mapCenterObj.getString("top"));
        mapData.put("bbox", mapBounds);

        mapData.put("zoomLevel", this.getZoomLevel());
      }
      else
      {
        mapData.put("bbox", MapUtil.getThematicBBox(bboxLayers, configuration));
      }
    }
    catch (JSONException e)
    {
      String error = "Could not produce the bounding box.";
      throw new ProgrammingErrorException(error, e);
    }

    List<? extends MapImage> mapImages = this.getAllHasImage().getAll();

    // Build json for all instances of image related to this map
    for (MapImage image : mapImages)
    {
      try
      {
        JSONObject imageInstanceJSONObj = new JSONObject();
        imageInstanceJSONObj.put("filePath", image.getImageFilePath());
        imageInstanceJSONObj.put("fileName", image.getImageName());
        imageInstanceJSONObj.put("imageXPosition", image.getImageXPosition());
        imageInstanceJSONObj.put("imageYPosition", image.getImageYPosition());
        imageInstanceJSONObj.put("imageId", image.getCustomImageId());
        savedImagesJSON.put(imageInstanceJSONObj);
      }
      catch (JSONException e)
      {
        String error = "Could collect map image info.";
        throw new ProgrammingErrorException(error, e);
      }
    }

    List<? extends TextElement> textElements = this.getAllHasTextElement().getAll();

    // Build json for all instances of image related to this map
    for (TextElement text : textElements)
    {
      try
      {
        JSONObject textInstanceJSONObj = new JSONObject();
        textInstanceJSONObj.put("textValue", text.getTextValue());
        textInstanceJSONObj.put("fontColor", text.getFontColor());
        textInstanceJSONObj.put("fontFamily", text.getFontFamily());
        textInstanceJSONObj.put("fontSize", text.getFontSize());
        textInstanceJSONObj.put("fontStyle", text.getFontStyle());
        textInstanceJSONObj.put("textXPosition", text.getTextXPosition());
        textInstanceJSONObj.put("textYPosition", text.getTextYPosition());
        textInstanceJSONObj.put("textId", text.getCustomTextElementId());
        savedTextJSON.put(textInstanceJSONObj);
      }
      catch (JSONException e)
      {
        String error = "Could collect text element info.";
        throw new ProgrammingErrorException(error, e);
      }
    }

    return mapData.toString();
  }

  /**
   * Returns the layers of this map in order of position on map, starting with the base layer at index 0.
   * 
   * @return
   */
  public Layer[] getOrderedLayers()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery hasQ = new HasLayersQuery(f);

    ValueQuery vq = new ValueQuery(f);
    vq.SELECT(hasQ.childId("child_id"), hasQ.getLayerPosition("layerPosition"));
    vq.WHERE(hasQ.parentId().EQ(this.getId()));
    vq.ORDER_BY_ASC(hasQ.getLayerPosition());

    Map<String, Integer> positions = new HashMap<String, Integer>();
    for (ValueObject o : vq.getIterator().getAll())
    {
      positions.put(o.getValue("child_id"), Integer.valueOf(o.getValue("layerPosition")));
    }

    Layer[] ordered = new Layer[positions.size()];
    for (Layer layer : this.getAllLayer().getAll())
    {
      ordered[positions.get(layer.getId())] = layer;
    }

    return ordered;
  }

  @Override
  public InputStream exportShapefile()
  {
    List<? extends Layer> layers = this.getAllLayer().getAll();
    if (layers.size() == 0)
    {
      String error = "The map [] does not have any layers to export.";
      throw new NoLayersInExportException(error);
    }

    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(getOrderedLayers(), false, new MapConfiguration());
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    List<Layer> layersInMap = new LinkedList<Layer>();
    while (iter.hasNext())
    {
      layersInMap.add(iter.next());
    }

    ShapefileExporter exporter = new ShapefileExporter();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    exporter.export(layersInMap, output);

    InputStream input = new ByteArrayInputStream(output.toByteArray());

    return input;
  }

  private void copyStyles(Styles source, Styles dest)
  {
    for (MdAttributeDAOIF mdAttr : source.getMdClass().definesAttributes())
    {
      if (!mdAttr.isSystem() && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
      {
        String name = mdAttr.definesAttribute();

        if (mdAttr instanceof MdAttributeEnumerationDAOIF)
        {
          String value = source.getEnumValues(name).get(0).getId();
          dest.addEnumItem(name, value);
        }
        else
        {
          String value = source.getValue(name);
          dest.setValue(name, value);
        }
      }
    }
  }

  /**
   * Creates a SavedMap and clones the given layers to be applied as layers on the created SavedMap.
   */
  @Override
  @Transaction
  @Authenticate
  public LayerViewQuery createFromExisting(String existingMapId)
  {
    SavedMap existingMap = SavedMap.get(existingMapId);

    if (this.isNew())
    {
      this.apply();
    }
    else
    {
      // Ensure that the saved map is not currently being used in a running cycle job
      CycleJobQuery query = new CycleJobQuery(new QueryFactory());
      query.WHERE(query.getSavedMap().EQ(this));
      query.AND(query.getRunning().EQ(true));

      if (query.getCount() > 0)
      {
        throw new MapInUseException();
      }

      // If saved elements exist for the saved map delete them so they can be
      // copied from the default without being duplicated
      for (Layer layer : this.getAllLayer().getAll())
      {
        layer.delete();
      }

      for (MapImage image : this.getAllHasImage().getAll())
      {
        image.delete();
      }

      for (TextElement text : this.getAllHasTextElement().getAll())
      {
        text.delete();
      }
    }

    // copy the layers from the existing map
    for (LayerView existingLayerView : existingMap.getAllLayers().getIterator().getAll())
    {
      Layer existingLayer = Layer.get(existingLayerView.getLayerId());
      Layer layer = new Layer();

      for (MdAttributeDAOIF mdAttr : layer.getMdClass().definesAttributes())
      {
        if (!mdAttr.isSystem() && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
        {
          String name = mdAttr.definesAttribute();

          if (mdAttr instanceof MdAttributeEnumerationDAOIF)
          {
            String value = existingLayer.getEnumValues(name).get(0).getId();
            layer.addEnumItem(name, value);
          }
          else if (!name.equals(Layer.SLDFILE) && !name.equals(Layer.DEFAULTSTYLES))
          {
            String value = existingLayer.getValue(name);
            layer.setValue(name, value);
          }
        }
      }

      // copy the default layer styles
      Styles existingStyles = existingLayer.getDefaultStyles();
      Styles styles = new Styles();
      copyStyles(existingStyles, styles);
      styles.apply();

      layer.setSemanticId(existingLayer.getSemanticId());
      layer.setDefaultStyles(styles);
      layer.apply();

      // copy all the categories on the layer
      for (AbstractCategory category : existingLayer.getAllHasCategory().getAll())
      {
        AbstractCategory copy;
        try
        {
          copy = (AbstractCategory) LoaderDecorator.load(category.getType()).newInstance();
        }
        catch (Throwable t)
        {
          throw new ProgrammingErrorException(t);
        }

        for (MdAttributeDAOIF mdAttr : copy.getMdClass().definesAttributes())
        {
          String name = mdAttr.definesAttribute();
          if (!mdAttr.isSystem() && !name.equals(AbstractCategory.STYLES) && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
          {
            copy.setValue(name, category.getValue(name));
          }
        }

        Styles cStyles = new Styles();
        copyStyles(category.getStyles(), cStyles);
        cStyles.apply();

        copy.setStyles(cStyles);
        copy.apply();

        layer.addHasCategory(copy).apply();
      }

      // copy the relationship between the SavedMap and Layer
      HasLayers rel = this.addLayer(layer);
      rel.setLayerPosition(existingLayerView.getLayerPosition());
      rel.apply();
    }

    // Copy instances of mapImage
    List<? extends MapImage> mapImages = existingMap.getAllHasImage().getAll();
    for (MapImage image : mapImages)
    {
      MapImage newImage = new MapImage();
      newImage.setImageName(image.getImageName());
      newImage.setImageFilePath(image.getImageFilePath());
      newImage.setImageXPosition(image.getImageXPosition());
      newImage.setImageYPosition(image.getImageYPosition());
      newImage.setCustomImageId(image.getCustomImageId());
      newImage.apply();

      HasImage newHasImage = this.addHasImage(newImage);
      newHasImage.apply();
    }

    // Copy instances of TextElement
    List<? extends TextElement> textElements = existingMap.getAllHasTextElement().getAll();
    for (TextElement textElement : textElements)
    {
      TextElement newTextElement = new TextElement();
      newTextElement.setTextValue(textElement.getTextValue());
      newTextElement.setFontColor(textElement.getFontColor());
      newTextElement.setFontFamily(textElement.getFontFamily());
      newTextElement.setFontSize(textElement.getFontSize());
      newTextElement.setFontStyle(textElement.getFontStyle());
      newTextElement.setCustomTextElementId(textElement.getCustomTextElementId());
      newTextElement.setTextXPosition(textElement.getTextXPosition());
      newTextElement.setTextYPosition(textElement.getTextYPosition());
      newTextElement.apply();

      HasTextElement newHasText = this.addHasTextElement(newTextElement);
      newHasText.apply();
    }

    this.appLock();
    // Copy over the map scale bar information
    this.setScaleBarXPosition(existingMap.getScaleBarXPosition());
    this.setScaleBarYPosition(existingMap.getScaleBarYPosition());
    this.setScaleBarActive(existingMap.getScaleBarActive());

    // Copy over the map north arrow information
    this.setNorthArrowXPosition(existingMap.getNorthArrowXPosition());
    this.setNorthArrowYPosition(existingMap.getNorthArrowYPosition());
    this.setNorthArrowActive(existingMap.getNorthArrowActive());

    // Copy the map zoom information
    this.setZoomLevel(existingMap.getZoomLevel());
    this.setMapCenter(existingMap.getMapCenter());
    this.apply();

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    return defaultMap.getAllLayers();
  }

  /**
   * Removes the given layer from this SavedMap and reorders the other layers accordingly.
   */
  @Override
  @Transaction
  public String deleteLayerFromMap(String layerId)
  {
    Layer layer = Layer.get(layerId);

    String semanticId = layer.getSemanticId();
    layer.delete();

    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();

    try
    {
      int count = 0;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }

    return semanticId;
  }

  /**
   * Moves the given layer to the desired position. All other layers are repositioned accordingly.
   * 
   */
  @Override
  @Transaction
  public void moveLayerOnMap(String layerId, Integer layerPosition)
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();
    List<HasLayers> rels = new LinkedList<HasLayers>();

    try
    {
      int oldIndex = 0;
      boolean found = false;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        if (rel.getChildId().equals(layerId))
        {
          found = true;
        }
        else if (!found)
        {
          oldIndex++;
        }

        rels.add(rel);
      }

      // swap the moved layer position
      rels.add(layerPosition, rels.remove(oldIndex));

      // now reset all layer indexes
      int count = 0;
      for (HasLayers rel : rels)
      {
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }
  }

  public long getLayerCount()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);

    q.WHERE(q.parentId().EQ(this.getId()));

    return q.getCount();
  }

  /**
   * Returns all non-default maps from all users. This way users can view maps that others have created.
   * 
   * @return
   */
  public static SavedMapQuery getAllSavedMaps()
  {
    QueryFactory f = new QueryFactory();
    SavedMapQuery q = new SavedMapQuery(f);

    q.WHERE(q.getType().NE(DefaultSavedMap.CLASS));
    q.AND(q.getDisease().EQ(Disease.getCurrent()));

    return q;
  }

  public static DefaultSavedMap getSessionDefaultMap()
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();

    return defaultMap;
  }

  /**
   * Loads the default map.
   * 
   * @param map
   *          FIXME the map parameter may not be needed
   * @return
   */
  @Transaction
  @Authenticate
  public static SavedMap loadDefaultMap(SavedMap map)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    if (defaultMap != null)
    {
      defaultMap.delete();
    }

    defaultMap = new DefaultSavedMap();
    defaultMap.setDisease(Disease.getCurrent());
    defaultMap.apply();

    settings.appLock();
    settings.setDefaultMap(defaultMap);
    settings.apply();

    return defaultMap;
  }

  public static void cleanOldViews(long olderThan)
  {
    for (String viewName : Database.getViewsByPrefix(Layer.GEO_VIEW_PREFIX))
    {
      String next = viewName;
      try
      {
        long parseLong = Long.parseLong(next.substring(Layer.GEO_VIEW_PREFIX.length()));
        if (parseLong < olderThan)
        {
          try
          {
            MapUtil.deleteMapView(viewName);
          }
          catch (DatabaseException e)
          {
            // This is okay
          }
        }
      }
      catch (NumberFormatException e)
      {
        // This can happen if there's a view that matches the prefix but doesn't
        // have the timestamp. Just ignore it.
      }
    }

    // // Also clean up any old map images (the directory will be recreated when
    // an
    // // image is uploaded)
    //
    // String deploy = DeployProperties.getDeployPath();
    // if (!deploy.endsWith("/"))
    // {
    // deploy += "/";
    // }
    // String imageDir = deploy + QueryConstants.MAP_IMAGES_DIR;
    // File dir = new File(imageDir);
    // if (dir.exists())
    // {
    // try
    // {
    // FileIO.deleteDirectory(dir);
    // }
    // catch (IOException e)
    // {
    // throw new ProgrammingErrorException(e);
    // }
    // }
  }

  /**
   * Cleans up all views older than an hour.
   */
  public static void cleanOldViews()
  {
    long anHourAgo = System.currentTimeMillis() - ( 60 * 60 * 1000 );
    cleanOldViews(anHourAgo);
  }

  @Override
  @Transaction
  public void updateNorthArrow(Integer northArrowXPosition, Integer northArrowYPosition, Boolean northArrowActive)
  {
    if (northArrowActive)
    {
      this.appLock();
      this.setNorthArrowXPosition(northArrowXPosition);
      this.setNorthArrowYPosition(northArrowYPosition);
      this.setNorthArrowActive(true);
      this.apply();
    }
    else
    {
      this.appLock();
      this.setNorthArrowXPosition(0);
      this.setNorthArrowYPosition(0);
      this.setNorthArrowActive(false);
      this.apply();
    }
  }

  @Override
  @Transaction
  public void updateScaleBar(Integer scaleBarXPosition, Integer scaleBarYPosition, Boolean scaleBarActive)
  {
    if (scaleBarActive)
    {
      this.appLock();
      this.setScaleBarXPosition(scaleBarXPosition);
      this.setScaleBarYPosition(scaleBarYPosition);
      this.setScaleBarActive(true);
      this.apply();
    }
    else
    {
      this.appLock();
      this.setScaleBarXPosition(0);
      this.setScaleBarYPosition(0);
      this.setScaleBarActive(false);
      this.apply();
    }
  }

  @Override
  public void updateLegendLocations(String legendLocations)
  {
    try
    {
      JSONObject legendsObj = new JSONObject(legendLocations);
      JSONArray legendsArr = legendsObj.getJSONArray("legends");

      for (int i = 0; i < legendsArr.length(); i++)
      {

        JSONObject legendObj = legendsArr.getJSONObject(i);
        String legendId = legendObj.getString("legendId");
        String layerId = legendId.replace("legend_", "");
        Integer legendTopPos = (int) Math.round(Float.parseFloat(legendObj.getString("top").replace("px", "")));
        Integer legendLeftPos = (int) Math.round(Float.parseFloat(legendObj.getString("left").replace("px", "")));

        Layer layer = Layer.get(layerId);

        layer.appLock();
        layer.setLegendXPosition(legendLeftPos);
        layer.setLegendYPosition(legendTopPos);
        layer.apply();
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public void updateImageLocations(String imageLocations)
  {
    try
    {
      JSONObject imagesObj = new JSONObject(imageLocations);
      JSONArray imagesArr = imagesObj.getJSONArray("images");

      for (int i = 0; i < imagesArr.length(); i++)
      {

        JSONObject imageObj = imagesArr.getJSONObject(i);
        String imageId = imageObj.getString("imageId");
        Integer imageTopPos = (int) Math.round(Float.parseFloat(imageObj.getString("top").replace("px", "")));
        Integer imageLeftPos = (int) Math.round(Float.parseFloat(imageObj.getString("left").replace("px", "")));

        String mdImageId = getImageByCustomImageId(imageId);

        MapImage image = MapImage.get(mdImageId);

        image.appLock();
        image.setImageXPosition(imageLeftPos);
        image.setImageYPosition(imageTopPos);
        image.apply();
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Persists the MapImage to the database
   * 
   * @savedMapId
   * @imageName
   * @imagePath
   */
  @Override
  @Transaction
  public String addMapImage(String savedMapId, String imageName, String imagePath)
  {
    String newImageCustomId = "image_" + IDGenerator.nextID();

    MapImage newImage = new MapImage();
    newImage.setImageName(imageName);
    newImage.setImageFilePath(imagePath);
    newImage.setCustomImageId(newImageCustomId);
    newImage.apply();

    // The relationship to the default map is most important because it will
    // later be passed to the saved map when the user saves
    HasImage defaultHasImage = this.addHasImage(newImage);
    defaultHasImage.apply();

    return newImageCustomId;
  }

  @Override
  public String getImageByCustomImageId(String customImageId)
  {
    String mdImageId = "";
    List<? extends MapImage> childImages = this.getAllHasImage().getAll();

    for (MapImage childImage : childImages)
    {
      String currCustomImageid = childImage.getCustomImageId();

      if (currCustomImageid.equals(customImageId))
      {
        mdImageId = childImage.getId();
      }
    }

    return mdImageId;
  }

  @Override
  public String removeMapImage(String customImageId)
  {
    String imageId = getImageByCustomImageId(customImageId);
    if (imageId != "")
    {
      MapImage image = MapImage.get(imageId);
      image.removeMapImage(imageId, this.getId());
    }

    return customImageId;
  }

  @Override
  @Transaction
  public String removeTextElement(String customTextElementId)
  {
    String textId = getTextByCustomTextElementId(customTextElementId);
    if (textId != "")
    {
      TextElement textElement = TextElement.get(textId);
      textElement.removeTextElement(textId, this.getId());
    }

    return customTextElementId;
  }

  @Override
  public void updateMapState(Integer zoomLevel, String mapCenter, Integer mapWidth, Integer mapHeight)
  {
    this.appLock();
    this.setZoomLevel(zoomLevel);
    this.setMapCenter(mapCenter);
    this.setMapWidth(mapWidth);
    this.setMapHeight(mapHeight);
    this.apply();

    /*
     * Hack work around: We need to apply the zoomLevel and mapCenter to the default map as well as this map.
     */
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();

    if (!this.getId().equals(defaultMap.getId()))
    {
      defaultMap.appLock();
      defaultMap.setZoomLevel(zoomLevel);
      defaultMap.setMapCenter(mapCenter);
      defaultMap.setMapWidth(mapWidth);
      defaultMap.setMapHeight(mapHeight);
      defaultMap.apply();
    }
  }

  /**
   * Persists the TextElement to the database
   * 
   * @savedMapId
   * @textValue
   * @fontColor
   * @fontFamily
   * @fontSize
   * @fontStyle
   * @customTextElementId
   */
  @Override
  @Transaction
  public String addTextElement(String savedMapId, String textValue, String fontColor, String fontFamily, Integer fontSize, String fontStyle, String customTextElementId)
  {
    TextElement newTextElem = new TextElement();
    newTextElem.setTextValue(textValue);
    newTextElem.setFontColor(fontColor);
    newTextElem.setFontFamily(fontFamily);
    newTextElem.setFontSize(fontSize);
    newTextElem.setFontStyle(fontStyle);
    newTextElem.setCustomTextElementId(customTextElementId);

    newTextElem.apply();

    // The relationship to the default map is most important because it will
    // later be passed to the saved map when the user saves
    HasTextElement defaultHasText = this.addHasTextElement(newTextElem);
    defaultHasText.apply();

    return customTextElementId;
  }

  @Override
  @Transaction
  public void updateTextElements(String textElements)
  {
    try
    {
      JSONObject textObjs = new JSONObject(textElements);
      JSONArray textArr = textObjs.getJSONArray("textElements");

      for (int i = 0; i < textArr.length(); i++)
      {

        JSONObject textObj = textArr.getJSONObject(i);
        String textId = textObj.getString("textId");
        Integer textTopPos = (int) Math.round(Float.parseFloat(textObj.getString("top").replace("px", "")));
        Integer textLeftPos = (int) Math.round(Float.parseFloat(textObj.getString("left").replace("px", "")));

        String mdTextId = getTextByCustomTextElementId(textId);

        if (mdTextId != "")
        {
          TextElement text = TextElement.get(mdTextId);

          text.appLock();
          text.setTextXPosition(textLeftPos);
          text.setTextYPosition(textTopPos);
          text.apply();
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Gets the id of the TextElement by the custom id
   * 
   * @customTextElementId
   */
  @Override
  public String getTextByCustomTextElementId(String customTextElementId)
  {
    String mdTextId = "";
    List<? extends TextElement> childTextElements = this.getAllHasTextElement().getAll();
    for (TextElement childTextElement : childTextElements)
    {
      String currCustomTextId = childTextElement.getCustomTextElementId();
      if (currCustomTextId.equals(customTextElementId))
      {
        mdTextId = childTextElement.getId();
      }
    }

    return mdTextId;
  }

  @Override
  public CycleJobView getCycleJobView()
  {
    CycleJobQuery query = new CycleJobQuery(new QueryFactory());
    query.WHERE(query.getSavedMap().EQ(this));
    OIterator<? extends CycleJob> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        CycleJob job = it.next();
        return job.getView();
      }

      CycleJobView view = new CycleJobView();
      view.setSavedMap(this);

      return view;
    }
    finally
    {
      it.close();
    }
  }

  public void deleteSavedImages()
  {
    OIterator<? extends MapImage> imageIterator = this.getAllHasImage();

    try
    {
      for (MapImage image : imageIterator)
      {
        image.removeMapImage(image.getId(), this.getId());
      }
    }
    finally
    {
      imageIterator.close();
    }
  }

  public void deleteCycleJob()
  {
    CycleJobView view = this.getCycleJobView();
    view.delete();
  }

  /**
   * Makes a getMap request to geoserver and returns the response as a ByteArrayOutputStream
   * 
   * @requestURL = geoserver getMap() request url
   */
  private byte[] getMapRequestToImage(String requestURL)
  {
    InputStream inStream = null;
    ByteArrayOutputStream outStream = null;
    try
    {
      URL url = new URL(requestURL);
      inStream = url.openStream();
      outStream = new ByteArrayOutputStream();

      byte[] b = new byte[2048];
      int length;

      while ( ( length = inStream.read(b) ) != -1)
      {
        outStream.write(b, 0, length);
      }
    }
    catch (MalformedURLException e)
    {
      String error = "The URL is not formated correctly.";
      throw new ProgrammingErrorException(error, e);
    }
    catch (IOException e)
    {
      String error = "Could not make the request to the map server.";
      throw new ProgrammingErrorException(error, e);
    }
    finally
    {
      if (inStream != null)
      {
        try
        {
          inStream.close();
        }
        catch (IOException e)
        {
          String error = "Could not close stream.";
          throw new ProgrammingErrorException(error, e);
        }
      }

      if (outStream != null)
      {
        try
        {
          outStream.close();
        }
        catch (IOException e)
        {
          String error = "Could not close stream.";
          throw new ProgrammingErrorException(error, e);
        }
      }
    }

    return outStream.toByteArray();
  }

  /**
   * Builds a map export input stream of all the map elements in a SavedMap.
   * 
   * @outFileFormat = format for the output file (png, jpg, bmp, gif)
   * @mapBounds = bounds of the map (as JSON)
   * @mapSize = size of the map panel <div> (as JSON)
   */
  @Override
  public InputStream generateMapImageExport(String outFileFormat, String mapBounds, String mapSize)
  {
    return this.generateMapImageExport(outFileFormat, mapBounds, mapSize, new MapConfiguration());
  }

  public InputStream generateMapImageExport(String outFileFormat, String mapBounds, String mapSize, MapConfiguration configuration)
  {
    InputStream inStream = null;

    int leftOffset = 305;
    int topOffset = 125;
    int width;
    int height;

    // Get dimensions of the map window (<div>)
    try
    {
      JSONObject mapSizeObj = new JSONObject(mapSize);
      width = mapSizeObj.getInt("width");
      height = mapSizeObj.getInt("height");
    }
    catch (JSONException e)
    {
      String error = "Could not parse map size.";
      throw new ProgrammingErrorException(error, e);
    }

    // Setup the base canvas to which we will add layers and map elements
    BufferedImage base = null;
    Graphics mapBaseGraphic = null;
    try
    {
      if (outFileFormat.toLowerCase().equals("png") || outFileFormat.toLowerCase().equals("gif"))
      {
        base = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      }
      else if (outFileFormat.equals("jpg") || outFileFormat.toLowerCase().equals("bmp"))
      {
        base = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      }

      // Create the base canvas that all other map elements will be draped on top of
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.setColor(Color.white);
      mapBaseGraphic.fillRect(0, 0, width, height);
      mapBaseGraphic.drawImage(base, 0, 0, null);

      // Ordering the layers from the default map
      List<Layer> orderedLayers = Arrays.asList(this.getOrderedLayers());

      // Add layers to the base canvas
      BufferedImage layerCanvas = getLayersExportCanvas(width, height, orderedLayers, mapBounds, configuration);

      // Offset the layerCanvas so that it is center
      int widthOffset = (int) ( ( width - layerCanvas.getWidth() ) / 2 );
      int heightOffset = (int) ( ( height - layerCanvas.getHeight() ) / 2 );

      mapBaseGraphic.drawImage(layerCanvas, widthOffset, heightOffset, null);

      // Add layers to the base canvas
      BufferedImage legendCanvas = getLegendExportCanvas(width, height);
      mapBaseGraphic.drawImage(legendCanvas, 0, 0, null);

      // Add saved TextElements to the base canvas
      BufferedImage imageCanvas = getSavedImageExportCanvas(width, height);
      mapBaseGraphic.drawImage(imageCanvas, 0, 0, null);

      // Add saved TextElements to the base canvas
      BufferedImage textCanvas = getTextElementExportCanvas(width, height);
      mapBaseGraphic.drawImage(textCanvas, 0, 0, null);

      // Add the north arrow to the base canvas
      if (this.getNorthArrowActive())
      {

        String deploy = DeployProperties.getDeployPath();
        String imagesDir = deploy + "/imgs";
        String northArrowPath = imagesDir + "/" + "northArrow.png";
        File northArrow = new File(northArrowPath);
        int savedMapWidth = this.getMapWidth();
        int savedMapHeight = this.getMapHeight();
        int arrowXPositionPercentBased = (int) Math.round( ((double)(this.getNorthArrowXPosition() - leftOffset)) / savedMapWidth * width );
        int arrowYPositionPercentBased = (int) Math.round( ((double)(this.getNorthArrowYPosition() - topOffset)) / savedMapHeight * height );


        try
        {
          BufferedImage northArrowImg = ImageIO.read(northArrow);
          mapBaseGraphic.drawImage(northArrowImg, arrowXPositionPercentBased, arrowYPositionPercentBased, 50, 50, null);
        }
        catch (IOException e)
        {
          String error = "Could not read the north arrow image.";
          throw new ProgrammingErrorException(error, e);
        }

      }
    }
    finally
    {
      ByteArrayOutputStream outStream = null;
      try
      {
        outStream = new ByteArrayOutputStream();
        ImageIO.write(base, outFileFormat, outStream);
        inStream = new ByteArrayInputStream(outStream.toByteArray());
      }
      catch (IOException e)
      {
        String error = "Could not write map image to the output stream.";
        throw new ProgrammingErrorException(error, e);
      }
      finally
      {
        if (outStream != null)
        {
          try
          {
            outStream.close();
          }
          catch (IOException e)
          {
            String error = "Could not close stream.";
            throw new ProgrammingErrorException(error, e);
          }
        }
      }

      if (mapBaseGraphic != null)
      {
        mapBaseGraphic.dispose();
      }
    }

    return inStream;
  }

  /**
   * Builds an image layer of all the layers in a SavedMap.
   * 
   * @mapWidth
   * @mapHeight
   * @orderedLayers
   * @mapBounds
   */
  private BufferedImage getLayersExportCanvas(int mapWidth, int mapHeight, List<Layer> orderedLayers, String mapBounds, MapConfiguration configuration)
  {
    String bottom;
    String top;
    String right;
    String left;
    String processingFormat = "png"; // needed to allow transparency on each overlay before combining to a single map/format
    String appName = CommonProperties.getDeployAppName();
    Graphics mapBaseGraphic = null;
    BufferedImage base = null;

    mapWidth = configuration.getLayerWidth(mapWidth);
    mapHeight = configuration.getLayerHeight(mapHeight);

    try
    {
      base = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.drawImage(base, 0, 0, null);

      // Get bounds of the map
      try
      {
        JSONObject mapBoundsObj = new JSONObject(mapBounds);
        bottom = mapBoundsObj.getString("bottom");
        top = mapBoundsObj.getString("top");
        right = mapBoundsObj.getString("right");
        left = mapBoundsObj.getString("left");
      }
      catch (JSONException e)
      {
        String error = "Could not parse map bounds.";
        throw new ProgrammingErrorException(error, e);
      }

      // Generates map overlays and combines them into a single map image
      for (Layer layer : orderedLayers)
      {
        if (configuration.includeLayer(layer))
        {

          ByteArrayInputStream layerInput = null;
          Graphics2D newOverlayBaseGraphic = null;
          Graphics2D mapLayerGraphic2d = null;

          String layersString = appName + ":" + configuration.getViewName(layer);
          String fileName = QueryConstants.createSLDName(layer.getId());
          String sldString = "http://127.0.0.1:8080/" + appName + "/webDir/" + QueryConstants.SLD_WEB_DIR + fileName + "." + QueryConstants.SLD_EXTENSION;

          StringBuffer requestURL = new StringBuffer();
          requestURL.append(MapUtil.getGeoServerLocalURL() + "/wms?");
          requestURL.append("LAYERS=" + layersString);
          requestURL.append("&");
          requestURL.append("STYLES="); // there are no geoserver styles being added. sld's are used instead
          requestURL.append("&");
          requestURL.append("SLD=" + sldString);
          requestURL.append("&");
          requestURL.append("SRS=EPSG%3A4326");
          requestURL.append("&");
          requestURL.append("TRANSPARENT=true");
          requestURL.append("&");
          requestURL.append("ISBASELAYER=false"); // in the browser the baselayer prop is set for the 1st layer in the map.
          requestURL.append("&");
          requestURL.append("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage");
          requestURL.append("&");
          requestURL.append("FORMAT=image%2F" + processingFormat);
          requestURL.append("&");
          requestURL.append("BBOX=" + left + "," + bottom + "," + right + "," + top);
          requestURL.append("&");
          requestURL.append("WIDTH=" + Integer.toString(mapWidth));
          requestURL.append("&");
          requestURL.append("HEIGHT=" + Integer.toString(mapHeight));

          // Make the getMap request to geoserver for this layer
          // and return a byte[] of the returned image
          byte[] layerOutput = getMapRequestToImage(requestURL.toString());

          try
          {
            layerInput = new ByteArrayInputStream(layerOutput);
            BufferedImage newOverlayBase = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);

            newOverlayBaseGraphic = newOverlayBase.createGraphics();

            // Read the just created image file from the file system
            BufferedImage thisLayerImg = ImageIO.read(layerInput);

            // Add transparency to the layerGraphic
            // This is set in JavaScript in the app so we are replicating browser side transparency settings that are applied to the whole layer
            AlphaComposite thisLayerComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getOpacity().floatValue());
            mapLayerGraphic2d = thisLayerImg.createGraphics();
            newOverlayBaseGraphic.setComposite(thisLayerComposite);

            // Add the current layerGraphic to the base image
            newOverlayBaseGraphic.drawImage(thisLayerImg, 0, 0, null);
            mapBaseGraphic.drawImage(newOverlayBase, 0, 0, null);

          }
          catch (IOException e)
          {
            String error = "Could not read the map request image from the map server.";
            throw new ProgrammingErrorException(error, e);
          }
          finally
          {
            if (newOverlayBaseGraphic != null)
            {
              newOverlayBaseGraphic.dispose();
            }

            if (mapLayerGraphic2d != null)
            {
              mapLayerGraphic2d.dispose();
            }

            if (layerInput != null)
            {
              try
              {
                layerInput.close();
              }
              catch (IOException e)
              {
                String error = "Could not close the stream.";
                throw new ProgrammingErrorException(error, e);
              }
            }
          }
        }
      }
    }
    finally
    {
      mapBaseGraphic.dispose();
    }

    return base;
  }

  /**
   * Builds an image layer of all the layers in a SavedMap.
   * 
   * @mapWidth
   * @mapHeight
   */
  private BufferedImage getLegendExportCanvas(int mapWidth, int mapHeight)
  {

    int leftOffset = 300;
    int topOffset = 125;
    BufferedImage base = null;
    Graphics mapBaseGraphic = null;
    int savedMapWidth = this.getMapWidth();
    int savedMapHeight = this.getMapHeight();

    List<? extends Layer> layers = this.getAllLayer().getAll();

    try
    {
      base = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.drawImage(base, 0, 0, null);

      // Generates map overlays and combines them into a single map image
      for (Layer layer : layers)
      {
        if (layer.getEnableLegend())
        {
          int iconSize = 16;
          int iconPadding = 4;
          int paddedIconWidth = iconSize + ( iconPadding * 2 );
          int borderWidth = 2;
          Graphics2D newLegendTitleBaseGraphic = null;
          int layerXPositionPercentBased = (int) Math.round( ((double)(layer.getLegendXPosition() - leftOffset)) / savedMapWidth * mapWidth );
          int layerYPositionPercentBased = (int) Math.round( ((double)(layer.getLegendYPosition() - topOffset)) / savedMapHeight * mapHeight );


          try
          {
            // handle color graphics and categories
            if (layer.getCreateRawLegend())
            {
              BufferedImage newLegendTitleBase = getLegendTitleImage(layer, 0);

              newLegendTitleBaseGraphic = newLegendTitleBase.createGraphics();
              int paddedTitleWidth = newLegendTitleBase.getWidth();
              int paddedTitleHeight = newLegendTitleBase.getHeight();

              // draw color icon to the right of the title
              int iconLeftPadding = paddedTitleWidth - paddedIconWidth + 4;
              int internalGraphicTopPadding = (int) Math.round( ( paddedTitleHeight - iconSize ) / 2);
              
              newLegendTitleBaseGraphic.setColor(Color.black);
              newLegendTitleBaseGraphic.drawLine(iconLeftPadding - 4, 0, iconLeftPadding - 4, paddedTitleHeight);
              newLegendTitleBaseGraphic.setStroke(new BasicStroke(borderWidth));

              BufferedImage icon = getLegendIcon(iconSize, iconSize, iconPadding, borderWidth, layer.getDefaultStyles().getPolygonFill());

              newLegendTitleBaseGraphic.drawImage(icon, iconLeftPadding, internalGraphicTopPadding, null);
              mapBaseGraphic.drawImage(newLegendTitleBase, layerXPositionPercentBased, layerYPositionPercentBased, paddedTitleWidth, paddedTitleHeight, null);
            }
            else if (layer.getAllHasCategory().getAll().size() > 0)
            {
              // draw categories with title
              BufferedImage categoryLegend = getLegendCategoryImage(layer);
              mapBaseGraphic.drawImage(categoryLegend, layerXPositionPercentBased, layerYPositionPercentBased, null);
            }
            else
            {
              BufferedImage newLegendTitleBase = getLegendTitleImage(layer, 0);

              newLegendTitleBaseGraphic = newLegendTitleBase.createGraphics();
              int paddedTitleWidth = newLegendTitleBase.getWidth();
              int paddedTitleHeight = newLegendTitleBase.getHeight();

              mapBaseGraphic.drawImage(newLegendTitleBase, layerXPositionPercentBased, layerYPositionPercentBased, paddedTitleWidth, paddedTitleHeight, null);
            }
          }
          finally
          {
            if (newLegendTitleBaseGraphic != null)
            {
              newLegendTitleBaseGraphic.dispose();
            }
          }
        }
      }
    }
    finally
    {
      mapBaseGraphic.dispose();
    }

    return base;
  }

  private BufferedImage getLegendTitleImage(Layer layer, int widthOverride)
  {

    FontMetrics fm;
    int textWidth;
    int textHeight;
    int textBoxHorizontalPadding = 4;
    int textBoxVerticalPadding = 4;
    int iconWidth = 20;
    int iconHeight = 20;
    int iconHorizontalPadding = 4;
    int iconVerticalPadding = 4;
    int paddedIconWidth = iconWidth + ( iconHorizontalPadding * 2 );
    int paddedIconHeight = iconHeight + ( iconVerticalPadding * 2 );
    int borderWidth = 2;
    int paddedTitleHeight;
    int paddedTitleWidth;
    int titleLeftPadding = textBoxHorizontalPadding;
    BufferedImage newLegendTitleBase;
    Graphics2D newLegendTitleBaseGraphic = null;

    try
    {
      // Build the Font object
      Font titleFont = getLegendFontObject(layer.getLegendTitleFontFamily(), layer.getLegendTitleFontSize(), layer.getLegendTitleFontStyles().get(0).toString());

      // Build variables for base legend graphic construction
      try
      {
        newLegendTitleBase = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        newLegendTitleBaseGraphic = newLegendTitleBase.createGraphics();

        newLegendTitleBaseGraphic.setFont(titleFont);

        fm = newLegendTitleBaseGraphic.getFontMetrics();
        textHeight = fm.getHeight();
        textWidth = fm.stringWidth(layer.getLegendTitle());

        if (widthOverride > 0)
        {
          paddedTitleWidth = widthOverride;
        }
        else
        {
          paddedTitleWidth = textWidth + ( textBoxHorizontalPadding * 2 ) + ( borderWidth * 2 );
        }

        paddedTitleHeight = textHeight + ( textBoxVerticalPadding * 2 ) + ( borderWidth * 2 );
        // paddedTitleWidth = textWidth + ( textBoxHorizontalPadding * 2 ) + ( borderWidth * 2 );
      }
      finally
      {
        // dispose of temporary graphics context
        if (newLegendTitleBaseGraphic != null)
        {
          newLegendTitleBaseGraphic.dispose();
        }
      }

      // make sure there's enough room for the icon graphic
      if (layer.getCreateRawLegend())
      {
        if (paddedTitleHeight < paddedIconHeight)
        {
          paddedTitleHeight = paddedIconHeight;
        }
        paddedTitleWidth = paddedTitleWidth + paddedIconWidth;
      }
      else
      {
        titleLeftPadding = ( ( paddedTitleWidth / 2 ) - ( ( textWidth + ( textBoxHorizontalPadding * 2 ) + ( borderWidth * 2 ) ) / 2 ) ) + textBoxHorizontalPadding;
      }

      newLegendTitleBase = new BufferedImage(paddedTitleWidth, paddedTitleHeight, BufferedImage.TYPE_INT_ARGB);
      newLegendTitleBaseGraphic = newLegendTitleBase.createGraphics();
      newLegendTitleBaseGraphic.drawImage(newLegendTitleBase, 0, 0, null);

      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      newLegendTitleBaseGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      newLegendTitleBaseGraphic.setFont(titleFont);

      // draw legend background
      newLegendTitleBaseGraphic.setColor(Color.white);
      newLegendTitleBaseGraphic.fillRect(0, 0, paddedTitleWidth, paddedTitleHeight);

      // draw legend border
      if (layer.getShowLegendBorder())
      {
        newLegendTitleBaseGraphic.setColor(Color.black);
        newLegendTitleBaseGraphic.drawRect(0, 0, paddedTitleWidth, paddedTitleHeight);
        newLegendTitleBaseGraphic.setStroke(new BasicStroke(borderWidth));
      }

      // draw title text
      fm = newLegendTitleBaseGraphic.getFontMetrics();
      newLegendTitleBaseGraphic.setColor(Color.decode(layer.getLegendTitleFontFill()));
      newLegendTitleBaseGraphic.drawString(layer.getLegendTitle(), titleLeftPadding, fm.getAscent() + textBoxVerticalPadding);

      newLegendTitleBaseGraphic.drawImage(newLegendTitleBase, 0, 0, null);
    }
    finally
    {
      if (newLegendTitleBaseGraphic != null)
      {
        newLegendTitleBaseGraphic.dispose();
      }
    }

    return newLegendTitleBase;
  }

  private BufferedImage getLegendIcon(int iconWidth, int iconHeight, int iconPadding, int borderWidth, String fillColor)
  {
    int paddedIconWidth = iconWidth + ( iconPadding * 2 );
    int paddedIconHeight = iconHeight + ( iconPadding * 2 );
    Graphics2D iconBaseGraphic = null;
    BufferedImage iconBase;

    try
    {
      iconBase = new BufferedImage(paddedIconWidth + 1, paddedIconHeight + 1, BufferedImage.TYPE_INT_ARGB);
      iconBaseGraphic = iconBase.createGraphics();
      iconBaseGraphic.drawImage(iconBase, 0, 0, null);

      // draw background color
      Color featureFill = Color.decode(fillColor);
      iconBaseGraphic.setColor(featureFill);
      iconBaseGraphic.fillRect(0, 0, iconWidth, iconHeight);

      // draw border
      iconBaseGraphic.setColor(Color.black);
      iconBaseGraphic.drawRect(0, 0, iconWidth, iconHeight);
      iconBaseGraphic.setStroke(new BasicStroke(borderWidth));

      iconBaseGraphic.drawImage(iconBase, 0, 0, null);
    }
    finally
    {
      if (iconBaseGraphic != null)
      {
        iconBaseGraphic.dispose();
      }
    }

    return iconBase;
  }

  @SuppressWarnings("deprecation")
  private BufferedImage getLegendCategoryImage(Layer layer)
  {
    BufferedImage newCategoryBase;
    Graphics2D newCategoryBaseGraphic = null;
    FontMetrics fm;
    int borderWidth = 1;
    int titleWidth;
    int titleHeight;
    int textWidth;
    int textPadding = 2;
    int paddedTextWidth;
    int paddedTitleHeight;
    int paddedTitleWidth;
    int iconWidth = 16;
    int iconHeight = 16;
    int iconPadding = 4;
    int paddedIconWidth = iconWidth + ( iconPadding * 2 );
    int paddedIconHeight = iconHeight + ( iconPadding * 2 );
    int catBaseContainerHeight;
    int legendMaxWidth = 0;
    BufferedImage newLegendTitleBase;
    Graphics2D newLegendTitleBaseGraphic = null;
    BufferedImage catBaseContainer;

    List<? extends AbstractCategory> cats = layer.getAllHasCategory().getAll();
    CategorySorter.sort(cats);

    // Get the title with the default title size
    try
    {
      newLegendTitleBase = getLegendTitleImage(layer, 0);
      newLegendTitleBaseGraphic = newLegendTitleBase.createGraphics();

      paddedTitleWidth = newLegendTitleBase.getWidth() - 1;
      paddedTitleHeight = newLegendTitleBase.getHeight() - 1;
      titleWidth = paddedTitleWidth - ( textPadding * 2 );
      titleHeight = paddedTitleHeight - ( textPadding * 2 );
    }
    finally
    {
      if (newLegendTitleBaseGraphic != null)
      {
        newLegendTitleBaseGraphic.dispose();
      }
    }

    // get the widest category label to set the base category image to the correct size
    Font widestLabel = getLayerWidestCategoryLabel(layer);

    // determine the width of the legend which should be the width of the widest of either the title or widest category
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    FontMetrics widestLabelFM = toolkit.getFontMetrics(widestLabel);

    // calculate the category base container height
    int widestLabelHeight = (int) Math.round(widestLabelFM.getHeight() + ( borderWidth * 2 ) + ( textPadding * 2 ));
    if (widestLabelHeight < paddedIconHeight)
    {
      // make sure there's enough room for the icon graphic
      widestLabelHeight = paddedIconHeight;
    }
    catBaseContainerHeight = ( cats.size() * widestLabelHeight ) + paddedTitleHeight;

    // calculate the category base container width
    int widestLabelWidth = (int) Math.round(widestLabelFM.stringWidth(widestLabel.getName()) + paddedIconWidth + ( borderWidth * 2 ) + ( textPadding * 2 ));
    if (titleWidth < widestLabelWidth)
    {
      legendMaxWidth = widestLabelWidth + textPadding + iconPadding;

      // get a new legend title with an overridden width
      newLegendTitleBase = getLegendTitleImage(layer, legendMaxWidth);
    }
    else
    {
      legendMaxWidth = titleWidth;
    }

    Graphics2D catBaseContainerGraphic = null;
    try
    {
      catBaseContainer = new BufferedImage(legendMaxWidth + 1, catBaseContainerHeight + 1, BufferedImage.TYPE_INT_ARGB);
      catBaseContainerGraphic = catBaseContainer.createGraphics();

      catBaseContainerGraphic.drawImage(catBaseContainer, 0, 0, null);
      catBaseContainerGraphic.drawImage(newLegendTitleBase, 0, 0, legendMaxWidth, paddedTitleHeight, null);

      String catLabel;
      int catNum = 0;
      for (AbstractCategory cat : cats)
      {
        if (cat instanceof RangeCategory)
        {
          String lowerBounds = ( (RangeCategory) cat ).getLowerBoundStr();
          String upperBounds = ( (RangeCategory) cat ).getUpperBoundStr();
          catLabel = lowerBounds + " < " + upperBounds;
        }
        else
        {
          catLabel = ( (NonRangeCategory) cat ).getExactValueStr();
        }

        Font categoryFont = getLegendFontObject(catLabel, layer.getLegendFontSize(), layer.getLegendFontStyles().get(0).toString());

        // Build variables for legend graphic construction
        try
        {
          newCategoryBase = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
          newCategoryBaseGraphic = newCategoryBase.createGraphics();

          newCategoryBaseGraphic.setFont(categoryFont);

          fm = newCategoryBaseGraphic.getFontMetrics();
          textWidth = fm.stringWidth(categoryFont.getName());
          paddedTextWidth = textWidth + ( textPadding * 2 ) + ( borderWidth * 2 ) + paddedIconWidth;
        }
        finally
        {
          // dispose of temporary graphics context
          if (newCategoryBaseGraphic != null)
          {
            newCategoryBaseGraphic.dispose();
          }
        }

        try
        {
          newCategoryBase = new BufferedImage(legendMaxWidth, widestLabelHeight, BufferedImage.TYPE_INT_ARGB);
          newCategoryBaseGraphic = newCategoryBase.createGraphics();
          newCategoryBaseGraphic.drawImage(newCategoryBase, 0, 0, null);

          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
          newCategoryBaseGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
          newCategoryBaseGraphic.setFont(categoryFont);

          // draw legend category background
          newCategoryBaseGraphic.setColor(Color.white);
          newCategoryBaseGraphic.fillRect(0, 0, legendMaxWidth, widestLabelHeight);

          // draw legend category border
          if (layer.getShowLegendBorder())
          {
            newCategoryBaseGraphic.setColor(Color.black);
            newCategoryBaseGraphic.drawRect(0, 0, legendMaxWidth, widestLabelHeight);
            newCategoryBaseGraphic.setStroke(new BasicStroke(borderWidth));
          }

          int iconLeftPadding = legendMaxWidth - ( paddedIconWidth + borderWidth + iconPadding );
          int internalGraphicTopPadding = (int) Math.round( ( widestLabelHeight ) / 2) - ( ( paddedIconHeight ) / 2 ) + iconPadding;

          // draw separator bar
          newCategoryBaseGraphic.setColor(Color.black);
          newCategoryBaseGraphic.drawLine(iconLeftPadding, 0, iconLeftPadding, widestLabelHeight);
          newCategoryBaseGraphic.setStroke(new BasicStroke(borderWidth));

          // draw the colored icon
          MdAttributeDAO md = (MdAttributeDAO) MdAttributeDAO.get(layer.getValue(Layer.LEGENDCOLOR));
          String colorAttribute = md.definesAttribute();
          String color = cat.getStyles().getValue(colorAttribute);

          BufferedImage icon = getLegendIcon(iconWidth, iconHeight, iconPadding, borderWidth, color);
          newCategoryBaseGraphic.drawImage(icon, iconLeftPadding + iconPadding + borderWidth, internalGraphicTopPadding, null);

          // draw category text
          fm = newCategoryBaseGraphic.getFontMetrics();
          newCategoryBaseGraphic.setColor(Color.decode(layer.getLegendFontFill()));
          int fontVertPos = ( ( widestLabelHeight + 1 ) / 2 ) - ( ( fm.getAscent() + fm.getDescent() ) / 2 ) + fm.getAscent();
          newCategoryBaseGraphic.drawString(catLabel, ( ( legendMaxWidth / 2 ) - ( paddedTextWidth / 2 ) ) + textPadding, fontVertPos);

          newCategoryBaseGraphic.drawImage(newCategoryBase, 0, 0, null);
          catBaseContainerGraphic.drawImage(newCategoryBase, 0, paddedTitleHeight + ( widestLabelHeight * catNum ), null);

          catNum++;
        }
        finally
        {
          if (newCategoryBaseGraphic != null)
          {
            newCategoryBaseGraphic.dispose();
          }
        }
      }
    }
    finally
    {
      if (catBaseContainerGraphic != null)
      {
        catBaseContainerGraphic.dispose();
      }
    }

    return catBaseContainer;
  }

  /**
   * Gets the widest label of all the legend items in a layers legend
   * 
   * @layer
   */
  private Font getLayerWidestCategoryLabel(Layer layer)
  {
    String label = "";
    int longest = 0;

    List<? extends AbstractCategory> cats = layer.getAllHasCategory().getAll();
    CategorySorter.sort(cats);

    for (AbstractCategory cat : cats)
    {
      if (cat instanceof RangeCategory)
      {
        String lowerBounds = ( (RangeCategory) cat ).getLowerBoundStr();
        String upperBounds = ( (RangeCategory) cat ).getUpperBoundStr();
        String exactValue = lowerBounds + " < " + upperBounds;
        if (exactValue.length() > longest)
        {
          longest = exactValue.length();
          label = exactValue;
        }
      }
      else
      {
        String exactValue = ( (NonRangeCategory) cat ).getExactValueStr();
        if (exactValue.length() > longest)
        {
          longest = exactValue.length();
          label = exactValue;
        }
      }
    }

    // Build the Font object
    Font labelFont = getLegendFontObject(label, layer.getLegendFontSize(), layer.getLegendFontStyles().get(0).toString());

    return labelFont;
  }

  /*
   * Get the Font object 
   * 
   * @text
   * @fontSize
   * @style
   */
  private Font getLegendFontObject(String text, int fontSize, String style)
  {
    Font font = null;

    // This conversion could be used to better replicate screen pixels as point based pixels
    // but is unneeded at this time. Keeping just in case.
    // int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
    // int convertedFontSize = (int) Math.round((fontSize * screenRes / 72.0) * 0.95);

    int convertedFontSize = fontSize;

    if (style.equals("NORMAL"))
    {
      font = new Font(text, Font.PLAIN, convertedFontSize);
    }
    else if (style.equals("BOLD"))
    {
      font = new Font(text, Font.BOLD, convertedFontSize);
    }
    else if (style.equals("ITALIC"))
    {
      font = new Font(text, Font.ITALIC, convertedFontSize);
    }

    return font;
  }

  /**
   * Builds an image layer of the saved MapImages in a SavedMap.
   * 
   * @mapWidth
   * @mapHeight
   */
  private BufferedImage getSavedImageExportCanvas(int mapWidth, int mapHeight)
  {

    int leftOffset = 300;
    int topOffset = 125;
    Graphics mapBaseGraphic = null;
    BufferedImage base = null;
    int savedMapWidth = this.getMapWidth();
    int savedMapHeight = this.getMapHeight();

    try
    {
      base = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.drawImage(base, 0, 0, null);

      List<? extends MapImage> allImage = this.getAllHasImage().getAll();
      for (MapImage image : allImage)
      {
        
        int imageXPositionPercentBased = (int) Math.round( ((double)(image.getImageXPosition() - leftOffset)) / savedMapWidth * mapWidth );
        int imageYPositionPercentBased = (int) Math.round( ((double)(image.getImageYPosition() - topOffset)) / savedMapHeight * mapHeight );


        String deploy = DeployProperties.getDeployPath();
        String thisImagePath = deploy + "/" + image.getImageFilePath();
        File thisImageFile = new File(thisImagePath);

        // BufferedImage thisImage;
        try
        {
          BufferedImage thisImage = ImageIO.read(thisImageFile);
          mapBaseGraphic.drawImage(thisImage, imageXPositionPercentBased, imageYPositionPercentBased, thisImage.getWidth(), thisImage.getHeight(), null);

        }
        catch (IOException e)
        {
          String error = "Could not read the image from the map server.";
          throw new ProgrammingErrorException(error, e);
        }
      }
    }
    finally
    {
      if (mapBaseGraphic != null)
      {
        mapBaseGraphic.dispose();
      }
    }

    return base;
  }

  /**
   * Builds an image layer of the text elements in a SavedMap.
   * 
   * @mapWidth
   * @mapHeight
   */
  private BufferedImage getTextElementExportCanvas(int mapWidth, int mapHeight)
  {

    int leftOffset = 300;
    int topOffset = 125;
    BufferedImage base = null;
    Graphics mapBaseGraphic = null;
    int savedMapWidth = this.getMapWidth();
    int savedMapHeight = this.getMapHeight();

    try
    {
      base = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
      mapBaseGraphic = base.getGraphics();
      mapBaseGraphic.drawImage(base, 0, 0, null);

      List<? extends TextElement> allText = this.getAllHasTextElement().getAll();
      for (TextElement text : allText)
      {
        int textXPositionPercentBased = (int) Math.round( ((double)(text.getTextXPosition() - leftOffset)) / savedMapWidth * mapWidth );
        int textYPositionPercentBased = (int) Math.round( ((double)(text.getTextYPosition() - topOffset)) / savedMapHeight * mapHeight );
        Graphics2D newTextBaseGraphic = null;
        
        try
        {
          BufferedImage newTextBase = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
          newTextBaseGraphic = newTextBase.createGraphics();

          Font font = new Font(text.getFontFamily(), this.getFontStyleCode(text.getFontStyle()), text.getFontSize());
          newTextBaseGraphic.setFont(font);
          FontMetrics fm = newTextBaseGraphic.getFontMetrics();
          int textWidth = fm.stringWidth(text.getTextValue());
          int textHeight = fm.getHeight();
          newTextBaseGraphic.dispose();

          newTextBase = new BufferedImage(textWidth, textHeight, BufferedImage.TYPE_INT_ARGB);
          newTextBaseGraphic = newTextBase.createGraphics();

          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
          newTextBaseGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
          newTextBaseGraphic.setFont(font);
          newTextBaseGraphic.setColor(Color.decode(text.getFontColor()));

          fm = newTextBaseGraphic.getFontMetrics();
          newTextBaseGraphic.drawString(text.getTextValue(), 0, fm.getAscent());

          mapBaseGraphic.drawImage(newTextBase, textXPositionPercentBased, textYPositionPercentBased, textWidth, textHeight, null);
        }
        finally
        {
          if (newTextBaseGraphic != null)
          {
            newTextBaseGraphic.dispose();
          }
        }
      }
    }
    finally
    {
      if (mapBaseGraphic != null)
      {
        mapBaseGraphic.dispose();
      }
    }

    return base;
  }

  /**
   * Get the Font style constant from the font style representation stored in the database
   * 
   * @fontStyle = the font style string as stored in the database
   */
  private int getFontStyleCode(String fontStyle)
  {
    String style = fontStyle.replaceAll("\\s", "").toUpperCase();
    int styleConstant = Font.PLAIN;

    if (style.contains("FONT-STYLE:BOLD;"))
    {
      styleConstant = Font.BOLD;
    }
    else if (style.contains("FONT-STYLE:ITALIC;"))
    {
      styleConstant = Font.ITALIC;
    }
    else if (style.contains("FONT-STYLE:NORMAL;"))
    {
      styleConstant = Font.PLAIN;
    }

    return styleConstant;
  }

  @Transaction
  public void deleteGeneratedMapsAndView()
  {
    GeneratedMapQuery query = new GeneratedMapQuery(new QueryFactory());
    query.WHERE(query.getSavedMap().EQ(this));

    OIterator<? extends GeneratedMap> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        GeneratedMap generatedMap = it.next();
        generatedMap.delete();
      }
    }
    finally
    {
      it.close();
    }

    MapUtil.deleteMapView(this.getGeneratedMapViewName());
  }

  public String getGeneratedMapViewName()
  {
    String viewName = GENERATED_MAP_VIEW_PREFIX + this.getMapName() + "_" + this.getDisease().getKey();

    // Postgres creates tables/views in lowercase, so enforce that convention
    // here as well so we don't get into trouble with mixed casing.
    viewName = viewName.toLowerCase();
    viewName = viewName.replaceAll("\\s", "_");

    return viewName;
  }

}
