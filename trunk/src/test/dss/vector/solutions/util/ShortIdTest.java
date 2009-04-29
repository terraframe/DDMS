package dss.vector.solutions.util;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.lang.Integer;
import java.util.Random;
import java.lang.Math;

import dss.vector.solutions.ShortId;


public class ShortIdTest extends TestCase
{


  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ShortIdTest.class);

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
      assertEquals(0, ShortId.fromBase30("0".toCharArray()));
      assertEquals(1, ShortId.fromBase30("1".toCharArray()));

      assertEquals(30, ShortId.fromBase30("10".toCharArray()));
      assertEquals(60, ShortId.fromBase30("20".toCharArray()));

      assertEquals(31, ShortId.fromBase30("11".toCharArray()));
      assertEquals(61, ShortId.fromBase30("21".toCharArray()));

      assertEquals(61, ShortId.fromBase30("0021".toCharArray()));

  }

  public void testToBase30() {
      assertEquals("0", ShortId.toBase30String(0));
      assertEquals("1", ShortId.toBase30String(1));

      assertEquals("10", ShortId.toBase30String(30));
      assertEquals("20", ShortId.toBase30String(60));

      assertEquals("11", ShortId.toBase30String(31));
      assertEquals("21", ShortId.toBase30String(61));

  }

  public void testExceptions() {

      try {
          ShortId.toBase30String(-1);
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("A");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("E");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("I");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("O");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("U");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("la;ss");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("23123ADJSDHF");
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30("SLTELSLSDF^D");
          fail();
      } catch (IllegalArgumentException expected) {}


  }

  public void testRoundTripBase30()
  {
      Random random = new Random();
      for(int i=0;i<10000;i++)
      {
          Integer j = new Integer(i);
          assertEquals(i,  ShortId.fromBase30(ShortId.toBase30String(i)));
          assertEquals(j.toString(),  ShortId.toBase30String(ShortId.fromBase30(j.toString())));

          long l = (long) Math.abs(random.nextInt());
          assertEquals(l,  ShortId.fromBase30(ShortId.toBase30String(l)));
          assertEquals(Long.toString(l),  ShortId.toBase30String(ShortId.fromBase30(Long.toString(l))));

          Long ll = new Long(Math.abs(random.nextLong()));
          assertEquals(ll.longValue(),  ShortId.fromBase30(ShortId.toBase30String(ll.longValue())));

      }
  }

  public void testLimits()
  {
      String max = "KBMTTCD1HD207";
      String max_plus_one = "KBMTTCD1HD208";
      String way_too_big = "KBMTTCD1HD20867812341235234";

      assertEquals(Long.MAX_VALUE,  ShortId.fromBase30(max));
      assertEquals(max,  ShortId.toBase30String(Long.MAX_VALUE));

      try {
          ShortId.fromBase30(max_plus_one);
          fail();
      } catch (IllegalArgumentException expected) {}

      try {
          ShortId.fromBase30(way_too_big);
          fail();
      } catch (IllegalArgumentException expected) {}

  }

}
