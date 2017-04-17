package dss.vector.solutions.entomology;

import dss.vector.solutions.util.ErrorUtility;

public class ResistancePropertyDisplayLabelController extends ResistancePropertyDisplayLabelControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/ResistancePropertyDisplayLabel/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 271429502;

  public ResistancePropertyDisplayLabelController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failCreate(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto = dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO.get(super.getClientRequest(), id);
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
    dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto = new dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failUpdate(dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
    dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO dto = dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO.get(clientRequest, id);
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
    dss.vector.solutions.entomology.ResistancePropertyDisplayLabelQueryDTO query = dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO.getAllInstances(clientRequest, null, true, 20, 1);
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
    dss.vector.solutions.entomology.ResistancePropertyDisplayLabelQueryDTO query = dss.vector.solutions.entomology.ResistancePropertyDisplayLabelDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
