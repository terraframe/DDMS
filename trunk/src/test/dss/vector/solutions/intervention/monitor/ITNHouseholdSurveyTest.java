package dss.vector.solutions.intervention.monitor;

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
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public class ITNHouseholdSurveyTest extends TestCase
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

  private static GeoEntity surveyLocation = null;
  
  private static Term      term = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ITNHouseholdSurveyTest.class);

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
    surveyLocation.delete();
    term.delete();
  }

  protected static void classSetUp()
  {
    surveyLocation = TestFixture.createRandomFacility();
    term = TestFixture.createRandomTerm();
  }

  public void testCreateITNHouseholdSurveyByFreeProvider()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurvey concrete = new ITNHouseholdSurvey();
    concrete.setStartDate(new Date());
    concrete.setEndDate(new Date());
    concrete.setSurveyLocation(surveyLocation);
    concrete.setAgentFirstName("Justin");
    concrete.setAgentSurname("Smethie");
    concrete.setQuestionnaireNumber("30");
    concrete.setResidents(34);
    concrete.setPregnantResidents(12);
    concrete.setChildResidents(2);
    concrete.setItns(10);
    concrete.setDamagedItns(2);
    concrete.setHangingItns(3);
    concrete.setOtherItns(4);
    concrete.setMonthReceived(11);
    concrete.setYearReceived(2007);
    concrete.setUsedItns(5);
    concrete.setUsedEveryNight(false);
    concrete.setNetsObtained(true);
    concrete.setFreeProvider(term);
    concrete.setWashed(term);
    concrete.setWashFrequency(34);
    concrete.setWashInterval(term);
    concrete.setRetreated(true);
    concrete.setRetreatmentPeriod(term);
    concrete.apply();

    try
    {
      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayNetsMd()))
      {
        ITNHouseholdSurveyNet rel = concrete.addNets(d);
        rel.setAmount(netAmount);
        rel.apply();
      }

      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayTargetGroupsMd()))
      {
        ITNHouseholdSurveyTargetGroup rel = concrete.addTargetGroups(d);
        rel.setAmount(targetGroupAmount);
        rel.apply();
      }

      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayNonUseReasonsMd()))
      {
        ITNHouseholdSurveyNonUseReason rel = concrete.addNonUseReasons(d);
        rel.setAmount(reasonAmount);
        rel.apply();
      }

      ITNHouseholdSurvey test = ITNHouseholdSurvey.get(concrete.getId());

      assertEquals(concrete.getStartDate(), test.getStartDate());
      assertEquals(concrete.getEndDate(), test.getEndDate());
      assertEquals(concrete.getSurveyLocation().getGeoId(), test.getSurveyLocation().getGeoId());
      assertEquals(concrete.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(concrete.getAgentSurname(), test.getAgentSurname());
      assertEquals(concrete.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(concrete.getResidents(), test.getResidents());
      assertEquals(concrete.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(concrete.getChildResidents(), test.getChildResidents());
      assertEquals(concrete.getItns(), test.getItns());
      assertEquals(concrete.getDamagedItns(), test.getDamagedItns());
      assertEquals(concrete.getHangingItns(), test.getHangingItns());
      assertEquals(concrete.getOtherItns(), test.getOtherItns());
      assertEquals(concrete.getMonthReceived(), test.getMonthReceived());
      assertEquals(concrete.getYearReceived(), test.getYearReceived());
      assertEquals(concrete.getUsedItns(), test.getUsedItns());
      assertEquals(concrete.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(concrete.getNetsObtained(), test.getNetsObtained());
      assertEquals(concrete.getFreeProvider().getId(), test.getFreeProvider().getId());
      assertEquals(null, test.getBoughtProvider());
      assertEquals(concrete.getWashed().getId(), test.getWashed().getId());
      assertEquals(concrete.getWashFrequency(), test.getWashFrequency());
      assertEquals(concrete.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(concrete.getRetreated(), test.getRetreated());
      assertEquals(concrete.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNet s : test.getAllNetsRel())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroup m : test.getAllTargetGroupsRel())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReason m : test.getAllNonUseReasonsRel())
        assertEquals(reasonAmount, m.getAmount());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateITNHouseholdSurveyByBoughtProvider()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurvey concrete = new ITNHouseholdSurvey();
    concrete.setStartDate(new Date());
    concrete.setEndDate(new Date());
    concrete.setSurveyLocation(surveyLocation);
    concrete.setAgentFirstName("Justin");
    concrete.setAgentSurname("Smethie");
    concrete.setQuestionnaireNumber("30");
    concrete.setResidents(34);
    concrete.setPregnantResidents(12);
    concrete.setChildResidents(2);
    concrete.setItns(10);
    concrete.setDamagedItns(2);
    concrete.setHangingItns(3);
    concrete.setOtherItns(4);
    concrete.setMonthReceived(11);
    concrete.setYearReceived(2007);
    concrete.setUsedItns(5);
    concrete.setUsedEveryNight(false);
    concrete.setNetsObtained(false);
    concrete.setBoughtProvider(term);
    concrete.setWashed(term);
    concrete.setWashFrequency(34);
    concrete.setWashInterval(term);
    concrete.setRetreated(true);
    concrete.setRetreatmentPeriod(term);
    concrete.apply();

    try
    {
      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayNetsMd()))
      {
        ITNHouseholdSurveyNet rel = concrete.addNets(d);
        rel.setAmount(netAmount);
        rel.apply();
      }

      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayTargetGroupsMd()))
      {
        ITNHouseholdSurveyTargetGroup rel = concrete.addTargetGroups(d);
        rel.setAmount(targetGroupAmount);
        rel.apply();
      }

      for (Term d : Term.getRootChildren(ITNHouseholdSurveyView.getDisplayNonUseReasonsMd()))
      {
        ITNHouseholdSurveyNonUseReason rel = concrete.addNonUseReasons(d);
        rel.setAmount(reasonAmount);
        rel.apply();
      }

      ITNHouseholdSurvey test = ITNHouseholdSurvey.get(concrete.getId());

      assertEquals(concrete.getStartDate(), test.getStartDate());
      assertEquals(concrete.getEndDate(), test.getEndDate());
      assertEquals(concrete.getSurveyLocation().getGeoId(), test.getSurveyLocation().getGeoId());
      assertEquals(concrete.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(concrete.getAgentSurname(), test.getAgentSurname());
      assertEquals(concrete.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(concrete.getResidents(), test.getResidents());
      assertEquals(concrete.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(concrete.getChildResidents(), test.getChildResidents());
      assertEquals(concrete.getItns(), test.getItns());
      assertEquals(concrete.getDamagedItns(), test.getDamagedItns());
      assertEquals(concrete.getHangingItns(), test.getHangingItns());
      assertEquals(concrete.getOtherItns(), test.getOtherItns());
      assertEquals(concrete.getMonthReceived(), test.getMonthReceived());
      assertEquals(concrete.getYearReceived(), test.getYearReceived());
      assertEquals(concrete.getUsedItns(), test.getUsedItns());
      assertEquals(concrete.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(concrete.getNetsObtained(), test.getNetsObtained());
      assertEquals(null, test.getFreeProvider());
      assertEquals(concrete.getBoughtProvider().getId(), test.getBoughtProvider().getId());
      assertEquals(concrete.getWashed().getId(), test.getWashed().getId());
      assertEquals(concrete.getWashFrequency(), test.getWashFrequency());
      assertEquals(concrete.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(concrete.getRetreated(), test.getRetreated());
      assertEquals(concrete.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNet s : test.getAllNetsRel())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroup m : test.getAllTargetGroupsRel())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReason m : test.getAllNonUseReasonsRel())
        assertEquals(reasonAmount, m.getAmount());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateViewWithFreeNet()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);
    view.apply();

    try
    {
      ITNHouseholdSurveyNet[] patients = view.getITNHouseholdSurveyNets();
      ITNHouseholdSurveyTargetGroup[] visits = view.getITNHouseholdSurveyTargetGroups();
      ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

      for (ITNHouseholdSurveyNet rel : patients)
      {
        rel.setAmount(netAmount);
        rel.apply();
      }

      for (ITNHouseholdSurveyTargetGroup rel : visits)
      {
        rel.setAmount(targetGroupAmount);
        rel.apply();
      }

      for (ITNHouseholdSurveyNonUseReason rel : reasons)
      {
        rel.setAmount(reasonAmount);
        rel.apply();
      }

      ITNHouseholdSurveyView test = ITNHouseholdSurvey.getView(view.getConcreteId());

      assertEquals(view.getStartDate(), test.getStartDate());
      assertEquals(view.getEndDate(), test.getEndDate());
      assertEquals(view.getSurveyLocation(), test.getSurveyLocation());
      assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(view.getAgentSurname(), test.getAgentSurname());
      assertEquals(view.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(view.getResidents(), test.getResidents());
      assertEquals(view.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(view.getChildResidents(), test.getChildResidents());
      assertEquals(view.getItns(), test.getItns());
      assertEquals(view.getDamagedItns(), test.getDamagedItns());
      assertEquals(view.getHangingItns(), test.getHangingItns());
      assertEquals(view.getOtherItns(), test.getOtherItns());
      assertEquals(view.getMonthReceived(), test.getMonthReceived());
      assertEquals(view.getYearReceived(), test.getYearReceived());
      assertEquals(view.getUsedItns(), test.getUsedItns());
      assertEquals(view.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(view.getNetsObtained(), test.getNetsObtained());
      assertEquals(view.getFreeProvider().getId(), test.getFreeProvider().getId());
      assertEquals(null, test.getBoughtProvider());
      assertEquals(view.getWashed().getId(), test.getWashed().getId());
      assertEquals(view.getWashFrequency(), test.getWashFrequency());
      assertEquals(view.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(view.getRetreated(), test.getRetreated());
      assertEquals(view.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNet s : test.getITNHouseholdSurveyNets())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroup m : test.getITNHouseholdSurveyTargetGroups())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReason m : test.getITNHouseholdSurveyNonUseReason())
        assertEquals(reasonAmount, m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testApplyAll()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    view.applyAll(nets, targetGroups, reasons);

    try
    {
      ITNHouseholdSurveyView test = ITNHouseholdSurvey.getView(view.getConcreteId());

      assertEquals(view.getStartDate(), test.getStartDate());
      assertEquals(view.getEndDate(), test.getEndDate());
      assertEquals(view.getSurveyLocation(), test.getSurveyLocation());
      assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(view.getAgentSurname(), test.getAgentSurname());
      assertEquals(view.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(view.getResidents(), test.getResidents());
      assertEquals(view.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(view.getChildResidents(), test.getChildResidents());
      assertEquals(view.getItns(), test.getItns());
      assertEquals(view.getDamagedItns(), test.getDamagedItns());
      assertEquals(view.getHangingItns(), test.getHangingItns());
      assertEquals(view.getOtherItns(), test.getOtherItns());
      assertEquals(view.getMonthReceived(), test.getMonthReceived());
      assertEquals(view.getYearReceived(), test.getYearReceived());
      assertEquals(view.getUsedItns(), test.getUsedItns());
      assertEquals(view.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(view.getNetsObtained(), test.getNetsObtained());
      assertEquals(view.getFreeProvider().getId(), test.getFreeProvider().getId());
      assertEquals(null, test.getBoughtProvider());
      assertEquals(view.getWashed().getId(), test.getWashed().getId());
      assertEquals(view.getWashFrequency(), test.getWashFrequency());
      assertEquals(view.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(view.getRetreated(), test.getRetreated());
      assertEquals(view.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNet s : test.getITNHouseholdSurveyNets())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroup m : test.getITNHouseholdSurveyTargetGroups())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReason m : test.getITNHouseholdSurveyNonUseReason())
        assertEquals(reasonAmount, m.getAmount());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testEditView()
  {
    Integer different = new Integer(99);
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(different);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(different);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(different);
    }

    view.applyAll(nets, targetGroups, reasons);

    try
    {
      ITNHouseholdSurveyView edit = ITNHouseholdSurvey.lockView(view.getConcreteId());
      edit.setStartDate(new Date());
      edit.setEndDate(new Date());
      edit.setSurveyLocation(surveyLocation.getGeoId());
      edit.setAgentFirstName("Justin");
      edit.setAgentSurname("Smethie");
      edit.setQuestionnaireNumber("30");
      edit.setResidents(34);
      edit.setPregnantResidents(12);
      edit.setChildResidents(2);
      edit.setItns(10);
      edit.setDamagedItns(2);
      edit.setHangingItns(3);
      edit.setOtherItns(4);
      edit.setMonthReceived(11);
      edit.setYearReceived(2007);
      edit.setUsedItns(5);
      edit.setUsedEveryNight(false);
      edit.setNetsObtained(true);
      edit.setFreeProvider(term);
      edit.setWashed(term);
      edit.setWashFrequency(34);
      edit.setWashInterval(term);
      edit.setRetreated(true);
      edit.setRetreatmentPeriod(term);

      ITNHouseholdSurveyNet[] lockedNets = edit.getITNHouseholdSurveyNets();
      ITNHouseholdSurveyTargetGroup[] lockedGroups = edit.getITNHouseholdSurveyTargetGroups();
      ITNHouseholdSurveyNonUseReason[] lockedReasons = edit.getITNHouseholdSurveyNonUseReason();

      for (ITNHouseholdSurveyNet rel : lockedNets)
      {
        rel.setAmount(netAmount);
      }

      for (ITNHouseholdSurveyTargetGroup rel : lockedGroups)
      {
        rel.setAmount(targetGroupAmount);
      }

      for (ITNHouseholdSurveyNonUseReason rel : lockedReasons)
      {
        rel.setAmount(reasonAmount);
      }

      edit.applyAll(lockedNets, lockedGroups, lockedReasons);

      ITNHouseholdSurveyView test = ITNHouseholdSurvey.getView(view.getConcreteId());

      assertEquals(edit.getStartDate(), test.getStartDate());
      assertEquals(edit.getEndDate(), test.getEndDate());
      assertEquals(edit.getSurveyLocation(), test.getSurveyLocation());
      assertEquals(edit.getAgentFirstName(), test.getAgentFirstName());
      assertEquals(edit.getAgentSurname(), test.getAgentSurname());
      assertEquals(edit.getQuestionnaireNumber(), test.getQuestionnaireNumber());
      assertEquals(edit.getResidents(), test.getResidents());
      assertEquals(edit.getPregnantResidents(), test.getPregnantResidents());
      assertEquals(edit.getChildResidents(), test.getChildResidents());
      assertEquals(edit.getItns(), test.getItns());
      assertEquals(edit.getDamagedItns(), test.getDamagedItns());
      assertEquals(edit.getHangingItns(), test.getHangingItns());
      assertEquals(edit.getOtherItns(), test.getOtherItns());
      assertEquals(edit.getMonthReceived(), test.getMonthReceived());
      assertEquals(edit.getYearReceived(), test.getYearReceived());
      assertEquals(edit.getUsedItns(), test.getUsedItns());
      assertEquals(edit.getUsedEveryNight(), test.getUsedEveryNight());
      assertEquals(edit.getNetsObtained(), test.getNetsObtained());
      assertEquals(edit.getFreeProvider().getId(), test.getFreeProvider().getId());
      assertEquals(null, test.getBoughtProvider());
      assertEquals(edit.getWashed().getId(), test.getWashed().getId());
      assertEquals(edit.getWashFrequency(), test.getWashFrequency());
      assertEquals(edit.getWashInterval().getId(), test.getWashInterval().getId());
      assertEquals(edit.getRetreated(), test.getRetreated());
      assertEquals(edit.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

      for (ITNHouseholdSurveyNet s : test.getITNHouseholdSurveyNets())
        assertEquals(netAmount, s.getAmount());

      for (ITNHouseholdSurveyTargetGroup m : test.getITNHouseholdSurveyTargetGroups())
        assertEquals(targetGroupAmount, m.getAmount());

      for (ITNHouseholdSurveyNonUseReason m : test.getITNHouseholdSurveyNonUseReason())
        assertEquals(reasonAmount, m.getAmount());
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

    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(calendar.getTime());
    view.setEndDate(calendar.getTime());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a future date");
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

  public void testNotApplicableFreeProvider()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(false);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a free net when the net provider is 'commerical'");
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

  public void testNotApplicableBoughtProvider()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setBoughtProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a free net when the net provider is 'commercial'");
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

  public void testNotApplicableWashFrequencyWithDontKnow()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a net wash frequency when nets washed is 'dont know'");
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

  public void testNotApplicableWashFrequencyWithNotWashed()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a net wash frequency when nets washed is 'no'");
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

  public void testNotApplicableWashIntervalWithDontKnow()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a net wash interval when nets washed is 'dont know'");
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

  public void testNotApplicableWashIntervalWithNotWashed()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a net wash interval when nets washed is 'no'");
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

  public void testNotApplicableRetreatmentPeriod()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setRetreated(false);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a retreatment period when nets were not retreated");
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

  public void testNotApplicableNonUseReasonAmount()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(true);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setRetreated(false);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with non use reasons when nets are used every night");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(reasons.length, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof NotApplicableProblem);
      }
    }
  }

  public void testMonthRangeProblem()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(14);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(false);
    view.setBoughtProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey an invalid month value");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof RangeValueProblem);
      }
    }
  }

  public void testYearRangeProblem()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(Calendar.getInstance().get(Calendar.YEAR) + 20);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(false);
    view.setBoughtProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    try
    {
      view.applyAll(nets, targetGroups, reasons);

      view.deleteConcrete();

      fail("Able to create ITN household survey with a free net when the net provider is 'commercial'");
    }
    catch (ProblemException e)
    {
      // This is expected: Ensure that the correct problem was thrown
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF p : problems)
      {
        assertTrue(p instanceof RangeValueProblem);
      }
    }
  }

  
  public void testQueryView()
  {
    Integer netAmount = new Integer(50);
    Integer targetGroupAmount = new Integer(10);
    Integer reasonAmount = new Integer(20);

    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.setStartDate(new Date());
    view.setEndDate(new Date());
    view.setSurveyLocation(surveyLocation.getGeoId());
    view.setAgentFirstName("Justin");
    view.setAgentSurname("Smethie");
    view.setQuestionnaireNumber("30");
    view.setResidents(34);
    view.setPregnantResidents(12);
    view.setChildResidents(2);
    view.setItns(10);
    view.setDamagedItns(2);
    view.setHangingItns(3);
    view.setOtherItns(4);
    view.setMonthReceived(11);
    view.setYearReceived(2007);
    view.setUsedItns(5);
    view.setUsedEveryNight(false);
    view.setNetsObtained(true);
    view.setFreeProvider(term);
    view.setWashed(term);
    view.setWashFrequency(34);
    view.setWashInterval(term);
    view.setRetreated(true);
    view.setRetreatmentPeriod(term);

    ITNHouseholdSurveyNet[] nets = view.getITNHouseholdSurveyNets();
    ITNHouseholdSurveyTargetGroup[] targetGroups = view.getITNHouseholdSurveyTargetGroups();
    ITNHouseholdSurveyNonUseReason[] reasons = view.getITNHouseholdSurveyNonUseReason();

    for (ITNHouseholdSurveyNet rel : nets)
    {
      rel.setAmount(netAmount);
    }

    for (ITNHouseholdSurveyTargetGroup rel : targetGroups)
    {
      rel.setAmount(targetGroupAmount);
    }

    for (ITNHouseholdSurveyNonUseReason rel : reasons)
    {
      rel.setAmount(reasonAmount);
    }

    view.applyAll(nets, targetGroups, reasons);

    try
    {
      ITNHouseholdSurveyViewQuery query = ITNHouseholdSurveyView.getPage(null, true, 0, 20);

      List<? extends ITNHouseholdSurveyView> list = query.getIterator().getAll();

      assertEquals(1, list.size());

      for (ITNHouseholdSurveyView test : list)
      {
        assertEquals(view.getStartDate(), test.getStartDate());
        assertEquals(view.getEndDate(), test.getEndDate());
        assertEquals(view.getSurveyLocation(), test.getSurveyLocation());
        assertEquals(view.getAgentFirstName(), test.getAgentFirstName());
        assertEquals(view.getAgentSurname(), test.getAgentSurname());
        assertEquals(view.getQuestionnaireNumber(), test.getQuestionnaireNumber());
        assertEquals(view.getResidents(), test.getResidents());
        assertEquals(view.getPregnantResidents(), test.getPregnantResidents());
        assertEquals(view.getChildResidents(), test.getChildResidents());
        assertEquals(view.getItns(), test.getItns());
        assertEquals(view.getDamagedItns(), test.getDamagedItns());
        assertEquals(view.getHangingItns(), test.getHangingItns());
        assertEquals(view.getOtherItns(), test.getOtherItns());
        assertEquals(view.getMonthReceived(), test.getMonthReceived());
        assertEquals(view.getYearReceived(), test.getYearReceived());
        assertEquals(view.getUsedItns(), test.getUsedItns());
        assertEquals(view.getUsedEveryNight(), test.getUsedEveryNight());
        assertEquals(view.getNetsObtained(), test.getNetsObtained());
        assertEquals(view.getFreeProvider().getId(), test.getFreeProvider().getId());
        assertEquals(null, test.getBoughtProvider());
        assertEquals(view.getWashed().getId(), test.getWashed().getId());
        assertEquals(view.getWashFrequency(), test.getWashFrequency());
        assertEquals(view.getWashInterval().getId(), test.getWashInterval().getId());
        assertEquals(view.getRetreated(), test.getRetreated());
        assertEquals(view.getRetreatmentPeriod().getId(), test.getRetreatmentPeriod().getId());

        for (ITNHouseholdSurveyNet s : test.getITNHouseholdSurveyNets())
          assertEquals(netAmount, s.getAmount());

        for (ITNHouseholdSurveyTargetGroup m : test.getITNHouseholdSurveyTargetGroups())
          assertEquals(targetGroupAmount, m.getAmount());

        for (ITNHouseholdSurveyNonUseReason m : test.getITNHouseholdSurveyNonUseReason())
          assertEquals(reasonAmount, m.getAmount());
      }
    }
    finally
    {
      view.deleteConcrete();
    }

  }

}
