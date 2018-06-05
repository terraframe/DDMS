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

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.ontology.Term;

public class DiagnosticAssayExcelView extends DiagnosticAssayExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = -307738699;

  public DiagnosticAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    DiagnosticAssayView assay = new DiagnosticAssayView();

    assay.setUniqueAssayId(this.getUniqueAssayId());

    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
    assay.setCollection(collection);

    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(), DiagnosticAssayView.getActiveIngredientMd());
    assay.setActiveIngredient(ingredient);

    Term specie = Term.validateByDisplayLabel(this.getSpecies(), DiagnosticAssayView.getSpeciesMd());
    assay.setSpecies(specie);
    Term stage = Term.validateByDisplayLabel(this.getLifeStage(), DiagnosticAssayView.getLifeStageMd());
    assay.setLifeStage(stage);
    assay.setSynergist(this.getSynergist());
    assay.setOutcome(Term.validateByDisplayLabel(this.getOutcome(), DiagnosticAssayView.getOutcomeMd()));

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(ACTIVEINGREDIENT);
    list.add(SPECIES);
    list.add(LIFESTAGE);
    list.add(SYNERGIST);
    list.add(OUTCOME);
    return list;
  }

}
