package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class SelectableOption implements Reloadable
{
  private String attributeName;

  private String displayLabel;

  private String key;

  public SelectableOption(String attributeName, String displayLabel, String key)
  {
    this.attributeName = attributeName;
    this.displayLabel = displayLabel;
    this.key = key;
  }

  protected String getAttributeName()
  {
    return attributeName;
  }

  protected String getDisplayLabel()
  {
    return displayLabel;
  }

  protected String getKey()
  {
    return key;
  }

  public String serialize()
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    buffer.append("attributeName:'" + this.getAttributeName() + "'");
    buffer.append(",displayLabel:'" + this.getDisplayLabel() + "'");
    buffer.append(",dtoType:'" + this.getDTOType() + "'");
    buffer.append(",key:'" + this.getKey() + "'");
    buffer.append(",type:'" + this.getType() + "'");
    buffer.append("}");

    return buffer.toString();
  }

  public abstract String getDTOType();

  public abstract String getType();
}
