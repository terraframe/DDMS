package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.util.FileIO;

public class ExcelImportServlet extends HttpServlet
{
  /**
   *
   */
  private static final long serialVersionUID = 0L;

  public static final String TYPE = "excelType";

  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
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

    // Data structure that contains synonym matching information for geo entities that could not
    // be identified.
    ViewDTO[] unknownGeoEntityDTOArray = null;

    // Parse the request
    boolean isGeoImport = false;
    
    String excelType = null;
    
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
      
      // No file was uploaded
      if (size==0)
      {
        req.setAttribute(ErrorUtility.ERROR_MESSAGE, MDSSProperties.getString("File_Upload_Blank"));
        req.setAttribute(TYPE, fields.get(TYPE));
        req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
        return;
      }

      int available = sourceStream.available();
      byte[] bytes = new byte[available];
      sourceStream.read(bytes);
      sourceStream.close();

      InputStream errorStream;
      excelType = fields.get(TYPE);
      
      // This referenced a constant, GeoEntityExcelViewDTO.CLASS, but was removed for now to eliminate the compile-time reference to a Reloadable class
      isGeoImport = excelType.equals("dss.vector.solutions.export.GeoEntityExcelView");

      if (isGeoImport)
      {
        if(size == 0)
        {
          res.setContentType("text/html;charset=UTF-8");
          res.setCharacterEncoding("UTF-8");
          res.getWriter().write(MDSSProperties.getString("File_Required"));

          return; // error case
        }
        String[] params = new String[1];
        params[0] = fields.get("parentGeoEntityId");
        errorStream = importExcelFile(clientRequest, bytes, excelType, params);
        
        if (errorStream.available()>0)
        {
          res.addHeader("Content-Disposition", "attachment;filename=\"errors.xls\"");
          ServletOutputStream outputStream = res.getOutputStream();
          FileIO.write(outputStream, errorStream);
          return;
        }
      }
      else
      {
        Class<?> facadeClass = LoaderDecorator.load("dss.vector.solutions.util.FacadeDTO");
        Method method = facadeClass.getMethod("checkSynonyms", ClientRequestIF.class, InputStream.class, String.class);
        unknownGeoEntityDTOArray = (ViewDTO[]) method.invoke(null, clientRequest, new ByteArrayInputStream(bytes), excelType);

        // all geo entities in the file were identified
        if (unknownGeoEntityDTOArray.length == 0)
        {
          errorStream = importExcelFile(clientRequest, bytes, excelType, new String[0]);

          if (errorStream.available()>0)
          {
            res.addHeader("Content-Disposition", "attachment;filename=\"errors.xls\"");
            ServletOutputStream outputStream = res.getOutputStream();
            FileIO.write(outputStream, errorStream);
            return;
          }
        }
      }
    }
    catch (InvocationTargetException e)
    {
      ErrorUtility.prepareThrowable(e.getTargetException(), req, res, false);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, res, false);
    }

    if (unknownGeoEntityDTOArray != null && unknownGeoEntityDTOArray.length > 0)
    {
      req.setAttribute("excelType", excelType);
      req.setAttribute("unknownGeoEntitys", unknownGeoEntityDTOArray);
      req.getRequestDispatcher("/WEB-INF/synonymFinder.jsp").forward(req, res);
    }
    else if(isGeoImport)
    {
      res.setContentType("text/html;charset=UTF-8");
      res.setCharacterEncoding("UTF-8");
      res.getWriter().write(MDSSProperties.getString("File_Upload_Success"));
    }
    else
    {
      ErrorUtility.prepareInformation(clientRequest.getInformation(), req);

      req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req, res);
    }
  }

  private InputStream importExcelFile(ClientRequestIF clientRequest, byte[] bytes, String type, String[] params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Class<?> facadeClass = LoaderDecorator.load("dss.vector.solutions.util.FacadeDTO");
    Method method = facadeClass.getMethod("importExcelFile", ClientRequestIF.class, InputStream.class, String.class, String.class, String[].class);
    return (InputStream) method.invoke(null, clientRequest, new ByteArrayInputStream(bytes), type, "setupImportListener", params);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, resp);
  }
}
