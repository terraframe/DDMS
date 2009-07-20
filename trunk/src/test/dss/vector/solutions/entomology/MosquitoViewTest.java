package dss.vector.solutions.entomology;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.terraframe.mojo.dataaccess.database.Database;

import dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult;
import dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult;
import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.mo.Generation;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.InfectivityMethodology;
import dss.vector.solutions.mo.InsecticideMethodology;
import dss.vector.solutions.mo.MolecularAssayResult;
import dss.vector.solutions.mo.Specie;

public class MosquitoViewTest extends TestCase
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

  private static GeoEntity              geoEntity              = null;

  private static MosquitoCollection     collection             = null;

  private static CollectionMethod       collectionMethod       = null;

  private static Specie                 specie                 = null;

  private static IdentificationMethod   identificationMethod   = null;

  private static MolecularAssayResult   result                 = null;

  private static Generation             F0                     = null;

  private static InfectivityMethodology infectivityMethodology = null;

  private static InsecticideMethodology insecticideMethodology = null;


  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MosquitoViewTest.class);

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
  public void testGetTargetSiteAccessors()
  {
    String sql = "";

    sql = MosquitoView.getTempTableSQL(InfectivityAssayTestResult.CLASS,"test");
    //System.out.println(sql);
    Database.parseAndExecute(sql);

    sql = MosquitoView.getTempTableSQL(TargetSiteAssayTestResult.CLASS,"test");
    //System.out.println(sql);
    Database.parseAndExecute(sql);

    sql = MosquitoView.getTempTableSQL(MetabolicAssayTestResult.CLASS,"test");
    //System.out.println(sql);
    Database.parseAndExecute(sql);

  }


}
