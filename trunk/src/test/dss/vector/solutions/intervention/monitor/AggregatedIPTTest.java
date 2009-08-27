package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.surveillance.TreatmentGrid;

public class AggregatedIPTTest extends TestCase
{
  @Override
  public TestResult run()
  {
    return super.run();
  }

  @Override
  public void run(TestResult testResult)
  {
    super.run(testResult);
  }

  private static GeoEntity geoEntity = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(AggregatedIPTTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }

      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classTearDown()
  {
    geoEntity.delete();
  }

  protected static void classSetUp()
  {
    geoEntity = new HealthFacility();
    geoEntity.setGeoId("9");
    geoEntity.setEntityName("Facility");
    geoEntity.apply();
  }

  public void testCreateAggregatedIPT()
  {
    Integer care = new Integer(50);
    Integer iron = new Integer(23);
    Integer itn = new Integer(50);
    Integer pregnant = new Integer(2);
    Integer totalItn = new Integer(50);

    AggregatedIPT concrete = new AggregatedIPT();
    concrete.setGeoEntity(geoEntity);
    concrete.setStartDate(new Date());
    concrete.setEndDate(new Date());
    concrete.setNumberNatalCare(care);
    concrete.setNumberPregnant(pregnant);
    concrete.setNumberPregnantIron(iron);
    concrete.setNumberPregnantITN(itn);
    concrete.setTotalITN(totalItn);
    concrete.apply();

    for (PatientGrid d : PatientGrid.getAll())
    {
      IPTPatients rel = concrete.addPatients(d);
      rel.setAmount(new Integer(50));
      rel.apply();
    }

    for (VisitGrid d : VisitGrid.getAll())
    {
      IPTANCVisit rel = concrete.addANCVisits(d);
      rel.setAmount(new Integer(10));
      rel.apply();
    }

    for (DoseGrid d : DoseGrid.getAll())
    {
      IPTDose rel = concrete.addDoses(d);
      rel.setAmount(new Integer(25));
      rel.apply();
    }

    for (TreatmentGrid d : TreatmentGrid.getAll())
    {
      IPTTreatment rel = concrete.addTreatments(d);
      rel.setAmount(new Integer(15));
      rel.apply();
    }


    try
    {
      AggregatedIPT test = AggregatedIPT.get(concrete.getId());

      assertEquals(concrete.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(concrete.getStartDate(), test.getStartDate());
      assertEquals(concrete.getEndDate(), test.getEndDate());
      assertEquals(concrete.getNumberNatalCare(), test.getNumberNatalCare());
      assertEquals(concrete.getNumberPregnant(), test.getNumberPregnant());
      assertEquals(concrete.getNumberPregnantIron(), test.getNumberPregnantIron());
      assertEquals(concrete.getNumberPregnantITN(), test.getNumberPregnantITN());
      assertEquals(concrete.getTotalITN(), test.getTotalITN());

      for (IPTPatients s : test.getAllPatientsRel())
        assertEquals(new Integer(50), s.getAmount());

      for (IPTANCVisit m : test.getAllANCVisitsRel())
        assertEquals(new Integer(10), m.getAmount());

      for (IPTDose t : test.getAllDosesRel())
        assertEquals(new Integer(25), t.getAmount());

      for (IPTTreatment r : test.getAllTreatmentsRel())
        assertEquals(new Integer(15), r.getAmount());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateView()
  {
    Integer care = new Integer(50);
    Integer iron = new Integer(23);
    Integer itn = new Integer(50);
    Integer pregnant = new Integer(2);
    Integer totalItn = new Integer(50);

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setNumberNatalCare(care);
    view.setNumberPregnant(pregnant);
    view.setNumberPregnantIron(iron);
    view.setNumberPregnantITN(itn);
    view.setTotalITN(totalItn);
    view.apply();

    IPTPatients[] patients = view.getIPTPatients();
    IPTANCVisit[] visits = view.getIPTANCVisits();
    IPTDose[] doses = view.getIPTDoses();
    IPTTreatment[] treatments = view.getIPTTreatments();

    for (IPTPatients rel : patients)
    {
      rel.setAmount(new Integer(50));
      rel.apply();
    }

    for (IPTANCVisit rel : visits)
    {
      rel.setAmount(new Integer(10));
      rel.apply();
    }

    for (IPTDose rel : doses)
    {
      rel.setAmount(new Integer(25));
      rel.apply();
    }

    for (IPTTreatment rel : treatments)
    {
      rel.setAmount(new Integer(15));
      rel.apply();
    }


    try
    {
      AggregatedIPTView test = AggregatedIPT.getView(view.getConcreteId());

      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getNumberNatalCare(), test.getNumberNatalCare());
      assertEquals(view.getNumberPregnant(), test.getNumberPregnant());
      assertEquals(view.getNumberPregnantIron(), test.getNumberPregnantIron());
      assertEquals(view.getNumberPregnantITN(), test.getNumberPregnantITN());
      assertEquals(view.getTotalITN(), test.getTotalITN());

      for (IPTPatients s : test.getIPTPatients())
        assertEquals(new Integer(50), s.getAmount());

      for (IPTANCVisit m : test.getIPTANCVisits())
        assertEquals(new Integer(10), m.getAmount());

      for (IPTDose t : test.getIPTDoses())
        assertEquals(new Integer(25), t.getAmount());

      for (IPTTreatment r : test.getIPTTreatments())
        assertEquals(new Integer(15), r.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    Integer care = new Integer(50);
    Integer iron = new Integer(23);
    Integer itn = new Integer(50);
    Integer pregnant = new Integer(2);
    Integer totalItn = new Integer(50);

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setNumberNatalCare(care);
    view.setNumberPregnant(pregnant);
    view.setNumberPregnantIron(iron);
    view.setNumberPregnantITN(itn);
    view.setTotalITN(totalItn);

    IPTPatients[] patients = view.getIPTPatients();
    IPTANCVisit[] visits = view.getIPTANCVisits();
    IPTDose[] doses = view.getIPTDoses();
    IPTTreatment[] treatments = view.getIPTTreatments();

    for (IPTPatients rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (IPTANCVisit rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (IPTDose rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    for (IPTTreatment rel : treatments)
    {
      rel.setAmount(new Integer(15));
    }

    view.applyAll(patients, visits, doses, treatments);

    try
    {
      AggregatedIPTView test = AggregatedIPT.getView(view.getConcreteId());

      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getNumberNatalCare(), test.getNumberNatalCare());
      assertEquals(view.getNumberPregnant(), test.getNumberPregnant());
      assertEquals(view.getNumberPregnantIron(), test.getNumberPregnantIron());
      assertEquals(view.getNumberPregnantITN(), test.getNumberPregnantITN());
      assertEquals(view.getTotalITN(), test.getTotalITN());

      for (IPTPatients s : test.getIPTPatients())
        assertEquals(new Integer(50), s.getAmount());

      for (IPTANCVisit m : test.getIPTANCVisits())
        assertEquals(new Integer(10), m.getAmount());

      for (IPTDose t : test.getIPTDoses())
        assertEquals(new Integer(25), t.getAmount());

      for (IPTTreatment r : test.getIPTTreatments())
        assertEquals(new Integer(15), r.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testSearchAggregatedCase()
  {
    Integer care = new Integer(50);
    Integer iron = new Integer(23);
    Integer itn = new Integer(50);
    Integer pregnant = new Integer(2);
    Integer totalItn = new Integer(50);

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setNumberNatalCare(care);
    view.setNumberPregnant(pregnant);
    view.setNumberPregnantIron(iron);
    view.setNumberPregnantITN(itn);
    view.setTotalITN(totalItn);

    IPTPatients[] patients = view.getIPTPatients();
    IPTANCVisit[] visits = view.getIPTANCVisits();
    IPTDose[] doses = view.getIPTDoses();
    IPTTreatment[] treatments = view.getIPTTreatments();

    for (IPTPatients rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (IPTANCVisit rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (IPTDose rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    for (IPTTreatment rel : treatments)
    {
      rel.setAmount(new Integer(15));
    }

    view.applyAll(patients, visits, doses, treatments);

    try
    {
      AggregatedIPTView test = AggregatedIPT.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.MONTH, 3, 2009);

      assertEquals(view.getConcreteId(), test.getConcreteId());
      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getNumberNatalCare(), test.getNumberNatalCare());
      assertEquals(view.getNumberPregnant(), test.getNumberPregnant());
      assertEquals(view.getNumberPregnantIron(), test.getNumberPregnantIron());
      assertEquals(view.getNumberPregnantITN(), test.getNumberPregnantITN());
      assertEquals(view.getTotalITN(), test.getTotalITN());

      for (IPTPatients s : test.getIPTPatients())
        assertEquals(new Integer(50), s.getAmount());

      for (IPTANCVisit m : test.getIPTANCVisits())
        assertEquals(new Integer(10), m.getAmount());

      for (IPTDose t : test.getIPTDoses())
        assertEquals(new Integer(25), t.getAmount());

      for (IPTTreatment r : test.getIPTTreatments())
        assertEquals(new Integer(15), r.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testUnknownCase()
  {
    AggregatedIPTView view = AggregatedIPT.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.QUARTER, 1, 2009);

    assertEquals("", view.getConcreteId());
  }
  
  public void testFutureDate()
  {
    Calendar calendar = Calendar.getInstance();    
    calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR) + 1;
    
    
    Integer care = new Integer(50);
    Integer iron = new Integer(23);
    Integer itn = new Integer(50);
    Integer pregnant = new Integer(2);
    Integer totalItn = new Integer(50);

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(year);
    view.setNumberNatalCare(care);
    view.setNumberPregnant(pregnant);
    view.setNumberPregnantIron(iron);
    view.setNumberPregnantITN(itn);
    view.setTotalITN(totalItn);

    IPTPatients[] patients = view.getIPTPatients();
    IPTANCVisit[] visits = view.getIPTANCVisits();
    IPTDose[] doses = view.getIPTDoses();
    IPTTreatment[] treatments = view.getIPTTreatments();

    for (IPTPatients rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (IPTANCVisit rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (IPTDose rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    for (IPTTreatment rel : treatments)
    {
      rel.setAmount(new Integer(15));
    }

    try
    {
      view.applyAll(patients, visits, doses, treatments);
      
      fail("Able to create an IPT with a future date");
    }
    catch(ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      
      for(ProblemIF p : e.getProblems())
      {
        assertTrue(p instanceof CurrentDateProblem);
      }
    }
  }

}
