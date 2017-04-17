package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = 1774239277)
public abstract class StockEventDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.StockEvent";
  private static final long serialVersionUID = 1774239277;
  
  protected StockEventDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected StockEventDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COST = "cost";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String EVENTDATE = "eventDate";
  public static java.lang.String ID = "id";
  public static java.lang.String ITEM = "item";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OTHERPARTY = "otherParty";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STAFF = "staff";
  public static java.lang.String STOCKDEPOT = "stockDepot";
  public static java.lang.String TRANSACTIONTYPE = "transactionType";
  public static java.lang.String TYPE = "type";
  public java.math.BigDecimal getCost()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(COST));
  }
  
  public void setCost(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(COST, "");
    }
    else
    {
      setValue(COST, value.toString());
    }
  }
  
  public boolean isCostWritable()
  {
    return isWritable(COST);
  }
  
  public boolean isCostReadable()
  {
    return isReadable(COST);
  }
  
  public boolean isCostModified()
  {
    return isModified(COST);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getCostMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(COST).getAttributeMdDTO();
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
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
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
  
  public java.util.Date getEventDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(EVENTDATE));
  }
  
  public void setEventDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(EVENTDATE, "");
    }
    else
    {
      setValue(EVENTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEventDateWritable()
  {
    return isWritable(EVENTDATE);
  }
  
  public boolean isEventDateReadable()
  {
    return isReadable(EVENTDATE);
  }
  
  public boolean isEventDateModified()
  {
    return isModified(EVENTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEventDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(EVENTDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.stock.StockItemDTO getItem()
  {
    if(getValue(ITEM) == null || getValue(ITEM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockItemDTO.get(getRequest(), getValue(ITEM));
    }
  }
  
  public String getItemId()
  {
    return getValue(ITEM);
  }
  
  public void setItem(dss.vector.solutions.stock.StockItemDTO value)
  {
    if(value == null)
    {
      setValue(ITEM, "");
    }
    else
    {
      setValue(ITEM, value.getId());
    }
  }
  
  public boolean isItemWritable()
  {
    return isWritable(ITEM);
  }
  
  public boolean isItemReadable()
  {
    return isReadable(ITEM);
  }
  
  public boolean isItemModified()
  {
    return isModified(ITEM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getItemMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITEM).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public String getOtherParty()
  {
    return getValue(OTHERPARTY);
  }
  
  public void setOtherParty(String value)
  {
    if(value == null)
    {
      setValue(OTHERPARTY, "");
    }
    else
    {
      setValue(OTHERPARTY, value);
    }
  }
  
  public boolean isOtherPartyWritable()
  {
    return isWritable(OTHERPARTY);
  }
  
  public boolean isOtherPartyReadable()
  {
    return isReadable(OTHERPARTY);
  }
  
  public boolean isOtherPartyModified()
  {
    return isModified(OTHERPARTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOtherPartyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OTHERPARTY).getAttributeMdDTO();
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
  
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityWritable()
  {
    return isWritable(QUANTITY);
  }
  
  public boolean isQuantityReadable()
  {
    return isReadable(QUANTITY);
  }
  
  public boolean isQuantityModified()
  {
    return isModified(QUANTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
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
  
  public dss.vector.solutions.stock.StockStaffDTO getStaff()
  {
    if(getValue(STAFF) == null || getValue(STAFF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockStaffDTO.get(getRequest(), getValue(STAFF));
    }
  }
  
  public String getStaffId()
  {
    return getValue(STAFF);
  }
  
  public void setStaff(dss.vector.solutions.stock.StockStaffDTO value)
  {
    if(value == null)
    {
      setValue(STAFF, "");
    }
    else
    {
      setValue(STAFF, value.getId());
    }
  }
  
  public boolean isStaffWritable()
  {
    return isWritable(STAFF);
  }
  
  public boolean isStaffReadable()
  {
    return isReadable(STAFF);
  }
  
  public boolean isStaffModified()
  {
    return isModified(STAFF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStaffMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STAFF).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getStockDepot()
  {
    if(getValue(STOCKDEPOT) == null || getValue(STOCKDEPOT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(STOCKDEPOT));
    }
  }
  
  public String getStockDepotId()
  {
    return getValue(STOCKDEPOT);
  }
  
  public void setStockDepot(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(STOCKDEPOT, "");
    }
    else
    {
      setValue(STOCKDEPOT, value.getId());
    }
  }
  
  public boolean isStockDepotWritable()
  {
    return isWritable(STOCKDEPOT);
  }
  
  public boolean isStockDepotReadable()
  {
    return isReadable(STOCKDEPOT);
  }
  
  public boolean isStockDepotModified()
  {
    return isModified(STOCKDEPOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStockDepotMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKDEPOT).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.stock.EventOptionDTO> getTransactionType()
  {
    return (java.util.List<dss.vector.solutions.stock.EventOptionDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.stock.EventOptionDTO.CLASS, getEnumNames(TRANSACTIONTYPE));
  }
  
  public java.util.List<String> getTransactionTypeEnumNames()
  {
    return getEnumNames(TRANSACTIONTYPE);
  }
  
  public void addTransactionType(dss.vector.solutions.stock.EventOptionDTO enumDTO)
  {
    addEnumItem(TRANSACTIONTYPE, enumDTO.toString());
  }
  
  public void removeTransactionType(dss.vector.solutions.stock.EventOptionDTO enumDTO)
  {
    removeEnumItem(TRANSACTIONTYPE, enumDTO.toString());
  }
  
  public void clearTransactionType()
  {
    clearEnum(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeWritable()
  {
    return isWritable(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeReadable()
  {
    return isReadable(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeModified()
  {
    return isModified(TRANSACTIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getTransactionTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TRANSACTIONTYPE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.stock.StockEventViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockEventViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.stock.StockEventViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockEventViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.stock.StockEventViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockEventViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.stock.StockEventDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.stock.StockEventDTO) dto;
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
  
  public static dss.vector.solutions.stock.StockEventQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.stock.StockEventQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.stock.StockEventDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.stock.StockEventDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.stock.StockEventDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
