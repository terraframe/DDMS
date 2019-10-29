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
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class AggregatedITNListener extends AbstractExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String SERVICES     = "Service ";

  private static final String TARGETGROUPS = "Target group ";

  private static final String ITNTYPE      = "ITN type ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    this.addGridColumns(extraColumns, ITNDataView.getDisplayServicesMd(), SERVICES, ITNService.getAmountMd());
    this.addGridColumns(extraColumns, ITNDataView.getDisplayTargetGroupsMd(), TARGETGROUPS, ITNTargetGroup.getAmountMd());
    this.addGridColumns(extraColumns, ITNDataView.getDisplayNetsMd(), ITNTYPE, ITNNet.getAmountMd());    
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
            Integer amount = ExcelUtil.getInteger(cell);
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
            Integer amount = ExcelUtil.getInteger(cell);
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
            Integer amount = ExcelUtil.getInteger(cell);
            aggregatedITN.addITNType(term, amount);
          }
        }
      }
    }
  }
}
