package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.util.FileIO;

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

  public static final String TYPE             = "type";

  public ExcelController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void excelExport(String type) throws IOException, ServletException
  {
    String[] split = type.split("\\.");
    String fileName = split[split.length - 1];
    try
    {
      InputStream inputStream = MdFormUtilDTO.excelExport(this.getClientRequest(), type);
      FileDownloadUtil.writeXLS(resp, fileName, inputStream);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, false);

      this.failExcelExport(type);
    }
  }

  @Override
  public void failExcelExport(String type) throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void excelImport() throws IOException, ServletException
  {
    if (ServletFileUpload.isMultipartContent(req))
    {
      ClientRequestIF clientRequest = this.getClientRequest();

      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      try
      {
        List<FileItem> items = upload.parseRequest(req);

        HashMap<String, String> fields = new HashMap<String, String>();
        InputStream sourceStream = null;
        long size = 0;

        for (FileItem item : items)
        {
          if (item.isFormField())
          {
            String name = item.getFieldName();
            String value = item.getString();
            fields.put(name, value);
          }
          else
          {
            size = item.getSize();
            sourceStream = item.getInputStream();
          }
        }

        if (size > 0)
        {
          int available = sourceStream.available();
          byte[] bytes = new byte[available];
          sourceStream.read(bytes);
          sourceStream.close();

          String excelType = fields.get(TYPE);

          UnknownGeoEntityDTO[] unknownEntities = FacadeDTO.checkSynonyms(clientRequest, new ByteArrayInputStream(bytes), excelType);

          // all geo entities in the file were identified
          if (unknownEntities != null && unknownEntities.length == 0)
          {
            InputStream errorStream = MdFormUtilDTO.excelImport(clientRequest, new ByteArrayInputStream(bytes), excelType);

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
        else
        {
          // No file was uploaded

          this.failExcelImport();
        }
      }
      catch (FileUploadException e)
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
  public void importType(String type) throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "importType");

      req.setAttribute("type", type);

      render("importType.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failImportType(type);
      }
    }
  }
}
