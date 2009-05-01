package dss.vector.solutions.irs;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class ApplicationRateController extends ApplicationRateControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240976699468L;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ApplicationRate/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  public ApplicationRateController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void view() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    AreaStandardsViewDTO dto = AreaStandardsViewDTO.getMostRecent(clientRequest);

    if(dto == null)
    {
      dto = new AreaStandardsViewDTO(clientRequest);
    }

    view(dto);
  }

  private void view(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo"));
      return;
    }

    req.setAttribute("dto", dto);
    render("viewComponent.jsp");
  }

  @Override
  public void failView() throws IOException, ServletException
  {
    super.failView();
  }

  @Override
  public void create(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.applyClone();

      view(dto);
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

  @Override
  public void failCreate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }

  @Override
  public void update(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      view(dto);
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

  @Override
  public void failUpdate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }
}
