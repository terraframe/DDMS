package dss.vector.solutions.report;

import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.RenderOption;

import com.runwaysdk.generation.loader.Reloadable;

public class HTMLUrlActionHandler extends AbstractUrlActionHandler implements Reloadable
{
  public HTMLUrlActionHandler(IReportDocument document, String baseURL, String reportURL)
  {
    super(document, baseURL, reportURL);
  }

  @Override
  protected String getDefaultFormat()
  {
    return RenderOption.OUTPUT_FORMAT_HTML;
  }

}
