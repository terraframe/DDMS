package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public interface ProgressMonitorIF extends Reloadable
{
  public void setFilename(String filename);

  public void setState(DataImportState state);

  public void setTotal(int total);

  public void setCurrentRow(int rowNum);

  public void finished();
  
  public void entityImported(TargetDefinitionIF entity);
}
