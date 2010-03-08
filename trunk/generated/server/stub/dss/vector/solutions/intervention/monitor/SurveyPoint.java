package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.LeftJoinEq;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLFloat;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class SurveyPoint extends SurveyPointBase implements com.terraframe.mojo.generation.loader.Reloadable
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
      QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, SurveyedPerson.CLASS, personQuery, personQuery.getTableAlias()+".id");
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
    }
    
    
    if(personQuery == null && xml.indexOf("one__dss_vector_solutions_intervention_monitor_SurveyedPerson")>0)
    {
      //we pass in a value query instead of a query factory so that we use a normal join instead of IN()
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
      if(personQuery == null)
      {
        //join against the house if there is no person
        valueQuery.WHERE(householdQuery.iTNs(itnQuery));
        QueryUtil.joinTermAllpaths(valueQuery, ITNInstance.CLASS, itnQuery);
        QueryUtil.joinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery);
      }
      else
      {
        //left join against the person is person is in this query
        LeftJoinEq leftJoin = personQuery.getSleptUnderNet().LEFT_JOIN_EQ(itnQuery);
        valueQuery.WHERE(leftJoin);
        QueryUtil.leftJoinTermDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery,itnQuery.getId().getColumnAlias());
        QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery,itnQuery.getId().getColumnAlias());
      }
      
    }
    
    
    if(valueQuery.hasSelectableRef("prevalence"))
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("prevalence");
      if(personQuery == null)
      {
        //we pass in a value query instead of a query factory so that we use a normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }
      
      String tableAlias = personQuery.getTableAlias();
      
      String rtdTested = "CASE "+tableAlias+"."+SurveyedPerson.PERFORMEDRDT+"_c WHEN '"+RefusedResponse.YES.getId()+"' THEN 1 ELSE NULL END";
      
      String totalTested ="SUM(COALESCE("+rtdTested+","+SurveyedPerson.PERFORMEDBLOODSLIDE+",0))::FLOAT" ;
      
      String totalPositive ="SUM(COALESCE("+SurveyedPerson.RDTRESULT+","+SurveyedPerson.BLOODSLIDERESULT+",0))::FLOAT" ;
     
      calc.setSQL(totalPositive+" / NULLIF("+totalTested+",0.0)");
    }
    
    
    if(valueQuery.hasSelectableRef("age"))
    {
      //valueQuery.hasSelectableRef
      
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectableRef("age");

      if(personQuery == null)
      {
        //we pass in a value query instead of a query factory so that we use a normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }
      
      
      String personTableAlias = personQuery.getTableAlias();
      String surveyPointTableAlais = surveyPointQuery.getTableAlias();
      String sql = "EXTRACT(year from AGE(" + surveyPointTableAlais + "."+SurveyPoint.SURVEYDATE+", " + personTableAlias + "."+SurveyedPerson.DOB+"))";
      dobSel.setSQL(sql);
    }
    
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    


    return valueQuery;
  }
}
