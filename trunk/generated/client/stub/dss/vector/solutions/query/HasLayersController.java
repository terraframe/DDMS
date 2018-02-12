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

import dss.vector.solutions.util.ErrorUtility;

public class HasLayersController extends HasLayersControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/query/HasLayers/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 834821409;

  public HasLayersController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void childQuery(java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.HasLayersQueryDTO query = dss.vector.solutions.query.HasLayersDTO.childQuery(clientRequest, childId);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failChildQuery(java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void create(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failCreate(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.query.HasLayersDTO dto = dss.vector.solutions.query.HasLayersDTO.lock(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void newInstance(java.lang.String parentId, java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.HasLayersDTO dto = new dss.vector.solutions.query.HasLayersDTO(clientRequest, parentId, childId);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance(java.lang.String parentId, java.lang.String childId) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void newRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("parentList", dss.vector.solutions.query.SavedMapDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("childList", dss.vector.solutions.query.LayerDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    render("newRelationshipComponent.jsp");
  }

  public void failNewRelationship() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void parentQuery(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.HasLayersQueryDTO query = dss.vector.solutions.query.HasLayersDTO.parentQuery(clientRequest, parentId);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failParentQuery(java.lang.String parentId) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void update(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failUpdate(dss.vector.solutions.query.HasLayersDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.HasLayersDTO dto = dss.vector.solutions.query.HasLayersDTO.get(clientRequest, id);
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
    dss.vector.solutions.query.HasLayersQueryDTO query = dss.vector.solutions.query.HasLayersDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.query.HasLayersQueryDTO query = dss.vector.solutions.query.HasLayersDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
