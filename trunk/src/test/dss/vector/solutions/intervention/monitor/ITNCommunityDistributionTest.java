package dss.vector.solutions.intervention.monitor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;

public class ITNCommunityDistributionTest extends TestCase
{
  private static final String     BATCH_NUMBER = "adfa";

  private static final BigDecimal CURRENCY     = new BigDecimal(18.51);

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

  private static GeoEntity distributionLocation = null;

  private static GeoEntity address              = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ITNCommunityDistributionTest.class);

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
    distributionLocation.delete();
    address.delete();
  }

  protected static void classSetUp()
  {
    distributionLocation = new HealthFacility();
    distributionLocation.setGeoId(TestConstants.GEO_ID);
    distributionLocation.setEntityName("Facility");
    distributionLocation.apply();

    address = new HealthFacility();
    address.setGeoId(TestConstants.GEO_ID_2);
    address.setEntityName("Facility");
    address.apply();
  }

  public void testCreateITNCommunityDistributionByHousehold()
  {
    ITNCommunityDistribution concrete = new ITNCommunityDistribution();
    concrete.setStartDate(new Date());
    concrete.setEndDate(new Date());
    concrete.setAgentFirstName("Justin");
    concrete.setAgentSurname("Smethie");
    concrete.setBatchNumber(BATCH_NUMBER);
    concrete.setEntryType(true);
    concrete.setHouseholdAddress(address);
    concrete.setHouseholdName("Justin");
    concrete.setHouseholdSurname("Smethie");
    concrete.setResidents(50);
    concrete.setSold(true);
    concrete.setCurrencyReceived(CURRENCY);
    concrete.setRetrieved(true);
    concrete.setNumberRetrieved(30);
    concrete.setPretreated(true);
    concrete.apply();

    try
    {
      for (Term d : Term.getRootChildren(ITNCommunityDistributionView.getDisplayNetsMd()))
      {
        ITNCommunityNet rel = concrete.addNets(d);
        rel.setAmount(new Integer(50));
        rel.apply();
      }

      for (Term d : Term.getRootChildren(ITNCommunityDistributionView.getDisplayTargetGroupsMd()))
      {
        ITNCommunityTargetGroup rel = concrete.addTargetGroups(d);
        rel.setAmount(new Integer(10));
        rel.apply();
      }

      ITNCommunityDistribution test = ITNCommunityDistribution.get(concrete.getId());

      assertEquals(concrete.getStartDate(), test.getStartDate());
      assertEquals(concrete.getEndDate(), test.getEndDate());
      assertEquals(concrete.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(concrete.getAgentSurname(), test.getAgentSurname());
      assertEquals(concrete.getBatchNumber(), test.getBatchNumber());
      assertEquals(concrete.getEntryType(), test.getEntryType());
      assertEquals(concrete.getHouseholdName(), test.getHouseholdName());
      assertEquals(concrete.getHouseholdSurname(), test.getHouseholdSurname());
      assertEquals(concrete.getHouseholdAddress().getId(), test.getHouseholdAddress().getId());
      assertEquals(concrete.getResidents(), test.getResidents());
      assertNull(test.getDistributionLocation());
      assertEquals(concrete.getSold(), test.getSold());
      assertEquals(concrete.getCurrencyReceived(), test.getCurrencyReceived());
      assertEquals(concrete.getRetrieved(), test.getRetrieved());
      assertEquals(concrete.getNumberRetrieved(), test.getNumberRetrieved());
      assertEquals(concrete.getPretreated(), test.getPretreated());

      for (ITNCommunityNet s : test.getAllNetsRel())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNCommunityTargetGroup m : test.getAllTargetGroupsRel())
        assertEquals(new Integer(10), m.getAmount());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateViewByHousehold()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);
    view.apply();

    try
    {
      ITNCommunityNet[] patients = view.getITNCommunityNets();
      ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

      for (ITNCommunityNet rel : patients)
      {
        rel.setAmount(new Integer(50));
        rel.apply();
      }

      for (ITNCommunityTargetGroup rel : visits)
      {
        rel.setAmount(new Integer(10));
        rel.apply();
      }

      ITNCommunityDistributionView test = ITNCommunityDistribution.getView(view.getConcreteId());

      assertEquals(view.getStartDate(), test.getStartDate());
      assertEquals(view.getEndDate(), test.getEndDate());
      assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(view.getAgentSurname(), test.getAgentSurname());
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getEntryType(), test.getEntryType());
      assertEquals(view.getHouseholdName(), test.getHouseholdName());
      assertEquals(view.getHouseholdSurname(), test.getHouseholdSurname());
      assertEquals(view.getHouseholdAddress(), test.getHouseholdAddress());
      assertEquals(view.getDistributionLocation(), test.getDistributionLocation());
      assertEquals(view.getResidents(), test.getResidents());
      assertEquals(view.getSold(), test.getSold());
      assertEquals(view.getCurrencyReceived(), test.getCurrencyReceived());
      assertEquals(view.getRetrieved(), test.getRetrieved());
      assertEquals(view.getNumberRetrieved(), test.getNumberRetrieved());
      assertEquals(view.getPretreated(), test.getPretreated());

      for (ITNCommunityNet s : test.getITNCommunityNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNCommunityTargetGroup m : test.getITNCommunityTargetGroups())
        assertEquals(new Integer(10), m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testCreateViewByDistributionLocation()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(false);
    view.setDistributionLocation(distributionLocation.getGeoId());
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);
    view.apply();

    try
    {
      ITNCommunityNet[] patients = view.getITNCommunityNets();
      ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

      for (ITNCommunityNet rel : patients)
      {
        rel.setAmount(new Integer(50));
        rel.apply();
      }

      for (ITNCommunityTargetGroup rel : visits)
      {
        rel.setAmount(new Integer(10));
        rel.apply();
      }

      ITNCommunityDistributionView test = ITNCommunityDistribution.getView(view.getConcreteId());

      assertEquals(view.getStartDate(), test.getStartDate());
      assertEquals(view.getEndDate(), test.getEndDate());
      assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(view.getAgentSurname(), test.getAgentSurname());
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getEntryType(), test.getEntryType());
      assertEquals(view.getHouseholdName(), test.getHouseholdName());
      assertEquals(view.getHouseholdSurname(), test.getHouseholdSurname());
      assertEquals(view.getHouseholdAddress(), test.getHouseholdAddress());
      assertEquals(view.getDistributionLocation(), test.getDistributionLocation());
      assertEquals(view.getResidents(), test.getResidents());
      assertEquals(view.getSold(), test.getSold());
      assertEquals(view.getCurrencyReceived(), test.getCurrencyReceived());
      assertEquals(view.getRetrieved(), test.getRetrieved());
      assertEquals(view.getNumberRetrieved(), test.getNumberRetrieved());
      assertEquals(view.getPretreated(), test.getPretreated());

      for (ITNCommunityNet s : test.getITNCommunityNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNCommunityTargetGroup m : test.getITNCommunityTargetGroups())
        assertEquals(new Integer(10), m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testEditView()
  {
    Integer value = new Integer(20);

    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    view.applyAll(patients, visits);

    try
    {
      ITNCommunityDistributionView edit = ITNCommunityDistribution.lockView(view.getConcreteId());
      edit.setStartDate(new Date());
      edit.setEndDate(new Date());
      edit.setAgentFirstName("Justin");
      edit.setAgentSurname("Smethie");
      edit.setBatchNumber(BATCH_NUMBER);
      edit.setEntryType(false);
      edit.setDistributionLocation(distributionLocation.getGeoId());
      edit.setHouseholdAddress(null);
      edit.setHouseholdName(null);
      edit.setHouseholdSurname(null);
      edit.setResidents(null);
      edit.setSold(true);
      edit.setCurrencyReceived(CURRENCY);
      edit.setRetrieved(true);
      view.setNumberRetrieved(30);
      edit.setPretreated(true);

      patients = edit.getITNCommunityNets();
      visits = edit.getITNCommunityTargetGroups();

      for (ITNCommunityNet rel : patients)
      {
        rel.setAmount(value);
      }

      for (ITNCommunityTargetGroup rel : visits)
      {
        rel.setAmount(value);
      }

      edit.applyAll(patients, visits);

      ITNCommunityDistributionView test = ITNCommunityDistribution.getView(view.getConcreteId());

      assertEquals(edit.getStartDate(), test.getStartDate());
      assertEquals(edit.getEndDate(), test.getEndDate());
      assertEquals(edit.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(edit.getAgentSurname(), test.getAgentSurname());
      assertEquals(edit.getBatchNumber(), test.getBatchNumber());
      assertEquals(edit.getEntryType(), test.getEntryType());
      assertEquals(edit.getHouseholdName(), test.getHouseholdName());
      assertEquals(edit.getHouseholdSurname(), test.getHouseholdSurname());
      assertEquals(edit.getHouseholdAddress(), test.getHouseholdAddress());
      assertEquals(edit.getDistributionLocation(), test.getDistributionLocation());
      assertEquals(edit.getResidents(), test.getResidents());
      assertEquals(edit.getSold(), test.getSold());
      assertEquals(edit.getCurrencyReceived(), test.getCurrencyReceived());
      assertEquals(edit.getRetrieved(), test.getRetrieved());
      assertEquals(edit.getNumberRetrieved(), test.getNumberRetrieved());
      assertEquals(edit.getPretreated(), test.getPretreated());

      for (ITNCommunityNet s : test.getITNCommunityNets())
        assertEquals(value, s.getAmount());

      for (ITNCommunityTargetGroup m : test.getITNCommunityTargetGroups())
        assertEquals(value, m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    view.applyAll(patients, visits);

    try
    {
      ITNCommunityDistributionView test = ITNCommunityDistribution.getView(view.getConcreteId());

      assertEquals(view.getStartDate(), test.getStartDate());
      assertEquals(view.getEndDate(), test.getEndDate());
      assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(view.getAgentSurname(), test.getAgentSurname());
      assertEquals(view.getBatchNumber(), test.getBatchNumber());
      assertEquals(view.getEntryType(), test.getEntryType());
      assertEquals(view.getHouseholdName(), test.getHouseholdName());
      assertEquals(view.getHouseholdSurname(), test.getHouseholdSurname());
      assertEquals(view.getHouseholdAddress(), test.getHouseholdAddress());
      assertEquals(view.getDistributionLocation(), test.getDistributionLocation());
      assertEquals(view.getResidents(), test.getResidents());
      assertEquals(view.getSold(), test.getSold());
      assertEquals(view.getCurrencyReceived(), test.getCurrencyReceived());
      assertEquals(view.getRetrieved(), test.getRetrieved());
      assertEquals(view.getNumberRetrieved(), test.getNumberRetrieved());
      assertEquals(view.getPretreated(), test.getPretreated());

      for (ITNCommunityNet s : test.getITNCommunityNets())
        assertEquals(new Integer(50), s.getAmount());

      for (ITNCommunityTargetGroup m : test.getITNCommunityTargetGroups())
        assertEquals(new Integer(10), m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testFutureDate()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.YEAR, 1);

    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(calendar.getTime());
    view.setEndDate(calendar.getTime());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able to create ITN community data with a future date");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof CurrentDateProblem);
      }
    }
  }

  public void testNotApplicableDistributionLocationEntry()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setDistributionLocation(distributionLocation.getGeoId());
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able set distribution location on a entry by household");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof NotApplicableProblem);
      }
    }

  }

  public void testNotApplicableHouseholdEntry()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(false);
    view.setDistributionLocation(distributionLocation.getGeoId());
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able set household fields on a Distribution Location");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(4, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof NotApplicableProblem);
      }
    }

  }

  public void testNotApplicableCurrencyRecieved()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(false);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able to set currency received when no nets are sold");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof CurrencyAmountProblem);
      }
    }

  }

  public void testNotApplicableNetsRetrieved()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(false);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able to set the number of nets retrieved when no nets where calculated");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof NotApplicableProblem);
      }
    }

  }

  public void testRequiredHouseholdAddress()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able to create ITN community data with a future date");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof RequiredAttributeProblem);
      }
    }

  }

  public void testRequiredDistributionLocation()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(false);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    try
    {
      view.applyAll(patients, visits);

      view.deleteConcrete();

      fail("Able to create ITN community data with a future date");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof RequiredAttributeProblem);
      }
    }
  }

  public void testQueryView()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setBatchNumber(BATCH_NUMBER);
    view.setEntryType(true);
    view.setHouseholdAddress(address.getGeoId());
    view.setHouseholdName("Justin");
    view.setHouseholdSurname("Smethie");
    view.setResidents(50);
    view.setSold(true);
    view.setCurrencyReceived(CURRENCY);
    view.setRetrieved(true);
    view.setNumberRetrieved(30);
    view.setPretreated(true);

    ITNCommunityNet[] patients = view.getITNCommunityNets();
    ITNCommunityTargetGroup[] visits = view.getITNCommunityTargetGroups();

    for (ITNCommunityNet rel : patients)
    {
      rel.setAmount(new Integer(50));
    }

    for (ITNCommunityTargetGroup rel : visits)
    {
      rel.setAmount(new Integer(10));
    }

    view.applyAll(patients, visits);

    try
    {
      ITNCommunityDistributionViewQuery query = ITNCommunityDistributionView.getPage(null, true, 0, 20);

      List<? extends ITNCommunityDistributionView> list = query.getIterator().getAll();

      assertEquals(1, list.size());

      for (ITNCommunityDistributionView test : list)
      {
        assertEquals(view.getStartDate(), test.getStartDate());
        assertEquals(view.getEndDate(), test.getEndDate());
        assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
        assertEquals(view.getAgentSurname(), test.getAgentSurname());
        assertEquals(view.getBatchNumber(), test.getBatchNumber());
        assertEquals(view.getEntryType(), test.getEntryType());
        assertEquals(view.getHouseholdName(), test.getHouseholdName());
        assertEquals(view.getHouseholdSurname(), test.getHouseholdSurname());
        // TODO: The query mapping code needs to be fixed such that empty geo
        // entity attributes does not result in the row not appearing
        
        // assertEquals(view.getHouseholdAddress(), test.getHouseholdAddress());
        // assertEquals(view.getDistributionLocation(), test.getDistributionLocation());
        assertEquals(view.getResidents(), test.getResidents());
        assertEquals(view.getSold(), test.getSold());
        assertEquals(view.getCurrencyReceived(), test.getCurrencyReceived());
        assertEquals(view.getRetrieved(), test.getRetrieved());
        assertEquals(view.getNumberRetrieved(), test.getNumberRetrieved());
        assertEquals(view.getPretreated(), test.getPretreated());
      }
    }
    finally
    {
      view.deleteConcrete();
    }

  }

}
