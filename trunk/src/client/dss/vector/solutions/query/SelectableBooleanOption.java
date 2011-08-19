package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class SelectableBooleanOption extends SelectableOption implements Reloadable
{
  private String positiveLabel;

  private String negativeLabel;

  public SelectableBooleanOption(String attributeName, String displayLabel, String key, String positiveLabel, String negativeLabel)
  {
    super(attributeName, displayLabel, key);

    this.positiveLabel = positiveLabel;
    this.negativeLabel = negativeLabel;
  }

  @Override
  public String getDTOType()
  {
    return "AttributeBooleanDTO";
  }

  @Override
  public String getType()
  {
    return "sqlinteger";
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
    buffer.append(",dropDownMap:{'0':'" + positiveLabel + "','1':'" + negativeLabel + "'}");
    buffer.append("}");

    return buffer.toString();
  }
}
