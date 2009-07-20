package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class GeoTargetController extends GeoTargetControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/GeoTarget/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240267414799L;

  public GeoTargetController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(GeoEntityDTO geoEntity, MalariaSeasonDTO season, Boolean showChildren)
      throws IOException, ServletException
  {
    try
    {
      validateParameters(geoEntity, season);

      ClientRequestIF clientRequest = super.getClientRequest();

      List<String> geoEntityIds = new ArrayList<String>();

      // Get Immediate Spray Children returns all of the immediate children which can
      // have spray targets associated, and the individual target
      for (GeoEntityDTO child : geoEntity.getImmediateSprayChildren())
      {
        geoEntityIds.add(child.getId());
      }

      geoEntityIds.add(geoEntity.getId());

      String[] array = geoEntityIds.toArray(new String[geoEntityIds.size()]);

      GeoTargetDTO item = new GeoTargetDTO(clientRequest);
      item.setSeason(season);
      item.setGeoEntity(geoEntity);

      GeoTargetViewDTO[] geoTargetViews = GeoTargetViewDTO.getGeoTargets(clientRequest, array, season);

      req.setAttribute("item", item);
      req.setAttribute("geoTargetViews", geoTargetViews);
      render("viewComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.viewAll();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.viewAll();
    }
  }

  private void validateParameters(GeoEntityDTO geoEntity, MalariaSeasonDTO season)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoEntity == null)
    {
      problems.add(new RequiredGeoIdProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (season == null)
    {
      problems.add(new RequiredSeasonProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failView(GeoEntityDTO geoEntinty, Integer year, Boolean showChildren) throws IOException,
      ServletException
  {
    this.viewAll();

  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("seasons", Arrays.asList(MalariaSeasonDTO.getAll(request)));
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
