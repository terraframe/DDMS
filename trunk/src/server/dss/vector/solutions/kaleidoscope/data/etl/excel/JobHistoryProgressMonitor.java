package dss.vector.solutions.kaleidoscope.data.etl.excel;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportHistory;
import dss.vector.solutions.kaleidoscope.data.etl.DataImportState;
import dss.vector.solutions.kaleidoscope.data.etl.ProgressMonitorIF;
import dss.vector.solutions.kaleidoscope.data.etl.TargetDefinitionIF;

public class JobHistoryProgressMonitor implements ProgressMonitorIF, Reloadable
{
  private int importCount = 0;
  
  private ExcelImportHistory history;
  
  public JobHistoryProgressMonitor(ExcelImportHistory history)
  {
    this.history = history;
  }
  
  @Override
  public void entityImported(TargetDefinitionIF entity)
  {
    importCount++;
    
    history.appLock();
    history.setImportCount(importCount);
    history.apply();
  }
  
  @Override
  public void setFilename(String filename)
  {
    // Intentionally empty
  }

  @Override
  public void setState(DataImportState state)
  {
    // Intentionally empty
  }

  @Override
  public void setTotal(int total)
  {
    history.appLock();
    history.setTotalRecords(total);
    history.apply();
  }

  @Override
  public void setCurrentRow(int rowNum)
  {
    // Intentionally empty
  }

  @Override
  public void finished()
  {
    // Intentionally empty
  }

}
