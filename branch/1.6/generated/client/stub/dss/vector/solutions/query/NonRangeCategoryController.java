package dss.vector.solutions.query;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

public class NonRangeCategoryController extends NonRangeCategoryControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/query/NonRangeCategory/";
  public static final String LAYOUT = "/layout.jsp";
  
  public static final String SUMMARY_VIEW = JSP_DIR+"summaryView.jsp";
  
  private static final long serialVersionUID = 1241158170601L;
  
  public NonRangeCategoryController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void update(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update NonRangeCategoryController");
    render("editComponent.jsp");
  }
  public void delete(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }
  public void failDelete(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit NonRangeCategoryController");
    render("editComponent.jsp");
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.query.NonRangeCategoryDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View NonRangeCategoryController");
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create NonRangeCategoryController");
    render("createComponent.jsp");
  }
  public void cancel(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.query.NonRangeCategoryDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      AbstractCategoryDTO category = new NonRangeCategoryDTO(this.getClientRequest());
      StylesDTO styles = new StylesDTO(this.getClientRequest());

      AbstractCategoryController.populateRequestForCategory(this.req, category, styles);
      
      render("createComponent.jsp");
    }
    catch(ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.NonRangeCategoryQueryDTO query = dss.vector.solutions.query.NonRangeCategoryDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All NonRangeCategoryController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.NonRangeCategoryQueryDTO query = dss.vector.solutions.query.NonRangeCategoryDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All NonRangeCategoryController Objects");
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      NonRangeCategoryDTO category = NonRangeCategoryDTO.lock(super.getClientRequest(), id);
      category.lock();
      AbstractCategoryController.populateRequestForCategory(req, category, category.getStyles());
    
      render("editComponent.jsp");
    }
    catch(ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
}
