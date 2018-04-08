/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
import com.runwaysdk.system.VaultFileDTO;

import dss.vector.solutions.ExcelImportHistoryDTO;
import dss.vector.solutions.ExcelImportManagerDTO;
import dss.vector.solutions.form.business.FormSurveyDTO;
import dss.vector.solutions.geo.UnknownGeoEntityDTO;
import dss.vector.solutions.kaleidoscope.JavascriptUtil;
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
  
  protected void renderExternal(String jsp, String dir) throws java.io.IOException, javax.servlet.ServletException
  {
    if(!resp.isCommitted())
    {
      if(this.isAsynchronous())
      {
        req.getRequestDispatcher(dir+jsp).forward(req, resp);
      }
      else
      {
        req.setAttribute(com.runwaysdk.controller.JSPFetcher.INNER_JSP, dir+jsp);
        req.getRequestDispatcher(layout).forward(req, resp);
      }
    }
  }
  
  public void excelImportFromVault(java.lang.String vaultId, String config) throws java.io.IOException, javax.servlet.ServletException
  {
    ExcelImportManagerDTO.excelImportFromVault(this.getClientRequest(), vaultId, config);
    
    resp.sendRedirect("dss.vector.solutions.generator.ExcelController.viewManager.mojo");
  }
  
  public void failExcelImportFromVault(java.lang.String vaultId) throws java.io.IOException, javax.servlet.ServletException
  {
    // do NOTHING (as usual)
  }
  
  private void resolveSynonyms(int pageNum, String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    URL url = new URL(this.req.getScheme(), this.req.getServerName(), this.req.getServerPort(), this.req.getContextPath());
    String path = url.toString();
    
    this.req.setAttribute("path", path);
    
    JavascriptUtil.loadDatasets(this.getClientRequest(), req);
    
    ExcelImportHistoryDTO history = ExcelImportHistoryDTO.get(this.getClientRequest(), historyId);
    
    try
    {
      JSONObject reconstructJSON = new JSONObject(history.getReconstructionJSON());
      reconstructJSON.put("pageNum", pageNum);
      this.req.setAttribute("reconstructionJSON", reconstructJSON.toString());
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
    
    this.renderExternal("dataset-management.jsp", "/WEB-INF/dss/vector/solutions/kaleidoscope/userMenu/");
  }
  
  @Override
  public void resolveGeoSynonyms(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    resolveSynonyms(3, historyId);
  }
  
  @Override
  public void failResolveGeoSynonyms(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    // do nothing
  }
  
  @Override
  public void resolveTermSynonyms(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    resolveSynonyms(4, historyId);
  }
  
  @Override
  public void failResolveTermSynonyms(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    // do nothing
  }
  
  @Override
  public void downloadErrorSpreadsheet(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    VaultFileDTO vfile = ExcelImportHistoryDTO.get(getClientRequest(), historyId).getErrorFile();
    
    String fileName = vfile.getFileName() + "." + vfile.getFileExtension();
    
    resp.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    
    ExcelImportHistoryDTO.downloadErrorSpreadsheet(getClientRequest(), historyId, resp.getOutputStream());
  }
  
  @Override
  public void failDownloadErrorSpreadsheet(java.lang.String historyId) throws java.io.IOException, javax.servlet.ServletException
  {
    // do nothing
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
        
        jHistory.put("id", history.getId());
        
        jHistory.put("name", history.getFileName());
        
        if (history.getImportCount() != null)
        {
          jHistory.put("importCount", history.getImportCount());
        }
        else
        {
          jHistory.put("importCount", 0);
        }
        
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
        
        jHistory.put("geoSyns", history.getNumberUnknownGeos());
        
        jHistory.put("termSyns", history.getNumberUnknownTerms());
        
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
            
            InputStream errorStream = configuration.excelImport(clientRequest, new ByteArrayInputStream(bytes), excelType, importer, upfile.getFilename());
            
            UnknownGeoEntityDTO[] unmatchedGeos = importer.getUnmatchedGeoViews();
            
            UnknownTermDTO[] unmatchedTerms = importer.getUnknownTerms();
            
            if (errorStream != null && errorStream.available() > 0)
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
