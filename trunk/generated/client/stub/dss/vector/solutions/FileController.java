package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import dss.vector.solutions.util.FileDownloadUtil;

public class FileController extends FileControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256759397676L;
  
  public FileController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
    
  @Override
  public void exportToFile(String message, String fileName) throws IOException, ServletException
  {    
    InputStream stream = new ByteArrayInputStream(message.getBytes());
    
    FileDownloadUtil.writeTXT(resp, fileName.replace(" ", ""), stream);
  }  
}
