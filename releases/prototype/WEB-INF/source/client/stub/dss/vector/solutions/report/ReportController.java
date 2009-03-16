package dss.vector.solutions.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

import dss.vector.solutions.entomology.MosquitoCollectionQueryDTO;
import dss.vector.solutions.report.ReportControllerBase;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.util.BirtEngine;

public class ReportController extends ReportControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236706138416L;

  public ReportController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void report(String reportName) throws IOException, ServletException
  {
    generateCollectionCSV(reportName);
    buildReport(reportName);
  }

  @SuppressWarnings("unchecked")
  private void buildReport(String reportName) throws ServletException
  {
    // get report name and launch the engine
    // resp.setContentType("text/html");
    resp.setContentType("application/pdf");
    resp.setHeader("Content-Disposition", "inline; filename=test.pdf");

    ServletContext sc = req.getSession().getServletContext();
    IReportEngine engine = BirtEngine.getBirtEngine(sc);

    IReportRunnable design;
    try
    {
      // Open report design
      design = engine.openReportDesign(sc.getRealPath("/reports/design") + "/" + reportName);
      // create task to run and render report
      IRunAndRenderTask task = engine.createRunAndRenderTask(design);

      task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
          this.getClass().getClassLoader());

      // set output options
      HTMLRenderOption options = new HTMLRenderOption();
      // set the image handler to a HTMLServerImageHandler if you plan on using
      // the base image url.
      options.setImageHandler(new HTMLServerImageHandler());
      // options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
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

  public void generateCollectionCSV(String reportName) throws IOException, ServletException
  {
    try
    {

      ServletContext sc = req.getSession().getServletContext();
      File file = new File(sc.getRealPath("/reports/data") + "/collection.csv");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));

      // Write the header: Birt reads the first row to determine the name of the
      // columns
      writer.write("GeoEntity, Date Collected, Collection Method\n");

      MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(getClientRequest(),
          MosquitoCollectionDTO.DATECOLLECTED, true, 0, 0);
      List<? extends MosquitoCollectionDTO> resultSet = query.getResultSet();

      for (MosquitoCollectionDTO dto : resultSet)
      {
        String entityName = dto.getGeoEntity().getEntityName();
        String dateCollected = new SimpleDateFormat().format(dto.getDateCollected());

        String termName = dto.getCollectionMethod().getTermName();
        writer.write(entityName + ", " + dateCollected + ", " + termName + "\n");
      }

      writer.flush();
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}