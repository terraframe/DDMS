package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.util.FileIO;

import dss.vector.solutions.export.GeoEntityExcelViewDTO;

public class ExcelImportServlet extends HttpServlet implements Reloadable
{
  /**
   *
   */
  private static final long serialVersionUID = 0L;

  public static final String TYPE = "excelType";

  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    ResourceBundle localized = ResourceBundle.getBundle("MDSS");

    if (!ServletFileUpload.isMultipartContent(req))
    {
      req.setAttribute(TYPE, req.getParameter(TYPE));
      req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
      return;
    }

    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);

    // Create a factory for disk-based file items
    FileItemFactory factory = new DiskFileItemFactory();

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    // Parse the request
    boolean isGeoImport = false;
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

      int available = sourceStream.available();
      byte[] bytes = new byte[available];
      sourceStream.read(bytes);
      sourceStream.close();

      InputStream errorStream;
      String type = fields.get(TYPE);
      isGeoImport = type.equals(GeoEntityExcelViewDTO.CLASS);

      if (isGeoImport)
      {
        if(size == 0)
        {
          res.setContentType("text/html;charset=UTF-8");
          res.setCharacterEncoding("UTF-8");
          res.getWriter().write(localized.getString("File_Required"));

          return; // error case
        }
        String[] params = new String[1];
        params[0] = fields.get("parentGeoEntityId");
        errorStream = FacadeDTO.importExcelFile(clientRequest, new ByteArrayInputStream(bytes), type, "setupImportListener", params);
      }
      else
      {
        errorStream = FacadeDTO.importExcelFile(clientRequest, new ByteArrayInputStream(bytes), type, "setupImportListener", new String[0]);
      }

      if (errorStream.available()>0)
      {
        res.addHeader("Content-Disposition", "attachment;filename=\"errors.xls\"");
        ServletOutputStream outputStream = res.getOutputStream();
        FileIO.write(outputStream, errorStream);
        return;
      }
    }
    catch (ProblemExceptionDTO p)
    {
      ErrorUtility.prepareProblems(p, req);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req);
    }

    if(isGeoImport)
    {
      res.setContentType("text/html;charset=UTF-8");
      res.setCharacterEncoding("UTF-8");
      res.getWriter().write(localized.getString("File_Upload_Success"));
    }
    else
    {
      req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req, res);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, resp);
  }
}
