package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.Function;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdRelationship;

import dss.vector.solutions.Patient;
import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.OutbreakCalculation;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public class IndividualCase extends IndividualCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360073722L;

  public IndividualCase()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    super.apply();

    // Perfrom outbreak notification
    validateOutbreak();
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

  private static Date[] getWindow(Date date)
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
    
    return new Date[] {calendar.getTime(), date};
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
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(diagnosisDate);
    calendar.add(Calendar.DAY_OF_MONTH, -28);
    Date fourWeeksAgo = calendar.getTime();

    IndividualCase individualCase = new IndividualCase();
    Person person = Person.get(personId);
    Patient patient = person.getPatientDelegate();
    if (patient != null)
    {
      IndividualCaseQuery query = new IndividualCaseQuery(new QueryFactory());
      query.WHERE(query.getDiagnosisDate().GE(fourWeeksAgo));
      query.WHERE(query.getPatient().EQ(patient));
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
      if (individualCase.getResidence()==null)
      {
        individualCase.setResidence(person.getResidentialGeoEntity());
      }
      if (individualCase.getResidenceText()==null)
      {
        individualCase.setResidenceText(person.getResidentialInformation());
      }
      if (individualCase.getWorkplace()==null)
      {
        individualCase.setWorkplace(person.getWorkGeoEntity());
      }
      if (individualCase.getWorkplaceText()==null)
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
  public static ValueQuery xmlToValueQuery(String xml, String config, Boolean includeGeometry, ThematicLayer thematicLayer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, thematicLayer, includeGeometry, IndividualIPT.CLASS, IndividualIPT.FACILITY);

    IndividualCaseQuery caseQuery = (IndividualCaseQuery) queryMap.get(IndividualCase.CLASS);
    IndividualInstanceQuery instanceQuery = (IndividualInstanceQuery) queryMap.get(IndividualInstance.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);


    valueQuery.WHERE(personQuery.getPatientDelegate().EQ(caseQuery.getPatient()));
    
    if(instanceQuery != null)
    {
      valueQuery.WHERE(instanceQuery.getIndividualCase().EQ(caseQuery.getId()));

      String[] individualAttributes = Term.getTermAttributes(IndividualInstance.CLASS);
      String sql = "(" + QueryUtil.getTermSubSelect(IndividualInstance.CLASS, individualAttributes) + ")";
      String subSelect = "instanceTermSubSel";
      valueQuery.AND(new InnerJoinEq("id","individualinstance",instanceQuery.getTableAlias(),"id",sql,subSelect));
    }
    
    



    
    for (Selectable s : Arrays.asList(valueQuery.getSelectables()))
    {
      while (s instanceof Function)
      {
        Function f = (Function)s;
        s = f.getSelectable();
      }
      if (s.getUserDefinedAlias().indexOf("__") >= 0)
      {
        if (s instanceof SelectableSQL)
        {       
          ( (SelectableSQL) s ).setSQL(getGridSql(s.getUserDefinedAlias(), instanceQuery.getTableAlias()));
        }
        
        if (s instanceof SelectableSQL)
        {       
          ( (SelectableSQL) s ).setSQL(getGridSql(s.getUserDefinedAlias(), instanceQuery.getTableAlias()));
        }
      }
      
    }

    
    return QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    //AttributeMoment dateAttribute = individualIPTQuery.getServiceDate();

    //return QueryUtil.setQueryDates(xml, valueQuery, dateAttribute);

  }
  
  private static String getGridSql(String gridAlias,String parentAlias)
  {
    // int firstIndex = gridAlias.indexOf("_", 0);
    int index1 = gridAlias.indexOf("__");
    int index2 = gridAlias.lastIndexOf("__");
    String attrib = gridAlias.substring(0, index1);
    String klass = gridAlias.substring(index1+2, index2).replace("_", ".");
    String term_id = gridAlias.substring(index2+2,gridAlias.length());
    String table = MdRelationship.getMdEntity(klass).getTableName();
    String sql = "SELECT " + attrib + " FROM " + table + " WHERE child_id = '"+term_id+"' " +
                 "AND parent_id = " +parentAlias+ ".id";
    return sql;
  }

}
