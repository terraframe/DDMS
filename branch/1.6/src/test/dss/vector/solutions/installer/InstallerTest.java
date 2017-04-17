package dss.vector.solutions.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.session.Request;

public class InstallerTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  @Request
  public void testSettingIdentifier() throws Exception
  {
    String installNumber = "0";

    // IMPORTANT: THE STATEMENT MUST BE THE SAME STATEMENT USED IN Installer.ins
    // TO UPDATE THE INSTALL IDENTIFIER. OTHERWISE THE TEST IS POINTLESS.
    String statement = "update property set property_value='" + installNumber + "' where property_name='SHORT_ID_OFFSET'";

    List<String> statements = new LinkedList<String>();

    statements.add(statement);

    try
    {
      int[] results = Database.executeBatch(statements);

      assertEquals(1, results.length);
      assertEquals(1, results[0]);
    }
    catch (Exception e)
    {
      fail("The sql in the Installer.ins will fail in trying to update the propertyname ");
    }
  }
}
