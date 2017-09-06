package dss.vector.solutions.kaleidoscope.report;

import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.RenderOption;

import com.runwaysdk.generation.loader.Reloadable;

public class HTMLUrlActionHandler extends AbstractUrlActionHandler implements Reloadable
{
  public HTMLUrlActionHandler(IReportDocument document, long pageNumber)
  {
    super(document, pageNumber);
  }

  @Override
  protected String getDefaultFormat()
  {
    return RenderOption.OUTPUT_FORMAT_HTML;
  }

}
