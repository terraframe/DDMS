/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.session.Session;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery.PersonQueryReferenceIF;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;

public class IndividualIPTCaseView extends IndividualIPTCaseViewBase implements com.runwaysdk.generation.loader.Reloadable
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
      this.setFacilityName(instance.getFacilityName());
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

      person.appLock();
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
    else if (this.getPatient() != null)
    {
      return this.getPatient().getView();
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

    Selectable selectable = (Selectable) query.getComponentQuery().getSelectableRef(sortAttribute);

    if (sortAttribute.equals(PATIENT))
    {
      selectable = ( (PersonQueryReferenceIF) selectable ).getLastName();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

  public static IndividualIPTCaseView[] searchCases(Date serviceDate, String patientId)
  {
    if (patientId == null || patientId.length() == 0)
    {
      MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(IndividualIPTCaseView.CLASS);
      MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(IndividualIPTCaseView.PATIENT).getMdAttributeConcrete();

      RequiredAttributeException exception = new RequiredAttributeException();

      exception.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

      throw exception;
    }

    List<IndividualIPTCaseView> list = new LinkedList<IndividualIPTCaseView>();

    IndividualIPTCaseQuery query = new IndividualIPTCaseQuery(new QueryFactory());
    query.WHERE(query.getPatient().getPerson().EQ(patientId));
    query.AND(query.getDisease().EQ(Disease.getCurrent()));
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

      while (it.hasNext())
      {
        IndividualIPTCaseView view = it.next();

        Date _serviceDate = view.getServiceDate();

        if (_serviceDate != null && ! ( _serviceDate.after(past) && ( _serviceDate.before(serviceDate) || _serviceDate.equals(serviceDate) ) ))
        {
          it.remove();
        }
      }
    }

    return list.toArray(new IndividualIPTCaseView[list.size()]);
  }
}
