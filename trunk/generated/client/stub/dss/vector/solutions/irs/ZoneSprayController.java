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

import dss.vector.solutions.util.DateConverter;
import dss.vector.solutions.util.ErrorUtility;

public class ZoneSprayController extends ZoneSprayControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ZoneSpray/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1240860686933L;

  public ZoneSprayController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failCreate(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Zone Sprays");

    render("createComponent.jsp");
  }

  public void update(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Zone Sprays");
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(ZoneSprayViewDTO.get(this.getClientRequest(), id));
  }

  public void view(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    // FIXME: This is a hack to ensure the dto is dirty when its sent back to
    // the server. Remove when nathan has submitted his fix.
    dto.setModified(true);
    dto.setModified(ZoneSprayViewDTO.SPRAYID, true);

    req.setAttribute("status", dto.getStatus());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "View Zone Sprays");
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    ZoneSprayViewDTO dto = ZoneSprayDTO.lockView(super.getClientRequest(), id);

    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Zone Sprays");
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.view(ZoneSprayDTO.unlockView(getClientRequest(), dto.getSprayId()));
  }

  public void failCancel(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void delete(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failDelete(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Zone Sprays");
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("page_title", "Search for an Zone Spray");

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod)
      throws IOException, ServletException
  {

    try
    {
      validateParameters(brand, geoId, date, sprayMethod);

      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);

      ZoneSprayViewDTO dto = ZoneSprayViewDTO.searchBySprayData(this.getClientRequest(), geoId, date,
          method, brand);

      if (dto.hasConcrete())
      {
        this.view(dto);
      }
      else
      {
        req.setAttribute("page_title", "New Zone Spray ");
        req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod);
    }
  }

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod)
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

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date, String method)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("page_title", "Search for an Zone Spray");

    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("geoId", geoId);
    req.setAttribute("method", method);
    req.setAttribute("page_title", "Search for an Zone Spray");

    render("searchComponent.jsp");
  }
}
