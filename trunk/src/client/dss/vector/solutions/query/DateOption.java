package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class DateOption implements Reloadable, SerializableOption
{
  private String classType;

  private String attributeName;

  public DateOption(String classType, String attributeName)
  {
    this.classType = classType;
    this.attributeName = attributeName;
  }

  public String serialize()
  {
    StringBuffer object = new StringBuffer();
    object.append("{klass:Mojo.Meta.findClass('" + classType + "')");
    object.append(", accessor:'" + attributeName + "'}");

    return object.toString();
  }

}
