package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttribute;

public class SourceField implements SourceFieldIF, Reloadable
{
  private String     columnName;

  private String     attributeName;

  private String     label;

  private ColumnType type;

  public String getColumnName()
  {
    return columnName;
  }

  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }

  public String getAttributeName()
  {
    return attributeName;
  }

  public void setAttributeName(String attributeName)
  {
    this.attributeName = attributeName;
  }

  @Override
  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  @Override
  public ColumnType getType()
  {
    return this.type;
  }

  public void setType(ColumnType type)
  {
    this.type = type;
  }

  @Override
  public void persist(ExcelSourceBinding source)
  {
    String type = source.getMdView().definesType();
    MdAttribute mdAttribute = MdAttribute.getByKey(type + "." + this.attributeName);

    ExcelFieldBinding field = new ExcelFieldBinding();
    field.setColumnHeader(this.columnName);
    field.setColumnLabel(this.label);
    field.setColumnType(this.type.name());
    field.setMdAttribute(mdAttribute);
    field.setSourceDefinition(source);
    field.apply();
  }
  
  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("name", this.attributeName);
    object.put("accepted", true);
    object.put("selected", true);
    object.put("label", this.label);
    object.put("type", this.type.name());
    object.put("columnType", this.type.name());
    
    return object;
  }
}
