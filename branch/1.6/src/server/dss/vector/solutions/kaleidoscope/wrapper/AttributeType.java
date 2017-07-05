package dss.vector.solutions.kaleidoscope.wrapper;

import com.runwaysdk.generation.loader.Reloadable;

public enum AttributeType implements Reloadable {
  DATE, DATETIME, TIME, NUMBER, BASIC, PERCENT;

  public boolean isNumber()
  {
    return this.equals(AttributeType.NUMBER) || this.equals(AttributeType.PERCENT);
  }
}
