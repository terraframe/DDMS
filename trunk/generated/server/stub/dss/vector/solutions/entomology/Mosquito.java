package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
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
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoServer;
import dss.vector.solutions.geo.GeoServerReloader;
import dss.vector.solutions.geo.generated.SentinalSiteQuery;

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
    while(iterator.hasNext())
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
      for(MdAttributeDAOIF attributeDAO : attributeDAOs)
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
    GeneratedBusinessQuery businessQuery = (SentinalSiteQuery) queryMap.get(geoEntityType);

    valueQuery.WHERE(collectionQuery.getGeoEntity().EQ(businessQuery));

    // join collection with geo entity
    SentinalSiteQuery ssQuery = (SentinalSiteQuery) queryMap.get(geoEntityType);
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
  public static String mapQuery(String xml, String geoEntityType)
  {
    ValueQuery query = xmlToValueQuery(xml, geoEntityType, true);
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
    GeoServerReloader.reload(sessionId, viewName);

    String dbView = GeoServer.MDSS_NAMESPACE + ":" + viewName;

    JSONArray layers = new JSONArray();
    layers.put(dbView.toLowerCase()); // GeoServer expects lowercase names

    return layers.toString();
  }
}
