package dss.vector.solutions.etl.dhis2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.ServletMethod;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.Controller;
import com.runwaysdk.mvc.Endpoint;
import com.runwaysdk.mvc.ErrorSerialization;
import com.runwaysdk.mvc.RequestParamter;
import com.runwaysdk.mvc.ResponseIF;
import com.runwaysdk.mvc.RestBodyResponse;
import com.runwaysdk.mvc.RestResponse;
import com.runwaysdk.system.metadata.MdTableDTO;

@Controller(url = "dhis2")
public class DHIS2Controller implements Reloadable
{
  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF instance(ClientRequestIF request) throws JSONException
  {
    DHIS2HTTPConfigurationDTO instance = DHIS2HTTPConfigurationDTO.getInstance(request);

    JSONObject response = new JSONObject();
    response.put("id", instance.getId());
    response.put("url", instance.getUrl());
    response.put("type", "BASIC");
    response.put("username", instance.getUsername());

    return new RestBodyResponse(response);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF connect(ClientRequestIF request, @RequestParamter(name = "connection") String connection) throws JSONException
  {
    JSONObject response = new JSONObject(connection);

    DHIS2HTTPConfigurationDTO instance = DHIS2HTTPConfigurationDTO.getInstance(request);
    instance.lock();
    instance.setUrl(response.getString("url"));
    instance.setUsername(response.getString("username"));

    if (response.has("password"))
    {
      instance.setPazzword(response.getString("password"));
    }

    instance.connect();

    return new RestResponse();
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF mappings(ClientRequestIF request) throws JSONException
  {
    DHIS2ExportableDatasetDTO[] datasets = DHIS2ExportableDatasetDTO.getAll(request);

    JSONArray response = new JSONArray();

    for (DHIS2ExportableDatasetDTO dataset : datasets)
    {
      response.put(this.serialize(dataset));
    }

    return new RestBodyResponse(response);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF apply(ClientRequestIF request, @RequestParamter(name = "mapping") String mapping) throws JSONException
  {
    JSONObject parameters = new JSONObject(mapping);

    DHIS2ExportableDatasetDTO dataset = new DHIS2ExportableDatasetDTO(request);
    dataset.setDhis2Name(parameters.getString("dhis2Name"));
    dataset.setQueryRef(MdTableDTO.get(request, parameters.getString("queryId")));

    if (parameters.has("description"))
    {
      dataset.setDescription(parameters.getString("description"));
    }

    dataset.apply();

    return new RestBodyResponse(this.serialize(dataset));
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF newInstance(ClientRequestIF request) throws JSONException
  {
    MdTableDTO[] queries = DHIS2ExportableDatasetDTO.getQueries(request);

    JSONArray response = new JSONArray();

    for (MdTableDTO query : queries)
    {
      JSONObject object = new JSONObject();
      object.put("id", query.getId());
      object.put("label", query.getDisplayLabel().getValue());

      response.put(object);
    }

    return new RestBodyResponse(response);
  }

  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF remove(ClientRequestIF request, @RequestParamter(name = "id") String id) throws JSONException
  {
    DHIS2ExportableDatasetDTO dataset = DHIS2ExportableDatasetDTO.get(request, id);
    dataset.delete();

    return new RestResponse();
  }
  
  @Endpoint(method = ServletMethod.POST, error = ErrorSerialization.JSON)
  public ResponseIF xport(ClientRequestIF request, @RequestParamter(name = "datasets") String datasets, @RequestParamter(name = "strategy") String strategy) throws JSONException
  {
    String response = DHIS2ExportableDatasetDTO.xport(request, datasets, strategy);
    
    return new RestBodyResponse(response);
  }

  private JSONObject serialize(DHIS2ExportableDatasetDTO dataset) throws JSONException
  {
    MdTableDTO query = dataset.getQueryRef();

    JSONObject object = new JSONObject();
    object.put("id", dataset.getId());
    object.put("dhis2Name", dataset.getDhis2Name());
    object.put("geoId", dataset.getGeoRefId());
    object.put("description", dataset.getDescription());
    object.put("queryId", query.getId());
    object.put("label", query.getDisplayLabel().getValue());

    return object;
  }
}
