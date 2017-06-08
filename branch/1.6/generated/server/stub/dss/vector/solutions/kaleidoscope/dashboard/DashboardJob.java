package dss.vector.solutions.kaleidoscope.dashboard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableBlob;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistoryQuery;
import com.runwaysdk.system.scheduler.JobHistoryRecordQuery;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geoserver.GeoserverBatch;
import dss.vector.solutions.geoserver.GeoserverFacade;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.dashboard.query.ThematicQueryBuilder;
import dss.vector.solutions.query.FilterInfo;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.MapConfiguration;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.util.DatabaseUtil;

public class DashboardJob extends DashboardJobBase implements Reloadable
{
  /**
   * 
   */
  private static final long   serialVersionUID          = 4887949719812133944L;

  /**
   * Default width & height for generated dashboards. The height of the dashboard will be calculated from this width and the aspect ratio of the
   * bounding box of all the layers.
   */
  public static final int     DEFAULT_WIDTH             = 773;

  public static final int     DEFAULT_HEIGHT            = 1000;

  private static final int    MARGIN                    = 0;

  private static final String GENERATED_MAP_VIEW_PREFIX = "k_";

  /**
   * Layer view alias
   */
  private static final String LAYER_VIEW_ALIAS          = "layerView";

  /**
   * Geo id view column alias
   */
  private static final String GEO_ID_ALIAS              = "geo_id";

  /**
   * Entity name view column alias
   */
  private static final String ENTITY_NAME_ALIAS         = "entity_name";

  /**
   * Geo entity table alias
   */
  private static final String GEO_ENTITY_ALIAS          = "ege";

  /**
   * Source geo entity table alias
   */
  private static final String SOURCE_GEO_ENTITY_ALIAS   = "sge";

  /**
   * All paths table alias
   */
  private static final String ALL_PATHS_ALIAS           = "apg";

  public DashboardJob()
  {
    super();
  }

  @Override
  public void apply()
  {
    Dashboard dashboard = this.getDashboard();

    if (dashboard != null)
    {
      String jobName = dashboard.getDisplayLabel().getValue() + " " + dashboard.getDisease().getDisplayLabel();

      this.setJobName(jobName);
      this.setJobId(jobName);
      this.setViewName(this.getGeneratedViewName(dashboard));

      this.getDescription().setDefaultValue(jobName);
    }

    super.apply();
  }

  private String getGeneratedViewName(Dashboard dashboard)
  {
    String viewName = GENERATED_MAP_VIEW_PREFIX + dashboard.getDisplayLabel().getValue() + " " + dashboard.getDisease().getDisplayLabel();

    // Postgres creates tables/views in lowercase, so enforce that convention
    // here as well so we don't get into trouble with mixed casing.
    viewName = GeoHierarchy.getSystemName(viewName, GENERATED_MAP_VIEW_PREFIX, false, "_");
    viewName = viewName.toLowerCase();
    return viewName;
  }

  @Override
  @Transaction
  public void delete()
  {
    /*
     * Delete the generated dashboards
     */
    this.deleteGeneratedMapsAndView();

    super.delete();
  }

  private void deleteGeneratedMapsAndView()
  {
    this.deleteDatabaseView();

    GeneratedDashboardQuery q = new GeneratedDashboardQuery(new QueryFactory());
    q.WHERE(q.getDashboard().EQ(this.getDashboardId()));

    OIterator<? extends GeneratedDashboard> iterator = q.getIterator();

    while (iterator.hasNext())
    {
      GeneratedDashboard gd = iterator.next();
      gd.delete();
    }
  }

  public void createDatabaseView()
  {
    Dashboard dashboard = this.getDashboard();

    if (dashboard != null)
    {
      Disease disease = dashboard.getDisease();

      this.createGenereatedMapDatabaseView(dashboard, disease);
    }
  }

  public void deleteDatabaseView()
  {
    Dashboard dashboard = this.getDashboard();

    if (dashboard != null)
    {
      MapUtil.deleteMapView(this.getViewName());
    }
  }

