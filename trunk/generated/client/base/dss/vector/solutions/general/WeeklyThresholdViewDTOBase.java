package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 909501959)
public abstract class WeeklyThresholdViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.WeeklyThresholdView";
  private static final long serialVersionUID = 909501959;
  
  protected WeeklyThresholdViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String THRESHOLDDATE = "thresholdDate";
  public static java.lang.String THRESHOLDVALUE = "thresholdValue";
  public static java.lang.String THRESHSOLDTYPE = "threshsoldType";
  public static java.lang.String YEAROFWEEK = "yearOfWeek";
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  public java.util.Date getThresholdDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(THRESHOLDDATE));
  }
  
  public void setThresholdDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(THRESHOLDDATE, "");
    }
    else
    {
      setValue(THRESHOLDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isThresholdDateWritable()
  {
    return isWritable(THRESHOLDDATE);
  }
  
  public boolean isThresholdDateReadable()
  {
    return isReadable(THRESHOLDDATE);
  }
  
  public boolean isThresholdDateModified()
  {
    return isModified(THRESHOLDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getThresholdDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(THRESHOLDDATE).getAttributeMdDTO();
  }
  
  public Integer getThresholdValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(THRESHOLDVALUE));
  }
  
  public void setThresholdValue(Integer value)
  {
    if(value == null)
    {
      setValue(THRESHOLDVALUE, "");
    }
    else
    {
      setValue(THRESHOLDVALUE, java.lang.Integer.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getThresholdValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(THRESHOLDVALUE).getAttributeMdDTO();
  }
  
  public String getThreshsoldType()
  {
    return getValue(THRESHSOLDTYPE);
  }
  
  public void setThreshsoldType(String value)
  {
    if(value == null)
    {
      setValue(THRESHSOLDTYPE, "");
    }
    else
    {
      setValue(THRESHSOLDTYPE, value);
    }
  }
  
  public boolean isThreshsoldTypeWritable()
  {
    return isWritable(THRESHSOLDTYPE);
  }
  
  public boolean isThreshsoldTypeReadable()
  {
    return isReadable(THRESHSOLDTYPE);
  }
  
  public boolean isThreshsoldTypeModified()
  {
    return isModified(THRESHSOLDTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getThreshsoldTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THRESHSOLDTYPE).getAttributeMdDTO();
  }
  
  public Integer getYearOfWeek()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFWEEK));
  }
  
  public void setYearOfWeek(Integer value)
  {
    if(value == null)
    {
      setValue(YEAROFWEEK, "");
    }
    else
    {
      setValue(YEAROFWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearOfWeekWritable()
  {
    return isWritable(YEAROFWEEK);
  }
  
  public boolean isYearOfWeekReadable()
  {
    return isReadable(YEAROFWEEK);
  }
  
  public boolean isYearOfWeekModified()
  {
    return isModified(YEAROFWEEK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearOfWeekMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFWEEK).getAttributeMdDTO();
  }
  
  public static final java.io.InputStream exportHistory(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.WeeklyThresholdViewDTO.CLASS, "exportHistory", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static WeeklyThresholdViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (WeeklyThresholdViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
