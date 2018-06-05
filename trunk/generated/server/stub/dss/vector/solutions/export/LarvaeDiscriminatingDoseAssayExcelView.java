/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.ontology.Term;

public class LarvaeDiscriminatingDoseAssayExcelView extends LarvaeDiscriminatingDoseAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245996428742L;

  public LarvaeDiscriminatingDoseAssayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    LarvaeDiscriminatingDoseAssay ldda = UniqueAssayUtil.getOrCreateAssay(LarvaeDiscriminatingDoseAssay.class, this.getUniqueAssayId());
    if (!ldda.isNew())
    {
      ldda.appLock();
    }

    ldda.setUniqueAssayId(this.getUniqueAssayId());
    ldda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    ldda.setTestDate(this.getTestDate());
    ldda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), LarvaeDiscriminatingDoseAssay.getSpecieMd()));
    ldda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), LarvaeDiscriminatingDoseAssay.getIdentificationMethodMd()));
    ldda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), LarvaeDiscriminatingDoseAssay.getTestMethodMd()));
    ldda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), LarvaeDiscriminatingDoseAssay.getGenerationMd()));
    ldda.setIsofemale(this.getIsofemale());
    ldda.setStartPoint(Term.validateByDisplayLabel(this.getStartPoint(), LarvaeDiscriminatingDoseAssay.getStartPointMd()));
    ldda.setEndPoint(Term.validateByDisplayLabel(this.getEndPoint(), LarvaeDiscriminatingDoseAssay.getEndPointMd()));

    // set the Insecticide if at least one value is set (three values are
    // required, but
    // we want validation to notify the user that values are missing).
    if (this.isModified(INSECTICIDEACTIVEINGREDIENT) || this.isModified(INSECTICIDEUNITS) || this.isModified(INSECTICIDEAMOUNT))
    {
      ldda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(), this.getInsecticideUnits(), this.getInsecticideAmount()));
    }

    ldda.setExposureTime(this.getExposureTime());
    ldda.setHoldingTime(this.getHoldingTime());
    ldda.setQuantityTested(this.getQuantityTested());
    ldda.setQuantityDead(this.getQuantityDead());
    ldda.setControlTestMortality(this.getControlTestMortality());
    ldda.setLt50(this.getLt50());
    ldda.setLt95(this.getLt95());

    ldda.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(UNIQUEASSAYID);
    list.add(TESTDATE);
    list.add(SPECIE);
    list.add(IDENTIFICATIONMETHOD);
    list.add(TESTMETHOD);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(STARTPOINT);
    list.add(ENDPOINT);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(EXPOSURETIME);
    list.add(HOLDINGTIME);
    list.add(QUANTITYTESTED);
    list.add(QUANTITYDEAD);
    list.add(CONTROLTESTMORTALITY);
    list.add(LT50);
    list.add(LT95);
    return list;
  }
}
