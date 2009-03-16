package dss.vector.solutions.entomology;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.controller.DateParseException;

import dss.vector.solutions.util.DateConverter;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConverterTest extends TestCase
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ConverterTest.class);

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
  
  public void testUSConvert() throws ParseException
  {
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);    
    Date test = format.parse(format.format(new Date()));
    
    Date date = (Date) new DateConverter("").parse(format.format(test), Locale.US);
    
    assertEquals(test, date);
  }
  
  public void testInvalidDate()
  {
    try
    {
      new DateConverter("").parse("12.23.1992 -1", Locale.US);
      
      fail("Able to parse an invalid date");
    }
    catch(ProblemExceptionDTO dto)
    {
      List<ProblemDTOIF> p = dto.getProblems();
      
      assertEquals(1, p.size());
      assertTrue(p.get(0) instanceof DateParseException);
    }    
  }
  
  public void testFRConvert() throws ParseException
  {
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);    
    Date test = format.parse(format.format(new Date()));
    
    Date date = (Date) new DateConverter("").parse(format.format(test), Locale.FRENCH);
    
    assertEquals(test, date);
  }
}
