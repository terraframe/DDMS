package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 66267617)
public abstract class OutbreakAlertDTOBase extends com.terraframe.mojo.business.InformationDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakAlert";
  private static final long serialVersionUID = 66267617;
  
  public OutbreakAlertDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String THRESHOLD = "threshold";
  public static java.lang.String THRESHOLDTYPE = "thresholdType";
  public static java.lang.String TOTALCASES = "totalCases";
  public String getAlertType()
  {
    return getValue(ALERTTYPE);
  }
  
  public void setAlertType(String value)
  {
    if(value == null)
    {
      setValue(ALERTTYPE, "");
    }
    else
    {
      setValue(ALERTTYPE, value);
    }
  }
  
  public boolean isAlertTypeWritable()
  {
    return isWritable(ALERTTYPE);
  }
  
  public boolean isAlertTypeReadable()
  {
    return isReadable(ALERTTYPE);
  }
  
  public boolean isAlertTypeModified()
  {
    return isModified(ALERTTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAlertTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ALERTTYPE).getAttributeMdDTO();
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public Integer getThreshold()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(THRESHOLD));
  }
  
  public void setThreshold(Integer value)
  {
    if(value == null)
    {
      setValue(THRESHOLD, "");
    }
    else
    {
      setValue(THRESHOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isThresholdWritable()
  {
    return isWritable(THRESHOLD);
  }
  
  public boolean isThresholdReadable()
  {
    return isReadable(THRESHOLD);
  }
  
  public boolean isThresholdModified()
  {
    return isModified(THRESHOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getThresholdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(THRESHOLD).getAttributeMdDTO();
  }
  
  public String getThresholdType()
  {
    return getValue(THRESHOLDTYPE);
  }
  
  public void setThresholdType(String value)
  {
    if(value == null)
    {
      setValue(THRESHOLDTYPE, "");
    }
    else
    {
      setValue(THRESHOLDTYPE, value);
    }
  }
  
  public boolean isThresholdTypeWritable()
  {
    return isWritable(THRESHOLDTYPE);
  }
  
  public boolean isThresholdTypeReadable()
  {
    return isReadable(THRESHOLDTYPE);
  }
  
  public boolean isThresholdTypeModified()
  {
    return isModified(THRESHOLDTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getThresholdTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THRESHOLDTYPE).getAttributeMdDTO();
  }
  
  public Long getTotalCases()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(TOTALCASES));
  }
  
  public void setTotalCases(Long value)
  {
    if(value == null)
    {
      setValue(TOTALCASES, "");
    }
    else
    {
      setValue(TOTALCASES, java.lang.Long.toString(value));
    }
  }
  
  public boolean isTotalCasesWritable()
  {
    return isWritable(TOTALCASES);
  }
  
  public boolean isTotalCasesReadable()
  {
    return isReadable(TOTALCASES);
  }
  
  public boolean isTotalCasesModified()
  {
    return isModified(TOTALCASES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTotalCasesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALCASES).getAttributeMdDTO();
  }
  
}
