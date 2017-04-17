package dss.vector.solutions.localization;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.LocaleDimension;

public class Bundle implements Reloadable
{
  private Map<String, String> properties;
  
  public Bundle(String bundleName, LocaleDimension localeDimension)
  {
    properties = new HashMap<String, String>(localeDimension.getPropertiesFromFile(bundleName));
  }
  
  public String getValue(String key)
  {
    return properties.get(key);
  }
  
  public Set<String> getKeySet()
  {
    return properties.keySet();
  }
}
