package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.util.RedirectUtility;

public class MenuItemController extends MenuItemControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/MenuItem/";
  
  private static final String EDIT_DISEASE_JSP = "editDisease.jsp";
  
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 492263447;
  
  public MenuItemController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void cancel(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }
  public void create(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failCreate(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
    req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  public void delete(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
    req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.general.MenuItemDTO dto = dss.vector.solutions.general.MenuItemDTO.lock(super.getClientRequest(), id);
      req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
      req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
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
      dss.vector.solutions.general.MenuItemDTO dto = new dss.vector.solutions.general.MenuItemDTO(clientRequest);
      req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
      req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
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
  public void update(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failUpdate(dss.vector.solutions.general.MenuItemDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
    req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
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
      dss.vector.solutions.general.MenuItemDTO dto = dss.vector.solutions.general.MenuItemDTO.get(clientRequest, id);
      req.setAttribute("term", dss.vector.solutions.util.AttributeUtil.getValue(dss.vector.solutions.general.MenuItemDTO.TERM, dto));
      req.setAttribute("url", dss.vector.solutions.general.SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
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
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    String diseaseName = (String) this.getRequest().getSession().getAttribute(MDSSUserDTO.DISEASENAME);
    DiseaseDTO disease = DiseaseDTO.getCurrent(this.getClientRequest());
    
    req.setAttribute("diseaseMaster", disease);
    
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.MenuItemViewQueryDTO query = dss.vector.solutions.general.MenuItemViewDTO.getViewsForDisease(clientRequest);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  
  @Override
  public void editDisease(String id) throws IOException, ServletException
  {
    DiseaseDTO dto = DiseaseDTO.get(this.getClientRequest(), id);
    dto.lock();
    
    req.setAttribute("term", dto.getMenuRoot());
    req.setAttribute("item", dto);
    
    render(EDIT_DISEASE_JSP);
  }
  
  @Override
  public void updateDisease(DiseaseDTO dto) throws IOException, ServletException
  {
    dto.apply();
    
    this.viewAll();
  }
  
  @Override
  public void cancelDisease(String id) throws IOException, ServletException
  {
    DiseaseDTO.unlock(this.getClientRequest(), id);
    
    this.viewAll();
  }
  
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.MenuItemQueryDTO query = dss.vector.solutions.general.MenuItemDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
