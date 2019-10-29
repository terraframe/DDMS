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
package dss.vector.solutions.query;

import java.awt.GraphicsEnvironment;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class StylesController extends StylesControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String  JSP_DIR          = "WEB-INF/dss/vector/solutions/query/Styles/";

  public static final String  LAYOUT           = "/layout.jsp";

  private static final long   serialVersionUID = 285080076;

  private static final Object lockObj          = new Object();

  private static String[]     fontFamilies     = null;

  public StylesController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  private static class FontStyleComparator implements Comparator<FontStyleDTO>, com.runwaysdk.generation.loader.Reloadable
  {

    public int compare(FontStyleDTO c1, FontStyleDTO c2)
    {
      return c1.getPriority().compareTo(c2.getPriority());
    }

  }

  protected static void setFontStylesAndFamiles(HttpServletRequest req, ClientRequestIF request)
  {
    List<FontStyleDTO> fontStyles = FontStylesDTO.allItems(request);
    Collections.sort(fontStyles, new FontStyleComparator());
    req.setAttribute("allFontStyles", fontStyles);

    synchronized (lockObj)
    {
      if (fontFamilies == null)
      {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontFamilies = ge.getAvailableFontFamilyNames();
      }
    }

    req.setAttribute("fontFamilies", fontFamilies);
  }

  protected static void populateRequestForStyles(HttpServletRequest req, StylesDTO styles, boolean enableCheckboxes)
  {
    ClientRequestIF request = styles.getRequest();

    req.setAttribute("styles", styles);
    req.setAttribute("enableCheckboxes", enableCheckboxes);
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(request));

    setFontStylesAndFamiles(req, request);
  }

  public void cancel(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.query.StylesDTO dto = dss.vector.solutions.query.StylesDTO.lock(super.getClientRequest(), id);
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.StylesDTO dto = new dss.vector.solutions.query.StylesDTO(clientRequest);
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(dss.vector.solutions.query.StylesDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.StylesDTO dto = dss.vector.solutions.query.StylesDTO.get(clientRequest, id);
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.StylesQueryDTO query = dss.vector.solutions.query.StylesDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.StylesQueryDTO query = dss.vector.solutions.query.StylesDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
