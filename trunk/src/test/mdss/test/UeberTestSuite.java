 package mdss.test;

import mdss.entomology.MosquitoCollectionTest;
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
      
    suite.addTest(MosquitoCollectionTest.suite());
    
    return suite;
  }
  
}
