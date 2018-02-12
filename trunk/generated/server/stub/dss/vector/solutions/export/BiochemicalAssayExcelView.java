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

import dss.vector.solutions.entomology.BiochemicalAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class BiochemicalAssayExcelView extends BiochemicalAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1997921529;

  public BiochemicalAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    BiochemicalAssayView assay = new BiochemicalAssayView();

    assay.setUniqueAssayId(this.getUniqueAssayId());
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(),
        BiochemicalAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
        BiochemicalAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), BiochemicalAssayView.getSexMd()));
    assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
        BiochemicalAssayView.getGenerationMd()));
    assay.setIsofemale(this.getIsofemale());
    assay.setAssay(Term.validateByDisplayLabel(this.getAssay(), BiochemicalAssayView.getAssayMd()));
    assay.setNumberTested(this.getNumberTested());
    assay.setNumberElevated(this.getNumberElevated());

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(MOSQUITOID);
    list.add(SPECIES);
    list.add(IDENTMETHOD);
    list.add(SEX);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(ASSAY);
    list.add(NUMBERTESTED);
    list.add(NUMBERELEVATED);
    return list;
  }
}
