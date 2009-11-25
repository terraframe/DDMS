package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1008110541)
public abstract class StylesDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Styles";
  private static final long serialVersionUID = -1008110541;
  
  protected StylesDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected StylesDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILL = "fill";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLE = "fontStyle";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POINTMARKER = "pointMarker";
  public static java.lang.String POINTSTROKE = "pointStroke";
  public static java.lang.String POINTWIDTH = "pointWidth";
  public static java.lang.String POLYGONFILL = "polygonFill";
  public static java.lang.String POLYGONSTROKE = "polygonStroke";
  public static java.lang.String POLYGONWIDTH = "polygonWidth";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomainDTO value)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public String getFill()
  {
    return getValue(FILL);
  }
  
  public void setFill(String value)
  {
    if(value == null)
    {
      setValue(FILL, "");
    }
    else
    {
      setValue(FILL, value);
    }
  }
  
  public boolean isFillWritable()
  {
    return isWritable(FILL);
  }
  
  public boolean isFillReadable()
  {
    return isReadable(FILL);
  }
  
  public boolean isFillModified()
  {
    return isModified(FILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILL).getAttributeMdDTO();
  }
  
  public String getFontFamily()
  {
    return getValue(FONTFAMILY);
  }
  
  public void setFontFamily(String value)
  {
    if(value == null)
    {
      setValue(FONTFAMILY, "");
    }
    else
    {
      setValue(FONTFAMILY, value);
    }
  }
  
  public boolean isFontFamilyWritable()
  {
    return isWritable(FONTFAMILY);
  }
  
  public boolean isFontFamilyReadable()
  {
    return isReadable(FONTFAMILY);
  }
  
  public boolean isFontFamilyModified()
  {
    return isModified(FONTFAMILY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontFamilyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFAMILY).getAttributeMdDTO();
  }
  
  public Integer getFontSize()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FONTSIZE));
  }
  
  public void setFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(FONTSIZE, "");
    }
    else
    {
      setValue(FONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFontSizeWritable()
  {
    return isWritable(FONTSIZE);
  }
  
  public boolean isFontSizeReadable()
  {
    return isReadable(FONTSIZE);
  }
  
  public boolean isFontSizeModified()
  {
    return isModified(FONTSIZE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getFontSizeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FONTSIZE).getAttributeMdDTO();
  }
  
  public String getFontStyle()
  {
    return getValue(FONTSTYLE);
  }
  
  public void setFontStyle(String value)
  {
    if(value == null)
    {
      setValue(FONTSTYLE, "");
    }
    else
    {
      setValue(FONTSTYLE, value);
    }
  }
  
  public boolean isFontStyleWritable()
  {
    return isWritable(FONTSTYLE);
  }
  
  public boolean isFontStyleReadable()
  {
    return isReadable(FONTSTYLE);
  }
  
  public boolean isFontStyleModified()
  {
    return isModified(FONTSTYLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontStyleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTSTYLE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public void setOwner(com.terraframe.mojo.system.ActorDTO value)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.WellKnownNamesDTO> getPointMarker()
  {
    return (java.util.List<dss.vector.solutions.query.WellKnownNamesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.query.WellKnownNames", getEnumNames(POINTMARKER));
  }
  
  public java.util.List<String> getPointMarkerEnumNames()
  {
    return getEnumNames(POINTMARKER);
  }
  
  public void addPointMarker(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    addEnumItem(POINTMARKER, enumDTO.toString());
  }
  
  public void removePointMarker(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    removeEnumItem(POINTMARKER, enumDTO.toString());
  }
  
  public void clearPointMarker()
  {
    clearEnum(POINTMARKER);
  }
  
  public boolean isPointMarkerWritable()
  {
    return isWritable(POINTMARKER);
  }
  
  public boolean isPointMarkerReadable()
  {
    return isReadable(POINTMARKER);
  }
  
  public boolean isPointMarkerModified()
  {
    return isModified(POINTMARKER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getPointMarkerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(POINTMARKER).getAttributeMdDTO();
  }
  
  public String getPointStroke()
  {
    return getValue(POINTSTROKE);
  }
  
  public void setPointStroke(String value)
  {
    if(value == null)
    {
      setValue(POINTSTROKE, "");
    }
    else
    {
      setValue(POINTSTROKE, value);
    }
  }
  
  public boolean isPointStrokeWritable()
  {
    return isWritable(POINTSTROKE);
  }
  
  public boolean isPointStrokeReadable()
  {
    return isReadable(POINTSTROKE);
  }
  
  public boolean isPointStrokeModified()
  {
    return isModified(POINTSTROKE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPointStrokeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POINTSTROKE).getAttributeMdDTO();
  }
  
  public Integer getPointWidth()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTWIDTH));
  }
  
  public void setPointWidth(Integer value)
  {
    if(value == null)
    {
      setValue(POINTWIDTH, "");
    }
    else
    {
      setValue(POINTWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointWidthWritable()
  {
    return isWritable(POINTWIDTH);
  }
  
  public boolean isPointWidthReadable()
  {
    return isReadable(POINTWIDTH);
  }
  
  public boolean isPointWidthModified()
  {
    return isModified(POINTWIDTH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPointWidthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTWIDTH).getAttributeMdDTO();
  }
  
  public String getPolygonFill()
  {
    return getValue(POLYGONFILL);
  }
  
  public void setPolygonFill(String value)
  {
    if(value == null)
    {
      setValue(POLYGONFILL, "");
    }
    else
    {
      setValue(POLYGONFILL, value);
    }
  }
  
  public boolean isPolygonFillWritable()
  {
    return isWritable(POLYGONFILL);
  }
  
  public boolean isPolygonFillReadable()
  {
    return isReadable(POLYGONFILL);
  }
  
  public boolean isPolygonFillModified()
  {
    return isModified(POLYGONFILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONFILL).getAttributeMdDTO();
  }
  
  public String getPolygonStroke()
  {
    return getValue(POLYGONSTROKE);
  }
  
  public void setPolygonStroke(String value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKE, "");
    }
    else
    {
      setValue(POLYGONSTROKE, value);
    }
  }
  
  public boolean isPolygonStrokeWritable()
  {
    return isWritable(POLYGONSTROKE);
  }
  
  public boolean isPolygonStrokeReadable()
  {
    return isReadable(POLYGONSTROKE);
  }
  
  public boolean isPolygonStrokeModified()
  {
    return isModified(POLYGONSTROKE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonStrokeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONSTROKE).getAttributeMdDTO();
  }
  
  public Integer getPolygonWidth()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POLYGONWIDTH));
  }
  
  public void setPolygonWidth(Integer value)
  {
    if(value == null)
    {
      setValue(POLYGONWIDTH, "");
    }
    else
    {
      setValue(POLYGONWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPolygonWidthWritable()
  {
    return isWritable(POLYGONWIDTH);
  }
  
  public boolean isPolygonWidthReadable()
  {
    return isReadable(POLYGONWIDTH);
  }
  
  public boolean isPolygonWidthModified()
  {
    return isModified(POLYGONWIDTH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPolygonWidthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POLYGONWIDTH).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.StylesDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.StylesDTO) dto;
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
  
  public static dss.vector.solutions.query.StylesQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.StylesQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.Styles", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.StylesDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.StylesDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.StylesDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.StylesDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.StylesDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.StylesDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
