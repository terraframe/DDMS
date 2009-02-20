package mdss.entomology;

import java.io.OutputStreamWriter;

import junit.framework.TestCase;

public class RTest extends TestCase
{
  public void testR()
  {
    try
    {
//      String arg = "--vanilla --slave >1";
//
//      Process p = new ProcessBuilder("R", arg).start();
//      OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
//      writer.write("a <-- 5;");
//      writer.write("a");
//      writer.write("q()");
//      writer.flush();

//      Process p = new ProcessBuilder("ls").start();

//      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));      
//      System.out.println("Done Writing");
//      System.out.println(reader.readLine());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
//  public void testJRI()
//  {
//    try
//    {
//      Rengine engine = new Rengine();      
//
//      long xp1 = engine.rniPutString("hello");
//      engine.rniAssign("a", xp1, 0);
//      long rniParse = engine.rniParse("a", 1);
//      
//      System.out.println(rniParse);
//    }
//    catch (Exception e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
}