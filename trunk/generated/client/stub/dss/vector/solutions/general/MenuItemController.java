package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class MenuItemController extends MenuItemControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String  JSP_DIR          = "WEB-INF/dss/vector/solutions/general/MenuItem/";

  private static final String EDIT_DISEASE_JSP = "editDisease.jsp";

  public static final String  LAYOUT           = "/layout.jsp";

  private static final long   serialVersionUID = 492263447;

  public MenuItemController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(MenuItemDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(MenuItemDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(MenuItemDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(MenuItemDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(MenuItemDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(MenuItemDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      this.edit(MenuItemDTO.lock(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }

  private void edit(MenuItemDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("term", AttributeUtil.getValue(MenuItemDTO.TERM, dto));
      req.setAttribute("url", SystemURLDTO.getURLs(this.getClientRequest()).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failEdit(dto.getId());
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      this.newInstance(new MenuItemDTO(super.getClientRequest()));
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  private void newInstance(MenuItemDTO dto) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("term", AttributeUtil.getValue(MenuItemDTO.TERM, dto));
      req.setAttribute("url", SystemURLDTO.getURLs(this.getClientRequest()).getResultSet());
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(MenuItemDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(MenuItemDTO dto) throws IOException, ServletException
  {
    req.setAttribute("term", AttributeUtil.getValue(MenuItemDTO.TERM, dto));
    req.setAttribute("url", SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      ClientRequestIF clientRequest = super.getClientRequest();
      MenuItemDTO dto = MenuItemDTO.get(clientRequest, id);
      req.setAttribute("term", AttributeUtil.getValue(MenuItemDTO.TERM, dto));
      req.setAttribute("url", SystemURLDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    DiseaseDTO disease = DiseaseDTO.getCurrent(this.getClientRequest());

    req.setAttribute("disease", disease);

    ClientRequestIF clientRequest = super.getClientRequest();
    MenuItemViewQueryDTO query = MenuItemViewDTO.getViewsForDisease(clientRequest);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void editDisease(String id) throws IOException, ServletException
  {
    try
    {
      editDisease(DiseaseDTO.get(this.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failEditDisease(id);
      }
    }
  }

  @Override
  public void failEditDisease(String id) throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  private void editDisease(DiseaseDTO dto) throws IOException, ServletException
  {
    dto.lock();

    req.setAttribute("term", dto.getMenuRoot());
    req.setAttribute("item", dto);

    render(EDIT_DISEASE_JSP);
  }

  @Override
  public void updateDisease(DiseaseDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failUpdateDisease(dto);
      }
    }
  }

  @Override
  public void failUpdateDisease(DiseaseDTO dto) throws IOException, ServletException
  {
    this.editDisease(dto);
  }

  @Override
  public void cancelDisease(String id) throws IOException, ServletException
  {
    DiseaseDTO.unlock(this.getClientRequest(), id);

    this.viewAll();
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    DiseaseDTO disease = DiseaseDTO.getCurrent(this.getClientRequest());
    MenuItemViewQueryDTO query = MenuItemViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);

    req.setAttribute("disease", disease);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
