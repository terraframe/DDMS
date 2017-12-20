package dss.vector.solutions.generator;
import java.util.HashMap;
import java.util.List;

import com.runwaysdk.business.Entity;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelImportProgressMonitorIF;

import dss.vector.solutions.ExcelImportHistory;


public class ExcelImportLegacyHistoryRecordingProgressMonitor implements ExcelImportProgressMonitorIF
{
  private int importCount = 0;
  
  private ExcelImportHistory history;
  
  public ExcelImportLegacyHistoryRecordingProgressMonitor(ExcelImportHistory history)
  {
    this.history = history;
  }

  @Override
  public void entityImported(Mutable arg0, HashMap<String, List<Entity>> arg1)
  {
    importCount++;
    
    history.appLock();
    history.setImportCount(importCount);
    history.apply();
  }

  @Override
  public void setCurrentRow(long row)
  {
    
  }

  @Override
  public void setTotalRows(long total)
  {
    history.appLock();
    history.setTotalRecords((int)total);
    history.apply();
  }

}
