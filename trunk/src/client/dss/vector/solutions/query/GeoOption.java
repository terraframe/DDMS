package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class GeoOption implements Reloadable, SerializableOption
{
  private String classType;

  private String attributeName;

  private String fieldLabel;

  public GeoOption(String classType, String attributeName, String fieldLabel)
  {
    this.classType = classType;
    this.attributeName = attributeName;
    this.fieldLabel = fieldLabel;
  }

  public String serialize()
  {
    StringBuffer object = new StringBuffer();
    object.append("{");
    object.append("keyName:'" + classType + "." + attributeName + "'");
    object.append(", display:'" + fieldLabel + "'");
    object.append("}");

    return object.toString();
  }
}
