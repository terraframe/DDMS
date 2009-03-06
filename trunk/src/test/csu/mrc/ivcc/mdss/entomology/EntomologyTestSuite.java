package csu.mrc.ivcc.mdss.entomology;

import junit.framework.Test;
import junit.framework.TestSuite;

public class EntomologyTestSuite extends TestSuite
{
  public static void main(String args[])
  {
    junit.textui.TestRunner.run(EntomologyTestSuite.suite());
  }
  
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    
    suite.addTest(ADDATest.suite());
    suite.addTest(MosquitoCollectionPointTest.suite());
    suite.addTest(MosquitoCollectionTest.suite());
    suite.addTest(MOTest.suite());
    
    return suite;
  }
}
