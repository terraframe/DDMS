package dss.vector.solutions.selenium;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SeleniumTestSuite
{

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(PopulationPrecision.class);
    return suite;
  }

  public static void main(String[] args)
  {
    junit.textui.TestRunner.run(suite());
  }
}
