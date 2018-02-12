/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
