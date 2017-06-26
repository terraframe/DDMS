package dss.vector.solutions.kaleidoscope;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;

public class DataSetController extends DataSetControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/dss/vector/solutions/kaleidoscope/userMenu/";

  public static final String LAYOUT  = "/layout.jsp";

  public DataSetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void management() throws IOException, ServletException
  {
    this.render("dataset-management.jsp");
  }

  @Override
  public void getAll() throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      String datasets = MappableClassDTO.getAllAsJSON(request);

      JSONObject object = new JSONObject(datasets);

      JSONControllerUtil.writeDirectReponse(this.resp, object);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  @Override
  public void applyDatasetUpdate(String dataset) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      JSONObject dsJSONObj = new JSONObject(dataset);
      String dsId = dsJSONObj.getString("id");

      MappableClassDTO ds = MappableClassDTO.lock(request, dsId);
      MappableClassDTO.applyDatasetUpdate(request, dataset);
      ds.unlock();

      JSONControllerUtil.writeDirectReponse(this.resp, new JSONObject(ds.getAsJSON()));
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  @Override
  public void remove(String id) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      MappableClassDTO.remove(request, id);

      JSONControllerUtil.writeDirectReponse(this.resp, "");
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  public void edit(String id) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      MappableClassDTO mappableClass = MappableClassDTO.lock(request, id);

      JSONControllerUtil.writeDirectReponse(this.resp, new JSONObject(mappableClass.getAsJSON()));
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  public void apply(String config) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      MappableClassDTO.applyDatasetUpdate(request, config);

      JSONControllerUtil.writeReponse(this.resp);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }

  public void cancel(String id) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      MappableClassDTO.unlock(request, id);

      JSONControllerUtil.writeReponse(this.resp);
    }
    catch (Throwable t)
    {
      JSONControllerUtil.handleException(this.resp, t, this.getClientRequest());
    }
  }
}
