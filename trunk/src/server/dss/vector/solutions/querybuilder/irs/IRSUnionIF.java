package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public interface IRSUnionIF extends Reloadable
{
  static final String RESOURCE_TARGET_VIEW = "resourceTargetView";
  
  public static final String FLOAT = "float";
  public static final String TEXT = "text";
  public static final String VARCHAR = "varchar";
  public static final String DATE = "date";
  
  // get/set owning IRSQuery
  void setIRSQuery(IRSQB irsQuery);
  
  String from();
  String where();
}
