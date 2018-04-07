/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public class LoggingProgressMonitor implements ProgressMonitorIF, Reloadable
{
  // private static Logger logger =
  // LoggerFactory.getLogger(ImportRunnable.class);

  private DataImportState state;

  private int             currentRow;

  private String          filename;

  private int             importCount;

  public LoggingProgressMonitor(String filename)
  {
    this.importCount = 0;
    this.currentRow = 0;
    this.setFilename(filename);
    this.setState(DataImportState.INITIAL);
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  public void setState(DataImportState state)
  {
    this.state = state;

    System.out.println("Spreadsheet importer entering state [" + state.toString() + "] on file [" + filename + "].");
  }

  public DataImportState getState()
  {
    return this.state;
  }

  public void setCurrentRow(int rowNum)
  {
    this.currentRow = rowNum;

    if (rowNum % 50 == 0)
    {
      System.out.println("Spreadsheet importer processing row [" + rowNum + "]");
    }
  }

  public int getCurrentRow()
  {
    return this.currentRow;
  }

  @Override
  public void setTotal(int total)
  {

  }

  @Override
  public void finished()
  {

  }

  @Override
  public void entityImported(String id)
  {
    this.importCount++;
  }

  @Override
  public int getImportCount()
  {
    return this.importCount;
  }
}
