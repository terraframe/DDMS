package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class SurveyPoint extends SurveyPointBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641306732L;

  public SurveyPoint()
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
    else if (this.getGeoEntity() != null && this.getSurveyDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());

      return this.getClassDisplayLabel() + ": (" + this.getGeoEntity().getLabel() + ", " + format.format(this.getSurveyDate()) + ")";
    }

    return super.toString();
  }

  @Override
  public void apply()
  {
    validateSurveyDate();
    
    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getSurveyDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      return this.getGeoEntity().getGeoId() + "." + format.format(this.getSurveyDate());
    }

    return this.getId();
  }

  @Override
  public void validateSurveyDate()
  {
    if (this.getSurveyDate() != null)
    {
      super.validateSurveyDate();

      Date current = new Date();

      if (current.before(this.getSurveyDate()))
      {
        String msg = "It is impossible to have a survey date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getSurveyDate());
        p.setCurrentDate(current);
        p.setNotification(this, SURVEYDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    // First delete all of the households of this survey point
    List<Household> list = new LinkedList<Household>();
    OIterator<? extends Household> it = this.getAllHouseholds();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
    }
    finally
    {
      it.close();
    }

    for (Household household : list)
    {
      household.delete();
    }

    super.delete();
  }

  @Override
  public SurveyPointView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public SurveyPointView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  private SurveyPointView getView()
  {
    SurveyPointView view = new SurveyPointView();

    view.populateView(this);

    return view;
  }

  public static SurveyPointView getView(String id)
  {
    return SurveyPoint.get(id).getView();
  }

  public static SurveyPoint searchByGeoEntityAndDate(GeoEntity geoEntity, Date date)
  {
    SurveyPointQuery query = new SurveyPointQuery(new QueryFactory());
    query.WHERE(query.getSurveyDate().EQ(date));
    query.AND(query.getGeoEntity().EQ(geoEntity));

    OIterator<? extends SurveyPoint> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
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

    SurveyPointQuery surveyPointQuery = (SurveyPointQuery) queryMap.get(SurveyPoint.CLASS);
    if (surveyPointQuery != null)
    {
      QueryUtil.joinGeoDisplayLabels(valueQuery, SurveyPoint.CLASS, surveyPointQuery);
    }

    HouseholdQuery householdQuery = (HouseholdQuery) queryMap.get(Household.CLASS);
    if (householdQuery != null)
    {
      valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, Household.CLASS, householdQuery);
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, Household.CLASS, householdQuery);
    }

    SurveyedPersonQuery personQuery = (SurveyedPersonQuery) queryMap.get(SurveyedPerson.CLASS);
    if (personQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new HouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
      }

      valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));

      QueryUtil.joinTermAllpaths(valueQuery, SurveyedPerson.CLASS, personQuery);
      QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, SurveyedPerson.CLASS, personQuery, personQuery.getTableAlias() + ".id");
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
    }

    if (personQuery == null && xml.indexOf("one__dss_vector_solutions_intervention_monitor_SurveyedPerson") > 0)
    {
      // we pass in a value query instead of a query factory so that we use a
      // normal join instead of IN()
      personQuery = new SurveyedPersonQuery(valueQuery);
      valueQuery.SELECT(personQuery.getId());
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
      if (householdQuery != null)
      {
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

    }

    ITNInstanceQuery itnQuery = (ITNInstanceQuery) queryMap.get(ITNInstance.CLASS);

    if (itnQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new HouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
      }
      if (personQuery == null)
      {
        // join against the house if there is no person
        valueQuery.WHERE(householdQuery.iTNs(itnQuery));
        QueryUtil.joinTermAllpaths(valueQuery, ITNInstance.CLASS, itnQuery);
        QueryUtil.joinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery);
      }
      else
      {
        // left join against the person is person is in this query
        LeftJoinEq leftJoin = personQuery.getSleptUnderNet().LEFT_JOIN_EQ(itnQuery);
        valueQuery.WHERE(leftJoin);
        QueryUtil.leftJoinTermDisplayLabels(valueQuery, itnQuery, itnQuery.getId().getColumnAlias());
        QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery, itnQuery.getId().getColumnAlias());
      }

    }

    if (valueQuery.hasSelectableRef(QueryConstants.RDT_PREVALENCE) || 
        valueQuery.hasSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE) ||
        valueQuery.hasSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE))
    {
      if (personQuery == null)
      {
        
        // we pass in a value query instead of a query factory so that we use a
        // normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        if (householdQuery != null)
        {
          valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
        }
        else
        {
          householdQuery = new HouseholdQuery(valueQuery);
          valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
        }
        
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

      String tableAlias = personQuery.getTableAlias();
      MdEntityDAOIF md = personQuery.getMdClassIF();
      String performedRDTCol = QueryUtil.getColumnName(md, SurveyedPerson.PERFORMEDRDT);
      String performedBloodSlideCol = QueryUtil.getColumnName(md, SurveyedPerson.PERFORMEDBLOODSLIDE);
      String rdtResultCol = QueryUtil.getColumnName(md, SurveyedPerson.RDTRESULT);
      String bloodSlideResultCol = QueryUtil.getColumnName(md, SurveyedPerson.BLOODSLIDERESULT);
      
      String yesId = RefusedResponse.YES.getId();
      String rtdTested = "CASE " + tableAlias + "." + performedRDTCol + "_c WHEN '" + yesId + "' THEN 1 ELSE NULL END";
      String rdtPositive = "SUM("+rdtResultCol+")";
      String rdtTotal = "SUM("+rtdTested+")";
      String rdtPrevalance = rdtPositive + "/NULLIF("+rdtTotal+",0.0)::float*100.0";
      
      String bloodslidePostive = "SUM("+bloodSlideResultCol+")";
      String bloodslideTotal = "SUM("+performedBloodSlideCol+")";
      String bloodslidePrevalence = bloodslidePostive+"/NULLIF("+bloodslideTotal+",0.0)::float*100.0";
      
//      String totalTested = "SUM(COALESCE(" + rtdTested + "," + performedBloodSlideCol + ",0))::FLOAT";
//      String totalPositive = "SUM(COALESCE(" + rdtResultCol + "," + bloodSlideResultCol + ",0))::FLOAT";

      // if bs+ then add r
      // else rdt+ then add r(+)
      
      if(valueQuery.hasSelectableRef(QueryConstants.RDT_PREVALENCE))
      {
        ((SelectableSQL)valueQuery.getSelectableRef(QueryConstants.RDT_PREVALENCE)).setSQL(rdtPrevalance);
      }
      
      if(valueQuery.hasSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE))
      {
        ((SelectableSQL)valueQuery.getSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE)).setSQL(bloodslidePrevalence);
      }

      if(valueQuery.hasSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE))
      {
        String totalP = "SUM(CASE WHEN "+performedBloodSlideCol+" = 1 THEN "+bloodSlideResultCol+" ";
        totalP += "WHEN " + tableAlias + "." + performedRDTCol + "_c = '"+yesId+"' THEN "+rdtResultCol+" ELSE NULL END) \n";
        
        String totalT = "SUM(CASE WHEN "+performedBloodSlideCol+" = 1 THEN 1 \n";
        totalT += "WHEN " + tableAlias + "." + performedRDTCol + "_c = '"+yesId+"' THEN 1 ELSE NULL END) ";
   
        String rdtBloodslidePrevalence = totalP+"/NULLIF("+totalT+",0.0)::float*100.0";
        
        ((SelectableSQL)valueQuery.getSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE)).setSQL(rdtBloodslidePrevalence);
      }
      
    }

    if (valueQuery.hasSelectableRef("age"))
    {
      // valueQuery.hasSelectableRef
      SelectableSQLInteger dobSel;
      Selectable sel = valueQuery.getSelectableRef("age");
      if(sel instanceof AggregateFunction)
      {
        dobSel = (SelectableSQLInteger) ((AggregateFunction)sel).getSelectable();
      }
      else
      {
        dobSel = (SelectableSQLInteger) sel;
      }

      if (personQuery == null)
      {
        // we pass in a value query instead of a query factory so that we use a
        // normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        if (householdQuery == null)
        {
          householdQuery = new HouseholdQuery(valueQuery);
          valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
        }
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

      String personTableAlias = personQuery.getTableAlias();
      String surveyPointTableAlais = surveyPointQuery.getTableAlias();
      
      String surveyDateCol = QueryUtil.getColumnName(surveyPointQuery.getMdClassIF(), SurveyPoint.SURVEYDATE);
      String dobCol = QueryUtil.getColumnName(personQuery.getMdClassIF(), SurveyedPerson.DOB);
      
      String sql = "EXTRACT(year from AGE(" + surveyPointTableAlais + "." + surveyDateCol + ", " + personTableAlias + "." + dobCol + "))";
      dobSel.setSQL(sql);
    }
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    return valueQuery;
  }
}
