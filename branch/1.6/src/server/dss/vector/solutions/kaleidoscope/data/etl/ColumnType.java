package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * These are the different kinds of cells we support. We keep track of the current one between the start and end.
 */
public enum ColumnType implements Reloadable {
  BOOLEAN, ERROR, FORMULA, INLINE_STRING, TEXT, NUMBER, UNDEFINED, DATE, LONG, DOUBLE, CATEGORY, LOCATION, IGNORE, LATITUDE, LONGITUDE, DOMAIN
}