package dss.vector.solutions.entomology;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.CompositeDataGrid;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.DynamicTermDataGrid;
import dss.vector.solutions.util.yui.TermSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class PupalContainerController extends PupalContainerControllerBase implements Reloadable
{
  private static final long serialVersionUID = -1295265360;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/entomology/PupalContainer/";

  public static final String LAYOUT           = "/layout.jsp";

  public PupalContainerController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

    this.search(new PupalCollectionViewDTO(this.getClientRequest()));
  }

  private void search(PupalCollectionViewDTO view) throws IOException, ServletException
  {
    PupalCollectionViewQueryDTO query = PupalCollectionViewDTO.getMostRecent(this.getClientRequest());

    this.setupDates(view);
    this.setupReferences(view);

    req.setAttribute("query", query);
    req.setAttribute("item", view);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByDTO(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    isAscending = ( isAscending == null ? true : isAscending );
    pageSize = ( pageSize == null ? 15 : pageSize );
    pageNumber = ( pageNumber == null ? 1 : pageNumber );

    ClientRequestIF request = this.getClientRequest();

    PupalCollectionViewQueryDTO query = PupalCollectionViewDTO.searchCollections(request, dto, sortAttribute, isAscending, pageSize, pageNumber);

    this.setupDates(dto);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  @Override
  public void failSearchByDTO(String sortAttribute, String isAscending, String pageSize, String pageNumber, PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    PupalCollectionViewQueryDTO query = PupalCollectionViewDTO.getMostRecent(request);

    this.setupDates(dto);
    req.setAttribute("query", query);
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  @Override
  public void searchByParameters(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String geoId, Date startDate, Date endDate, String premiseType, String collectionId) throws IOException, ServletException
  {
    PupalCollectionViewDTO view = new PupalCollectionViewDTO(this.getClientRequest());
    view.setValue(PupalCollectionViewDTO.GEOENTITY, geoId);
    view.setStartDate(startDate);
    view.setEndDate(endDate);
    view.setCollectionId(collectionId);
    view.setValue(PupalCollectionViewDTO.PREMISETYPE, premiseType);

    this.searchByDTO(sortAttribute, isAscending, pageSize, pageNumber, view);
  }

  @Override
  public void forward(PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      PupalCollectionViewDTO collection = PupalCollectionViewDTO.getCollection(this.getClientRequest(), dto);

      String concreteId = collection.getConcreteId();

      if (concreteId == null || concreteId.equals(""))
      {
        // Ensure the user has permissions to create a PupalCollection
        new PupalCollectionDTO(this.getClientRequest());
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
  public void failForward(PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    this.search(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    // go back to household view after entering person
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);

    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.view(PupalPremiseDTO.getView(super.getClientRequest(), id));
  }

  public void view(PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      this.setupDates(dto);
      this.setupReferences(dto);

      DataGrid grid = this.getGrid(request, dto);

      req.setAttribute("item", dto);
      req.setAttribute("grid", grid);
      req.setAttribute("relationship", new PupalContainerViewDTO(request));

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.search(dto);
      }
    }
  }

  @Override
  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void delete(PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deletePremise();

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
  public void failDelete(PupalCollectionViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }

  private void setupReferences(PupalCollectionViewDTO dto)
  {
    req.setAttribute("premiseType", dto.getPremiseType());
  }

  private void setupDates(PupalCollectionViewDTO dto)
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

  private DataGrid getGrid(ClientRequestIF request, PupalCollectionViewDTO dto)
  {
    PupalContainerViewDTO[] views = dto.getContainers();
    PupalContainerAmountViewDTO[][] amounts = PupalContainerViewDTO.getAmountsForViews(request, views);

    PupalContainerViewDTO view = new PupalContainerViewDTO(request);
    PupalContainerAmountViewDTO amount = new PupalContainerAmountViewDTO(request);

    String[] viewKeys = this.getContainerKeys();
    Map<String, ColumnSetup> viewColumns = this.getViewColumns(viewKeys, 1, false);
    
    ColumnSetup shapeSetup = GridBuilder.getSetup(viewColumns, PupalContainerViewDTO.SHAPE);
    shapeSetup.setIncludeBlank(false);

    String[] amountKeys = this.getAmountKeys();
    Map<String, ColumnSetup> methodColumns = this.getColumns(amountKeys, 1, true);

    ViewDataGrid viewGenerator = new ViewDataGrid(view, viewColumns, viewKeys, views);

    String label = view.getPupaeAmountMd().getDisplayLabel();
    TermSetup setup = new TermSetup(PupalContainerAmountViewDTO.AMOUNT, PupalContainerAmountViewDTO.TERM);

    DynamicTermDataGrid dynamicGenerator = new DynamicTermDataGrid(amount, methodColumns, amountKeys, setup, PupalContainerViewDTO.CLASS, PupalContainerViewDTO.PUPAEAMOUNT, label, amounts);

    CompositeDataGrid generator = new CompositeDataGrid("grid", true, viewGenerator, dynamicGenerator);

    return generator;
  }

  private String[] getContainerKeys()
  {
    String[] keys = new String[] { PupalContainerViewDTO.CONCRETEID, PupalContainerViewDTO.CONTAINERID, PupalContainerViewDTO.CONTAINERTYPE, PupalContainerViewDTO.SHAPE, PupalContainerViewDTO.HEIGHT, PupalContainerViewDTO.WIDTH, PupalContainerViewDTO.CONTAINERLENGTH,
        PupalContainerViewDTO.OPENINGWIDTH, PupalContainerViewDTO.OPENINGLENGTH, PupalContainerViewDTO.DIAMETER, PupalContainerViewDTO.OPENINGDIAMETER, PupalContainerViewDTO.SHADING, PupalContainerViewDTO.LID, PupalContainerViewDTO.ROOF, PupalContainerViewDTO.FILLMETHOD,
        PupalContainerViewDTO.FILLFREQUENCY, PupalContainerViewDTO.DRAWDOWNFREQUENCY, PupalContainerViewDTO.DRAWDOWNPERCENT};

    this.upperFirstCharacter(keys);

    return keys;
  }

  private String[] getAmountKeys()
  {
    String[] keys = new String[] {PupalContainerAmountViewDTO.TERM, PupalContainerAmountViewDTO.AMOUNT};

    this.upperFirstCharacter(keys);

    return keys;
  }

  private Map<String, ColumnSetup> getColumns(String[] keys, int hidden, boolean editable)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < hidden ? new ColumnSetup(true, editable) : new ColumnSetup(false, true) );

      map.put(keys[i], setup);
    }

    return map;
  }

  private Map<String, ColumnSetup> getViewColumns(String[] keys, int hidden, boolean editable)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < hidden ? new ColumnSetup(true, editable) : new ColumnSetup(false, true) );

      if(i == 4)
      {
        setup.setValidator("validateShape");
      }
      else if(i > 4 && i < 9)
      {
        setup.setValidator("validateRectangle");
      }
      else if(i == 9 || i == 10)
      {
        setup.setValidator("validateCircle");
      }

      map.put(keys[i], setup);
    }

    return map;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = CommonGenerationUtil.upperFirstCharacter(array[i]);
    }
  }
}
