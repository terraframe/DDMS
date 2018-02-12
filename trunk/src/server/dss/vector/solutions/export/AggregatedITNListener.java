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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class AggregatedITNListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String SERVICES     = "Service ";

  private static final String TARGETGROUPS = "Target group ";

  private static final String ITNTYPE      = "ITN type ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term grid : TermRootCache.getRoots(ITNDataView.getDisplayServicesMd()))
    {
      String amount = MdAttribute.get(ITNService.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(SERVICES + grid.getTermId(), grid.getName().toString() + " " + amount));
    }

    for (Term grid : TermRootCache.getRoots(ITNDataView.getDisplayTargetGroupsMd()))
    {
      String amount = MdAttribute.get(ITNTargetGroup.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(TARGETGROUPS + grid.getTermId(), grid.getName().toString() + " " + amount));
    }

    for (Term grid : TermRootCache.getRoots(ITNDataView.getDisplayNetsMd()))
    {
      String amount = MdAttribute.get(ITNNet.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(ITNTYPE + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    AggregatedITNExcelView aggregatedITN = (AggregatedITNExcelView) instance;

    for (Term term : TermRootCache.getRoots(ITNDataView.getDisplayServicesMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(SERVICES + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedITN.addService(term, amount);
          }
        }
      }
    }

    for (Term term : TermRootCache.getRoots(ITNDataView.getDisplayTargetGroupsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TARGETGROUPS + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedITN.addTargetGroup(term, amount);
          }
        }
      }
    }

    for (Term term : TermRootCache.getRoots(ITNDataView.getDisplayNetsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(ITNTYPE + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedITN.addITNType(term, amount);
          }
        }
      }
    }
  }
}
