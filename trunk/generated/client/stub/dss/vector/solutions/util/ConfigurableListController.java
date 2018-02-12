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

import javax.servlet.ServletException;

public class ConfigurableListController extends ConfigurableListControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243479609000L;
  
  public ConfigurableListController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/util/ConfigurableListController/";
    this.layout = "/layout.jsp";
  }
  
  @Override
  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

//    ClientRequestIF clientRequest = super.getClientRequest();
    render("viewAll.jsp");
  }
}
