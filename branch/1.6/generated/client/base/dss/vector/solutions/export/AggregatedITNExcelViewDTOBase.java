package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 882896802)
public abstract class AggregatedITNExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.AggregatedITNExcelView";
  private static final long serialVersionUID = 882896802;
  
  protected AggregatedITNExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERDISTRIBUTED = "numberDistributed";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String RECEIVEDFORCOMMUNITYRESPONSE = "receivedForCommunityResponse";
  public static java.lang.String RECEIVEDFORTARGETGROUPS = "receivedForTargetGroups";
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void setBatchNumber(String value)
  {
    if(value == null)
    {
      setValue(BATCHNUMBER, "");
    }
    else
    {
      setValue(BATCHNUMBER, value);
    }
  }
  
  public boolean isBatchNumberWritable()
  {
    return isWritable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberReadable()
  {
    return isReadable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberModified()
  {
    return isModified(BATCHNUMBER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, value.toString());
    }
  }
  
  public boolean isCurrencyReceivedWritable()
  {
    return isWritable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedReadable()
  {
    return isReadable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedModified()
  {
    return isModified(CURRENCYRECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getNumberDistributed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDISTRIBUTED));
  }
  
  public void setNumberDistributed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDISTRIBUTED, "");
    }
    else
    {
      setValue(NUMBERDISTRIBUTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberDistributedWritable()
  {
    return isWritable(NUMBERDISTRIBUTED);
  }
  
  public boolean isNumberDistributedReadable()
  {
    return isReadable(NUMBERDISTRIBUTED);
  }
  
  public boolean isNumberDistributedModified()
  {
    return isModified(NUMBERDISTRIBUTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberDistributedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERDISTRIBUTED).getAttributeMdDTO();
  }
  
  public Integer getNumberSold()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void setNumberSold(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSOLD, "");
    }
    else
    {
      setValue(NUMBERSOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberSoldWritable()
  {
    return isWritable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldReadable()
  {
    return isReadable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldModified()
  {
    return isModified(NUMBERSOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberSoldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSOLD).getAttributeMdDTO();
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
  
  public String getPeriodType()
  {
    return getValue(PERIODTYPE);
  }
  
  public void setPeriodType(String value)
  {
    if(value == null)
    {
      setValue(PERIODTYPE, "");
    }
    else
    {
      setValue(PERIODTYPE, value);
    }
  }
  
  public boolean isPeriodTypeWritable()
  {
    return isWritable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeReadable()
  {
    return isReadable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeModified()
  {
    return isModified(PERIODTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPeriodTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public Integer getPeriodYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodYearWritable()
  {
    return isWritable(PERIODYEAR);
  }
  
  public boolean isPeriodYearReadable()
  {
    return isReadable(PERIODYEAR);
  }
  
  public boolean isPeriodYearModified()
  {
    return isModified(PERIODYEAR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodYearMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIODYEAR).getAttributeMdDTO();
  }
  
  public Integer getReceivedForCommunityResponse()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORCOMMUNITYRESPONSE));
  }
  
  public void setReceivedForCommunityResponse(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, "");
    }
    else
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedForCommunityResponseWritable()
  {
    return isWritable(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public boolean isReceivedForCommunityResponseReadable()
  {
    return isReadable(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public boolean isReceivedForCommunityResponseModified()
  {
    return isModified(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReceivedForCommunityResponseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVEDFORCOMMUNITYRESPONSE).getAttributeMdDTO();
  }
  
  public Integer getReceivedForTargetGroups()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORTARGETGROUPS));
  }
  
  public void setReceivedForTargetGroups(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORTARGETGROUPS, "");
    }
    else
    {
      setValue(RECEIVEDFORTARGETGROUPS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedForTargetGroupsWritable()
  {
    return isWritable(RECEIVEDFORTARGETGROUPS);
  }
  
  public boolean isReceivedForTargetGroupsReadable()
  {
    return isReadable(RECEIVEDFORTARGETGROUPS);
  }
  
  public boolean isReceivedForTargetGroupsModified()
  {
    return isModified(RECEIVEDFORTARGETGROUPS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReceivedForTargetGroupsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVEDFORTARGETGROUPS).getAttributeMdDTO();
  }
  
  public static AggregatedITNExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedITNExcelViewDTO) dto;
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
