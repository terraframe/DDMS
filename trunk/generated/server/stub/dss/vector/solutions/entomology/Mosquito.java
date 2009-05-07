package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedBusinessQuery;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSiteQuery;
import dss.vector.solutions.query.Mapping;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;

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
  @Transaction
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
  private static ValueQuery xmlToValueQuery(String xml, String geoEntityType, boolean includeGeometry, ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    // include the thematic layer (if applicable).
    if(thematicLayer != null)
    {
      Matcher matcher = thematicLayer.matchOnThematicVariable();
      if(matcher != null)
      {
        String entityAlias = matcher.group(ThematicLayer.TYPE_GROUP);
        String variable = matcher.group(ThematicLayer.VARIABLE_GROUP);

        valueQueryParser.addAttributeSelectable(entityAlias, variable, "", QueryConstants.THEMATIC_DATA_COLUMN);
      }
    }

    // include the geometry of the GeoEntity
    if (includeGeometry)
    {
      MdBusiness geoEntityMd = MdBusiness.getMdBusiness(geoEntityType);

      MdAttributeGeometry mdAttrGeo = GeoHierarchy.getGeometry(geoEntityMd);

      String attributeName = mdAttrGeo.getAttributeName();

      valueQueryParser.addAttributeSelectable(geoEntityType, attributeName, "", "");
      valueQueryParser.addAttributeSelectable(geoEntityType, GeoEntity.ENTITYNAME, "", QueryConstants.ENTITY_NAME_COLUMN);
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

    return valueQuery;
  }

  /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String xml, String geoEntityType)
  {
    return xmlToValueQuery(xml, geoEntityType, false, null);
  }

  /**
   * Creates a
   *
   * @param xml
   * @return
   */
  public static String mapQuery(String xml, String thematicLayerType, String[] universalLayers, String savedSearchId)
  {
    if(savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);
    ThematicLayer thematicLayer = search.getThematicLayer();

    ValueQuery query = xmlToValueQuery(xml, thematicLayerType, true, thematicLayer);

    String layers = Mapping.generateLayers(universalLayers, query, search, thematicLayer);
    return layers;
  }
}
