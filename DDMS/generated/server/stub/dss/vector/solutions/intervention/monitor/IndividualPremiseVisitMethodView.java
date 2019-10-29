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

public class IndividualPremiseVisitMethodView extends IndividualPremiseVisitMethodViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1376577075;
  
  public IndividualPremiseVisitMethodView()
  {
    super();
  }
  
  public void populateView(IndividualPremiseVisitMethod concrete)
  {
    if(!concrete.isNew())
    {
      this.setVisit(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setUsed(concrete.getUsed());
  }

  private void populateConcrete(IndividualPremiseVisitMethod concrete)
  {
    concrete.setUsed(this.getUsed());
  }

  private void buildAttributeMap(IndividualPremiseVisitMethod concrete)
  {
    new AttributeNotificationMap(concrete, IndividualPremiseVisitMethod.USED, this, IndividualPremiseVisitMethodView.USED);
  }

  @Override
  @Transaction
  public void apply()
  {
    IndividualPremiseVisitMethod concrete = null;

    IndividualPremiseVisit _visit = this.getVisit();
    
    concrete = _visit.getInterventionMethodsRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new IndividualPremiseVisitMethod(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }
}
