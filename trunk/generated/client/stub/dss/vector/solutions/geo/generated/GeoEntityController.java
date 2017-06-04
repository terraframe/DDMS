package dss.vector.solutions.geo.generated;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.transport.conversion.json.BusinessDTOToJSON;
import com.runwaysdk.transport.conversion.json.JSONReturnObject;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.geo.GeoEntityViewDTO;
import dss.vector.solutions.util.ErrorUtility;

public class GeoEntityController extends GeoEntityControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/geo/generated/GeoEntity/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1255627160683L;

  public GeoEntityController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void update(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }
  
  @Override
  public void fetchAllParents(String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      JSONArray json = new JSONArray();
      
      GeoEntityViewDTO[] ancestors = GeoEntityDTO.getAncestors(getClientRequest(), id);
      
      for (int i = 0; i < ancestors.length; ++i)
      {
        GeoEntityViewDTO ancestor = ancestors[i];
        
        JSONObject ancestorJSON = new JSONObject();
//        ancestorJSON.put("view", BusinessDTOToJSON.getConverter(ancestor).populate());
        ancestorJSON.put("id", ancestor.getGeoEntityId());
        
        JSONArray childrenJSON = new JSONArray();
        List<? extends GeoEntityDTO> children = GeoEntityDTO.getAllLocatedInGeoEntity(this.getClientRequest(), ancestor.getGeoEntityId());
        for (GeoEntityDTO child: children)
        {
          GeoEntityViewDTO viewChild = new GeoEntityViewDTO(this.getClientRequest());
          viewChild.setGeoEntityId(child.getId());
          viewChild.setGeoId(child.getGeoId());
          viewChild.setEntityLabel(child.getEntityLabel().getValue());
          viewChild.setTypeDisplayLabel(child.getTypeDisplayLabel());
          viewChild.setActivated(child.getActivated());
          
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

  public void failUpdate(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dto.getTerm());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.GeoEntityDTO dto = dss.vector.solutions.geo.generated.GeoEntityDTO.get(clientRequest, id);
    req.setAttribute("term", dto.getTerm());
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.GeoEntityQueryDTO query = dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.geo.generated.GeoEntityQueryDTO query = dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    dss.vector.solutions.geo.generated.GeoEntityDTO dto = dss.vector.solutions.geo.generated.GeoEntityDTO.lock(super.getClientRequest(), id);
    req.setAttribute("term", dto.getTerm());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void cancel(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void create(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (java.lang.Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dto.getTerm());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  
  public void delete(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(dss.vector.solutions.geo.generated.GeoEntityDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("term", dto.getTerm());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }
}
