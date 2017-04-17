package dss.vector.solutions.ontology;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;

public class BrowserFieldController extends BrowserFieldControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/BrowserField/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1252959711841L;

  public BrowserFieldController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    BrowserFieldQueryDTO query = BrowserFieldDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failViewAll();
      }
    }
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      BrowserFieldViewDTO field = BrowserFieldDTO.getView(clientRequest, id);

      TermDTO defaultValue = field.getDefaultValue();
      BrowserRootViewDTO[] roots = field.getRoots();

      req.setAttribute("buttonId", field.getMdAttributeId() + "_defaultTermBtn");
      req.setAttribute("defaultValue", defaultValue);
      req.setAttribute("field", field);
      req.setAttribute("roots", Arrays.asList(roots));

      render("viewComponent.jsp");
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

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(BrowserFieldDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(BrowserFieldDTO dto) throws IOException, ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    BrowserFieldDTO dto = BrowserFieldDTO.lock(super.getClientRequest(), id);
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(BrowserFieldDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(BrowserFieldDTO dto) throws IOException, ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(BrowserFieldDTO dto) throws IOException, ServletException
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

  public void failCancel(BrowserFieldDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(BrowserFieldDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(BrowserFieldDTO dto) throws IOException, ServletException
  {
    req.setAttribute("mdAttribute", com.runwaysdk.system.metadata.MdAttributeDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
