package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class KnockDownTimePropertyController extends KnockDownTimePropertyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/KnockDownTimeProperty/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1237411066733L;
  
  public KnockDownTimePropertyController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failDelete(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDelete(dto);
    }
  }
  public void failDelete(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void update(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto);
    }
  }
  public void failUpdate(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("updateComponent.jsp");
  }
  public void edit(String id) throws IOException, ServletException
  {
    KnockDownTimePropertyDTO dto = KnockDownTimePropertyDTO.lock(super.getClientRequest(), id);
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyDTO dto = new KnockDownTimePropertyDTO(clientRequest);
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }
  public void create(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto);
    }
  }
  public void failCreate(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void cancel(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto);
  }
  public void failCancel(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(KnockDownTimePropertyDTO.get(super.getClientRequest(), id));
  }
  
  public void view(KnockDownTimePropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    

    render("viewComponent.jsp");    
  }
  
  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
  
  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyQueryDTO query = KnockDownTimePropertyDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyQueryDTO query = KnockDownTimePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);    
    
    render("viewAllComponent.jsp");
  }
  
  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  public void search() throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    
    render("searchComponent.jsp");
  }

  public void searchByInsecticide(String insecticideId) throws IOException, ServletException
  {
    InsecticideDTO insecticide = InsecticideDTO.get(super.getClientRequest(), insecticideId);
    
    try
    {
      KnockDownTimePropertyDTO property = KnockDownTimePropertyDTO.searchByInsecticide(super.getClientRequest(), insecticide);
      
      req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
      req.setAttribute("item", property);
      
      render("viewComponent.jsp");
    }
    catch(UndefinedKnockDownPropertyExceptionDTO e)
    {
      KnockDownTimePropertyDTO property = new KnockDownTimePropertyDTO(super.getClientRequest());
      property.setInsecticide(insecticide);
      
      
      req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
      req.setAttribute("item", property);
      
      render("createComponent.jsp");      
    }    
  }  
}
