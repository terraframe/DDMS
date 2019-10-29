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

import dss.vector.solutions.ontology.Term;

public class GravidValidator implements Reloadable
{
  private Term sex;

  private Integer gravid;

  private Integer quantityTested;

  private AbstractAssay assay;

  public GravidValidator(AdultAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay);
  }

  public GravidValidator(EfficacyAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay);
  }

  public GravidValidator(Term sex, Integer gravid, Integer quantityTested, AbstractAssay assay)
  {
    this.sex = sex;
    this.gravid = gravid;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {

    //null is allowed
    if(gravid == null)return;

    String maleId = "";
    String unknownId = "";

    if (gravid != 0 && sex != null && ( sex.getTermId().equals(maleId) || sex.getTermId().equals(unknownId) ))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.GRAVID);
      p.apply();
      p.throwIt();
    }

    if(quantityTested == null)return;

    if (gravid > quantityTested)
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

      InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
      p.setGravid(gravid);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.GRAVID);
      p.apply();
      p.throwIt();
    }
  }
}
