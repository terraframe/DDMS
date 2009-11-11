package dss.vector.solutions.ontology;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;

public class TermController extends TermControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/Term/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1253040304944L;

  public TermController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void viewTree() throws IOException, ServletException
  {
    // Views the ontology as a tree structure (default to MO/is_a for now)
    req.getRequestDispatcher(JSP_DIR + "tree.jsp").forward(req, resp);
  }

  @Override
  public void confirmChangeParent(String childId, String parentId) throws IOException, ServletException
  {
    try
    {
      // This will force a ConfirmParentChangeException
      TermDTO.confirmChangeParent(this.getClientRequest(), childId, parentId);
    }
    catch (ConfirmParentChangeExceptionDTO e)
    {
      req.setAttribute("message", e.getLocalizedMessage());
      req.setAttribute("childId", childId);
      req.setAttribute("parentId", parentId);

      req.getRequestDispatcher(JSP_DIR + "confirmChangeParent.jsp").forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void delete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failDelete(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("ontology", dss.vector.solutions.ontology.OntologyDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void update(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failUpdate(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("ontology", dss.vector.solutions.ontology.OntologyDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(
        req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("ontology", dss.vector.solutions.ontology.OntologyDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.ontology.TermDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void create(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCreate(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("ontology", dss.vector.solutions.ontology.OntologyDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.TermQueryDTO query = dss.vector.solutions.ontology.TermDTO
        .getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  @Override
  public void newInstance() throws IOException, ServletException
  {
    try
    {
      req.setAttribute("item", new TermDTO(this.getClientRequest()));
      render("createComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.ontology.TermDTO dto = dss.vector.solutions.ontology.TermDTO.lock(super
          .getClientRequest(), id);
      req.setAttribute("ontology", dss.vector.solutions.ontology.OntologyDTO.getAllInstances(
          super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void cancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      if (!dto.isNewInstance())
      {
        dto.unlock();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.TermQueryDTO query = dss.vector.solutions.ontology.TermDTO
        .getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
