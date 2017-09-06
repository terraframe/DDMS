package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public class FieldValue implements Reloadable
{
  /**
   * Flag denoting if the source value for the field was blank. This may be true even if the value is not null, because the value may be derived.
   */
  private boolean blank;

  private Object  value;

  public FieldValue()
  {
    this(null, true);
  }

  public FieldValue(Object value)
  {
    this(value, false);
  }

  public FieldValue(Object value, boolean blank)
  {
    this.value = value;
    this.blank = blank;
  }

  public boolean isBlank()
  {
    return this.blank;
  }

  public void setBlank(boolean blank)
  {
    this.blank = blank;
  }

  public void setValue(Object value)
  {
    this.value = value;
  }

  public Object getValue()
  {
    return value;
  }
}
