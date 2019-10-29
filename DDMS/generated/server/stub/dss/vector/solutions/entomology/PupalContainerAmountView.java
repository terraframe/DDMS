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

import dss.vector.solutions.ontology.Term;

public class PupalContainerAmountView extends PupalContainerAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1412152919;
  
  public PupalContainerAmountView()
  {
    super();
  }

  public void populateView(PupalContainerAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setContainer(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(PupalContainerAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(PupalContainerAmount concrete)
  {
    new AttributeNotificationMap(concrete, PupalContainerAmount.AMOUNT, this, PupalContainerAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    PupalContainerAmount concrete = null;

    PupalContainer _container = this.getContainer();
    Term _term = this.getTerm();
    
    concrete = _container.getPupalRel(_term);
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new PupalContainerAmount(_container, _term);
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }
}
