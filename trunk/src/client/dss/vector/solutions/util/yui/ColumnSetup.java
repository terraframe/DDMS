package dss.vector.solutions.util.yui;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * @author jsmethie
 * 
 */
public class ColumnSetup implements Reloadable
{
  /**
   * If this column is hidden
   */
  private boolean hidden;

  /**
   * If this column is editable
   */
  private boolean editable;

  /**
   * If the column should sum its values onto the last row
   */
  private boolean sum;

  /**
   * Validator to use in the column
   */
  private String  validator;

  /**
   * Type to use if this setup represents a reference column
   */
  private String  type;

  /**
   * The name of the static method used to populate the reference column
   */
  private String  method;

  private String  title;

  private String  label;

  private String  getter;

  private String  setter;
  
  private Integer width;

  public ColumnSetup()
  {
    this(false, true);
  }

  public ColumnSetup(boolean hidden, boolean editable)
  {
    this(hidden, editable, null, null, null);
  }

  public ColumnSetup(boolean hidden, boolean editable, String validator, String type, String method)
  {
    this.hidden = hidden;
    this.editable = editable;
    this.validator = validator;
    this.type = type;
    this.method = method;
    this.sum = false;

    this.title = null;
    this.label = null;
  }

  public boolean isHidden()
  {
    return hidden;
  }

  public void setHidden(boolean hidden)
  {
    this.hidden = hidden;
  }

  public boolean isEditable()
  {
    return editable;
  }

  public void setEditable(boolean editable)
  {
    this.editable = editable;
  }

  public String getValidator()
  {
    return validator;
  }

  public void setValidator(String validator)
  {
    this.validator = validator;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getMethod()
  {
    return method;
  }

  public void setMethod(String method)
  {
    this.method = method;
  }

  public boolean isSum()
  {
    return sum;
  }

  public void setSum(boolean sum)
  {
    this.sum = sum;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getGetter()
  {
    return getter;
  }

  public void setGetter(String getter)
  {
    this.getter = getter;
  }

  public String getSetter()
  {
    return setter;
  }

  public void setSetter(String setter)
  {
    this.setter = setter;
  }

  public boolean hasGetter()
  {
    return (this.getter != null && this.getter.length() > 0);
  }

  public Integer getWidth()
  {
    return width;
  }

  public void setWidth(Integer width)
  {
    this.width = width;
  }  
}
