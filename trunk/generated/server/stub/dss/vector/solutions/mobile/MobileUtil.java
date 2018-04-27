package dss.vector.solutions.mobile;

import dss.vector.solutions.odk.ODKFormExporter;

public class MobileUtil extends MobileUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2080435980;
  
  public MobileUtil()
  {
    super();
  }
  
  public static String export(java.lang.String mobileType)
  {
    return ODKFormExporter.export();
  }
}
