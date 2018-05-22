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

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.AggregatedIPT;
import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.IPTANCVisit;
import dss.vector.solutions.intervention.monitor.IPTDose;
import dss.vector.solutions.intervention.monitor.IPTPatients;
import dss.vector.solutions.intervention.monitor.IPTTreatment;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedIPTExcelView extends AggregatedIPTExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246660812283L;

  private List<Term>        patients;
  private List<Integer>     patientAmounts;
  private List<Term>        visits;
  private List<Integer>     visitAmounts;
  private List<Term>        doses;
  private List<Integer>     doseAmounts;
  private List<Term>        treatments;
  private List<Integer>     treatmentAmounts;
  
  public AggregatedIPTExcelView()
  {
    super();
    patients = new LinkedList<Term>();
    patientAmounts = new LinkedList<Integer>();
    visits = new LinkedList<Term>();
    visitAmounts = new LinkedList<Integer>();
    doses = new LinkedList<Term>();
    doseAmounts = new LinkedList<Integer>();
    treatments = new LinkedList<Term>();
    treatmentAmounts = new LinkedList<Integer>();
  }

  @Override
  @Transaction
  public void apply()
  {
    GeoEntity geoEntity = getGeoEntity();

    PeriodType periodType = ExcelEnums.getPeriodType(this.getPeriodType());
    
    AggregatedIPTView ipt = AggregatedIPT.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getPeriodYear());
    
    if (ipt.hasConcrete())
    {
      ipt = AggregatedIPT.lockView(ipt.getConcreteId());
    }
    
    ipt.setNumberPregnant(this.getNumberPregnant());
    ipt.setNumberNatalCare(this.getNumberNatalCare());
    ipt.setNumberPregnantIron(this.getNumberPregnantIron());
    ipt.setNumberPregnantITN(this.getNumberPregnantITN());
    ipt.setTotalITN(this.getTotalITN());
    
    IPTPatients[] existingPatients = ipt.getIPTPatients();
    IPTPatients[] patientArray = new IPTPatients[patients.size()];
    for (int i = 0; i < patientArray.length; i++)
    {
      // Default to a new relationship
      String termId = patients.get(i).getId();
      patientArray[i] = new IPTPatients(ipt.getConcreteId(), termId);
      
      // If a relationship already exists, use it instead
      for (IPTPatients existing : existingPatients)
      {
        if (existing.getChildId().equals(termId))
        {
          existing.lock();
          patientArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < patientAmounts.size())
      {
        patientArray[i].setAmount((patientAmounts.get(i)));
      }
    }
    
    IPTANCVisit[] existingVisits = ipt.getIPTANCVisits();
    IPTANCVisit[] visitArray = new IPTANCVisit[visits.size()];
    for (int i = 0; i < visitArray.length; i++)
    {
      // Default to a new relationship
      String termId = visits.get(i).getId();
      visitArray[i] = new IPTANCVisit(ipt.getConcreteId(), termId);
      
      // If a relationship already exists, use it instead
      for (IPTANCVisit existing : existingVisits)
      {
        if (existing.getChildId().equals(termId))
        {
          existing.lock();
          visitArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < visitAmounts.size())
      {
        visitArray[i].setAmount((visitAmounts.get(i)));
      }
    }
    
    IPTDose[] existingDoses = ipt.getIPTDoses();
    IPTDose[] doseArray = new IPTDose[doses.size()];
    for (int i = 0; i < doseArray.length; i++)
    {
      // Default to a new relationship
      String termId = doses.get(i).getId();
      doseArray[i] = new IPTDose(ipt.getConcreteId(), termId);
      
      // If a relationship already exists, use it instead
      for (IPTDose existing : existingDoses)
      {
        if (existing.getChildId().equals(termId))
        {
          existing.lock();
          doseArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < doseAmounts.size())
      {
        doseArray[i].setAmount((doseAmounts.get(i)));
      }
    }
    
    IPTTreatment[] existingTreatments = ipt.getIPTTreatments();
    IPTTreatment[] treatmentArray = new IPTTreatment[treatments.size()];
    for (int i = 0; i < treatmentArray.length; i++)
    {
      // Default to a new relationship
      String termId = treatments.get(i).getId();
      treatmentArray[i] = new IPTTreatment(ipt.getConcreteId(), termId);
      
      // If a relationship already exists, use it instead
      for (IPTTreatment existing : existingTreatments)
      {
        if (existing.getChildId().equals(termId))
        {
          existing.lock();
          treatmentArray[i] = existing;
        }
      }
      
      // Set the amount
      if (i < treatmentAmounts.size())
      {
        treatmentArray[i].setAmount((treatmentAmounts.get(i)));
      }
    }
    
    ipt.applyAll(patientArray, visitArray, doseArray, treatmentArray);
  }
  
  public static LinkedList<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(PERIODTYPE);
    list.add(PERIOD);
    list.add(PERIODYEAR);
    list.add(NUMBERPREGNANT);
    list.add(NUMBERNATALCARE);
    list.add(NUMBERPREGNANTIRON);
    list.add(NUMBERPREGNANTITN);
    list.add(TOTALITN);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new AggregatedIPTListener());
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
    exporter.addListener(new AggregatedIPTListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }

  public void addPatient(Term grid, Integer amount)
  {
    patients.add(grid);
    patientAmounts.add(amount);
  }

  public void addVisit(Term grid, Integer amount)
  {
    visits.add(grid);
    visitAmounts.add(amount);
  }

  public void addDose(Term grid, Integer amount)
  {
    doses.add(grid);
    doseAmounts.add(amount);
  }

  public void addTreatment(Term grid, Integer amount)
  {
    treatments.add(grid);
    treatmentAmounts.add(amount);
  }
}
