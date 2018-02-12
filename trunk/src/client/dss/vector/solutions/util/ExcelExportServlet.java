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

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdClassQueryDTO;

public class ExcelExportServlet extends HttpServlet implements Reloadable
{
  /**
   *
   */
  private static final long serialVersionUID = -4211492402501853068L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    String type = req.getParameter(ExcelImportServlet.TYPE);

    String[] split = type.split("\\.");
    String fileName = split[split.length-1];
    try
    {
      InputStream inputStream = clientRequest.exportExcelFile(type, "setupExportListener", new String[]{});
      
      FileDownloadUtil.writeXLS(res, fileName, inputStream);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, res, false);
      
      req.getRequestDispatcher("/WEB-INF/excelExportFail.jsp").forward(req, res);
//      req.getRequestDispatcher(req.getHeader("referer")).forward(req, res);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    MdClassQueryDTO query = FacadeDTO.getMDSSClasses(clientRequest);
    req.setAttribute("classes", query.getResultSet());

    req.getRequestDispatcher("/WEB-INF/excelExport.jsp").forward(req, resp);
  }

}
