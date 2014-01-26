package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.form.business.FormSurveyDTO;
import dss.vector.solutions.geo.UnknownGeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;
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
            UnknownGeoEntityDTO[] unknownEntities = FacadeDTO.checkSynonyms(clientRequest, new ByteArrayInputStream(bytes), excelType);

            // all geo entities in the file were identified
            if (unknownEntities != null && unknownEntities.length == 0)
            {
              InputStream errorStream = configuration.excelImport(clientRequest, new ByteArrayInputStream(bytes), excelType);

              if (errorStream.available() > 0)
              {
                resp.addHeader("Content-Disposition", "attachment;filename=\"errors.xls\"");
                ServletOutputStream outputStream = resp.getOutputStream();
                FileIO.write(outputStream, errorStream);
              }
              else
              {
                render("importSuccess.jsp");
              }
            }
            else if (unknownEntities != null && unknownEntities.length > 0)
            {
              req.setAttribute("action", configuration.getFormUrl());
              req.setAttribute("excelType", excelType);
              req.setAttribute("unknownGeoEntitys", unknownEntities);
              req.getRequestDispatcher("/WEB-INF/synonymFinder.jsp").forward(req, resp);
            }
            else
            {
              ErrorUtility.prepareInformation(clientRequest.getInformation(), req);

              render("importFailure.jsp");
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
