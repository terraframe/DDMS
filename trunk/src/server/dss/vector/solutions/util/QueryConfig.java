package dss.vector.solutions.util;

import java.lang.reflect.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;

public class QueryConfig implements com.terraframe.mojo.generation.loader.Reloadable
{
  private JSONObject config;
  
  private static final String SELECTED_UNIVERSALS = "selectedUniversals";

//  private static final String THEMATIC_LAYER_TYPE = "thematicLayerType";
  
  public QueryConfig(String configJSON)
  {
    try
    {
      config = new JSONObject(configJSON);
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  /*
  public String getThematicLayerType()
  {
    try
    {
      return config.getString(THEMATIC_LAYER_TYPE);
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }  
  }
  */
  
  public String[] getSelectedUniversals()
  {
    return (String[]) toArray(String.class, SELECTED_UNIVERSALS);
  }
  
  private Object[] toArray(Class<?> klass, String key)
  {
    try
    {
      JSONArray arr = config.getJSONArray(key);
      Object[] oArr = (Object[]) Array.newInstance(klass, arr.length());
      
      for(int i=0; i<arr.length(); i++)
      {
        oArr[i] = arr.get(i);
      }
      
      return oArr;
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }     
  }
}
