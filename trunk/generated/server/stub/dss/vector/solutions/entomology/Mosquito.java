package dss.vector.solutions.entomology;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoin;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdAttributeVirtual;

import dss.vector.solutions.entomology.assay.AssayTestResult;
import dss.vector.solutions.entomology.assay.AssayTestResultQuery;
import dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult;
import dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult;
import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
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
  @Authenticate
  public static ValueQuery xmlToValueQuery(String xml, String config, boolean includeGeometry, ThematicLayer thematicLayer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, queryConfig, thematicLayer, includeGeometry, MosquitoCollection.CLASS, MosquitoCollection.GEOENTITY);

    MosquitoQuery mosquitoQuery = (MosquitoQuery) queryMap.get(Mosquito.CLASS);

    // join Mosquito with mosquito collection
    MosquitoCollectionQuery collectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    valueQuery.FROM(collectionQuery);
    if (mosquitoQuery != null)
    {
      valueQuery.WHERE(mosquitoQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    UninterestingSpecieGroupQuery groupQuery = (UninterestingSpecieGroupQuery) queryMap.get(UninterestingSpecieGroup.CLASS);
    if (groupQuery != null)
    {
      valueQuery.WHERE(groupQuery.getCollection().getId().EQ(collectionQuery.getId()));
    }

    SelectableMoment dateAttribute = collectionQuery.getDateCollected();

    //force the joins for date based selectables
    ConcreteMosquitoCollectionQuery concreteCollectionQuery = (ConcreteMosquitoCollectionQuery) queryMap.get(ConcreteMosquitoCollection.CLASS);
    if(concreteCollectionQuery == null)
    {
      for(Join join: dateAttribute.getJoinStatements())
      {
        valueQuery.WHERE((InnerJoin) join);
      }
    }
    
    for(Entry<String, GeneratedEntityQuery> e : queryMap.entrySet()) {
      if (e.getValue() instanceof AllPathsQuery)
      {
        String key = e.getKey();
        AllPathsQuery allPathsQuery = (AllPathsQuery) e.getValue();
        
        int index1 = key.indexOf("__");
        int index2 = key.lastIndexOf("__");
        String attrib = key.substring(0, index1);
        String klass = key.substring(index1+2, index2).replace("_", ".");
        String attrib2 = key.substring(index2+2,key.length());
        Selectable term  = valueQuery.getSelectable(attrib2);
        
        
        valueQuery.WHERE(new InnerJoinEq("id", term.getDefiningTableName(), term.getDefiningTableAlias(), "childTerm", allPathsQuery.getMdClassIF().getTableName(), allPathsQuery.getTableAlias()));
      }
    }


    valueQuery = joinAssays(valueQuery, mosquitoQuery, "InfectivityAssaysSnapshot", InfectivityAssayTestResult.CLASS);
    valueQuery = joinAssays(valueQuery, mosquitoQuery, "TargetSiteAssaysSnapshot", TargetSiteAssayTestResult.CLASS);
    valueQuery = joinAssays(valueQuery, mosquitoQuery, "MetabolicAssaysSnapshot",MetabolicAssayTestResult.CLASS);

    valueQuery = QueryUtil.setQueryDates(xml,valueQuery,dateAttribute);



    if (mosquitoQuery != null)
    {
      valueQuery = QueryUtil.setQueryRatio(xml,valueQuery,"COUNT("+mosquitoQuery.getTableAlias()+".id)");
    }

    if (groupQuery != null)
    {
      valueQuery = QueryUtil.setQueryRatio(xml,valueQuery,"COUNT("+groupQuery.getTableAlias()+".id)");
    }


    return valueQuery;
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

   @Transaction
   public static String mapQuery(String xml, String config, String[] universalLayers, String savedSearchId)
   {
     if (savedSearchId == null || savedSearchId.trim().length() == 0)
     {
       String error = "Cannot map a query without a current SavedSearch instance.";
       SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
       throw ex;
     }

     SavedSearch search = SavedSearch.get(savedSearchId);
     ThematicLayer thematicLayer = search.getThematicLayer();

     if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
     {
       String error = "Cannot create a map for search [" + search.getQueryName()
           + "] without having selected a thematic layer.";
       NoThematicLayerException ex = new NoThematicLayerException(error);
       throw ex;
     }

     // Update ThematicLayer if the thematic layer type has changed or
     // if one has not yet been defined.
     String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
     if (thematicLayer.getGeometryStyle() == null
         || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
     {
       thematicLayer.changeLayerType(thematicLayerType);
     }

     ValueQuery query = xmlToValueQuery(xml, config, true, thematicLayer);

     System.out.println(query.getSQL());

     String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

     return layers;
   }

   /**
   * Queries for Mosquitos.
   *
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryEntomology(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = xmlToValueQuery(queryXML, config, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

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
