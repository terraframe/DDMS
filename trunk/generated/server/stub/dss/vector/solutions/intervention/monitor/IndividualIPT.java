package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.query.AttributeMoment;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public class IndividualIPT extends IndividualIPTBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253643991663L;

  public IndividualIPT()
  {
    super();
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
    validateNumberOfRecievedITNs();
    validateServiceDate();

    // Set the service date to the last update time
    if(this.getServiceDate() == null)
    {
      this.setServiceDate(new Date());
    }
    
    super.apply();
  }


  @Override
  public void validateNumberOfRecievedITNs()
  {
    if (this.getNumberOfRecievedITNs() != null && ( this.getRecievedITN() == null || !this.getRecievedITN() ))
    {
      String msg = "Number of nets recieved is not applicable when no nets are recieved.";

      MdAttributeBooleanDAOIF retrievedMd = (MdAttributeBooleanDAOIF) getRecievedITNMd();
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, NUMBEROFRECIEVEDITNS);
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
  public static ValueQuery xmlToValueQuery(String xml, String[] selectedUniversals, Boolean includeGeometry, ThematicLayer thematicLayer)
  {

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, thematicLayer, includeGeometry, selectedUniversals, IndividualIPT.CLASS, IndividualIPT.FACILITY);   
   
    IndividualIPTQuery IndividualIPTQuery = (IndividualIPTQuery) queryMap.get(IndividualIPT.CLASS);

   

   
    AttributeMoment dateAttribute = IndividualIPTQuery.getServiceDate();

    return QueryUtil.setQueryDates(xml,valueQuery,dateAttribute);

  }
  
}
