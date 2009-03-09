package csu.mrc.ivcc.mdss.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

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
      return;
    
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    
    // Create a factory for disk-based file items
    FileItemFactory factory = new DiskFileItemFactory();

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    // Parse the request
    try
    {
      List<FileItem> items = upload.parseRequest(req);
      InputStream inputStream = new BufferedInputStream(items.get(0).getInputStream());
      clientRequest.importExcelFile(inputStream);
    }
    catch (ProblemExceptionDTO p)
    {
      List<ProblemDTOIF> problems = p.getProblems();
      req.setAttribute("problems", problems);
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }
    finally
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
