package dss.vector.solutions.kaleidoscope;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.ValueObjectDTO;
import com.runwaysdk.business.ValueQueryDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.controller.ServletMethod;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.Controller;
import com.runwaysdk.mvc.Endpoint;
import com.runwaysdk.mvc.ErrorSerialization;
import com.runwaysdk.mvc.RedirectResponse;
import com.runwaysdk.mvc.RequestParamter;
import com.runwaysdk.mvc.ResponseIF;
import com.runwaysdk.mvc.RestBodyResponse;

import dss.vector.solutions.geo.generated.GeoEntityDTO;

@Controller(url = "uploader")
public class DataUploaderController implements Reloadable
{
  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF getAttributeInformation(ClientRequestIF request, @RequestParamter(name = "file") MultipartFileParameter file) throws IOException, JSONException
  {
    String fileName = file.getFilename();
    InputStream stream = file.getInputStream();

    try
    {
      JSONObject object = new JSONObject();
      object.put("information", new JSONObject(DataUploaderDTO.getAttributeInformation(request, fileName, stream)));
      object.put("options", new JSONObject(DataUploaderDTO.getOptionsJSON(request)));
      // object.put("classifiers", new JSONArray(TermDTO.getCategoryTermsAsJSON(request)));

      return new RestBodyResponse(object);
    }
    finally
    {
      /*
       * Just in case the stream isn't closed by the server method
       */
      stream.close();
    }
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF importData(ClientRequestIF request, @RequestParamter(name = "configuration") String configuration) throws JSONException
  {
    String result = DataUploaderDTO.importData(request, configuration);

    return new RestBodyResponse(new JSONObject(result));
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF cancelImport(ClientRequestIF request, @RequestParamter(name = "configuration") String configuration)
  {
    DataUploaderDTO.cancelImport(request, configuration);

    return new RestBodyResponse("");
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF getSavedConfiguration(ClientRequestIF request, @RequestParamter(name = "id") String id, @RequestParamter(name = "sheetName") String sheetName) throws JSONException
  {
    String configuration = DataUploaderDTO.getSavedConfiguration(request, id, sheetName);

    JSONObject object = new JSONObject();
    object.put("datasets", new JSONObject(configuration));

    return new RestBodyResponse(object);
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF validateDatasetName(ClientRequestIF request, @RequestParamter(name = "name") String name, @RequestParamter(name = "id") String id)
  {
    DataUploaderDTO.validateDatasetName(request, name, id);

    return new RestBodyResponse("");
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF validateCategoryName(ClientRequestIF request, @RequestParamter(name = "name") String name, @RequestParamter(name = "id") String id)
  {
    DataUploaderDTO.validateCategoryName(request, name, id);

    return new RestBodyResponse("");
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF createGeoEntity(ClientRequestIF request, @RequestParamter(name = "parentId") String parentId, @RequestParamter(name = "universalId") String universalId, @RequestParamter(name = "label") String label) throws JSONException
  {
    String entityId = DataUploaderDTO.createGeoEntity(request, parentId, universalId, label);

    JSONObject object = new JSONObject();
    object.put("entityId", entityId);

    return new RestBodyResponse(object);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF createGeoEntitySynonym(ClientRequestIF request, @RequestParamter(name = "entityId") String entityId, @RequestParamter(name = "label") String label) throws JSONException
  {
    String response = DataUploaderDTO.createGeoEntitySynonym(request, entityId, label);

    JSONObject object = new JSONObject(response);

    return new RestBodyResponse(object);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF deleteGeoEntity(ClientRequestIF request, @RequestParamter(name = "entityId") String entityId)
  {
    DataUploaderDTO.deleteGeoEntity(request, entityId);

    return new RestBodyResponse("");
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF deleteGeoEntitySynonym(ClientRequestIF request, @RequestParamter(name = "synonymId") String synonymId)
  {
    DataUploaderDTO.deleteGeoEntitySynonym(request, synonymId);

    return new RestBodyResponse("");
  }

  @Endpoint(error = ErrorSerialization.JSON)
  public ResponseIF getGeoEntitySuggestions(ClientRequestIF request, @RequestParamter(name = "parentId") String parentId, @RequestParamter(name = "universalId") String universalId, @RequestParamter(name = "text") String text, @RequestParamter(name = "limit") Integer limit) throws JSONException
  {
    JSONArray response = new JSONArray();

    ValueQueryDTO query = GeoEntityDTO.getGeoEntitySuggestions(request, parentId, universalId, text, limit);
    List<ValueObjectDTO> results = query.getResultSet();

    for (ValueObjectDTO result : results)
    {
      JSONObject object = new JSONObject();
      object.put("text", result.getValue(GeoEntityDTO.ENTITYLABEL));
      object.put("data", result.getValue(GeoEntityDTO.ID));

      response.put(object);
    }

    return new RestBodyResponse(response);
  }
  
  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF createTerm(ClientRequestIF request, @RequestParamter(name = "parentId") String parentId, @RequestParamter(name = "label") String label) throws JSONException
  {
    String termId = DataUploaderDTO.createTerm(request, parentId, label);

    JSONObject object = new JSONObject();
    object.put("id", termId);

    return new RestBodyResponse(object);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF createTermSynonym(ClientRequestIF request, @RequestParamter(name = "termId") String termId, @RequestParamter(name = "label") String label) throws JSONException
  {
    String response = DataUploaderDTO.createTermSynonym(request, termId, label);

    JSONObject object = new JSONObject(response);

    return new RestBodyResponse(object);
  }
  
  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF deleteTerm(ClientRequestIF request, @RequestParamter(name = "termId") String termId)
  {
    DataUploaderDTO.deleteTerm(request, termId);

    return new RestBodyResponse("");
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF deleteTermSynonym(ClientRequestIF request, @RequestParamter(name = "synonymId") String synonymId)
  {
    DataUploaderDTO.deleteTermSynonym(request, synonymId);

    return new RestBodyResponse("");
  }
}
