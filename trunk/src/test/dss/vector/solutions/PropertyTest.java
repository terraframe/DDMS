package dss.vector.solutions;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class PropertyTest extends TestCase
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
    suite.addTestSuite(PropertyTest.class);

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

  protected static void classSetUp()
  {

  }

  protected static void classTearDown()
  {

  }

  public void testGetNextID()
  {
    Property segmentsProperty = Property.getByPackageAndName(PropertyInfo.SYSTEM_PACKAGE,
        PropertyInfo.SHORT_ID_SEGMENTS);
    segmentsProperty.lock();
    segmentsProperty.setPropertyValue("1024");
    segmentsProperty.apply();

    Property offsetProperty = Property.getByPackageAndName(PropertyInfo.INSTALL_PACKAGE, LocalPropertyInfo.SHORT_ID_OFFSET);
    offsetProperty.lock();
    offsetProperty.setPropertyValue("512");
    offsetProperty.apply();

    int segments = Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_SEGMENTS);
    int offset = Property.getInt(PropertyInfo.INSTALL_PACKAGE, LocalPropertyInfo.SHORT_ID_OFFSET);

    // assume ids are 8 long and in base 30
    long MAX_ID = (long) Math.pow(30, 8);

    long totalOffset = ( MAX_ID / segments ) * offset;

    Long oldValue = Property.getLong(PropertyInfo.SYSTEM_PACKAGE, LocalPropertyInfo.SHORT_ID_COUNTER);

    long expectedId = totalOffset + oldValue + 1;

    String nextId = LocalProperty.getNextId();

    Long newValue = Property.getLong(PropertyInfo.SYSTEM_PACKAGE, LocalPropertyInfo.SHORT_ID_COUNTER);

    assertEquals((long) oldValue + 1, (long) newValue);

    assertEquals(Base30.fromBase30(nextId), expectedId);

    assertEquals(Base30.toBase30String(expectedId, 8), nextId);

    System.out.println("nextId is :" + nextId);

    System.out.println("nubmer of ids is  :" + MAX_ID);

    System.out.println("max id is  :" + Base30.toBase30String(MAX_ID, 8));

  }

}
