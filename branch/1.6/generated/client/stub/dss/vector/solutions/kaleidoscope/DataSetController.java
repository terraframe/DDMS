package dss.vector.solutions.kaleidoscope;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;

public class DataSetController extends DataSetControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public DataSetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void getAll() throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();

    try
    {
      String datasets = MappableClassDTO.getAllAsJSON(request);

      JSONObject object = new JSONObject();
      object.put("datasets", new JSONArray(datasets));

      JSONControllerUtil.writeReponse(this.resp, object);
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

      JSONControllerUtil.writeReponse(this.resp, new JSONObject(ds.getAsJSON()));
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

      JSONControllerUtil.writeReponse(this.resp, "");
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

      JSONControllerUtil.writeReponse(this.resp, new JSONObject(mappableClass.getAsJSON()));
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
