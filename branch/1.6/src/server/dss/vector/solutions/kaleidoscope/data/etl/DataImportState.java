package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public enum DataImportState implements Reloadable
{
  INITIAL, VALIDATION, DATAIMPORT, COMPLETE, VALIDATIONFAIL
}
