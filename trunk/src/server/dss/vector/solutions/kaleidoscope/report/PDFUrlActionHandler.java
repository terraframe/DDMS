package dss.vector.solutions.kaleidoscope.report;

import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.RenderOption;

import com.runwaysdk.generation.loader.Reloadable;

public class PDFUrlActionHandler extends AbstractUrlActionHandler implements Reloadable
{
  public PDFUrlActionHandler(IReportDocument document)
  {
    super(document, 0);
  }

  @Override
  protected String getDefaultFormat()
  {
    return RenderOption.OUTPUT_FORMAT_PDF;
  }

}
