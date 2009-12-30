package dss.vector.solutions.entomology.assay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class LarvaeDiscriminatingDoseAssayController extends LarvaeDiscriminatingDoseAssayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/LarvaeDiscriminatingDoseAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236962666744L;

  public LarvaeDiscriminatingDoseAssayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      this.edit(LarvaeDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id));
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failEdit(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failEdit(id);
    }

  }

  private void edit(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto);
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
    LarvaeDiscriminatingDoseAssayDTO dto = new LarvaeDiscriminatingDoseAssayDTO(clientRequest);

    if (req.getParameter("collection_id") != null)
    {
      dto.setCollection(MosquitoCollectionDTO.get(clientRequest, req.getParameter("collection_id")));
    }

    this.newInstance(dto);
  }

  private void newInstance(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto);

    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayViewQueryDTO query = LarvaeDiscriminatingDoseAssayViewDTO.getPage(clientRequest, null, true, 20, 1);
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
    LarvaeDiscriminatingDoseAssayViewQueryDTO query = LarvaeDiscriminatingDoseAssayViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failUpdate(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void create(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
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

  public void failCreate(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    view(LarvaeDiscriminatingDoseAssayDTO.get(this.getClientRequest(), id));
  }

  public void view(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);

    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto);
  }

  public void failCancel(LarvaeDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  private void setupReferences(LarvaeDiscriminatingDoseAssayDTO dto)
  {
    req.setAttribute("startPoint", dto.getStartPoint());
    req.setAttribute("endPoint", dto.getEndPoint());
    req.setAttribute("generation", dto.getGeneration());
    req.setAttribute("identificationMethod", dto.getIdentificationMethod());
    req.setAttribute("testMethod", dto.getTestMethod());
    req.setAttribute("specie", dto.getSpecie());
    
    String collectionId = dto.getValue(KnockDownAssayDTO.COLLECTION);
    
    if(collectionId != null && !collectionId.equals(""))
    {
      req.setAttribute("collection", MosquitoCollectionDTO.getView(this.getClientRequest(), collectionId));
    }    
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("insecticide", InsecticideDTO.getAll(request));
  }

}
