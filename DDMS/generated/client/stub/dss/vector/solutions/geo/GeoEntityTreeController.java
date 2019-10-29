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
package dss.vector.solutions.geo;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.util.FacadeDTO;
import dss.vector.solutions.util.FileDownloadUtil;

public class GeoEntityTreeController extends GeoEntityTreeControllerBase implements Reloadable
{
  private static final long   serialVersionUID                    = 1236364846909L;

  private static final String TREE_JSP                            = "/WEB-INF/geoEntityTree.jsp";

  private static final String TREE_COMPONENT_JSP                  = "/WEB-INF/geoEntityTreeComponent.jsp";

  private static final String SINGLE_SELECT_SEARCH_COMPONENT_JSP  = "/WEB-INF/singleSelectSearchComponent.jsp";

  private static final String MULTIPLE_SELECT_SEARCH_COMPONENT_JS = "/WEB-INF/multipleSelectSearchComponent.jsp";

  public static final String  ROOT_GEO_ENTITY_ID                  = "rootGeoEntityId";

  public GeoEntityTreeController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void displaySingleSelectSearch(String rootGeoEntityId, Boolean[] flags, String[] extraUniversals) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyViewDTO[] views = GeoHierarchyDTO.collectHierarchies(this.getClientRequest(), rootGeoEntityId, flags, extraUniversals);
      req.setAttribute("views", views);

      req.getRequestDispatcher(SINGLE_SELECT_SEARCH_COMPONENT_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      handleException(t);
    }
  }
  
  @Override
  public void displayMultipleSelectSearch(String rootGeoEntityId, Boolean[] flags, String[] extraUniversals) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyViewDTO[] views = GeoHierarchyDTO.collectHierarchies(this.getClientRequest(), rootGeoEntityId, flags, extraUniversals);
      req.setAttribute("views", views);

      req.getRequestDispatcher(MULTIPLE_SELECT_SEARCH_COMPONENT_JS).forward(req, resp);
    }
    catch (Throwable t)
    {
      handleException(t);
    }
  }

  @Override
  public void displayTree(String rootGeoEntityId) throws IOException, ServletException
  {
    try
    {
      if (rootGeoEntityId == null || rootGeoEntityId.trim().length() == 0)
      {
        EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
        rootGeoEntityId = earth.getId();
      }

      if (this.isAsynchronous())
      {
        req.getRequestDispatcher(TREE_COMPONENT_JSP).forward(req, resp);
      }
      else
      {
        req.getRequestDispatcher(TREE_JSP).forward(req, resp);
      }
    }
    catch (Throwable t)
    {
      handleException(t);
    }
  }

  private void handleException(Throwable t) throws IOException
  {
    JSONRunwayExceptionDTO ex = new JSONRunwayExceptionDTO(t);
    this.resp.setStatus(500);
    this.resp.getWriter().write(ex.getJSON());
  }
  
  @Override
  public void export(String parentId, Boolean includeGeoData) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      
      InputStream stream = FacadeDTO.exportGeoChildren(clientRequest, parentId, includeGeoData);
      
      FileDownloadUtil.writeXLS(resp, "GeoExport", stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }
}
