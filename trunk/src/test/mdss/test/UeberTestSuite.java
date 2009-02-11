 package mdss.test;

import mdss.entomology.EntomologyTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class UeberTestSuite
{
  
  public static void main(String args[])
  {  
    junit.textui.TestRunner.run(UeberTestSuite.suite());
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
      
    suite.addTest(EntomologyTestSuite.suite());
    
    return suite;
  }
  
}
