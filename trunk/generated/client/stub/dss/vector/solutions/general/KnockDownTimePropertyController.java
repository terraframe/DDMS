package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;

public class KnockDownTimePropertyController extends KnockDownTimePropertyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/KnockDownTimeProperty/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1237411066733L;
  
  public KnockDownTimePropertyController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }
  public void failDelete(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void update(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("updateComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    KnockDownTimePropertyDTO dto = KnockDownTimePropertyDTO.lock(super.getClientRequest(), id);
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyDTO dto = new KnockDownTimePropertyDTO(clientRequest);
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void create(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void cancel(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto);
  }
  public void failCancel(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(KnockDownTimePropertyDTO.get(super.getClientRequest(), id));
  }
  
  public void view(KnockDownTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
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
    dss.vector.solutions.general.KnockDownTimePropertyQueryDTO query = dss.vector.solutions.general.KnockDownTimePropertyDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownTimePropertyQueryDTO query = KnockDownTimePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);    
    
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  
  //@Override
  public void search() throws IOException, ServletException
  {
    req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
    
    render("searchComponent.jsp");
  }

  //@Override
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
