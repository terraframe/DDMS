package dss.vector.solutions.util.yui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.runwaysdk.ClientSession;
import com.runwaysdk.DoNotWeave;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.TestConstants;
import dss.vector.solutions.entomology.InfectionAssayViewDTO;

public class YUIColumnTest extends TestCase implements DoNotWeave
{
  protected static ClientSession   systemSession;

  protected static ClientRequestIF systemRequest;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(YUIColumnTest.class);

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
    systemSession = WebClientSession.createUserSession("SYSTEM", TestConstants.PASSWORD, new Locale[] { Locale.US });
    systemRequest = systemSession.getRequest();
  }

  protected static void classTearDown()
  {
    systemSession.logout();
  }

  private Map<String, ColumnSetup> getColumns(String[] keys)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < 2 ? new ColumnSetup(true, false) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { InfectionAssayViewDTO.CONCRETEID, InfectionAssayViewDTO.COLLECTION, InfectionAssayViewDTO.MOSQUITOID, InfectionAssayViewDTO.SPECIES, InfectionAssayViewDTO.IDENTMETHOD, InfectionAssayViewDTO.SEX, InfectionAssayViewDTO.PARASITE, InfectionAssayViewDTO.TESTMETHOD, InfectionAssayViewDTO.INFECTED, InfectionAssayViewDTO.NUMBERTESTED, InfectionAssayViewDTO.NUMBERPOSITIVE };

    this.upperFirstCharacter(keys);

    return keys;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = CommonGenerationUtil.upperFirstCharacter(array[i]);
    }
  }

  public void testDynamicTermMetadata()
  {
    InfectionAssayViewDTO view = new InfectionAssayViewDTO(systemRequest);

    String[] keys = this.getKeys();

    Map<String, ColumnSetup> map = this.getColumns(keys);

//    DynamicTermDataGrid generator = new DynamicTermDataGrid(new TermDataGrid(view, map, keys, new TermSetup()), ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTMETHOD, "");
//
//    System.out.println(generator.getMetadata());
  }

  public void testDynamicTermColumns()
  {
    InfectionAssayViewDTO view = new InfectionAssayViewDTO(systemRequest);

    String[] keys = this.getKeys();

    Map<String, ColumnSetup> map = this.getColumns(keys);

//    DynamicTermDataGrid generator = new DynamicTermDataGrid(new TermDataGrid(view, map, keys, new TermSetup()), ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTMETHOD, "");
//
//    List<String> columns = generator.getColumns();
//
//    for(String column : columns)
//    {
//      System.out.println(column);
//    }
  }
}
