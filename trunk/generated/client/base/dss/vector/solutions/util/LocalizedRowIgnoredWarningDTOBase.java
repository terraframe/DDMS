package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = -727775949)
public abstract class LocalizedRowIgnoredWarningDTOBase extends com.runwaysdk.business.WarningDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.LocalizedRowIgnoredWarning";
  private static final long serialVersionUID = -727775949;
  
  public LocalizedRowIgnoredWarningDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String ROW = "row";
  public static java.lang.String SHEET = "sheet";
  public Integer getRow()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROW));
  }
  
  public void setRow(Integer value)
  {
    if(value == null)
    {
      setValue(ROW, "");
    }
    else
    {
      setValue(ROW, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRowWritable()
  {
    return isWritable(ROW);
  }
  
  public boolean isRowReadable()
  {
    return isReadable(ROW);
  }
  
  public boolean isRowModified()
  {
    return isModified(ROW);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRowMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROW).getAttributeMdDTO();
  }
  
  public String getSheet()
  {
    return getValue(SHEET);
  }
  
  public void setSheet(String value)
  {
    if(value == null)
    {
      setValue(SHEET, "");
    }
    else
    {
      setValue(SHEET, value);
    }
  }
  
  public boolean isSheetWritable()
  {
    return isWritable(SHEET);
  }
  
  public boolean isSheetReadable()
  {
    return isReadable(SHEET);
  }
  
  public boolean isSheetModified()
  {
    return isModified(SHEET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSheetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SHEET).getAttributeMdDTO();
  }
  
}
