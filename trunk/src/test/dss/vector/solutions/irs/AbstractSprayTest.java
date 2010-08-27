package dss.vector.solutions.irs;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AbstractSprayTest extends TestCase
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

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(AbstractSprayTest.class);

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
  }

  protected static void classSetUp()
  {
  }

//  public void testCreateTempTable()
//  {
//    String insecticideView = "insecticide_view";
//    String resourceTargetView = "resource_target_view";
//    String zoneSprayView = "zone_spray_view";
//    String sprayStatusView = "spray_status_view";
//    String tableName = "all_levels_spray_view";
//
//    //SprayStatus.createTempTable(sprayStatusView);
//    //InsecticideBrand.createTempTable(insecticideView);
//    ResourceTarget.createDatabaseView(resourceTargetView);
//
//    //OperatorSpray.createTempTable("operator_spray_view");
//    //TeamSpray.createTempTable("team_spray_view");
//    //ZoneSpray.createTempTable(zoneSprayView);
//
//     ZoneSpray.createTempTable(tableName,resourceTargetView);
//
//
//  }



}
