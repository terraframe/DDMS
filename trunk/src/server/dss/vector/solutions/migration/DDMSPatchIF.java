package dss.vector.solutions.migration;

public interface DDMSPatchIF
{
  public Integer getPatchVersion();
  
  public void doIt();
  
  public void undoIt();
}
