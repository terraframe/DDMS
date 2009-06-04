package dss.vector.solutions.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;

import dss.vector.solutions.query.SavedSearchDTO;
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
  public void generateReport(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    buildReport(queryXML, geoEntityType, savedSearchId);
  }

  @SuppressWarnings("unchecked")
  private void buildReport(String queryXML, String geoEntityType, String savedSearchId) throws ServletException
  {
    SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

    resp.setHeader("Content-Disposition", "attachment;filename=" + search.getQueryName() + ".pdf");

    // Get report name and launch the engine
    ServletContext sc = req.getSession().getServletContext();
    IReportEngine engine = BirtEngine.getBirtEngine(sc);

    try
    {
      String csv = search.generateCSV(queryXML, geoEntityType, savedSearchId);

      // Open report design
      IReportRunnable design = engine.openReportDesign(search.getTemplateStream());

      // Change the data source to the temporary csv directory
      // FIXME This only works because the Server and Clerver
      //       are on the same box.
      ReportDesignHandle handle = (ReportDesignHandle) design.getDesignHandle();
      
      for (Iterator i = handle.getDataSources().iterator(); i.hasNext();)
      {
        ((DesignElementHandle) i.next()).setProperty("HOME", csv);
      }
      
      // Update the data set to use the temporary csv file
      for (Iterator i = handle.getDataSets().iterator(); i.hasNext();)
      {
        DesignElementHandle dataset = (DesignElementHandle) i.next();
        
        Pattern pattern = Pattern.compile("^(select\\s+.*?\\s+from\\s+)(.*?)(\\s+:.*)$");        
        Matcher matcher = pattern.matcher((String) dataset.getProperty("queryText"));
        
        matcher.find();
        
        StringBuffer result = new StringBuffer(matcher.group(1));
        result.append("temp.csv");
        result.append(matcher.group(3));        
                
        dataset.setProperty("queryText", result.toString());
      }

      
      // set output options
      IRenderOption options = new RenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_PDF);
      options.setOutputStream(resp.getOutputStream());

      HashMap<String, Object> contextMap = new HashMap<String, Object>();
      contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

      // create task to run and render report      
      IRunAndRenderTask task = engine.createRunAndRenderTask(design);
      task.setAppContext(contextMap);       
      task.setRenderOption(options);
      
      // run report
      task.run();
      task.close();
      
      // Delete the temp file
      search.deleteCSV(csv);
    }
    catch (Exception e)
    {
      throw new ServletException(e);
    }
  }
}