  /**
   * Creates a new database view which restricts the output of the layer database view to only include the geo entities within or containing the
   * supplied geo entity.
   * 
   * @param geoId
   * @param layer
   * @return
   */
  private String createGenereatedMapDatabaseView(Dashboard dashboard, Disease disease)
  {
    String viewName = this.getViewName();

    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    GeneratedDashboardQuery gmQuery = new GeneratedDashboardQuery(factory);
    DashboardQuery smQuery = new DashboardQuery(factory);

    SelectableChar mapName = smQuery.getName();
    mapName.setUserDefinedAlias("map_name");
    mapName.setColumnAlias("map_name");

    SelectableChar cycleLayerName = gmQuery.getCycleLayerName();
    cycleLayerName.setUserDefinedAlias("cycle_layer_name");
    cycleLayerName.setColumnAlias("cycle_layer_name");

    SelectableChar cycleUniversal = gmQuery.getCycleUniversal();
    cycleUniversal.setUserDefinedAlias("cycle_universal");
    cycleUniversal.setColumnAlias("cycle_universal");

    SelectableChar filterGeoId = gmQuery.getFilterGeoId();
    filterGeoId.setUserDefinedAlias("filter_geo_id");
    filterGeoId.setColumnAlias("filter_geo_id");

    SelectableChar filterGeoEntityName = gmQuery.getFilterGeoEntityName();
    filterGeoEntityName.setUserDefinedAlias("filter_geo_entity_name");
    filterGeoEntityName.setColumnAlias("filter_geo_entity_name");

    SelectableMoment createDate = gmQuery.getCreateDate();
    createDate.setUserDefinedAlias("create_date");
    createDate.setColumnAlias("create_date");

    SelectableBlob mapImage = gmQuery.getMapImage();
    mapImage.setUserDefinedAlias("map_image");
    mapImage.setColumnAlias("map_image");

    query.SELECT(mapName, cycleLayerName, cycleUniversal, filterGeoId, filterGeoEntityName, createDate, mapImage);
    query.WHERE(smQuery.getId().EQ(dashboard.getId()));
    query.AND(gmQuery.getDashboard().EQ(smQuery));

    String sql = query.getSQL();

    Database.createView(viewName, sql);

    return viewName;
  }

  /**
   * Creates a new database view which restricts the output of the layer database view to only include the geo entities within or containing the
   * supplied geo entity.
   *
   * @param geoId
   * @param layer
   * @return
   */
  private void createDatabaseView(String geoId, DashboardLayer layer, String viewName)
  {
    MdBusinessDAOIF mdGeoEntity = MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);
    MdAttributeConcreteDAOIF mdGeoId = mdGeoEntity.definesAttribute(GeoEntity.GEOID);
    MdAttributeConcreteDAOIF mdId = mdGeoEntity.definesAttribute(GeoEntity.ID);

