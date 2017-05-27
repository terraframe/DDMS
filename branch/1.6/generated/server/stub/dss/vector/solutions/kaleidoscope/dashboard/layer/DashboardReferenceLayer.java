package dss.vector.solutions.kaleidoscope.dashboard.layer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geoserver.GeoserverFacade;
import dss.vector.solutions.kaleidoscope.dashboard.Dashboard;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMap;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyle;
import dss.vector.solutions.kaleidoscope.dashboard.Drilldown;
import dss.vector.solutions.kaleidoscope.dashboard.query.ThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.wrapper.FeatureType;
import dss.vector.solutions.kaleidoscope.wrapper.MapVisitor;
import dss.vector.solutions.kaleidoscope.wrapper.ReferenceLayer;
import dss.vector.solutions.util.QueryUtil;

public class DashboardReferenceLayer extends DashboardReferenceLayerBase implements com.runwaysdk.generation.loader.Reloadable, ReferenceLayer
{
  private static final long serialVersionUID = -1393835330;

  public DashboardReferenceLayer()
  {
    super();
  }

  @Override
  public void accepts(MapVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override
  protected DashboardLayer newInstance()
  {
    return new DashboardReferenceLayer();
  }

  public void setName(String val)
  {
    this.getNameLabel().setValue(val);
  }

  @Override
  public String getJSON()
  {
    return this.toJSON().toString();
  }

  @Override
  public JSONObject toJSON()
  {
    DashboardMap map = this.getDashboardMap();
    Dashboard dashboard = map.getDashboard();
    Map<String, Integer> indices = dashboard.getUniversalIndices();

    return this.toJSON(indices);
  }

  /**
   * @prerequisite conditions is populated with any DashboardConditions necessary for restricting the view dataset.
   * 
   * @return A ValueQuery for use in creating/dropping the database view which will be used with GeoServer.
   */
  @Override
  public ValueQuery getViewQuery(LinkedList<Drilldown> drilldowns)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    OIterator<? extends DashboardStyle> iter = this.getAllHasStyle();
    try
    {
      while (iter.hasNext())
      {
        DashboardStyle style = iter.next();
        if (style instanceof DashboardStyle)
        {
          GeoHierarchy universal = this.getUniversal(drilldowns);
          MdBusinessDAOIF mdBusiness = MdBusinessDAO.get(universal.getGeoEntityClassId());

          // geoentity label
          GeoEntityQuery geQ1 = (GeoEntityQuery) QueryUtil.getQuery(mdBusiness, query);
          SelectableSingle label = geQ1.getEntityLabel().localize(GeoEntity.ENTITYLABEL);
          label.setColumnAlias(ThematicQueryBuilder.LABEL_ALIAS);
          label.setUserDefinedAlias(ThematicQueryBuilder.LABEL_ALIAS);

          // geo id (for uniqueness)
          Selectable geoId1 = geQ1.getGeoId(GeoEntity.GEOID);
          geoId1.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
          geoId1.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);

          query.SELECT(label);
          query.SELECT(geoId1);

          Selectable geom;
          if (this.getFeatureType().equals(FeatureType.POINT))
          {
            geom = geQ1.get(GeoEntity.GEOPOINT);
          }
          else
          {
            geom = geQ1.get(GeoEntity.GEOMULTIPOLYGON);
          }

          geom.setColumnAlias(GeoserverFacade.GEOM_COLUMN);
          geom.setUserDefinedAlias(GeoserverFacade.GEOM_COLUMN);
          query.SELECT(geom);
        }
      }
    }
    finally
    {
      iter.close();
    }

    if (log.isDebugEnabled())
    {
      // print the SQL if the generated
      log.debug("SLD for Layer [%s], this:\n" + query.getSQL());
    }

    this.viewHasData = true;
    if (query.getCount() == 0)
    {
      EmptyLayerInformation info = new EmptyLayerInformation();
      info.setLayerName(this.getName());
      info.apply();

      info.throwIt();

      this.viewHasData = false;
    }

