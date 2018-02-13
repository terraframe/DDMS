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
package dss.vector.solutions.gis.shapefile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Test;

import com.runwaysdk.util.FileIO;

import dss.vector.solutions.gis.GISImportLogger;


public class ShapefileImportLoggerTest
{
  public static String FILE = "test/import.log";

  @After
  public void tearDown() throws Exception
  {
    FileIO.deleteFile(new File(FILE));
  }

  /**
   * Ensure that a log file is created on the file system and the contents of
   * the file are logged correctly.
   * 
   * @throws Exception
   */
  @Test
  public void testLog() throws Exception
  {
    File file = new File(FILE);

    GISImportLogger logger = new GISImportLogger(file);

    logger.log("123", new RuntimeException("This is a test log"));

    logger.close();

    assertTrue(logger.hasLogged());
    assertTrue(file.exists());

    BufferedReader reader = new BufferedReader(new FileReader(file));

    assertTrue(reader.ready());

    while (reader.ready())
    {
      assertEquals("123 : This is a test log", reader.readLine());
    }
  }

  /**
   * Ensure that the logger doesn't create file if nothing is logged
   * 
   * @throws Exception
   */
  @Test
  public void testEmptyLog() throws Exception
  {
    File file = new File(FILE);

    GISImportLogger logger = new GISImportLogger(file);
    logger.close();

    assertFalse(logger.hasLogged());
    assertFalse(file.exists());
  }
}
