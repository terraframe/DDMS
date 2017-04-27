package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;


public class LocationProblem implements ImportProblemIF, Comparable<ImportProblemIF>, Reloadable
{
  public static final String TYPE = "locations";

  private String             label;

  private List<JSONObject>   context;

  private GeoEntity          parent;

  private GeoHierarchy       universal;

  public LocationProblem(String label, List<JSONObject> context, GeoEntity parent, GeoHierarchy universal)
  {
    this.label = label;
    this.context = context;
    this.parent = parent;
    this.universal = universal;
  }

  public String getKey()
  {
    return this.parent.getId() + "-" + this.label;
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("type", "LOCATION");
    object.put("label", label);
    object.put("parentId", parent.getId());
    object.put("universalId", universal.getId());
    object.put("universalLabel", universal.getDisplayLabel());
    object.put("context", new JSONArray(context));

    return object;
  }

  @Override
  public int compareTo(ImportProblemIF problem)
  {
    return this.getKey().compareTo(problem.getKey());
  }

  @Override
  public String getType()
  {
    return TYPE;
  }
}
