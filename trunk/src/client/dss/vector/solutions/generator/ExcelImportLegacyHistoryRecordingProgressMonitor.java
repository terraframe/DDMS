/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
