package dss.vector.solutions.general;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.irs.RequiredGeoIdProblemDTO;
import dss.vector.solutions.surveillance.RequiredYearProblemDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PopulationDataController extends PopulationDataControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/PopulationData/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final String VIEWS            = "views";

  public static final String ITEM             = "item";

  private static final long  serialVersionUID = 1256052009335L;

  public PopulationDataController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

      render("searchComponent.jsp");
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never happen
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void searchForPopulationData(String geoId, Integer yearOfData) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, yearOfData);

      ClientRequestIF request = this.getClientRequest();

      PopulationDataViewDTO[] views = PopulationDataViewDTO.getViews(request, geoId, yearOfData);

      JSONObject calcuatedValues = new JSONObject();

      for(PopulationDataViewDTO view : views)
      {
        calcuatedValues.put(view.getGeoEntity(), new JSONArray(Arrays.asList(view.getCalculatedPopulation()))); 
      }
      
      req.setAttribute("calculatedValues", calcuatedValues);
      
      
      if (views.length > 0)
      {
        req.setAttribute(ITEM, views[views.length - 1]);
      }
      else
      {
        PopulationDataViewDTO item = new PopulationDataViewDTO(request);
        item.setGeoEntity(geoId);
        item.setYearOfData(yearOfData);
        
        req.setAttribute(ITEM, item);
      }

      req.setAttribute(VIEWS, views);

      render("viewComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String year = ( yearOfData == null ? null : yearOfData.toString() );

      this.failSearchForPopulationData(geoId, year);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String year = ( yearOfData == null ? null : yearOfData.toString() );

      this.failSearchForPopulationData(geoId, year);
    }
  }

  @Override
  public void failSearchForPopulationData(String geoId, String yearOfData) throws IOException, ServletException
  {
    this.search();
  }

  private void validateParameters(String geoId, Integer year)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      problems.add(new RequiredGeoIdProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (year == null)
    {
      problems.add(new RequiredYearProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

}
