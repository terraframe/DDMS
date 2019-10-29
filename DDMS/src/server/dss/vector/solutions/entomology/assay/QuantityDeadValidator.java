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

import com.runwaysdk.generation.loader.Reloadable;

public class QuantityDeadValidator implements Reloadable
{
  private Integer quantityDead;

  private Integer quantityTested;

  private AbstractAssay assay;

  public QuantityDeadValidator(AdultDiscriminatingDoseAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(LarvaeDiscriminatingDoseAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(EfficacyAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(Integer quantityDead, Integer quantityTested, AbstractAssay assay)
  {
    this.quantityDead = quantityDead;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {
    if(quantityDead == null || quantityTested == null)return;

    if (quantityDead > quantityTested)
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
      p.setQuantityDead(quantityDead);
      p.setQuantityTested(quantityTested);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.QUANTITYDEAD);
      p.apply();
      p.throwIt();
    }
  }
}
