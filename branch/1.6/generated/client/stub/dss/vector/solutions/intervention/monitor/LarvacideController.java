package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.TeamMemberDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class LarvacideController extends LarvacideControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Larvacide/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1257372021440L;

  public LarvacideController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(LarvacideDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failCreate(LarvacideDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    LarvacideQueryDTO query = LarvacideDTO.getAllInstances(clientRequest, LarvacideDTO.STARTDATE, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      LarvacideDTO dto = new LarvacideDTO(clientRequest);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
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

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");

      ClientRequestIF clientRequest = super.getClientRequest();
      LarvacideDTO dto = LarvacideDTO.get(clientRequest, id);

      TeamMemberDTO leader = (TeamMemberDTO) AttributeUtil.getValue(LarvacideDTO.TEAMLEADER, dto);

      if (leader != null)
      {
        req.setAttribute("leader", leader.getView());
      }

      req.setAttribute("grid", this.getDataGrid(dto, clientRequest));
      req.setAttribute("item", dto);

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }

  private ViewDataGrid getDataGrid(LarvacideDTO dto, ClientRequestIF clientRequest)
  {
    LarvacideInstanceViewDTO view = new LarvacideInstanceViewDTO(clientRequest);
    view.setValue(LarvacideInstanceViewDTO.CONTROLID, dto.getId());

    LarvacideInstanceViewDTO[] data = dto.getInstanceViews();
    String[] keys = { "ConcreteId", "ControlId", "Target", "Treated", "ControlMethod", "Substance", "Unit", "UnitsUsed" };

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("ConcreteId", new ColumnSetup(true, false));
    map.put("ControlId", new ColumnSetup(true, false));
    map.put("Target", new ColumnSetup(false, true));
    map.put("Treated", new ColumnSetup(false, true));
    map.put("Substance", new ColumnSetup(false, true));
    map.put("ControlMethod", new ColumnSetup(false, true));
    map.put("Unit", new ColumnSetup(false, true));
    map.put("UnitsUsed", new ColumnSetup(false, true));

    return new ViewDataGrid(view, map, keys, data);
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(LarvacideDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();
      this.view(dto.getId());
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

  public void failCancel(LarvacideDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    LarvacideQueryDTO query = LarvacideDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      LarvacideDTO dto = LarvacideDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      render("editComponent.jsp");
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void delete(LarvacideDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
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

  public void failDelete(LarvacideDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void update(LarvacideDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failUpdate(LarvacideDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
