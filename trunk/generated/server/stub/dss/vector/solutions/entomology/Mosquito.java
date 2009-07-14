package dss.vector.solutions.entomology;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.SelectableSQLDate;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultQuery;
import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResultQuery;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryConfig;
import dss.vector.solutions.util.QueryUtil;

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

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, MosquitoCollection.CLASS, MosquitoCollection.GEOENTITY);

    MosquitoQuery mosquitoQuery = (MosquitoQuery) queryMap.get(Mosquito.CLASS);
    UninterestingSpecieGroupQuery groupQuery = (UninterestingSpecieGroupQuery) queryMap.get(UninterestingSpecieGroup.CLASS);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    if (mosquitoQuery != null)
    {
      valueQuery.WHERE(mosquitoQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }



    for (Entry<Class<AssayTestResult>, MdAttributeVirtualDAOIF> e : MosquitoView.getAssayMap().entrySet())
    {
      String assayClassName = e.getKey().getCanonicalName();
      AssayTestResultQuery assayQuery = (AssayTestResultQuery) queryMap.get(assayClassName);
      if (assayQuery != null)
      {
        // this is an implicit natural join
         valueQuery.WHERE(assayQuery.getMosquito().getId().EQ(mosquitoQuery.getId()));
        //Left Join the assay Query
        //valueQuery.AND(assayQuery.getMosquito().LEFT_JOIN_EQ(mosquitoQuery));

         String tableName = assayQuery.getId().getDefiningTableName();
         String tableAlias = assayQuery.getId().getDefiningTableAlias();
         String testMethodAlias = tableName.replace("testresult", "_TESTMETHOD");

         String assayType = null;
         String testMethodType = null;

         if(assayQuery instanceof InfectivityAssayTestResultQuery)
         {
           assayType = "infectivityassaytestresult";
           testMethodType = "infectivitymethodology";
         }

         if(assayQuery instanceof TargetSiteAssayTestResultQuery)
         {
           assayType = "targetsiteassaytestresult";
           testMethodType = "insecticidemethodology";
         }

         if (xml.indexOf(testMethodAlias) > 0)
         {
           SelectableSQLCharacter testMethod = (SelectableSQLCharacter) valueQuery.getSelectable(testMethodAlias);
           {
             testMethod.setSQL("SELECT label.defaultLocale \n" +
                     "     FROM " + assayType + "  tr LEFT JOIN " + testMethodType + " im on tr.testMethod = im.id\n" +
                     "     LEFT JOIN abstractterm term ON im.id = term.id \n" +
                     "     LEFT JOIN abstracttermdisplaylabel label ON term.displayLabel = label.id\n" +
                     "     WHERE tr.id = " + tableAlias + ".id");
           }
         }

      }

    }

    if (groupQuery != null)
    {
      valueQuery.WHERE(groupQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    if (xml.indexOf("SpecieRatio") > 0)
    {
      SelectableSQLCharacter specieRatio = (SelectableSQLCharacter) valueQuery.getSelectable("SpecieRatio");
      specieRatio.setSQL("''");
    }

    SelectableMoment dateAttribute = collectionQuery.getDateCollected();
    ConcreteMosquitoCollectionQuery concreteCollectionQuery = (ConcreteMosquitoCollectionQuery) queryMap.get(ConcreteMosquitoCollection.CLASS);
    //this ensures that the date attribute is joined correctly
    if (concreteCollectionQuery == null)
    {
      valueQuery.FROM(dateAttribute.getDefiningTableName(), dateAttribute.getDefiningTableAlias());
      for(Join join: dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
    }

    return setQueryDates(xml,valueQuery,dateAttribute);
  }

  private static ValueQuery setQueryDates(String xml , ValueQuery valueQuery,  SelectableMoment dateAttribute)
  {

    String da = dateAttribute.getQualifiedName();

    if (xml.indexOf("DATEGROUP_SEASON") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_SEASON");
      dateGroup.setSQL("SELECT seasonName FROM malariaseason as ms WHERE ms.startdate < " + da + " and ms.enddate > " + da);
    }

    if (xml.indexOf("DATEGROUP_EPIWEEK") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_EPIWEEK");
      dateGroup.setSQL("to_char(" + da + ",'IW')");
    }

    if (xml.indexOf("DATEGROUP_MONTH") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_MONTH");
      dateGroup.setSQL("to_char(" + da + ",'MM')");
    }

    if (xml.indexOf("DATEGROUP_QUARTER") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_QUARTER");
      dateGroup.setSQL("to_char(" + da + ",'Q')");
    }

    if (xml.indexOf("DATEGROUP_YEAR") > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable("DATEGROUP_YEAR");
      dateGroup.setSQL("to_char(" + da + ",'YYYY')");
    }

    if (xml.indexOf("START_DATE_RANGE") > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectable("START_DATE_RANGE");
      dateGroup.setSQL("''");
      Pattern pattern = Pattern.compile("<operator>GE</operator>\\n<value>(\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d)</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'"+matcher.group(1)+"'");
      }
    }

    if (xml.indexOf("END_DATE_RANGE") > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectable("END_DATE_RANGE");
      dateGroup.setSQL("''");

      Pattern pattern = Pattern.compile("<operator>LE</operator>\\n<value>(\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d)</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'"+matcher.group(1)+"'");
      }
    }


    String sql = valueQuery.getSQL();
    System.out.println(sql);

    return valueQuery;

  }  /**
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
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

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
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    QueryConfig queryConfig = new QueryConfig(config);
    String[] selectedUniversals = queryConfig.getSelectedUniversals();

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
