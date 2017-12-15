package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public interface ProgressMonitorIF extends Reloadable
{
  public void setFilename(String filename);
  
  public void setState(DataImportState state);
  
  public void setCurrentRow(double rowNum);
}
