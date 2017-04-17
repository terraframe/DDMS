package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

public interface ImportResponseIF
{
  public JSONObject toJSON() throws JSONException;

  public boolean hasProblems();
}