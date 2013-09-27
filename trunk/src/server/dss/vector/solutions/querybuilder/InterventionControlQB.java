package dss.vector.solutions.querybuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethod;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReason;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery;
import dss.vector.solutions.intervention.monitor.ControlIntervention;
import dss.vector.solutions.intervention.monitor.ControlInterventionQuery;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisit;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethod;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery;
import dss.vector.solutions.intervention.monitor.InsecticideIntervention;
import dss.vector.solutions.intervention.monitor.InsecticideInterventionQuery;
import dss.vector.solutions.intervention.monitor.PersonIntervention;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethod;
import dss.vector.solutions.intervention.monitor.PersonInterventionQuery;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class InterventionControlQB extends AbstractQB implements Reloadable
{
  public InterventionControlQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageSize, pageSize);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return ControlIntervention.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
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
          else if(individualPremiseVisitQuery == null && 
              (alias.contains(IndividualPremiseVisitMethod.CLASS.replaceAll("\\.", "_")) || alias.startsWith(QueryConstants.REASONS_FOR_NOT_TREATED_PREFIX)))
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
      Boolean needsJoin = QueryUtil.getSingleAttribteGridSql(valueQuery, individualPremiseVisitQuery.getTableAlias());
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
      QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedPremiseVisitQuery.getTableAlias());

      valueQuery.WHERE(aggregatedPremiseVisitQuery.getPoint().EQ(controlInterventionQuery));
    }

    if (personInterventionQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PersonIntervention.CLASS, personInterventionQuery);
      QueryUtil.getSingleAttribteGridSql(valueQuery, personInterventionQuery.getTableAlias());
      
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
    String view2 = "premiseVisitUnion2";
    MdEntityDAOIF visitMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseVisit.CLASS);
    String available = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISESAVAILABLE);
    String included = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISESINCLUDED);
    String treated = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.TREATED);
    String premises = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.PREMISES);
    String visited = QueryUtil.getColumnName(visitMd, AggregatedPremiseVisit.VISITED);
    String amount = QueryUtil.getColumnName(AggregatedPremiseReason.getAmountMd());

    // available and included for vehicle-based spraying
    String visit = "visit";
    
    String availableSum = QueryUtil.sumColumnForId(null, visit, view, available);
    String includedSum = QueryUtil.sumColumnForId(null, visit, view, included);
    
    String treatedCol = byTreatmentMethod ? used : treated;
    String treatedSum = QueryUtil.sumColumnForId((byReasonNotTreated ? view2 : view), visit, null, treatedCol);
    String premisesSum = QueryUtil.sumColumnForId((byReasonNotTreated ? view2 : view), visit, null, premises);
    String visitedSum = QueryUtil.sumColumnForId((byReasonNotTreated ? view2 : view), visit, null, visited);
    
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PREMISES_AVAILABLE_FOR_VEHICLE_SPRAYING, "" + availableSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PREMISES_INCLUDED_FOR_VEHICLE_SPRAYING, "" + includedSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_TREATED_WITH_VEHICLE_SPRAYING, "(" + includedSum + "/NULLIF(" + availableSum + ",0.0))*100") || needsView;

    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.TOTAL_PREMISES_AVAILABLE,  premisesSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.TOTAL_PREMISES_VISITED, visitedSum + "") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.TOTAL_PREMISES_TREATED, treatedSum) || needsView;
    
    if(byReasonNotTreated)
    {
      String amountSum = QueryUtil.sumColumnForId(view, visit, view, amount);
      needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.TOTAL_PREMISES_NOT_TREATED, "" + amountSum +"") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_VISITED_NOT_TREATED, "((" + amountSum + ")/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    }
    else
    {
      needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.TOTAL_PREMISES_NOT_TREATED, "" + visitedSum + " - " + treatedSum +"") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_VISITED_NOT_TREATED, "((" + visitedSum + " - " + treatedSum + ")/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    }

    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_PREMISES_VISITED, "(" + visitedSum + "/NULLIF(" + premisesSum + ",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_PREMISES_TREATED, "(" + treatedSum + "/NULLIF(" + premisesSum + ",0.0))*100") || needsView;
    needsView = QueryUtil.setSelectabeSQL(valueQuery, QueryConstants.PERCENT_VISITED_TREATED, "(" + treatedSum + "/NULLIF(" + visitedSum + ",0.0))*100") || needsView;
    
    String controlInterventionTable = MdBusiness.getMdBusiness(ControlIntervention.CLASS).getTableName();

    String reasonCol = QueryUtil.getColumnName(IndividualPremiseVisit.getReasonsForNotTreatedMd());
    /* For reference (from #2065)
    (1) # premises available to visit(AG); BASED ON COMBINED CRUD DATA FOR IND PREM ((# YES + # NO) for Visited, ticket 2060) AND AGGR PREM (# premises available to visit)
    (2) # available premises that were visited(AG); BASED ON COMBINED CRUD DATA FOR IND PREM (# YES for Visited) AND AGGR PREM (# Visited)
    (3) # visited premises that were treated(AG); BASED ON COMBINED CRUD DATA FOR IND PREM (# YES for Treated) AND AGGR PREM (# Total treated)
    (4) # visited premises that were not treated(AG); BASED ON COMBINED CRUD DATA FOR IND PREM (# YES for Visited - # YES for Treated) AND AGGR PREM (# Visited - # Total treated)
    */
    if (needsView)
    {
      // FIX for #2415 (individual visit - premise available for visit is only 0 if it's null for the calc)
      String numPremAvailVisit = "(CASE WHEN "+visited+" IS NOT NULL THEN 1 ELSE 0 END)";
      
      String idCol = QueryUtil.getIdColumn();

      MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(AggregatedPremiseMethod.CLASS);

      String aggVisitTable = MdBusiness.getMdBusiness(AggregatedPremiseVisit.CLASS).getTableName();
      String individualVisitTable = MdBusiness.getMdEntity(IndividualPremiseVisit.CLASS).getTableName();

      String parentTerm = QueryUtil.getColumnName(AllPaths.getParentTermMd());
      String viewTerm = "view_term";

      String iGE = QueryUtil.getColumnName(IndividualPremiseVisit.getGeoEntityMd());
      String aGE = QueryUtil.getColumnName(AggregatedPremiseVisit.getGeoEntityMd());
      
      if(byReasonNotTreated)
      {
        String aggPresmiseReaonTable = MdEntity.getMdEntity(AggregatedPremiseReason.CLASS).getTableName();
        
        String pvu2 = "";
        pvu2 += "   SELECT  point, "+numPremAvailVisit+" AS premises, visited, treated, individual_premise_visit.id as visit \n";
        pvu2 += "   FROM individual_premise_visit individual_premise_visit \n";
        pvu2 += "   WHERE  visited IS NOT null \n"; 
        pvu2 += "   UNION ALL \n";
        pvu2 += "   SELECT  point,premises, visited , treated, aggregated_premise_visit.id as visit \n";
        pvu2 += "   FROM aggregated_premise_visit aggregated_premise_visit \n";
        pvu2 += "   WHERE  visited IS NOT null \n";
        
        String  viewSql = "";
        viewSql = "SELECT  point, "+individualVisitTable+"."+idCol+" as visit, \n";
        viewSql += "CASE WHEN "+reasonCol+" is null THEN 0 ELSE 1 END AS amount, "+individualVisitTable+".id as id, term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+iGE+" AS geo_entity \n";
        viewSql += " FROM " + individualVisitTable + " individual_premise_visit LEFT JOIN term as term0 on "+individualVisitTable+"."+reasonCol+" = term0.id\n";
        viewSql += "WHERE "+visited+" IS NOT NULL";
        
        viewSql += " UNION ALL\n";
        
        viewSql += " SELECT  point, "+aggPresmiseReaonTable+".parent_id as visit, \n";
        viewSql += " "+amount+", "+aggPresmiseReaonTable+".id as id,  term0.name as childId_displayLabel,\n";
        viewSql += " term0.id AS "+viewTerm+", "+aGE+" AS geo_entity \n";
        viewSql += " FROM " + aggVisitTable + " aggregated_premise_visit , " + aggPresmiseReaonTable + " "+aggPresmiseReaonTable+"  LEFT JOIN term as term0 on "+aggPresmiseReaonTable+".child_id = term0.id\n";
        viewSql += " WHERE  "+aggPresmiseReaonTable+".parent_id = aggregated_premise_visit.id\n";
        
        this.addWITHEntry(new WITHEntry(view, viewSql));
        this.addWITHEntry(new WITHEntry(view2, pvu2));
        valueQuery.AND(new InnerJoinEq(idCol, controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view, view));
        valueQuery.AND(new InnerJoinEq(idCol, controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view2, view2));
      }
      else if(byTreatmentMethod)
      {
        String individualVisitMethodTable = MdEntity.getMdEntity(IndividualPremiseVisitMethod.CLASS).getTableName();
        String aggVisitMethodTable = MdEntity.getMdEntity(AggregatedPremiseMethod.CLASS).getTableName();
        String apAmount = QueryUtil.getColumnName(ammountMd, AggregatedPremiseMethod.AMOUNT);

        String viewSql = "";
        viewSql = "SELECT  point, "+numPremAvailVisit+" AS "+ premises +", "+visited+
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
        viewSql += " WHERE  "+aggVisitMethodTable+".parent_id = aggregated_premise_visit.id \n";

        this.addWITHEntry(new WITHEntry(view, viewSql));
        valueQuery.AND(new InnerJoinEq(idCol, controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view, view));
      }
      else
      {
        String individualVisitMethodTable = MdEntity.getMdEntity(IndividualPremiseVisitMethod.CLASS).getTableName();
        String aggVisitMethodTable = MdEntity.getMdEntity(AggregatedPremiseMethod.CLASS).getTableName();
        String apAmount = QueryUtil.getColumnName(ammountMd, AggregatedPremiseMethod.AMOUNT);

        String viewSql = "";
        viewSql = "SELECT  point, "+numPremAvailVisit+" AS "+ premises +", "+visited+
          ", " + treated + ", " + used + ", 0 as "+available+", 0 as "+included+", "+individualVisitMethodTable+".parent_id as visit, \n";
        viewSql += individualVisitTable+"."+idCol+" AS id, "+iGE+" AS geo_entity \n";
        viewSql += " FROM " + individualVisitTable + " individual_premise_visit , " + individualVisitMethodTable + " "+individualVisitMethodTable+" \n";
        viewSql += " WHERE  "+individualVisitMethodTable+".parent_id = individual_premise_visit.id AND "+visited+" IS NOT null \n";
      
        viewSql += " UNION ALL\n";
      
        viewSql += " SELECT  point," + premises + ", " + visited + " , " + treated + ", " + apAmount + ", "+available+", "+included+", "+aggVisitMethodTable+".parent_id as visit, \n";
        viewSql += " aggregated_premise_method.child_id as id, "+aGE+" AS geo_entity \n";
        viewSql += " FROM " + aggVisitTable + " aggregated_premise_visit , " + aggVisitMethodTable + " "+aggVisitMethodTable+" \n";
        viewSql += " WHERE  "+aggVisitMethodTable+".parent_id = aggregated_premise_visit.id \n";
      
        this.addWITHEntry(new WITHEntry(view, viewSql));
        valueQuery.AND(new InnerJoinEq(idCol, controlInterventionTable, controlInterventionQuery.getTableAlias(), "point", view, view));
      }


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

    // Set the SQL for IndividualPremiseVisitView.REASONSFORNOTTREATED, which although a reference
    // attribute it is treated more like a grid.
    for(Selectable sel : valueQuery.getSelectableRefs())
    {
      String alias = sel.getUserDefinedAlias();
      if(alias.startsWith(QueryConstants.REASONS_FOR_NOT_TREATED_PREFIX))
      {
        String termId = alias.substring((QueryConstants.REASONS_FOR_NOT_TREATED_PREFIX).length());
        sel.setColumnAlias("_"+termId.substring(0, 32)); // Make the column alias shorter so it won't be truncated
        String sql = "CASE WHEN "+reasonCol+" = '"+termId+"' THEN 1 ELSE 0 END";
        
        SelectableSQL selSql;
        if(sel instanceof Function)
        {
          selSql = (SelectableSQL) ((Function)sel).getSelectable();
        }
        else
        {
          selSql = ((SelectableSQL)sel);
        }
          
        selSql.setSQL(sql);
      }
    }
    
    this.addGeoDisplayLabelQuery(controlInterventionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    return QueryUtil.setQueryDates(xml, valueQuery, controlInterventionQuery, controlInterventionQuery.getStartDate(), controlInterventionQuery.getEndDate(), controlInterventionQuery.getDisease());
  }
  
  /**
   * Attempts to set IndividualPremiseVisit calculations on the value query.
   * @param valueQuery
   * @return true if the calculations were specified and added; otherwise, false.
   */
  private boolean setIndividualCalculations(ValueQuery valueQuery)
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
  private boolean hasTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
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
