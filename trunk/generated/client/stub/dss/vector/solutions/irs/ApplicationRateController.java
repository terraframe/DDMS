package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ApplicationRateController extends ApplicationRateControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1240976699468L;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ApplicationRate/";

  public static final String LAYOUT           = "/layout.jsp";

  public ApplicationRateController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void view() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    AreaStandardsViewDTO dto = AreaStandardsViewDTO.getMostRecent(clientRequest);

    if (dto == null)
    {
      dto = new AreaStandardsViewDTO(clientRequest);
    }

    view(dto);
  }

  private void view(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    // Map<String, RowSetup> rowMap = new HashMap<String, RowSetup>();
    // rowMap.put("Brand", new RowSetup("getBrandView"));
    // rowMap.put("Nozzle", new RowSetup("getNozzleView"));
    //    
    // Map<String, String> map = new HashMap<String, String>();
    // map.put("Brand", InsecticideBrandViewDTO.class.getName());
    // map.put("Nozzle", NozzleViewDTO.class.getName());
    //
    // String deleteColumn =
    // "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";

    // this.setupBrandGrid();
    this.setupNozzleGrid();
    this.setupConfigurationGrid();

    req.setAttribute("targetUnits", TargetUnitDTO.allItems(this.getClientRequest()));
    req.setAttribute("dto", dto);
    render("viewComponent.jsp");
  }

  private void setupBrandGrid()
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InsecticideBrandViewDTO view = new InsecticideBrandViewDTO(clientRequest);
    InsecticideBrandViewDTO[] data = InsecticideBrandViewDTO.getAll(clientRequest);
    String[] keys = { "InsecticdeId", "BrandName", "ActiveIngredient", "Amount", "Weight", "SachetsPerRefill", "Enabled" };

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("InsecticdeId", new ColumnSetup(true, false));
    map.put("BrandName", new ColumnSetup(false, true));
    map.put("ActiveIngredient", new ColumnSetup(false, true));
    map.put("Amount", new ColumnSetup(false, true, "validateAmount", null, null));
    map.put("Weight", new ColumnSetup(false, true));
    map.put("SachetsPerRefill", new ColumnSetup(false, true));
    map.put("Enabled", new ColumnSetup(false, true));

    req.setAttribute("brandGrid", new ViewDataGrid(view, map, keys, data));
  }

  private void setupNozzleGrid()
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    NozzleViewDTO view = new NozzleViewDTO(clientRequest);
    NozzleViewDTO[] data = NozzleViewDTO.getAll(clientRequest);
    String[] keys = { "NozzleId", "DisplayLabel", "Ratio", "Enabled" };

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("NozzleId", new ColumnSetup(true, false));
    map.put("DisplayLabel", new ColumnSetup(false, true));
    map.put("Ratio", new ColumnSetup(false, true));
    map.put("Enabled", new ColumnSetup(false, true));

    req.setAttribute("nozzleGrid", new ViewDataGrid(view, map, keys, data));
  }

  private void setupConfigurationGrid()
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InsecticideNozzleViewDTO view = new InsecticideNozzleViewDTO(clientRequest);
    InsecticideNozzleViewDTO[] data = InsecticideNozzleViewDTO.getAll(clientRequest);
    String[] keys = { "InsecticideNozzleId", "ConfigurationDate", "Brand", "Nozzle", "Enabled" };

    ColumnSetup brandSetup = new ColumnSetup(false, true, null, InsecticideBrandViewDTO.class.getName(), "getNozzleInsecticideBrands");
    ColumnSetup nozzleSetup = new ColumnSetup(false, true, null, NozzleViewDTO.class.getName(), "getAllActive");

    brandSetup.setGetter("getBrandView");
    nozzleSetup.setGetter("getNozzleView");

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("InsecticideNozzleId", new ColumnSetup(true, false));
    map.put("ConfigurationDate", new ColumnSetup(false, true));
    map.put("Brand", brandSetup);
    map.put("Nozzle", nozzleSetup);
    map.put("Enabled", new ColumnSetup(false, true));
    map.put("BrandLabel", new ColumnSetup(true, false));
    map.put("NozzleLabel", new ColumnSetup(true, false));

    req.setAttribute("configurationGrid", new ViewDataGrid(view, map, keys, data));
  }

  @Override
  public void failView() throws IOException, ServletException
  {
    super.failView();
  }

  @Override
  public void create(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.applyClone();

      view(dto);
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

  @Override
  public void failCreate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }

  @Override
  public void update(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      view(dto);
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

  @Override
  public void failUpdate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }
}
