package dss.vector.solutions.entomology.assay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.entomology.AdultDiscriminatingDoseIntervalGridBuilder;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class AdultDiscriminatingDoseAssayController extends AdultDiscriminatingDoseAssayControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/AdultDiscriminatingDoseAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final long   serialVersionUID = 1235419628808L;

  public AdultDiscriminatingDoseAssayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
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

  public void failCancel(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    AdultDiscriminatingDoseAssayViewQueryDTO query = AdultDiscriminatingDoseAssayViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      this.view(AdultDiscriminatingDoseAssayDTO.get(clientRequest, id));
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

  public void view(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will not create a new object
    ErrorUtility.prepareInformation(this.getClientRequest().getInformation(), req);

    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto, true);
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
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

  public void failCreate(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    AdultDiscriminatingDoseAssayViewQueryDTO query = AdultDiscriminatingDoseAssayViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      AdultDiscriminatingDoseAssayDTO dto = new AdultDiscriminatingDoseAssayDTO(clientRequest);

      String collectionId = req.getParameter("collection_id");

      if (collectionId != null && !collectionId.equals(""))
      {
        dto.setValue(AdultDiscriminatingDoseAssayDTO.COLLECTION, collectionId);
      }

      this.newInstance(dto);
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

  private void newInstance(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto, false);
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

  public void failUpdate(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    edit(dto);
  }

  @Override
  public void cloneAssay(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      // Ensure the user has permissions to create a new adult discriminating
      // dose assay
      new AdultDiscriminatingDoseAssayDTO(request);

      AdultDiscriminatingDoseAssayDTO dto = AdultDiscriminatingDoseAssayDTO.cloneAssay(request, id);

      this.newInstance(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCloneAssay(id);
      }
    }
  }

  @Override
  public void failCloneAssay(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      edit(AdultDiscriminatingDoseAssayDTO.lock(super.getClientRequest(), id));
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

  private void edit(AdultDiscriminatingDoseAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto, false);

    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  private void setupReferences(AdultDiscriminatingDoseAssayDTO dto, boolean readOnly)
  {
    req.setAttribute("sex", AttributeUtil.getValue(AdultDiscriminatingDoseAssayDTO.SEX, dto));
    req.setAttribute("generation", AttributeUtil.getValue(AdultDiscriminatingDoseAssayDTO.GENERATION, dto));
    req.setAttribute("identificationMethod", AttributeUtil.getValue(AdultDiscriminatingDoseAssayDTO.IDENTIFICATIONMETHOD, dto));
    req.setAttribute("testMethod", AttributeUtil.getValue(AdultDiscriminatingDoseAssayDTO.TESTMETHOD, dto));
    req.setAttribute("specie", AttributeUtil.getValue(AdultDiscriminatingDoseAssayDTO.SPECIE, dto));

    String collectionId = dto.getValue(AdultDiscriminatingDoseAssayDTO.COLLECTION);

    if (collectionId != null && !collectionId.equals(""))
    {
      req.setAttribute("collection", MosquitoCollectionDTO.getView(this.getClientRequest(), collectionId));
    }

    req.setAttribute("grid", new AdultDiscriminatingDoseIntervalGridBuilder(this.getClientRequest(), readOnly, dto).build());
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("insecticide", InsecticideDTO.getAll(request));
  }
}
