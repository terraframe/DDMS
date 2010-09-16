package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public interface IRSUnionIF extends Reloadable
{
  static final String RESOURCE_TARGET_VIEW = "resourceTargetView";
  
  public static final String FLOAT = "float";
  public static final String TEXT = "text";
  public static final String DATE = "date";
  
  // get/set owning IRSQuery
  void setIRSQuery(IRSQuery irsQuery);
  
  String from();
  String where();
}
