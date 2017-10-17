package dss.vector.solutions.ontology;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownTerm extends UnknownTermBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -749809791;

  public UnknownTerm()
  {
    super();
  }

  public JSONObject serialize() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put(TERMNAME, this.getTermName());
    object.put(BROWSERCLASS, this.getBrowserClass());
    object.put(BROWSERATTRIBUTE, this.getBrowserAttribute());
    object.put(ATTRIBUTELABEL, this.getAttributeLabel());

    return object;
  }

  public static UnknownTerm deserialize(JSONObject object) throws JSONException
  {
    UnknownTerm uTerm = new UnknownTerm();
    uTerm.setTermName(object.getString(TERMNAME));
    uTerm.setBrowserClass(object.getString(BROWSERCLASS));
    uTerm.setBrowserAttribute(object.getString(BROWSERATTRIBUTE));
    uTerm.setAttributeLabel(object.getString(ATTRIBUTELABEL));

    return uTerm;
  }

}
