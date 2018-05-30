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

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.AggregatedCaseView;

public class AggregatedCaseReferralListener extends AbstractExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String REFERRAL   = "Referral/Stock - ";

  private static final String REASON     = "Transfer Reason - ";

  private static final String DIAGNOSTIC = "Diagnostic Total - ";

  private static final String POSITIVE   = "Diagnostic Positive - ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    this.addGridColumns(extraColumns, AggregatedCaseView.getCaseStockReferralMd(), REFERRAL);
    this.addGridColumns(extraColumns, AggregatedCaseView.getCaseReferralsMd(), REASON);
    this.addGridColumns(extraColumns, AggregatedCaseView.getCaseDiagnosticMd(), DIAGNOSTIC, " - Total tests", "total");
    this.addGridColumns(extraColumns, AggregatedCaseView.getCaseDiagnosticMd(), POSITIVE, " - Total positive tests", "positive");
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    AggregatedCaseReferralsExcelView aggregatedCase = (AggregatedCaseReferralsExcelView) instance;

    for (Term term : TermRootCache.getRoots(AggregatedCaseView.getCaseStockReferralMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(REFERRAL + term.getTermId()))
        {
          aggregatedCase.addReferral(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }

    for (Term term : TermRootCache.getRoots(AggregatedCaseView.getCaseReferralsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(REASON + term.getTermId()))
        {
          aggregatedCase.addReason(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }

    for (Term term : TermRootCache.getRoots(AggregatedCaseView.getCaseDiagnosticMd()))
    {
      Integer amount = null;
      Integer amountPositive = null;

      for (ExcelColumn column : extraColumns)
      {
        String attributeName = column.getAttributeName();
        String termId = term.getTermId();
        if (attributeName.equals(DIAGNOSTIC + termId))
        {
          amount = ExcelUtil.getInteger(row.getCell(column.getIndex()));
        }
        if (attributeName.equals(POSITIVE + termId))
        {
          amountPositive = ExcelUtil.getInteger(row.getCell(column.getIndex()));
        }
      }

      // Don't add any associations if either attribute is unspecified
      if (amount != null && amountPositive != null)
      {
        aggregatedCase.addDiagnostic(term, amount, amountPositive);
      }
    }
  }
}
