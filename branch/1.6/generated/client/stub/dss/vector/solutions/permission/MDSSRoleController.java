package dss.vector.solutions.permission;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class MDSSRoleController extends MDSSRoleControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/permission/MDSSRole/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -2016934929;

  public MDSSRoleController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSRoleViewQueryDTO query = MDSSRoleViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(MDSSRoleDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  public void failCancel(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      MDSSRoleViewDTO dto = MDSSRoleDTO.lockView(super.getClientRequest(), id);

      this.edit(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  private void edit(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(MDSSRoleDTO.getView(super.getClientRequest(), id));
  }

  public void view(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    MDSSRoleViewQueryDTO query = MDSSRoleViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create an Efficacy Assay
      new MDSSRoleDTO(clientRequest);

      this.newInstance(new MDSSRoleViewDTO(clientRequest));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }
  }

  private void newInstance(MDSSRoleViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void getPermissions(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      MDSSRoleViewDTO dto = MDSSRoleDTO.getView(clientRequest, id);
      PermissionViewDTO[] permissions = dto.getPermissions();

      this.getPermissions(dto, permissions);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetPermissions(id);
      }
    }
  }

  private void getPermissions(MDSSRoleViewDTO dto, PermissionViewDTO[] permissions) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    List<PermissionOptionMasterDTO> options = PermissionOptionDTO.allItems(clientRequest);
    Collections.reverse(options);

    PermissionViewDTO header = new PermissionViewDTO(clientRequest);

    req.setAttribute("urlLabel", header.getLabelMd().getDisplayLabel());
    req.setAttribute("permissionLabel", header.getPermissionMd().getDisplayLabel());
    req.setAttribute("options", options);
    req.setAttribute("permissions", Arrays.asList(permissions));
    req.setAttribute("item", dto);

    render("editPermissionComponent.jsp");
  }

  @Override
  public void failGetPermissions(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  @Override
  public void setPermissions(MDSSRoleViewDTO dto, PermissionViewDTO[] permissions) throws IOException, ServletException
  {
    try
    {
      dto.setPermissions(permissions);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failSetPermissions(dto, permissions);
      }
    }
  }

  @Override
  public void failSetPermissions(MDSSRoleViewDTO dto, PermissionViewDTO[] permissions) throws IOException, ServletException
  {
    this.getPermissions(dto, permissions);
  }

  @Override
  public void getUniversalPermissions(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      MDSSRoleViewDTO dto = MDSSRoleDTO.getView(clientRequest, id);
      UniversalPermissionViewDTO[] permissions = dto.getUniversalPermissions();

      this.getUniversalPermissions(dto, permissions);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetPermissions(id);
      }
    }
  }

  private void getUniversalPermissions(MDSSRoleViewDTO dto, UniversalPermissionViewDTO[] permissions) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    UniversalPermissionViewDTO header = new UniversalPermissionViewDTO(clientRequest);
    String labelLabel = header.getLabelMd().getDisplayLabel();
    String permissionLabel = header.getPermissionMd().getDisplayLabel();

    req.setAttribute("permissions", Arrays.asList(permissions));
    req.setAttribute("universalLabel", labelLabel);
    req.setAttribute("permissionLabel", permissionLabel);
    req.setAttribute("item", dto);

    render("editUniversalComponent.jsp");
  }

  @Override
  public void failGetUniversalPermissions(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  @Override
  public void setUniversalPermissions(MDSSRoleViewDTO dto, UniversalPermissionViewDTO[] permissions) throws IOException, ServletException
  {
    try
    {
      dto.setUniversalPermissions(permissions);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failSetUniversalPermissions(dto, permissions);
      }
    }
  }

  @Override
  public void failSetUniversalPermissions(MDSSRoleViewDTO dto, UniversalPermissionViewDTO[] permissions) throws IOException, ServletException
  {
    this.getUniversalPermissions(dto, permissions);
  }
}
