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
package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.IPTANCVisit;
import dss.vector.solutions.intervention.monitor.IPTDose;
import dss.vector.solutions.intervention.monitor.IPTPatients;
import dss.vector.solutions.intervention.monitor.IPTTreatment;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class AggregatedIPTListener extends AbstractExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String PATIENTS  = "patient ";

  private static final String ANCVISITS = "ANC visit ";

  private static final String DOSES     = "dose ";

  private static final String TREATMENT = "treatment ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    this.addGridColumns(extraColumns, AggregatedIPTView.getDisplayPatientsMd(), PATIENTS, IPTPatients.getAmountMd());
    this.addGridColumns(extraColumns, AggregatedIPTView.getDisplayVisitsMd(), ANCVISITS, IPTANCVisit.getAmountMd());
    this.addGridColumns(extraColumns, AggregatedIPTView.getDisplayDoseMd(), DOSES, IPTDose.getAmountMd());
    this.addGridColumns(extraColumns, AggregatedIPTView.getDisplayTreatmentsMd(), TREATMENT, IPTTreatment.getAmountMd());
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    AggregatedIPTExcelView aggregatedIPT = (AggregatedIPTExcelView) instance;

    for (Term term : TermRootCache.getRoots(AggregatedIPTView.getDisplayPatientsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(PATIENTS + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedIPT.addPatient(term, amount);
          }
        }
      }
    }

    for (Term term : TermRootCache.getRoots(AggregatedIPTView.getDisplayVisitsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(ANCVISITS + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedIPT.addVisit(term, amount);
          }
        }
      }
    }

    for (Term term : TermRootCache.getRoots(AggregatedIPTView.getDisplayDoseMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DOSES + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedIPT.addDose(term, amount);
          }
        }
      }
    }

    for (Term term : TermRootCache.getRoots(AggregatedIPTView.getDisplayTreatmentsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TREATMENT + term.getTermId()))
        {
          Cell cell = row.getCell(column.getIndex());
          if (cell != null)
          {
            Integer amount = new Double(cell.getNumericCellValue()).intValue();
            aggregatedIPT.addTreatment(term, amount);
          }
        }
      }
    }
  }
}
