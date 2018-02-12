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
package dss.vector.solutions.etl.dhis2;

public class GeoLevelMapController extends GeoLevelMapControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/etl/dhis2/GeoLevelMap/";
  public static final String LAYOUT = "/layout.jsp";
  
  public GeoLevelMapController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void create(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }
  public void failCreate(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }
  public void failDelete(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto = dss.vector.solutions.etl.dhis2.GeoLevelMapDTO.lock(super.getClientRequest(), id);
      req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto = new dss.vector.solutions.etl.dhis2.GeoLevelMapDTO(clientRequest);
      req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void update(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }
  public void failUpdate(dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.etl.dhis2.GeoLevelMapDTO dto = dss.vector.solutions.etl.dhis2.GeoLevelMapDTO.get(clientRequest, id);
      req.setAttribute("orgUnitLevel", dss.vector.solutions.etl.dhis2.OrgUnitLevelDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("universal", com.runwaysdk.system.metadata.MdBusinessDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch(java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.etl.dhis2.GeoLevelMapQueryDTO query = dss.vector.solutions.etl.dhis2.GeoLevelMapDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.etl.dhis2.GeoLevelMapQueryDTO query = dss.vector.solutions.etl.dhis2.GeoLevelMapDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
