package dss.vector.solutions.util;

import com.terraframe.mojo.generation.loader.Reloadable;

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
   * Validator to use in the column
   */
  private String validator;
  
  /**
   * Type to use if this setup represents a reference column
   */
  private String type;
  
  /**
   * The name of the static method used to populate the reference column
   */
  private String method;  
  
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
}
