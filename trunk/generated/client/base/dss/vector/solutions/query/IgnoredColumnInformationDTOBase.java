package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 844694051)
public abstract class IgnoredColumnInformationDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.IgnoredColumnInformation";
  private static final long serialVersionUID = 844694051;
  
  public IgnoredColumnInformationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLUMNNAME = "columnName";
  public static java.lang.String ID = "id";
  public String getColumnName()
  {
    return getValue(COLUMNNAME);
  }
  
  public void setColumnName(String value)
  {
    if(value == null)
    {
      setValue(COLUMNNAME, "");
    }
    else
    {
      setValue(COLUMNNAME, value);
    }
  }
  
  public boolean isColumnNameWritable()
  {
    return isWritable(COLUMNNAME);
  }
  
  public boolean isColumnNameReadable()
  {
    return isReadable(COLUMNNAME);
  }
  
  public boolean isColumnNameModified()
  {
    return isModified(COLUMNNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getColumnNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(COLUMNNAME).getAttributeMdDTO();
  }
  
}
