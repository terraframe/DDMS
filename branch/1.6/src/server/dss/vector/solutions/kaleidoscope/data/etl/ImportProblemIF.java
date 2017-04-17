package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

public interface ImportProblemIF
{
  public JSONObject toJSON() throws JSONException;

  public String getKey();
  
  public String getType();
}
