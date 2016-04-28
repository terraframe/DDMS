package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.AggregatedCaseView;

public class AggregatedCaseReferralListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String REFERRAL   = "Referral/Stock - ";

  private static final String REASON     = "Transfer Reason - ";

  private static final String DIAGNOSTIC = "Diagnostic Total - ";

  private static final String POSITIVE   = "Diagnostic Positive - ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term referral : TermRootCache.getRoots(AggregatedCaseView.getCaseStockReferralMd()))
    {
      extraColumns.add(new ExcelColumn(REFERRAL + referral.getTermId(), referral.getTermDisplayLabel().getValue()));
    }

    for (Term category : TermRootCache.getRoots(AggregatedCaseView.getCaseReferralsMd()))
    {
      extraColumns.add(new ExcelColumn(REASON + category.getTermId(), category.getTermDisplayLabel().getValue()));
    }

    for (Term patient : TermRootCache.getRoots(AggregatedCaseView.getCaseDiagnosticMd()))
    {
      String label = patient.getTermDisplayLabel().getValue();
      extraColumns.add(new ExcelColumn(DIAGNOSTIC + patient.getTermId(), label + " - Total tests"));
      extraColumns.add(new ExcelColumn(POSITIVE + patient.getTermId(), label + " - Total positive tests"));
    }
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
