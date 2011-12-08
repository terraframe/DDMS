package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.util.FileIO;

public class PostPatchSetup implements Runnable
{
  private int maxPermSize;

  public PostPatchSetup()
  {
    this.maxPermSize = 256;
  }

  @Override
  public void run()
  {
    try
    {
      File startup = new File(PostInstallSetup.DEFAULT_TOMCAT + "/bin/startup.bat");
      this.readAndReplace(startup, "-XX:MaxPermSize=\\d*M", "-XX:MaxPermSize=" + this.maxPermSize + "M");
    }
    catch (IOException e)
    {
      throw new ProgrammingErrorException(e);

    }
  }

  private void readAndReplace(File file, String regex, String replacement) throws IOException
  {
    String data = FileIO.readString(file);
    String replaced = data.replaceAll(regex, replacement);
    FileIO.write(file, replaced);
  }

  public static void main(String[] args)
  {
    new PostPatchSetup().run();
  }
}
