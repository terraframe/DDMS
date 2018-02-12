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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class SymptomListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String SYMPTOM = "symptom ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term term : TermRootCache.getRoots(IndividualInstance.getSymptomMd()))
    {
      extraColumns.add(new ExcelColumn(SYMPTOM + term.getTermId(), term.getTermDisplayLabel().getValue()));
    }
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    IndividualCaseExcelView individualCase = (IndividualCaseExcelView) instance;

    for (Term term : TermRootCache.getRoots(IndividualInstance.getSymptomMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(SYMPTOM + term.getTermId()))
        {
          if (ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            individualCase.addSymptom(term);
          }
        }
      }
    }
  }

}
