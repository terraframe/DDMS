package dss.vector.solutions.kaleidoscope.dashboard.layer;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.controller.ServletMethod;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.Controller;
import com.runwaysdk.mvc.Endpoint;
import com.runwaysdk.mvc.ErrorSerialization;
import com.runwaysdk.mvc.InputStreamResponse;
import com.runwaysdk.mvc.RequestParamter;
import com.runwaysdk.mvc.ResponseIF;
import com.runwaysdk.mvc.RestBodyResponse;
import com.runwaysdk.mvc.RestResponse;

import dss.vector.solutions.kaleidoscope.JSONControllerUtil;

@Controller(url = "iconimage")
public class CategoryIconController implements Reloadable
{
  @Endpoint(method = ServletMethod.POST)
  public ResponseIF create(ClientRequestIF request, @RequestParamter(name = "file") MultipartFileParameter file, @RequestParamter(name = "label") String label)
  {
    try
    {
      String filename = file.getFilename();
      InputStream stream = file.getInputStream();

      try
      {
        String result = CategoryIconDTO.create(request, filename, stream, label);

        return new RestBodyResponse(new JSONArray(result));
      }
      finally
      {
        /*
         * Just in case the stream isn't closed by the server method
         */
        stream.close();
      }
    }
    catch (Throwable t)
    {
      return JSONControllerUtil.handleException(t);
    }
  }

  @Endpoint(method = ServletMethod.POST)
  public ResponseIF apply(ClientRequestIF request, @RequestParamter(name = "id") String id, @RequestParamter(name = "file") MultipartFileParameter file, @RequestParamter(name = "label") String label)
  {
    try
    {
      CategoryIconDTO icon = CategoryIconDTO.get(request, id);
      icon.getDisplayLabel().setValue(label);

      if (file != null)
      {
        String filename = file.getFilename();
        InputStream stream = file.getInputStream();

        try
        {
          icon.applyWithFile(filename, stream);
        }
        finally
        {
          /*
           * Just in case the stream isn't closed by the server method
           */
          stream.close();
        }
      }
      else
      {
        icon.apply();
      }

      return new RestBodyResponse(new JSONObject(icon.getAsJSON()));
    }
    catch (Throwable t)
    {
      return JSONControllerUtil.handleException(t);
    }
  }

  @Endpoint(method = ServletMethod.POST)
  public ResponseIF edit(ClientRequestIF request, @RequestParamter(name = "id") String id)
  {
    try
    {
      CategoryIconDTO icon = CategoryIconDTO.lock(request, id);

      return new RestBodyResponse(new JSONObject(icon.getAsJSON()));
    }
    catch (Throwable t)
    {
      return JSONControllerUtil.handleException(t);
    }
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF unlock(ClientRequestIF request, @RequestParamter(name = "id") String id)
  {
    CategoryIconDTO.unlock(request, id);

    return new RestResponse();
  }

  @Endpoint(method = ServletMethod.GET, error = ErrorSerialization.JSON)
  public ResponseIF getAll(ClientRequestIF request) throws JSONException
  {
    String icons = CategoryIconDTO.getAllAsJSON(request);

    JSONObject object = new JSONObject();
    object.put("icons", new JSONArray(icons));

    return new RestBodyResponse(object);
  }

  @Endpoint(method = ServletMethod.POST)
  public ResponseIF remove(ClientRequestIF request, @RequestParamter(name = "id") String id)
  {
    try
    {
      CategoryIconDTO.remove(request, id);

      return new RestBodyResponse("");
    }
    catch (Throwable t)
    {
      return JSONControllerUtil.handleException(t);
    }
  }

  @Endpoint(method = ServletMethod.GET)
  public ResponseIF getCategoryIconImage(ClientRequestIF request, @RequestParamter(name = "id") String id)
  {
    CategoryIconDTO icon = CategoryIconDTO.get(request, id);

    return new InputStreamResponse(icon.getIcon(), "image/png");
  }
}
