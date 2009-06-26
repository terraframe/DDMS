package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedBusinessQuery;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.MapWithoutGeoEntityException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.query.ThematicVariable;

public class Mosquito extends MosquitoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288152336L;

  public Mosquito()
  {
    super();
  }

  public AssayTestResult[] getTestResults()
  {
    List<AssayTestResult> list = new LinkedList<AssayTestResult>();
    AssayTestResultQuery query = new AssayTestResultQuery(new QueryFactory());

    query.WHERE(query.getMosquito().EQ(this));

    OIterator<? extends AssayTestResult> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }
      return list.toArray(new AssayTestResult[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public void apply()
  {
    boolean first = this.isNew() && !this.isAppliedToDB();

    super.apply();

    // Create a relationship the Collection-Mosquito relationship used for
    // querying
    if (first)
    {
      CollectionMosquito rel = new CollectionMosquito(this.getCollection(), this);
      rel.apply();
    }
  }

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

    view.populateView(this);

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
    if (thematicLayer != null)
    {
      ThematicVariable thematicVariable = thematicLayer.getThematicVariable();
      if (thematicVariable != null)
      {
        String entityAlias = thematicVariable.getEntityAlias();
        String attributeName = thematicVariable.getAttributeName();

        valueQueryParser.setColumnAlias(entityAlias, attributeName, QueryConstants.THEMATIC_DATA_COLUMN);
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
    MorphologicalSpecieGroupQuery groupQuery = (MorphologicalSpecieGroupQuery) queryMap.get(MorphologicalSpecieGroup.CLASS);

    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    if(collectionQuery == null)
    {
       collectionQuery = new MosquitoCollectionQuery(queryFactory);
    }
    String dateAttribute = "";

    // join Mosquito with mosquito collection
    if (mosquitoQuery != null)
    {
      // valueQuery.WHERE(mosquitoQuery.getCollection().EQ(collectionQuery));
      dateAttribute = mosquitoQuery.getTestDate().getQualifiedName();
    }
    if (groupQuery != null)
    {
      // valueQuery.WHERE(groupQuery.getCollection().EQ(collectionQuery));
      dateAttribute = collectionQuery.getDateCollected().getQualifiedName();
    }

    // join collection with geo entity and select that entity type's geometry
    if (geoEntityType != null && geoEntityType.trim().length() > 0)
    {
      GeneratedBusinessQuery businessQuery = (GeneratedBusinessQuery) queryMap.get(geoEntityType);
      valueQuery.WHERE(collectionQuery.getGeoEntity().EQ(businessQuery));
    }

    if (xml.indexOf("DATEGROUP_SEASON") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_SEASON");
      dateGroup.setSQL("SELECT seasonName FROM malariaseason as ms WHERE ms.startdate < " + dateAttribute + " and ms.enddate > " + dateAttribute);
    }

    if (xml.indexOf("DATEGROUP_EPIWEEK") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_EPIWEEK");
      dateGroup.setSQL("to_char(" + dateAttribute + ",'YYYY-IW')");
    }

    if (xml.indexOf("DATEGROUP_MONTH") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_MONTH");
      dateGroup.setSQL("to_char(" + dateAttribute + ",'YYYY-MM')");
    }

    if (xml.indexOf("DATEGROUP_QUARTER") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_QUARTER");
      dateGroup.setSQL("to_char(" + dateAttribute + ",'YYYY-Q')");
    }

    if (xml.indexOf("SpecieRatio") > 0)
    {
      SelectableSQLCharacter specieRatio = (SelectableSQLCharacter) valueQuery.getSelectable("SpecieRatio");
      // valueQuery.valueQuery.g

      specieRatio.setSQL("''");
    }

    String sql = valueQuery.getSQL();
    System.out.println(sql);
    return valueQuery;
  }

  /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  @Transaction
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String xml, String geoEntityType, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    return xmlToValueQuery(xml, geoEntityType, false, null);
  }

  /**
   *
   *
   * @param xml
   * @return
   */
  @Transaction
  public static String mapQuery(String xml, String thematicLayerType, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    if (thematicLayerType == null || thematicLayerType.trim().length() == 0)
    {
      String error = "Cannot create a map for search [] without having restricted by a GeoEntity(s).";
      MapWithoutGeoEntityException ex = new MapWithoutGeoEntityException(error);
      throw ex;
    }

    // Create the thematic layer if it does not exist
    ThematicLayer thematicLayer = search.getThematicLayer();
    if (thematicLayer == null)
    {
      thematicLayer = ThematicLayer.newInstance(thematicLayerType);
      search.setThematicLayer(thematicLayer);
    }
    // Update ThematicLayer if the thematic layer type has changed.
    else if (!thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    ValueQuery query = xmlToValueQuery(xml, thematicLayerType, true, thematicLayer);

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);
    return layers;
  }

  @Override
  public MosquitoView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public MosquitoView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public static MosquitoView getView(String id)
  {
    return Mosquito.get(id).getView();
  }
}
