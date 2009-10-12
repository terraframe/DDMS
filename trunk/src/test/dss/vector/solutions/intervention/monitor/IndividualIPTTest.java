package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;

import dss.vector.solutions.Person;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class IndividualIPTTest extends TestCase
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

  private static HealthFacility    facility = null;

  private static SentinelSite      site     = null;

  private static Person            person   = null;

  private static IPTRecipient      patient  = null;

  private static IndividualIPTCase iptCase  = null;

  private static Term                term     = null;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(IndividualIPTTest.class);

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
    iptCase.delete();

    facility.delete();

    site.delete();

    patient.delete();

    Person.get(person.getId()).delete();

    term.delete();
  }

  protected static void classSetUp()
  {
    facility = new HealthFacility();
    facility.setGeoId(TestConstants.GEO_ID);
    facility.setEntityName("Facility");
    facility.apply();

    site = new SentinelSite();
    site.setGeoId(TestConstants.GEO_ID_2);
    site.setEntityName("Site");
    site.apply();

    person = new Person();
    person.setFirstName("Justin");
    person.setLastName("Smethie");
    person.setDateOfBirth(new Date());
    person.addSex(Sex.MALE);
    person.apply();

    patient = new IPTRecipient();
    patient.setPerson(person);
    patient.apply();

    person.setIptRecipientDelegate(patient);
    person.apply();

    iptCase = new IndividualIPTCase();
    iptCase.setPatient(patient);
    iptCase.setResidentialLocation(site);
    iptCase.apply();

    term = TestFixture.createRandomTerm();
  }

  public void testCreateIndividualIPT()
  {
    IndividualIPT concrete = new IndividualIPT();
    concrete.setFacility(facility);
    concrete.setIptCase(iptCase);
    concrete.setPatientType(term);
    concrete.setIsANCVisit(true);
    concrete.setVisitNumber(term);
    concrete.setDoseNumber(term);
    concrete.setDoseType(term);
    concrete.setRecievedSupplement(true);
    concrete.setRecievedITN(true);
    concrete.setNumberOfRecievedITNs(5);
    concrete.setAdministratorName("Justin");
    concrete.setAdministratorSurname("Smethie");
    concrete.apply();

    try
    {
      IndividualIPT test = IndividualIPT.get(concrete.getId());

      assertEquals(concrete.getFacility().getId(), test.getFacility().getId());
      assertEquals(concrete.getIptCase().getId(), test.getIptCase().getId());

      assertEquals(concrete.getPatientType().getId(), test.getPatientType().getId());
      assertEquals(concrete.getIsANCVisit(), test.getIsANCVisit());
      assertEquals(concrete.getVisitNumber().getId(), test.getVisitNumber().getId());
      assertEquals(concrete.getDoseNumber().getId(), test.getDoseNumber().getId());
      assertEquals(concrete.getDoseType().getId(), test.getDoseType().getId());
      assertEquals(concrete.getRecievedSupplement(), test.getRecievedSupplement());
      assertEquals(concrete.getRecievedITN(), test.getRecievedITN());
      assertEquals(concrete.getNumberOfRecievedITNs(), test.getNumberOfRecievedITNs());
      assertEquals(concrete.getAdministratorName(), test.getAdministratorName());
      assertEquals(concrete.getAdministratorSurname(), test.getAdministratorSurname());
    }
    finally
    {
      concrete.delete();
    }
  }

  public void testCreateIndividualIPTView()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.setFacility(facility.getGeoId());

    view.setIptCase(iptCase);
    view.setPatientType(term);
    view.setIsANCVisit(true);
    view.setVisitNumber(term);
    view.setDoseNumber(term);
    view.setDoseType(term);
    view.setRecievedSupplement(true);
    view.setRecievedITN(true);
    view.setNumberOfRecievedITNs(5);
    view.setAdministratorName("Justin");
    view.setAdministratorSurname("Smethie");
    view.apply();

    try
    {
      IndividualIPTView test = IndividualIPT.getView(view.getConcreteId());

      assertEquals(view.getFacility(), test.getFacility());

      assertEquals(view.getIptCase().getId(), test.getIptCase().getId());
      
      assertEquals(view.getPatientType().getId(), test.getPatientType().getId());
      assertEquals(view.getIsANCVisit(), test.getIsANCVisit());
      assertEquals(view.getVisitNumber().getId(), test.getVisitNumber().getId());
      assertEquals(view.getDoseNumber().getId(), test.getDoseNumber().getId());
      assertEquals(view.getDoseType().getId(), test.getDoseType().getId());
      assertEquals(view.getRecievedSupplement(), test.getRecievedSupplement());
      assertEquals(view.getRecievedITN(), test.getRecievedITN());
      assertEquals(view.getNumberOfRecievedITNs(), test.getNumberOfRecievedITNs());
      assertEquals(view.getAdministratorName(), test.getAdministratorName());
      assertEquals(view.getAdministratorSurname(), test.getAdministratorSurname());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testEditIndividualIPTView()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.setFacility(facility.getGeoId());

    view.setIptCase(iptCase);
    
    view.setPatientType(term);
    view.setIsANCVisit(true);
    view.setVisitNumber(term);
    view.setDoseNumber(term);
    view.setDoseType(term);
    view.setRecievedSupplement(true);
    view.setRecievedITN(true);
    view.setNumberOfRecievedITNs(5);
    view.setAdministratorName("Justin");
    view.setAdministratorSurname("Smethie");
    view.apply();

    try
    {
      IndividualIPTView edit = IndividualIPT.lockView(view.getConcreteId());
      edit.setFacility(facility.getGeoId());
      edit.setIptCase(iptCase);
      edit.setPatientType(term);
      edit.setIsANCVisit(true);
      edit.setVisitNumber(term);
      edit.setDoseNumber(term);
      edit.setDoseType(term);
      edit.setRecievedSupplement(true);
      edit.setRecievedITN(true);
      edit.setNumberOfRecievedITNs(2);
      edit.setAdministratorName("Justin");
      edit.setAdministratorSurname("Smethie");
      edit.apply();

      IndividualIPTView test = IndividualIPT.getView(view.getConcreteId());

      assertEquals(edit.getFacility(), test.getFacility());
      assertEquals(edit.getIptCase().getId(), test.getIptCase().getId());
      assertEquals(edit.getPatientType().getId(), test.getPatientType().getId());
      assertEquals(edit.getIsANCVisit(), test.getIsANCVisit());
      assertEquals(edit.getVisitNumber().getId(), test.getVisitNumber().getId());
      assertEquals(edit.getDoseNumber().getId(), test.getDoseNumber().getId());
      assertEquals(edit.getDoseType().getId(), test.getDoseType().getId());
      assertEquals(edit.getRecievedSupplement(), test.getRecievedSupplement());
      assertEquals(edit.getRecievedITN(), test.getRecievedITN());
      assertEquals(edit.getNumberOfRecievedITNs(), test.getNumberOfRecievedITNs());
      assertEquals(edit.getAdministratorName(), test.getAdministratorName());
      assertEquals(edit.getAdministratorSurname(), test.getAdministratorSurname());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testGetPage()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.setFacility(facility.getGeoId());

    view.setIptCase(iptCase);
    
    view.setPatientType(term);
    view.setIsANCVisit(true);
    view.setVisitNumber(term);
    view.setDoseNumber(term);
    view.setDoseType(term);
    view.setRecievedSupplement(true);
    view.setRecievedITN(true);
    view.setNumberOfRecievedITNs(5);
    view.setAdministratorName("Justin");
    view.setAdministratorSurname("Smethie");
    view.apply();

    try
    {
      IndividualIPTViewQuery query = IndividualIPTView.getPage(null, true, 20, 1);
      List<? extends IndividualIPTView> list = query.getIterator().getAll();

      assertEquals(1, list.size());

      IndividualIPTView test = list.get(0);

      assertEquals(view.getFacility(), test.getFacility());
      assertEquals(view.getIptCase().getId(), test.getIptCase().getId());      
      assertEquals(view.getPatientType().getId(), test.getPatientType().getId());
      assertEquals(view.getIsANCVisit(), test.getIsANCVisit());
      assertEquals(view.getVisitNumber().getId(), test.getVisitNumber().getId());
      assertEquals(view.getDoseNumber().getId(), test.getDoseNumber().getId());
      assertEquals(view.getDoseType().getId(), test.getDoseType().getId());
      assertEquals(view.getRecievedSupplement(), test.getRecievedSupplement());
      assertEquals(view.getRecievedITN(), test.getRecievedITN());
      assertEquals(view.getNumberOfRecievedITNs(), test.getNumberOfRecievedITNs());
      assertEquals(view.getAdministratorName(), test.getAdministratorName());
      assertEquals(view.getAdministratorSurname(), test.getAdministratorSurname());
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testNumberOfRecievedITNs()
  {
    try
    {

      IndividualIPTView view = new IndividualIPTView();
      view.setFacility(facility.getGeoId());
      view.setIptCase(iptCase);
      
      view.setPatientType(term);
      view.setIsANCVisit(true);
      view.setVisitNumber(term);
      view.setDoseNumber(term);
      view.setDoseType(term);
      view.setRecievedSupplement(true);
      view.setRecievedITN(false);
      view.setNumberOfRecievedITNs(5);
      view.setAdministratorName("Justin");
      view.setAdministratorSurname("Smethie");
      view.apply();

      view.deleteConcrete();

      fail("Able to create individual IPT information with a number of recieved ITNs when Recieved ITNs is false");
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

  public void testNonHealthFacilityGeoId()
  {
    try
    {
      IndividualIPTView view = new IndividualIPTView();
      view.setFacility(site.getGeoId());
      view.setIptCase(iptCase);
      
      view.setPatientType(term);
      view.setIsANCVisit(true);
      view.setVisitNumber(term);
      view.setDoseNumber(term);
      view.setDoseType(term);
      view.setRecievedSupplement(true);
      view.setRecievedITN(true);
      view.setNumberOfRecievedITNs(5);
      view.setAdministratorName("Justin");
      view.setAdministratorSurname("Smethie");
      view.apply();

      view.deleteConcrete();

      fail("Able to create individual IPT information with an invalid geo id");
    }
    catch (InvalidReferenceException e)
    {
      // This is expected: Ensure that the correct problem was thrown
    }
  }

}
