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
package dss.vector.solutions.general;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;

import dss.vector.solutions.util.ErrorUtility;

public class ReportModuleController extends ReportModuleControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/ReportModule/";

  public static final String LAYOUT  = "/layout.jsp";

  public ReportModuleController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void form() throws IOException, ServletException
  {
    this.form(new ReportModuleViewDTO(this.getClientRequest()));
  }

  private void form(ReportModuleViewDTO view) throws IOException, ServletException
  {
    req.setAttribute("item", view);
    req.setAttribute("modules", Arrays.asList(ReportModuleViewDTO.getModules(this.getClientRequest())));

    render("form.jsp");
  }

  @Override
  public void buildDatabaseViews(ReportModuleViewDTO view) throws IOException, ServletException
  {
    try
    {
      view.buildDatabaseViews();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failBuildDatabaseViews(view);
      }
    }
  }

  @Override
  public void failBuildDatabaseViews(ReportModuleViewDTO view) throws IOException, ServletException
  {
    this.form(view);
  }

}
