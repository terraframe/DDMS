package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.gis.dataaccess.MdAttributeGeometryDAOIF;
import com.terraframe.mojo.query.GeneratedBusinessQuery;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoServerReloader;
import dss.vector.solutions.geo.generated.SentinelSiteQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;

public class Mosquito extends MosquitoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288152336L;

  public Mosquito()
  {
    super();
  }

  public List<AssayTestResult> getTestResults()
  {
    List<AssayTestResult> list = new LinkedList<AssayTestResult>();
    AssayTestResultQuery query = new AssayTestResultQuery(new QueryFactory());

    query.WHERE(query.getMosquito().EQ(this));

    OIterator<? extends AssayTestResult> iterator = query.getIterator();
    while (iterator.hasNext())
    {
      list.add(iterator.next());
    }

    iterator.close();

    return list;
  }

  @Override
  public void delete()
  {
    // DELETE all of the mosquito test results first
    for (AssayTestResult result : this.getTestResults())
    {
      result.delete();
    }

    super.delete();
  }

  public MosquitoView getView()
  {
    MosquitoView view = new MosquitoView();

    view.setSpecie(this.getSpecie());
    view.setCollection(this.getCollection());
    view.setGeneration(this.getGeneration());
    view.setIsofemale(this.getIsofemale());
    view.setIdentificationMethod(this.getIdentificationMethod());
    view.setTestDate(this.getTestDate());
    view.setMosquitoId(this.getId());
    view.setSampleId(this.getSampleId());

    if (this.getSex().size() > 0)
    {
      view.addSex(this.getSex().get(0));
    }

    try
    {
      view.setAssays(this.getTestResults());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }

    view.applyNoPersist();

    return view;
  }

  public AssayTestResult getTestResult(Class<AssayTestResult> c)
  {
    for (AssayTestResult result : this.getTestResults())
    {
      if (c.isInstance(result))
      {
        return result;
      }
    }

    return null;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  private static ValueQuery xmlToValueQuery(String xml, String geoEntityType, boolean includeGeometry)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    // include the geometry of the GeoEntity
    if (includeGeometry)
    {
      MdBusiness geoEntityMd = MdBusiness.getMdBusiness(geoEntityType);

      MdBusinessDAO geoEntityMdDAO = (MdBusinessDAO) BusinessFacade.getEntityDAO(geoEntityMd);
      List<? extends MdAttributeDAOIF> attributeDAOs = geoEntityMdDAO.getAllDefinedMdAttributes();

      String attributeName = null;
      for (MdAttributeDAOIF attributeDAO : attributeDAOs)
      {
        if (attributeDAO instanceof MdAttributeGeometryDAOIF)
        {
          attributeName = attributeDAO.definesAttribute();
          break;
        }
      }

      valueQueryParser.addAttributeSelectable(geoEntityType, attributeName, "");
    }

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    GeoHierarchy.addGeoHierarchyJoinConditions(valueQuery, queryMap);

    MosquitoQuery mosquitoQuery = (MosquitoQuery) queryMap.get(Mosquito.CLASS);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery collectionQuery = new MosquitoCollectionQuery(queryFactory);
    valueQuery.WHERE(mosquitoQuery.getCollection().EQ(collectionQuery));

    // join collection with geo entity and select that entity type's geometry
    GeneratedBusinessQuery businessQuery = (SentinelSiteQuery) queryMap.get(geoEntityType);

    valueQuery.WHERE(collectionQuery.getGeoEntity().EQ(businessQuery));

    // join collection with geo entity
    SentinelSiteQuery ssQuery = (SentinelSiteQuery) queryMap.get(geoEntityType);
    valueQuery.WHERE(collectionQuery.getGeoEntity().EQ(ssQuery));

    String sql = valueQuery.getSQL();
    System.out.println(sql);

    return valueQuery;
  }

  /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String xml, String geoEntityType)
  {
    return xmlToValueQuery(xml, geoEntityType, false);
  }

  /**
   * Creates a
   *
   * @param xml
   * @return
   */
  public static String mapQuery(String xml, String thematicLayerType, String[] universalLayers)
  {
    ValueQuery query = xmlToValueQuery(xml, thematicLayerType, true);
    String sql = query.getSQL();

    String viewName = "MDSSTest";

    try
    {
      Database.dropView(viewName);
    }
    catch (Exception e)
    {
      // FIXME ignore for testing
    }

    Database.createView(viewName, sql);

    String sessionId = Session.getCurrentSession().getId();

    String baseView = QueryConstants.MDSS_NAMESPACE + ":" + viewName.toLowerCase();

    JSONArray layers = new JSONArray();

    try
    {
      JSONObject baseLayer = new JSONObject();
      baseLayer.put("view", baseView);
      baseLayer.put("sld", "");
      layers.put(baseLayer);
    }
    catch (JSONException e)
    {
      String error = "Unable to define the base layer [" + baseView + "].";
      throw new ProgrammingErrorException(error, e);
    }


    // create views (if needed) for all other layers
    for (String layerId : universalLayers)
    {
      // TODO return null if count(*) == 0 and don't add
      // to layers (do inside createViewTable())
      Layer layer = Layer.get(layerId);
      GeoHierarchy geoH = layer.getGeoHierarchy();

      boolean includeLayer = geoH.createViewTable(sessionId);
      if (includeLayer)
      {
        MdBusiness md = geoH.getGeoEntityClass();
        String layerView = md.getTypeName().toLowerCase() + QueryConstants.VIEW_NAME_SUFFIX;
        String namespacedView = QueryConstants.MDSS_NAMESPACE + ":" + layerView;

        JSONObject layerObj = new JSONObject();

        try
        {
          WebFile file = WebFile.get(layer.getSldFile());
          String fullWebDir = LocalProperties.getWebDirectory();
          if(fullWebDir.endsWith("/"))
          {
            fullWebDir = fullWebDir.substring(0, fullWebDir.length()-1);
          }
          String webDir = fullWebDir.substring(fullWebDir.lastIndexOf("/"));
          if(webDir.startsWith("/"))
          {
            webDir = webDir.substring(1);
          }
          String filePath = webDir+ "/" + file.getFilePath() + file.getFileName() + "." + file.getFileExtension();

          layerObj.put("view", namespacedView);

          // Generated a random query string to force GeoServer to not cache the SLD
          String r = String.valueOf(Math.random());
          r = r.substring(r.length()-6);

          layerObj.put("sld", filePath+ "?a="+r);
        }
        catch (JSONException e)
        {
          String error = "Unable to define the layer for [" + md.getDisplayLabel().getValue() + "].";
          throw new ProgrammingErrorException(error, e);
        }

        layers.put(layerObj);
      }
    }

    MdAttributeGeometry geoAttr = GeoHierarchy.getGeometry(thematicLayerType);
    GeoServerReloader.reload(sessionId, viewName, geoAttr);

    return layers.toString();
  }
}
