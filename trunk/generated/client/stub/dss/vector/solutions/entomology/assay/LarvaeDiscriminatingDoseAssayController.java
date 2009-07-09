package dss.vector.solutions.entomology.assay;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.LarvaeAgeDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.util.ErrorUtility;

public class LarvaeDiscriminatingDoseAssayController extends LarvaeDiscriminatingDoseAssayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/LarvaeDiscriminatingDoseAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236962666744L;

  public LarvaeDiscriminatingDoseAssayController(HttpServletRequest req, HttpServletResponse resp,
      Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void edit(String id) throws IOException, ServletException
  {
    LarvaeDiscriminatingDoseAssayDTO dto = LarvaeDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id);
    this.setupRequest();
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

    this.setupRequest();
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(".viewAll.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".viewAll.mojo"));
      return;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayQueryDTO query = LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    LarvaeDiscriminatingDoseAssayQueryDTO query = LarvaeDiscriminatingDoseAssayDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
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
    this.setupRequest();
    req.setAttribute("item", dto);

    render("updateComponent.jsp");
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
    this.setupRequest();
    req.setAttribute("item", dto);

    render("createComponent.jsp");
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
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + dto.getId());
      return;
    }

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

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("ageRange", Arrays.asList(LarvaeAgeDTO.getAllActive(request)));
    req.setAttribute("generation", Arrays.asList(GenerationDTO.getAllActive(request)));
    req.setAttribute("identificationMethod", Arrays.asList(IdentificationMethodDTO.getAllActive(request)));
    req.setAttribute("testMethod", Arrays.asList(ResistanceMethodologyDTO.getAllActive(request)));
    req.setAttribute("insecticide", InsecticideDTO.getAll(request));
    req.setAttribute("specie", Arrays.asList(SpecieDTO.getAllActive(request)));
  }

}
