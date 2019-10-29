/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
