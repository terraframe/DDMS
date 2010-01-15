package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1520723737)
public abstract class StylesDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Styles";
  private static final long serialVersionUID = 1520723737;
  
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
  
  public static java.lang.String ANCHORPOINTX = "anchorPointX";
  public static java.lang.String ANCHORPOINTY = "anchorPointY";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISPLACEMENTX = "displacementX";
  public static java.lang.String DISPLACEMENTY = "displacementY";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILL = "fill";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLES = "fontStyles";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LABELHALOFILL = "labelHaloFill";
  public static java.lang.String LABELHALOOPACITY = "labelHaloOpacity";
  public static java.lang.String LABELHALORADIUS = "labelHaloRadius";
  public static java.lang.String LABELROTATION = "labelRotation";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POINTMARKER = "pointMarker";
  public static java.lang.String POINTROTATION = "pointRotation";
  public static java.lang.String POINTSIZE = "pointSize";
  public static java.lang.String POINTSTROKE = "pointStroke";
  public static java.lang.String POINTSTROKEOPACITY = "pointStrokeOpacity";
  public static java.lang.String POINTWIDTH = "pointWidth";
  public static java.lang.String POLYGONFILL = "polygonFill";
  public static java.lang.String POLYGONFILLOPACITY = "polygonFillOpacity";
  public static java.lang.String POLYGONSTROKE = "polygonStroke";
  public static java.lang.String POLYGONSTROKEOPACITY = "polygonStrokeOpacity";
  public static java.lang.String POLYGONWIDTH = "polygonWidth";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public java.math.BigDecimal getAnchorPointX()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(ANCHORPOINTX));
  }
  
  public void setAnchorPointX(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(ANCHORPOINTX, "");
    }
    else
    {
      setValue(ANCHORPOINTX, value.toString());
    }
  }
  
  public boolean isAnchorPointXWritable()
  {
    return isWritable(ANCHORPOINTX);
  }
  
  public boolean isAnchorPointXReadable()
  {
    return isReadable(ANCHORPOINTX);
  }
  
  public boolean isAnchorPointXModified()
  {
    return isModified(ANCHORPOINTX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getAnchorPointXMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ANCHORPOINTX).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getAnchorPointY()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(ANCHORPOINTY));
  }
  
  public void setAnchorPointY(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(ANCHORPOINTY, "");
    }
    else
    {
      setValue(ANCHORPOINTY, value.toString());
    }
  }
  
  public boolean isAnchorPointYWritable()
  {
    return isWritable(ANCHORPOINTY);
  }
  
  public boolean isAnchorPointYReadable()
  {
    return isReadable(ANCHORPOINTY);
  }
  
  public boolean isAnchorPointYModified()
  {
    return isModified(ANCHORPOINTY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getAnchorPointYMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ANCHORPOINTY).getAttributeMdDTO();
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
  
  public Integer getDisplacementX()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLACEMENTX));
  }
  
  public void setDisplacementX(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLACEMENTX, "");
    }
    else
    {
      setValue(DISPLACEMENTX, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplacementXWritable()
  {
    return isWritable(DISPLACEMENTX);
  }
  
  public boolean isDisplacementXReadable()
  {
    return isReadable(DISPLACEMENTX);
  }
  
  public boolean isDisplacementXModified()
  {
    return isModified(DISPLACEMENTX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplacementXMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLACEMENTX).getAttributeMdDTO();
  }
  
  public Integer getDisplacementY()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLACEMENTY));
  }
  
  public void setDisplacementY(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLACEMENTY, "");
    }
    else
    {
      setValue(DISPLACEMENTY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplacementYWritable()
  {
    return isWritable(DISPLACEMENTY);
  }
  
  public boolean isDisplacementYReadable()
  {
    return isReadable(DISPLACEMENTY);
  }
  
  public boolean isDisplacementYModified()
  {
    return isModified(DISPLACEMENTY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDisplacementYMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLACEMENTY).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(FONTSTYLES));
  }
  
  public java.util.List<String> getFontStylesEnumNames()
  {
    return getEnumNames(FONTSTYLES);
  }
  
  public void addFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    addEnumItem(FONTSTYLES, enumDTO.toString());
  }
  
  public void removeFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    removeEnumItem(FONTSTYLES, enumDTO.toString());
  }
  
  public void clearFontStyles()
  {
    clearEnum(FONTSTYLES);
  }
  
  public boolean isFontStylesWritable()
  {
    return isWritable(FONTSTYLES);
  }
  
  public boolean isFontStylesReadable()
  {
    return isReadable(FONTSTYLES);
  }
  
  public boolean isFontStylesModified()
  {
    return isModified(FONTSTYLES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getFontStylesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(FONTSTYLES).getAttributeMdDTO();
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
  
  public String getLabelHaloFill()
  {
    return getValue(LABELHALOFILL);
  }
  
  public void setLabelHaloFill(String value)
  {
    if(value == null)
    {
      setValue(LABELHALOFILL, "");
    }
    else
    {
      setValue(LABELHALOFILL, value);
    }
  }
  
  public boolean isLabelHaloFillWritable()
  {
    return isWritable(LABELHALOFILL);
  }
  
  public boolean isLabelHaloFillReadable()
  {
    return isReadable(LABELHALOFILL);
  }
  
  public boolean isLabelHaloFillModified()
  {
    return isModified(LABELHALOFILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLabelHaloFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABELHALOFILL).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getLabelHaloOpacity()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(LABELHALOOPACITY));
  }
  
  public void setLabelHaloOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(LABELHALOOPACITY, "");
    }
    else
    {
      setValue(LABELHALOOPACITY, value.toString());
    }
  }
  
  public boolean isLabelHaloOpacityWritable()
  {
    return isWritable(LABELHALOOPACITY);
  }
  
  public boolean isLabelHaloOpacityReadable()
  {
    return isReadable(LABELHALOOPACITY);
  }
  
  public boolean isLabelHaloOpacityModified()
  {
    return isModified(LABELHALOOPACITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getLabelHaloOpacityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(LABELHALOOPACITY).getAttributeMdDTO();
  }
  
  public Integer getLabelHaloRadius()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LABELHALORADIUS));
  }
  
  public void setLabelHaloRadius(Integer value)
  {
    if(value == null)
    {
      setValue(LABELHALORADIUS, "");
    }
    else
    {
      setValue(LABELHALORADIUS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLabelHaloRadiusWritable()
  {
    return isWritable(LABELHALORADIUS);
  }
  
  public boolean isLabelHaloRadiusReadable()
  {
    return isReadable(LABELHALORADIUS);
  }
  
  public boolean isLabelHaloRadiusModified()
  {
    return isModified(LABELHALORADIUS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLabelHaloRadiusMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LABELHALORADIUS).getAttributeMdDTO();
  }
  
  public Integer getLabelRotation()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LABELROTATION));
  }
  
  public void setLabelRotation(Integer value)
  {
    if(value == null)
    {
      setValue(LABELROTATION, "");
    }
    else
    {
      setValue(LABELROTATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLabelRotationWritable()
  {
    return isWritable(LABELROTATION);
  }
  
  public boolean isLabelRotationReadable()
  {
    return isReadable(LABELROTATION);
  }
  
  public boolean isLabelRotationModified()
  {
    return isModified(LABELROTATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLabelRotationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LABELROTATION).getAttributeMdDTO();
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
    return (java.util.List<dss.vector.solutions.query.WellKnownNamesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.WellKnownNamesDTO.CLASS, getEnumNames(POINTMARKER));
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
  
  public Integer getPointRotation()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTROTATION));
  }
  
  public void setPointRotation(Integer value)
  {
    if(value == null)
    {
      setValue(POINTROTATION, "");
    }
    else
    {
      setValue(POINTROTATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointRotationWritable()
  {
    return isWritable(POINTROTATION);
  }
  
  public boolean isPointRotationReadable()
  {
    return isReadable(POINTROTATION);
  }
  
  public boolean isPointRotationModified()
  {
    return isModified(POINTROTATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPointRotationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTROTATION).getAttributeMdDTO();
  }
  
  public Integer getPointSize()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTSIZE));
  }
  
  public void setPointSize(Integer value)
  {
    if(value == null)
    {
      setValue(POINTSIZE, "");
    }
    else
    {
      setValue(POINTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointSizeWritable()
  {
    return isWritable(POINTSIZE);
  }
  
  public boolean isPointSizeReadable()
  {
    return isReadable(POINTSIZE);
  }
  
  public boolean isPointSizeModified()
  {
    return isModified(POINTSIZE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPointSizeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTSIZE).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPointStrokeOpacity()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POINTSTROKEOPACITY));
  }
  
  public void setPointStrokeOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POINTSTROKEOPACITY, "");
    }
    else
    {
      setValue(POINTSTROKEOPACITY, value.toString());
    }
  }
  
  public boolean isPointStrokeOpacityWritable()
  {
    return isWritable(POINTSTROKEOPACITY);
  }
  
  public boolean isPointStrokeOpacityReadable()
  {
    return isReadable(POINTSTROKEOPACITY);
  }
  
  public boolean isPointStrokeOpacityModified()
  {
    return isModified(POINTSTROKEOPACITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getPointStrokeOpacityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POINTSTROKEOPACITY).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPolygonFillOpacity()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POLYGONFILLOPACITY));
  }
  
  public void setPolygonFillOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POLYGONFILLOPACITY, "");
    }
    else
    {
      setValue(POLYGONFILLOPACITY, value.toString());
    }
  }
  
  public boolean isPolygonFillOpacityWritable()
  {
    return isWritable(POLYGONFILLOPACITY);
  }
  
  public boolean isPolygonFillOpacityReadable()
  {
    return isReadable(POLYGONFILLOPACITY);
  }
  
  public boolean isPolygonFillOpacityModified()
  {
    return isModified(POLYGONFILLOPACITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getPolygonFillOpacityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POLYGONFILLOPACITY).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPolygonStrokeOpacity()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POLYGONSTROKEOPACITY));
  }
  
  public void setPolygonStrokeOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKEOPACITY, "");
    }
    else
    {
      setValue(POLYGONSTROKEOPACITY, value.toString());
    }
  }
  
  public boolean isPolygonStrokeOpacityWritable()
  {
    return isWritable(POLYGONSTROKEOPACITY);
  }
  
  public boolean isPolygonStrokeOpacityReadable()
  {
    return isReadable(POLYGONSTROKEOPACITY);
  }
  
  public boolean isPolygonStrokeOpacityModified()
  {
    return isModified(POLYGONSTROKEOPACITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getPolygonStrokeOpacityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POLYGONSTROKEOPACITY).getAttributeMdDTO();
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
