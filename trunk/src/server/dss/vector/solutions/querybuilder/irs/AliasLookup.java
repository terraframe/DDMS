package dss.vector.solutions.querybuilder.irs;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

public class AliasLookup implements Reloadable
{
  /**
   * Map of selectable aliases and their Alias enum object.
   */
  private static Map<String, Alias> aliasMap;
  
  static
  {
    aliasMap = new HashMap<String, Alias>();
    
    for(Alias alias : Alias.values())
    {
      aliasMap.put(alias.getXmlAlias(), alias);
    }
  }
  
  public static Alias get(String alias)
  {
    return aliasMap.get(alias);
  }
}
