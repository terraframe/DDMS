package dss.vector.solutions;

import com.terraframe.mojo.dataaccess.io.UpdateVersion;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;

public class Revisioner
{
  @StartSession
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
