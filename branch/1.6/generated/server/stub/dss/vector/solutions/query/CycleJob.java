package dss.vector.solutions.query;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableBlob;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;
import com.runwaysdk.system.scheduler.JobHistoryQuery;
import com.runwaysdk.system.scheduler.JobHistoryRecordQuery;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.UserSettings;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.sld.SLDWriter;

public class CycleJob extends CycleJobBase implements com.runwaysdk.generation.loader.Reloadable
{

  private static final long   serialVersionUID        = -1179434183;

  /**
   * Default width & height for generated maps. The height of the map will be calculated from this width and the aspect ratio of the bounding box of all the layers.
   */
  public static final int     DEFAULT_WIDTH           = 773;

  public static final int     DEFAULT_HEIGHT          = 1000;

  private static final int    MARGIN                  = 100;

  /**
   * Layer view alias
   */
  private static final String LAYER_VIEW_ALIAS        = "layerView";

  /**
   * Geo id view column alias
   */
  private static final String GEO_ID_ALIAS            = "geo_id";

  /**
   * Entity name view column alias
   */
  private static final String ENTITY_NAME_ALIAS       = "entity_name";

  /**
   * Geo entity table alias
   */
  private static final String GEO_ENTITY_ALIAS        = "ege";

  /**
   * Source geo entity table alias
   */
  private static final String SOURCE_GEO_ENTITY_ALIAS = "sge";

  /**
   * All paths table alias
   */
  private static final String ALL_PATHS_ALIAS         = "apg";

  public CycleJob()
  {
    super();
  }

  public CycleJobView getView()
  {
    CycleJobView view = new CycleJobView();
    view.populate(this);

    return view;
  }

  /**
   * Populates this CycleJob from the values specified in the view
   * 
   * @param view
   */
  public void populate(CycleJobView view)
  {
    this.setJobName(view.getJobName());
    this.setSavedMap(view.getSavedMap());
    this.setLayerId(view.getLayerId());
    this.setImageHeight(view.getImageHeight());
    this.setImageWidth(view.getImageWidth());
  }

  public Layer getLayer()
  {
    QueryFactory factory = new QueryFactory();

    SavedMapQuery mapQuery = new SavedMapQuery(factory);
    mapQuery.WHERE(mapQuery.getId().EQ(this.getSavedMapId()));

    LayerQuery query = new LayerQuery(factory);
    query.WHERE(query.getSemanticId().EQ(this.getLayerId()));
    query.AND(query.map(mapQuery));

    OIterator<? extends Layer> it = query.getIterator();

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

  @Override
  public void apply()
  {
    SavedMap savedMap = this.getSavedMap();

    if (savedMap != null)
    {
      String jobName = savedMap.getMapName() + " " + savedMap.getDisease().getDisplayLabel();

      this.setJobName(jobName);
      this.setJobId(jobName);
      this.getDescription().setDefaultValue(jobName);

      SessionIF session = Session.getCurrentSession();

      if (session != null)
      {
        SingleActorDAOIF userDAO = session.getUser();
        MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

        UserSettings settings = UserSettings.createIfNotExists(mdssUser);
        DefaultSavedMap defaultMap = settings.getDefaultMap();

        /*
         * This should never happen.  It should be prevented from the front-end.
         */
        if (savedMap.getId().equals(defaultMap.getId()))
        {
          String msg = "A cycle job cannot be defined on a the default map";
          throw new ProgrammingErrorException(msg);
        }
      }
    }

    super.apply();
  }

  @Override
  @Transaction
  public void delete()
  {
    /*
     * Delete the generated maps
     */
    this.getSavedMap().deleteGeneratedMapsAndView();

    super.delete();
  }

  @Transaction
  public void updateSLDs(Layer[] layers, MapConfigurationIF configuration)
  {
    for (Layer layer : layers)
    {
      new SLDWriter(Layer.get(layer.getId()), configuration).write();
    }
  }

  @Transaction
  public Layer[] setupTemplateLayers(SavedMap map)
  {
    Map<Layer, ValueQuery> layers = MapUtil.createDBViews(map.getOrderedLayers(), false, new MapConfiguration(map.getDisease()));

    List<Layer> list = new LinkedList<Layer>();

    // Remove all layers which do not have any data
    Set<Layer> keySet = layers.keySet();
    int i = 0;

    for (Layer layer : keySet)
    {
      ValueQuery valueQuery = layers.get(layer);

      if (i == 0 || valueQuery.getCount() > 0)
      {
        list.add(layer);
      }

      i++;
    }

    return list.toArray(new Layer[list.size()]);
  }

  public void createDatabaseView()
  {
    SavedMap map = this.getSavedMap();

    if (map != null)
    {
      Disease disease = map.getDisease();

      this.createGenereatedMapDatabaseView(map, disease);
    }
  }

  public void deleteDatabaseView()
  {
    SavedMap map = this.getSavedMap();

    if (map != null)
    {
      map.deleteMapView();
    }
  }

  /**
   * Creates a new database view which restricts the output of the layer database view to only include the geo entities within or containing the supplied geo entity.
   * 
   * @param geoId
   * @param layer
   * @return
   */
  private String createGenereatedMapDatabaseView(SavedMap map, Disease disease)
  {
    String viewName = map.getGeneratedMapViewName();

    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    GeneratedMapQuery gmQuery = new GeneratedMapQuery(factory);
    SavedMapQuery smQuery = new SavedMapQuery(factory);

    SelectableChar mapName = smQuery.getMapName();
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
    query.WHERE(smQuery.getId().EQ(map.getId()));
    query.AND(gmQuery.getSavedMap().EQ(smQuery));

    String sql = query.getSQL();

    Database.createView(viewName, sql);

    return viewName;
  }

  /**
   * Creates a new database view which restricts the output of the layer database view to only include the geo entities within or containing the supplied geo entity.
   * 
   * @param geoId
   * @param layer
   * @return
   */
  private void createDatabaseView(String geoId, Layer layer, String viewName)
  {
    /*   select *
    FROM ddms."geo$1413984777140" AS v,
    ddms.geo_entity AS ge,
    ddms.geo_entity AS sge,
    ddms.allpaths_geo AS apg
    WHERE ge.geo_id = v.geoid_v
    AND sge.geo_id = '800803'
    AND ((ge.id = apg.parent_geo_entity AND apg.child_geo_entity = sge.id)
    OR (ge.id = apg.child_geo_entity AND apg.parent_geo_entity = sge.id))
    */

    MdBusinessDAOIF mdGeoEntity = MdBusinessDAO.getMdBusinessDAO(GeoEntity.CLASS);
    MdAttributeConcreteDAOIF mdGeoId = mdGeoEntity.definesAttribute(GeoEntity.GEOID);
    MdAttributeConcreteDAOIF mdId = mdGeoEntity.definesAttribute(GeoEntity.ID);

    MdBusinessDAOIF mdAllPaths = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS);
    MdAttributeConcreteDAOIF mdParentGeoEntity = mdAllPaths.definesAttribute(AllPaths.PARENTGEOENTITY);
    MdAttributeConcreteDAOIF mdChildGeoEntity = mdAllPaths.definesAttribute(AllPaths.CHILDGEOENTITY);

    String innerViewName = layer.getViewName();

    QueryFactory factory = new QueryFactory();
    ValueQuery query = new ValueQuery(factory);

    SelectableSQLCharacter vGeoId = query.aSQLCharacter(GEO_ID_ALIAS, LAYER_VIEW_ALIAS + "." + QueryConstants.GEO_ID_COLUMN);
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
    sql = sql.replaceAll("\\(" + LAYER_VIEW_ALIAS + "." + QueryConstants.GEO_ID_COLUMN + "\\) AS " + GEO_ID_ALIAS + "", LAYER_VIEW_ALIAS + ".*");

    Database.createView(viewName, sql);
  }

