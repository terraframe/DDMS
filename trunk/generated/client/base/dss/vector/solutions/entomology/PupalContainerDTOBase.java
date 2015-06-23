package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -927043304)
public abstract class PupalContainerDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.PupalContainer";
  private static final long serialVersionUID = -927043304;
  
  protected PupalContainerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PupalContainerDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONTAINERID = "containerId";
  public static java.lang.String CONTAINERLENGTH = "containerLength";
  public static java.lang.String CONTAINERTYPE = "containerType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DIAMETER = "diameter";
  public static java.lang.String DRAWDOWNFREQUENCY = "drawdownFrequency";
  public static java.lang.String DRAWDOWNPERCENT = "drawdownPercent";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILLFREQUENCY = "fillFrequency";
  public static java.lang.String FILLMETHOD = "fillMethod";
  public static java.lang.String HEIGHT = "height";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LID = "lid";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OPENINGDIAMETER = "openingDiameter";
  public static java.lang.String OPENINGLENGTH = "openingLength";
  public static java.lang.String OPENINGWIDTH = "openingWidth";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PREMISE = "premise";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SHADING = "shading";
  public static java.lang.String SHAPE = "shape";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WIDTH = "width";
  public String getContainerId()
  {
    return getValue(CONTAINERID);
  }
  
  public void setContainerId(String value)
  {
    if(value == null)
    {
      setValue(CONTAINERID, "");
    }
    else
    {
      setValue(CONTAINERID, value);
    }
  }
  
  public boolean isContainerIdWritable()
  {
    return isWritable(CONTAINERID);
  }
  
  public boolean isContainerIdReadable()
  {
    return isReadable(CONTAINERID);
  }
  
  public boolean isContainerIdModified()
  {
    return isModified(CONTAINERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getContainerIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTAINERID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getContainerLength()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CONTAINERLENGTH));
  }
  
  public void setContainerLength(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CONTAINERLENGTH, "");
    }
    else
    {
      setValue(CONTAINERLENGTH, value.toString());
    }
  }
  
  public boolean isContainerLengthWritable()
  {
    return isWritable(CONTAINERLENGTH);
  }
  
  public boolean isContainerLengthReadable()
  {
    return isReadable(CONTAINERLENGTH);
  }
  
  public boolean isContainerLengthModified()
  {
    return isModified(CONTAINERLENGTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getContainerLengthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONTAINERLENGTH).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getContainerType()
  {
    if(getValue(CONTAINERTYPE) == null || getValue(CONTAINERTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CONTAINERTYPE));
    }
  }
  
  public String getContainerTypeId()
  {
    return getValue(CONTAINERTYPE);
  }
  
  public void setContainerType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CONTAINERTYPE, "");
    }
    else
    {
      setValue(CONTAINERTYPE, value.getId());
    }
  }
  
  public boolean isContainerTypeWritable()
  {
    return isWritable(CONTAINERTYPE);
  }
  
  public boolean isContainerTypeReadable()
  {
    return isReadable(CONTAINERTYPE);
  }
  
  public boolean isContainerTypeModified()
  {
    return isModified(CONTAINERTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getContainerTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CONTAINERTYPE).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getDiameter()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(DIAMETER));
  }
  
  public void setDiameter(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(DIAMETER, "");
    }
    else
    {
      setValue(DIAMETER, value.toString());
    }
  }
  
  public boolean isDiameterWritable()
  {
    return isWritable(DIAMETER);
  }
  
  public boolean isDiameterReadable()
  {
    return isReadable(DIAMETER);
  }
  
  public boolean isDiameterModified()
  {
    return isModified(DIAMETER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getDiameterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(DIAMETER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDrawdownFrequency()
  {
    if(getValue(DRAWDOWNFREQUENCY) == null || getValue(DRAWDOWNFREQUENCY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DRAWDOWNFREQUENCY));
    }
  }
  
  public String getDrawdownFrequencyId()
  {
    return getValue(DRAWDOWNFREQUENCY);
  }
  
  public void setDrawdownFrequency(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DRAWDOWNFREQUENCY, "");
    }
    else
    {
      setValue(DRAWDOWNFREQUENCY, value.getId());
    }
  }
  
  public boolean isDrawdownFrequencyWritable()
  {
    return isWritable(DRAWDOWNFREQUENCY);
  }
  
  public boolean isDrawdownFrequencyReadable()
  {
    return isReadable(DRAWDOWNFREQUENCY);
  }
  
  public boolean isDrawdownFrequencyModified()
  {
    return isModified(DRAWDOWNFREQUENCY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDrawdownFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DRAWDOWNFREQUENCY).getAttributeMdDTO();
  }
  
  public Integer getDrawdownPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DRAWDOWNPERCENT));
  }
  
  public void setDrawdownPercent(Integer value)
  {
    if(value == null)
    {
      setValue(DRAWDOWNPERCENT, "");
    }
    else
    {
      setValue(DRAWDOWNPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDrawdownPercentWritable()
  {
    return isWritable(DRAWDOWNPERCENT);
  }
  
  public boolean isDrawdownPercentReadable()
  {
    return isReadable(DRAWDOWNPERCENT);
  }
  
  public boolean isDrawdownPercentModified()
  {
    return isModified(DRAWDOWNPERCENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDrawdownPercentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DRAWDOWNPERCENT).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getFillFrequency()
  {
    if(getValue(FILLFREQUENCY) == null || getValue(FILLFREQUENCY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FILLFREQUENCY));
    }
  }
  
  public String getFillFrequencyId()
  {
    return getValue(FILLFREQUENCY);
  }
  
  public void setFillFrequency(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FILLFREQUENCY, "");
    }
    else
    {
      setValue(FILLFREQUENCY, value.getId());
    }
  }
  
  public boolean isFillFrequencyWritable()
  {
    return isWritable(FILLFREQUENCY);
  }
  
  public boolean isFillFrequencyReadable()
  {
    return isReadable(FILLFREQUENCY);
  }
  
  public boolean isFillFrequencyModified()
  {
    return isModified(FILLFREQUENCY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getFillFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FILLFREQUENCY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getFillMethod()
  {
    if(getValue(FILLMETHOD) == null || getValue(FILLMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FILLMETHOD));
    }
  }
  
  public String getFillMethodId()
  {
    return getValue(FILLMETHOD);
  }
  
  public void setFillMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FILLMETHOD, "");
    }
    else
    {
      setValue(FILLMETHOD, value.getId());
    }
  }
  
  public boolean isFillMethodWritable()
  {
    return isWritable(FILLMETHOD);
  }
  
  public boolean isFillMethodReadable()
  {
    return isReadable(FILLMETHOD);
  }
  
  public boolean isFillMethodModified()
  {
    return isModified(FILLMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getFillMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FILLMETHOD).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getHeight()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(HEIGHT));
  }
  
  public void setHeight(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(HEIGHT, "");
    }
    else
    {
      setValue(HEIGHT, value.toString());
    }
  }
  
  public boolean isHeightWritable()
  {
    return isWritable(HEIGHT);
  }
  
  public boolean isHeightReadable()
  {
    return isReadable(HEIGHT);
  }
  
  public boolean isHeightModified()
  {
    return isModified(HEIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getHeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HEIGHT).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getLid()
  {
    if(getValue(LID) == null || getValue(LID).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(LID));
    }
  }
  
  public String getLidId()
  {
    return getValue(LID);
  }
  
  public void setLid(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(LID, "");
    }
    else
    {
      setValue(LID, value.getId());
    }
  }
  
  public boolean isLidWritable()
  {
    return isWritable(LID);
  }
  
  public boolean isLidReadable()
  {
    return isReadable(LID);
  }
  
  public boolean isLidModified()
  {
    return isModified(LID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LID).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpeningDiameter()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGDIAMETER));
  }
  
  public void setOpeningDiameter(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGDIAMETER, "");
    }
    else
    {
      setValue(OPENINGDIAMETER, value.toString());
    }
  }
  
  public boolean isOpeningDiameterWritable()
  {
    return isWritable(OPENINGDIAMETER);
  }
  
  public boolean isOpeningDiameterReadable()
  {
    return isReadable(OPENINGDIAMETER);
  }
  
  public boolean isOpeningDiameterModified()
  {
    return isModified(OPENINGDIAMETER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningDiameterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGDIAMETER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpeningLength()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGLENGTH));
  }
  
  public void setOpeningLength(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGLENGTH, "");
    }
    else
    {
      setValue(OPENINGLENGTH, value.toString());
    }
  }
  
  public boolean isOpeningLengthWritable()
  {
    return isWritable(OPENINGLENGTH);
  }
  
  public boolean isOpeningLengthReadable()
  {
    return isReadable(OPENINGLENGTH);
  }
  
  public boolean isOpeningLengthModified()
  {
    return isModified(OPENINGLENGTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningLengthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGLENGTH).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpeningWidth()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGWIDTH));
  }
  
  public void setOpeningWidth(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGWIDTH, "");
    }
    else
    {
      setValue(OPENINGWIDTH, value.toString());
    }
  }
  
  public boolean isOpeningWidthWritable()
  {
    return isWritable(OPENINGWIDTH);
  }
  
  public boolean isOpeningWidthReadable()
  {
    return isReadable(OPENINGWIDTH);
  }
  
  public boolean isOpeningWidthModified()
  {
    return isModified(OPENINGWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGWIDTH).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.PupalPremiseDTO getPremise()
  {
    if(getValue(PREMISE) == null || getValue(PREMISE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.PupalPremiseDTO.get(getRequest(), getValue(PREMISE));
    }
  }
  
  public String getPremiseId()
  {
    return getValue(PREMISE);
  }
  
  public void setPremise(dss.vector.solutions.entomology.PupalPremiseDTO value)
  {
    if(value == null)
    {
      setValue(PREMISE, "");
    }
    else
    {
      setValue(PREMISE, value.getId());
    }
  }
  
  public boolean isPremiseWritable()
  {
    return isWritable(PREMISE);
  }
  
  public boolean isPremiseReadable()
  {
    return isReadable(PREMISE);
  }
  
  public boolean isPremiseModified()
  {
    return isModified(PREMISE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPremiseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PREMISE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRoof()
  {
    if(getValue(ROOF) == null || getValue(ROOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ROOF));
    }
  }
  
  public String getRoofId()
  {
    return getValue(ROOF);
  }
  
  public void setRoof(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ROOF, "");
    }
    else
    {
      setValue(ROOF, value.getId());
    }
  }
  
  public boolean isRoofWritable()
  {
    return isWritable(ROOF);
  }
  
  public boolean isRoofReadable()
  {
    return isReadable(ROOF);
  }
  
  public boolean isRoofModified()
  {
    return isModified(ROOF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRoofMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOF).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getShading()
  {
    if(getValue(SHADING) == null || getValue(SHADING).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SHADING));
    }
  }
  
  public String getShadingId()
  {
    return getValue(SHADING);
  }
  
  public void setShading(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SHADING, "");
    }
    else
    {
      setValue(SHADING, value.getId());
    }
  }
  
  public boolean isShadingWritable()
  {
    return isWritable(SHADING);
  }
  
  public boolean isShadingReadable()
  {
    return isReadable(SHADING);
  }
  
  public boolean isShadingModified()
  {
    return isModified(SHADING);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getShadingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SHADING).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.ContainerShapeDTO> getShape()
  {
    return (java.util.List<dss.vector.solutions.entomology.ContainerShapeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.entomology.ContainerShapeDTO.CLASS, getEnumNames(SHAPE));
  }
  
  public java.util.List<String> getShapeEnumNames()
  {
    return getEnumNames(SHAPE);
  }
  
  public void addShape(dss.vector.solutions.entomology.ContainerShapeDTO enumDTO)
  {
    addEnumItem(SHAPE, enumDTO.toString());
  }
  
  public void removeShape(dss.vector.solutions.entomology.ContainerShapeDTO enumDTO)
  {
    removeEnumItem(SHAPE, enumDTO.toString());
  }
  
  public void clearShape()
  {
    clearEnum(SHAPE);
  }
  
  public boolean isShapeWritable()
  {
    return isWritable(SHAPE);
  }
  
  public boolean isShapeReadable()
  {
    return isReadable(SHAPE);
  }
  
  public boolean isShapeModified()
  {
    return isModified(SHAPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getShapeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(SHAPE).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getWidth()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(WIDTH));
  }
  
  public void setWidth(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(WIDTH, "");
    }
    else
    {
      setValue(WIDTH, value.toString());
    }
  }
  
  public boolean isWidthWritable()
  {
    return isWritable(WIDTH);
  }
  
  public boolean isWidthReadable()
  {
    return isReadable(WIDTH);
  }
  
  public boolean isWidthModified()
  {
    return isModified(WIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WIDTH).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.PupalContainerViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalContainerDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalContainerViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalContainerDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllPupal()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllPupal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO> getAllPupalRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO> getAllPupalRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.PupalContainerAmountDTO addPupal(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.PupalContainerAmountDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.PupalContainerAmountDTO addPupal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.PupalContainerAmountDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public void removePupal(dss.vector.solutions.entomology.PupalContainerAmountDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removePupal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.PupalContainerAmountDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllPupal()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public static void removeAllPupal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.PupalContainerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.PupalContainerDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.entomology.PupalContainerQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.PupalContainerQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.PupalContainerDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.PupalContainerDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalContainerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.PupalContainerDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalContainerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
