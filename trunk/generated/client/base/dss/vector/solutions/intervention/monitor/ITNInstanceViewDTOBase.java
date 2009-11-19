package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 630318936)
public abstract class ITNInstanceViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNInstanceView";
  private static final long serialVersionUID = 630318936;
  
  protected ITNInstanceViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DAMAGED = "damaged";
  public static java.lang.String HANGING = "hanging";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String ISNETUSED = "isNetUsed";
  public static java.lang.String MONTHRECIEVED = "monthRecieved";
  public static java.lang.String MONTHRETREATED = "monthRetreated";
  public static java.lang.String NETBRAND = "netBrand";
  public static java.lang.String NETID = "netId";
  public static java.lang.String NOTUSEDFORSLEEPING = "notUsedForSleeping";
  public static java.lang.String OBTAINED = "obtained";
  public static java.lang.String PRICE = "price";
  public static java.lang.String PURPOSE = "purpose";
  public static java.lang.String PURPOSECOMMENTS = "purposeComments";
  public static java.lang.String RETEATED = "reteated";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHPERIOD = "washPeriod";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String YEARRECIEVED = "yearRecieved";
  public static java.lang.String YEARRETREATED = "yearRetreated";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDamaged()
  {
    if(getValue(DAMAGED) == null || getValue(DAMAGED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DAMAGED));
    }
  }
  
  public void setDamaged(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DAMAGED, "");
    }
    else
    {
      setValue(DAMAGED, value.getId());
    }
  }
  
  public boolean isDamagedWritable()
  {
    return isWritable(DAMAGED);
  }
  
  public boolean isDamagedReadable()
  {
    return isReadable(DAMAGED);
  }
  
  public boolean isDamagedModified()
  {
    return isModified(DAMAGED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDamagedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DAMAGED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getHanging()
  {
    if(getValue(HANGING) == null || getValue(HANGING).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(HANGING));
    }
  }
  
  public void setHanging(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(HANGING, "");
    }
    else
    {
      setValue(HANGING, value.getId());
    }
  }
  
  public boolean isHangingWritable()
  {
    return isWritable(HANGING);
  }
  
  public boolean isHangingReadable()
  {
    return isReadable(HANGING);
  }
  
  public boolean isHangingModified()
  {
    return isModified(HANGING);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHangingMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HANGING).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdDTO getHousehold()
  {
    if(getValue(HOUSEHOLD) == null || getValue(HOUSEHOLD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.HouseholdDTO.get(getRequest(), getValue(HOUSEHOLD));
    }
  }
  
  public void setHousehold(dss.vector.solutions.intervention.monitor.HouseholdDTO value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLD, "");
    }
    else
    {
      setValue(HOUSEHOLD, value.getId());
    }
  }
  
  public boolean isHouseholdWritable()
  {
    return isWritable(HOUSEHOLD);
  }
  
  public boolean isHouseholdReadable()
  {
    return isReadable(HOUSEHOLD);
  }
  
  public boolean isHouseholdModified()
  {
    return isModified(HOUSEHOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHouseholdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
  }
  
  public Boolean getIsNetUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISNETUSED));
  }
  
  public void setIsNetUsed(Boolean value)
  {
    if(value == null)
    {
      setValue(ISNETUSED, "");
    }
    else
    {
      setValue(ISNETUSED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsNetUsedWritable()
  {
    return isWritable(ISNETUSED);
  }
  
  public boolean isIsNetUsedReadable()
  {
    return isReadable(ISNETUSED);
  }
  
  public boolean isIsNetUsedModified()
  {
    return isModified(ISNETUSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsNetUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISNETUSED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthRecieved()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.MonthOfYear", getEnumNames(MONTHRECIEVED));
  }
  
  public java.util.List<String> getMonthRecievedEnumNames()
  {
    return getEnumNames(MONTHRECIEVED);
  }
  
  public void addMonthRecieved(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    addEnumItem(MONTHRECIEVED, enumDTO.toString());
  }
  
  public void removeMonthRecieved(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    removeEnumItem(MONTHRECIEVED, enumDTO.toString());
  }
  
  public void clearMonthRecieved()
  {
    clearEnum(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedWritable()
  {
    return isWritable(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedReadable()
  {
    return isReadable(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedModified()
  {
    return isModified(MONTHRECIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getMonthRecievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRECIEVED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthRetreated()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.MonthOfYear", getEnumNames(MONTHRETREATED));
  }
  
  public java.util.List<String> getMonthRetreatedEnumNames()
  {
    return getEnumNames(MONTHRETREATED);
  }
  
  public void addMonthRetreated(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    addEnumItem(MONTHRETREATED, enumDTO.toString());
  }
  
  public void removeMonthRetreated(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    removeEnumItem(MONTHRETREATED, enumDTO.toString());
  }
  
  public void clearMonthRetreated()
  {
    clearEnum(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedWritable()
  {
    return isWritable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedReadable()
  {
    return isReadable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedModified()
  {
    return isModified(MONTHRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getMonthRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRETREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getNetBrand()
  {
    if(getValue(NETBRAND) == null || getValue(NETBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(NETBRAND));
    }
  }
  
  public void setNetBrand(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(NETBRAND, "");
    }
    else
    {
      setValue(NETBRAND, value.getId());
    }
  }
  
  public boolean isNetBrandWritable()
  {
    return isWritable(NETBRAND);
  }
  
  public boolean isNetBrandReadable()
  {
    return isReadable(NETBRAND);
  }
  
  public boolean isNetBrandModified()
  {
    return isModified(NETBRAND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getNetBrandMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NETBRAND).getAttributeMdDTO();
  }
  
  public String getNetId()
  {
    return getValue(NETID);
  }
  
  public void setNetId(String value)
  {
    if(value == null)
    {
      setValue(NETID, "");
    }
    else
    {
      setValue(NETID, value);
    }
  }
  
  public boolean isNetIdWritable()
  {
    return isWritable(NETID);
  }
  
  public boolean isNetIdReadable()
  {
    return isReadable(NETID);
  }
  
  public boolean isNetIdModified()
  {
    return isModified(NETID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNetIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NETID).getAttributeMdDTO();
  }
  
  public Boolean getNotUsedForSleeping()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTUSEDFORSLEEPING));
  }
  
  public void setNotUsedForSleeping(Boolean value)
  {
    if(value == null)
    {
      setValue(NOTUSEDFORSLEEPING, "");
    }
    else
    {
      setValue(NOTUSEDFORSLEEPING, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNotUsedForSleepingWritable()
  {
    return isWritable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingReadable()
  {
    return isReadable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingModified()
  {
    return isModified(NOTUSEDFORSLEEPING);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNotUsedForSleepingMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTUSEDFORSLEEPING).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getObtained()
  {
    if(getValue(OBTAINED) == null || getValue(OBTAINED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(OBTAINED));
    }
  }
  
  public void setObtained(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(OBTAINED, "");
    }
    else
    {
      setValue(OBTAINED, value.getId());
    }
  }
  
  public boolean isObtainedWritable()
  {
    return isWritable(OBTAINED);
  }
  
  public boolean isObtainedReadable()
  {
    return isReadable(OBTAINED);
  }
  
  public boolean isObtainedModified()
  {
    return isModified(OBTAINED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getObtainedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OBTAINED).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPrice()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PRICE));
  }
  
  public void setPrice(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PRICE, "");
    }
    else
    {
      setValue(PRICE, value.toString());
    }
  }
  
  public boolean isPriceWritable()
  {
    return isWritable(PRICE);
  }
  
  public boolean isPriceReadable()
  {
    return isReadable(PRICE);
  }
  
  public boolean isPriceModified()
  {
    return isModified(PRICE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getPriceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PRICE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPurpose()
  {
    if(getValue(PURPOSE) == null || getValue(PURPOSE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PURPOSE));
    }
  }
  
  public void setPurpose(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PURPOSE, "");
    }
    else
    {
      setValue(PURPOSE, value.getId());
    }
  }
  
  public boolean isPurposeWritable()
  {
    return isWritable(PURPOSE);
  }
  
  public boolean isPurposeReadable()
  {
    return isReadable(PURPOSE);
  }
  
  public boolean isPurposeModified()
  {
    return isModified(PURPOSE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPurposeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PURPOSE).getAttributeMdDTO();
  }
  
  public String getPurposeComments()
  {
    return getValue(PURPOSECOMMENTS);
  }
  
  public void setPurposeComments(String value)
  {
    if(value == null)
    {
      setValue(PURPOSECOMMENTS, "");
    }
    else
    {
      setValue(PURPOSECOMMENTS, value);
    }
  }
  
  public boolean isPurposeCommentsWritable()
  {
    return isWritable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsReadable()
  {
    return isReadable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsModified()
  {
    return isModified(PURPOSECOMMENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getPurposeCommentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PURPOSECOMMENTS).getAttributeMdDTO();
  }
  
  public Boolean getReteated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETEATED));
  }
  
  public void setReteated(Boolean value)
  {
    if(value == null)
    {
      setValue(RETEATED, "");
    }
    else
    {
      setValue(RETEATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReteatedWritable()
  {
    return isWritable(RETEATED);
  }
  
  public boolean isReteatedReadable()
  {
    return isReadable(RETEATED);
  }
  
  public boolean isReteatedModified()
  {
    return isModified(RETEATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getReteatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETEATED).getAttributeMdDTO();
  }
  
  public Long getSleptUnderNet()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
  }
  
  public void setSleptUnderNet(Long value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, java.lang.Long.toString(value));
    }
  }
  
  public boolean isSleptUnderNetWritable()
  {
    return isWritable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetReadable()
  {
    return isReadable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetModified()
  {
    return isModified(SLEPTUNDERNET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSleptUnderNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public Integer getWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
  }
  
  public void setWashFrequency(Integer value)
  {
    if(value == null)
    {
      setValue(WASHFREQUENCY, "");
    }
    else
    {
      setValue(WASHFREQUENCY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWashFrequencyWritable()
  {
    return isWritable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyReadable()
  {
    return isReadable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyModified()
  {
    return isModified(WASHFREQUENCY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getWashFrequencyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WASHFREQUENCY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWashPeriod()
  {
    if(getValue(WASHPERIOD) == null || getValue(WASHPERIOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHPERIOD));
    }
  }
  
  public void setWashPeriod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHPERIOD, "");
    }
    else
    {
      setValue(WASHPERIOD, value.getId());
    }
  }
  
  public boolean isWashPeriodWritable()
  {
    return isWritable(WASHPERIOD);
  }
  
  public boolean isWashPeriodReadable()
  {
    return isReadable(WASHPERIOD);
  }
  
  public boolean isWashPeriodModified()
  {
    return isModified(WASHPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHPERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getWashed()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.Response", getEnumNames(WASHED));
  }
  
  public java.util.List<String> getWashedEnumNames()
  {
    return getEnumNames(WASHED);
  }
  
  public void addWashed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(WASHED, enumDTO.toString());
  }
  
  public void removeWashed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(WASHED, enumDTO.toString());
  }
  
  public void clearWashed()
  {
    clearEnum(WASHED);
  }
  
  public boolean isWashedWritable()
  {
    return isWritable(WASHED);
  }
  
  public boolean isWashedReadable()
  {
    return isReadable(WASHED);
  }
  
  public boolean isWashedModified()
  {
    return isModified(WASHED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getWashedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public Integer getYearRecieved()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECIEVED));
  }
  
  public void setYearRecieved(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRECIEVED, "");
    }
    else
    {
      setValue(YEARRECIEVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearRecievedWritable()
  {
    return isWritable(YEARRECIEVED);
  }
  
  public boolean isYearRecievedReadable()
  {
    return isReadable(YEARRECIEVED);
  }
  
  public boolean isYearRecievedModified()
  {
    return isModified(YEARRECIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearRecievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECIEVED).getAttributeMdDTO();
  }
  
  public Integer getYearRetreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRETREATED));
  }
  
  public void setYearRetreated(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRETREATED, "");
    }
    else
    {
      setValue(YEARRETREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearRetreatedWritable()
  {
    return isWritable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedReadable()
  {
    return isReadable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedModified()
  {
    return isModified(YEARRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRETREATED).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNInstanceViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNInstanceViewDTO) dto;
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
