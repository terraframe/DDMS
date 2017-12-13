package dss.vector.solutions.generator;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;

import dss.vector.solutions.ExcelImportHistoryDTO;
import dss.vector.solutions.ExcelImportManagerDTO;
import dss.vector.solutions.form.business.FormSurveyDTO;
import dss.vector.solutions.geo.UnknownGeoEntityDTO;
import dss.vector.solutions.ontology.UnknownTermDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.ExcelUtil;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.RedirectUtility;

public class ExcelController extends ExcelControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/generator/Excel/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 237694310;

  public static final String TYPE             = "excelType";

  public ExcelController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void viewManager() throws java.io.IOException, javax.servlet.ServletException
  {
    URL url = new URL(this.req.getScheme(), this.req.getServerName(), this.req.getServerPort(), this.req.getContextPath());
    String path = url.toString();

    this.req.setAttribute("path", path);

    this.render("manager.jsp");
  }
  
  @Override
  public void failViewManager() throws java.io.IOException, javax.servlet.ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
  
  @Override
  public void clearHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    ExcelImportHistoryDTO.deleteAllHistory(getClientRequest());
  }
  
  @Override
  public void failClearHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    // Do nothing! Intentionally empty.
  }
  
  @Override
  public void getAllHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      JSONArray jHistories = new JSONArray();
      
      ExcelImportHistoryDTO[] histories = ExcelImportHistoryDTO.getAllHistory(getClientRequest());
      
      for (int i = 0; i < histories.length; ++i)
      {
        ExcelImportHistoryDTO history = histories[i];
        
        JSONObject jHistory = new JSONObject();
        
        jHistory.put("name", history.getHistoryComment());
        jHistory.put("importCount", history.getImportCount());
        jHistory.put("totalRecords", history.getTotalRecords());
        jHistory.put("status", history.getStatus().get(0).name());
        jHistory.put("startTime", new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z").format(history.getStartTime()));
        if (history.getEndTime() != null)
        {
          jHistory.put("endTime", new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z").format(history.getEndTime()));
        }
        else
        {
          jHistory.put("endTime", "");
        }
        jHistory.put("hasError", history.getErrorFile() != null);
        jHistory.put("hasSynonym", history.getSerializedUnknownGeos() != null || history.getSerializedUnknownTerms() != null);
        
        jHistories.put(jHistory);
      }
      
      resp.getWriter().write(jHistories.toString());
    }
    catch (JSONException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Override
  public void failGetAllHistory() throws java.io.IOException, javax.servlet.ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
  
  @Override
  public void excelExport(String excelType) throws IOException, ServletException
  {
    String[] split = excelType.split("\\.");
    String fileName = split[split.length - 1];
    try
    {
      InputStream inputStream = MdFormUtilDTO.excelExport(this.getClientRequest(), excelType);
      FileDownloadUtil.writeXLS(resp, fileName, inputStream);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, false);

      this.failExcelExport(excelType);
    }
  }

  @Override
  public void failExcelExport(String excelType) throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void surveyExcelExport() throws IOException, ServletException
  {
    try
    {
      InputStream inputStream = FormSurveyDTO.excelExport(this.getClientRequest());
      FileDownloadUtil.writeXLS(resp, "survey", inputStream);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, false);

      this.failSurveyExcelExport();
    }
  }

  @Override
  public void failSurveyExcelExport() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void excelImport(String excelType, MultipartFileParameter upfile) throws IOException, ServletException
  {
    this.excelImport(new DefaultImportConfiguration(), excelType, upfile);
  }

  @SuppressWarnings("unchecked")
  private void excelImport(ImportConfiguration configuration, String excelType, MultipartFileParameter upfile) throws IOException, ServletException
  {
    if (ServletFileUpload.isMultipartContent(req))
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      
      try
      {
        if (upfile != null && upfile.getSize() > 0)
        {
          InputStream sourceStream = upfile.getInputStream();
          int available = sourceStream.available();
          byte[] bytes = new byte[available];
          sourceStream.read(bytes);
          sourceStream.close();

          try
          {
            ExcelImportManagerDTO importer = ExcelImportManagerDTO.getNewInstance(clientRequest);
            
            InputStream errorStream = configuration.excelImport(clientRequest, new ByteArrayInputStream(bytes), excelType, importer);
            
            UnknownGeoEntityDTO[] unmatchedGeos = importer.getUnmatchedGeoViews();
            
            UnknownTermDTO[] unmatchedTerms = importer.getUnknownTerms();
            
            if (errorStream.available() > 0)
            {
              Boolean hasSynonyms = ( unmatchedGeos != null && unmatchedGeos.length > 0 ) || ( unmatchedTerms != null && unmatchedTerms.length > 0 );

              ExcelUtil.respondError(new BufferedInputStream(errorStream), upfile.getFilename(), resp, importer.getId(), hasSynonyms);
              return;
            }
          }
          catch (Throwable t)
          {
            boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

            if (!redirected)
            {
              configuration.redirectError(this, excelType);
            }
          }
        }
        else
        {
          // No file was uploaded

          render("importFailure.jsp");
        }
      }
      catch (Exception e)
      {
        ErrorUtility.prepareInformation(clientRequest.getInformation(), req);

        render("importFailure.jsp");
      }
    }
    else
    {
      this.importType("");
    }
  }
  
  @Override
  public void failExcelImport(String excelType, MultipartFileParameter upfile) throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void importType(String excelType) throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "importType");

      req.setAttribute(TYPE, excelType);

      render("importType.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failImportType(excelType);
      }
    }
  }
  
  @Override
  public void failImportType(String excelType) throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void surveyImportType() throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "surveyImportType");

      this.req.setAttribute(TYPE, FormSurveyDTO.CLASS);
      this.render("importSurveyType.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSurveyImportType();
      }
    }
  }

  @Override
  public void surveyExcelImport(String type, MultipartFileParameter upfile) throws IOException, ServletException
  {
    this.excelImport(new SurveyImportConfiguration(), type, upfile);
  }
}
