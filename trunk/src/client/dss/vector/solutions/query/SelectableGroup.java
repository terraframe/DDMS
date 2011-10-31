package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public class SelectableGroup implements Reloadable
{
  private String                 label;

  private String                 group;

  private String                 classType;

  private List<SelectableOption> options;

  public SelectableGroup()
  {
    this.label = "Default Label";
    this.group = "root";
    this.classType = "test.class";
    this.options = new LinkedList<SelectableOption>();
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getGroupName()
  {
    return group;
  }

  public void setGroup(String group)
  {
    this.group = group;
  }

  public String getClassType()
  {
    return classType;
  }

  public void setClassType(String classType)
  {
    this.classType = classType;
  }

  protected void addOption(SelectableOption option)
  {
    this.options.add(option);
  }

  public String serialize()
  {
    StringBuffer array = new StringBuffer();

    for (SelectableOption option : this.options)
    {
      array.append("," + option.serialize());
    }

    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    buffer.append("title:'" + this.label + "'");
    buffer.append(",group:'" + this.group + "'");
    buffer.append(",klass:'" + this.classType + "'");
    buffer.append(",values:[" + array.toString().replaceFirst(",", "") + "]");
    buffer.append("}");

    return buffer.toString();
  }
}
