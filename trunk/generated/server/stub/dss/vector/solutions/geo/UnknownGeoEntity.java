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
package dss.vector.solutions.geo;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownGeoEntity extends UnknownGeoEntityBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248828577387L;
  
  public UnknownGeoEntity()
  {
    super();
  }
  
  public JSONObject serialize() throws JSONException
  {
    JSONObject object = new JSONObject();
    
    object.put(ENTITYNAME, this.getEntityName());
    object.put(ENTITYTYPE, this.getEntityType());
    object.put(KNOWNHIERARCHY, this.getKnownHierarchy());
    object.put(SIBLINGS, this.getSiblings());
    object.put(SYNONYMS, this.getSynonyms());

    return object;
  }

  public static UnknownGeoEntity deserialize(JSONObject object) throws JSONException
  {
    UnknownGeoEntity entity = new UnknownGeoEntity();

    entity.setEntityName(object.getString(ENTITYNAME));
    entity.setEntityType(object.getString(ENTITYTYPE));
    entity.setKnownHierarchy(object.getString(KNOWNHIERARCHY));
    entity.setSiblings(object.getString(SIBLINGS));
    entity.setSynonyms(object.getString(SYNONYMS));
    
    return entity;
  }
  
}
