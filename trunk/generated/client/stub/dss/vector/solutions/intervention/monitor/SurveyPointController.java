package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class SurveyPointController extends SurveyPointControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/SurveyPoint/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239641276396L;

  public SurveyPointController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void update(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failUpdate(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    EarthDTO earth = EarthDTO.getEarthInstance(super.getClientSession().getRequest());

    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    SurveyPointQueryDTO query = SurveyPointDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void create(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failCreate(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    EarthDTO earth = EarthDTO.getEarthInstance(super.getClientSession().getRequest());

    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      
      //Ensure the user has permissions to create a survey point
      new SurveyPointDTO(clientRequest);
      
      SurveyPointViewDTO dto = new SurveyPointViewDTO(clientRequest);

      req.setAttribute("item", dto);
      render("createComponent.jsp");
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

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    view(SurveyPointDTO.getView(super.getClientRequest(), id));
  }

  public void view(SurveyPointViewDTO survey) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", survey.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");
    
    String geoId = survey.getGeoId();

    if(geoId != null && !geoId.equals(""))
    {
      req.setAttribute("entity", GeoEntityDTO.searchByGeoId(this.getClientRequest(), geoId));
    }

    req.setAttribute("item", survey);
    req.setAttribute("households", Arrays.asList(survey.getHouseholdViews()));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(SurveyPointDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
  }

  public void failCancel(SurveyPointDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.deleteConcrete();
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

  public void failDelete(SurveyPointViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    EarthDTO earth = EarthDTO.getEarthInstance(super.getClientSession().getRequest());

    req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SurveyPointQueryDTO query = SurveyPointDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      SurveyPointViewDTO dto = SurveyPointDTO.lockView(super.getClientRequest(), id);

      req.setAttribute("item", dto);
      render("editComponent.jsp");
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

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

}
