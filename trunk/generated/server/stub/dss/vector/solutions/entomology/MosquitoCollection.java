package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class MosquitoCollection extends MosquitoCollectionBase implements com.runwaysdk.generation.loader.Reloadable
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
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getCollectionId() != null)
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

    // DELETE ALL BIOASSAYS
    DiagnosticAssayView[] diagnosticAssays = view.getDiagnosticAssays();
    
    for (DiagnosticAssayView assay : diagnosticAssays)
    {
      assay.deleteConcrete();
    }
    
    TimeResponseAssayView[] timeResponseAssays = view.getTimeResponseAssays();
    
    for (TimeResponseAssayView assay : timeResponseAssays)
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

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
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
      this.setCollectionId(LocalProperty.getNextId());
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

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    
    
    if (valueQuery.hasSelectableRef("mosquitoCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("mosquitoCount");
      calc.setSQL("NULL");
    }
    if (valueQuery.hasSelectableRef("collectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("collectionCount");
      calc.setSQL("NULL");
    }
    if (valueQuery.hasSelectableRef("subCollectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("subCollectionCount");
      calc.setSQL("NULL");
    }
    
    
    
    if (xml.contains("abundance_1") || xml.contains("mosquitoCount") || xml.contains("ollectionCount"))
    {

      String viewName = "abundance_view";

      setAbundance(valueQuery, 1, "1");
      setAbundance(valueQuery, 10, "10");
      setAbundance(valueQuery, 100, "100");
      setAbundance(valueQuery, 1000, "1000");

      valueQuery.WHERE(mosquitoCollectionQuery.getAbundance().EQ(true));

      String withQuery = getWithQuerySQL(viewName, valueQuery);

      ValueQuery overrideQuery = new ValueQuery(queryFactory);

      for (Selectable s : valueQuery.getSelectableRefs())
      {
        String attributeName = s.getDbColumnName();
        String columnAlias = s.getColumnAlias();
        String columnName = s.getColumnAlias();

        if (attributeName.equals("mosquitoCount"))
        {
          columnName = "abundance_sum";
        }
        if (attributeName.equals("collectionCount"))
        {
          columnName = "array_length(allCollectionIds,1)";
        }       
        if (attributeName.equals("subCollectionCount"))
        {
          columnName = "coalesce(array_length(allSubCollectionIds,1))";
        }

        
        if (attributeName.equals("abundance_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        
 
        if (attributeName.equals("abundance_subcol_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }

        if (s instanceof SelectableSQLFloat)
        {
          overrideQuery.SELECT(overrideQuery.aSQLFloat(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else if (s instanceof SelectableSQLInteger)
        {
          overrideQuery.SELECT(overrideQuery.aSQLInteger(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else if (s instanceof AttributeMoment)
        {
          overrideQuery.SELECT(overrideQuery.aSQLDate(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else
        {
          overrideQuery.SELECT(overrideQuery.aSQLText(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }

      }

      overrideQuery.setSqlPrefix(withQuery);
      overrideQuery.FROM(viewName, viewName);
      return overrideQuery;

    }

    return valueQuery;
  }

  public static void setAbundance(ValueQuery valueQuery, Integer multiplier, String sql)
  {
    String selectableName = "abundance_" + multiplier;
    if(valueQuery.hasSelectableRef(selectableName)) {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }
    
    selectableName = "abundance_subcol_" + multiplier;
    if(valueQuery.hasSelectableRef(selectableName)) {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }

  }

  
  public static String getWithQuerySQL(String viewName, ValueQuery valueQuery)
  {

    String joinMainQuery = "";
    
    String areaGroup = "";
    
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      if (s.getDbColumnName().startsWith("geoId_") || s.getDbColumnName().startsWith("collectionMethod") || s.getDbColumnName().startsWith("subCollectionId") || s.getDbColumnName().startsWith("DATEGROUP"))
      {
        joinMainQuery += "\n AND ss." + s.getColumnAlias() + " = mainQuery." + s.getColumnAlias() + " ";
        areaGroup += "||  mainQuery." + s.getColumnAlias() + " ";
      }

    }
    areaGroup = areaGroup.substring(2);

    String origQuery = valueQuery.getSQL();
    
    String collectionIdCol = QueryUtil.getColumnName(MosquitoCollection.getCollectionIdMd());
    String subCollectionId = QueryUtil.getColumnName(SubCollection.getSubCollectionIdMd());
    String taxonCol = QueryUtil.getColumnName(SubCollection.getTaxonMd());
    String totalCol = QueryUtil.getColumnName(SubCollection.getTotalMd());
    String parentTermCol = QueryUtil.getColumnName(AllPaths.getParentTermMd());
    String childTermCol = QueryUtil.getColumnName(AllPaths.getChildTermMd());
    
    String selectAddtions = taxonCol+",\n SUM("+totalCol+") as abundance_sum, \n array_agg("+collectionIdCol+") as collectionIds, \n array_agg(coalesce("+collectionIdCol+" || "+subCollectionId+", "+collectionIdCol+")) as subCollectionIds  \n,";

    origQuery = origQuery.replaceFirst("SELECT", "SELECT "+ selectAddtions).replaceFirst("GROUP BY", "GROUP BY "+taxonCol+",");

    String sql = "WITH RECURSIVE mainQuery AS \n";
    sql += "(" + origQuery + "),\n";
    
    //taxonCountQuery is where each node gets the total of its child species
    sql += "taxonCountQuery AS (\n";
    sql += "SELECT mainQuery.* ,";
    sql += "(SELECT SUM(ss.abundance_sum) FROM mainQuery as ss, allpaths_ontology ap ";
    //used to calcuate ratio
    sql += "WHERE ss."+taxonCol+" = "+childTermCol+" AND "+parentTermCol+" = mainQuery."+taxonCol+" AND ss."+taxonCol+" != mainQuery."+taxonCol+" " + joinMainQuery + " ) as total_of_children, \n";
    //list of collection ids in this group
    sql += "ARRAY(SELECT distinct unnest(collectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allCollectionIds, \n";
    //list of sub collection ids in this group
    sql += "ARRAY(SELECT distinct unnest(subCollectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allSubCollectionIds, \n";
    //used to order the recursive decent
    sql += "(SELECT COUNT(*) FROM mainQuery as ss, allpaths_ontology ap WHERE ss."+taxonCol+" = "+parentTermCol+"  AND "+childTermCol+" = mainQuery."+taxonCol+" AND ss.taxon != mainQuery."+taxonCol+" "+ joinMainQuery + " )as depth, ";
    //the parent specie of this row in this group, may skip levels
    sql += "(SELECT ss."+taxonCol+" as depth FROM mainQuery as ss, allpaths_ontology ap WHERE ss."+taxonCol+" = "+parentTermCol+"  AND "+childTermCol+" = mainQuery."+taxonCol+" AND ss."+taxonCol+" != mainQuery."+taxonCol+" " + joinMainQuery; 
    sql += " GROUP BY ss."+taxonCol+" ORDER BY COUNT(*) DESC LIMIT 1 )as parent,\n";
    
    sql += areaGroup + " AS areaGroup\n";
    sql += " FROM mainQuery),\n";
    sql += " \n";

    joinMainQuery = joinMainQuery.replace("mainQuery","taxonCountQuery");
    

    sql += " percent_view AS ( ";
    sql += "SELECT taxonCountQuery.*,";
    //  -- ((me+my_children)/sum(everyone_at_my_level+their children))
    sql += "((abundance_sum + coalesce(total_of_children,0)) / \n ";
    sql += "(SELECT SUM(coalesce(ss.total_of_children,0) + ss.abundance_sum) FROM taxonCountQuery AS ss WHERE ss.parent = taxonCountQuery.parent" + joinMainQuery + "   ))  as my_share \n";
    sql += "FROM taxonCountQuery \n";
    sql += "),";
    
    
    sql += " rollup_view AS ( \n";
    sql += " SELECT areagroup, "+taxonCol+", parent, depth , my_share , abundance_sum + coalesce(total_of_children,0) as final_abundance\n";
    sql += "     FROM percent_view\n";
    sql += "     WHERE depth = 0\n";
    sql += " UNION\n";
    sql += " SELECT child_v.areagroup, child_v."+taxonCol+", child_v.parent, child_v.depth ,child_v.my_share, \n";
    sql += "  parent_v.final_abundance * child_v.my_share \n";
    sql += " FROM rollup_view parent_v, percent_view child_v WHERE parent_v.taxon = child_v.parent AND parent_v.areagroup = child_v.areagroup \n";
    sql += " ),\n";
    sql += "\n";

    
    sql += " "+viewName+" AS (\n";
    sql += "SELECT pv.*, final_abundance\n";
    sql += "FROM percent_view pv join  rollup_view  rv on rv.areagroup = pv.areagroup AND  rv."+taxonCol+" = pv."+taxonCol+" \n";
    sql += " )\n";

    sql += " \n";
    

    return sql;
  }

}
