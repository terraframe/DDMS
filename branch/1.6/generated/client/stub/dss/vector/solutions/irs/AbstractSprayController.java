package dss.vector.solutions.irs;

import dss.vector.solutions.util.ErrorUtility;

public class AbstractSprayController extends AbstractSprayControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/AbstractSpray/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -94626961;

  public AbstractSprayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failCreate(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("brand", java.util.Arrays.asList(dss.vector.solutions.irs.InsecticideBrandDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sprayMethod", dss.vector.solutions.irs.SprayMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("surfaceType", dto.getSurfaceType());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("brand", java.util.Arrays.asList(dss.vector.solutions.irs.InsecticideBrandDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sprayMethod", dss.vector.solutions.irs.SprayMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("surfaceType", dto.getSurfaceType());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.irs.AbstractSprayDTO dto = dss.vector.solutions.irs.AbstractSprayDTO.lock(super.getClientRequest(), id);
    req.setAttribute("brand", java.util.Arrays.asList(dss.vector.solutions.irs.InsecticideBrandDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sprayMethod", dss.vector.solutions.irs.SprayMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("surfaceType", dto.getSurfaceType());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failUpdate(dss.vector.solutions.irs.AbstractSprayDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("brand", java.util.Arrays.asList(dss.vector.solutions.irs.InsecticideBrandDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sprayMethod", dss.vector.solutions.irs.SprayMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("surfaceType", dto.getSurfaceType());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.irs.AbstractSprayDTO dto = dss.vector.solutions.irs.AbstractSprayDTO.get(clientRequest, id);
    req.setAttribute("brand", java.util.Arrays.asList(dss.vector.solutions.irs.InsecticideBrandDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("sprayMethod", dss.vector.solutions.irs.SprayMethodDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("surfaceType", dto.getSurfaceType());
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
    dss.vector.solutions.irs.AbstractSprayQueryDTO query = dss.vector.solutions.irs.AbstractSprayDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.irs.AbstractSprayQueryDTO query = dss.vector.solutions.irs.AbstractSprayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
