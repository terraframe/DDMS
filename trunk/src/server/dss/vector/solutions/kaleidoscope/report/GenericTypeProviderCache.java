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
package dss.vector.solutions.kaleidoscope.report;

import java.util.HashMap;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.kaleidoscope.dashboard.MetadataWrapper;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public class GenericTypeProviderCache implements Reloadable
{
  private static GenericTypeProviderCache instance;

  /**
   * List of MdClass objects which have geo nodes
   */
  private List<MdClass>                   classes;

  /**
   * Map of the GeoNode objectsz for each type
   */
  private HashMap<String, List<GeoNode>>  nodes;

  public GenericTypeProviderCache()
  {
    this.classes = MetadataWrapper.getMdClassesWithGeoNodes();
    this.nodes = new HashMap<String, List<GeoNode>>();
  }

  public List<MdClass> getMdClassesWithGeoNodes()
  {
    return classes;
  }

  public List<GeoNode> getGeoNodes(String type)
  {
    if (!this.nodes.containsKey(type))
    {
      this.nodes.put(type, MetadataWrapper.getGeoNodes(type));
    }

    return this.nodes.get(type);
  }

  public static synchronized GenericTypeProviderCache getInstance()
  {
    if (instance == null)
    {
      instance = new GenericTypeProviderCache();
    }

    return instance;
  }

}
