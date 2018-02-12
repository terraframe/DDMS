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

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

public class LarvacideInstanceView extends LarvacideInstanceViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372023603L;
  
  public LarvacideInstanceView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    LarvacideInstance concrete = new LarvacideInstance();

    if (this.hasConcrete())
    {
      concrete = LarvacideInstance.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);
    this.populateConcrete(concrete);
    concrete.apply();
    
    OIterator<? extends Larvacide> iterator = concrete.getAllControlLarvacide();
    if (!iterator.hasNext())
    {
      // If there's no relationship, we need to create one
      concrete.addControlLarvacide(Larvacide.get(this.getControlId())).apply();
    }
    iterator.close();

    this.populateView(concrete);
  }

  private void buildAttributeMap(LarvacideInstance concrete)
  {
    new AttributeNotificationMap(concrete, LarvacideInstance.TARGET, this, LarvacideInstanceView.TARGET);
    new AttributeNotificationMap(concrete, LarvacideInstance.TREATED, this, LarvacideInstanceView.TREATED);
    new AttributeNotificationMap(concrete, LarvacideInstance.CONTROLMETHOD, this, LarvacideInstanceView.CONTROLMETHOD);
    new AttributeNotificationMap(concrete, LarvacideInstance.UNIT, this, LarvacideInstanceView.UNIT);
    new AttributeNotificationMap(concrete, LarvacideInstance.UNITSUSED, this, LarvacideInstanceView.UNITSUSED);
    new AttributeNotificationMap(concrete, LarvacideInstance.SUBSTANCE, this, LarvacideInstanceView.SUBSTANCE);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public void populateView(LarvacideInstance concrete)
  {
    this.setConcreteId(concrete.getId());
    
    OIterator<? extends Larvacide> iterator = concrete.getAllControlLarvacide();
    if (iterator.hasNext())
    {
      this.setControlId(iterator.next().getId());
    }
    iterator.close();
    
    this.setTarget(concrete.getTarget());
    this.setTreated(concrete.getTreated());
    this.setControlMethod(concrete.getControlMethod());
    this.setUnit(concrete.getUnit());
    this.setUnitsUsed(concrete.getUnitsUsed());
    this.setSubstance(concrete.getSubstance());
  }
  
  private void populateConcrete(LarvacideInstance concrete)
  {
    concrete.setTarget(this.getTarget());
    concrete.setTreated(this.getTreated());
    concrete.setControlMethod(this.getControlMethod());
    concrete.setUnit(this.getUnit());
    concrete.setUnitsUsed(this.getUnitsUsed());
    concrete.setSubstance(this.getSubstance());
  }
  
  @Override
  public void deleteConcrete()
  {
    if (hasConcrete())
    {
      LarvacideInstance.get(this.getConcreteId()).delete();
    }
  }
  
  @Transaction
  public static LarvacideInstanceView[] applyAll(LarvacideInstanceView[] views)
  {
    for (LarvacideInstanceView view : views)
    {
      view.apply();
    }
    return views;
  }
}
