package dss.vector.solutions.ontology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.DataGrid;

public class TermSynonymController extends TermSynonymControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/ontology/TermSynonym/";

  public static final String LAYOUT  = "/layout.jsp";

  public TermSynonymController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  private void search(TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    TermSynonymArrayViewQueryDTO query = TermSynonymArrayViewDTO.getMostRecent(this.getClientRequest());

    req.setAttribute("query", query);
    req.setAttribute("item", view);

    render("searchComponent.jsp");
  }

  @Override
  public void search() throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      ClientRequestIF request = this.getClientRequest();
      TermSynonymArrayViewDTO view = new TermSynonymArrayViewDTO(request);
      search(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearch();
      }
    }
  }

  public void searchByParameters(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String termName, java.lang.String termId, java.lang.String synonymNames) throws java.io.IOException, javax.servlet.ServletException
  {
    TermSynonymArrayViewDTO view = new TermSynonymArrayViewDTO(this.getClientRequest());
    view.setTermName(termName);
    // view.setTermTypeDisplayLabel(termTypeDisplayLabel);
    view.setSynonymNames(synonymNames);

    this.searchByDTO(view, sortAttribute, isAscending, pageSize, pageNumber);
  }

  @Override
  public void searchByDTO(TermSynonymArrayViewDTO view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      isAscending = ( isAscending == null ? true : isAscending );
      pageSize = ( pageSize == null ? 15 : pageSize );
      pageNumber = ( pageNumber == null ? 1 : pageNumber );

      ClientRequestIF request = this.getClientRequest();

      TermSynonymArrayViewQueryDTO query = TermSynonymArrayViewDTO.searchByView(request, view, sortAttribute, isAscending, pageSize, pageNumber);

      req.setAttribute("query", query);
      req.setAttribute("item", view);

      render("searchComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchByDTO(sortAttribute, isAscending, pageSize, pageNumber);
      }
    }
  }

  public void delete(dss.vector.solutions.ontology.TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.delete();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(view);
      }
    }
  }

  public void failDelete(dss.vector.solutions.ontology.TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewTermSyn(view);
  }

  private void addGrid(TermSynonymArrayViewDTO arrayView)
  {
    // Create GeoSynonymDTOs from the concatented ids in the view. The grid will work off the GeoSynonyms.
    List<TermSynonymViewDTO> data = new ArrayList<TermSynonymViewDTO>();

    String concats = arrayView.getSynonymIds();
    if (concats.length() > 0)
    {
      String[] ids = concats.split(",");

      int i = 0;
      while (i < ids.length)
      {
        String id = ids[i];

        TermSynonymDTO dto = TermSynonymDTO.get(this.getClientRequest(), id);

        TermSynonymViewDTO view = new TermSynonymViewDTO(this.getClientRequest());
        view.setSynonymName(dto.getTermName());
        view.setTermSynonymId(id);

        data.add(view);

        ++i;
      }
    }

    DataGrid grid = new TermSynonymGridBuilder(this.getClientRequest(), false, data.toArray(new TermSynonymViewDTO[data.size()])).build();

    req.setAttribute("grid", grid);
  }

  private void viewTermSyn(TermSynonymArrayViewDTO dto) throws IOException, ServletException
  {
    dto.lock();

    req.setAttribute("item", dto);
    req.setAttribute("newInstance", false);
    addGrid(dto);

    render("viewComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      viewTermSyn(TermSynonymArrayViewDTO.getTermSynonym(this.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }

  public void create(TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.apply();

      viewTermSyn(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(view);
      }
    }
  }

  public void failCreate(dss.vector.solutions.ontology.TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }

  public void newInstance(TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      if (view == null)
      {
        ClientRequestIF clientRequest = super.getClientRequest();
        view = new TermSynonymArrayViewDTO(clientRequest);
      }

      req.setAttribute("item", view);
      req.setAttribute("newInstance", true);
      addGrid(view);

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }

  public void failSearchByDTO(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }

  public void failSearch() throws java.io.IOException, javax.servlet.ServletException
  {
    // Uhh.. well, search is the GeoSynonym homepage. If that's not working then... redirect to the DDMS homepage?
    this.resp.sendRedirect("/");
  }

  public void cancel(dss.vector.solutions.ontology.TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      view.unlock();
      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(view);
      }
    }
  }

  public void failCancel(dss.vector.solutions.ontology.TermSynonymArrayViewDTO view) throws java.io.IOException, javax.servlet.ServletException
  {
    this.search();
  }
}
