package dss.vector.solutions;

import com.terraframe.mojo.dataaccess.io.UpdateVersion;

public class Revisioner
{
  public static void main(String[] args)
  {
    long start = System.currentTimeMillis();
    // This undoes anything in my 999 schema
    UpdateVersion.main(new String[]
    {
      "doc/",
      "/version_gis.xsd",
      "0001240779112298"
    });

    // And this puts it back in
    UpdateVersion.main(new String[]
    {
      "doc/",
      "/version_gis.xsd",
      "0001240779112299"
    });
    long stop = System.currentTimeMillis();

    System.out.println("Execution time: " + (stop-start)/1000.0 + " seconds");
  }
}
