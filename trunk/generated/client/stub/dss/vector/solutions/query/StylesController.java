package dss.vector.solutions.query;

import java.awt.GraphicsEnvironment;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.terraframe.mojo.constants.ClientRequestIF;

public class StylesController extends StylesControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/query/Styles/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 285080076;
  
  private static final Object lockObj = new Object();
  
  private static String[] fontFamilies = null;
  
  public StylesController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  private static class FontStyleComparator implements Comparator<FontStyleDTO>, com.terraframe.mojo.generation.loader.Reloadable
  {

    public int compare(FontStyleDTO c1, FontStyleDTO c2)
    {
      return c1.getPriority().compareTo(c2.getPriority());
    }
    
  }  
  
  protected static void populateRequestForStyles(HttpServletRequest req, StylesDTO styles)
  {
    ClientRequestIF request = styles.getRequest();
    
    req.setAttribute("styles", styles);
    
    List<FontStyleDTO> fontStyles =  FontStylesDTO.allItems(request);
    Collections.sort(fontStyles, new FontStyleComparator());
    
    req.setAttribute("allFontStyles", fontStyles);
    req.setAttribute("pointMarker", dss.vector.solutions.query.WellKnownNamesDTO.allItems(request));
    
    synchronized (lockObj) {
      if(fontFamilies == null)
      {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontFamilies = ge.getAvailableFontFamilyNames();
      }
    }
    
 
    req.setAttribute("fontFamilies", fontFamilies);
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
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto);
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
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failDelete(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failDelete(dto);
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
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
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
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto);
    }
    catch(java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto);
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
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
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
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
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
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.StylesQueryDTO query = dss.vector.solutions.query.StylesDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
