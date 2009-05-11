package dss.vector.solutions.util;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.lang.Integer;
import java.util.Random;
import java.lang.Math;

import dss.vector.solutions.Base30;


public class Base30Test extends TestCase
{


  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Base30Test.class);

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

  public void testFromBase30() {
      assertEquals(0, Base30.fromBase30("0".toCharArray()));
      assertEquals(1, Base30.fromBase30("1".toCharArray()));

      assertEquals(30, Base30.fromBase30("10".toCharArray()));
      assertEquals(60, Base30.fromBase30("20".toCharArray()));

      assertEquals(31, Base30.fromBase30("11".toCharArray()));
      assertEquals(61, Base30.fromBase30("21".toCharArray()));

      assertEquals(61, Base30.fromBase30("0021".toCharArray()));

  }

  public void testToBase30() {
      assertEquals("0", Base30.toBase30String(0));
      assertEquals("1", Base30.toBase30String(1));

      assertEquals("10", Base30.toBase30String(30));
      assertEquals("20", Base30.toBase30String(60));

      assertEquals("11", Base30.toBase30String(31));
      assertEquals("21", Base30.toBase30String(61));

  }

  public void testExceptions() {

      try {
          Base30.toBase30String(-1);
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("A");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("E");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("I");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("O");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("U");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("la;ss");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("23123ADJSDHF");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30("SLTELSLSDF^D");
          fail();
      } catch (IllegalArgumentException expected) {}


  }

  public void testRoundTripBase30()
  {
      Random random = new Random();
      for(int i=0;i<10000;i++)
      {
          Integer j = new Integer(i);
          assertEquals(i,  Base30.fromBase30(Base30.toBase30String(i)));
          assertEquals(j.toString(),  Base30.toBase30String(Base30.fromBase30(j.toString())));

          long l = (long) Math.abs(random.nextInt());
          assertEquals(l,  Base30.fromBase30(Base30.toBase30String(l)));
          assertEquals(Long.toString(l),  Base30.toBase30String(Base30.fromBase30(Long.toString(l))));

          Long ll = new Long(Math.abs(random.nextLong()));
          assertEquals(ll.longValue(),  Base30.fromBase30(Base30.toBase30String(ll.longValue())));

      }
  }

  public void testLimits()
  {
      String max = "KBMTTCD1HD207";
      String max_plus_one = "KBMTTCD1HD208";
      String way_too_big = "KBMTTCD1HD20867812341235234";

      assertEquals(Long.MAX_VALUE,  Base30.fromBase30(max));
      assertEquals(max,  Base30.toBase30String(Long.MAX_VALUE));

      try {
          Base30.fromBase30(max_plus_one);
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          Base30.fromBase30(way_too_big);
          fail();
      } catch (IllegalArgumentException expected) {}

  }

}
