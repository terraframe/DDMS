package dss.vector.solutions.kaleidoscope.report;

import com.runwaysdk.generation.loader.Reloadable;

public class AttributeBooleanMetadata extends AttributeMetadata implements AttributePrimitiveMetadataIF, Reloadable
{

  public AttributeBooleanMetadata(String name, String label, Boolean required)
  {
    super(name, label, required);
  }

  @Override
  public int getColumnType()
  {
    return MetaDataTypeInfo.BOOLEAN_PARAMETER;
  }
}
