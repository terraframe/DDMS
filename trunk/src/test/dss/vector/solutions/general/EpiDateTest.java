package dss.vector.solutions.general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemException;
import com.terraframe.mojo.ProblemIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.surveillance.PeriodType;

public class EpiDateTest extends TestCase
{
  SimpleDateFormat sys = new SimpleDateFormat("yyyy-mm-dd");
  SimpleDateFormat full = new SimpleDateFormat("EEE, d MMM yyyy");
  SimpleDateFormat mf = new SimpleDateFormat("MMM");
  SimpleDateFormat df = new SimpleDateFormat("dd");

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(EpiDateTest.class);

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

  public void testEpiWeek() throws ParseException
  {
    int offset = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    GregorianCalendar d = new GregorianCalendar();
    d.clear();
    d.set(Calendar.DAY_OF_WEEK,offset);
    System.out.println("offset = " + df.format(d.getTime()));
    Integer testYear = 1997;
    while (testYear < 2011)
    {
      System.out.println("***********************************************\n SYSTEM YEAR = " + testYear);
      System.out.println("EW Mon S  M  T  W Th  F  S  Mon");
      for (Integer i = 0; i < 4; i++)
      {
        EpiDate epiDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, i, testYear);
        assertTrue(epiDate.getStartDate().before(epiDate.getEndDate()));
        GregorianCalendar week = new GregorianCalendar();
        week.clear();
        week.setTime(epiDate.getStartDate());
        System.out.print(Integer.toString(i) + "= " + mf.format(week.getTime()) + " ");
        for (int j = 0; j < 7; j++)
        {
          System.out.print(df.format(week.getTime()) + " ");
          week.add(Calendar.DAY_OF_WEEK, 1);
        }
        System.out.println(mf.format(week.getTime()) + " ");
      }
      testYear++;
    }
  }

  /*
   * public void testDarrellEpiWeek() throws ParseException { SimpleDateFormat
   * sys = new SimpleDateFormat("yyyy-mm-dd"); SimpleDateFormat full = new
   * SimpleDateFormat("EEE, d MMM yyyy"); SimpleDateFormat mf = new
   * SimpleDateFormat("MMM"); SimpleDateFormat df = new SimpleDateFormat("dd");
   *
   * int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE,
   * PropertyInfo.EPI_START_DAY);
   *
   * System.out.println("startDay = " + startDay);
   * System.out.println("EW       Mon S M T W Th F S Mon");
   *
   * int testYear = 1997; while (testYear < 2014) {
   * System.out.println("***********************************************\n YEAR = "
   * + testYear); System.out.println("EW Mon S  M  T  W Th  F  S  Mon");
   * GregorianCalendar cal = new GregorianCalendar(); cal.clear();
   * cal.setFirstDayOfWeek(startDay); // The last day of the first epi week is
   * the first SAT in Jan provided it is // 4 or more days in.
   * cal.setMinimalDaysInFirstWeek(4 + startDay); cal.set(Calendar.YEAR,
   * testYear); cal.set(Calendar.WEEK_OF_YEAR, 1);
   *
   * for (int i = 0; i < 4; i++) { System.out.print(Integer.toString(i) + "= " +
   * mf.format(cal.getTime()) + " "); for (int j = 0; j < 7; j++) {
   * cal.add(Calendar.DAY_OF_WEEK, 1); System.out.print(df.format(cal.getTime())
   * + " "); } System.out.println(mf.format(cal.getTime()) + " "); } testYear++;
   * }
   *
   *
   * }
   */
  public void testEpiQuarter()
  {
    Integer period = 2;
    Integer testYear = 2009;
    EpiDate epiDate = EpiDate.getInstanceByPeriod(PeriodType.QUARTER, period, testYear);
    Date startDate = epiDate.getStartDate();
    Date endDate = epiDate.getEndDate();
    System.out.println("\n\nP" + period + " Start=" + full.format(startDate) + " End=" + full.format(endDate));
    EpiDate epiDate2 = EpiDate.getInstanceByDate(startDate, endDate);
    System.out.println("Type=" + epiDate2.getType().toString() + " Period = " + epiDate2.getPeriod() + " Year=" + epiDate2.getEpiYear());
    assertEquals(epiDate.getType(), epiDate2.getType());
    assertEquals(epiDate.getPeriod(), epiDate2.getPeriod());
    assertEquals(epiDate.getEpiYear(), epiDate2.getEpiYear());
  }

  public void testEpiMonth()
  {
    Integer period = 7;
    Integer testYear = 2009;
    EpiDate epiDate = EpiDate.getInstanceByPeriod(PeriodType.MONTH, period, testYear);
    Date startDate = epiDate.getStartDate();
    Date endDate = epiDate.getEndDate();
    System.out.println("\n\nP" + period + " Start =  " + full.format(startDate) + " End=" + full.format(endDate));
    EpiDate epiDate2 = EpiDate.getInstanceByDate(startDate, endDate);
    System.out.println("Type=" + epiDate2.getType().toString() + " Period=" + epiDate2.getPeriod() + " Year=" + epiDate2.getEpiYear());
    assertEquals(epiDate.getType(), epiDate2.getType());
    assertEquals(epiDate.getPeriod(), epiDate2.getPeriod());
    assertEquals(epiDate.getEpiYear(), epiDate2.getEpiYear());
  }

}
