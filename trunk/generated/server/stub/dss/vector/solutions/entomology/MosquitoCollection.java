package dss.vector.solutions.entomology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Function;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQLFloat;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Property;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1894808272;

  public MosquitoCollection()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getCollectionId() != null)
    {
      return this.getCollectionId();
    }
    
    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getCollectionId();
  }

  @Override
  @Transaction
  public void delete()
  {
    // Delete all of the sub collections
    MosquitoCollectionView view = this.getView();

    SubCollectionView[] collections = view.getSubCollections();

    for (SubCollectionView collection : collections)
    {
      collection.deleteConcrete();
    }

    // Delete all of the Assays
    List<? extends CollectionAssay> list = this.getAssays();

    for (CollectionAssay assay : list)
    {
      assay.delete();
    }

    // Delete all infection and pooled infection assays
    InfectionAssayView[] infectionAssays = view.getInfectionAssays();

    for (InfectionAssayView assay : infectionAssays)
    {
      assay.deleteConcrete();
    }

    PooledInfectionAssayView[] pooledInfectionAssays = view.getPooledInfectionAssays();

    for (PooledInfectionAssayView assay : pooledInfectionAssays)
    {
      assay.deleteConcrete();
    }

    // DELETE ALL MECHANISM ASSAYS
    BiochemicalAssayView[] biochemicalAssays = view.getBiochemicalAssays();

    for (BiochemicalAssayView assay : biochemicalAssays)
    {
      assay.deleteConcrete();
    }

    MolecularAssayView[] molecularAssays = view.getMolecularAssays();

    for (MolecularAssayView assay : molecularAssays)
    {
      assay.deleteConcrete();
    }

    super.delete();
  }

  private List<? extends CollectionAssay> getAssays()
  {
    CollectionAssayQuery query = new CollectionAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));
    OIterator<? extends CollectionAssay> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void apply()
  {
    this.populateCollectionId();
    this.populateLifeStageName();
    this.validateCollectionDate();

    super.apply();
  }

  @Override
  public void validateCollectionDate()
  {
    if (this.getCollectionDate() != null && this.getCollectionDate().after(new Date()))
    {
      String msg = "It is impossible to have a test date after the current date";

      CurrentDateProblem p = new CurrentDateProblem(msg);
      p.setGivenDate(this.getCollectionDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, COLLECTIONDATE);
      p.apply();
      p.throwIt();
    }
  }

  private void populateLifeStageName()
  {
    for (LifeStage stage : this.getLifeStage())
    {
      this.setLifeStageName(stage.getEnumName());
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(Property.getNextId());
    }
  }

  @Override
  @Transaction
  public MosquitoCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MosquitoCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MosquitoCollectionView getView()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.populateView(this);

    return view;
  }

  public static MosquitoCollection getByCollectionId(String collectionId)
  {
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(collectionId));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      MosquitoCollection collection = iterator.next();
      iterator.close();
      return collection;
    }
    else
    {
      throw new DataNotFoundException("No mosquito collection with collection id [" + collectionId + "] found", MdTypeDAO.getMdTypeDAO(CLASS));
    }
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    SubCollectionQuery subCollectionQuery = (SubCollectionQuery) queryMap.get(SubCollection.CLASS);

    if (subCollectionQuery != null)
    {
      valueQuery.WHERE(subCollectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, SubCollection.CLASS, subCollectionQuery);

    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    if (xml.contains("abundance_"))
    {

      String tableAlias = subCollectionQuery.getTableAlias();
      String subCollectionTableName = MdBusiness.getMdBusiness(SubCollection.CLASS).getTableName();
      String collectionTableName = MdBusiness.getMdBusiness(SubCollection.CLASS).getTableName();
      String viewName = "abundance_view";
      
      setAbundance(valueQuery, 1, "1");
      setAbundance(valueQuery, 10, "10");
      setAbundance(valueQuery, 100, "100");
      setAbundance(valueQuery, 1000, "100");

      ArrayList<Selectable> joinSelectabes = new ArrayList<Selectable>();
      for (Selectable s : Arrays.asList(valueQuery.getSelectables()))
      {
        if (! ( s instanceof Function ))
        {
          if (s.getDefiningTableName().equals(subCollectionTableName) || s.getDefiningTableName().equals(collectionTableName) )
          {
            joinSelectabes.add(s);
            valueQuery.WHERE(new InnerJoinEq(s.getColumnAlias(), s.getDefiningTableName(), s.getDefiningTableAlias(), s.getColumnAlias(), viewName, viewName));
          }
        }
      }
      
      valueQuery.WHERE(mosquitoCollectionQuery.getAbundance().EQ(true));
      Database.parseAndExecute(getTempTableSQL(viewName, valueQuery,joinSelectabes));

      setAbundance(valueQuery, 1, "(SUM(abundance_calulated))/COUNT(*)");
      setAbundance(valueQuery, 10, "(SUM(abundance_calulated))/COUNT(*)");
      setAbundance(valueQuery, 100, "(SUM(abundance_calulated))/COUNT(*)");
      setAbundance(valueQuery, 1000, "(SUM(abundance_calulated))/COUNT(*)");


      valueQuery.FROM(viewName, viewName);
      valueQuery.WHERE(new InnerJoinEq("taxon", subCollectionTableName, tableAlias, "taxon", viewName, "abundance_view"));

    

    }

    return valueQuery;
  }

  public static void setAbundance(ValueQuery valueQuery, Integer multiplier, String sql)
  {
    try
    {
      String selectableName = "abundance_" + multiplier;
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectable(selectableName);

      calc.setSQL("" + multiplier + " * " + sql);
    }
    catch (QueryException e)
    {
    }
  }

  public static String getTempTableSQL(String viewName, ValueQuery valueQuery, ArrayList<Selectable> joinSelectabes)
  {

    String origQuery = valueQuery.getSQL();

    String joinable = "";

    for (Selectable s : joinSelectabes)
    {
      joinable += s.getColumnAlias() + ",";
    }    
    
    origQuery = origQuery.replaceFirst("SELECT", "SELECT taxon,SUM(total) as abundance_calc,").replaceFirst("GROUP BY", "GROUP BY taxon,");

    String sql = "DROP TABLE IF EXISTS " + viewName + ";\n";
    sql += "CREATE TEMP TABLE " + viewName + " AS ";
    sql += "( WITH sub1 AS \n";
    sql += "(" + origQuery + "),\n";

    sql += "sub2 AS (\n";
    sql += "SELECT " + joinable + "taxon as taxon_id, abundance_calc ,\n";
    sql += "(SELECT SUM(abundance_calc) FROM sub1 as ss, allpaths_ontology ap WHERE ss.taxon = childterm AND parentterm = sub1.taxon AND ss.taxon != sub1.taxon )as total_of_children, \n";
    sql += "(SELECT parent_id from termrelationship WHERE taxon = child_id ) as parent \n";
    sql += " FROM sub1),\n";
    sql += " \n";

    sql += " sub3 AS (\n";
    sql += " SELECT " + joinable + "taxon_id,abundance_calc,parent,\n";
    sql += " coalesce( total_of_children,0) as total_of_children, \n";
    sql += " coalesce( abundance_calc/(select(total_of_children ) from sub2 as ss where ss.taxon_id = sub2.parent) * \n";
    sql += " (select(total_of_children+abundance_calc) from sub2 as ss where ss.taxon_id = sub2.parent)-abundance_calc ,0)as abundance\n";
    sql += " FROM sub2)\n";
    sql += " \n";

    sql += " SELECT " + joinable + " taxon_id as taxon,\n";
    sql += " total_of_children+abundance_calc+abundance as abundance_calulated \n";
    sql += " FROM sub3\n";
    sql += " )\n";

    System.out.println(sql);
    return sql;
  }
}
