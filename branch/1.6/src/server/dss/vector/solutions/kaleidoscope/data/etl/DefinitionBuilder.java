package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DefinitionBuilder
{
  private SourceDefinitionIF source;

  private TargetDefinitionIF target;

  public DefinitionBuilder(SourceDefinitionIF source, TargetDefinitionIF target)
  {
    this.source = source;
    this.target = target;
  }

  public JSONObject getConfiguration() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("name", source.getName());
    object.put("label", source.getLabel());
    object.put("country", source.getCountry());
    object.put("fields", this.getFields());
    object.put("attributes", this.getAttributes());
    object.put("coordinates", this.getCoordinates());

    if (!source.isNew() || source.isApplied())
    {
      object.put("existing", source.getId());
    }

    return object;
  }

  private JSONObject getAttributes() throws JSONException
  {
    JSONArray ids = new JSONArray();
    JSONObject values = new JSONObject();

    List<TargetFieldIF> fields = this.target.getFields();

    for (TargetFieldIF field : fields)
    {
      if (field instanceof TargetFieldGeoEntityIF)
      {
        TargetFieldGeoEntityIF eField = (TargetFieldGeoEntityIF) field;

        ids.put(eField.getId());

        values.put(eField.getId(), eField.toJSON());
      }
    }

    JSONObject object = new JSONObject();
    object.put("ids", ids);
    object.put("values", values);

    return object;
  }

  private JSONObject getCoordinates() throws JSONException
  {
    JSONArray ids = new JSONArray();
    JSONObject values = new JSONObject();

    List<TargetFieldIF> fields = this.target.getFields();

    for (TargetFieldIF field : fields)
    {
      if (field instanceof TargetFieldPointIF)
      {
        TargetFieldPointIF pField = (TargetFieldPointIF) field;

        ids.put(pField.getId());

        values.put(pField.getId(), pField.toJSON());
      }
    }

    JSONObject object = new JSONObject();
    object.put("ids", ids);
    object.put("values", values);

    return object;
  }

  private JSONArray getFields() throws JSONException
  {
    Map<String, String> map = this.getFieldUniversals();

    List<SourceFieldIF> sFields = this.source.getFields();
    JSONArray fields = new JSONArray();

    for (SourceFieldIF sField : sFields)
    {
      JSONObject object = sField.toJSON();

      String attributeName = sField.getAttributeName();

      if (map.containsKey(attributeName))
      {
        String universalId = map.get(attributeName);

        object.put("universal", universalId);
      }

      fields.put(object);
    }
    return fields;
  }

  private Map<String, String> getFieldUniversals()
  {
    Map<String, String> map = new HashMap<String, String>();

    List<TargetFieldIF> fields = this.target.getFields();

    for (TargetFieldIF field : fields)
    {
      if (field instanceof TargetFieldGeoEntityIF)
      {
        map.putAll( ( (TargetFieldGeoEntityIF) field ).getUniversalAttributes());
      }
    }

    return map;
  }
}