    return query;
  }

  @Override
  protected void populate(DashboardLayer source)
  {
    super.populate(source);

    if (source instanceof DashboardReferenceLayer)
    {
      DashboardReferenceLayer tSource = (DashboardReferenceLayer) source;

      this.setUniversal(tSource.getUniversal());
    }
  }

  private GeoHierarchy getUniversal(LinkedList<Drilldown> drilldowns)
  {
    if (drilldowns.size() > 0)
    {
      Drilldown drilldown = drilldowns.getLast();
      Map<String, String> universals = drilldown.getUniversals();

      if (universals.containsKey(this.getId()))
      {
        String universalId = universals.get(this.getId());

        return GeoHierarchy.get(universalId);
      }
    }

    return this.getUniversal();
  }

  /**
   * JSONObject with containing all options used in the CRUD form.
   * 
   * @return
   */
  public static String getOptionsJSON(String dashboardId)
  {
    try
    {
      Dashboard dashboard = Dashboard.get(dashboardId);

      String[] fonts = DashboardThematicStyle.getSortedFonts();

      // Set possible layer types based on attribute type
      Map<String, String> layerTypes = new LinkedHashMap<String, String>();
      layerTypes.put(AllLayerType.BASICPOINT.getEnumName(), AllLayerType.BASICPOINT.getDisplayLabel());
      layerTypes.put(AllLayerType.BASICPOLYGON.getEnumName(), AllLayerType.BASICPOLYGON.getDisplayLabel());

      JSONArray pointTypes = new JSONArray();
      pointTypes.put("CIRCLE");
      pointTypes.put("STAR");
      pointTypes.put("SQUARE");
      pointTypes.put("TRIANGLE");
      pointTypes.put("CROSS");
      pointTypes.put("X");

      JSONObject object = new JSONObject();
      object.put("availableFonts", new JSONArray(Arrays.asList(fonts)));
      object.put("layerTypeNames", new JSONArray(layerTypes.keySet()));
      object.put("layerTypeLabels", new JSONArray(layerTypes.values()));
      object.put("pointTypes", pointTypes);

      object.put("universals", getUniversalsJSON(dashboard));

      return object.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Returns the reference layer options.
   * 
   * @return
   */
  public static JSONArray getUniversalsJSON(Dashboard dashboard)
  {
    try
    {
      DashboardMap map = dashboard.getMap();

      GeoHierarchy country = GeoHierarchy.getGeoHierarchyFromType(Country.CLASS);

      JSONArray array = new JSONArray();

      Map<String, JSONObject> options = new LinkedHashMap<String, JSONObject>();

      List<GeoHierarchy> children = country.getAllChildren();

      for (GeoHierarchy child : children)
      {
        JSONObject object = new JSONObject();
        object.put("value", child.getId());
        object.put("label", child.getDisplayLabel());

        options.put(child.getId(), object);
      }

      // Remove all reference layer options which have already exist
      List<? extends DashboardLayer> layers = map.getAllHasLayer().getAll();

      for (DashboardLayer layer : layers)
      {
        if (layer instanceof DashboardReferenceLayer)
        {
          DashboardReferenceLayer referenceLayer = (DashboardReferenceLayer) layer;

          options.remove(referenceLayer.getUniversalId());
        }
      }

      // Add all options into the array
      for (JSONObject option : options.values())
      {
        array.put(option);
      }

      return array;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

  }

  public JSONObject toJSON(Map<String, Integer> indices)
  {
    try
    {
      JSONObject json = new JSONObject();
      json.put("viewName", getViewName());
      json.put("sldName", getSLDName());
      json.put("layerName", getName());
      json.put("layerId", getId());
      json.put("inLegend", this.getDisplayInLegend());
      json.put("legendXPosition", this.getDashboardLegend().getLegendXPosition());
      json.put("legendYPosition", this.getDashboardLegend().getLegendYPosition());
      json.put("groupedInLegend", this.getDashboardLegend().getGroupedInLegend());
      json.put("featureStrategy", getFeatureStrategy());
      json.put("universalId", this.getUniversalId());
      json.put("mapId", this.getDashboardMapId());
      json.put("layerExists", true);
      json.put("isActive", true);
      json.put("layerType", "REFERENCELAYER");
      json.put("index", indices.get(this.getUniversalId()));

      JSONArray jsonStyles = new JSONArray();
      List<? extends DashboardStyle> styles = this.getStyles();
      for (int i = 0; i < styles.size(); ++i)
      {
        DashboardStyle style = styles.get(i);
        jsonStyles.put(style.toJSON());
      }
      json.put("styles", jsonStyles);

      return json;
    }
    catch (JSONException ex)
    {
      log.error("Could not properly form DashboardLayer [" + this.toString() + "] into valid JSON to send back to the client.");
      throw new ProgrammingErrorException(ex);
    }
  }
}