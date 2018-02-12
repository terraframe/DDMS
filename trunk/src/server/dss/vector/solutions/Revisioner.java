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
package dss.vector.solutions;

import com.runwaysdk.dataaccess.io.UpdateVersion;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;

public class Revisioner
{
  @Request
  public static void main(String[] args)
  {
    go();
  }

  @Transaction
  private static void go()
  {
    long start = System.currentTimeMillis();
    // This undoes anything in my 999 schema
    UpdateVersion.main(new String[]
    {
      "doc/",
      "/version_gis.xsd",
      "9999999999999998"
    });

    // And this puts it back in
    UpdateVersion.main(new String[]
    {
      "doc/",
      "/version_gis.xsd",
      "9999999999999999"
    });
    long stop = System.currentTimeMillis();

    System.out.println("Execution time: " + (stop-start)/1000.0 + " seconds");
  }
}
