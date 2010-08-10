package dss.vector.solutions.util;

import java.io.File;

import com.runwaysdk.session.Request;

/**
 * Use this class to import a directory of ontologies.  Assumes the file:
 * <code>ro.obo</code> is in the directory, as it defines relationships used
 * by all of the ontologies.
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
      String errMsg =
        "Incorrect args!  Takes three arguments: \n"+
        "1) path and filename of ro.obo\n"+
        "2) path and filename of ontology file\n"+
        "3) title of ontology \n";
      System.err.println(errMsg);
    }

    File roOboFile = new File(args[0]);
    if (!roOboFile.exists())
    {
      System.err.println("Error: file ["+roOboFile.toString()+"] does not exist.");
    }

    File oboFile = new File(args[1]);
    if (!oboFile.exists())
    {
      System.err.println("Error: file ["+oboFile.toString()+"] does not exist.");
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
