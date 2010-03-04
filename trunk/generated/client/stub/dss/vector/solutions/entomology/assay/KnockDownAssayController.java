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
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class KnockDownAssayController extends KnockDownAssayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/assay/KnockDownAssay/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1237230661615L;

  public KnockDownAssayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(KnockDownAssayDTO dto) throws IOException, ServletException
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

  public void failCreate(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownAssayViewQueryDTO query = KnockDownAssayViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      KnockDownAssayDTO dto = KnockDownAssayDTO.lock(super.getClientRequest(), id);

      this.edit(dto);
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

  private void edit(KnockDownAssayDTO dto) throws IOException, ServletException
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

  public void delete(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    KnockDownAssayViewQueryDTO query = KnockDownAssayViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
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
      KnockDownAssayDTO dto = new KnockDownAssayDTO(clientRequest);

      if (req.getParameter("collection_id") != null)
      {
        dto.setCollection(MosquitoCollectionDTO.get(clientRequest, req.getParameter("collection_id")));
      }

      this.newInstance(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failNewInstance();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failNewInstance();
    }

  }

  private void newInstance(KnockDownAssayDTO dto) throws IOException, ServletException
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

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(KnockDownAssayDTO.get(super.getClientRequest(), id));
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failView(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failView(id);
    }
  }

  private void view(KnockDownAssayDTO dto) throws IOException, ServletException
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

  public void update(KnockDownAssayDTO dto) throws IOException, ServletException
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

  public void failUpdate(KnockDownAssayDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  private void setupReferences(KnockDownAssayDTO dto)
  {
    req.setAttribute("sex", AttributeUtil.getValue(KnockDownAssayDTO.SEX, dto));
    req.setAttribute("generation", AttributeUtil.getValue(KnockDownAssayDTO.GENERATION, dto));
    req.setAttribute("identificationMethod", AttributeUtil.getValue(KnockDownAssayDTO.IDENTIFICATIONMETHOD, dto));
    req.setAttribute("testMethod", AttributeUtil.getValue(KnockDownAssayDTO.TESTMETHOD, dto));
    req.setAttribute("specie", AttributeUtil.getValue(KnockDownAssayDTO.SPECIE, dto));

    String collectionId = dto.getValue(KnockDownAssayDTO.COLLECTION);

    if (collectionId != null && !collectionId.equals(""))
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
