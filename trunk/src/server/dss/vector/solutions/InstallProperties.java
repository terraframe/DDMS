package dss.vector.solutions;

import java.util.Locale;
import java.util.ResourceBundle;

import com.runwaysdk.generation.loader.Reloadable;

public class InstallProperties implements Reloadable
{

  private static final ResourceBundle bundle             = ResourceBundle.getBundle("install", Locale
      .getDefault(), InstallProperties.class.getClassLoader());
  
  public static boolean isMaster()
  {
    return Boolean.parseBoolean(bundle.getString("master"));
  }
  
  /**
   * Checks if this node is the master node and throws an exception
   * if it is not.
   * 
   * @return
   */
  public static void validateMasterOperation()
  {
    if(!isMaster())
    {
      throw new MasterOperationException();
    }
  }
}
