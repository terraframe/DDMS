package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

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

  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    if (!ServletFileUpload.isMultipartContent(req))
    {
      req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
      return;
    }
    
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    
    // Create a factory for disk-based file items
    FileItemFactory factory = new DiskFileItemFactory();

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    // Parse the request
    try
    {
      List<FileItem> items = upload.parseRequest(req);
      HashMap<String, String> fields = new HashMap<String, String>();
      InputStream sourceStream = null;
      
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
          sourceStream = item.getInputStream();
        }
      }
      
      int available = sourceStream.available();
      byte[] bytes = new byte[available];
      sourceStream.read(bytes);
      sourceStream.close();
      
      InputStream errorStream;
      String type = fields.get("type");
      if (type.equals(GeoEntityExcelViewDTO.CLASS))
      {
        errorStream = clientRequest.importExcelFile(new ByteArrayInputStream(bytes), type, "setupImportListener", fields.get("parentGeoEntityId"));
      }
      else
      {
        errorStream = clientRequest.importExcelFile(new ByteArrayInputStream(bytes), type, "setupImportListener");
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
    req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req, res);
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, resp);
  }
}
