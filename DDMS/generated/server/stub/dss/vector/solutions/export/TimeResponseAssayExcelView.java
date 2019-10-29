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
package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.ontology.Term;

public class TimeResponseAssayExcelView extends TimeResponseAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1853916247;

  public TimeResponseAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    TimeResponseAssayView assay = new TimeResponseAssayView();

    // NOTE: We are now searching by assay id
//    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
//    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
//        DiagnosticAssayView.getActiveIngredientMd());
//    Term specie = Term.validateByDisplayLabel(this.getSpecies(), DiagnosticAssayView.getSpeciesMd());
//    Term stage = Term.validateByDisplayLabel(this.getLifeStage(), DiagnosticAssayView.getLifeStageMd());
//    
    // // Search for an existing record
    // TimeResponseAssayQuery query = new TimeResponseAssayQuery(new
    // QueryFactory());
    // query.WHERE(query.getCollection().EQ(collection));
    // query.WHERE(query.getAssay().EQ(assayTerm));
    // query.WHERE(query.getActiveIngredient().EQ(ingredient));
    // query.WHERE(query.getSpecies().EQ(specie));
    // query.WHERE(query.getLifeStage().EQ(stage));
    // OIterator<? extends TimeResponseAssay> iterator = query.getIterator();
    // try
    // {
    // if (iterator.hasNext())
    // {
    // TimeResponseAssay next = iterator.next();
    // next.lock();
    // assay.populateView(next);
    // }
    // else
    // {
    // assay.setCollection(collection);
    // assay.setAssay(assayTerm);
    // assay.setActiveIngredient(ingredient);
    // assay.setSpecies(specie);
    // assay.setLifeStage(stage);
    // }
    // }
    // finally
    // {
    // iterator.close();
    // }

    assay.setUniqueAssayId(this.getUniqueAssayId());
    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
    assay.setCollection(collection);
    Term assayTerm = Term.validateByDisplayLabel(this.getAssay(), TimeResponseAssayView.getAssayMd());
    assay.setAssay(assayTerm);
    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
        TimeResponseAssayView.getActiveIngredientMd());
    assay.setActiveIngredient(ingredient);
    Term specie = Term.validateByDisplayLabel(this.getSpecies(), TimeResponseAssayView.getSpeciesMd());
    assay.setSpecies(specie);
    Term stage = Term.validateByDisplayLabel(this.getLifeStage(),
        TimeResponseAssayView.getLifeStageMd());
    assay.setLifeStage(stage);
    assay.setSynergist(this.getSynergist());
    assay.setTestStrainResult(this.getTestStrainResult());
    assay.setReferenceStrainResult(this.getReferenceStrainResult());

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(ASSAY);
    list.add(ACTIVEINGREDIENT);
    list.add(SPECIES);
    list.add(LIFESTAGE);
    list.add(SYNERGIST);
    list.add(TESTSTRAINRESULT);
    list.add(REFERENCESTRAINRESULT);
    return list;
  }

}
