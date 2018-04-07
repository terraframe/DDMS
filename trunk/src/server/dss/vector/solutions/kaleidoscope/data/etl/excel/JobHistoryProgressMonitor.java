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
package dss.vector.solutions.kaleidoscope.data.etl.excel;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportHistory;
import dss.vector.solutions.kaleidoscope.data.etl.ProgressMonitorIF;

public class JobHistoryProgressMonitor implements ProgressMonitorIF, Reloadable
{
  private int importCount = 0;
  
  private ExcelImportHistory history;
  
  public JobHistoryProgressMonitor(ExcelImportHistory history)
  {
    this.history = history;
  }
  
  @Override
  public void entityImported(String id)
  {
    importCount++;
    
    history.appLock();
    history.setImportCount(importCount);
    history.apply();
  }  
  
  @Override
  public int getImportCount()
  {
    return this.importCount;
  }
  
  @Override
  public void setFilename(String filename)
  {
    // Intentionally empty
  }

//  @Override
//  public void setState(DataImportState state)
//  {
//    // Intentionally empty
//  }

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
  
  public ExcelImportHistory getHistory()
  {
    return history;
  }

}
