package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class OperatorSprayController extends OperatorSprayControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/OperatorSpray/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1240853382362L;

  public OperatorSprayController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failCreate(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Operator Sprays");

    render("createComponent.jsp");
  }

  public void update(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failUpdate(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Operator Sprays");
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(OperatorSprayViewDTO.get(this.getClientRequest(), id));
  }

  public void view(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    dto.setModified(true);
    dto.setModified(TeamSprayViewDTO.SPRAYID, true);
    req.setAttribute("status", dto.getStatus());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View Operator Sprays");
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    OperatorSprayViewDTO dto = OperatorSprayDTO.lockView(super.getClientRequest(), id);

    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Operator Sprays");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void cancel(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.view(OperatorSprayDTO.unlockView(getClientRequest(), dto.getSprayId()));
  }

  public void failCancel(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void delete(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.search();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(OperatorSprayViewDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Operator Sprays");
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);
    SprayOperatorViewDTO[] operators = SprayOperatorViewDTO.getAll(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("operators", Arrays.asList(operators));
    req.setAttribute("page_title", "Search for an Operator Spray");

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod,
      SprayOperatorDTO operator) throws IOException, ServletException
  {
    try
    {
      validateParameters(brand, geoId, date, sprayMethod, operator);

      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);

      OperatorSprayViewDTO dto = OperatorSprayViewDTO.searchBySprayData(this.getClientRequest(), geoId,
          date, method, brand, operator.getId());

      if (dto.hasConcrete())
      {
        this.view(dto);
      }
      else
      {
        req.setAttribute("page_title", "New Operator Spray ");
        req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, operator);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, operator);
    }
  }

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date,
      String sprayMethod, SprayOperatorDTO operator)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (brand == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredInsecticideBrandProblemDTO(clientRequest, req.getLocale()));
    }

    if (geoId == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (date == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayDateProblemDTO(clientRequest, req.getLocale()));
    }

    if (sprayMethod == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayMethodProblemDTO(clientRequest, req.getLocale()));
    }

    if (operator == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayOperatorProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date,
      String method, SprayOperatorDTO operator) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);
    SprayOperatorViewDTO[] operators = SprayOperatorViewDTO.getAll(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("operators", Arrays.asList(operators));
    req.setAttribute("page_title", "Search for an Operator Spray");

    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("geoId", geoId);
    req.setAttribute("method", method);
    req.setAttribute("operator", operator);
    req.setAttribute("page_title", "Search for an Operator Spray");

    render("searchComponent.jsp");
  }

}
