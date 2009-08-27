package dss.vector.solutions.intervention.monitor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.surveillance.PeriodType;

public class ITNTest extends TestCase
{
  private static final String     BATCH_NUMBER       = "adfa";

  private static final Integer    TARGET_GROUPS      = 13;

  private static final Integer    COMMUNITY_RESPONSE = 15;

  private static final Integer    DISTRIBUTED        = 16;

  private static final Integer    SOLD               = 16;

  private static final BigDecimal CURRENCY           = new BigDecimal(18);

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
    suite.addTestSuite(ITNTest.class);

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

  public void testCreateITNData()
  {
    ITNData concrete = new ITNData();
    concrete.setGeoEntity(geoEntity);
    concrete.setStartDate(new Date());
    concrete.setEndDate(new Date());
    concrete.setBatchNumber(BATCH_NUMBER);
    concrete.setReceivedForTargetGroups(TARGET_GROUPS);
    concrete.setReceivedForCommunityResponse(COMMUNITY_RESPONSE);
    concrete.setNumberDistributed(DISTRIBUTED);
    concrete.setNumberSold(SOLD);
    concrete.setCurrencyReceived(CURRENCY);
    concrete.apply();

    for (Net d : Net.getAll())
    {
      ITNNet rel = concrete.addNets(d);
      rel.setAmount(new Integer(50));
      rel.apply();
    }

    for (TargetGroupGrid d : TargetGroupGrid.getAll())
    {
      ITNTargetGroup rel = concrete.addTargetGroups(d);
      rel.setAmount(new Integer(10));
      rel.apply();
    }

    for (ServiceGrid d : ServiceGrid.getAll())
    {
      ITNService rel = concrete.addServices(d);
      rel.setAmount(new Integer(25));
      rel.apply();
    }

    try
    {
      ITNData test = ITNData.get(concrete.getId());

      assertEquals(concrete.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(concrete.getStartDate(), test.getStartDate());
      assertEquals(concrete.getEndDate(), test.getEndDate());
      assertEquals(concrete.getBatchNumber(), test.getBatchNumber());
      assertEquals(concrete.getReceivedForTargetGroups(), test.getReceivedForTargetGroups());
      assertEquals(concrete.getReceivedForCommunityResponse(), test.getReceivedForCommunityResponse());
      assertEquals(concrete.getNumberDistributed(), test.getNumberDistributed());
      assertEquals(concrete.getNumberSold(), test.getNumberSold());

      for (ITNNet s : test.getAllNetsRel())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNTargetGroup m : test.getAllTargetGroupsRel())
        assertEquals(new Integer(10), m.getAmount());

      for (ITNService t : test.getAllServicesRel())
        assertEquals(new Integer(25), t.getAmount());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateView()
  {
    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setBatchNumber(BATCH_NUMBER);
    view.setReceivedForTargetGroups(TARGET_GROUPS);
    view.setReceivedForCommunityResponse(COMMUNITY_RESPONSE);
    view.setNumberDistributed(DISTRIBUTED);
    view.setNumberSold(SOLD);
    view.apply();

    ITNNet[] patients = view.getITNNets();
    ITNTargetGroup[] visits = view.getITNTargetGroups();
    ITNService[] doses = view.getITNServices();

    for (ITNNet rel : patients)
    {
      rel.setAmount(new Integer(50));
      rel.apply();
    }

    for (ITNTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
      rel.apply();
    }

    for (ITNService rel : doses)
    {
      rel.setAmount(new Integer(25));
      rel.apply();
    }

    try
    {
      ITNDataView test = ITNData.getView(view.getConcreteId());

      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getReceivedForTargetGroups(), test.getReceivedForTargetGroups());
      assertEquals(view.getReceivedForCommunityResponse(), test.getReceivedForCommunityResponse());
      assertEquals(view.getNumberDistributed(), test.getNumberDistributed());
      assertEquals(view.getNumberSold(), test.getNumberSold());

      for (ITNNet s : test.getITNNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNTargetGroup m : test.getITNTargetGroups())
        assertEquals(new Integer(10), m.getAmount());

      for (ITNService t : test.getITNServices())
        assertEquals(new Integer(25), t.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setBatchNumber(BATCH_NUMBER);
    view.setReceivedForTargetGroups(TARGET_GROUPS);
    view.setReceivedForCommunityResponse(COMMUNITY_RESPONSE);
    view.setNumberDistributed(DISTRIBUTED);
    view.setNumberSold(SOLD);

    ITNNet[] patients = view.getITNNets();
    ITNTargetGroup[] visits = view.getITNTargetGroups();
    ITNService[] doses = view.getITNServices();

    for (ITNNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (ITNService rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    view.applyAll(patients, visits, doses);

    try
    {
      ITNDataView test = ITNData.getView(view.getConcreteId());

      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getReceivedForTargetGroups(), test.getReceivedForTargetGroups());
      assertEquals(view.getReceivedForCommunityResponse(), test.getReceivedForCommunityResponse());
      assertEquals(view.getNumberDistributed(), test.getNumberDistributed());
      assertEquals(view.getNumberSold(), test.getNumberSold());

      for (ITNNet s : test.getITNNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNTargetGroup m : test.getITNTargetGroups())
        assertEquals(new Integer(10), m.getAmount());

      for (ITNService t : test.getITNServices())
        assertEquals(new Integer(25), t.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testSearchAggregatedCase()
  {
    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(2009);
    view.setBatchNumber(BATCH_NUMBER);
    view.setReceivedForTargetGroups(TARGET_GROUPS);
    view.setReceivedForCommunityResponse(COMMUNITY_RESPONSE);
    view.setNumberDistributed(DISTRIBUTED);
    view.setNumberSold(SOLD);

    ITNNet[] patients = view.getITNNets();
    ITNTargetGroup[] visits = view.getITNTargetGroups();
    ITNService[] doses = view.getITNServices();

    for (ITNNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (ITNService rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    view.applyAll(patients, visits, doses);

    try
    {
      ITNDataView test = ITNData.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.MONTH, 3, 2009);

      assertEquals(view.getConcreteId(), test.getConcreteId());
      assertEquals(view.getGeoId(), test.getGeoId());
      assertEquals(view.getPeriod(), test.getPeriod());
      assertEquals(view.getPeriodYear(), test.getPeriodYear());
      assertEquals(1, test.getPeriodType().size());
      assertEquals(view.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(view.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getReceivedForTargetGroups(), test.getReceivedForTargetGroups());
      assertEquals(view.getReceivedForCommunityResponse(), test.getReceivedForCommunityResponse());
      assertEquals(view.getNumberDistributed(), test.getNumberDistributed());
      assertEquals(view.getNumberSold(), test.getNumberSold());

      for (ITNNet s : test.getITNNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNTargetGroup m : test.getITNTargetGroups())
        assertEquals(new Integer(10), m.getAmount());

      for (ITNService t : test.getITNServices())
        assertEquals(new Integer(25), t.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testUnknownCase()
  {
    ITNDataView view = ITNData.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.QUARTER, 1, 2009);

    assertEquals("", view.getConcreteId());
  }

  public void testFutureDate()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR) + 1;

    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.addPeriodType(PeriodType.MONTH);
    view.setPeriod(3);
    view.setPeriodYear(year);
    view.setBatchNumber(BATCH_NUMBER);
    view.setReceivedForTargetGroups(TARGET_GROUPS);
    view.setReceivedForCommunityResponse(COMMUNITY_RESPONSE);
    view.setNumberDistributed(DISTRIBUTED);
    view.setNumberSold(SOLD);

    ITNNet[] patients = view.getITNNets();
    ITNTargetGroup[] visits = view.getITNTargetGroups();
    ITNService[] doses = view.getITNServices();

    for (ITNNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    for (ITNService rel : doses)
    {
      rel.setAmount(new Integer(25));
    }

    try
    {
      view.applyAll(patients, visits, doses);

      fail("Able to create an IPT with a future date");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown

      for (ProblemIF p : e.getProblems())
      {
        assertTrue(p instanceof CurrentDateProblem);
      }
    }
  }
}
