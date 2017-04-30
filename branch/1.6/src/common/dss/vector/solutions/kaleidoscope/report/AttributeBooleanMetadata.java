package dss.vector.solutions.kaleidoscope.report;

public class AttributeBooleanMetadata extends AttributeMetadata implements AttributePrimitiveMetadataIF
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
