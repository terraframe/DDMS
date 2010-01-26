package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQLFloat;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.OutbreakCalculation;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IndividualCase extends IndividualCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360073722L;

  public IndividualCase()
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
  @Transaction
  public void apply()
  {    
    validateDiagnosisDate();
    validateCaseEntryDate();
    validateCaseReportDate();

    if (this.getProbableSource() == null)
    {
      this.setProbableSource(this.getResidence());
    }
    
    if(this.isNew())
    {
      // Truncate the createdByDate and store it in entry date
      this.setCaseEntryDate(new Date());
    }

    // If no age is specified, calculate it
    if (this.getAge() == null && this.getDiagnosisDate() != null && this.getPatient() != null)
    {
      long difference = this.getDiagnosisDate().getTime() - this.getPatient().getPerson().getDateOfBirth().getTime();
      // Divide by the number of milliseconds in a year
      long age = difference / 31556926000l;
      this.setAge((int) age);
    }

    super.apply();

    // Perfrom outbreak notification
    validateOutbreak();
  }

  @Override
  public void validateCaseReportDate()
  {
    if (this.getCaseReportDate() != null && this.getCaseReportDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getCaseReportDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, CASEREPORTDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateCaseEntryDate()
  {
    if (this.getCaseEntryDate() != null && this.getCaseEntryDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getCaseEntryDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, CASEENTRYDATE);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateDiagnosisDate()
  {
    if (this.getDiagnosisDate() != null && this.getDiagnosisDate().after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(this.getDiagnosisDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, DIAGNOSISDATE);
      p.apply();
      p.throwIt();
    }
  }

  private void validateOutbreak()
  {
    GeoEntity geoEntity = this.getProbableSource();
    Date date = this.getDiagnosisDate();

    if (geoEntity != null && date != null)
    {
      Date[] window = IndividualCase.getWindow(date);

      // Get all relevant Geo Entities
      GeoEntity[] entities = geoEntity.getPoliticalAncestors();

      // First get the Threshold data for the relevant GeoEntities
      for (GeoEntity entity : entities)
      {
        long count = IndividualCase.getCount(entity, window[0], window[1]);

        ThresholdData.checkThresholdViolation(date, entity, count);
      }
    }
  }  

  protected static Date[] getWindow(Date date)
  {
    Property property = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE, PropertyInfo.IS_EPI_WEEK);

    OutbreakCalculation method = OutbreakCalculation.valueOf(property.getPropertyValue());

    if (method.equals(OutbreakCalculation.EPI_WEEK))
    {
      EpiDate week = EpiDate.getEpiWeek(date);

      return new Date[] { week.getStartDate(), week.getEndDate() };
    }

    // Use the sliding window approach
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, -6);

    return new Date[] { calendar.getTime(), date };
  }

  private static long getCount(GeoEntity entity, Date startDate, Date endDate)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery entityQuery = entity.getPoliticalDecendants(factory);

    IndividualCaseQuery query = new IndividualCaseQuery(factory);

    Condition condition = query.getProbableSource().EQ(entityQuery);
    condition = AND.get(condition, query.getDiagnosisDate().GE(startDate));
    condition = AND.get(condition, query.getDiagnosisDate().LE(endDate));

    query.WHERE(condition);

    return query.getCount();
  }

  public static IndividualCase searchForExistingCase(Date diagnosisDate, String personId)
  {
    if (diagnosisDate == null)
    {
      diagnosisDate = new Date();
    }

    int newCasePeriod = Property.getInt("dss.vector.solutions.intervention.monitor", "newCasePeriod");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(diagnosisDate);
    calendar.add(Calendar.DAY_OF_MONTH, -7 * newCasePeriod);
    
    Date newCasePeriodCutoff = calendar.getTime();

    IndividualCase individualCase = new IndividualCase();
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    
    if (patient != null)
    {
      IndividualCaseQuery query = new IndividualCaseQuery(new QueryFactory());
      
      Condition condition = AND.get(query.getDiagnosisDate().GE(newCasePeriodCutoff), query.getDiagnosisDate().LE(diagnosisDate), query.getPatient().EQ(patient));
      
      query.WHERE(condition);
      query.ORDER_BY_DESC(query.getDiagnosisDate());

      OIterator<? extends IndividualCase> iterator = query.getIterator();
      if (iterator.hasNext())
      {
        individualCase = iterator.next();
      }
      iterator.close();
    }

    if (individualCase.isNew())
    {
      // If values don't exist on the case, give them defaults from the person
      if (individualCase.getResidence() == null)
      {
        individualCase.setResidence(person.getResidentialGeoEntity());
      }
      if (individualCase.getResidenceText() == null)
      {
        individualCase.setResidenceText(person.getResidentialInformation());
      }
      if (individualCase.getWorkplace() == null)
      {
        individualCase.setWorkplace(person.getWorkGeoEntity());
      }
      if (individualCase.getWorkplaceText() == null)
      {
        individualCase.setWorkplaceText(person.getWorkInformation());
      }
    }

    return individualCase;
  }

  @Override
  @Transaction
  public void applyWithPersonId(String personId)
  {
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient == null)
    {
      patient = new Patient();
      patient.setPerson(person);
      patient.apply();

      person.lock();
      person.setPatientDelegate(patient);
      person.apply();
    }

    this.setPatient(patient);
    this.apply();
  }

  public IndividualInstanceQuery getInstances()
  {
    IndividualInstanceQuery query = new IndividualInstanceQuery(new QueryFactory());
    query.WHERE(query.getIndividualCase().EQ(this));
    return query;
  }

  @Override
  protected String buildKey()
  {
    Patient patient = this.getPatient();
    GeoEntity source = this.getProbableSource();
    Date date = this.getDiagnosisDate();
    if (patient != null && date != null && source != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      Person person = patient.getPerson();

      return person.getFirstName() + "." + person.getLastName() + "." + source.getGeoId() + "." + format.format(date);
    }
    return this.getId();
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

    IndividualCaseQuery caseQuery = (IndividualCaseQuery) queryMap.get(IndividualCase.CLASS);
    IndividualInstanceQuery instanceQuery = (IndividualInstanceQuery) queryMap.get(IndividualInstance.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    valueQuery.WHERE(personQuery.getPatientDelegate().EQ(caseQuery.getPatient()));

    valueQuery.WHERE(instanceQuery.getIndividualCase().EQ(caseQuery.getId()));

    QueryUtil.joinGeoDisplayLabels(valueQuery, IndividualCase.CLASS, caseQuery);

    QueryUtil.joinTermAllpaths(valueQuery, IndividualInstance.CLASS, instanceQuery);

    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);

    try
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("instances");
      String sql = "COUNT(*)";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }

    try
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("cases");
      String tableAlias = caseQuery.getTableAlias();
      String tableName = MdBusiness.getMdBusiness(IndividualInstance.CLASS).getTableName();
      String sql = "SUM(1/(SELECT COUNT(*) FROM " + tableName + " AS ii WHERE ii.individualcase = " + tableAlias + ".id))";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }

    try
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("deaths");
      String sql = "SUM(diedInFacility)";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }

    try
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("cfr");
      String tableAlias = caseQuery.getTableAlias();
      String tableName = MdBusiness.getMdBusiness(IndividualInstance.CLASS).getTableName();
      String sql = "(SUM(diedInFacility)/SUM(1/(SELECT COUNT(*) FROM " + tableName + " AS ii WHERE ii.individualcase = " + tableAlias + ".id)))*100.0";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }


    calculateIncidence(valueQuery,caseQuery,queryConfig, xml,100);
    calculateIncidence(valueQuery,caseQuery,queryConfig, xml,1000);
    calculateIncidence(valueQuery,caseQuery,queryConfig, xml,10000);
    calculateIncidence(valueQuery,caseQuery,queryConfig, xml,100000);
    calculateIncidence(valueQuery,caseQuery,queryConfig, xml,1000000);
    
    QueryUtil.getSingleAttribteGridSql(valueQuery, instanceQuery.getTableAlias());

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    return valueQuery;

  }
  
  private static void calculateIncidence(ValueQuery valueQuery,IndividualCaseQuery caseQuery,JSONObject queryConfig, String xml,Integer multiplier )
  {
    try
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("incidence_"+multiplier);
      
      String geoType = null;
      
      String attributeKey = null;
      
      String[] selectedUniversals = null;
      
      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0 && attributeKey.equals(IndividualCase.CLASS+'.'+IndividualCase.PROBABLESOURCE))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          //dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          geoType =  GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          geoType = geoType.substring(geoType.lastIndexOf('.')).toLowerCase();
          geoType = attributeKey + '.' + geoType + '.' + GeoEntity.GEOID;
          geoType = geoType.replace('.', '_');
        }
      }

      
      String timePeriod = "yearly";
      
      if(xml.indexOf("season")>0)
      {
        timePeriod = "seasonal";
      }
      
      Selectable s = valueQuery.getSelectableRef(geoType);
      
      String columnAlias = s.getQualifiedName();
      
      String tableAlias = caseQuery.getTableAlias();
      String tableName = MdBusiness.getMdBusiness(IndividualInstance.CLASS).getTableName();
      
      String sql = "(SUM(1.0/(SELECT COUNT(*) FROM " + tableName + " AS ii WHERE ii.individualcase = " + tableAlias + ".id))/";
      sql += " AVG(get_"+timePeriod+"_population_by_geoid_and_date("+columnAlias+", caseReportDate)))*"+multiplier;

      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    catch (JSONException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
