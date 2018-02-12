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
package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class CollectionContainerView extends CollectionContainerViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -720402978;

  public CollectionContainerView()
  {
    super();
  }

  public void populateView(CollectionContainer concrete)
  {
    if(!concrete.isNew())
    {
      this.setConcreteId(concrete.getId());
    }
    
    this.setTerm(concrete.getChild());
    this.setNumberContainers(concrete.getNumberContainers());
    this.setNumberWithWater(concrete.getNumberWithWater());
    this.setNumberDestroyed(concrete.getNumberDestroyed());
    this.setNumberWithLarvicide(concrete.getNumberWithLarvicide());
    this.setNumberImmatures(concrete.getNumberImmatures());
    this.setNumberLarvae(concrete.getNumberLarvae());
    this.setNumberPupae(concrete.getNumberPupae());
    this.setNumberLarvaeCollected(concrete.getNumberLarvaeCollected());
    this.setNumberPupaeCollected(concrete.getNumberPupaeCollected());
  }

  private void populateConcrete(CollectionContainer concrete)
  {
    concrete.setNumberContainers(this.getNumberContainers());
    concrete.setNumberWithWater(this.getNumberWithWater());
    concrete.setNumberDestroyed(this.getNumberDestroyed());
    concrete.setNumberWithLarvicide(this.getNumberWithLarvicide());
    concrete.setNumberImmatures(this.getNumberImmatures());
    concrete.setNumberLarvae(this.getNumberLarvae());
    concrete.setNumberPupae(this.getNumberPupae());
    concrete.setNumberLarvaeCollected(this.getNumberLarvaeCollected());
    concrete.setNumberPupaeCollected(this.getNumberPupaeCollected());
  }

  private void buildAttributeMap(CollectionContainer concrete)
  {
    new AttributeNotificationMap(concrete, CollectionContainer.ID, this, CollectionContainerView.CONCRETEID);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERCONTAINERS, this, CollectionContainerView.NUMBERCONTAINERS);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERWITHWATER, this, CollectionContainerView.NUMBERWITHWATER);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERDESTROYED, this, CollectionContainerView.NUMBERDESTROYED);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERWITHLARVICIDE, this, CollectionContainerView.NUMBERWITHLARVICIDE);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERIMMATURES, this, CollectionContainerView.NUMBERIMMATURES);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERLARVAE, this, CollectionContainerView.NUMBERLARVAE);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERLARVAECOLLECTED, this, CollectionContainerView.NUMBERLARVAECOLLECTED);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERPUPAE, this, CollectionContainerView.NUMBERPUPAE);
    new AttributeNotificationMap(concrete, CollectionContainer.NUMBERPUPAECOLLECTED, this, CollectionContainerView.NUMBERPUPAECOLLECTED);
  }

  @Override
  @Transaction
  public void apply()
  {
    CollectionContainer concrete = null;

    if (this.hasConcrete())
    {
      concrete = CollectionContainer.lock(this.getConcreteId());
    }
    else
    {
      concrete = new CollectionContainer(this.getTaxon(), this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      CollectionContainer.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

}
