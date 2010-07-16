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

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.SkipIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.Physician;
import dss.vector.solutions.PhysicianQuery;
import dss.vector.solutions.Property;
import dss.vector.solutions.RelativeValueProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.OutbreakCalculation;
import dss.vector.solutions.general.ThresholdAlertCalculationType;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.util.MDSSProperties;
import dss.vector.solutions.util.QueryUtil;

public class IndividualCase extends IndividualCaseBase implements com.runwaysdk.generation.loader.Reloadable
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
    validateSymptomOnset();
    validateDiagnosisDate();
    validateCaseEntryDate();
    validateCaseReportDate();
    validateHemorrhagicOnset();
    validatePlasmaLeakageOnset();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    if (this.getProbableSource() == null)
    {
      this.setProbableSource(this.getResidence());
    }

    if (this.isNew())
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

    if (this.getSymptomOnset() == null)
    {
      this.setSymptomOnset(this.getDiagnosisDate());

      super.apply();
    }

    // Perfrom outbreak notification
    // validateOutbreak();
  }

  @Override
  public void validateSymptomOnset()
  {
    if (this.getSymptomOnset() != null)
    {
      if (this.getSymptomOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getSymptomOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, SYMPTOMONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getSymptomOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, SYMPTOMONSET);
        p.setAttributeDisplayLabel(getSymptomOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateCaseReportDate()
  {
    if (this.getCaseReportDate() != null)
    {
      if (this.getCaseReportDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getCaseReportDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, CASEREPORTDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getDiagnosisDate() != null && this.getCaseReportDate().before(this.getDiagnosisDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEREPORTDATE);
        p.setAttributeDisplayLabel(getCaseReportDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(getDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getCaseReportDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEREPORTDATE);
        p.setAttributeDisplayLabel(getCaseReportDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateCaseEntryDate()
  {
    if (this.getCaseEntryDate() != null)
    {
      if (this.getCaseEntryDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getCaseEntryDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, CASEENTRYDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getCaseEntryDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, CASEENTRYDATE);
        p.setAttributeDisplayLabel(getCaseEntryDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateDiagnosisDate()
  {
    if (this.getDiagnosisDate() != null)
    {
      if (this.getDiagnosisDate().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getDiagnosisDate());
        p.setCurrentDate(new Date());
        p.setNotification(this, DIAGNOSISDATE);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getDiagnosisDate()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, DIAGNOSISDATE);
        p.setAttributeDisplayLabel(getDiagnosisDateMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateHemorrhagicOnset()
  {
    if (this.getHemorrhagicOnset() != null)
    {
      if (this.getHemorrhagicOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getHemorrhagicOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, HEMORRHAGICONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getHemorrhagicOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, HEMORRHAGICONSET);
        p.setAttributeDisplayLabel(getHemorrhagicOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validatePlasmaLeakageOnset()
  {
    if (this.getPlasmaLeakageOnset() != null)
    {
      if (this.getPlasmaLeakageOnset().after(new Date()))
      {
        CurrentDateProblem p = new CurrentDateProblem();
        p.setGivenDate(this.getPlasmaLeakageOnset());
        p.setCurrentDate(new Date());
        p.setNotification(this, PLASMALEAKAGEONSET);
        p.apply();
        p.throwIt();
      }

      if (this.getPatient() != null && this.getPatient().getPerson() != null && this.getPatient().getPerson().getDateOfBirth() != null && this.getPatient().getPerson().getDateOfBirth().after(this.getPlasmaLeakageOnset()))
      {
        RelativeValueProblem p = new RelativeValueProblem();
        p.setNotification(this, PLASMALEAKAGEONSET);
        p.setAttributeDisplayLabel(getPlasmaLeakageOnsetMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setRelation(MDSSProperties.getString("Compare_AE"));
        p.setRelativeAttributeLabel(Person.getDateOfBirthMd().getDisplayLabel(Session.getCurrentLocale()));
        p.apply();

        p.throwIt();
      }
    }
  }

  @SkipIfProblem
  public void validateOutbreak()
  {
    GeoEntity geoEntity = this.getProbableSource();
    Date date = this.getDiagnosisDate();

    if (geoEntity != null && date != null)
    {
      Date[] window = IndividualCase.getWindow(date);

      // Get all relevant Geo Entities
      GeoEntity[] entities = geoEntity.getPopulationAncestors();

      // First get the Threshold data for the relevant GeoEntities
      for (GeoEntity entity : entities)
      {
        double count = IndividualCase.getCount(entity, window[0], window[1]);

        ThresholdData.checkThresholdViolation(date, entity, count);
      }
    }
  }

  protected static Date[] getWindow(Date date)
  {
    ThresholdAlertCalculationType config = ThresholdAlertCalculationType.getCurrent();

    if (config.getCountingMethod().contains(OutbreakCalculation.SLIDING_WEEK))
    {
      // Use the sliding window approach
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.DAY_OF_YEAR, -6);

      return new Date[] { calendar.getTime(), date };
    }

    // Use the Epi week approach
    EpiDate week = EpiDate.getEpiWeek(date);

    return new Date[] { week.getStartDate(), week.getEndDate() };
  }

  private static double getCount(GeoEntity entity, Date startDate, Date endDate)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery entityQuery = entity.getPoliticalDecendants(factory);

    PoliticalThresholdCalculator calc = new PoliticalThresholdCalculator();
    double[] counts = calc.getIndividualCounts(factory, entityQuery, startDate, endDate);

    ThresholdAlertCalculationType config = ThresholdAlertCalculationType.getCurrent();

    double ratio = (double) config.getClinicalPositivePercentage() / 100.0d;
    return counts[PoliticalThresholdCalculator.POSITIVE_COUNT_INDEX] + ( counts[PoliticalThresholdCalculator.CLINICAL_COUNT_INDEX] * ratio );
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
      query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
      query.AND(query.getDiagnosisDate().GE(newCasePeriodCutoff));
      query.AND(query.getDiagnosisDate().LE(diagnosisDate));
      query.AND(query.getPatient().EQ(patient));
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
      // If values don't exist on the case, give them defaults from the
      // person
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
  public void applyWithPersonId(String personId, IndividualInstance instance, Term[] symptoms)
  {
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient == null)
    {
      patient = new Patient();
      patient.setPerson(person);
      patient.apply();

      person.lockPerson();
      person.setPatientDelegate(patient);
      person.apply();
    }

    this.setPatient(patient);
    this.apply();

    instance.setIndividualCase(this);
    instance.applyAll(symptoms);
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
    PhysicianQuery physicianQuery = (PhysicianQuery) queryMap.get(Physician.CLASS);

    if (physicianQuery != null)
    {
      valueQuery.WHERE(instanceQuery.getPhysician().EQ(physicianQuery));
    }

    valueQuery.WHERE(personQuery.getPatientDelegate().EQ(caseQuery.getPatient()));

    valueQuery.WHERE(instanceQuery.getIndividualCase().EQ(caseQuery.getId()));

    QueryUtil.joinGeoDisplayLabels(valueQuery, IndividualCase.CLASS, caseQuery);
    QueryUtil.joinGeoDisplayLabels(valueQuery, Person.CLASS, personQuery);

    String idCol = QueryUtil.getIdColumn();
    QueryUtil.leftJoinTermDisplayLabels(valueQuery, instanceQuery, instanceQuery.getTableAlias() + "." + idCol);
    QueryUtil.leftJoinTermDisplayLabels(valueQuery, caseQuery, caseQuery.getTableAlias() + "." + idCol);

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, IndividualInstance.CLASS, instanceQuery);

    if (valueQuery.hasSelectableRef("healthFacility"))
    {
      SelectableSQLCharacter hf = (SelectableSQLCharacter) valueQuery.getSelectableRef("healthFacility");
      QueryUtil.subselectGeoDisplayLabels(hf, IndividualInstance.CLASS, IndividualInstance.HEALTHFACILITY, instanceQuery.getTableAlias() + "." + idCol);
    }

    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);

    if (valueQuery.hasSelectableRef("instances"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("instances");
      String sql = "COUNT(*)";
      calc.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef("cases"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("cases");
      String tableAlias = caseQuery.getTableAlias();
      // String sql = "SUM(1/(SELECT COUNT(*) FROM " + tableName +
      // " AS ii WHERE ii." + indCaseCol + " = " + tableAlias + ".id))";
      String sql = "COUNT(DISTINCT " + tableAlias + "." + idCol + ")";
      calc.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef("deaths"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("deaths");
      String diedInFacCol = QueryUtil.getColumnName(instanceQuery.getMdClassIF(), IndividualInstance.DIEDINFACILITY);
      String sql = "SUM(" + diedInFacCol + ")";
      calc.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef("cfr"))
    {
      String indCaseCol = QueryUtil.getColumnName(instanceQuery.getMdClassIF(), IndividualInstance.INDIVIDUALCASE);
      String diedInFacCol = QueryUtil.getColumnName(instanceQuery.getMdClassIF(), IndividualInstance.DIEDINFACILITY);
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("cfr");
      String tableAlias = caseQuery.getTableAlias();
      String tableName = MdBusiness.getMdBusiness(IndividualInstance.CLASS).getTableName();
      String sql = "(SUM(" + diedInFacCol + ")/SUM(1/(SELECT COUNT(*) FROM " + tableName + " AS ii WHERE ii." + indCaseCol + " = " + tableAlias + "." + idCol + ")))*100.0";
      calc.setSQL(sql);
    }

    calculateIncidence(valueQuery, caseQuery, queryConfig, xml, 100);
    calculateIncidence(valueQuery, caseQuery, queryConfig, xml, 1000);
    calculateIncidence(valueQuery, caseQuery, queryConfig, xml, 10000);
    calculateIncidence(valueQuery, caseQuery, queryConfig, xml, 100000);
    calculateIncidence(valueQuery, caseQuery, queryConfig, xml, 1000000);

    QueryUtil.getSingleAttribteGridSql(valueQuery, instanceQuery.getTableAlias());

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    Disease disease = Disease.getCurrent();
    valueQuery.AND(caseQuery.getDisease().EQ(disease));

    return valueQuery;

  }

  private static void calculateIncidence(ValueQuery valueQuery, IndividualCaseQuery caseQuery, JSONObject queryConfig, String xml, Integer multiplier)
  {
    SelectableSQLFloat calc;
    if (valueQuery.hasSelectableRef("incidence_" + multiplier))
    {
      calc = (SelectableSQLFloat) valueQuery.getSelectableRef("incidence_" + multiplier);
    }
    else
    {
      return;
    }

    String geoType = null;
    try
    {
      String attributeKey = null;

      String[] selectedUniversals = null;

      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0 && attributeKey.equals(IndividualCase.CLASS + '.' + IndividualCase.PROBABLESOURCE))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          geoType = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          geoType = geoType.substring(geoType.lastIndexOf('.')).toLowerCase();
          geoType = attributeKey + '.' + geoType + '.' + GeoEntity.GEOID;
          geoType = geoType.replace('.', '_');
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    String timePeriod = "yearly";

    if (xml.indexOf("season") > 0)
    {
      timePeriod = "seasonal";
    }

    Selectable s;
    try
    {
      s = valueQuery.getSelectableRef(geoType);
    }
    catch (QueryException e)
    {
      throw new IncidencePopulationException(e);
    }

    String columnAlias = s.getDbQualifiedName();

    String tableAlias = caseQuery.getTableAlias();

    MdEntityDAOIF mdIndInst = MdEntityDAO.getMdEntityDAO(IndividualInstance.CLASS);
    String tableName = mdIndInst.getTableName();
    String indCaseCol = QueryUtil.getColumnName(mdIndInst, IndividualInstance.INDIVIDUALCASE);
    String onset = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.SYMPTOMONSET);

    String sql = "(SUM(1.0/(SELECT COUNT(*) FROM " + tableName + " AS ii WHERE ii." + indCaseCol + " = " + tableAlias + ".id))/";
    sql += " AVG(NULLIF(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", " + onset + "),0.0)))*" + multiplier;

    calc.setSQL(sql);
  }
}
