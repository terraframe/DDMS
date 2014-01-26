package dss.vector.solutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.general.EpiConfigurationDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PropertyController extends PropertyControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/Property/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236023121846L;

  public PropertyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void viewPackage(String propertyPackage) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PropertyQueryDTO query = PropertyDTO.getAllByPackage(clientRequest, propertyPackage);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(PropertyDTO.get(this.getClientRequest(), id));
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

  private void view(PropertyDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    String pack = dto.getPropertyPackage();
    boolean isCaseConfiguration = pack.equals(PropertyInfo.MONITOR_PACKAGE);

    req.setAttribute("isCaseConfiguration", isCaseConfiguration);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(PropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      // IMPORTANT: This action first locks and then applies the dto. As such,
      // it is a completely thread unsafe mechanism where the last user who
      // submitts will have their data perserved. Therefore it should on be used
      // for updating the EPI_WEEK property
      PropertyDTO locked = PropertyDTO.lock(this.getClientRequest(), dto.getId());
      locked.setPropertyValue(dto.getPropertyValue());
      locked.apply();

      this.newInstance();
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

  public void failCreate(PropertyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("configuration", new EpiConfigurationDTO(this.getClientRequest()));
    req.setAttribute("item", dto);
    render("epiWeekComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PropertyDTO dto = PropertyDTO.getByPackageAndName(clientRequest, PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    req.setAttribute("item", dto);
    req.getRequestDispatcher(dir + "epiWeekExcel.jsp").forward(req, resp);
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void cancel(PropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();

      this.view(dto);
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

  public void localCancel(LocalPropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();

      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failLocalCancel(dto);
      }
    }
  }

  public void failCancel(PropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void failLocalCancel(LocalPropertyDTO dto) throws IOException, ServletException
  {
    this.localEdit(dto.getId());
  }

  public void viewAll() throws IOException, ServletException
  {
    try
    {
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

      ClientRequestIF clientRequest = super.getClientRequest();

      DefaultGeoEntityQueryDTO query2 = DefaultGeoEntityDTO.getAllInstances(clientRequest, null, true, 20, 1);
      LocalPropertyQueryDTO local = LocalPropertyDTO.getAllInstances(clientRequest, null, true, 20, 1);

      req.setAttribute("query2", query2);
      req.setAttribute("local", local);

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
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void update(PropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      // dto.lock();
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

  public void localUpdate(LocalPropertyDTO dto) throws IOException, ServletException
  {
    try
    {
      // dto.lock();
      dto.apply();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failLocalUpdate(dto);
      }
    }
  }

  public void failUpdate(PropertyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void failLocalUpdate(LocalPropertyDTO dto) throws IOException, ServletException
  {
    this.localEdit(dto.getId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      PropertyDTO dto = PropertyDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void localEdit(String id) throws IOException, ServletException
  {
    try
    {
      LocalPropertyDTO dto = LocalPropertyDTO.lock(super.getClientRequest(), id);
      req.setAttribute("item", dto);
      render("localEditComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failLocalEdit(id);
      }
    }
  }

  public void failLocalEdit(String id) throws IOException, ServletException
  {
    failEdit(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      /*
       * PropertyDTO dto = new PropertyDTO(clientRequest);
       */
      PropertyDTO dto = PropertyDTO.getByPackageAndName(clientRequest, PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

      req.setAttribute("item", dto);
      req.setAttribute("configuration", new EpiConfigurationDTO(clientRequest));
      render("epiWeekComponent.jsp");
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

  public void failNewInstance() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void editFlag() throws IOException, ServletException
  {
    try
    {
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "editFlag");

      render("editFlagComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEditFlag();
      }
    }
  }

  @Override
  public void failEditFlag() throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index").forward(this.req, this.resp);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setFlag(MultipartFileParameter upfile) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      // Ensure the user has permission to update Properties
      new PropertyDTO(request);

      if (upfile == null || upfile.getSize() == 0)
      {
        throw new FileRequiredExceptionDTO(request, req.getLocale());
      }

      this.setFlag(upfile.getInputStream());

      this.editFlag();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSetFlag(upfile);
      }
    }
  }

  @Override
  public void failSetFlag(MultipartFileParameter upfile) throws IOException, ServletException
  {
    this.editFlag();
  }

  private void setFlag(InputStream stream)
  {
    // Upload the template file to the vault
    try
    {
      String directory = DeployProperties.getDeployPath() + "/imgs/flags/";

      File dir = new File(directory);

      if (!dir.exists())
      {
        dir.mkdirs();
      }

      File flag = new File(directory + "current");

      if (!flag.exists())
      {
        boolean created = flag.createNewFile();

        if (created)
        {
          FileIO.write(new FileOutputStream(flag), stream);
        }
      }
      else
      {
        FileIO.write(new FileOutputStream(flag), stream);
      }
    }
    catch (IOException e)
    {
      UnableToUploadFlagExceptionDTO exception = new UnableToUploadFlagExceptionDTO(this.getClientRequest(), req.getLocale());
      exception.setReason(e.getLocalizedMessage());

      throw exception;
    }
  }

}
