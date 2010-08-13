package dss.vector.solutions.intervention.monitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.ontology.AllPathsQuery;
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

    ControlInterventionQuery controlInterventionQuery = (ControlInterventionQuery) queryMap.get(ControlIntervention.CLASS);

    AggregatedPremiseVisitQuery aggregatedPremiseVisitQuery = (AggregatedPremiseVisitQuery) queryMap.get(AggregatedPremiseVisit.CLASS);
    IndividualPremiseVisitQuery individualPremiseVisitQuery = (IndividualPremiseVisitQuery) queryMap.get(IndividualPremiseVisit.CLASS);
    PersonInterventionQuery personInterventionQuery = (PersonInterventionQuery) queryMap.get(PersonIntervention.CLASS);
    InsecticideInterventionQuery insecticideInterventionQuery = (InsecticideInterventionQuery) queryMap.get(InsecticideIntervention.CLASS);
    
    /*
     * Some types have relationships with Terms, but the incoming query is unaware of
     * those types because the Terms are represented as SelectableSQL objects, which are
     * not associated with any specific type. Instead we can look for the relationship between
     * the type and term which is contained in the user defined alias.
     */
    for(Selectable sel : valueQuery.getSelectableRefs())
    {
      if(sel instanceof Function)
      {
        sel = ((Function)sel).getSelectable();
      }
      
      if(sel instanceof SelectableSQL)
      {
        String alias = sel.getUserDefinedAlias();
        if(alias != null)
        {
          // Check for the relationship in the alias and end looping if found because
          // the types are mutually exclusive anyway, so only one match of any type is required.
          if(personInterventionQuery == null && alias.contains(PersonInterventionMethod.CLASS.replaceAll("\\.", "_")))
          {
            personInterventionQuery = new PersonInterventionQuery(valueQuery);
            break;
          }
          else if(individualPremiseVisitQuery == null && alias.contains(IndividualPremiseVisitMethod.CLASS.replaceAll("\\.", "_")))
          {
            individualPremiseVisitQuery = new IndividualPremiseVisitQuery(valueQuery);
            break;
          }
          else if(aggregatedPremiseVisitQuery == null &&
              (alias.contains(AggregatedPremiseMethod.CLASS.replaceAll("\\.", "_")) || alias.contains(AggregatedPremiseReason.CLASS.replaceAll("\\.", "_"))))
          {
            aggregatedPremiseVisitQuery = new AggregatedPremiseVisitQuery(valueQuery);
            break;
          }
        }
      }
    }
    
    if (valueQuery.hasSelectableRef("subGeoEntity_ic"))
    {
      if (individualPremiseVisitQuery == null)
      {
        individualPremiseVisitQuery = new IndividualPremiseVisitQuery(valueQuery);
      }
      
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) valueQuery.getSelectableRef("subGeoEntity_ic");
      valueQuery.WHERE(individualPremiseVisitQuery.getId().NE("0"));

      QueryUtil.subselectGeoDisplayLabels(subGeo, IndividualPremiseVisit.CLASS, IndividualPremiseVisit.GEOENTITY, individualPremiseVisitQuery.getTableAlias() + "." + "id");
    }

    // include InvidualPremiseVisitQuery if the user is selecting the calculation columns.
    if(setIndividualCalculations(valueQuery))
    {
      if (individualPremiseVisitQuery == null)
      {
        individualPremiseVisitQuery = new IndividualPremiseVisitQuery(valueQuery);
      }
    }
    
    if (individualPremiseVisitQuery != null)
    {

      QueryUtil.joinTermAllpaths(valueQuery, IndividualPremiseVisit.CLASS, individualPremiseVisitQuery);
      Boolean needsJoin = QueryUtil.getSingleAttribteGridSql(valueQuery, individualPremiseVisitQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);
      if (needsJoin)
      {
        valueQuery.WHERE(individualPremiseVisitQuery.getId().NE("0"));
      }

      valueQuery.WHERE(individualPremiseVisitQuery.getPoint().EQ(controlInterventionQuery));
      valueQuery.AND(individualPremiseVisitQuery.getVisited().NE((Boolean) null)); // a nullified visit field MUST be excluded entirely
    }
    
    // Grab the sub-geoentity for aggregated intervention. Note that vehicle-based spraying
    // uses the same sub-geoentity as aggregated intervention (the geoentity is set the same in the CRUD).
    // Also note that the two selectables are mutually exclusive.
    String subGeoAlias = null;
    boolean vehicleBasedSprayGeo = false;
    if(valueQuery.hasSelectableRef("subGeoEntity_ip"))
    {
      subGeoAlias = "subGeoEntity_ip";
    }
    else if(valueQuery.hasSelectableRef("subGeoEntity_v"))
    {
      subGeoAlias = "subGeoEntity_v";
      vehicleBasedSprayGeo = true;
    }
    
    if (subGeoAlias != null)
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) valueQuery.getSelectableRef(subGeoAlias);
      if (aggregatedPremiseVisitQuery == null)
      {
        aggregatedPremiseVisitQuery = new AggregatedPremiseVisitQuery(valueQuery);
        valueQuery.WHERE(aggregatedPremiseVisitQuery.getId().NE("0"));
      }

      QueryUtil.subselectGeoDisplayLabels(subGeo, AggregatedPremiseVisit.CLASS, AggregatedPremiseVisit.GEOENTITY, aggregatedPremiseVisitQuery.getTableAlias() + "." + "id");
    }
    
    if (aggregatedPremiseVisitQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, AggregatedPremiseVisit.CLASS, aggregatedPremiseVisitQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedPremiseVisitQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);

      valueQuery.WHERE(aggregatedPremiseVisitQuery.getPoint().EQ(controlInterventionQuery));
    }

    if (personInterventionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PersonIntervention.CLASS, personInterventionQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery, personInterventionQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);
      
      valueQuery.WHERE(personInterventionQuery.getPoint().EQ(controlInterventionQuery));
    }

    if (insecticideInterventionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideIntervention.CLASS, insecticideInterventionQuery);
      
      valueQuery.WHERE(insecticideInterventionQuery.getIntervention().EQ(controlInterventionQuery));
    }

    InsecticideBrandQuery insecticideBrandQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
    if (insecticideBrandQuery != null)
    {

      if (insecticideInterventionQuery == null)
      {
        insecticideInterventionQuery = new InsecticideInterventionQuery(valueQuery);
      }

      valueQuery.WHERE(insecticideInterventionQuery.getInsecticide().EQ(insecticideBrandQuery));
      valueQuery.AND(insecticideInterventionQuery.getIntervention().EQ(controlInterventionQuery));

      QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
    }

    MdEntityDAOIF individualVisit = MdEntityDAO.getMdEntityDAO(IndividualPremiseVisitMethod.CLASS);

    // "used" is the column for the union of used and amount on individual and aggregated methods, respectively.
    String used = QueryUtil.getColumnName(individualVisit, IndividualPremiseVisitMethod.USED);

    boolean needsView = valueQuery.hasSelectableRef("childId_r") || valueQuery.hasSelectableRef("childId_tm");
    
    // The way we fetch columns and group the data depends on whether or not
    // the user is trying to group by the treatment method, reasons not treated,
    // or none at all (default). Note that the two options are mutually exclusive.
    boolean byTreatmentMethod = valueQuery.hasSelectableRef("childId_tm");
    boolean byReasonNotTreated = valueQuery.hasSelectableRef("childId_r");
    
    String view = "premiseVisitUnion";
    MdEntityDAOIF visitMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseVisit.CLASS);
    String available = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISESAVAILABLE);
    String included = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISESINCLUDED);
    String treated = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.TREATED);
    String premises = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISES);
    String visited = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.VISITED);
    String amount = QueryUtil.getColumnName(AggregatedPremiseReason.getAmountMd());

    // available and included for vehicle-based spraying
    String availableSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + view+"."+available + "))";
    String includedSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + view+"."+included + "))";
    
    String treatedCol = byTreatmentMethod ? used : treated;
    String treatedSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + treatedCol + "))";
    String premisesSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + premises + "))";
    String visitedSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + visited + "))";