  private List<FilterInfo> getFilterInfo()
  {
    Layer layer = this.getLayer();
    String viewName = layer.getViewName();

    ValueQuery query = new ValueQuery(new QueryFactory());
    SelectableSQLCharacter geoIdCol = query.aSQLCharacter(GEO_ID_ALIAS, LAYER_VIEW_ALIAS + "." + QueryConstants.GEO_ID_COLUMN);
    SelectableSQLCharacter entityNameCol = query.aSQLCharacter(ENTITY_NAME_ALIAS, LAYER_VIEW_ALIAS + "." + QueryConstants.ENTITY_NAME_COLUMN);

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

  public static CycleJob get(SavedMap map, String layerId)
  {
    QueryFactory factory = new QueryFactory();

    CycleJobQuery query = new CycleJobQuery(factory);
    query.WHERE(query.getLayerId().EQ(layerId));
    query.AND(query.getSavedMap().EQ(map));

    OIterator<? extends CycleJob> it = query.getIterator();

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

  @Override
  public void execute(ExecutionContext executionContext)
  {
    // Make sure there's not another instance of this job already running.
    QueryFactory qf = new QueryFactory();
    CycleJobQuery cjq = new CycleJobQuery(qf);
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
    
    SavedMap map = this.getSavedMap();
    Disease disease = map.getDisease();
    Layer cycleLayer = this.getLayer();

    /*
     * 1) Delete the existing generated maps
     */
    map.deleteGeneratedMapsAndView();

    /*
     * 2 ) Generate and store new maps
     */
    Layer[] layers = this.setupTemplateLayers(map);

    try
    {
      Map<String, String> views = this.generateViewNames(layers);

      /*
       * 2a) Create the map
       */
      MapConfiguration configuration = new MapConfiguration(views, map.getDisease());

      // Re-print all SLD files for the layers
      this.updateSLDs(layers, configuration);

      List<FilterInfo> list = this.getFilterInfo();

      for (FilterInfo filter : list)
      {
        String filterGeoId = filter.getGeoId();
        String filterEntityName = filter.getEntityName();

        // For every layer in the map create a new database view restricting the output to
        // geo entities which contain or are contained by the current geoId
        try
        {
          /*
           * 2b) Create the database views which will be used for mapping
           */
          for (Layer layer : layers)
          {
            String viewName = views.get(layer.getId());

            this.createDatabaseView(filterGeoId, layer, viewName);
          }

          map.refreshMap(map.getId(), configuration);

          JSONArray array = MapUtil.getThematicBBox(Arrays.asList(layers), configuration);

          double left = array.getDouble(0);
          double bottom = array.getDouble(1);
          double right = array.getDouble(2);
          double top = array.getDouble(3);

          JSONObject mapBounds = new JSONObject();
          mapBounds.put("left", left);
          mapBounds.put("bottom", bottom);
          mapBounds.put("right", right);
          mapBounds.put("top", top);

          int defaultWidth = this.getImageWidth() - MARGIN;
          int defaultHeight = this.getImageHeight() - MARGIN;

          int layerWidth = (int) Math.min(defaultWidth, Math.round( ( ( ( right - left ) / ( top - bottom ) ) * defaultHeight )));
          int layerHeight = (int) Math.min(defaultHeight, Math.round( ( ( ( top - bottom ) / ( right - left ) ) * defaultWidth )));

          JSONObject mapSize = new JSONObject();
          mapSize.put("width", this.getImageWidth());
          mapSize.put("height", this.getImageHeight());

          configuration.setLayerWidth(layerWidth);
          configuration.setLayerHeight(layerHeight);

          InputStream istream = map.generateMapImageExport("png", mapBounds.toString(), mapSize.toString(), configuration);

          /*
           * 2c) Store the new generated maps
           */
          try
          {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();

            try
            {
              FileIO.write(ostream, istream);

              byte[] imageInByte = ostream.toByteArray();

              GeneratedMap generated = new GeneratedMap();
              generated.setMapImage(imageInByte);
              generated.setSavedMap(map);
              generated.setCycleLayerName(cycleLayer.getLayerName());
              generated.setCycleUniversal(cycleLayer.getGeoHierarchy().getDisplayLabel());
              generated.setFilterGeoId(filterGeoId);
              generated.setFilterGeoEntityName(filterEntityName);
              generated.setDisease(disease);
              generated.apply();

//              /*
//              * This is for testing
//              */
//              try
//              {
//                OutputStream tstream = new FileOutputStream(DeployProperties.getJspDir() + "/" + map.getMapName().replaceAll("//s", "") + "-" + filterGeoId + ".png");
//
//                FileIO.write(tstream, new ByteArrayInputStream(generated.getMapImage()));
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
        catch (IOException e)
        {
          throw new ProgrammingErrorException(e);
        }
        catch (JSONException e)
        {
          throw new ProgrammingErrorException(e);
        }
        finally
        {
          Collection<String> viewNames = views.values();

          for (String viewName : viewNames)
          {
            MapUtil.deleteMapView(viewName);
          }

          MapUtil.removeLayers(viewNames.toArray(new String[viewNames.size()]));
        }
      }

      /*
       * 3) Create a user friendly view which can be accessed from birt for the generated maps.
       *    The view name should have the following format m_[mapName]_[disease]
       */
      this.createGenereatedMapDatabaseView(map, disease);
    }
    finally
    {
      for (Layer layer : layers)
      {
        MapUtil.deleteMapView(layer.getViewName());
      }
    }
  }

  private Map<String, String> generateViewNames(Layer[] layers)
  {
    Map<String, String> map = new LinkedHashMap<String, String>();

    for (Layer layer : layers)
    {
      String viewName = Layer.GEO_VIEW_PREFIX + IDGenerator.nextID();

      map.put(layer.getId(), viewName);
    }

    return map;
  }

  @Override
  public Integer getImageWidth()
  {
    if (super.getImageWidth() != null)
    {
      return super.getImageWidth();
    }

    return DEFAULT_WIDTH;
  }

  @Override
  public Integer getImageHeight()
  {
    if (super.getImageHeight() != null)
    {
      return super.getImageHeight();
    }

    return DEFAULT_HEIGHT;
  }

  @Authenticate
  public synchronized JobHistory start()
  {
    return super.start();
  }

  @Authenticate
  public void stop()
  {
    super.stop();
  }

  @Authenticate
  public void pause()
  {
    super.pause();
  }

  @Authenticate
  public void cancel()
  {
    super.cancel();
  }

  @Authenticate
  public void resume()
  {
    super.resume();
  }

  public static void createGeneratedMapViews()
  {
    CycleJobQuery query = new CycleJobQuery(new QueryFactory());

    OIterator<? extends CycleJob> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        CycleJob job = iterator.next();

        try
        {
          job.createDatabaseView();
        }
        catch (Exception e)
        {
          // Do nothing
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static void deleteGeneratedMapViews()
  {
    CycleJobQuery query = new CycleJobQuery(new QueryFactory());

    OIterator<? extends CycleJob> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        CycleJob job = iterator.next();

        try
        {
          job.deleteDatabaseView();
        }
        catch (Exception e)
        {
          // Do nothing
        }
      }
    }
    finally
    {
      iterator.close();
    }
  }

}
