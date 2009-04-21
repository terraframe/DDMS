package dss.vector.solutions.irs;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinalSite;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GeoTargetTest extends TestCase
{
  public static GeoEntity geoEntity;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(GeoTargetTest.class);

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
  }

  protected static void classSetUp()
  {
    geoEntity = new SentinalSite();
    geoEntity.setActivated(true);
    geoEntity.setGeoId("0");
    geoEntity.setEntityName("testName");
    geoEntity.apply();
  }

  public void testCreate() throws Exception
  {
    Integer year = 2009;

    GeoTarget target = new GeoTarget();
    target.setGeoEntity(geoEntity);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();

    try
    {
      GeoTarget test = GeoTarget.get(target.getId());

      assertEquals(test.getGeoEntity().getId(), target.getGeoEntity().getId());
      assertEquals(test.getTargetYear(), target.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), GeoTarget.class.getMethod("getTarget_" + i).invoke(test));
      }
    }
    finally
    {
      target.delete();
    }
  }

  public void testUpdate() throws Exception
  {
    Integer year = 2009;

    GeoTarget target = new GeoTarget();
    target.setGeoEntity(geoEntity);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();

    GeoTarget edit = GeoTarget.lock(target.getId());

    for (int i = 0; i < 53; i++)
    {
      GeoTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(edit, 2 * i);
    }

    edit.apply();

    try
    {
      GeoTarget test = GeoTarget.get(edit.getId());

      assertEquals(test.getGeoEntity().getId(), edit.getGeoEntity().getId());
      assertEquals(test.getTargetYear(), edit.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(2 * i), GeoTarget.class.getMethod("getTarget_" + i).invoke(edit));
      }
    }
    finally
    {
      edit.delete();
    }
  }

  public void testDelete() throws Exception
  {
    Integer year = 2009;

    GeoTarget target = new GeoTarget();
    target.setGeoEntity(geoEntity);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();
    target.delete();

    try
    {
      GeoTarget.get(target.getId());

      fail("Able in get a deleted resource");
    }
    catch(Exception e)
    {
      //This is expected
    }
  }

  public void testCreateView() throws Exception
  {
    Integer year = 2009;

    GeoTargetView view = new GeoTargetView();
    view.setGeoEntity(geoEntity);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    try
    {
      GeoTargetView test = GeoTarget.getView(view.getTargetId());

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getGeoEntity().getId(), view.getGeoEntity().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), GeoTargetView.class.getMethod("getTarget_" + i).invoke(test));
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testUpdateView() throws Exception
  {
    Integer year = 2009;

    GeoTargetView view = new GeoTargetView();
    view.setGeoEntity(geoEntity);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    GeoTargetView edit = view.lock();

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edit, 2 * i);
    }

    edit.apply();

    try
    {
      GeoTargetView test = GeoTarget.getView(view.getTargetId());

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getGeoEntity().getId(), view.getGeoEntity().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(2 * i), GeoTargetView.class.getMethod("getTarget_" + i).invoke(test));
      }
    }
    finally
    {
      view.deleteConcrete();
    }

  }

  public void testSearch() throws Exception
  {
    Integer year = 2009;

    GeoTargetView view = new GeoTargetView();
    view.setGeoEntity(geoEntity);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    try
    {
      GeoTargetView test = GeoTarget.find(geoEntity);

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getGeoEntity().getId(), view.getGeoEntity().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), GeoTargetView.class.getMethod("getTarget_" + i).invoke(test));
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testEmptySearch() throws Exception
  {
    GeoTargetView test = GeoTarget.find(geoEntity);

    assertEquals(test.getTargetId(), "");
    assertEquals(test.getGeoEntity().getId(), geoEntity.getId());
  }

  public void testApplyAll() throws Exception
  {
    Integer year = 2009;

    GeoTargetView[] views = new GeoTargetView[2];
    GeoTargetView[] tests = new GeoTargetView[2];

    views[0] = new GeoTargetView();
    views[0].setGeoEntity(geoEntity);
    views[0].setTargetYear(year);

    views[1] = new GeoTargetView();
    views[1].setGeoEntity(geoEntity);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    GeoTargetView.applyAll(views);

    try
    {
      tests[0] = GeoTarget.getView(views[0].getTargetId());
      tests[1] = GeoTarget.getView(views[1].getTargetId());

      assertEquals(tests[0].getTargetId(), views[0].getTargetId());
      assertEquals(tests[0].getGeoEntity().getId(), views[0].getGeoEntity().getId());
      assertEquals(tests[0].getTargetYear(), views[0].getTargetYear());

      assertEquals(tests[1].getTargetId(), views[1].getTargetId());
      assertEquals(tests[1].getGeoEntity().getId(), views[1].getGeoEntity().getId());
      assertEquals(tests[1].getTargetYear(), views[1].getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), GeoTargetView.class.getMethod("getTarget_" + i).invoke(tests[0]));
        assertEquals(new Integer(i * 2), GeoTargetView.class.getMethod("getTarget_" + i).invoke(tests[1]));
      }
    }
    finally
    {
      views[0].deleteConcrete();
      views[1].deleteConcrete();
    }
  }

  public void testEditAll() throws Exception
  {
    Integer year = 2009;

    GeoTargetView[] views = new GeoTargetView[2];
    GeoTargetView[] edits = new GeoTargetView[2];
    GeoTargetView[] tests = new GeoTargetView[2];

    views[0] = new GeoTargetView();
    views[0].setGeoEntity(geoEntity);
    views[0].setTargetYear(year);

    views[1] = new GeoTargetView();
    views[1].setGeoEntity(geoEntity);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    GeoTargetView.applyAll(views);

    edits = GeoTargetView.lockAll(views);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edits[0], i * 2);
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edits[1], i * 4);
    }

    GeoTargetView.applyAll(edits);

    try
    {
      tests[0] = GeoTarget.getView(edits[0].getTargetId());
      tests[1] = GeoTarget.getView(edits[1].getTargetId());

      assertEquals(tests[0].getTargetId(), edits[0].getTargetId());
      assertEquals(tests[0].getGeoEntity().getId(), edits[0].getGeoEntity().getId());
      assertEquals(tests[0].getTargetYear(), edits[0].getTargetYear());

      assertEquals(tests[1].getTargetId(), edits[1].getTargetId());
      assertEquals(tests[1].getGeoEntity().getId(), edits[1].getGeoEntity().getId());
      assertEquals(tests[1].getTargetYear(), edits[1].getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i * 2), GeoTargetView.class.getMethod("getTarget_" + i).invoke(tests[0]));
        assertEquals(new Integer(i * 4), GeoTargetView.class.getMethod("getTarget_" + i).invoke(tests[1]));
      }
    }
    finally
    {
      edits[0].deleteConcrete();
      edits[1].deleteConcrete();
    }
  }

  public void testSum() throws Exception
  {
    Integer year = 2009;

    GeoTargetView[] views = new GeoTargetView[2];

    views[0] = new GeoTargetView();
    views[0].setGeoEntity(geoEntity);
    views[0].setTargetYear(year);

    views[1] = new GeoTargetView();
    views[1].setGeoEntity(geoEntity);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      GeoTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    GeoTargetView.applyAll(views);
    GeoTargetView sum = GeoTargetView.sum(geoEntity, views);

    try
    {
      assertEquals(geoEntity.getId(), sum.getGeoEntity().getId());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i + i * 2), GeoTargetView.class.getMethod("getTarget_" + i).invoke(sum));
      }
    }
    finally
    {
      views[0].deleteConcrete();
      views[1].deleteConcrete();
    }
  }
}
