package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1818729239)
public abstract class CategoryGenDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.CategoryGen";
  private static final long serialVersionUID = 1818729239;
  
  protected CategoryGenDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CategoryGenDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CATEGORYCOUNT = "categoryCount";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACTORYTYPE = "factoryType";
  public static java.lang.String FONTFILLEND = "fontFillEnd";
  public static java.lang.String FONTFILLSTART = "fontFillStart";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LABELHALOFILLEND = "labelHaloFillEnd";
  public static java.lang.String LABELHALOFILLSTART = "labelHaloFillStart";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POINTSIZEEND = "pointSizeEnd";
  public static java.lang.String POINTSIZESTART = "pointSizeStart";
  public static java.lang.String POINTSTROKEEND = "pointStrokeEnd";
  public static java.lang.String POINTSTROKESTART = "pointStrokeStart";
  public static java.lang.String POLYGONFILLEND = "polygonFillEnd";
  public static java.lang.String POLYGONFILLSTART = "polygonFillStart";
  public static java.lang.String POLYGONSTROKEEND = "polygonStrokeEnd";
  public static java.lang.String POLYGONSTROKESTART = "polygonStrokeStart";
  public static java.lang.String PRECISIONFIGURES = "precisionFigures";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public Integer getCategoryCount()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATEGORYCOUNT));
  }
  
  public void setCategoryCount(Integer value)
  {
    if(value == null)
    {
      setValue(CATEGORYCOUNT, "");
    }
    else
    {
      setValue(CATEGORYCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCategoryCountWritable()
  {
    return isWritable(CATEGORYCOUNT);
  }
  
  public boolean isCategoryCountReadable()
  {
    return isReadable(CATEGORYCOUNT);
  }
  
  public boolean isCategoryCountModified()
  {
    return isModified(CATEGORYCOUNT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCategoryCountMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATEGORYCOUNT).getAttributeMdDTO();
  }
  
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
  
  public String getFactoryType()
  {
    return getValue(FACTORYTYPE);
  }
  
  public void setFactoryType(String value)
  {
    if(value == null)
    {
      setValue(FACTORYTYPE, "");
    }
    else
    {
      setValue(FACTORYTYPE, value);
    }
  }
  
  public boolean isFactoryTypeWritable()
  {
    return isWritable(FACTORYTYPE);
  }
  
  public boolean isFactoryTypeReadable()
  {
    return isReadable(FACTORYTYPE);
  }
  
  public boolean isFactoryTypeModified()
  {
    return isModified(FACTORYTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFactoryTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACTORYTYPE).getAttributeMdDTO();
  }
  
  public String getFontFillEnd()
  {
    return getValue(FONTFILLEND);
  }
  
  public void setFontFillEnd(String value)
  {
    if(value == null)
    {
      setValue(FONTFILLEND, "");
    }
    else
    {
      setValue(FONTFILLEND, value);
    }
  }
  
  public boolean isFontFillEndWritable()
  {
    return isWritable(FONTFILLEND);
  }
  
  public boolean isFontFillEndReadable()
  {
    return isReadable(FONTFILLEND);
  }
  
  public boolean isFontFillEndModified()
  {
    return isModified(FONTFILLEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontFillEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFILLEND).getAttributeMdDTO();
  }
  
  public String getFontFillStart()
  {
    return getValue(FONTFILLSTART);
  }
  
  public void setFontFillStart(String value)
  {
    if(value == null)
    {
      setValue(FONTFILLSTART, "");
    }
    else
    {
      setValue(FONTFILLSTART, value);
    }
  }
  
  public boolean isFontFillStartWritable()
  {
    return isWritable(FONTFILLSTART);
  }
  
  public boolean isFontFillStartReadable()
  {
    return isReadable(FONTFILLSTART);
  }
  
  public boolean isFontFillStartModified()
  {
    return isModified(FONTFILLSTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontFillStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFILLSTART).getAttributeMdDTO();
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
  
  public String getLabelHaloFillEnd()
  {
    return getValue(LABELHALOFILLEND);
  }
  
  public void setLabelHaloFillEnd(String value)
  {
    if(value == null)
    {
      setValue(LABELHALOFILLEND, "");
    }
    else
    {
      setValue(LABELHALOFILLEND, value);
    }
  }
  
  public boolean isLabelHaloFillEndWritable()
  {
    return isWritable(LABELHALOFILLEND);
  }
  
  public boolean isLabelHaloFillEndReadable()
  {
    return isReadable(LABELHALOFILLEND);
  }
  
  public boolean isLabelHaloFillEndModified()
  {
    return isModified(LABELHALOFILLEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLabelHaloFillEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABELHALOFILLEND).getAttributeMdDTO();
  }
  
  public String getLabelHaloFillStart()
  {
    return getValue(LABELHALOFILLSTART);
  }
  
  public void setLabelHaloFillStart(String value)
  {
    if(value == null)
    {
      setValue(LABELHALOFILLSTART, "");
    }
    else
    {
      setValue(LABELHALOFILLSTART, value);
    }
  }
  
  public boolean isLabelHaloFillStartWritable()
  {
    return isWritable(LABELHALOFILLSTART);
  }
  
  public boolean isLabelHaloFillStartReadable()
  {
    return isReadable(LABELHALOFILLSTART);
  }
  
  public boolean isLabelHaloFillStartModified()
  {
    return isModified(LABELHALOFILLSTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLabelHaloFillStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABELHALOFILLSTART).getAttributeMdDTO();
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
  
  public String getLayerId()
  {
    return getValue(LAYERID);
  }
  
  public void setLayerId(String value)
  {
    if(value == null)
    {
      setValue(LAYERID, "");
    }
    else
    {
      setValue(LAYERID, value);
    }
  }
  
  public boolean isLayerIdWritable()
  {
    return isWritable(LAYERID);
  }
  
  public boolean isLayerIdReadable()
  {
    return isReadable(LAYERID);
  }
  
  public boolean isLayerIdModified()
  {
    return isModified(LAYERID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLayerIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERID).getAttributeMdDTO();
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
  
  public Integer getPointSizeEnd()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTSIZEEND));
  }
  
  public void setPointSizeEnd(Integer value)
  {
    if(value == null)
    {
      setValue(POINTSIZEEND, "");
    }
    else
    {
      setValue(POINTSIZEEND, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointSizeEndWritable()
  {
    return isWritable(POINTSIZEEND);
  }
  
  public boolean isPointSizeEndReadable()
  {
    return isReadable(POINTSIZEEND);
  }
  
  public boolean isPointSizeEndModified()
  {
    return isModified(POINTSIZEEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPointSizeEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTSIZEEND).getAttributeMdDTO();
  }
  
  public Integer getPointSizeStart()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTSIZESTART));
  }
  
  public void setPointSizeStart(Integer value)
  {
    if(value == null)
    {
      setValue(POINTSIZESTART, "");
    }
    else
    {
      setValue(POINTSIZESTART, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointSizeStartWritable()
  {
    return isWritable(POINTSIZESTART);
  }
  
  public boolean isPointSizeStartReadable()
  {
    return isReadable(POINTSIZESTART);
  }
  
  public boolean isPointSizeStartModified()
  {
    return isModified(POINTSIZESTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPointSizeStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTSIZESTART).getAttributeMdDTO();
  }
  
  public String getPointStrokeEnd()
  {
    return getValue(POINTSTROKEEND);
  }
  
  public void setPointStrokeEnd(String value)
  {
    if(value == null)
    {
      setValue(POINTSTROKEEND, "");
    }
    else
    {
      setValue(POINTSTROKEEND, value);
    }
  }
  
  public boolean isPointStrokeEndWritable()
  {
    return isWritable(POINTSTROKEEND);
  }
  
  public boolean isPointStrokeEndReadable()
  {
    return isReadable(POINTSTROKEEND);
  }
  
  public boolean isPointStrokeEndModified()
  {
    return isModified(POINTSTROKEEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPointStrokeEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POINTSTROKEEND).getAttributeMdDTO();
  }
  
  public String getPointStrokeStart()
  {
    return getValue(POINTSTROKESTART);
  }
  
  public void setPointStrokeStart(String value)
  {
    if(value == null)
    {
      setValue(POINTSTROKESTART, "");
    }
    else
    {
      setValue(POINTSTROKESTART, value);
    }
  }
  
  public boolean isPointStrokeStartWritable()
  {
    return isWritable(POINTSTROKESTART);
  }
  
  public boolean isPointStrokeStartReadable()
  {
    return isReadable(POINTSTROKESTART);
  }
  
  public boolean isPointStrokeStartModified()
  {
    return isModified(POINTSTROKESTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPointStrokeStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POINTSTROKESTART).getAttributeMdDTO();
  }
  
  public String getPolygonFillEnd()
  {
    return getValue(POLYGONFILLEND);
  }
  
  public void setPolygonFillEnd(String value)
  {
    if(value == null)
    {
      setValue(POLYGONFILLEND, "");
    }
    else
    {
      setValue(POLYGONFILLEND, value);
    }
  }
  
  public boolean isPolygonFillEndWritable()
  {
    return isWritable(POLYGONFILLEND);
  }
  
  public boolean isPolygonFillEndReadable()
  {
    return isReadable(POLYGONFILLEND);
  }
  
  public boolean isPolygonFillEndModified()
  {
    return isModified(POLYGONFILLEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonFillEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONFILLEND).getAttributeMdDTO();
  }
  
  public String getPolygonFillStart()
  {
    return getValue(POLYGONFILLSTART);
  }
  
  public void setPolygonFillStart(String value)
  {
    if(value == null)
    {
      setValue(POLYGONFILLSTART, "");
    }
    else
    {
      setValue(POLYGONFILLSTART, value);
    }
  }
  
  public boolean isPolygonFillStartWritable()
  {
    return isWritable(POLYGONFILLSTART);
  }
  
  public boolean isPolygonFillStartReadable()
  {
    return isReadable(POLYGONFILLSTART);
  }
  
  public boolean isPolygonFillStartModified()
  {
    return isModified(POLYGONFILLSTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonFillStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONFILLSTART).getAttributeMdDTO();
  }
  
  public String getPolygonStrokeEnd()
  {
    return getValue(POLYGONSTROKEEND);
  }
  
  public void setPolygonStrokeEnd(String value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKEEND, "");
    }
    else
    {
      setValue(POLYGONSTROKEEND, value);
    }
  }
  
  public boolean isPolygonStrokeEndWritable()
  {
    return isWritable(POLYGONSTROKEEND);
  }
  
  public boolean isPolygonStrokeEndReadable()
  {
    return isReadable(POLYGONSTROKEEND);
  }
  
  public boolean isPolygonStrokeEndModified()
  {
    return isModified(POLYGONSTROKEEND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonStrokeEndMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONSTROKEEND).getAttributeMdDTO();
  }
  
  public String getPolygonStrokeStart()
  {
    return getValue(POLYGONSTROKESTART);
  }
  
  public void setPolygonStrokeStart(String value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKESTART, "");
    }
    else
    {
      setValue(POLYGONSTROKESTART, value);
    }
  }
  
  public boolean isPolygonStrokeStartWritable()
  {
    return isWritable(POLYGONSTROKESTART);
  }
  
  public boolean isPolygonStrokeStartReadable()
  {
    return isReadable(POLYGONSTROKESTART);
  }
  
  public boolean isPolygonStrokeStartModified()
  {
    return isModified(POLYGONSTROKESTART);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPolygonStrokeStartMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONSTROKESTART).getAttributeMdDTO();
  }
  
  public Integer getPrecisionFigures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRECISIONFIGURES));
  }
  
  public void setPrecisionFigures(Integer value)
  {
    if(value == null)
    {
      setValue(PRECISIONFIGURES, "");
    }
    else
    {
      setValue(PRECISIONFIGURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrecisionFiguresWritable()
  {
    return isWritable(PRECISIONFIGURES);
  }
  
  public boolean isPrecisionFiguresReadable()
  {
    return isReadable(PRECISIONFIGURES);
  }
  
  public boolean isPrecisionFiguresModified()
  {
    return isModified(PRECISIONFIGURES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPrecisionFiguresMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PRECISIONFIGURES).getAttributeMdDTO();
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
  
  public static dss.vector.solutions.query.CategoryGenDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.CategoryGenDTO) dto;
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
  
  public static dss.vector.solutions.query.CategoryGenQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.CategoryGenQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.CategoryGen", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.CategoryGenDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.CategoryGenDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.CategoryGenDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.CategoryGenDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.CategoryGenDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.CategoryGenDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
