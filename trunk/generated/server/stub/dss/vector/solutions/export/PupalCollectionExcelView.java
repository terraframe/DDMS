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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.PupalContainerAmountView;
import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PupalCollectionExcelView extends PupalCollectionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1912421822;

  private List<Term>        pupae;

  private List<Integer>     pupaeAmounts;
  
  public PupalCollectionExcelView()
  {
    super();
    pupae = new LinkedList<Term>();
    pupaeAmounts = new LinkedList<Integer>();
  }
  
  @Override
  public void apply()
  {
    PupalCollectionView collection = getCollection();
    
    PupalContainerView container = new PupalContainerView();
    
    String cid = this.getContainerId();
    if (cid.length()==0)
    {
      ContainerIdRequiredProblem problem = new ContainerIdRequiredProblem();
      problem.throwIt();
    }
    else
    {
      container.setContainerId(cid);
    }
    
    for (PupalContainerView existing : collection.getContainers())
    {
      if (existing.getContainerId().equals(cid))
      {
        container = existing;
      }
    }
    
    container.setContainerType(Term.validateByDisplayLabel(this.getContainerType(), PupalContainerView.getContainerTypeMd()));
    container.addShape(ExcelEnums.getContainerShape(this.getShape()));
    container.setHeight(this.getHeight());
    container.setWidth(this.getWidth());
    container.setContainerLength(this.getContainerLength());
    container.setOpeningWidth(this.getOpeningWidth());
    container.setOpeningLength(this.getOpeningLength());
    container.setDiameter(this.getDiameter());
    container.setOpeningDiameter(this.getDiameter());
    container.setShading(Term.validateByDisplayLabel(this.getShading(), PupalContainerView.getShadingMd()));
    container.setLid(Term.validateByDisplayLabel(this.getLid(), PupalContainerView.getLidMd()));
    container.setRoof(Term.validateByDisplayLabel(this.getRoof(), PupalContainerView.getRoofMd()));
    container.setFillMethod(Term.validateByDisplayLabel(this.getFillMethod(), PupalContainerView.getFillMethodMd()));
    container.setFillFrequency(Term.validateByDisplayLabel(this.getFillFrequency(), PupalContainerView.getFillFrequencyMd()));
    container.setDrawdownFrequency(Term.validateByDisplayLabel(this.getDrawdownFrequency(), PupalContainerView.getDrawdownFrequencyMd()));
    container.setDrawdownPercent(this.getDrawdownPercent());

    PupalContainerAmountView[] existingAmounts = container.getAmounts();
    PupalContainerAmountView[][] pupaeArray = new PupalContainerAmountView[1][pupae.size()];
    for (int i = 0; i < pupaeAmounts.size(); i++)
    {
      // Default to a new record
      pupaeArray[0][i] = new PupalContainerAmountView();
      Term term = pupae.get(i);
      pupaeArray[0][i].setTerm(term);
      
      // If a record already exists, use it instead
      for (PupalContainerAmountView existing : existingAmounts)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(PupalContainerAmountView.TERM).equals(term.getId()))
        {
          pupaeArray[0][i] = existing;
        }
      }
      
      // Set the amount
      pupaeArray[0][i].setAmount(pupaeAmounts.get(i));
    }
    
    collection.applyWithContainers(new PupalContainerView[] {container}, pupaeArray);
  }

  private PupalCollectionView getCollection()
  {
    if(this.getStartDate().after(new Date()))
      System.out.println();
    String cid = this.getCollectionId();
    PupalCollectionView collection = new PupalCollectionView();
    collection.setGeoEntity(this.getGeoEntity());
    collection.setStartDate(this.getStartDate());
    collection.setEndDate(this.getEndDate());
    collection.setCollectionId(cid);
    collection.setPremiseType(Term.validateByDisplayLabel(this.getPremiseType(), PupalCollectionView.getPremiseTypeMd()));
    
    collection = PupalCollectionView.getCollection(collection);
    collection.setNotes(this.getNotes());
    collection.setNumberExamined(this.getNumberExamined());
    collection.setPremiseSize(this.getPremiseSize());
    collection.setNumberInhabitants(this.getNumberInhabitants());
    
    return collection;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(COLLECTIONID);
    list.add(PREMISETYPE);
    list.add(NOTES);
    list.add(NUMBEREXAMINED);
    list.add(PREMISESIZE);
    list.add(NUMBERINHABITANTS);
    list.add(CONTAINERID);
    list.add(CONTAINERTYPE);
    list.add(SHAPE);
    list.add(HEIGHT);
    list.add(WIDTH);
    list.add(CONTAINERLENGTH);
    list.add(OPENINGWIDTH);
    list.add(OPENINGLENGTH);
    list.add(DIAMETER);
    list.add(OPENINGDIAMETER);
    list.add(SHADING);
    list.add(LID);
    list.add(ROOF);
    list.add(FILLMETHOD);
    list.add(FILLFREQUENCY);
    list.add(DRAWDOWNFREQUENCY);
    list.add(DRAWDOWNPERCENT);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new PupalCollectionListener());
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new PupalCollectionListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }

  public void addPupaeAmount(Term term, Integer amount)
  {
    pupae.add(term);
    pupaeAmounts.add(amount);
  }
  
  
}
