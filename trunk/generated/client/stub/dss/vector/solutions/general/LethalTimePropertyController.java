package dss.vector.solutions.general;

import java.io.IOException;

import javax.servlet.ServletException;

public class LethalTimePropertyController extends LethalTimePropertyControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/general/LethalTimeProperty/";
  public static final String LAYOUT = "/layout.jsp";
  
  private static final long serialVersionUID = 1237411048787L;
  
  public LethalTimePropertyController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  public void delete(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
  public void failDelete(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void update(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }
  public void failUpdate(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("updateComponent.jsp");
  }
  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.LethalTimePropertyQueryDTO query = dss.vector.solutions.general.LethalTimePropertyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.LethalTimePropertyDTO dto = new dss.vector.solutions.general.LethalTimePropertyDTO(clientRequest);
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dss.vector.solutions.general.LethalTimePropertyDTO.get(clientRequest, id));
    
    render("viewComponent.jsp");
  }
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.general.LethalTimePropertyQueryDTO query = dss.vector.solutions.general.LethalTimePropertyDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void cancel(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }
  public void failCancel(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  public void create(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch(com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }
  public void failCreate(dss.vector.solutions.general.LethalTimePropertyDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.general.LethalTimePropertyDTO dto = dss.vector.solutions.general.LethalTimePropertyDTO.lock(super.getClientRequest(), id);
    req.setAttribute("insecticide", dss.vector.solutions.general.InsecticideDTO.getAll(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
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
      LethalTimePropertyDTO property = LethalTimePropertyDTO.searchByInsecticide(super.getClientRequest(), insecticide);
      
      req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
      req.setAttribute("item", property);
      
      render("viewComponent.jsp");
    }
    catch(UndefinedLethalTimePropertyExceptionDTO e)
    {
      LethalTimePropertyDTO property = new LethalTimePropertyDTO(super.getClientRequest());
      property.setInsecticide(insecticide);
      
      
      req.setAttribute("insecticide", InsecticideDTO.getAll(super.getClientSession().getRequest()));
      req.setAttribute("item", property);
      
      render("createComponent.jsp");      
    }    
  }  

}
