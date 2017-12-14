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
