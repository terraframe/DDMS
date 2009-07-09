package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;

public class MosquitoCollectionPointController extends MosquitoCollectionPointControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollectionPoint/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1235073586764L;

  public MosquitoCollectionPointController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }


  @Override
  public void searchByGeoIdAndDate(String geoId, Date startDate, Date endDate) throws IOException,
      ServletException
  {
    try
    {
      validateParameters(geoId, startDate, endDate);

      ClientRequestIF request = super.getClientRequest();
      MosquitoCollectionPointViewDTO[] collection = MosquitoCollectionPointViewDTO.searchByGeoEntityAndDate(request, geoId, startDate, endDate);

      req.setAttribute("geoEntity", GeoEntityDTO.searchByGeoId(request, geoId));
      req.setAttribute("startDate", startDate);
      req.setAttribute("endDate", endDate);
      req.setAttribute("collection_points", collection);

      render("viewAllComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failStartDate = ( startDate == null ) ? null : startDate.toString();
      String failEndDate = ( endDate == null ) ? null : endDate.toString();

      this.failSearchByGeoIdAndDate(geoId, failStartDate, failEndDate);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failStartDate = ( startDate == null ) ? null : startDate.toString();
      String failEndDate = ( endDate == null ) ? null : endDate.toString();

      this.failSearchByGeoIdAndDate(geoId, failStartDate, failEndDate);
    }

  }

  private void validateParameters(String geoId, Date startDate, Date endDate)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(request, req.getLocale()));
    }

    if (startDate == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredStartDateProblemDTO(request, req.getLocale()));
    }

    if (endDate == null)
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      problems.add(new RequiredEndDateProblemDTO(request, req.getLocale()));
    }

    if (startDate != null && endDate != null && endDate.before(startDate)) 
    {
        ClientRequestIF request = this.getClientSession().getRequest();
    
    	InvalidStartAndEndDatesProblemDTO problem = new InvalidStartAndEndDatesProblemDTO(request, req.getLocale());
    	problem.setStartDate(startDate);
    	problem.setEndDate(endDate);
    	problems.add(problem);
    }
    
    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByGeoIdAndDate(String geoId, String startDate, String endDate)
      throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("geoId", geoId);
    req.setAttribute("startDate", startDate);
    req.setAttribute("endDate", endDate);
    
    this.search();
  }

  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    render("searchComponent.jsp");
  }

}
