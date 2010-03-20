package dss.vector.solutions.surveillance;

import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.runwaysdk.ClientSession;
import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.PeriodMonthProblemDTO;
import dss.vector.solutions.PeriodQuarterProblemDTO;
import dss.vector.solutions.PeriodWeekProblemDTO;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;

public class AggregatedCaseTestDTO extends TestCase implements DoNotWeave
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

  private static GeoEntityDTO          geoEntity = null;

  private static AggregatedAgeGroupDTO ageGroup;

  private static ClientSession         clientSession;

  private static ClientRequestIF       clientRequest;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(AggregatedCaseTestDTO.class);

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
    clientSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[]{Locale.US});
    clientRequest = clientSession.getRequest();
    clientRequest.setKeepMessages(false);

    geoEntity = TestFixture.createRandomSite(clientRequest);
    
    ageGroup = AggregatedAgeGroupDTO.getAll(clientRequest)[0];
  }

  public void testInvalidEpiWeek()
  {
    try
    {
      AggregatedCaseDTO.searchByGeoEntityAndEpiDate(clientRequest, geoEntity, PeriodTypeDTO.WEEK, 70, 1999, ageGroup);

      fail("Able to set an invalid epi week");
    }
    catch(ProblemExceptionDTO e)
    {
      List<ProblemDTOIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof PeriodWeekProblemDTO);
    }
  }

  public void testInvalidEpiMonth()
  {
    try
    {
      AggregatedCaseDTO.searchByGeoEntityAndEpiDate(clientRequest, geoEntity, PeriodTypeDTO.MONTH, 13, 1999, ageGroup);

      fail("Able to set an invalid epi week");
    }
    catch(ProblemExceptionDTO e)
    {
      List<ProblemDTOIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof PeriodMonthProblemDTO);
    }
  }

  public void testInvalidEpiQuarter()
  {
    try
    {
      AggregatedCaseDTO.searchByGeoEntityAndEpiDate(clientRequest, geoEntity, PeriodTypeDTO.QUARTER, 5, 1999, ageGroup);

      fail("Able to set an invalid epi week");
    }
    catch(ProblemExceptionDTO e)
    {
      List<ProblemDTOIF> problems = e.getProblems();

      assertEquals(1, problems.size());
      assertTrue(problems.get(0) instanceof PeriodQuarterProblemDTO);
    }    
  }

}
