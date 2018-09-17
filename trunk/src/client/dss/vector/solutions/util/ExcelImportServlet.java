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
package dss.vector.solutions.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.runwaysdk.ClientSession;
import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.LoaderDecorator;

public class ExcelImportServlet extends HttpServlet
{
  private static HashMap<String,String> importDoneMessages = new HashMap<String,String>();
  /**
   *
   */
  private static final long  serialVersionUID = 0L;

  public static final String TYPE             = "excelType";

  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    if (!ServletFileUpload.isMultipartContent(req))
    {
      req.setAttribute(TYPE, req.getParameter(TYPE));
      req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
      return;
    }

    ClientRequestIF clientRequest = (ClientRequestIF) req.getAttribute(ClientConstants.CLIENTREQUEST);

    // Create a factory for disk-based file items
    FileItemFactory factory = new DiskFileItemFactory();

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    // Parse the request
    boolean isGeoImport = false;

    String excelType = null;

    try
    {
      List<FileItem> items = upload.parseRequest(req);
      HashMap<String, String> fields = new HashMap<String, String>();
      InputStream sourceStream = null;
      long size = 0;
      String fileName = "";

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

          fileName = item.getName();
          if (fileName != null)
          {
            fileName = FilenameUtils.getName(fileName);
          }
        }
      }

      // No file was uploaded
      if (size == 0)
      {
        req.setAttribute(ErrorUtility.ERROR_MESSAGE, LocalizationFacadeDTO.getFromBundles(clientRequest, "File_Upload_Blank"));
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

      // This referenced a constant, GeoEntityExcelViewDTO.CLASS, but was
      // removed for now to eliminate the compile-time reference to a Reloadable
      // class
      isGeoImport = excelType.equals("dss.vector.solutions.export.GeoEntityExcelView");

      String[] params;
      
      if (isGeoImport)
      {
        if (size == 0)
        {
          res.setContentType("text/html;charset=UTF-8");
          res.setCharacterEncoding("UTF-8");
          res.getWriter().write(LocalizationFacadeDTO.getFromBundles(clientRequest, "File_Required"));

          return; // error case
        }
        params = new String[1];
        params[0] = fields.get("parentGeoEntityId");
//        errorStream = importExcelFile(clientRequest, bytes, excelType, params);

//        if (errorStream.available() > 0)
//        {
//          ExcelUtil.respondError(new BufferedInputStream(errorStream), fileName, res, null, false);
//          return;
//        }
      }
      else
      {
        params = new String[0];
      }
      
      Class<?> managerClass = LoaderDecorator.load("dss.vector.solutions.ExcelImportManagerDTO");
      Method newInstMethod = managerClass.getMethod("getNewInstance", ClientRequestIF.class);
      Object managerInst = (Object) newInstMethod.invoke(null, clientRequest);
      Method getIdMethod = managerClass.getMethod("getId");
      String managerId = (String) getIdMethod.invoke(managerInst);

      Method importWhatYouCanMethod = managerClass.getMethod("importWhatYouCan", ClientRequestIF.class, String.class, InputStream.class, String[].class, String.class);
      errorStream = (InputStream) importWhatYouCanMethod.invoke(null, clientRequest, managerId, new ByteArrayInputStream(bytes), params, fileName);

      Method getUnmatchedGeoViewsMethod = managerClass.getMethod("getUnmatchedGeoViews", ClientRequestIF.class, String.class);
      Method getUnknownTermsMethod = managerClass.getMethod("getUnknownTerms", ClientRequestIF.class, String.class);

      // Data structure that contains synonym matching information for geo
      // entities that could not be identified.
      ViewDTO[] unknownEntities = (ViewDTO[]) getUnmatchedGeoViewsMethod.invoke(null, clientRequest, managerId);

      // Data structure that contains synonym matching information for terms
      // that could not be identified.
      ViewDTO[] unknownTerms = (ViewDTO[]) getUnknownTermsMethod.invoke(null, clientRequest, managerId);

      if (errorStream != null && errorStream.available() > 0)
      {
        Boolean hasSynonyms = ( unknownEntities != null && unknownEntities.length > 0 ) || ( unknownTerms != null && unknownTerms.length > 0 );

        ExcelUtil.respondError(new BufferedInputStream(errorStream), fileName, res, managerId, hasSynonyms);
        return;
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

    // if (unknownGeoEntityDTOArray != null && unknownGeoEntityDTOArray.length >
    // 0)
    // {
    // req.setAttribute("action", "excelimport");
    // req.setAttribute("excelType", excelType);
    // req.setAttribute("unknownGeoEntitys", unknownGeoEntityDTOArray);
    // req.getRequestDispatcher("/WEB-INF/synonymFinder.jsp").forward(req, res);
    // }
    if (isGeoImport)
    {
      res.setContentType("text/html;charset=UTF-8");
      res.setCharacterEncoding("UTF-8");
      res.getWriter().write(LocalizationFacadeDTO.getFromBundles(clientRequest, "File_Upload_Success"));
    }
    else
    {
      // ErrorUtility.prepareInformation(clientRequest.getInformation(), req);
      //
      // req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req,
      // res);

      if (req.getAttribute(ErrorUtility.ERROR_MESSAGE) != null)
      {
        res.addHeader(ErrorUtility.ERROR_MESSAGE, String.valueOf(req.getAttribute(ErrorUtility.ERROR_MESSAGE)));
      }
    }
  }

  private InputStream importExcelFile(ClientRequestIF clientRequest, byte[] bytes, String type, String[] params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Class<?> facadeClass = LoaderDecorator.load("dss.vector.solutions.util.FacadeDTO");
    Method method = facadeClass.getMethod("importExcelFile", ClientRequestIF.class, InputStream.class, String.class, String.class, String[].class);
    return (InputStream) method.invoke(null, clientRequest, new ByteArrayInputStream(bytes), type, "setupImportListener", params);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF) req.getAttribute(ClientConstants.CLIENTREQUEST);
    if (clientRequest == null)
    {
      ClientSession session = ( (ClientSession) req.getSession().getAttribute(ClientConstants.CLIENTSESSION) );
      if (session != null)
      {
        clientRequest = session.getRequest();
        req.setAttribute(ClientConstants.CLIENTREQUEST, clientRequest);
      }
    }

    String path = getRequestPath(req);

    if (path.contains("excelImportSynonyms"))
    {
      Class<?> managerClass = LoaderDecorator.load("dss.vector.solutions.ExcelImportManagerDTO");

      String managerId = req.getParameter("managerId");
      String excelType = req.getParameter("excelType");

      ViewDTO[] unknownEntities = null;
      ViewDTO[] unknownTerms = null;

      try
      {
        Method getUnmatchedGeoViewsMethod = managerClass.getMethod("getUnmatchedGeoViews", ClientRequestIF.class, String.class);
        unknownEntities = (ViewDTO[]) getUnmatchedGeoViewsMethod.invoke(null, clientRequest, managerId);

        Method getUnknownTermsMethod = managerClass.getMethod("getUnknownTerms", ClientRequestIF.class, String.class);
        unknownTerms = (ViewDTO[]) getUnknownTermsMethod.invoke(null, clientRequest, managerId);

      }
      catch (InvocationTargetException e)
      {
        ErrorUtility.prepareThrowable(e.getTargetException(), req, res, false);
      }
      catch (Exception e)
      {
        ErrorUtility.prepareThrowable(e, req, res, false);
      }

      if ( ( unknownEntities != null && unknownEntities.length > 0 ) || ( unknownTerms != null && unknownTerms.length > 0 ))
      {
        req.setAttribute("action", "excelimport");
        req.setAttribute("excelType", excelType);
        req.setAttribute("unknownGeoEntitys", unknownEntities);
        req.setAttribute("unknownTerms", unknownTerms);
        req.getRequestDispatcher("/WEB-INF/synonymFinder.jsp").forward(req, res);
      }
      else
      {
        req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
      }
    }
    else if (path.contains("excelImportDone"))
    {
      if (req.getParameter("errorMessage") != null)
      {
        req.setAttribute("errorMessage", req.getParameter("errorMessage"));
      }
      if (importDoneMessages.containsKey(clientRequest.getSessionId()))
      {
        req.setAttribute("msg", importDoneMessages.remove(clientRequest.getSessionId()));
      }

      req.getRequestDispatcher("/WEB-INF/excelImportDone.jsp").forward(req, res);
    }
    else
    {
      req.getRequestDispatcher("/WEB-INF/excelImport.jsp").forward(req, res);
    }
  }

  private String getRequestPath(HttpServletRequest req)
  {
    String servletPath = req.getServletPath();

    if (!"".equals(servletPath))
    {
      return servletPath;
    }

    String requestUri = req.getRequestURI();
    int startIndex = req.getContextPath().equals("") ? 0 : req.getContextPath().length();
    int endIndex = req.getPathInfo() == null ? requestUri.length() : requestUri.indexOf(req.getPathInfo());

    return requestUri.substring(startIndex, endIndex);
  }
  
  public static void addImportDoneMessage(String sessionId, String msg)
  {
    importDoneMessages.put(sessionId, msg);
  }
}
