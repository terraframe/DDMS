import java.io.File;
import java.io.IOException;

import com.terraframe.mojo.dataaccess.io.dataDefinition.SAXImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;


public class OBOUtil
{
  /**
   * @param args
   * @throws IOException 
   */
  @StartSession
  public static void main(String[] args) throws IOException
  {
    importTypeDefs(args[0]);
  }

  public static String rootPackageName = "org.obofoundry";

  @Transaction
  public static void importTypeDefs(String xmlFile) throws IOException
  {
    SAXImporter.runImport(new File(xmlFile)); 
  }

  protected static String getAttributeName(String line)
  {
    int delimiterIndex = line.indexOf(":");
    
    if (delimiterIndex == -1)
    {
      return "";
    }
    
    return line.substring(0, delimiterIndex).trim();
  }

  protected static String getAttributeValue(String line)
  {
    int delimiterIndex = line.indexOf(":");

    if (delimiterIndex == -1)
    {
      return "";
    }

    return line.substring(delimiterIndex+1, line.length()).trim();
  }


}


