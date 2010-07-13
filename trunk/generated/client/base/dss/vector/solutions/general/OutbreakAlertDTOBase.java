package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 179271636)
public abstract class OutbreakAlertDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakAlert";
  private static final long serialVersionUID = 179271636;
  
  public OutbreakAlertDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTUALVALUE = "actualValue";
  public static java.lang.String ALERTLEVEL = "alertLevel";
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String EMAILFAILURE = "emailFailure";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String THRESHOLDTYPE = "thresholdType";
  public static java.lang.String THRESHOLDVALUE = "thresholdValue";
  public Double getActualValue()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALVALUE));
  }
  
  public void setActualValue(Double value)
  {
    if(value == null)
    {
      setValue(ACTUALVALUE, "");
    }
    else
    {
      setValue(ACTUALVALUE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isActualValueWritable()
  {
    return isWritable(ACTUALVALUE);
  }
  
  public boolean isActualValueReadable()
  {
    return isReadable(ACTUALVALUE);
  }
  
  public boolean isActualValueModified()
  {
    return isModified(ACTUALVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getActualValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ACTUALVALUE).getAttributeMdDTO();
  }
  
  public String getAlertLevel()
  {
    return getValue(ALERTLEVEL);
  }
  
  public void setAlertLevel(String value)
  {
    if(value == null)
    {
      setValue(ALERTLEVEL, "");
    }
    else
    {
      setValue(ALERTLEVEL, value);
    }
  }
  
  public boolean isAlertLevelWritable()
  {
    return isWritable(ALERTLEVEL);
  }
  
  public boolean isAlertLevelReadable()
  {
    return isReadable(ALERTLEVEL);
  }
  
  public boolean isAlertLevelModified()
  {
    return isModified(ALERTLEVEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAlertLevelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ALERTLEVEL).getAttributeMdDTO();
  }
  
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAlertTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ALERTTYPE).getAttributeMdDTO();
  }
  
  public Boolean getEmailFailure()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EMAILFAILURE));
  }
  
  public void setEmailFailure(Boolean value)
  {
    if(value == null)
    {
      setValue(EMAILFAILURE, "");
    }
    else
    {
      setValue(EMAILFAILURE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEmailFailureWritable()
  {
    return isWritable(EMAILFAILURE);
  }
  
  public boolean isEmailFailureReadable()
  {
    return isReadable(EMAILFAILURE);
  }
  
  public boolean isEmailFailureModified()
  {
    return isModified(EMAILFAILURE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEmailFailureMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(EMAILFAILURE).getAttributeMdDTO();
  }
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getThresholdTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THRESHOLDTYPE).getAttributeMdDTO();
  }
  
  public Double getThresholdValue()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(THRESHOLDVALUE));
  }
  
  public void setThresholdValue(Double value)
  {
    if(value == null)
    {
      setValue(THRESHOLDVALUE, "");
    }
    else
    {
      setValue(THRESHOLDVALUE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isThresholdValueWritable()
  {
    return isWritable(THRESHOLDVALUE);
  }
  
  public boolean isThresholdValueReadable()
  {
    return isReadable(THRESHOLDVALUE);
  }
  
  public boolean isThresholdValueModified()
  {
    return isModified(THRESHOLDVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getThresholdValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(THRESHOLDVALUE).getAttributeMdDTO();
  }
  
}
