package dss.vector.solutions.irs;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResourceTargetTest extends TestCase
{
  public static Targeter targeter;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ResourceTargetTest.class);

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
    targeter.delete();
  }

  protected static void classSetUp()
  {
    targeter = new SprayTeam();
    targeter.apply();
  }

  public void testCreate() throws Exception
  {
    Integer year = 2009;

    ResourceTarget target = new ResourceTarget();
    target.setTargeter(targeter);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();

    try
    {
      ResourceTarget test = ResourceTarget.get(target.getId());

      assertEquals(test.getTargeter().getId(), target.getTargeter().getId());
      assertEquals(test.getTargetYear(), target.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), ResourceTarget.class.getMethod("getTarget_" + i).invoke(test));
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

    ResourceTarget target = new ResourceTarget();
    target.setTargeter(targeter);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();

    ResourceTarget edit = ResourceTarget.lock(target.getId());

    for (int i = 0; i < 53; i++)
    {
      ResourceTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(edit, 2 * i);
    }

    edit.apply();

    try
    {
      ResourceTarget test = ResourceTarget.get(edit.getId());

      assertEquals(test.getTargeter().getId(), edit.getTargeter().getId());
      assertEquals(test.getTargetYear(), edit.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(2 * i), ResourceTarget.class.getMethod("getTarget_" + i).invoke(edit));
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

    ResourceTarget target = new ResourceTarget();
    target.setTargeter(targeter);
    target.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTarget.class.getMethod("setTarget_" + i, Integer.class).invoke(target, i);
    }

    target.apply();
    target.delete();

    try
    {
      ResourceTarget.get(target.getId());

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

    ResourceTargetView view = new ResourceTargetView();
    view.setTargeter(targeter);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    try
    {
      ResourceTargetView test = ResourceTarget.getView(view.getTargetId());

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getTargeter().getId(), view.getTargeter().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(test));
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

    ResourceTargetView view = new ResourceTargetView();
    view.setTargeter(targeter);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    ResourceTargetView edit = view.lock();

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edit, 2 * i);
    }

    edit.apply();

    try
    {
      ResourceTargetView test = ResourceTarget.getView(view.getTargetId());

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getTargeter().getId(), view.getTargeter().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(2 * i), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(test));
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

    ResourceTargetView view = new ResourceTargetView();
    view.setTargeter(targeter);
    view.setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(view, i);
    }

    view.apply();

    try
    {
      ResourceTargetView test = ResourceTarget.searchByTargeterAndYear(targeter, year);

      assertEquals(test.getTargetId(), view.getTargetId());
      assertEquals(test.getTargeter().getId(), view.getTargeter().getId());
      assertEquals(test.getTargetYear(), view.getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(test));
      }
    }
    finally
    {
      view.deleteConcrete();
    }
  }

  public void testEmptySearch() throws Exception
  {
    Integer year = 2009;

    ResourceTargetView test = ResourceTarget.searchByTargeterAndYear(targeter, year);

    assertEquals(test.getTargetId(), "");
    assertEquals(test.getTargeter().getId(), targeter.getId());
  }

  public void testApplyAll() throws Exception
  {
    Integer year = 2009;

    ResourceTargetView[] views = new ResourceTargetView[2];
    ResourceTargetView[] tests = new ResourceTargetView[2];

    views[0] = new ResourceTargetView();
    views[0].setTargeter(targeter);
    views[0].setTargetYear(year);

    views[1] = new ResourceTargetView();
    views[1].setTargeter(targeter);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    ResourceTargetView.applyAll(views);

    try
    {
      tests[0] = ResourceTarget.getView(views[0].getTargetId());
      tests[1] = ResourceTarget.getView(views[1].getTargetId());

      assertEquals(tests[0].getTargetId(), views[0].getTargetId());
      assertEquals(tests[0].getTargeter().getId(), views[0].getTargeter().getId());
      assertEquals(tests[0].getTargetYear(), views[0].getTargetYear());

      assertEquals(tests[1].getTargetId(), views[1].getTargetId());
      assertEquals(tests[1].getTargeter().getId(), views[1].getTargeter().getId());
      assertEquals(tests[1].getTargetYear(), views[1].getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(tests[0]));
        assertEquals(new Integer(i * 2), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(tests[1]));
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

    ResourceTargetView[] views = new ResourceTargetView[2];
    ResourceTargetView[] edits = new ResourceTargetView[2];
    ResourceTargetView[] tests = new ResourceTargetView[2];

    views[0] = new ResourceTargetView();
    views[0].setTargeter(targeter);
    views[0].setTargetYear(year);

    views[1] = new ResourceTargetView();
    views[1].setTargeter(targeter);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    ResourceTargetView.applyAll(views);

    edits = ResourceTargetView.lockAll(views);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edits[0], i * 2);
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(edits[1], i * 4);
    }

    ResourceTargetView.applyAll(edits);

    try
    {
      tests[0] = ResourceTarget.getView(edits[0].getTargetId());
      tests[1] = ResourceTarget.getView(edits[1].getTargetId());

      assertEquals(tests[0].getTargetId(), edits[0].getTargetId());
      assertEquals(tests[0].getTargeter().getId(), edits[0].getTargeter().getId());
      assertEquals(tests[0].getTargetYear(), edits[0].getTargetYear());

      assertEquals(tests[1].getTargetId(), edits[1].getTargetId());
      assertEquals(tests[1].getTargeter().getId(), edits[1].getTargeter().getId());
      assertEquals(tests[1].getTargetYear(), edits[1].getTargetYear());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i * 2), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(tests[0]));
        assertEquals(new Integer(i * 4), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(tests[1]));
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

    ResourceTargetView[] views = new ResourceTargetView[2];

    views[0] = new ResourceTargetView();
    views[0].setTargeter(targeter);
    views[0].setTargetYear(year);

    views[1] = new ResourceTargetView();
    views[1].setTargeter(targeter);
    views[1].setTargetYear(year);

    for (int i = 0; i < 53; i++)
    {
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[0], i);
      ResourceTargetView.class.getMethod("setTarget_" + i, Integer.class).invoke(views[1], i * 2);
    }

    ResourceTargetView.applyAll(views);
    ResourceTargetView sum = ResourceTargetView.sum(targeter, views);

    try
    {
      assertEquals(targeter.getId(), sum.getTargeter().getId());

      for (int i = 0; i < 53; i++)
      {
        assertEquals(new Integer(i + i * 2), ResourceTargetView.class.getMethod("getTarget_" + i).invoke(sum));
      }
    }
    finally
    {
      views[0].deleteConcrete();
      views[1].deleteConcrete();
    }
  }

}
