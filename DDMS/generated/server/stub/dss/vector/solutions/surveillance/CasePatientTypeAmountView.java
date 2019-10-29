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
package dss.vector.solutions.surveillance;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class CasePatientTypeAmountView extends CasePatientTypeAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1710865329;
  
  public CasePatientTypeAmountView()
  {
    super();
  }
  
  public void populateView(CasePatientTypeAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setPatient(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CasePatientTypeAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CasePatientTypeAmount concrete)
  {
    new AttributeNotificationMap(concrete, CasePatientTypeAmount.AMOUNT, this, CasePatientTypeAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CasePatientTypeAmount concrete = null;

    CasePatientType _visit = this.getPatient();
    
    concrete = _visit.getPatientsRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new CasePatientTypeAmount(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
