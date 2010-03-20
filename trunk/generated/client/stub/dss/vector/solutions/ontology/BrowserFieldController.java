package dss.vector.solutions.ontology;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dss.vector.solutions.util.ErrorUtility;

public class BrowserFieldController extends BrowserFieldControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/BrowserField/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252959711841L;

  public BrowserFieldController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.BrowserFieldQueryDTO query = dss.vector.solutions.ontology.BrowserFieldDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();

      // Map fields to their default terms (if one exists).
      BrowserFieldViewDTO[] results = BrowserFieldDTO.getAsViews(clientRequest);
      List<String> defaultTermIds = new LinkedList<String>();
      Map<String, String> defaultTerms = new HashMap<String, String>();
      for (BrowserFieldViewDTO field : results)
      {
        String defaultTerm = field.getValue(BrowserFieldViewDTO.DEFAULTVALUE);
        if (defaultTerm != null && defaultTerm.length() > 0)
        {
          defaultTermIds.add(defaultTerm);
          defaultTerms.put(field.getBrowserFieldId(), defaultTerm);
        }
        else
        {
          defaultTerms.put(field.getBrowserFieldId(), "");
        }
      }

      // Create a map of termId => TermViewDTO so BrowserFields can
      // dereference their default term values.
      Map<String, TermViewDTO> termMap = new HashMap<String, TermViewDTO>();
      TermViewQueryDTO defaultQueryQuery = TermDTO.getByIds(clientRequest, defaultTermIds.toArray(new String[defaultTermIds.size()]));
      for (TermViewDTO term : defaultQueryQuery.getResultSet())
      {
        termMap.put(term.getTermId(), term);
      }

      BrowserRootViewQueryDTO rootQuery = BrowserRootDTO.getAsViews(clientRequest);
      // Build a map whose values are lists that contain BrowserRootViewDTOs
      // grouped by their owning MdAttribute.
      Map<String, List<BrowserRootViewDTO>> rootMap = new HashMap<String, List<BrowserRootViewDTO>>();
      for (BrowserRootViewDTO view : rootQuery.getResultSet())
      {
        String attrId = view.getMdAttributeId();
        if (!rootMap.containsKey(attrId))
        {
          rootMap.put(attrId, new LinkedList<BrowserRootViewDTO>());
        }

        rootMap.get(attrId).add(view);
      }

      req.setAttribute("fields", results);
      req.setAttribute("rootMap", rootMap);
      req.setAttribute("terms", termMap);
      req.setAttribute("defaultTerms", defaultTerms);

      render("viewAllComponent.jsp");
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failViewAll();
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failViewAll();
    }
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.ontology.BrowserFieldDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void create(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.ontology.BrowserFieldDTO dto = dss.vector.solutions.ontology.BrowserFieldDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      dss.vector.solutions.util.ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto);
    }
    catch (java.lang.Throwable t)
    {
      dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.unlock();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  public void failCancel(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
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

  public void failDelete(dss.vector.solutions.ontology.BrowserFieldDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