    MdBusinessDAOIF mdAllPaths = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS);
    MdAttributeConcreteDAOIF mdParentGeoEntity = mdAllPaths.definesAttribute(AllPaths.PARENTGEOENTITY);
    MdAttributeConcreteDAOIF mdChildGeoEntity = mdAllPaths.definesAttribute(AllPaths.CHILDGEOENTITY);

    String innerViewName = layer.getViewName();

    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    SelectableSQLCharacter vGeoId = query.aSQLCharacter(GEO_ID_ALIAS, LAYER_VIEW_ALIAS + "." + ThematicQueryBuilder.LOCATION_ALIAS);
    SelectableSQLCharacter eGeoId = query.aSQLCharacter("eGeoId", GEO_ENTITY_ALIAS + "." + mdGeoId.getColumnName());
    SelectableSQLCharacter sGeoId = query.aSQLCharacter("sGeoId", SOURCE_GEO_ENTITY_ALIAS + "." + mdGeoId.getColumnName());
    SelectableSQLCharacter eId = query.aSQLCharacter("eId", GEO_ENTITY_ALIAS + "." + mdId.getColumnName());
    SelectableSQLCharacter sId = query.aSQLCharacter("sId", SOURCE_GEO_ENTITY_ALIAS + "." + mdId.getColumnName());
    SelectableSQLCharacter apPe = query.aSQLCharacter("aParentEntity", ALL_PATHS_ALIAS + "." + mdParentGeoEntity.getColumnName());
    SelectableSQLCharacter apCe = query.aSQLCharacter("aChildEntity", ALL_PATHS_ALIAS + "." + mdChildGeoEntity.getColumnName());

    query.SELECT(vGeoId);
    query.FROM(innerViewName, LAYER_VIEW_ALIAS);
    query.FROM(mdGeoEntity.getTableName(), GEO_ENTITY_ALIAS);
    query.FROM(mdGeoEntity.getTableName(), SOURCE_GEO_ENTITY_ALIAS);
    query.FROM(mdAllPaths.getTableName(), ALL_PATHS_ALIAS);
    query.WHERE(vGeoId.EQ(eGeoId));
    query.AND(sGeoId.EQ(geoId));
    query.AND(OR.get(AND.get(eId.EQ(apPe), apCe.EQ(sId)), AND.get(eId.EQ(apCe), apPe.EQ(sId))));

    String sql = query.getSQL();

    // Regex is dumb, but its the only way to perform a "SELECT *"
    sql = sql.replaceAll("\\(" + LAYER_VIEW_ALIAS + "." + ThematicQueryBuilder.LOCATION_ALIAS + "\\) AS " + GEO_ID_ALIAS + "", LAYER_VIEW_ALIAS + ".*");

    Database.createView(viewName, sql);
  }

  private List<FilterInfo> getFilterInfo(DashboardThematicLayer layer)
  {
    String viewName = layer.getViewName();

    ValueQuery query = new ValueQuery(new QueryFactory());
    SelectableSQLCharacter geoIdCol = query.aSQLCharacter(GEO_ID_ALIAS, LAYER_VIEW_ALIAS + "." + ThematicQueryBuilder.LOCATION_ALIAS);
    SelectableSQLCharacter entityNameCol = query.aSQLCharacter(ENTITY_NAME_ALIAS, LAYER_VIEW_ALIAS + "." + ThematicQueryBuilder.LABEL_ALIAS);

    query.SELECT_DISTINCT(geoIdCol, entityNameCol);
    query.FROM(viewName, LAYER_VIEW_ALIAS);
    query.ORDER_BY_ASC(geoIdCol);

    OIterator<ValueObject> iterator = query.getIterator();
    List<FilterInfo> list = new LinkedList<FilterInfo>();

    try
    {
      while (iterator.hasNext())
      {
        ValueObject vObject = iterator.next();
        String geoId = vObject.getValue(GEO_ID_ALIAS);
        String entityName = vObject.getValue(ENTITY_NAME_ALIAS);

        list.add(new FilterInfo(geoId, entityName));
      }
    }
    finally
    {
      iterator.close();
    }

    return list;
  }

  public static DashboardJob get(Dashboard dashboard)
  {
    QueryFactory factory = new QueryFactory();

    DashboardJobQuery query = new DashboardJobQuery(factory);
    query.WHERE(query.getDashboard().EQ(dashboard));

    OIterator<? extends DashboardJob> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  private DashboardLayer[] setupLayers(Dashboard dashboard, DashboardMap map)
  {
    List<DashboardCondition> conditions = dashboard.getConditions();

    DashboardLayer[] layers = map.getOrderedLayers();

    for (DashboardLayer layer : layers)
    {
      layer.setConditions(conditions);

      layer.createDatabaseView(true);

      DashboardStyle style = layer.getStyles().get(0);
      style.setName(layer.getViewName());
    }

    return layers;
  }

  private Map<String, String> generateViewNames(DashboardLayer[] layers)
  {
    Map<String, String> dashboard = new LinkedHashMap<String, String>();

    for (DashboardLayer layer : layers)
    {
      String viewName = Layer.GEO_VIEW_PREFIX + IDGenerator.nextID();

      dashboard.put(layer.getId(), viewName);
    }

    return dashboard;
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    // Make sure there's not another instance of this job already running.
    if (executionContext != null)
    {
      QueryFactory qf = new QueryFactory();
      DashboardJobQuery cjq = new DashboardJobQuery(qf);
      JobHistoryQuery jhq = new JobHistoryQuery(qf);
      JobHistoryRecordQuery jhrq = new JobHistoryRecordQuery(qf);

      jhq.WHERE(jhq.getStatus().containsExactly(AllJobStatus.RUNNING));
      jhrq.WHERE(jhrq.hasChild(jhq));
      cjq.AND(cjq.getId().EQ(jhrq.parentId()));
      cjq.AND(cjq.getId().EQ(this.getId()));
      jhq.AND(jhq.getId().NE(executionContext.getJobHistory().getId()));

      if (jhq.getCount() > 0)
      {
        // TODO : We should be localizing our exceptions.
        throw new RuntimeException("Only one instance of a job may be running at a time.");
      }
    }

    Dashboard dashboard = this.getDashboard();
    Disease disease = dashboard.getDisease();
    DashboardThematicLayer cycleLayer = (DashboardThematicLayer) this.getLayer();
    DashboardMap map = dashboard.getMap();

    /*
     * 1) Delete the existing generated dashboards
     */
    this.deleteGeneratedMapsAndView();

    /*
     * 2 ) Generate and store new database views
     */
    DashboardLayer[] layers = this.setupLayers(dashboard, map);

    try
    {
      Map<String, String> views = this.generateViewNames(layers);

      /*
       * 2a) Create the dashboard
       */
      MapConfiguration configuration = new MapConfiguration(views, dashboard.getDisease());

      List<FilterInfo> list = this.getFilterInfo(cycleLayer);

      for (FilterInfo filter : list)
      {
        String filterGeoId = filter.getGeoId();
        String filterEntityName = filter.getEntityName();

        // For every layer in the dashboard create a new database view restricting the output to
        // geo entities which contain or are contained by the current geoId
        try
        {

          /*
           * 2b) Create the database views which will be used for mapping
           */
          GeoserverBatch batch = new GeoserverBatch();

          for (DashboardLayer layer : layers)
          {
            String viewName = views.get(layer.getId());

            this.createDatabaseView(filterGeoId, layer, viewName);

            batch.addLayerToDrop(layer);
            batch.addLayerToPublish(layer);
          }

          /*
           * 2c) Publish the database layers
           */
          GeoserverFacade.pushUpdates(batch);

          JSONArray array = MapUtil.getThematicBBox(Arrays.asList(layers), configuration, .5F);

          double left = array.getDouble(0);
          double bottom = array.getDouble(1);
          double right = array.getDouble(2);
          double top = array.getDouble(3);

          JSONObject bounds = new JSONObject();
          bounds.put("left", left);
          bounds.put("bottom", bottom);
          bounds.put("right", right);
          bounds.put("top", top);

          JSONObject baseMap = new JSONObject();
          baseMap.put("NAME", "Open Street Map");
          baseMap.put("LOCLIZATION_KEY", "osmBasic");
          baseMap.put("LAYER_SOURCE_TYPE", "OSM");

          int defaultWidth = this.getImageWidth() - MARGIN;
          int defaultHeight = this.getImageHeight() - MARGIN;

          int layerWidth = (int) Math.min(defaultWidth, Math.round( ( ( ( right - left ) / ( top - bottom ) ) * defaultHeight )));
          int layerHeight = (int) Math.min(defaultHeight, Math.round( ( ( ( top - bottom ) / ( right - left ) ) * defaultWidth )));

          JSONObject size = new JSONObject();
          size.put("width", this.getImageWidth());
          size.put("height", this.getImageHeight());
          size.put("layerWidth", layerWidth);
          size.put("layerHeight", layerHeight);

          InputStream istream = map.generateMapImageExport("png", bounds.toString(), size.toString(), baseMap.toString(), layers);

          /*
           * 2c) Store the new generated dashboards
           */
          try
          {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();

            try
            {
              FileIO.write(ostream, istream);

              byte[] imageInByte = ostream.toByteArray();

              AggregationStrategy strategy = cycleLayer.getAggregationStrategy();

              GeneratedDashboard generated = new GeneratedDashboard();
              generated.setMapImage(imageInByte);
              generated.setDashboard(dashboard);
              generated.setCycleLayerName(cycleLayer.getLayerName());
              generated.setFilterGeoId(filterGeoId);
              generated.setFilterGeoEntityName(filterEntityName);
              generated.setDisease(disease);

              if (strategy instanceof UniversalAggregationStrategy)
              {
                generated.setCycleUniversal( ( (UniversalAggregationStrategy) strategy ).getUniversal().getDisplayLabel());
              }

              generated.apply();

//              /*
//               * This is for testing
//               */
//              try
//              {
//                OutputStream tstream = new FileOutputStream(dashboard.getName().replaceAll("//s", "") + "-" + filterGeoId + ".png");
//
//                FileIO.write(tstream, new ByteArrayInputStream(imageInByte));
//              }
//              catch (Exception e)
//              {
//                e.printStackTrace();
//              }
            }
            finally
            {
              ostream.close();
            }
          }
          finally
          {
            istream.close();
          }
        }
        catch (JSONException | IOException e)
        {
          throw new ProgrammingErrorException(e);
        }
        finally
        {
          /*
           * Remove the temp views
           */
          Collection<String> viewNames = views.values();

          for (String viewName : viewNames)
          {
            DatabaseUtil.dropView(viewName, "", false);
          }

          /*
           * Remove the temp geoserver layer and styles
           */
          GeoserverBatch batch = new GeoserverBatch();

          for (DashboardLayer layer : layers)
          {
            batch.addLayerToDrop(layer);
          }

          GeoserverFacade.pushUpdates(batch);
        }
      }

      /*
       * 3) Create a user friendly view which can be accessed from birt for the generated dashboards. The view name should have the following format
       */
      this.createGenereatedMapDatabaseView(dashboard, disease);
    }
    finally
    {
      for (DashboardLayer layer : layers)
      {
        DatabaseUtil.dropView(layer.getViewName(), "", false);
      }
    }
  }

  public Integer getImageWidth()
  {
    Integer width = super.getImageWidth();

    if (width != null)
    {
      return width;
    }

    return DEFAULT_WIDTH;
  }

  public Integer getImageHeight()
  {
    Integer height = super.getImageHeight();

    if (height != null)
    {
      return height;
    }

    return DEFAULT_HEIGHT;
  }

  public static String getJSON(String dashboardId)
  {
    try
    {
      Dashboard dashboard = Dashboard.get(dashboardId);

      DashboardJob job = DashboardJob.get(dashboard);

      if (job == null)
      {
        job = new DashboardJob();
        job.setDashboard(dashboard);
      }
      else
      {
        job.lock();
      }

      JSONArray jLayers = new JSONArray();

      DashboardLayer[] layers = dashboard.getMap().getOrderedLayers();

      for (DashboardLayer layer : layers)
      {
        JSONObject jLayer = new JSONObject();
        jLayer.put("id", layer.getId());
        jLayer.put("label", layer.getLayerName());

        jLayers.put(jLayer);
      }

      JSONObject json = new JSONObject();
      json.put("width", job.getImageWidth());
      json.put("height", job.getImageHeight());
      json.put("id", job.getId());
      json.put("layer", job.getLayerId());
      json.put("dashboard", job.getDashboardId());
      json.put("new", job.isNew());

      JSONObject response = new JSONObject();
      response.put("job", json);
      response.put("layers", jLayers);

      return response.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  public static void applyJSON(String json)
  {
    try
    {
      JSONObject object = new JSONObject(json);

      DashboardJob job = null;

      if (object.getBoolean("new"))
      {
        job = new DashboardJob();
        job.setDashboard(Dashboard.get(object.getString("dashboard")));
      }
      else
      {
        String id = object.getString("id");
        job = DashboardJob.get(id);
      }

      job.setImageHeight(object.getInt("height"));
      job.setImageWidth(object.getInt("width"));
      job.setLayer(DashboardLayer.get(object.getString("layer")));
      job.apply();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
}