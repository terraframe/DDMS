package csu.mrc.ivcc.mdss.report;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

import csu.mrc.ivcc.mdss.util.BirtEngine;

public class ReportController extends ReportControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236706138416L;

  public ReportController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void report(String reportName) throws IOException, ServletException
  {
    // get report name and launch the engine
//    resp.setContentType("text/html");
    resp.setContentType( "application/pdf" );
    resp.setHeader ("Content-Disposition","inline; filename=test.pdf");

    ServletContext sc = req.getSession().getServletContext();
    IReportEngine engine = BirtEngine.getBirtEngine(sc);

    IReportRunnable design;
    try
    {
      // Open report design
      design = engine.openReportDesign(sc.getRealPath("/reports/design") + "/" + reportName);
      // create task to run and render report
      IRunAndRenderTask task = engine.createRunAndRenderTask(design);

      task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

      // set output options
      HTMLRenderOption options = new HTMLRenderOption();
      // set the image handler to a HTMLServerImageHandler if you plan on using
      // the base image url.
      options.setImageHandler(new HTMLServerImageHandler());
//      options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
      options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
      options.setOutputStream(resp.getOutputStream());
      options.setBaseImageURL(req.getContextPath() + "/imgs");
      options.setImageDirectory(sc.getRealPath("/imgs"));
      task.setRenderOption(options);

      // run report
      task.run();
      task.close();
    }
    catch (Exception e)
    {

      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}
