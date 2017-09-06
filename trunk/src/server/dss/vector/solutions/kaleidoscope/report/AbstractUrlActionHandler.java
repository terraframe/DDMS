package dss.vector.solutions.kaleidoscope.report;

import java.util.logging.Logger;

import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.IAction;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.script.IReportContext;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class AbstractUrlActionHandler extends HTMLActionHandler implements Reloadable
{
  /** logger */
  protected Logger        log = Logger.getLogger(AbstractUrlActionHandler.class.getName());

  private IReportDocument document;

  private long            pageNumber;

  public AbstractUrlActionHandler(IReportDocument document, long pageNumber)
  {
    this.document = document;
    this.pageNumber = pageNumber;
  }

  protected abstract String getDefaultFormat();

  /**
   * Get URL of the action.
   * 
   * @param actionDefn
   * @param context
   * @return URL
   */
  public String getURL(IAction action, IReportContext context)
  {
    if (action != null && action.getType() == IAction.ACTION_BOOKMARK)
    {
      return this.buildBookmarkAction(action, context);
    }

    return super.getURL(action, context);
  }

  public String buildBookmarkAction(IAction action, IReportContext context)
  {
    if (action.getReportName() != null)
    {
      StringBuffer buffer = new StringBuffer(this.buildDrillAction(action, context));
      this.appendBookmark(buffer, action.getBookmark());

      return buffer.toString();
    }
    else
    {
      StringBuffer buffer = new StringBuffer();
      buffer.append("#");
      buffer.append("report");
      buffer.append("/" + action.getBookmark());

      if (this.document != null)
      {
        long pageNumber = this.document.getPageNumber(action.getBookmark());

        if (this.pageNumber != pageNumber)
        {
          buffer.append("/" + pageNumber);
        }
      }

      return buffer.toString();
    }
  }
}
