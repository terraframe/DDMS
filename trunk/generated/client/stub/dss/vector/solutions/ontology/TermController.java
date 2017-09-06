package dss.vector.solutions.ontology;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.transport.conversion.json.BusinessDTOToJSON;
import com.runwaysdk.transport.conversion.json.JSONReturnObject;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.LocalizationFacadeDTO;

public class TermController extends TermControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/ontology/Term/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1253040304944L;

  public TermController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void fetchAllParents(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      JSONArray json = new JSONArray();
      
      Iterator<? extends TermViewDTO> it = TermDTO.getAncestors(getClientRequest(), id).getResultSet().iterator();
      
      while (it.hasNext())
      {
        TermViewDTO ancestor = it.next();
        
        JSONObject ancestorJSON = new JSONObject();
//        ancestorJSON.put("view", BusinessDTOToJSON.getConverter(ancestor).populate());
        ancestorJSON.put("id", ancestor.getTermId());
        
        JSONArray childrenJSON = new JSONArray();
        List<? extends TermViewDTO> children = TermDTO.getOntologyChildren(this.getClientRequest(), ancestor.getTermId(), false).getResultSet();
        for (TermViewDTO child: children)
        {
          childrenJSON.put(BusinessDTOToJSON.getConverter(child).populate());
        }
        
        ancestorJSON.put("children", childrenJSON);
        
        json.put(ancestorJSON);
      }
      
      resp.getWriter().print(new JSONReturnObject(json).toString());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void delete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // req.setAttribute("ontology",
    // dss.vector.solutions.ontology.OntologyDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  @Override
  public void update(dss.vector.solutions.ontology.TermDTO dto, Boolean inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.updateFromTree(inactive);
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failUpdate(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // req.setAttribute("ontology",
    // dss.vector.solutions.ontology.OntologyDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    // req.setAttribute("ontology",
    // dss.vector.solutions.ontology.OntologyDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.ontology.TermDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  @Override
  public void create(dss.vector.solutions.ontology.TermDTO dto, String parentId, Boolean inactive) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.applyWithParent(parentId, false, null, inactive);

      resp.getWriter().print(dto.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCreate(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // req.setAttribute("ontology",
    // dss.vector.solutions.ontology.OntologyDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.TermQueryDTO query = dss.vector.solutions.ontology.TermDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  @Override
  public void newInstance() throws IOException, ServletException
  {
    try
    {
      TermDTO dto = new TermDTO(this.getClientRequest());
      populateReqForTerm(dto);

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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  private void populateReqForTerm(TermDTO dto)
  {
    InactivePropertyDTO prop = dto.getInactiveByDisease();
    boolean inactive = prop.getInactive();
    String inactiveLabel = prop.getInactiveMd().getDisplayLabel();

    req.setAttribute("inactive", inactive);
    req.setAttribute("inactiveLabel", inactiveLabel);

    req.setAttribute("item", dto);

    req.setAttribute("isRoot", dto instanceof RootTermDTO);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.ontology.TermDTO dto = dss.vector.solutions.ontology.TermDTO.lock(super.getClientRequest(), id);
      populateReqForTerm(dto);

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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void cancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
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
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void failCancel(dss.vector.solutions.ontology.TermDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.ontology.TermQueryDTO query = dss.vector.solutions.ontology.TermDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void exportTerm(java.lang.String term) throws java.io.IOException, javax.servlet.ServletException
  {
    OutputStream os = resp.getOutputStream();
    
    resp.setContentType("application/xls");
    resp.addHeader("Content-Disposition", "attachment;filename=\"export.xls\"");
    
    TermDTO.export(this.getClientRequest(), term, os);
    
    os.close();
  }
  
  @Override
  public void importTerms(MultipartFileParameter importFile) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = (ClientRequestIF) req.getAttribute(ClientConstants.CLIENTREQUEST);

    resp.setContentType("text/html;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    
    try
    {
      // No file was uploaded
      if (!ServletFileUpload.isMultipartContent(req) || importFile == null || importFile.getSize() == 0)
      {
        throw new RuntimeException(LocalizationFacadeDTO.getFromBundles(clientRequest, "File_Required"));        
      }
      else
      {
        TermDTO.importTerms(clientRequest, importFile.getInputStream());
        
        resp.getWriter().write(LocalizationFacadeDTO.getFromBundles(clientRequest, "File_Upload_Success"));
      }
    }
    catch (Exception e)
    {
      resp.getWriter().write(e.getLocalizedMessage());
    }
  }
}
