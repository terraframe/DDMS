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
