package dss.vector.solutions.entomology;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdAttributeVirtual;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult;
import dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult;
import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult;
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
      //TODO: do from in additation to where
      valueQuery.FROM(mosquitoQuery);
      valueQuery.WHERE(mosquitoQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    if (groupQuery != null)
    {
      //TODO: do from and where
      valueQuery.FROM(groupQuery);
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
        //valueQuery.WHERE((InnerJoin) join);
      }
    }

    valueQuery = joinAssays(valueQuery, mosquitoQuery, "InfectivityAssaysSnapshot", InfectivityAssayTestResult.CLASS);
    valueQuery = joinAssays(valueQuery, mosquitoQuery, "TargetSiteAssaysSnapshot", TargetSiteAssayTestResult.CLASS);
    valueQuery = joinAssays(valueQuery, mosquitoQuery, "MetabolicAssaysSnapshot",MetabolicAssayTestResult.CLASS);

    return QueryUtil.setQueryDates(xml,valueQuery,dateAttribute);

  }

   public static ValueQuery joinAssays(ValueQuery valueQuery,MosquitoQuery mosquitoQuery,String tableName, String klass)
   {
     MdAttributeVirtual[] accessors = MosquitoView.getAccessors(klass);
     boolean joinAssays = false;
     for (MdAttributeVirtual attrib : accessors)
     {
       String acc = attrib.getAttributeName().toLowerCase();
       String result = acc + "_defaultLocale";
       try
       {
         SelectableSQL s =  (SelectableSQL) valueQuery.getSelectable(result);
         s.setSQL(result);
         joinAssays=true;
       }
       catch (QueryException e){}

       String method = acc + "testmethod_defualtLocale";
       try
       {
         SelectableSQL s =  (SelectableSQL) valueQuery.getSelectable(method);
         s.setSQL(method);
         joinAssays=true;
       }
       catch (QueryException e){}

       result = acc + "";
       try
       {
         SelectableSQL s =  (SelectableSQL) valueQuery.getSelectable(result);
         s.setSQL(result);
         joinAssays=true;
       }
       catch (QueryException e){}

       method = acc + "testmethod";
       try
       {
         SelectableSQL s =  (SelectableSQL) valueQuery.getSelectable(method);
         s.setSQL(method);
         joinAssays=true;
       }
       catch (QueryException e){}
     }
     if(joinAssays)
     {
       String sql = MosquitoView.getTempTableSQL(klass,tableName);
       System.out.println(sql);
       Database.parseAndExecute(sql);
       valueQuery.FROM(tableName,tableName);
       valueQuery.WHERE(new InnerJoinEq("id","mosquito",mosquitoQuery.getTableAlias(),"mosquito_id",tableName,tableName));
     }
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

    System.out.println(valueQuery.getSQL());

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
