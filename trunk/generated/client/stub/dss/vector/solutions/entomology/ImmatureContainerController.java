package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class ImmatureContainerController extends ImmatureContainerControllerBase implements Reloadable
{
  private static final long  serialVersionUID = 248754845;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/ImmatureContainer/";

  public static final String LAYOUT           = "/layout.jsp";

  public ImmatureContainerController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    try
    {
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

      this.search(new ImmatureCollectionViewDTO(this.getClientRequest()));
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failSearch();
      }
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  private void search(ImmatureCollectionViewDTO view) throws IOException, ServletException
  {
    ImmatureCollectionViewQueryDTO query = ImmatureCollectionViewDTO.getMostRecent(this.getClientRequest());

    this.setupDates(view);
    this.setupReferences(view);

    req.setAttribute("query", query);
    req.setAttribute("item", view);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByDTO(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    isAscending = ( isAscending == null ? true : isAscending );
    pageSize = ( pageSize == null ? 15 : pageSize );
    pageNumber = ( pageNumber == null ? 1 : pageNumber );

    ClientRequestIF request = this.getClientRequest();

    ImmatureCollectionViewQueryDTO query = ImmatureCollectionViewDTO.searchCollections(request, dto, sortAttribute, isAscending, pageSize, pageNumber);

    this.setupDates(dto);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  @Override
  public void failSearchByDTO(String sortAttribute, String isAscending, String pageSize, String pageNumber, ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    ImmatureCollectionViewQueryDTO query = ImmatureCollectionViewDTO.getMostRecent(request);

    this.setupDates(dto);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByParameters(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String geoId, Date startDate, Date endDate, String premiseType, String taxon, String collectionId) throws IOException, ServletException
  {
    ImmatureCollectionViewDTO view = new ImmatureCollectionViewDTO(this.getClientRequest());
    view.setValue(ImmatureCollectionViewDTO.GEOENTITY, geoId);
    view.setStartDate(startDate);
    view.setEndDate(endDate);
    view.setCollectionId(collectionId);
    view.setValue(ImmatureCollectionViewDTO.PREMISETYPE, premiseType);
    view.setValue(ImmatureCollectionViewDTO.TAXON, taxon);

    this.searchByDTO(sortAttribute, isAscending, pageSize, pageNumber, view);
  }

  @Override
  public void forward(ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ImmatureCollectionViewDTO collection = ImmatureCollectionViewDTO.getCollection(this.getClientRequest(), dto);

      String concreteId = collection.getConcreteId();

      if (concreteId == null || concreteId.equals(""))
      {
        // Ensure the user has permissions to create a ImmatureCollection
        new ImmatureCollectionDTO(this.getClientRequest());
      }

      this.view(collection);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failForward(dto);
      }
    }
  }

  @Override
  public void failForward(ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    this.search(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      // go back to household view after entering person
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);

      utility.checkURL(this.getClass().getSimpleName(), "view");

      this.view(PremiseTaxonDTO.getView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }

  public void view(ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    CollectionContainerViewDTO[] containers = dto.getContainers();

    view(dto, containers);
  }

  private void view(ImmatureCollectionViewDTO dto, CollectionContainerViewDTO[] containers) throws IOException, ServletException
  {
    try
    {
      this.setupDates(dto);
      this.setupReferences(dto);

      req.setAttribute("item", dto);
      req.setAttribute("containers", Arrays.asList(containers));
      req.setAttribute("relationship", new CollectionContainerViewDTO(this.getClientRequest()));

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failView(dto.getTaxonId());
      }
    }
  }

  @Override
  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void update(ImmatureCollectionViewDTO dto, CollectionContainerViewDTO[] containers) throws IOException, ServletException
  {
    try
    {
      dto.applyWithContainers(containers);

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failUpdate(dto, containers);
      }
    }
  }

  @Override
  public void failUpdate(ImmatureCollectionViewDTO dto, CollectionContainerViewDTO[] containers) throws IOException, ServletException
  {
    this.view(dto, containers);
  }

  @Override
  public void delete(ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteTaxon();

      this.search();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }

  @Override
  public void failDelete(ImmatureCollectionViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }

  private void setupReferences(ImmatureCollectionViewDTO dto)
  {
    req.setAttribute("premiseType", dto.getPremiseType());
    req.setAttribute("taxon", dto.getTaxon());
  }

  private void setupDates(ImmatureCollectionViewDTO dto)
  {
    if (dto.getStartDate() != null)
    {
      String startDate = new DefaultConverter(Date.class).format(dto.getStartDate(), req.getLocale());
      req.setAttribute("startDate", startDate);
    }

    if (dto.getEndDate() != null)
    {
      String endDate = new DefaultConverter(Date.class).format(dto.getEndDate(), req.getLocale());
      req.setAttribute("endDate", endDate);
    }
  }

}