//    String notTreatedSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " +amount + "))";

    
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "premises_available_for_vehicle_spraying", "" + availableSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "premises_included_for_vehicle_spraying", "" + includedSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_treated_with_vehicle_spraying", "(" + includedSum + "/NULLIF(" + availableSum + ",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_available", "" + premisesSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_visited", "" + visitedSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_treated", "" + treatedSum) || needsView;
    
    if(byReasonNotTreated)
    {
      String amountSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT visit || '~' || " + amount + "))";
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_not_treated", "" + amountSum +"") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_not_treated", "((" + amountSum + ")/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    }
    else
    {
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "total_premises_not_treated", "" + visitedSum + " - " + treatedSum +"") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_not_treated", "((" + visitedSum + " - " + treatedSum + ")/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    }

    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_premises_visited", "(" + visitedSum + "/NULLIF(" + premisesSum + ",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_premises_treated", "(" + treatedSum + "/NULLIF(" + premisesSum + ",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_treated", "(" + treatedSum + "/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
//    needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_visited_not_treated", "((" + visitedSum + "-" + treatedSum + ")/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    
    String controlInterventionTable = MdBusiness.getMdBusiness(ControlIntervention.CLASS).getTableName();

    if (needsView)
    {
      String idCol = QueryUtil.getIdColumn();

      MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseMethod.CLASS);

      String aggVisitTable = MdBusiness.getMdBusiness(AggregatedPremiseVisit.CLASS).getTableName();
      String individualVisitTable = MdBusiness.getMdEntity(IndividualPremiseVisit.CLASS).getTableName();

      String parentTerm = QueryUtil.getColumnName(AllPaths.getParentTermMd());
      String viewTerm = "view_term";

      String iGE = QueryUtil.getColumnName(IndividualPremiseVisit.getGeoEntityMd());
      String aGE = QueryUtil.getColumnName(AggregatedPremiseVisit.getGeoEntityMd());
      
      String viewSql;
      if(byReasonNotTreated)
      {
        String aggPresmiseReaonTable = MdEntity.getMdEntity(AggregatedPremiseReason.CLASS).getTableName();
        String reasonCol = QueryUtil.getColumnName(IndividualPremiseVisit.getReasonsForNotTreatedMd());
        
        
        viewSql = "SELECT  point, " + visited + " AS "+ premises +", "+visited+
          " , " + treated + ", "+individualVisitTable+"."+idCol+" as visit, \n";
        viewSql += "CASE WHEN "+reasonCol+" is null THEN 0 ELSE 1 END AS amount, "+individualVisitTable+".id as id, term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+iGE+" AS geo_entity \n";
        viewSql += " FROM " + individualVisitTable + " individual_premise_visit LEFT JOIN term as term0 on "+individualVisitTable+"."+reasonCol+" = term0.id\n";
        viewSql += "WHERE "+visited+" IS NOT NULL";
        
        viewSql += " UNION ALL\n";
        
        viewSql += " SELECT  point," + premises + ", " + visited + 
          " , " + treated + ", "+aggPresmiseReaonTable+".parent_id as visit, \n";
        viewSql += " "+amount+", "+aggPresmiseReaonTable+".id as id,  term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+aGE+" AS geo_entity \n";
        viewSql += " FROM " + aggVisitTable + " aggregated_premise_visit , " + aggPresmiseReaonTable + " "+aggPresmiseReaonTable+"  LEFT JOIN term as term0 on "+aggPresmiseReaonTable+".child_id = term0.id\n";
        viewSql += " WHERE  "+aggPresmiseReaonTable+".parent_id = aggregated_premise_visit.id\n";
      }
      else if(byTreatmentMethod)
      {
        String individualVisitMethodTable = MdEntity.getMdEntity(IndividualPremiseVisitMethod.CLASS).getTableName();
        String aggVisitMethodTable = MdEntity.getMdEntity(AggregatedPremiseMethod.CLASS).getTableName();
        String apAmount = QueryUtil.getColumnName(ammountMd, AggregatedPremiseMethod.AMOUNT);

        viewSql = "SELECT  point, "+visited+" AS "+ premises +", "+visited+
          ", " + treated + ", " + used + ", 0 as "+available+", 0 as "+included+", "+individualVisitMethodTable+".parent_id as visit, \n";
        viewSql += "individual_premise_visit_metho.child_id as id, term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+iGE+" AS geo_entity \n";
        viewSql += " FROM " + individualVisitTable + " individual_premise_visit , " + individualVisitMethodTable + " "+individualVisitMethodTable+"  LEFT JOIN term as term0 on "+individualVisitMethodTable+".child_id = term0.id\n";
        viewSql += " WHERE  "+individualVisitMethodTable+".parent_id = individual_premise_visit.id AND "+visited+" IS NOT null \n";
      
        viewSql += " UNION ALL\n";
      
        viewSql += " SELECT  point," + premises + ", " + visited + " , " + treated + ", " + apAmount + ", "+available+", "+included+", "+aggVisitMethodTable+".parent_id as visit, \n";
        viewSql += " aggregated_premise_method.child_id as id,  term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+aGE+" AS geo_entity \n";
        viewSql += " FROM " + aggVisitTable + " aggregated_premise_visit , " + aggVisitMethodTable + " "+aggVisitMethodTable+"  LEFT JOIN term as term0 on "+aggVisitMethodTable+".child_id = term0.id\n";
        viewSql += " WHERE  "+aggVisitMethodTable+".parent_id = aggregated_premise_visit.id AND "+visited+" IS NOT null \n";
      }
      else
      {
        String individualVisitMethodTable = MdEntity.getMdEntity(IndividualPremiseVisitMethod.CLASS).getTableName();
        String aggVisitMethodTable = MdEntity.getMdEntity(AggregatedPremiseMethod.CLASS).getTableName();
        String apAmount = QueryUtil.getColumnName(ammountMd, AggregatedPremiseMethod.AMOUNT);

        viewSql = "SELECT  point, "+visited+" AS "+ premises +", "+visited+
          ", " + treated + ", " + used + ", 0 as "+available+", 0 as "+included+", "+individualVisitMethodTable+".parent_id as visit, \n";
        viewSql += individualVisitTable+"."+idCol+" AS id, "+iGE+" AS geo_entity \n";
        viewSql += " FROM " + individualVisitTable + " individual_premise_visit , " + individualVisitMethodTable + " "+individualVisitMethodTable+" \n";
        viewSql += " WHERE  "+individualVisitMethodTable+".parent_id = individual_premise_visit.id AND "+visited+" IS NOT null \n";
      
        viewSql += " UNION ALL\n";
      
        viewSql += " SELECT  point," + premises + ", " + visited + " , " + treated + ", " + apAmount + ", "+available+", "+included+", "+aggVisitMethodTable+".parent_id as visit, \n";
        viewSql += " aggregated_premise_method.child_id as id, "+aGE+" AS geo_entity \n";
        viewSql += " FROM " + aggVisitTable + " aggregated_premise_visit , " + aggVisitMethodTable + " "+aggVisitMethodTable+" \n";
        viewSql += " WHERE  "+aggVisitMethodTable+".parent_id = aggregated_premise_visit.id AND "+visited+" IS NOT null \n";
      }


      valueQuery.setSqlPrefix("WITH " + view + " AS (" + viewSql + ")");
      valueQuery.AND(new InnerJoinEq("id", controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view, view));

      // join the sub-geoentity if we're querying vehicle-based spraying
      if(vehicleBasedSprayGeo)
      {
        String aggGeo = aggregatedPremiseVisitQuery.getTableAlias()+"."+QueryUtil.getColumnName(AggregatedPremiseVisit.getGeoEntityMd());
        valueQuery.AND(valueQuery.aSQLCharacter("unionSubEntity", view+".geo_entity")
            .EQ(valueQuery.aSQLCharacter("aggPremiseSubEntity", aggGeo)));
      }
      
      // Join the view and main query on the terms, so WHERE criteria will be applied to the view
      if(hasTermCriteria(valueQuery, queryMap))
      {
        valueQuery.AND(valueQuery.aSQLCharacter("ontology_parent_term", parentTerm).EQ(valueQuery.aSQLCharacter(viewTerm, viewTerm)));
      }
      
      // Don't display rows that have a null term when selecting reasons for untreated
      if(byReasonNotTreated)
      {
        valueQuery.AND(valueQuery.aSQLCharacter("ignoreEmptyTerms", view+".childId_displayLabel").NE((String)null));
      }
      
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, ControlIntervention.CLASS, controlInterventionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    return QueryUtil.setQueryDates(xml, valueQuery, controlInterventionQuery, controlInterventionQuery.getStartDate(), controlInterventionQuery.getEndDate());
  }
  
  /**
   * Attempts to set IndividualPremiseVisit calculations on the value query.
   * @param valueQuery
   * @return true if the calculations were specified and added; otherwise, false.
   */
  private static boolean setIndividualCalculations(ValueQuery valueQuery)
  {
    final String suffix = "_ind"; // suffix used in the query screen.
    MdAttributeDAOIF[] attribs = new MdAttributeDAOIF[]{IndividualPremiseVisit.getVisitedMd(), IndividualPremiseVisit.getTreatedMd(), 
        IndividualPremiseVisit.getReasonsForNotTreatedMd()};
    
    boolean added = false;
    Map<String, Selectable> total = new HashMap<String, Selectable>();
    List<String> agg = new LinkedList<String>();
    for(MdAttributeDAOIF attrib : attribs)
    {
      String alias = attrib.definesAttribute()+suffix;
      if(valueQuery.hasSelectableRef(alias))
      {
        added = true;
        
        Selectable sel = valueQuery.getSelectableRef(alias);
        
        SelectableSQL selSQL;
        if(sel instanceof Function)
        {
          selSQL = (SelectableSQL) ((Function)sel).getSelectable();
          agg.add(alias);
        }
        else
        {
          selSQL = (SelectableSQL) sel;
        }
        
        total.put(alias, sel);

        String col = QueryUtil.getColumnName(attrib);
        
        String sql;
        if(attrib instanceof MdAttributeBooleanDAOIF)
        {
          // nulls will be excluded (set in WHERE criteria), so treating the boolean
          // values 0 or 1 as integers is perfectly fine.
          sql = col;
        }
        else
        {
          // A value in a reference attribute is treated as boolean true (1)
          sql = "CASE WHEN "+col+" IS null THEN 0 ELSE 1 END";
        }
        
        selSQL.setSQL(sql);
      }
    }
    
    // group by the other selectables that are not aggregates
    if(agg.size() > 0)
    {
      for(String aggAlias : agg)
      {
        total.remove(aggAlias);
      }
      
      for(Selectable toGroup : total.values())
      {
        valueQuery.GROUP_BY((SelectableSingle) toGroup);
      }
    }
    
    return added;
  }
  
  /**
   * Looks for Term criteria for the Treatment Method and Reason Not Treated terms in
   * the calculations.
   * 
   * @param queryMap
   */
  private static boolean hasTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    if(valueQuery.hasSelectableRef("childId_r") || valueQuery.hasSelectableRef("childId_tm"))
    {
      for(GeneratedEntityQuery q : queryMap.values())
      {
        if(q instanceof AllPathsQuery)
        {
          return true;
        }
      }
    }
    
    return false;
  }



}
