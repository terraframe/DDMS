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
package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.constants.MdActionInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.web.javascript.JavascriptConstants;
import com.runwaysdk.web.json.JSONControllerServlet;

public class ContentTypeFilter implements Filter, Reloadable
{

  public void destroy()
  {
  }

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
  {
    // check the request for the Runway javascript file
    HttpServletRequest httpRequest = (HttpServletRequest) req;

    String path = httpRequest.getRequestURI();

    if (path.endsWith(JSONControllerServlet.class.getSimpleName()))
    {
      res.setContentType(JavascriptConstants.CONTENT_TYPE);
      chain.doFilter(req, res);
    }
    else if (path.endsWith(MdActionInfo.ACTION_SUFFIX) || path.endsWith(MdActionInfo.AJAX_ACTION_SUFFIX))
    {
      res.setContentType("text/html;charset=UTF-8");
      chain.doFilter(req, res);
    }
    else
    {
      chain.doFilter(req, res);
    }
  }

  public void init(FilterConfig arg0) throws ServletException
  {
  }

}
