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

public class CaseDiagnosisTypeAmountView extends CaseDiagnosisTypeAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1002346837;
  
  public CaseDiagnosisTypeAmountView()
  {
    super();
  }
  
  public void populateView(CaseDiagnosisTypeAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setDiagnosis(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseDiagnosisTypeAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseDiagnosisTypeAmount concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiagnosisTypeAmount.AMOUNT, this, CaseDiagnosisTypeAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiagnosisTypeAmount concrete = null;

    CaseDiagnosisType _visit = this.getDiagnosis();
    
    concrete = _visit.getCategoriesRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new CaseDiagnosisTypeAmount(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
