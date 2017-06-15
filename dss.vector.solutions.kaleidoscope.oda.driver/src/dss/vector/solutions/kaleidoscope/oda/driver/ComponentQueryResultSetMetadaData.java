package dss.vector.solutions.kaleidoscope.oda.driver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

import dss.vector.solutions.kaleidoscope.report.AttributeCharacterMetadataIF;
import dss.vector.solutions.kaleidoscope.report.AttributeDecMetadataIF;
import dss.vector.solutions.kaleidoscope.report.AttributeMetadataIF;
import dss.vector.solutions.kaleidoscope.report.RemoteQueryIF;

public class ComponentQueryResultSetMetadaData implements IResultSetMetaData
{
  private RemoteQueryIF query;

  private List<String>  attributeNames;

  public ComponentQueryResultSetMetadaData(RemoteQueryIF query)
  {
    this.query = query;
    this.attributeNames = new ArrayList<String>(this.query.getAttributeNames());
  }

  private String getAttributeName(int index)
  {
    return this.attributeNames.get( ( index - 1 ));
  }

  private AttributeMetadataIF getAttributeMetadata(int index)
  {
    AttributeMetadataIF attribute = this.query.getAttributeMetadata(this.getAttributeName(index));
    return attribute;
  }

  @Override
  public int getColumnCount() throws OdaException
  {
    return attributeNames.size();
  }

  @Override
  public int getColumnDisplayLength(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    if (attributeMetadata instanceof AttributeCharacterMetadataIF)
    {
      return ( (AttributeCharacterMetadataIF) attributeMetadata ).getSize();
    }

    return -1;
  }

  @Override
  public String getColumnLabel(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    return attributeMetadata.getDisplayLabel();
  }

  @Override
  public String getColumnName(int index) throws OdaException
  {
    return this.getAttributeMetadata(index).getName();
  }

  @Override
  public int getColumnType(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    return attributeMetadata.getColumnType();

    // if (attributeMetadata instanceof AttributeCharacterMetadataIF)
    // {
    // return MetaDataTypeInfo.STRING_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeLongMetadataIF)
    // {
    // return MetaDataTypeInfo.INTEGER_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeDoubleMetadataIF)
    // {
    // return MetaDataTypeInfo.DOUBLE_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeDecimalMetadataIF)
    // {
    // return MetaDataTypeInfo.DECIMAL_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeDateMetadataIF)
    // {
    // return MetaDataTypeInfo.DATE_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeTimeMetadataIF)
    // {
    // return MetaDataTypeInfo.TIME_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeDateTimeMetadataIF)
    // {
    // return MetaDataTypeInfo.TIMESTAMP_PARAMETER;
    // }
    // else if (attributeMetadata instanceof AttributeBooleanMetadataIF)
    // {
    // return MetaDataTypeInfo.BOOLEAN_PARAMETER;
    // }
    //
    // return 0;
  }

  @Override
  public String getColumnTypeName(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    return attributeMetadata.getClass().getSimpleName();
  }

  @Override
  public int getPrecision(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    if (attributeMetadata instanceof AttributeDecMetadataIF)
    {
      return ( (AttributeDecMetadataIF) attributeMetadata ).getPrecision();
    }

    return -1;
  }

  @Override
  public int getScale(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    if (attributeMetadata instanceof AttributeDecMetadataIF)
    {
      return ( (AttributeDecMetadataIF) attributeMetadata ).getScale();
    }

    return -1;
  }

  @Override
  public int isNullable(int index) throws OdaException
  {
    AttributeMetadataIF attributeMetadata = this.getAttributeMetadata(index);

    return ( attributeMetadata.isRequired() ? IResultSetMetaData.columnNoNulls : IResultSetMetaData.columnNullable );
  }

}
