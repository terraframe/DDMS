package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.system.metadata.SupportedLocaleDTO;
import com.terraframe.mojo.system.metadata.SupportedLocaleQueryDTO;

public class LocalizationController extends LocalizationControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/util/Localization/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long serialVersionUID = 883845243;
  
  public LocalizationController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void selectLocales() throws IOException, ServletException
  {
    renderSelectLocales();
  }

  private void renderSelectLocales() throws IOException, ServletException
  {
//    List<SupportedLocaleDTO> list = new LinkedList<SupportedLocaleDTO>();
//    
    Locale[] array = new Locale[] { Locale.ENGLISH, Locale.FRENCH, new Locale("pt")};
//    for (Locale l : array)
//    {
//      SupportedLocaleDTO dto = new SupportedLocaleDTO(getClientRequest());
//      dto.setEnumName(l.toString());
//      dto.setLocaleLabel(l.getDisplayName());
//      list.add(dto);
//    }
    
//    SupportedLocaleQueryDTO allInstances = SupportedLocaleDTO.getAllInstances(getClientRequest(), SupportedLocaleDTO.ENUMNAME, true, 20, 1);
    req.setAttribute("supported", Arrays.asList(array));
    render("selectLocales.jsp");
  }
  
  @SuppressWarnings("unchecked")
  public void importFile() throws IOException, ServletException
  {
    String message = "";
    try
    {
      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      FileItem file = null;
      List<FileItem> items = upload.parseRequest(this.req);
      for (FileItem item : items)
      {
        if (!item.isFormField() && item.getSize() > 0)
        {
          file = item;
        }
      }

      ClientRequestIF request = this.getClientRequest();
      if (file == null)
      {
        message = MDSSProperties.getString("File_Required");
        return;
      }
      
      LocalizationFacadeDTO.importFile(request, file.getInputStream());

      message = MDSSProperties.getString("File_Upload_Success");
    }
    catch (Throwable e)
    {
      this.getResponse().getWriter().write(e.getLocalizedMessage());
    }
    finally
    {
      this.resp.setContentType("text/html;charset=UTF-8");
      this.resp.setCharacterEncoding("UTF-8");
      this.resp.getWriter().write(message);
    }
  }
  
  public void exportFile(String[] locales) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();
    
    InputStream stream = LocalizationFacadeDTO.exportFile(clientRequest, locales);
    
    FileDownloadUtil.writeXLS(resp, "Localization", stream);
  }
}
