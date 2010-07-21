package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

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
    ipt.setNumberPregnant(this.getNumberPregnant());
    ipt.setNumberNatalCare(this.getNumberNatalCare());
    ipt.setNumberPregnantIron(this.getNumberPregnantIron());
    ipt.setNumberPregnantITN(this.getNumberPregnantITN());
    ipt.setTotalITN(this.getTotalITN());

    IPTPatients[] patientArray = new IPTPatients[patients.size()];
    for (int i = 0; i < patientArray.length; i++)
    {
      if (i < patientAmounts.size())
      {
        patientArray[i] = new IPTPatients(ipt.getConcreteId(), patients.get(i).getId());
        patientArray[i].setAmount((patientAmounts.get(i)));
      }
    }
    
    IPTANCVisit[] visitArray = new IPTANCVisit[visits.size()];
    for (int i = 0; i < visitArray.length; i++)
    {
      if (i < visitAmounts.size())
      {
        visitArray[i] = new IPTANCVisit(ipt.getConcreteId(), visits.get(i).getId());
        visitArray[i].setAmount((visitAmounts.get(i)));
      }
    }
    
    IPTDose[] doseArray = new IPTDose[doses.size()];
    for (int i = 0; i < doseArray.length; i++)
    {
      if (i < doseAmounts.size())
      {
        doseArray[i] = new IPTDose(ipt.getConcreteId(), doses.get(i).getId());
        doseArray[i].setAmount((doseAmounts.get(i)));
      }
    }
    
    IPTTreatment[] treatmentArray = new IPTTreatment[treatments.size()];
    for (int i = 0; i < treatmentArray.length; i++)
    {
      if (i < treatmentAmounts.size())
      {
        treatmentArray[i] = new IPTTreatment(ipt.getConcreteId(), treatments.get(i).getId());
        treatmentArray[i].setAmount((treatmentAmounts.get(i)));
      }
    }
    
    ipt.applyAll(patientArray, visitArray, doseArray, treatmentArray);
  }
  
  public static List<String> customAttributeOrder()
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

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new AggregatedIPTListener());
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new AggregatedIPTListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
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
