package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PersonController extends PersonControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/Person/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240792904565L;

  public PersonController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search(PersonViewDTO person) throws IOException, ServletException
  {
    PersonQueryDTO query = person.searchForDuplicates();
    req.setAttribute("query", query);
    req.setAttribute("newPerson", person);

    // Saving the sex is a pain. This is a shortcut.
    req.setAttribute("sexEnumName", person.getSex().getDisplayLabel());
    render("searchResults.jsp");
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PersonViewDTO view = new PersonViewDTO(clientRequest);
    renderCreate(view);
  }

  @Override
  public void continueNewInstance(PersonViewDTO person) throws IOException, ServletException
  {
    renderCreate(person);
  }

  @Override
  public void createFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      person.apply();
      this.view(person.getPersonId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.renderCreate(person);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.renderCreate(person);
    }
  }

  @Override
  public void failCreateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    renderCreate(person);
  }

  private void renderCreate(PersonViewDTO view) throws IOException, ServletException
  {
    req.setAttribute("sex", view.getSex());
    req.setAttribute("item", view);
    render("createComponent.jsp");
  }

  public void edit(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      PersonViewDTO dto = PersonDTO.lockView(super.getClientRequest(), id);

      renderEdit(dto);
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

  @Override
  public void updateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      person.apply();
      this.view(person.getPersonId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.renderEdit(person);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.renderEdit(person);
    }
  }

  @Override
  public void failUpdateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    renderEdit(person);
  }

  private void renderEdit(PersonViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("sex", dto.getSex());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  @Override
  public void deleteFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      PersonDTO.lock(super.getClientRequest(), person.getPersonId()).delete();
      viewAll();
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failDeleteFromView(person);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDeleteFromView(person);
    }
  }

  @Override
  public void failDeleteFromView(PersonViewDTO person) throws IOException, ServletException
  {
    this.renderEdit(person);
  }

  private void renderView(PersonViewDTO view) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", view.getPersonId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("sex", view.getSex());
    req.setAttribute("item", view);
    render("viewComponent.jsp");
  }

  public void viewPage(String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.PersonQueryDTO query = PersonDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    if (!this.isAsynchronous())
    {
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

      ClientRequestIF clientRequest = super.getClientRequest();
      dss.vector.solutions.PersonQueryDTO query = PersonDTO.getAllInstances(clientRequest, null, true, 20, 1);
      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PersonViewDTO view = PersonDTO.getView(clientRequest, id);

    renderView(view);
  }

  public void failView(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void create(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failEdit(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void delete(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void setupRequest()
  {
  }

  public void failSearch(PersonDTO person) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void editRecipient(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      PersonViewDTO dto = new PersonViewDTO(request);

      if (id != null && !id.equals(""))
      {
        dto = PersonDTO.getView(request, id);
      }

      req.setAttribute("sex", dto.getSex());
      req.setAttribute("item", dto);

      render("editRecipientComponent.jsp");
    }
    catch (Throwable t)
    {
      if (this.isAsynchronous())
      {
        JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
        resp.setStatus(500);
        resp.getWriter().print(jsonE.getJSON());
      }
      else
      {
        this.viewAll();
      }
    }
  }

  @Override
  public void failEditRecipient(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void updateRecipient(PersonViewDTO patient) throws IOException, ServletException
  {
    try
    {
      patient.applyNonDelegates();

      if (!this.isAsynchronous())
      {
        this.viewAll();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      if (this.isAsynchronous())
      {
        JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
        resp.setStatus(500);
        resp.getWriter().print(jsonE.getJSON());
      }
      else
      {
        ErrorUtility.prepareProblems(e, req);
        this.failUpdateRecipient(patient);
      }
    }
    catch (Throwable t)
    {
      if (this.isAsynchronous())
      {
        JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
        resp.setStatus(500);
        resp.getWriter().print(jsonE.getJSON());
      }
      else
      {
        ErrorUtility.prepareThrowable(t, req);
        this.failUpdateRecipient(patient);
      }
    }
  }

  @Override
  public void failUpdateRecipient(PersonViewDTO patient) throws IOException, ServletException
  {
    req.setAttribute("item", patient);

    render("editRecipientComponent.jsp");
  }

}
