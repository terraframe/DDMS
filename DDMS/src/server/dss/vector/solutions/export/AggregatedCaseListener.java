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
package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

public class AggregatedCaseListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TREATMENT = "treatment ";
  private static final String STOCK = "stock ";
  private static final String METHOD = "method ";
  private static final String DIAGNOSTIC = "diagnostic ";
  private static final String POSITIVE = "positive ";
  private static final String REFERRAL = "referral ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(Workbook workbook)
  {
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    AggregatedCaseExcelView aggregatedCase = (AggregatedCaseExcelView) instance;
    
  }
}
