package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class IndividualIPT extends IndividualIPTBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253643991663L;

  public IndividualIPT()
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
    else if (this.getServiceDate() != null && this.getFacility() != null)
    {      
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());

      return this.getClassDisplayLabel() + ": (" + this.getFacility().getLabel() + ", " + format.format(this.getServiceDate()) + ")";
    }
    
    return this.buildKey();
  }

  @Override
  protected String buildKey()
  {
    // ITN Community Distribution class has no attributes that can form a unique
    // identifier
    return this.getId();
  }

  public static IndividualIPTView getView(String id)
  {
    return IndividualIPT.get(id).getView();
  }

  public IndividualIPTView getView()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.populateView(this);

    return view;
  }

  @Override
  public IndividualIPTView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public IndividualIPTView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    validateNumberOfReceivedITNs();
    validateServiceDate();

    // Set the service date to the last update time
    if (this.getServiceDate() == null)
    {
      this.setServiceDate(new Date());
    }

    super.apply();
  }

  @Override
  public void validateNumberOfReceivedITNs()
  {
    if (this.getNumberOfReceivedITNs() != null && ( this.getReceivedITN() == null || !this.getReceivedITN() ))
    {
      String msg = "Number of nets received is not applicable when no nets are received.";

      MdAttributeBooleanDAOIF retrievedMd = (MdAttributeBooleanDAOIF) getReceivedITNMd();
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, NUMBEROFRECEIVEDITNS);
      p.setInputAttribute(retrievedMd.getDisplayLabel(locale));
      p.setInputValue(retrievedMd.getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateServiceDate()
  {
    if (this.getServiceDate() != null)
    {
      Date current = new Date();

      if (current.before(this.getServiceDate()))
      {
        String msg = "It is impossible to have a service date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getServiceDate());
        p.setCurrentDate(current);
        p.setNotification(this, SERVICEDATE);
        p.apply();
        p.throwIt();
      }
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

    IndividualIPTQuery individualIPTQuery = (IndividualIPTQuery) queryMap.get(IndividualIPT.CLASS);
    IndividualIPTCaseQuery individualIPTCaseQuery = (IndividualIPTCaseQuery) queryMap.get(IndividualIPTCase.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    if(individualIPTCaseQuery != null)
    {
      valueQuery.WHERE(individualIPTQuery.getIptCase().EQ(individualIPTCaseQuery.getId()));
      
      if (personQuery != null)
      {
        valueQuery.WHERE(personQuery.getIptRecipientDelegate().EQ(individualIPTCaseQuery.getPatient()));
        QueryUtil.joinTermAllpaths(valueQuery,dss.vector.solutions.Person.CLASS,personQuery);
        
        QueryUtil.joinGeoDisplayLabels(valueQuery,Person.CLASS,personQuery); 
      }
      
    }
    

    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectableRef("age");

      String personTableAlias = personQuery.getTableAlias();
      String serviceDateCol = QueryUtil.getColumnName(individualIPTQuery.getMdClassIF(), IndividualIPT.SERVICEDATE);
      String dateOfBirthCol = QueryUtil.getColumnName(personQuery.getMdClassIF(), Person.DATEOFBIRTH);
      
      String sql = "EXTRACT(year from AGE( "+serviceDateCol+", " + personTableAlias + "."+dateOfBirthCol+"))";
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
      // Person.DOB not included in query.
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery,IndividualIPT.CLASS,individualIPTQuery);
    
    QueryUtil.joinTermAllpaths(valueQuery,IndividualIPT.CLASS,individualIPTQuery);  

    QueryUtil.setTermRestrictions(valueQuery, queryMap );    
    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, individualIPTCaseQuery.getDisease());
    
    Disease disease = Disease.getCurrent();
    valueQuery.AND(individualIPTCaseQuery.getDisease().EQ(disease));
    
    return valueQuery;

  }
}
