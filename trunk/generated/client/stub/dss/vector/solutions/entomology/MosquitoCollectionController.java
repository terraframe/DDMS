package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.mo.CollectionMethodDTO;
import dss.vector.solutions.util.DateConverter;
import dss.vector.solutions.util.ErrorUtility;

public class MosquitoCollectionController extends MosquitoCollectionControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/MosquitoCollection/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1235073590401L;

  public MosquitoCollectionController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void delete(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failDelete(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", CollectionMethodDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Mosquito Collections");
    render("editComponent.jsp");
  }

  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionDTO dto = new MosquitoCollectionDTO(clientRequest);
    req.setAttribute("MosquitoCollection_collectionMethod", CollectionMethodDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Mosquito Collections");
    render("createComponent.jsp");
  }

  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failUpdate(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", CollectionMethodDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Error - Update a Mosquito Collection");
    render("editComponent.jsp");
  }

  public void failSearchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity,
      java.lang.String collectionDate) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + id);
      return;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    // req.setAttribute("MosquitoCollection_collectionMethod",
    // CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", MosquitoCollectionDTO.get(clientRequest, id));
    render("viewComponent.jsp");
  }

  public void viewAssays(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    // req.setAttribute("MosquitoCollection_collectionMethod",
    // CollectionMethodDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO.get(
        clientRequest, id));
    render("viewAssaysComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, null, true,
        20, 1);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Mosquito Collections");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest,
        sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    req.setAttribute("page_title", "View All Mosquito Collections");
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    MosquitoCollectionDTO dto = MosquitoCollectionDTO.lock(super.getClientRequest(), id);
    req.setAttribute("MosquitoCollection_collectionMethod", CollectionMethodDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Mosquito Collection");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void create(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failCreate(MosquitoCollectionDTO dto) throws java.io.IOException,
      javax.servlet.ServletException
  {
    req.setAttribute("MosquitoCollection_collectionMethod", CollectionMethodDTO.getAllInstances(
        super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    // req.setAttribute("AbstractMosquitoCollection_geoEntity",
    // dss.vector.solutions.geo.generated.GeoEntityDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "ERROR - Create Mosquito Collection");
    render("createComponent.jsp");
  }

  public void searchByGeoIdAndDate(String geoId, Date collectionDate, CollectionMethodDTO collectionMethod) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, collectionDate, collectionMethod);

      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(super.getClientRequest(), geoId);

      this.searchByGeoEntityAndDate(geoEntity, collectionDate, collectionMethod);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = (collectionDate == null) ? null : collectionDate.toString();
      String failGeoId = (geoId == null) ? null : geoId.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failDate, collectionMethod);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = (collectionDate == null) ? null : collectionDate.toString();
      String failGeoId = (geoId == null) ? null : geoId.toString();

      this.failSearchByGeoIdAndDate(failGeoId, failDate, collectionMethod);
    }
  }

  public void failSearchByGeoIdAndDate(String geoId, String collectionDate, CollectionMethodDTO collectionMethod) throws java.io.IOException, javax.servlet.ServletException
  {
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll( super.getClientSession().getRequest());
    req.setAttribute("MosquitoCollection_collectionMethod", Arrays.asList(methods));

    if (geoId == null || collectionDate == null)
    {
      this.search();

      return;
    }

    try
    {
      Date d = (Date) new DateConverter("Collection Date").parse(collectionDate, this.getRequest().getLocale());

      this.searchByGeoIdAndDate(geoId, d, collectionMethod);
    }
    catch (Exception e)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, "createDate", false, 10, 1);
      req.setAttribute("query", query);

      render("searchComponent.jsp");
    }
  }

  public void search() throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(super.getClientSession().getRequest());
    MosquitoCollectionQueryDTO query = MosquitoCollectionDTO.getAllInstances(clientRequest, "createDate", false, 10, 1);

    req.setAttribute("MosquitoCollection_collectionMethod", Arrays.asList(methods));
    req.setAttribute("query", query);

    req.setAttribute("page_title", "Search For Mosquito Collections");

    render("searchComponent.jsp");
  }

  public void searchByGeoEntityAndDate(GeoEntityDTO geoEntity, Date collectionDate, CollectionMethodDTO collectionMethod) throws IOException, ServletException
  {
    try
    {
      CollectionMethodDTO[] methods = CollectionMethodDTO.getAll(super.getClientSession().getRequest());
      MosquitoCollectionDTO collection = MosquitoCollectionDTO.searchByGeoEntityAndDateAndCollectionMethod(super.getClientRequest(), geoEntity, collectionDate, collectionMethod);
      String jsp = "viewComponent.jsp";

      if (collection == null)
      {
        collection = new MosquitoCollectionDTO(super.getClientRequest());
        collection.setDateCollected(collectionDate);
        collection.setGeoEntity(geoEntity);
        collection.setCollectionMethod(collectionMethod);
        req.setAttribute("page_title", "Mosquito_Collection_Not_Found_Creating_New");
        jsp = "createComponent.jsp";
      }


      req.setAttribute("MosquitoCollection_collectionMethod", Arrays.asList(methods));
      req.setAttribute("item", collection);

      render(jsp);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchByGeoEntityAndDate(geoEntity, collectionDate.toString(), collectionMethod);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchByGeoEntityAndDate(geoEntity, collectionDate.toString(), collectionMethod);
    }

  }

  private void validateParameters(String geoId, Date collectionDate, CollectionMethodDTO collectionMethod)
  {
    if (geoId == null)
    {
      String msg = "Geo Id requires a value";
      throw new RuntimeException(msg);
    }

    if (collectionDate == null)
    {
      String msg = "Collection Date requires a value";
      throw new RuntimeException(msg);
    }

    if (collectionMethod == null)
    {
      String msg = "Collection Method requires a value";
      throw new RuntimeException(msg);
    }
  }
}
