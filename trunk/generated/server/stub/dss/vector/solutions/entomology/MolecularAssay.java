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

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.MolecularSumProblem;
import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Disease;

public class MolecularAssay extends MolecularAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1142002498;

  public MolecularAssay()
  {
    super();
  }
    
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else if(this.getMosquitoId() != null && !this.getMosquitoId().equals(""))
    {
      return this.getMosquitoId();
    }
    else if(this.getSpecies() != null)
    {
      return this.getSpecies().getTermDisplayLabel().getValue();
    }
    
    return super.toString();
  }

  @Override
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);
    
    validateMosquitoId();
    validateSum();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }

  @Override
  public void validateMosquitoId()
  {
    Integer sum = this.getSum();

    if (sum > 1 && ( this.hasMosquitoId() ))
    {
      this.setMosquitoId(null);
    }
    if (sum == 1 && ( !this.hasMosquitoId() ))
    {
      this.setMosquitoId(LocalProperty.getNextId());
    }
  }

  private Integer getSum()
  {
    int sum = 0;

    sum += ( this.getNumberRR() != null ? this.getNumberRR() : 0 );
    sum += ( this.getNumberRS() != null ? this.getNumberRS() : 0 );
    sum += ( this.getNumberSS() != null ? this.getNumberSS() : 0 );
    sum += ( this.getNumberRRp() != null ? this.getNumberRRp() : 0 );
    sum += ( this.getNumberRpRp() != null ? this.getNumberRpRp() : 0 );
    sum += ( this.getNumberSRp() != null ? this.getNumberSRp() : 0 );

    return sum;
  }

  public void validateSum()
  {
    Integer sum = this.getSum();

    if (!(sum > 0))
    {
      String msg = "The sum of RR, RS, SS, RR', R'R', and SR' must be GT 0";
      MolecularSumProblem p = new MolecularSumProblem(msg);
      p.apply();

      p.throwIt();
    }
  }

  private boolean hasMosquitoId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  @Override
  @Transaction
  public MolecularAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MolecularAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MolecularAssayView getView()
  {
    MolecularAssayView view = new MolecularAssayView();

    view.populateView(this);

    return view;
  }
  
}
