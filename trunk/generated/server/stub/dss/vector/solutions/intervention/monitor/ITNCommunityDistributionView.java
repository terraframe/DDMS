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

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.GridComparator;

public class ITNCommunityDistributionView extends ITNCommunityDistributionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252612685812L;

  public ITNCommunityDistributionView()
  {
    super();
  }

  public void populateView(ITNCommunityDistribution concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setAgentFirstName(concrete.getAgentFirstName());
    this.setAgentSurname(concrete.getAgentSurname());
    this.setItnsReceived(concrete.getItnsReceived());
    this.setHasBatchNumber(concrete.getHasBatchNumber());
    this.setBatchNumber(concrete.getBatchNumber());
    this.setEntryType(concrete.getEntryType());
    this.setSold(concrete.getSold());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
    this.setRetrieved(concrete.getRetrieved());
    this.setNumberRetrieved(concrete.getNumberRetrieved());
    this.setPretreated(concrete.getPretreated());
    this.setHouseholdName(concrete.getHouseholdName());
    this.setHouseholdSurname(concrete.getHouseholdSurname());
    this.setResidents(concrete.getResidents());

    if (concrete.getHouseholdAddress() != null)
    {
      this.setHouseholdAddress(concrete.getHouseholdAddress().getGeoId());
    }

    if (concrete.getDistributionLocation() != null)
    {
      this.setDistributionLocation(concrete.getDistributionLocation().getGeoId());
    }
  }

  private void populateConcrete(ITNCommunityDistribution concrete)
  {
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setAgentFirstName(this.getAgentFirstName());
    concrete.setAgentSurname(this.getAgentSurname());
    concrete.setItnsReceived(this.getItnsReceived());
    concrete.setHasBatchNumber(this.getHasBatchNumber());
    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setEntryType(this.getEntryType());
    concrete.setSold(this.getSold());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
    concrete.setRetrieved(this.getRetrieved());
    concrete.setNumberRetrieved(this.getNumberRetrieved());
    concrete.setPretreated(this.getPretreated());
    concrete.setHouseholdName(this.getHouseholdName());
    concrete.setHouseholdSurname(this.getHouseholdSurname());
    concrete.setResidents(this.getResidents());

    if (this.getHouseholdAddress() != null && !this.getHouseholdAddress().equals(""))
    {
      concrete.setHouseholdAddress(GeoEntity.searchByGeoId(this.getHouseholdAddress()));
    }
    else
    {
      concrete.setHouseholdAddress(null);
    }

    if (this.getDistributionLocation() != null && !this.getDistributionLocation().equals(""))
    {
      concrete.setDistributionLocation(GeoEntity.searchByGeoId(this.getDistributionLocation()));
    }
    else
    {
      concrete.setDistributionLocation(null);
    }
  }

  private void buildAttributeMap(ITNCommunityDistribution concrete)
  {
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.AGENTFIRSTNAME, this, ITNCommunityDistributionView.AGENTFIRSTNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.AGENTSURNAME, this, ITNCommunityDistributionView.AGENTSURNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.ITNSRECEIVED, this, ITNCommunityDistributionView.ITNSRECEIVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HASBATCHNUMBER, this, ITNCommunityDistributionView.HASBATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.BATCHNUMBER, this, ITNCommunityDistributionView.BATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.CURRENCYRECEIVED, this, ITNCommunityDistributionView.CURRENCYRECEIVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.DISTRIBUTIONLOCATION, this, ITNCommunityDistributionView.DISTRIBUTIONLOCATION);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.ENDDATE, this, ITNCommunityDistributionView.ENDDATE);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.ENTRYTYPE, this, ITNCommunityDistributionView.ENTRYTYPE);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDADDRESS, this, ITNCommunityDistributionView.HOUSEHOLDADDRESS);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDNAME, this, ITNCommunityDistributionView.HOUSEHOLDNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDSURNAME, this, ITNCommunityDistributionView.HOUSEHOLDSURNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.NUMBERRETRIEVED, this, ITNCommunityDistributionView.NUMBERRETRIEVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.PRETREATED, this, ITNCommunityDistributionView.PRETREATED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.RESIDENTS, this, ITNCommunityDistributionView.RESIDENTS);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.RETRIEVED, this, ITNCommunityDistributionView.RETRIEVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.SOLD, this, ITNCommunityDistributionView.SOLD);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.STARTDATE, this, ITNCommunityDistributionView.STARTDATE);
  }

  @Override
  public void apply()
  {
    ITNCommunityDistribution concrete = new ITNCommunityDistribution();

    if (this.hasConcrete())
    {
      concrete = ITNCommunityDistribution.get(this.getConcreteId());
    }

    // Build the attribute map between ITNCommunityDistribution and
    // ITNCommunityDistributionView for error handling
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
      ITNCommunityDistribution.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(ITNCommunityNet[] nets, ITNCommunityTargetGroup[] targetGroups)
  {
    this.apply();

    for (ITNCommunityNet net : nets)
    {
      net.overwriteParentId(this.getConcreteId());
      net.apply();
    }

    for (ITNCommunityTargetGroup group : targetGroups)
    {
      group.overwriteParentId(this.getConcreteId());
      group.apply();
    }
  }
  
  @Override
  public ITNCommunityNet[] getITNCommunityNets()
  {
    Set<ITNCommunityNet> set = new TreeSet<ITNCommunityNet>(new GridComparator());

    for (Term d : TermRootCache.getRoots(ITNCommunityDistributionView.getDisplayNetsMd()))
    {
      set.add(new ITNCommunityNet(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNCommunityDistribution concrete = ITNCommunityDistribution.get(this.getConcreteId());

      for (ITNCommunityNet d : concrete.getAllNetsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNCommunityNet[set.size()]);
  }


  @Override
  public ITNCommunityTargetGroup[] getITNCommunityTargetGroups()
  {
    Set<ITNCommunityTargetGroup> set = new TreeSet<ITNCommunityTargetGroup>(new GridComparator());

    for (Term d : TermRootCache.getRoots(ITNCommunityDistributionView.getDisplayTargetGroupsMd()))
    {
      set.add(new ITNCommunityTargetGroup(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNCommunityDistribution concrete = ITNCommunityDistribution.get(this.getConcreteId());

      for (ITNCommunityTargetGroup d : concrete.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNCommunityTargetGroup[set.size()]);
  }

  public static ITNCommunityDistributionViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ITNCommunityDistributionViewQuery query = new ITNCommunityDistributionViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = STARTDATE;
    }

    SelectablePrimitive selectable = (SelectablePrimitive) query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }

}
