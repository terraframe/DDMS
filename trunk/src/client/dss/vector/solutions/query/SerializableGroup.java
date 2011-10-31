package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public class SerializableGroup implements Reloadable
{
  private List<SerializableOption> options;

  public SerializableGroup()
  {
    this.options = new LinkedList<SerializableOption>();
  }

  public void addOption(SerializableOption option)
  {
    this.options.add(option);
  }

  public String serialize()
  {
    StringBuffer object = new StringBuffer();

    for (SerializableOption option : options)
    {
      object.append(",");
      object.append(option.serialize());
    }

    return object.toString().replaceFirst(",", "");
  }

}
