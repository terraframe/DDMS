package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ControlIntervention extends ControlInterventionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1736678503;

  public ControlIntervention()
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

    return this.getClassDisplayLabel();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  @Transaction
  public void delete()
  {
    this.deleteInsecticideIntervention();

    this.deletePersonIntervention();

    this.deleteIndividualPremises();

    this.deleteAggregatedPremises();

    super.delete();
  }

  public void deleteInsecticideIntervention()
  {
    List<InsecticideIntervention> list = this.getInsecticideInterventions();

    for (InsecticideIntervention visit : list)
    {
      visit.delete();
    }
  }

  private List<InsecticideIntervention> getInsecticideInterventions()
  {
    List<InsecticideIntervention> list = new LinkedList<InsecticideIntervention>();
    InsecticideInterventionQuery query = new InsecticideInterventionQuery(new QueryFactory());
    query.WHERE(query.getIntervention().EQ(this));
    OIterator<? extends InsecticideIntervention> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;

  }

  public void deleteAggregatedPremises()
  {
    List<AggregatedPremiseVisit> list = this.getAggregatedPremiseVisits();

    for (AggregatedPremiseVisit visit : list)
    {
      visit.delete();
    }
  }

  private List<AggregatedPremiseVisit> getAggregatedPremiseVisits()
  {
    List<AggregatedPremiseVisit> list = new LinkedList<AggregatedPremiseVisit>();
    AggregatedPremiseVisitQuery query = new AggregatedPremiseVisitQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends AggregatedPremiseVisit> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  public void deleteIndividualPremises()
  {
    List<IndividualPremiseVisit> list = this.getIndividualPremiseVisits();

    for (IndividualPremiseVisit visit : list)
    {
      visit.delete();
    }
  }

  private List<IndividualPremiseVisit> getIndividualPremiseVisits()
  {
    List<IndividualPremiseVisit> list = new LinkedList<IndividualPremiseVisit>();
    IndividualPremiseVisitQuery query = new IndividualPremiseVisitQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends IndividualPremiseVisit> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  public void deletePersonIntervention()
  {
    List<PersonIntervention> list = this.getPersonInterventions();

    for (PersonIntervention visit : list)
    {
      visit.delete();
    }
  }

  private List<PersonIntervention> getPersonInterventions()
  {
    List<PersonIntervention> list = new LinkedList<PersonIntervention>();
    PersonInterventionQuery query = new PersonInterventionQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends PersonIntervention> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public ControlInterventionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public ControlInterventionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ControlInterventionView getView()
  {
    ControlInterventionView view = new ControlInterventionView();

    view.populateView(this);

    return view;
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

    
    ControlInterventionQuery controlInterventionQuery  = (ControlInterventionQuery) queryMap.get(ControlIntervention.CLASS);
    
    IndividualPremiseVisitQuery individualPremiseVisitQuery  = (IndividualPremiseVisitQuery) queryMap.get(IndividualPremiseVisit.CLASS);
    
    if (individualPremiseVisitQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, IndividualPremiseVisit.CLASS, individualPremiseVisitQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery,individualPremiseVisitQuery.getTableAlias());
    }
    //IndividualPremiseVisitMethodQuery individualPremiseVisitMethodQuery  = (IndividualPremiseVisitMethodQuery) queryMap.get(IndividualPremiseVisitMethod.CLASS);
    
    
    AggregatedPremiseVisitQuery  aggregatedPremiseVisitQuery  = (AggregatedPremiseVisitQuery) queryMap.get(AggregatedPremiseVisit.CLASS);
    
    
    if ( aggregatedPremiseVisitQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, AggregatedPremiseVisit.CLASS, aggregatedPremiseVisitQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery,aggregatedPremiseVisitQuery.getTableAlias());
    }
    //AggregatedPremiseReasonQuery aggregatedPremiseReasonQuery  = (AggregatedPremiseReasonQuery) queryMap.get(AggregatedPremiseReason.CLASS);
    //AggregatedPremiseMethodQuery aggregatedPremiseMethodQuery  = (AggregatedPremiseMethodQuery) queryMap.get(AggregatedPremiseMethod.CLASS);
    
    
    PersonInterventionQuery personInterventionQuery  = (PersonInterventionQuery) queryMap.get(PersonIntervention.CLASS);
    
    if ( personInterventionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PersonIntervention.CLASS, personInterventionQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery,personInterventionQuery.getTableAlias());
    }
    
    
    //PersonInterventionMethodQuery personInterventionMethodQuery  = (PersonInterventionMethodQuery) queryMap.get(PersonInterventionMethod.CLASS);
    
    
    
    InsecticideInterventionQuery insecticideInterventionQuery  = (InsecticideInterventionQuery) queryMap.get(InsecticideIntervention.CLASS);
    if ( insecticideInterventionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideIntervention.CLASS, insecticideInterventionQuery);
    }
    
    
    InsecticideBrandQuery insecticideBrandQuery  = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
    if ( insecticideBrandQuery != null)
    {
      
      if ( insecticideInterventionQuery != null)
      {
        valueQuery.WHERE(insecticideInterventionQuery.getInsecticide().EQ(insecticideBrandQuery));
      }
      
      QueryUtil.joinEnumerationDisplayLabels(valueQuery,  InsecticideBrand.CLASS, insecticideBrandQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
    }

    boolean needsView = false; 
    MdEntityDAOIF visitMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseVisit.CLASS);
    String id = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.ID);
    String treated = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.TREATED);
    String premises = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISES);
    String visited = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.VISITED);
    
    String treatedSum =     "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + treated + "))";
    String premisesSum =     "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + premises + "))";
    String visitedSum =     "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + visited + "))";
    
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_visited", ""+visitedSum+"") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_treated", ""+treatedSum) || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_not_treated", ""+premisesSum+"-"+treatedSum) || needsView;
    //needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_person_days", "total_person_days") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_premises_visited", "("+visitedSum+"/NULLIF("+premisesSum+",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_premises_treated", "("+treatedSum+"/NULLIF("+premisesSum+",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_treated", "("+treatedSum+"/NULLIF("+visitedSum+",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_not_treated", "(("+premisesSum+"-"+treatedSum+")/NULLIF("+visitedSum+",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "childId_displayLabel", "childid_displaylabel") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "method_qty", "SUM(used)") || needsView;
    
    
    
    String controlInterventionTable = MdBusiness.getMdBusiness(ControlIntervention.CLASS).getTableName();

    if( needsView)
    { 
      
      
      MdEntityDAOIF individualVisit = MdEntityDAO.getMdEntityDAO(IndividualPremiseVisitMethod.CLASS);

      String used = QueryUtil.getColumnName(individualVisit, IndividualPremiseVisitMethod.USED);
      //String aggCase = QueryUtil.getColumnName(individualVisit, CasePatientType.AGGREGATEDCASE);
      MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseMethod.CLASS );

      String amount = QueryUtil.getColumnName(ammountMd, AggregatedPremiseMethod.AMOUNT);
  //    String id = QueryUtil.getColumnName(ammountMd, CasePatientTypeAmount.ID);
      String child_id = "child_id";
      String parent_id = "parent_id";
      String aggVisitTable = MdBusiness.getMdBusiness(AggregatedPremiseVisit.CLASS).getTableName();
      String aggVisitMethodTable = MdEntity.getMdEntity(AggregatedPremiseMethod.CLASS).getTableName();
      String individualVisitTable = MdBusiness.getMdEntity(IndividualPremiseVisit.CLASS).getTableName();
      String individualVisitMethodTable = MdEntity.getMdEntity(IndividualPremiseVisitMethod.CLASS).getTableName();
      
      
      
      String viewSql = "SELECT  point, 1 as "+premises+", "+visited+" , "+treated+", "+used+", individual_premise_visit_metho.parent_id as visit, \n";
      viewSql += "individual_premise_visit_metho.child_id as id, term0.name as childId_displayLabel\n";
      viewSql += " FROM "+individualVisitTable+" individual_premise_visit , "+individualVisitMethodTable+" individual_premise_visit_metho  LEFT JOIN term as term0 on individual_premise_visit_metho.child_id = term0.id\n";
      viewSql += " WHERE  individual_premise_visit_metho.parent_id = individual_premise_visit.id\n";
      viewSql += " UNION ALL\n";
      viewSql += " SELECT  point,"+premises+", "+visited+" , "+treated+", "+amount+", aggregated_premise_method.parent_id as visit, \n";
      viewSql += " aggregated_premise_method.child_id as id,  term0.name as childId_displayLabel\n";
      viewSql += " FROM "+aggVisitTable+" aggregated_premise_visit , "+aggVisitMethodTable+" aggregated_premise_method  LEFT JOIN term as term0 on aggregated_premise_method.child_id = term0.id\n";
      viewSql += " WHERE  aggregated_premise_method.parent_id = aggregated_premise_visit.id\n";
      
      
      //(SELECT collection_container.id , term0.name as childId_displayLabel FROM collection_container 
      //as collection_container LEFT JOIN term as term0 on collection_container.child_id = term0.id) dss_vector_solutions_entomology_CollectionContainerTermSubSel 
      
      String view = "dss_vector_solutions_intervention_monitor_IndividualPremiseVisitTermSubSel";    
      
      valueQuery.setSqlPrefix("WITH "+view+" AS (" + viewSql + ")");
      valueQuery.AND(new InnerJoinEq("id", controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view, view));
    }
    
    
    
    QueryUtil.joinGeoDisplayLabels(valueQuery, ControlIntervention.CLASS, controlInterventionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
      
    
    return QueryUtil.setQueryDates(xml, valueQuery, controlInterventionQuery, controlInterventionQuery.getStartDate(), controlInterventionQuery.getEndDate());

  }
  
  static boolean getSelectabeTermRelationSQL(ValueQuery valueQuery, String ref, String sql)
  {
    if (valueQuery.hasSelectableRef(ref))
    {
      Selectable s = valueQuery.getSelectableRef(ref);

      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }
      
      ( (SelectableSQL) s ).setSQL(sql);
      return true;
    }
    return false;
  }

}
