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
package dss.vector.solutions.entomology.assay;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;

public class AdultDiscriminatingDoseIntervalView extends AdultDiscriminatingDoseIntervalViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1984642489;

  public AdultDiscriminatingDoseIntervalView()
  {
    super();
  }

  public static AdultDiscriminatingDoseIntervalView build(AdultDiscriminatingDoseAssay assay, int time)
  {
    AdultDiscriminatingDoseIntervalView interval = new AdultDiscriminatingDoseIntervalView();
    interval.setIntervalTime(time);

    if (!assay.isNew())
    {
      interval.setAssay(assay);
    }

    return interval;
  }

  @Override
  public void apply()
  {
    AdultDiscriminatingDoseInterval concrete = this.getConcrete();

    // Build the attribute map between the concrete object and the view for
    // error handling.
    this.buildAttributeMap(concrete);

    this.populationConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateView(AdultDiscriminatingDoseInterval concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setAssay(concrete.getAssay());
    this.setIntervalTime(concrete.getIntervalTime());
    this.setAmount(concrete.getAmount());
  }

  private void populationConcrete(AdultDiscriminatingDoseInterval concrete)
  {
    concrete.setAssay(this.getAssay());
    concrete.setIntervalTime(this.getIntervalTime());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(AdultDiscriminatingDoseInterval concrete)
  {
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.ID, this, AdultDiscriminatingDoseIntervalView.CONCRETEID);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.ASSAY, this, AdultDiscriminatingDoseIntervalView.ASSAY);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.INTERVALTIME, this, AdultDiscriminatingDoseIntervalView.INTERVALTIME);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.AMOUNT, this, AdultDiscriminatingDoseIntervalView.AMOUNT);
  }

  public AdultDiscriminatingDoseInterval getConcrete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      return AdultDiscriminatingDoseInterval.lock(this.getConcreteId());
    }
    else
    {
      return new AdultDiscriminatingDoseInterval();
    }
  }
}
