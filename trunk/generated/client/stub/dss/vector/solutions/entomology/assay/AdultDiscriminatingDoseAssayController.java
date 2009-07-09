package dss.vector.solutions.entomology.assay;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.entomology.AssaySexDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.mo.GenerationDTO;
import dss.vector.solutions.mo.IdentificationMethodDTO;
import dss.vector.solutions.mo.ResistanceMethodologyDTO;
import dss.vector.solutions.mo.SpecieDTO;
import dss.vector.solutions.util.ErrorUtility;

public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase
    implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/AdultDiscriminatingDoseAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1235419628808L;

  public AdultDiscriminatingDoseAssayController(HttpServletRequest req, HttpServletResponse resp,
      Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    dto.unlock();

    this.view(dto);
  }

  public void failCancel(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
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
    AdultDiscriminatingDoseAssayQueryDTO query = AdultDiscriminatingDoseAssayDTO.getAllInstances(
        clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    this.view(AdultDiscriminatingDoseAssayDTO.get(clientRequest, id));
  }

  public void view(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + dto.getId());
      return;
    }

    this.setupRequest();

    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
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

  public void failDelete(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void create(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
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

  public void failCreate(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AdultDiscriminatingDoseAssayQueryDTO query = AdultDiscriminatingDoseAssayDTO.getAllInstances(
        clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AdultDiscriminatingDoseAssayDTO dto = new AdultDiscriminatingDoseAssayDTO(clientRequest);

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

  public void update(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
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

  public void failUpdate(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    AdultDiscriminatingDoseAssayDTO dto = AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(),
        id);
    this.setupRequest();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("sex", AssaySexDTO.allItems(request));
    req.setAttribute("generation", Arrays.asList(GenerationDTO.getAllActive(request)));
    req.setAttribute("identificationMethod", Arrays.asList(IdentificationMethodDTO.getAllActive(request)));
    req.setAttribute("testMethod", Arrays.asList(ResistanceMethodologyDTO.getAllActive(request)));
    req.setAttribute("insecticide", InsecticideDTO.getAll(request));
    req.setAttribute("specie", Arrays.asList(SpecieDTO.getAllActive(request)));
  }

}
