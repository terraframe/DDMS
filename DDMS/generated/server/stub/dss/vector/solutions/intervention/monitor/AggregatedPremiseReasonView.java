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

public class AggregatedPremiseReasonView extends AggregatedPremiseReasonViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -819119072;

  public AggregatedPremiseReasonView()
  {
    super();
  }

  public void populateView(AggregatedPremiseReasonBase concrete)
  {
    if (!concrete.isNew())
    {
      this.setVisit(concrete.getParent());
    }

    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(AggregatedPremiseReasonBase concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(AggregatedPremiseReasonBase concrete)
  {
    new AttributeNotificationMap(concrete, AggregatedPremiseReason.AMOUNT, this, AggregatedPremiseReasonView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    AggregatedPremiseReasonBase concrete = null;

    AggregatedPremiseVisit _visit = this.getVisit();

    concrete = _visit.getNonTreatmentReasonsRel(this.getTerm());

    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new AggregatedPremiseReason(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
