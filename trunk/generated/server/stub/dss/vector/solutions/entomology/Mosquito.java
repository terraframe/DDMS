package dss.vector.solutions.entomology;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.xml.sax.SAXParseException;

import com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.SelectableSingle;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.NoColumnsAddedException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.query.ThematicVariable;
import dss.vector.solutions.util.QueryConfig;

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
  private static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals, boolean includeGeometry, ThematicLayer thematicLayer)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    ValueQueryParser valueQueryParser;

    try
    {
      valueQueryParser = new ValueQueryParser(xml, valueQuery);
    }
    catch (QueryException e)
    {
      // Check if the error was because no selectables were added.
      Throwable t = e.getCause();
      if (t != null && t instanceof SAXParseException && t.getMessage().contains("{selectable}"))
      {
        NoColumnsAddedException ex = new NoColumnsAddedException();
        throw ex;
      }
      else
      {
        throw e;
      }
    }

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
      thematicLayer.getGeoHierarchy().getGeoEntityClass();
      MdBusiness geoEntityMd = thematicLayer.getGeoHierarchy().getGeoEntityClass();

      MdAttributeGeometry mdAttrGeo = GeoHierarchy.getGeometry(geoEntityMd);

      String attributeName = mdAttrGeo.getAttributeName();

      // FIXME might need a ValueQuery and might need to go after the code below
      String type = geoEntityMd.definesType();
      valueQueryParser.addAttributeSelectable(type, attributeName, "", "");
      valueQueryParser.addAttributeSelectable(type, GeoEntity.ENTITYNAME, "", QueryConstants.ENTITY_NAME_COLUMN);
    }

    List<ValueQuery> leftJoinValueQueries = new LinkedList<ValueQuery>();
    for (String selectedGeoEntityType : selectedUniversals)
    {
      GeoEntityQuery geoEntityQuery = new GeoEntityQuery(queryFactory);

      AllPathsQuery subAllPathsQuery = new AllPathsQuery(queryFactory);
      ValueQuery geoEntityVQ = new ValueQuery(queryFactory);
      MdBusinessDAOIF geoEntityMd = MdBusinessDAO.getMdBusinessDAO(selectedGeoEntityType);

      Selectable selectable1 = geoEntityQuery.getEntityName(geoEntityMd.getTypeName() + "_entityName");
      Selectable selectable2 = geoEntityQuery.getGeoId(geoEntityMd.getTypeName() + "_geoId");

      List<MdBusinessDAOIF> allClasses = geoEntityMd.getAllSubClasses();
      Condition[] geoConditions = new Condition[allClasses.size()];
      for (int i = 0; i < allClasses.size(); i++)
      {
        geoConditions[i] = subAllPathsQuery.getParentUniversal().EQ(allClasses.get(i));
      }

      geoEntityVQ.SELECT(selectable1, selectable2, subAllPathsQuery.getChildGeoEntity("CHILD_ID"));
      geoEntityVQ.WHERE(OR.get(geoConditions));
      geoEntityVQ.AND(subAllPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

      leftJoinValueQueries.add(geoEntityVQ);

      valueQueryParser.setValueQuery(selectedGeoEntityType, geoEntityVQ);
    }

    Map<String, GeneratedEntityQuery> queryMap = valueQueryParser.parse();

    AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(AllPaths.CLASS);
    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    if (allPathsQuery != null)
    {
      List<SelectableSingle> leftJoinSelectables = new LinkedList<SelectableSingle>();
      for (ValueQuery leftJoinVQ : leftJoinValueQueries)
      {
        leftJoinSelectables.add(leftJoinVQ.aReference("CHILD_ID"));
      }

      int size = leftJoinSelectables.size();
      if(size > 0)
      {
        valueQuery.AND(allPathsQuery.getChildGeoEntity().LEFT_JOIN_EQ(leftJoinSelectables.toArray(new SelectableSingle[size])));
      }

      // Join Collection to GeoEntity
      valueQuery.AND(collectionQuery.getGeoEntity().EQ(allPathsQuery.getChildGeoEntity()));
    }

    MosquitoQuery mosquitoQuery = (MosquitoQuery) queryMap.get(Mosquito.CLASS);
    MorphologicalSpecieGroupQuery groupQuery = (MorphologicalSpecieGroupQuery) queryMap.get(MorphologicalSpecieGroup.CLASS);

    if (collectionQuery == null)
    {
      collectionQuery = new MosquitoCollectionQuery(queryFactory);
    }
    String dateAttribute = "";

    // join Mosquito with mosquito collection
    if (mosquitoQuery != null)
    {
      dateAttribute = mosquitoQuery.getTestDate().getQualifiedName();
      valueQuery.WHERE(mosquitoQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    for (Entry<Class<AssayTestResult>, MdAttributeVirtualDAOIF> e : MosquitoView.getAssayMap().entrySet())
    {
      String assayClassName = e.getKey().getCanonicalName();
      AssayTestResultQuery assayQuery = (AssayTestResultQuery) queryMap.get(assayClassName);
      if (assayQuery != null)
      {
        //this is an implicit natural join
        valueQuery.WHERE(assayQuery.getMosquito().getId().EQ(mosquitoQuery.getId()));
        // Left Join the assay Query
       // valueQuery.AND(assayQuery.getMosquito().LEFT_JOIN_EQ(mosquitoQuery.));
      }

    }

    if (groupQuery != null)
    {
      dateAttribute = collectionQuery.getDateCollected().getQualifiedName();
      valueQuery.WHERE(groupQuery.getCollection().getId().EQ(collectionQuery.getId()));
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
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

    ValueQuery valueQuery = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId,String[] restrictingEntities)
  {
    // FIXME put parsing into common place
    String selectedUniversals[];
    try
    {
      JSONArray arr = new JSONArray(config);
      selectedUniversals = new String[arr.length()];
      for(int i=0; i<selectedUniversals.length; i++)
      {
        selectedUniversals[i] = arr.getString(i);
      }
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId, String[] restrictingEntities)
  {
    // FIXME put parsing into common place
    String selectedUniversals[];
    try
    {
      JSONArray arr = new JSONArray(config);
      selectedUniversals = new String[arr.length()];
      for(int i=0; i<selectedUniversals.length; i++)
      {
        selectedUniversals[i] = arr.getString(i);
      }
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, selectedUniversals, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }

  /**
   *
   *
   * @param xml
   * @return
   * @Transaction public static String mapQuery(String xml, String
   *              thematicLayerType, String[] universalLayers, String
   *              savedSearchId) { if (savedSearchId == null ||
   *              savedSearchId.trim().length() == 0) { String error =
   *              "Cannot map a query without a current SavedSearch instance.";
   *              SavedSearchRequiredException ex = new
   *              SavedSearchRequiredException(error); throw ex; }
   *
   *              SavedSearch search = SavedSearch.get(savedSearchId);
   *
   *              if (thematicLayerType == null ||
   *              thematicLayerType.trim().length() == 0) { String error =
   *              "Cannot create a map for search [] without having restricted by a GeoEntity(s)."
   *              ; MapWithoutGeoEntityException ex = new
   *              MapWithoutGeoEntityException(error); throw ex; }
   *
   *              // Create the thematic layer if it does not exist
   *              ThematicLayer thematicLayer = search.getThematicLayer(); if
   *              (thematicLayer == null) { thematicLayer =
   *              ThematicLayer.newInstance(thematicLayerType);
   *              search.setThematicLayer(thematicLayer); } // Update
   *              ThematicLayer if the thematic layer type has changed. else if
   *              (!thematicLayer.getGeoHierarchy().getQualifiedType().equals(
   *              thematicLayerType)) {
   *              thematicLayer.changeLayerType(thematicLayerType); }
   *
   *              ValueQuery query = xmlToValueQuery(xml, thematicLayerType,
   *              true, thematicLayer);
   *
   *              String layers = MapUtil.generateLayers(universalLayers, query,
   *              search, thematicLayer); return layers; }
   */

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
