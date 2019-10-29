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

import java.io.File;

import com.runwaysdk.session.Request;

/**
 * Use this class to import a directory of ontologies. Assumes the file:
 * <code>ro.obo</code> is in the directory, as it defines relationships used by
 * all of the ontologies.
 * 
 * @author nathan
 * 
 */
public class OntologyImportManager
{
  /**
   * @param args
   */
  @Request
  public static void main(String[] args) throws Exception
  {
    if (args.length != 3)
    {
      String errMsg = "Incorrect args!  Takes three arguments: \n" + "1) path and filename of ro.obo\n" + "2) path and filename of ontology file\n" + "3) title of ontology \n";
      System.err.println(errMsg);
    }

    File roOboFile = new File(args[0]);
    if (!roOboFile.exists())
    {
      System.err.println("Error: file [" + roOboFile.toString() + "] does not exist.");
    }

    File oboFile = new File(args[1]);
    if (!oboFile.exists())
    {
      System.err.println("Error: file [" + oboFile.toString() + "] does not exist.");
    }

    String ontologyTitle = args[2];

    importStandardRelationshipDefs(roOboFile);
    importOntologies(oboFile, ontologyTitle);
  }

  private static void importStandardRelationshipDefs(File roOboFile) throws Exception
  {
    OntologyImporter oi = new OntologyImporter(roOboFile.getAbsolutePath());
    oi.setDisplayStatusToSysOut(true);
    oi.importOntology();
  }

  private static void importOntologies(File oboFile, String ontologyTitle) throws Exception
  {
    OntologyImporter oi = new OntologyImporter(oboFile.getAbsolutePath(), ontologyTitle);
    oi.setDisplayStatusToSysOut(true);
    oi.importOntology();
  }

}
