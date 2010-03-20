package dss.vector.solutions.surveillance;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.ClientSession;
import com.runwaysdk.ProblemException;
import com.runwaysdk.ProblemIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermDTO;

public class AggregatedCaseTest extends TestCase
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
  
  private static Term                 term         = null;
  
  private static Term[]               terms = null;
  
  private static TermDTO[]             dtos = null;

  private static GeoEntity             geoEntity   = null;

  private static AggregatedAgeGroup    ageGroup    = null;

  private static AggregatedAgeGroupDTO ageGroupDTO = null;

  private static ClientSession         clientSession;

  private static ClientRequestIF       clientRequest;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(AggregatedCaseTest.class);

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
    term.delete();
    geoEntity.delete();
    clientSession.logout();
  }

  protected static void classSetUp()
  {
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[]{Locale.US});
    clientRequest = clientSession.getRequest();

    geoEntity = TestFixture.createRandomSite();
    term = TestFixture.createRandomTerm();

    ageGroup = AggregatedAgeGroup.getAll()[0];
    ageGroupDTO = AggregatedAgeGroupDTO.getAll(clientRequest)[0];
    
    terms = new Term[]{term};
    dtos = new TermDTO[]{TermDTO.get(clientRequest, term.getId())};
  }

  public void testCreateAggregatedCase()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCase c = new AggregatedCase();
    c.setGeoEntity(geoEntity);
    c.setStartDate(new Date());
    c.setEndDate(new Date());
    c.setAgeGroup(ageGroup);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);
    c.apply();

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      method.apply();
    }

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentsMd()))
    {
      CaseTreatment t = c.addTreatment(d);
      t.setAmount(new Integer(30));
      t.apply();
    }

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      CaseTreatmentStock s = c.addTreatmentStock(d);
      s.setOutOfStock(true);
      s.apply();
    }

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(d);
      t.setAmount(new Integer(40));
      t.apply();
    }

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      CaseReferral r = c.addReferral(d);
      r.setAmount(new Integer(70));
      r.apply();
    }

    try
    {
      AggregatedCase test = AggregatedCase.get(c.getId());

      assertEquals(c.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(c.getStartAge(), test.getStartAge());
      assertEquals(c.getEndAge(), test.getEndAge());
      assertEquals(c.getStartDate(), test.getStartDate());
      assertEquals(c.getEndAge(), test.getEndAge());
      assertEquals(c.getCases(), test.getCases());
      assertEquals(c.getCasesFemale(), test.getCasesFemale());
      assertEquals(c.getCasesMale(), test.getCasesMale());
      assertEquals(c.getCasesPregnant(), test.getCasesPregnant());
      assertEquals(c.getClinicallyDiagnosed(), test.getClinicallyDiagnosed());
      assertEquals(c.getDeaths(), test.getDeaths());

      for (CaseTreatmentStock s : test.getAllTreatmentStockRel())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethod m : test.getAllTreatmentMethodRel())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatment t : test.getAllTreatmentRel())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferral r : test.getAllReferralRel())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnostic d : test.getAllDiagnosticMethodRel())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testApplyAll()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCase c = new AggregatedCase();
    c.setGeoEntity(geoEntity);
    c.setStartDate(new Date());
    c.setEndDate(new Date());
    c.setAgeGroup(ageGroup);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseTreatment t = c.addTreatment(d);
      t.setAmount(new Integer(30));
      treatments.add(t);

    }

    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      CaseTreatmentStock s = c.addTreatmentStock(d);
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(d);
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      CaseReferral r = c.addReferral(d);
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);

    try
    {
      AggregatedCase test = AggregatedCase.get(c.getId());

      assertEquals(c.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(c.getStartAge(), test.getStartAge());
      assertEquals(c.getEndAge(), test.getEndAge());
      assertEquals(c.getStartDate(), test.getStartDate());
      assertEquals(c.getEndAge(), test.getEndAge());
      assertEquals(c.getCases(), test.getCases());
      assertEquals(c.getCasesFemale(), test.getCasesFemale());
      assertEquals(c.getCasesMale(), test.getCasesMale());
      assertEquals(c.getCasesPregnant(), test.getCasesPregnant());
      assertEquals(c.getClinicallyDiagnosed(), test.getClinicallyDiagnosed());
      assertEquals(c.getDeaths(), test.getDeaths());

      for (CaseTreatmentStock s : test.getAllTreatmentStockRel())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethod m : test.getAllTreatmentMethodRel())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatment t : test.getAllTreatmentRel())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferral r : test.getAllReferralRel())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnostic d : test.getAllDiagnosticMethodRel())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testStartDateProblem()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date startDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date endDate = calendar.getTime();

    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCase c = new AggregatedCase();
    c.setGeoEntity(geoEntity);
    c.setStartDate(startDate);
    c.setEndDate(endDate);
    c.setAgeGroup(ageGroup);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseTreatment t = c.addTreatment(d);
      t.setAmount(new Integer(30));
      treatments.add(t);

    }

    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      CaseTreatmentStock s = c.addTreatmentStock(d);
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(d);
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      CaseReferral r = c.addReferral(d);
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    try
    {
      c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);
      c.delete();

      fail("Able to create an aggregated case with the start date and end date after the current date");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(2, problems.size());

      for (ProblemIF problem : problems)
      {
        assertTrue(problem instanceof CurrentDateProblem);
      }
    }

  }

  public void testEndDateProblem()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -10);
    Date startDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_YEAR, 99);
    Date endDate = calendar.getTime();

    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCase c = new AggregatedCase();
    c.setGeoEntity(geoEntity);
    c.setStartDate(startDate);
    c.setEndDate(endDate);
    c.setAgeGroup(ageGroup);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);


    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      CaseTreatment t = c.addTreatment(d);
      t.setAmount(new Integer(30));
      treatments.add(t);

    }

    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      CaseTreatmentStock s = c.addTreatmentStock(d);
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(d);
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term d : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      CaseReferral r = c.addReferral(d);
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    try
    {
      c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);
      c.delete();

      fail("Able to create an aggregated case with the end date after the current date");
    }
    catch (ProblemException e)
    {
      // This is expected
      List<ProblemIF> problems = e.getProblems();

      assertEquals(1, problems.size());

      for (ProblemIF problem : problems)
      {
        assertTrue(problem instanceof CurrentDateProblem);
      }
    }

  }
  
  public void testCreateAggregatedView()
  {
    EpiDate date = EpiDate.getInstanceByPeriod(PeriodType.QUARTER, new Integer(1), new Integer(2007));

    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCaseView c = ageGroup.getView();
    c.setGeoEntity(geoEntity);
    c.setPeriod(1);
    c.addPeriodType(PeriodType.QUARTER);
    c.setPeriodYear(2007);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    for (Term g : terms)
    {
      CaseDiagnostic method = new CaseDiagnostic(c.getId(), g.getId());
      method.setAmount(new Integer(50));
      method.setAmountPositive(new Integer(30));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term g : terms)
    {
      CaseTreatment t = new CaseTreatment(c.getId(), g.getId());
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStock s = new CaseTreatmentStock(c.getId(), g.getId());
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term g : terms)
    {
      CaseTreatmentMethod t = new CaseTreatmentMethod(c.getId(), g.getId());
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term g : terms)
    {
      CaseReferral r = new CaseReferral(c.getId(), g.getId());
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);

    try
    {
      Calendar c1 = new GregorianCalendar();
      Calendar c2 = new GregorianCalendar();

      AggregatedCase test = AggregatedCase.get(c.getCaseId());

      assertEquals(c.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(ageGroup.getStartAge(), test.getStartAge());
      assertEquals(ageGroup.getEndAge(), test.getEndAge());

      c1.setTime(date.getStartDate());
      c2.setTime(test.getStartDate());

      assertEquals(c1.get(Calendar.DATE), c2.get(Calendar.DATE));
      assertEquals(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
      assertEquals(c1.get(Calendar.YEAR), c2.get(Calendar.YEAR));

      c1.setTime(date.getEndDate());
      c2.setTime(test.getEndDate());

      assertEquals(c1.get(Calendar.DATE), c2.get(Calendar.DATE));
      assertEquals(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
      assertEquals(c1.get(Calendar.YEAR), c2.get(Calendar.YEAR));

      assertEquals(c.getCases(), test.getCases());
      assertEquals(c.getCasesFemale(), test.getCasesFemale());
      assertEquals(c.getCasesMale(), test.getCasesMale());
      assertEquals(c.getCasesPregnant(), test.getCasesPregnant());
      assertEquals(c.getClinicallyDiagnosed(), test.getClinicallyDiagnosed());
      assertEquals(c.getDeaths(), test.getDeaths());

      for (CaseTreatmentStock s : test.getAllTreatmentStockRel())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethod m : test.getAllTreatmentMethodRel())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatment t : test.getAllTreatmentRel())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferral r : test.getAllReferralRel())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnostic d : test.getAllDiagnosticMethodRel())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }
  
  public void testGetRelationshipsView()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCaseView c = ageGroup.getView();
    c.setGeoEntity(geoEntity);
    c.setPeriod(1);
    c.addPeriodType(PeriodType.QUARTER);
    c.setPeriodYear(2007);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    for (Term g : terms)
    {
      CaseDiagnostic method = new CaseDiagnostic(c.getId(), g.getId());
      method.setAmount(new Integer(50));
      method.setAmountPositive(new Integer(30));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term g : terms)
    {
      CaseTreatment t = new CaseTreatment(c.getId(), g.getId());
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStock s = new CaseTreatmentStock(c.getId(), g.getId());
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term g : terms)
    {
      CaseTreatmentMethod t = new CaseTreatmentMethod(c.getId(), g.getId());
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term g : terms)
    {
      CaseReferral r = new CaseReferral(c.getId(), g.getId());
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);

    try
    {
      AggregatedCaseView test = AggregatedCase.getView(c.getCaseId());

      for (CaseTreatmentStock s : test.getTreatmentStocks())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethod m : test.getTreatmentMethods())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatment t : test.getTreatments())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferral r : test.getReferrals())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnostic d : test.getDiagnosticMethods())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }
  
  public void testViewApplyAllDTO()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCaseViewDTO c = new InfiantCaseViewDTO(clientRequest);
    c.setGeoEntity(GeoEntityDTO.get(clientRequest, geoEntity.getId()));
    c.setPeriod(1);
    c.addPeriodType(PeriodTypeDTO.QUARTER);
    c.setPeriodYear(2007);
    c.setAgeGroup(ageGroupDTO);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnosticDTO> diagnostics = new LinkedList<CaseDiagnosticDTO>();

    for (TermDTO d : dtos)
    {
      CaseDiagnosticDTO method = new CaseDiagnosticDTO(clientRequest, c.getId(), d.getId());
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatmentDTO> treatments = new LinkedList<CaseTreatmentDTO>();
    List<CaseTreatmentStockDTO> stocks = new LinkedList<CaseTreatmentStockDTO>();
    for (TermDTO g : dtos)
    {
      CaseTreatmentDTO t = new CaseTreatmentDTO(clientRequest, c.getId(), g.getId());
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStockDTO s = new CaseTreatmentStockDTO(clientRequest, c.getId(), g.getId());
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethodDTO> methods = new LinkedList<CaseTreatmentMethodDTO>();
    for (TermDTO g : dtos)
    {
      CaseTreatmentMethodDTO t = new CaseTreatmentMethodDTO(clientRequest, c.getId(), g.getId());
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferralDTO> referrals = new LinkedList<CaseReferralDTO>();
    for (TermDTO g : dtos)
    {
      CaseReferralDTO r = new CaseReferralDTO(clientRequest, c.getId(), g.getId());
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatmentDTO[] treatArray = treatments.toArray(new CaseTreatmentDTO[treatments.size()]);
    CaseTreatmentMethodDTO[] methodArray = methods.toArray(new CaseTreatmentMethodDTO[methods.size()]);
    CaseTreatmentStockDTO[] stockArray = stocks.toArray(new CaseTreatmentStockDTO[stocks.size()]);
    CaseDiagnosticDTO[] diagnosticArray = diagnostics.toArray(new CaseDiagnosticDTO[diagnostics.size()]);
    CaseReferralDTO[] referralArray = referrals.toArray(new CaseReferralDTO[referrals.size()]);

    c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);

    try
    {
      AggregatedCaseViewDTO test = AggregatedCaseDTO.getView(clientRequest, c.getCaseId());

      assertEquals(c.getGeoEntity().getId(), test.getGeoEntity().getId());
      assertEquals(c.getCases(), test.getCases());
      assertEquals(c.getCasesFemale(), test.getCasesFemale());
      assertEquals(c.getCasesMale(), test.getCasesMale());
      assertEquals(c.getCasesPregnant(), test.getCasesPregnant());
      assertEquals(c.getClinicallyDiagnosed(), test.getClinicallyDiagnosed());
      assertEquals(c.getDeaths(), test.getDeaths());

      for (CaseTreatmentStockDTO s : test.getTreatmentStocks())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethodDTO m : test.getTreatmentMethods())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatmentDTO t : test.getTreatments())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferralDTO r : test.getReferrals())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnosticDTO d : test.getDiagnosticMethods())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }
  
  public void testSearchAggregatedCase()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCaseView c = ageGroup.getView();
    c.setGeoEntity(geoEntity);
    c.setPeriod(1);
    c.addPeriodType(PeriodType.QUARTER);
    c.setPeriodYear(2007);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    for (Term g : terms)
    {
      CaseDiagnostic method = new CaseDiagnostic(c.getId(), g.getId());
      method.setAmount(new Integer(50));
      method.setAmountPositive(new Integer(30));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for (Term g : terms)
    {
      CaseTreatment t = new CaseTreatment(c.getId(), g.getId());
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStock s = new CaseTreatmentStock(c.getId(), g.getId());
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for (Term g : terms)
    {
      CaseTreatmentMethod t = new CaseTreatmentMethod(c.getId(), g.getId());
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for (Term g : terms)
    {
      CaseReferral r = new CaseReferral(c.getId(), g.getId());
      r.setAmount(new Integer(70));
      referrals.add(r);
    }

    CaseTreatment[] treatArray = treatments.toArray(new CaseTreatment[treatments.size()]);
    CaseTreatmentMethod[] methodArray = methods.toArray(new CaseTreatmentMethod[methods.size()]);
    CaseTreatmentStock[] stockArray = stocks.toArray(new CaseTreatmentStock[stocks.size()]);
    CaseDiagnostic[] diagnosticArray = diagnostics.toArray(new CaseDiagnostic[diagnostics.size()]);
    CaseReferral[] referralArray = referrals.toArray(new CaseReferral[referrals.size()]);

    c.applyAll(treatArray, methodArray, stockArray, diagnosticArray, referralArray);

    try
    {
      AggregatedCaseView test = AggregatedCase.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.QUARTER, 1, 2007, ageGroup);

      assertNotNull(test);
      assertEquals(c.getCases(), test.getCases());
      assertEquals(c.getCasesFemale(), test.getCasesFemale());
      assertEquals(c.getCasesMale(), test.getCasesMale());
      assertEquals(c.getCasesPregnant(), test.getCasesPregnant());
      assertEquals(c.getClinicallyDiagnosed(), test.getClinicallyDiagnosed());
      assertEquals(c.getDeaths(), test.getDeaths());
      assertEquals(c.getPeriod(), test.getPeriod());
      assertEquals(c.getPeriodType().size(), test.getPeriodType().size());
      assertEquals(c.getPeriodType().get(0), test.getPeriodType().get(0));
      assertEquals(c.getPeriodYear(), test.getPeriodYear());

      for (CaseTreatmentStock s : test.getTreatmentStocks())
        assertEquals(new Boolean(true), s.getOutOfStock());
      for (CaseTreatmentMethod m : test.getTreatmentMethods())
        assertEquals(new Integer(40), m.getAmount());
      for (CaseTreatment t : test.getTreatments())
        assertEquals(new Integer(30), t.getAmount());
      for (CaseReferral r : test.getReferrals())
        assertEquals(new Integer(70), r.getAmount());
      for (CaseDiagnostic d : test.getDiagnosticMethods())
        assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testUnknownCase()
  {
    AggregatedCaseView test = AggregatedCase.searchByGeoEntityAndEpiDate(geoEntity, PeriodType.QUARTER, 1, 2009, ageGroup);

    assertEquals("", test.getCaseId());
  }
}
