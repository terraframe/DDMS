package dss.vector.solutions.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.ApplicationException;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;

public class AbstractCategoryController extends AbstractCategoryControllerBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/query/AbstractCategory/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long  serialVersionUID = 1241158216846L;

  public AbstractCategoryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  protected static void populateRequestForCategory(HttpServletRequest req, AbstractCategoryDTO category, StylesDTO styles)
  {
    try
    {
      req.setAttribute("category", category);
      StylesController.populateRequestForStyles(req, styles, true);
    }
    catch(Throwable e)
    {
      throw new ApplicationException(e);
    }
  }  
  
  @Override
  public void saveCategory(AbstractCategoryDTO category, StylesDTO styles, String layerId) throws IOException,
      ServletException
  {
    try
    {
      category.applyWithStyles(styles, layerId);
      
      List<? extends AbstractCategoryDTO> categories = LayerDTO.getAllHasCategory(this.getClientRequest(), layerId);
      CategorySorter.sort(categories);
      
      for(AbstractCategoryDTO cat : categories)
      {
        req.setAttribute("category", cat);
        if(cat instanceof NonRangeCategoryDTO)
        {
          req.getRequestDispatcher(NonRangeCategoryController.SUMMARY_VIEW).include(req, resp);
        }
        else
        {
          req.getRequestDispatcher(RangeCategoryController.SUMMARY_VIEW).include(req, resp);
        }
      }
    }
    catch(ProblemExceptionDTO e)
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

  public void create(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create AbstractCategoryController");
    render("createComponent.jsp");
  }

  public void delete(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit AbstractCategoryController");
    render("editComponent.jsp");
  }

  public void cancel(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dss.vector.solutions.query.AbstractCategoryDTO dto = dss.vector.solutions.query.AbstractCategoryDTO
          .lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      req.setAttribute("page_title", "Edit AbstractCategoryController");
      render("editComponent.jsp");
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

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(dss.vector.solutions.query.AbstractCategoryDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update AbstractCategoryController");
    render("editComponent.jsp");
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.AbstractCategoryQueryDTO query = dss.vector.solutions.query.AbstractCategoryDTO
        .getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AbstractCategoryController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("item", dss.vector.solutions.query.AbstractCategoryDTO.get(clientRequest, id));
    req.setAttribute("page_title", "View AbstractCategoryController");
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    dss.vector.solutions.query.AbstractCategoryQueryDTO query = dss.vector.solutions.query.AbstractCategoryDTO
        .getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All AbstractCategoryController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
