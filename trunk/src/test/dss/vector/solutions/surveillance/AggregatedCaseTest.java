package dss.vector.solutions.surveillance;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.TestConstants;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.SentinalSite;

public class AggregatedCaseTest extends TestCase
{
  private static GeoEntity             geoEntity            = null;
  private static ClientSession clientSession;
  private static ClientRequestIF clientRequest;


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
    geoEntity.delete();
    clientSession.logout();

  }

  protected static void classSetUp()
  {
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, Locale.US);
    clientRequest = clientSession.getRequest();

    geoEntity = new SentinalSite();
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("Sentinel Site");
    geoEntity.apply();
  }

  public void testConvertEpiWeek()
  {

  }

  public void testInvalidEpiWeek()
  {

  }

  public void testInvalidEpiMonth()
  {

  }

  public void testInvalidEpiQuarter()
  {

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
    c.setStartAge(0);
    c.setEndAge(1);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);
    c.apply();

    for(DiagnosticGrid d : DiagnosticGrid.getAll())
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      method.apply();
    }

    for(TreatmentGrid g : TreatmentGrid.getAll())
    {
      CaseTreatment t = c.addTreatment(g);
      t.setAmount(new Integer(30));
      t.apply();

      CaseTreatmentStock s = c.addTreatmentStock(g);
      s.setOutOfStock(true);
      s.apply();
    }

    for(TreatmentMethodGrid g : TreatmentMethodGrid.getAll())
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(g);
      t.setAmount(new Integer(40));
      t.apply();
    }

    for(ReferralGrid g : ReferralGrid.getAll())
    {
      CaseReferral r = c.addReferral(g);
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

      for(CaseTreatmentStock s : test.getAllTreatmentStockRel()) assertEquals(new Boolean(true), s.getOutOfStock());
      for(CaseTreatmentMethod m : test.getAllTreatmentMethodRel()) assertEquals(new Integer(40), m.getAmount());
      for(CaseTreatment t : test.getAllTreatmentRel()) assertEquals(new Integer(30), t.getAmount());
      for(CaseReferral r : test.getAllReferralRel()) assertEquals(new Integer(70), r.getAmount());
      for(CaseDiagnostic d : test.getAllDiagnosticMethodRel()) assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testDuplicateAggregatedCAse()
  {

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
    c.setStartAge(0);
    c.setEndAge(1);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnostic> diagnostics = new LinkedList<CaseDiagnostic>();
    for(DiagnosticGrid d : DiagnosticGrid.getAll())
    {
      CaseDiagnostic method = c.addDiagnosticMethod(d);
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatment> treatments = new LinkedList<CaseTreatment>();
    List<CaseTreatmentStock> stocks = new LinkedList<CaseTreatmentStock>();
    for(TreatmentGrid g : TreatmentGrid.getAll())
    {
      CaseTreatment t = c.addTreatment(g);
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStock s = c.addTreatmentStock(g);
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethod> methods = new LinkedList<CaseTreatmentMethod>();
    for(TreatmentMethodGrid g : TreatmentMethodGrid.getAll())
    {
      CaseTreatmentMethod t = c.addTreatmentMethod(g);
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferral> referrals = new LinkedList<CaseReferral>();
    for(ReferralGrid g : ReferralGrid.getAll())
    {
      CaseReferral r = c.addReferral(g);
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

      for(CaseTreatmentStock s : test.getAllTreatmentStockRel()) assertEquals(new Boolean(true), s.getOutOfStock());
      for(CaseTreatmentMethod m : test.getAllTreatmentMethodRel()) assertEquals(new Integer(40), m.getAmount());
      for(CaseTreatment t : test.getAllTreatmentRel()) assertEquals(new Integer(30), t.getAmount());
      for(CaseReferral r : test.getAllReferralRel()) assertEquals(new Integer(70), r.getAmount());
      for(CaseDiagnostic d : test.getAllDiagnosticMethodRel()) assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testApplyAllDTO()
  {
    Integer cases = new Integer(50);
    Integer casesFemale = new Integer(23);
    Integer casesMale = new Integer(50);
    Integer casesPregnant = new Integer(2);
    Integer clinicallyDiagnosed = new Integer(50);
    Integer deaths = new Integer(50);

    AggregatedCaseDTO c = new AggregatedCaseDTO(clientRequest);
    c.setGeoEntity(GeoEntityDTO.get(clientRequest, geoEntity.getId()));
    c.setStartDate(new Date());
    c.setEndDate(new Date());
    c.setStartAge(0);
    c.setEndAge(1);
    c.setCases(cases);
    c.setCasesFemale(casesFemale);
    c.setCasesMale(casesMale);
    c.setCasesPregnant(casesPregnant);
    c.setClinicallyDiagnosed(clinicallyDiagnosed);
    c.setDeaths(deaths);

    List<CaseDiagnosticDTO> diagnostics = new LinkedList<CaseDiagnosticDTO>();

    for(DiagnosticGridDTO d : DiagnosticGridDTO.getAll(clientRequest))
    {
      CaseDiagnosticDTO method = new CaseDiagnosticDTO(clientRequest, c.getId(), d.getId());
      method.setAmount(new Integer(50));
      diagnostics.add(method);
    }

    List<CaseTreatmentDTO> treatments = new LinkedList<CaseTreatmentDTO>();
    List<CaseTreatmentStockDTO> stocks = new LinkedList<CaseTreatmentStockDTO>();
    for(TreatmentGridDTO g : TreatmentGridDTO.getAll(clientRequest))
    {
      CaseTreatmentDTO t = new CaseTreatmentDTO(clientRequest, c.getId(), g.getId());
      t.setAmount(new Integer(30));
      treatments.add(t);

      CaseTreatmentStockDTO s = new CaseTreatmentStockDTO(clientRequest, c.getId(), g.getId());
      s.setOutOfStock(true);
      stocks.add(s);
    }

    List<CaseTreatmentMethodDTO> methods = new LinkedList<CaseTreatmentMethodDTO>();
    for(TreatmentMethodGridDTO g : TreatmentMethodGridDTO.getAll(clientRequest))
    {
      CaseTreatmentMethodDTO t = new CaseTreatmentMethodDTO(clientRequest, c.getId(), g.getId());
      t.setAmount(new Integer(40));
      methods.add(t);
    }

    List<CaseReferralDTO> referrals = new LinkedList<CaseReferralDTO>();
    for(ReferralGridDTO g : ReferralGridDTO.getAll(clientRequest))
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
      AggregatedCaseDTO test = AggregatedCaseDTO.get(clientRequest, c.getId());

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

      for(CaseTreatmentStockDTO s : test.getAllTreatmentStockRelationships()) assertEquals(new Boolean(true), s.getOutOfStock());
      for(CaseTreatmentMethodDTO m : test.getAllTreatmentMethodRelationships()) assertEquals(new Integer(40), m.getAmount());
      for(CaseTreatmentDTO t : test.getAllTreatmentRelationships()) assertEquals(new Integer(30), t.getAmount());
      for(CaseReferralDTO r : test.getAllReferralRelationships()) assertEquals(new Integer(70), r.getAmount());
      for(CaseDiagnosticDTO d : test.getAllDiagnosticMethodRelationships()) assertEquals(new Integer(50), d.getAmount());
    }
    finally
    {
      c.delete();
    }
  }

  public void testCreateAggregatedView()
  {

  }


  public void testEditAggregatedView()
  {

  }

  public void testSearchAggregatedCase()
  {

  }

  public void testSearchInactiveAggregatedAgeCase()
  {

  }

  public void testUnknownCase()
  {

  }
}
