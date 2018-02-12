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

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.GridComparator;

public class ITNDistributionView extends ITNDistributionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255545119443L;

  public ITNDistributionView()
  {
    super();
  }

  public void populateView(ITNDistribution concrete)
  {
    this.setConcreteId(concrete.getId());

    if (concrete.getFacility() != null)
    {
      this.setFacility(concrete.getFacility().getGeoId());
    }

    this.setBatchNumber(concrete.getBatchNumber());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
    this.setDistributionDate(concrete.getDistributionDate());
    this.setDistributorName(concrete.getDistributorName());
    this.setDistributorSurname(concrete.getDistributorSurname());
    this.setNet(concrete.getNet());
    this.setNumberSold(concrete.getNumberSold());
    this.setPerson(concrete.getRecipient().getPerson());
    this.setService(concrete.getService());
  }

  private void populateConcrete(ITNDistribution concrete)
  {
    String facility = this.getFacility();

    if (facility != null && !facility.equals(""))
    {
      GeoEntity geoEntity = HealthFacility.searchByGeoId(facility);

      if (geoEntity instanceof HealthFacility)
      {
        concrete.setFacility((HealthFacility) geoEntity);
      }
      else
      {
        // No results = the geoId is invalid.
        throw new InvalidReferenceException("[" + facility + "] is not a valid Health Facility geo id.", (MdAttributeReferenceDAOIF) ITNDistribution.getFacilityMd());
      }
    }

    Person person = this.getPerson();

    ITNRecipient recipient = person.getItnRecipientDelegate();

    if (recipient == null)
    {
      recipient = new ITNRecipient();
      recipient.setPerson(person);
      recipient.apply();

      person.appLock();
      person.setItnRecipientDelegate(recipient);
      person.apply();
    }

    concrete.setRecipient(recipient);
    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
    concrete.setDistributionDate(this.getDistributionDate());
    concrete.setDistributorName(this.getDistributorName());
    concrete.setDistributorSurname(this.getDistributorSurname());
    concrete.setNet(this.getNet());
    concrete.setNumberSold(this.getNumberSold());
    concrete.setService(this.getService());
  }

  private void buildAttributeMap(ITNDistribution concrete)
  {
    new AttributeNotificationMap(concrete, ITNDistribution.FACILITY, this, ITNDistributionView.FACILITY);
    new AttributeNotificationMap(concrete, ITNDistribution.BATCHNUMBER, this, ITNDistributionView.BATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNDistribution.CURRENCYRECEIVED, this, ITNDistributionView.CURRENCYRECEIVED);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTIONDATE, this, ITNDistributionView.DISTRIBUTIONDATE);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTORNAME, this, ITNDistributionView.DISTRIBUTORNAME);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTORSURNAME, this, ITNDistributionView.DISTRIBUTORSURNAME);
    new AttributeNotificationMap(concrete, ITNDistribution.NET, this, ITNDistributionView.NET);
    new AttributeNotificationMap(concrete, ITNDistribution.NUMBERSOLD, this, ITNDistributionView.NUMBERSOLD);
    new AttributeNotificationMap(concrete, ITNDistribution.RECIPIENT, this, ITNDistributionView.PERSON);
    new AttributeNotificationMap(concrete, ITNDistribution.SERVICE, this, ITNDistributionView.SERVICE);
  }

  @Override
  @Transaction
  public void apply()
  {
    ITNDistribution concrete = new ITNDistribution();

    if (this.hasConcrete())
    {
      concrete = ITNDistribution.get(this.getConcreteId());
    }

    // Build the attribute map between ITNDistribution and
    // ITNDistributionView for error handling
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
      ITNDistribution.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  @Transaction
  public void applyAll(ITNDistributionTargetGroup[] targetGroups)
  {
    int distributed = this.getAmountDistributed(targetGroups);

    this.validateNumberSold(distributed);

    this.apply();

    for (ITNDistributionTargetGroup targetGroup : targetGroups)
    {
      targetGroup.overwriteParentId(this.getConcreteId());
      targetGroup.apply();
    }
  }

  private int getAmountDistributed(ITNDistributionTargetGroup[] targetGroups)
  {
    int total = 0;

    for (ITNDistributionTargetGroup targetGroup : targetGroups)
    {
      total += targetGroup.getAmount();
    }

    return total;
  }

  private void validateNumberSold(int distribution)
  {
    if (this.getNumberSold() != null && this.getNumberSold() > distribution)
    {
      NumberSoldProblem problem = new NumberSoldProblem();
      problem.setNotification(this, NUMBERSOLD);
      problem.apply();
      
      problem.throwIt();
    }
  }

  @Override
  public ITNDistributionTargetGroup[] getDistributionTargetGroups()
  {
    Set<ITNDistributionTargetGroup> set = new TreeSet<ITNDistributionTargetGroup>(new GridComparator());

    for (Term d : TermRootCache.getRoots(ITNDistributionView.getTargetGroupsMd()))
    {
      set.add(new ITNDistributionTargetGroup(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNDistribution concrete = ITNDistribution.get(this.getConcreteId());

      for (ITNDistributionTargetGroup d : concrete.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actual
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNDistributionTargetGroup[set.size()]);
  }

  public static ITNDistributionViewQuery searchHistory(ITNDistributionView view)
  {
    ITNDistributionViewQuery query = new ITNDistributionViewQuery(new QueryFactory());

    Person person = view.getPerson();

    Condition condition = query.getPerson().EQ(person);

    String facility = view.getFacility();
    String batchNumber = view.getBatchNumber();

    if (facility != null && !facility.equals(""))
    {
      condition = AND.get(condition, query.getFacility().EQ(facility));
    }

    if (view.getDistributionDate() != null)
    {
      condition = AND.get(condition, query.getDistributionDate().EQ(view.getDistributionDate()));
    }

    if (batchNumber != null && !batchNumber.equals(""))
    {
      condition = AND.get(condition, query.getBatchNumber().EQ(batchNumber));
    }

    query.WHERE(condition);

    return query;
  }

}
