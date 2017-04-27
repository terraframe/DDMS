package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;

public interface ImportProblemIF extends Reloadable
{
  public JSONObject toJSON() throws JSONException;

  public String getKey();
  
  public String getType();
}
