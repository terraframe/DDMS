package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.PersonQuery.PersonQueryReferenceIF;
import dss.vector.solutions.geo.generated.GeoEntity;

public class IndividualIPTCaseView extends IndividualIPTCaseViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254014386608L;

  public IndividualIPTCaseView()
  {
    super();
  }

  public void populateView(IndividualIPTCase concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setPatient(concrete.getPatient().getPerson());

    GeoEntity location = concrete.getResidentialLocation();

    if (location != null)
    {
      this.setResidentialLocation(location.getGeoId());
    }

    IndividualIPTView instance = this.getMostRecentInstance();

    if (instance != null)
    {
      this.setFacility(instance.getFacility());
      this.setServiceDate(instance.getServiceDate());
    }
  }

  private void populateConcrete(IndividualIPTCase concrete)
  {
    Person person = this.getPatient();

    IPTRecipient recipient = person.getIptRecipientDelegate();

    if (recipient == null)
    {
      recipient = new IPTRecipient();
      recipient.setPerson(person);
      recipient.apply();

      person.lock();
      person.setIptRecipientDelegate(recipient);
      person.apply();
    }

    String location = this.getResidentialLocation();

    if (location != null && !location.equals(""))
    {
      concrete.setResidentialLocation(GeoEntity.searchByGeoId(location));
    }

    concrete.setPatient(recipient);
  }

  private void buildAttributeMap(IndividualIPTCase concrete)
  {
    new AttributeNotificationMap(concrete, IndividualIPTCase.PATIENT, this, IndividualIPTCaseView.PATIENT);
    new AttributeNotificationMap(concrete, IndividualIPTCase.RESIDENTIALLOCATION, this, IndividualIPTCaseView.RESIDENTIALLOCATION);
  }

  @Override
  @Transaction
  public void applyWithInstance(IndividualIPTView instance)
  {
    this.apply();

    instance.setIptCase(IndividualIPTCase.get(this.getConcreteId()));
    instance.apply();
  }

  @Override
  public void apply()
  {
    IndividualIPTCase concrete = new IndividualIPTCase();

    if (this.hasConcrete())
    {
      concrete = IndividualIPTCase.get(this.getConcreteId());
    }

    // Build the attribute map between IndividualIPT and
    // IndividualIPTView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      IndividualIPTCase.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public IndividualIPTView getMostRecentInstance()
  {
    IndividualIPTQuery query = new IndividualIPTQuery(new QueryFactory());
    query.WHERE(query.getIptCase().EQ(this.getConcreteId()));
    query.ORDER_BY_DESC(query.getServiceDate());

    OIterator<? extends IndividualIPT> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        return iterator.next().getView();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public PersonView getPatientView()
  {
    if (this.hasConcrete())
    {
      IndividualIPTCase iptCase = IndividualIPTCase.get(this.getConcreteId());

      return iptCase.getPatient().getPerson().getView();
    }

    return null;
  }

  public static IndividualIPTCaseViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    IndividualIPTCaseViewQuery query = new IndividualIPTCaseViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = PATIENT;
    }

    Selectable selectable = (Selectable) query.getComponentQuery().getSelectable(sortAttribute);

    if (sortAttribute.equals(PATIENT))
    {
      selectable = ( (PersonQueryReferenceIF) selectable ).getLastName();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  public static IndividualIPTCaseView[] searchCases(Date serviceDate, String patientId)
  {
    List<IndividualIPTCaseView> list = new LinkedList<IndividualIPTCaseView>();

    IndividualIPTCaseQuery query = new IndividualIPTCaseQuery(new QueryFactory());
    query.WHERE(query.getPatient().getPerson().EQ(patientId));
    OIterator<? extends IndividualIPTCase> iterator = query.getIterator();
    
    try
    {
      while (iterator.hasNext())
      {
        IndividualIPTCaseView view = iterator.next().getView();

        list.add(view);
      }
    }
    finally
    {
      iterator.close();
    }

    if (serviceDate != null)
    {
      // We only want to search for IPT cases which are newer than 9 months ago
      int limit = 9;

      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(serviceDate);
      calendar.add(Calendar.MONTH, -limit);

      Date past = calendar.getTime();
      
      Iterator<IndividualIPTCaseView> it = list.iterator();
      
      while(it.hasNext())
      {
        IndividualIPTCaseView view = it.next();
        
        if (view.getServiceDate() != null && !(view.getServiceDate().after(past) && view.getServiceDate().before(serviceDate)))
        {
          it.remove();
        }
      }
    }

    return list.toArray(new IndividualIPTCaseView[list.size()]);
  }
}
