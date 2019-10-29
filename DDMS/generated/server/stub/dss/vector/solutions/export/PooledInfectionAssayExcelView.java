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
import dss.vector.solutions.entomology.PooledInfectionAssayView;
import dss.vector.solutions.ontology.Term;

public class PooledInfectionAssayExcelView extends PooledInfectionAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2134010;

  public PooledInfectionAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    PooledInfectionAssayView assay = new PooledInfectionAssayView();

    assay.setUniqueAssayId(this.getUniqueAssayId());

    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setPoolId(this.getPoolId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(),
        PooledInfectionAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
        PooledInfectionAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), PooledInfectionAssayView.getSexMd()));
    assay.setParasite(Term.validateByDisplayLabel(this.getParasite(),
        PooledInfectionAssayView.getParasiteMd()));
    assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
        PooledInfectionAssayView.getTestMethodMd()));
    assay.setInfected(this.getInfected());
    assay.setMosquitosTested(this.getMosquitosTested());
    assay.setPoolsTested(this.getPoolsTested());
    assay.setNumberPositive(this.getNumberPositive());

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(POOLID);
    list.add(SPECIES);
    list.add(IDENTMETHOD);
    list.add(SEX);
    list.add(PARASITE);
    list.add(TESTMETHOD);
    list.add(INFECTED);
    list.add(MOSQUITOSTESTED);
    list.add(POOLSTESTED);
    list.add(NUMBERPOSITIVE);
    return list;
  }
}
