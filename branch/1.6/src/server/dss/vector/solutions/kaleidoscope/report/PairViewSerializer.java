package dss.vector.solutions.kaleidoscope.report;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class PairViewSerializer implements Reloadable
{

  public String serialize(List<PairView> views)
  {
    try
    {
      JSONArray attributeNames = new JSONArray();
      attributeNames.put(PairView.LABEL);
      attributeNames.put(PairView.VALUE);

      JSONArray results = new JSONArray();

      for (PairView view : views)
      {
        JSONObject result = new JSONObject();
        result.put(PairView.LABEL, view.getLabel());
        result.put(PairView.VALUE, view.getValue());

        results.put(result);
      }

      JSONArray metadata = new JSONArray();
      metadata.put(this.serialize(PairView.getLabelMd()));
      metadata.put(this.serialize(PairView.getValueMd()));

      JSONObject query = new JSONObject();
      query.put("attributeNames", attributeNames);
      query.put("results", results);
      query.put("metadata", metadata);

      return query.toString();
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }

  private JSONObject serialize(MdAttributeConcreteDAOIF mdAttribute) throws JSONException
  {
    AttributeMetadataIF metadata = RemoteQueryBuilder.buildAttributeMetadata(mdAttribute);

    return metadata.toJSON();
  }

}
