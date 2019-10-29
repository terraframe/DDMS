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
package dss.vector.solutions.util;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostInstallSetupTest
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
  public void testNoArguments()
  {
    try
    {
      PostInstallSetup.main(new String[] {});

      fail("Able to run the post-install setup without the proper arguments");
    }
    catch (Exception e)
    {
      // This is expected
    }
  }

  @Test
  public void testUpdateCSS()
  {
    PostInstallSetup.main(new String[] { "-aDDMS", "-n0", "-itrue", "-c", "-m/home/jsmethie/workspace/manager/", "-t/home/jsmethie/apache-tomcat-6.0.26/" });
  }
}
