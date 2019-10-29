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
package dss.vector.solutions.synchronization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;

public class ImportController extends ImportControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/synchronization/Import/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long serialVersionUID = -1339908979;
  
  public ImportController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void viewLog() throws IOException, ServletException
  {
    try
    {
      renderViewAll(ImportLogViewDTO.getQuery(getClientRequest(), ImportLogViewDTO.SOURCESITE, true, 20, 1));
    }
    catch(Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      
      if(!redirected)
      {
        this.failViewLog();
      }
    }
  }
  
  @Override
  public void failViewLog() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
  
  public void viewLogPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ImportLogViewQueryDTO query = ImportLogViewDTO.getQuery(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    renderViewAll(query);
  }

  private void renderViewAll(ImportLogViewQueryDTO query) throws IOException, ServletException
  {
    req.setAttribute("query", query);
    render("viewImportLog.jsp");
  }
}
