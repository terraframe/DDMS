/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.data.etl.excel;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.data.etl.ColumnType;

public class CountSheetHandler implements SheetHandler, Reloadable
{
  private int rowNum;

  public CountSheetHandler()
  {
    this.rowNum = 0;
  }

  @Override
  public void startSheet(String sheetName)
  {
  }

  @Override
  public void endSheet()
  {
  }

  @Override
  public void startRow(int rowNum)
  {
    this.rowNum = rowNum;
  }

  @Override
  public void endRow()
  {
  }

  @Override
  public void cell(String cellReference, String contentValue, String formattedValue, ColumnType cellType)
  {
  }

  @Override
  public void headerFooter(String text, boolean isHeader, String tagName)
  {
  }

  public int getRowNum()
  {
    return rowNum;
  }
  
  @Override
  public void setDatasetProperty(String dataset)
  {
  }
}
