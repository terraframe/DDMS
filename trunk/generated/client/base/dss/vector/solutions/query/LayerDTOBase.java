package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 428218279)
public abstract class LayerDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Layer";
  private static final long serialVersionUID = 428218279;
  
  protected LayerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LayerDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ADDTOBBOX = "addToBBox";
  public static java.lang.String CLIPTOBASELAYER = "clipToBaseLayer";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATERAWLEGEND = "createRawLegend";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEFAULTSTYLES = "defaultStyles";
  public static java.lang.String ENABLELEGEND = "enableLegend";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOHIERARCHY = "geoHierarchy";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LEGENDCOLOR = "legendColor";
  public static java.lang.String LEGENDFONTFAMILY = "legendFontFamily";
  public static java.lang.String LEGENDFONTFILL = "legendFontFill";
  public static java.lang.String LEGENDFONTSIZE = "legendFontSize";
  public static java.lang.String LEGENDFONTSTYLES = "legendFontStyles";
  public static java.lang.String LEGENDID = "legendId";
  public static java.lang.String LEGENDTITLE = "legendTitle";
  public static java.lang.String LEGENDTITLEFONTFAMILY = "legendTitleFontFamily";
  public static java.lang.String LEGENDTITLEFONTFILL = "legendTitleFontFill";
  public static java.lang.String LEGENDTITLEFONTSIZE = "legendTitleFontSize";
  public static java.lang.String LEGENDTITLEFONTSTYLES = "legendTitleFontStyles";
  public static java.lang.String LEGENDXPOSITION = "legendXPosition";
  public static java.lang.String LEGENDYPOSITION = "legendYPosition";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MDATTRIBUTE = "mdAttribute";
  public static java.lang.String OPACITY = "opacity";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String RENDERAS = "renderAs";
  public static java.lang.String SAVEDSEARCH = "savedSearch";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SHOWLEGENDBORDER = "showLegendBorder";
  public static java.lang.String SHOWTHEMATICVALUE = "showThematicValue";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLDFILE = "sldFile";
  public static java.lang.String THEMATICCOLUMNALIAS = "thematicColumnAlias";
  public static java.lang.String THEMATICUSERALIAS = "thematicUserAlias";
  public static java.lang.String THEMATICVARIABLE = "thematicVariable";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VIEWCREATED = "viewCreated";
  public static java.lang.String VIEWNAME = "viewName";
  public Boolean getAddToBBox()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ADDTOBBOX));
  }
  
  public void setAddToBBox(Boolean value)
  {
    if(value == null)
    {
      setValue(ADDTOBBOX, "");
    }
    else
    {
      setValue(ADDTOBBOX, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAddToBBoxWritable()
  {
    return isWritable(ADDTOBBOX);
  }
  
  public boolean isAddToBBoxReadable()
  {
    return isReadable(ADDTOBBOX);
  }
  
  public boolean isAddToBBoxModified()
  {
    return isModified(ADDTOBBOX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getAddToBBoxMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ADDTOBBOX).getAttributeMdDTO();
  }
  
  public Boolean getClipToBaseLayer()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CLIPTOBASELAYER));
  }
  
  public void setClipToBaseLayer(Boolean value)
  {
    if(value == null)
    {
      setValue(CLIPTOBASELAYER, "");
    }
    else
    {
      setValue(CLIPTOBASELAYER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isClipToBaseLayerWritable()
  {
    return isWritable(CLIPTOBASELAYER);
  }
  
  public boolean isClipToBaseLayerReadable()
  {
    return isReadable(CLIPTOBASELAYER);
  }
  
  public boolean isClipToBaseLayerModified()
  {
    return isModified(CLIPTOBASELAYER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getClipToBaseLayerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CLIPTOBASELAYER).getAttributeMdDTO();
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
  
  public Boolean getCreateRawLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CREATERAWLEGEND));
  }
  
  public void setCreateRawLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(CREATERAWLEGEND, "");
    }
    else
    {
      setValue(CREATERAWLEGEND, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isCreateRawLegendWritable()
  {
    return isWritable(CREATERAWLEGEND);
  }
  
  public boolean isCreateRawLegendReadable()
  {
    return isReadable(CREATERAWLEGEND);
  }
  
  public boolean isCreateRawLegendModified()
  {
    return isModified(CREATERAWLEGEND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getCreateRawLegendMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CREATERAWLEGEND).getAttributeMdDTO();
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
  
  public dss.vector.solutions.query.StylesDTO getDefaultStyles()
  {
    if(getValue(DEFAULTSTYLES) == null || getValue(DEFAULTSTYLES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.StylesDTO.get(getRequest(), getValue(DEFAULTSTYLES));
    }
  }
  
  public String getDefaultStylesId()
  {
    return getValue(DEFAULTSTYLES);
  }
  
  public void setDefaultStyles(dss.vector.solutions.query.StylesDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTSTYLES, "");
    }
    else
    {
      setValue(DEFAULTSTYLES, value.getId());
    }
  }
  
  public boolean isDefaultStylesWritable()
  {
    return isWritable(DEFAULTSTYLES);
  }
  
  public boolean isDefaultStylesReadable()
  {
    return isReadable(DEFAULTSTYLES);
  }
  
  public boolean isDefaultStylesModified()
  {
    return isModified(DEFAULTSTYLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDefaultStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTSTYLES).getAttributeMdDTO();
  }
  
  public Boolean getEnableLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLELEGEND));
  }
  
  public void setEnableLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLELEGEND, "");
    }
    else
    {
      setValue(ENABLELEGEND, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnableLegendWritable()
  {
    return isWritable(ENABLELEGEND);
  }
  
  public boolean isEnableLegendReadable()
  {
    return isReadable(ENABLELEGEND);
  }
  
  public boolean isEnableLegendModified()
  {
    return isModified(ENABLELEGEND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnableLegendMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLELEGEND).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.GeoHierarchyDTO getGeoHierarchy()
  {
    if(getValue(GEOHIERARCHY) == null || getValue(GEOHIERARCHY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(GEOHIERARCHY));
    }
  }
  
  public String getGeoHierarchyId()
  {
    return getValue(GEOHIERARCHY);
  }
  
  public void setGeoHierarchy(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHY, "");
    }
    else
    {
      setValue(GEOHIERARCHY, value.getId());
    }
  }
  
  public boolean isGeoHierarchyWritable()
  {
    return isWritable(GEOHIERARCHY);
  }
  
  public boolean isGeoHierarchyReadable()
  {
    return isReadable(GEOHIERARCHY);
  }
  
  public boolean isGeoHierarchyModified()
  {
    return isModified(GEOHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOHIERARCHY).getAttributeMdDTO();
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
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public boolean isLayerNameWritable()
  {
    return isWritable(LAYERNAME);
  }
  
  public boolean isLayerNameReadable()
  {
    return isReadable(LAYERNAME);
  }
  
  public boolean isLayerNameModified()
  {
    return isModified(LAYERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLayerNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERNAME).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdAttributeDTO getLegendColor()
  {
    if(getValue(LEGENDCOLOR) == null || getValue(LEGENDCOLOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(LEGENDCOLOR));
    }
  }
  
  public String getLegendColorId()
  {
    return getValue(LEGENDCOLOR);
  }
  
  public void setLegendColor(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(LEGENDCOLOR, "");
    }
    else
    {
      setValue(LEGENDCOLOR, value.getId());
    }
  }
  
  public boolean isLegendColorWritable()
  {
    return isWritable(LEGENDCOLOR);
  }
  
  public boolean isLegendColorReadable()
  {
    return isReadable(LEGENDCOLOR);
  }
  
  public boolean isLegendColorModified()
  {
    return isModified(LEGENDCOLOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLegendColorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LEGENDCOLOR).getAttributeMdDTO();
  }
  
  public String getLegendFontFamily()
  {
    return getValue(LEGENDFONTFAMILY);
  }
  
  public void setLegendFontFamily(String value)
  {
    if(value == null)
    {
      setValue(LEGENDFONTFAMILY, "");
    }
    else
    {
      setValue(LEGENDFONTFAMILY, value);
    }
  }
  
  public boolean isLegendFontFamilyWritable()
  {
    return isWritable(LEGENDFONTFAMILY);
  }
  
  public boolean isLegendFontFamilyReadable()
  {
    return isReadable(LEGENDFONTFAMILY);
  }
  
  public boolean isLegendFontFamilyModified()
  {
    return isModified(LEGENDFONTFAMILY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendFontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDFONTFAMILY).getAttributeMdDTO();
  }
  
  public String getLegendFontFill()
  {
    return getValue(LEGENDFONTFILL);
  }
  
  public void setLegendFontFill(String value)
  {
    if(value == null)
    {
      setValue(LEGENDFONTFILL, "");
    }
    else
    {
      setValue(LEGENDFONTFILL, value);
    }
  }
  
  public boolean isLegendFontFillWritable()
  {
    return isWritable(LEGENDFONTFILL);
  }
  
  public boolean isLegendFontFillReadable()
  {
    return isReadable(LEGENDFONTFILL);
  }
  
  public boolean isLegendFontFillModified()
  {
    return isModified(LEGENDFONTFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendFontFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDFONTFILL).getAttributeMdDTO();
  }
  
  public Integer getLegendFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDFONTSIZE));
  }
  
  public void setLegendFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDFONTSIZE, "");
    }
    else
    {
      setValue(LEGENDFONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLegendFontSizeWritable()
  {
    return isWritable(LEGENDFONTSIZE);
  }
  
  public boolean isLegendFontSizeReadable()
  {
    return isReadable(LEGENDFONTSIZE);
  }
  
  public boolean isLegendFontSizeModified()
  {
    return isModified(LEGENDFONTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLegendFontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LEGENDFONTSIZE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getLegendFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(LEGENDFONTSTYLES));
  }
  
  public java.util.List<String> getLegendFontStylesEnumNames()
  {
    return getEnumNames(LEGENDFONTSTYLES);
  }
  
  public void addLegendFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    addEnumItem(LEGENDFONTSTYLES, enumDTO.toString());
  }
  
  public void removeLegendFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    removeEnumItem(LEGENDFONTSTYLES, enumDTO.toString());
  }
  
  public void clearLegendFontStyles()
  {
    clearEnum(LEGENDFONTSTYLES);
  }
  
  public boolean isLegendFontStylesWritable()
  {
    return isWritable(LEGENDFONTSTYLES);
  }
  
  public boolean isLegendFontStylesReadable()
  {
    return isReadable(LEGENDFONTSTYLES);
  }
  
  public boolean isLegendFontStylesModified()
  {
    return isModified(LEGENDFONTSTYLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLegendFontStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LEGENDFONTSTYLES).getAttributeMdDTO();
  }
  
  public String getLegendId()
  {
    return getValue(LEGENDID);
  }
  
  public void setLegendId(String value)
  {
    if(value == null)
    {
      setValue(LEGENDID, "");
    }
    else
    {
      setValue(LEGENDID, value);
    }
  }
  
  public boolean isLegendIdWritable()
  {
    return isWritable(LEGENDID);
  }
  
  public boolean isLegendIdReadable()
  {
    return isReadable(LEGENDID);
  }
  
  public boolean isLegendIdModified()
  {
    return isModified(LEGENDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDID).getAttributeMdDTO();
  }
  
  public String getLegendTitle()
  {
    return getValue(LEGENDTITLE);
  }
  
  public void setLegendTitle(String value)
  {
    if(value == null)
    {
      setValue(LEGENDTITLE, "");
    }
    else
    {
      setValue(LEGENDTITLE, value);
    }
  }
  
  public boolean isLegendTitleWritable()
  {
    return isWritable(LEGENDTITLE);
  }
  
  public boolean isLegendTitleReadable()
  {
    return isReadable(LEGENDTITLE);
  }
  
  public boolean isLegendTitleModified()
  {
    return isModified(LEGENDTITLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendTitleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDTITLE).getAttributeMdDTO();
  }
  
  public String getLegendTitleFontFamily()
  {
    return getValue(LEGENDTITLEFONTFAMILY);
  }
  
  public void setLegendTitleFontFamily(String value)
  {
    if(value == null)
    {
      setValue(LEGENDTITLEFONTFAMILY, "");
    }
    else
    {
      setValue(LEGENDTITLEFONTFAMILY, value);
    }
  }
  
  public boolean isLegendTitleFontFamilyWritable()
  {
    return isWritable(LEGENDTITLEFONTFAMILY);
  }
  
  public boolean isLegendTitleFontFamilyReadable()
  {
    return isReadable(LEGENDTITLEFONTFAMILY);
  }
  
  public boolean isLegendTitleFontFamilyModified()
  {
    return isModified(LEGENDTITLEFONTFAMILY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendTitleFontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDTITLEFONTFAMILY).getAttributeMdDTO();
  }
  
  public String getLegendTitleFontFill()
  {
    return getValue(LEGENDTITLEFONTFILL);
  }
  
  public void setLegendTitleFontFill(String value)
  {
    if(value == null)
    {
      setValue(LEGENDTITLEFONTFILL, "");
    }
    else
    {
      setValue(LEGENDTITLEFONTFILL, value);
    }
  }
  
  public boolean isLegendTitleFontFillWritable()
  {
    return isWritable(LEGENDTITLEFONTFILL);
  }
  
  public boolean isLegendTitleFontFillReadable()
  {
    return isReadable(LEGENDTITLEFONTFILL);
  }
  
  public boolean isLegendTitleFontFillModified()
  {
    return isModified(LEGENDTITLEFONTFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLegendTitleFontFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEGENDTITLEFONTFILL).getAttributeMdDTO();
  }
  
  public Integer getLegendTitleFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDTITLEFONTSIZE));
  }
  
  public void setLegendTitleFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDTITLEFONTSIZE, "");
    }
    else
    {
      setValue(LEGENDTITLEFONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLegendTitleFontSizeWritable()
  {
    return isWritable(LEGENDTITLEFONTSIZE);
  }
  
  public boolean isLegendTitleFontSizeReadable()
  {
    return isReadable(LEGENDTITLEFONTSIZE);
  }
  
  public boolean isLegendTitleFontSizeModified()
  {
    return isModified(LEGENDTITLEFONTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLegendTitleFontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LEGENDTITLEFONTSIZE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getLegendTitleFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(LEGENDTITLEFONTSTYLES));
  }
  
  public java.util.List<String> getLegendTitleFontStylesEnumNames()
  {
    return getEnumNames(LEGENDTITLEFONTSTYLES);
  }
  
  public void addLegendTitleFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    addEnumItem(LEGENDTITLEFONTSTYLES, enumDTO.toString());
  }
  
  public void removeLegendTitleFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    removeEnumItem(LEGENDTITLEFONTSTYLES, enumDTO.toString());
  }
  
  public void clearLegendTitleFontStyles()
  {
    clearEnum(LEGENDTITLEFONTSTYLES);
  }
  
  public boolean isLegendTitleFontStylesWritable()
  {
    return isWritable(LEGENDTITLEFONTSTYLES);
  }
  
  public boolean isLegendTitleFontStylesReadable()
  {
    return isReadable(LEGENDTITLEFONTSTYLES);
  }
  
  public boolean isLegendTitleFontStylesModified()
  {
    return isModified(LEGENDTITLEFONTSTYLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLegendTitleFontStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LEGENDTITLEFONTSTYLES).getAttributeMdDTO();
  }
  
  public Integer getLegendXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDXPOSITION));
  }
  
  public void setLegendXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDXPOSITION, "");
    }
    else
    {
      setValue(LEGENDXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLegendXPositionWritable()
  {
    return isWritable(LEGENDXPOSITION);
  }
  
  public boolean isLegendXPositionReadable()
  {
    return isReadable(LEGENDXPOSITION);
  }
  
  public boolean isLegendXPositionModified()
  {
    return isModified(LEGENDXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLegendXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LEGENDXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getLegendYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDYPOSITION));
  }
  
  public void setLegendYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDYPOSITION, "");
    }
    else
    {
      setValue(LEGENDYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLegendYPositionWritable()
  {
    return isWritable(LEGENDYPOSITION);
  }
  
  public boolean isLegendYPositionReadable()
  {
    return isReadable(LEGENDYPOSITION);
  }
  
  public boolean isLegendYPositionModified()
  {
    return isModified(LEGENDYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLegendYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LEGENDYPOSITION).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.metadata.MdAttributeDTO getMdAttribute()
  {
    if(getValue(MDATTRIBUTE) == null || getValue(MDATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(MDATTRIBUTE));
    }
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTE);
  }
  
  public void setMdAttribute(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTE, "");
    }
    else
    {
      setValue(MDATTRIBUTE, value.getId());
    }
  }
  
  public boolean isMdAttributeWritable()
  {
    return isWritable(MDATTRIBUTE);
  }
  
  public boolean isMdAttributeReadable()
  {
    return isReadable(MDATTRIBUTE);
  }
  
  public boolean isMdAttributeModified()
  {
    return isModified(MDATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMdAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MDATTRIBUTE).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpacity()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPACITY));
  }
  
  public void setOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPACITY, "");
    }
    else
    {
      setValue(OPACITY, value.toString());
    }
  }
  
  public boolean isOpacityWritable()
  {
    return isWritable(OPACITY);
  }
  
  public boolean isOpacityReadable()
  {
    return isReadable(OPACITY);
  }
  
  public boolean isOpacityModified()
  {
    return isModified(OPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPACITY).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.AllRenderTypesDTO> getRenderAs()
  {
    return (java.util.List<dss.vector.solutions.query.AllRenderTypesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.AllRenderTypesDTO.CLASS, getEnumNames(RENDERAS));
  }
  
  public java.util.List<String> getRenderAsEnumNames()
  {
    return getEnumNames(RENDERAS);
  }
  
  public void addRenderAs(dss.vector.solutions.query.AllRenderTypesDTO enumDTO)
  {
    addEnumItem(RENDERAS, enumDTO.toString());
  }
  
  public void removeRenderAs(dss.vector.solutions.query.AllRenderTypesDTO enumDTO)
  {
    removeEnumItem(RENDERAS, enumDTO.toString());
  }
  
  public void clearRenderAs()
  {
    clearEnum(RENDERAS);
  }
  
  public boolean isRenderAsWritable()
  {
    return isWritable(RENDERAS);
  }
  
  public boolean isRenderAsReadable()
  {
    return isReadable(RENDERAS);
  }
  
  public boolean isRenderAsModified()
  {
    return isModified(RENDERAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getRenderAsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(RENDERAS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.SavedSearchDTO getSavedSearch()
  {
    if(getValue(SAVEDSEARCH) == null || getValue(SAVEDSEARCH).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedSearchDTO.get(getRequest(), getValue(SAVEDSEARCH));
    }
  }
  
  public String getSavedSearchId()
  {
    return getValue(SAVEDSEARCH);
  }
  
  public void setSavedSearch(dss.vector.solutions.query.SavedSearchDTO value)
  {
    if(value == null)
    {
      setValue(SAVEDSEARCH, "");
    }
    else
    {
      setValue(SAVEDSEARCH, value.getId());
    }
  }
  
  public boolean isSavedSearchWritable()
  {
    return isWritable(SAVEDSEARCH);
  }
  
  public boolean isSavedSearchReadable()
  {
    return isReadable(SAVEDSEARCH);
  }
  
  public boolean isSavedSearchModified()
  {
    return isModified(SAVEDSEARCH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSavedSearchMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SAVEDSEARCH).getAttributeMdDTO();
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
  
  public Boolean getShowLegendBorder()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SHOWLEGENDBORDER));
  }
  
  public void setShowLegendBorder(Boolean value)
  {
    if(value == null)
    {
      setValue(SHOWLEGENDBORDER, "");
    }
    else
    {
      setValue(SHOWLEGENDBORDER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isShowLegendBorderWritable()
  {
    return isWritable(SHOWLEGENDBORDER);
  }
  
  public boolean isShowLegendBorderReadable()
  {
    return isReadable(SHOWLEGENDBORDER);
  }
  
  public boolean isShowLegendBorderModified()
  {
    return isModified(SHOWLEGENDBORDER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getShowLegendBorderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SHOWLEGENDBORDER).getAttributeMdDTO();
  }
  
  public Boolean getShowThematicValue()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SHOWTHEMATICVALUE));
  }
  
  public void setShowThematicValue(Boolean value)
  {
    if(value == null)
    {
      setValue(SHOWTHEMATICVALUE, "");
    }
    else
    {
      setValue(SHOWTHEMATICVALUE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isShowThematicValueWritable()
  {
    return isWritable(SHOWTHEMATICVALUE);
  }
  
  public boolean isShowThematicValueReadable()
  {
    return isReadable(SHOWTHEMATICVALUE);
  }
  
  public boolean isShowThematicValueModified()
  {
    return isModified(SHOWTHEMATICVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getShowThematicValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SHOWTHEMATICVALUE).getAttributeMdDTO();
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
  
  public String getSldFile()
  {
    return getValue(SLDFILE);
  }
  
  public void setSldFile(String value)
  {
    if(value == null)
    {
      setValue(SLDFILE, "");
    }
    else
    {
      setValue(SLDFILE, value);
    }
  }
  
  public boolean isSldFileWritable()
  {
    return isWritable(SLDFILE);
  }
  
  public boolean isSldFileReadable()
  {
    return isReadable(SLDFILE);
  }
  
  public boolean isSldFileModified()
  {
    return isModified(SLDFILE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSldFileMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SLDFILE).getAttributeMdDTO();
  }
  
  public String getThematicColumnAlias()
  {
    return getValue(THEMATICCOLUMNALIAS);
  }
  
  public void setThematicColumnAlias(String value)
  {
    if(value == null)
    {
      setValue(THEMATICCOLUMNALIAS, "");
    }
    else
    {
      setValue(THEMATICCOLUMNALIAS, value);
    }
  }
  
  public boolean isThematicColumnAliasWritable()
  {
    return isWritable(THEMATICCOLUMNALIAS);
  }
  
  public boolean isThematicColumnAliasReadable()
  {
    return isReadable(THEMATICCOLUMNALIAS);
  }
  
  public boolean isThematicColumnAliasModified()
  {
    return isModified(THEMATICCOLUMNALIAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getThematicColumnAliasMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THEMATICCOLUMNALIAS).getAttributeMdDTO();
  }
  
  public String getThematicUserAlias()
  {
    return getValue(THEMATICUSERALIAS);
  }
  
  public void setThematicUserAlias(String value)
  {
    if(value == null)
    {
      setValue(THEMATICUSERALIAS, "");
    }
    else
    {
      setValue(THEMATICUSERALIAS, value);
    }
  }
  
  public boolean isThematicUserAliasWritable()
  {
    return isWritable(THEMATICUSERALIAS);
  }
  
  public boolean isThematicUserAliasReadable()
  {
    return isReadable(THEMATICUSERALIAS);
  }
  
  public boolean isThematicUserAliasModified()
  {
    return isModified(THEMATICUSERALIAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getThematicUserAliasMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THEMATICUSERALIAS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.ThematicVariableDTO getThematicVariable()
  {
    if(getValue(THEMATICVARIABLE) == null || getValue(THEMATICVARIABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.ThematicVariableDTO.get(getRequest(), getValue(THEMATICVARIABLE));
    }
  }
  
  public String getThematicVariableId()
  {
    return getValue(THEMATICVARIABLE);
  }
  
  public void setThematicVariable(dss.vector.solutions.query.ThematicVariableDTO value)
  {
    if(value == null)
    {
      setValue(THEMATICVARIABLE, "");
    }
    else
    {
      setValue(THEMATICVARIABLE, value.getId());
    }
  }
  
  public boolean isThematicVariableWritable()
  {
    return isWritable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableReadable()
  {
    return isReadable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableModified()
  {
    return isModified(THEMATICVARIABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getThematicVariableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(THEMATICVARIABLE).getAttributeMdDTO();
  }
  
  public Boolean getViewCreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIEWCREATED));
  }
  
  public void setViewCreated(Boolean value)
  {
    if(value == null)
    {
      setValue(VIEWCREATED, "");
    }
    else
    {
      setValue(VIEWCREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isViewCreatedWritable()
  {
    return isWritable(VIEWCREATED);
  }
  
  public boolean isViewCreatedReadable()
  {
    return isReadable(VIEWCREATED);
  }
  
  public boolean isViewCreatedModified()
  {
    return isModified(VIEWCREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getViewCreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VIEWCREATED).getAttributeMdDTO();
  }
  
  public String getViewName()
  {
    return getValue(VIEWNAME);
  }
  
  public void setViewName(String value)
  {
    if(value == null)
    {
      setValue(VIEWNAME, "");
    }
    else
    {
      setValue(VIEWNAME, value);
    }
  }
  
  public boolean isViewNameWritable()
  {
    return isWritable(VIEWNAME);
  }
  
  public boolean isViewNameReadable()
  {
    return isReadable(VIEWNAME);
  }
  
  public boolean isViewNameModified()
  {
    return isModified(VIEWNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getViewNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VIEWNAME).getAttributeMdDTO();
  }
  
  public final void applyWithStyles(dss.vector.solutions.query.StylesDTO styles, java.lang.String savedMapId)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.Styles", "java.lang.String"};
    Object[] _parameters = new Object[]{styles, savedMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "applyWithStyles", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyWithStyles(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.query.StylesDTO styles, java.lang.String savedMapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.query.Styles", "java.lang.String"};
    Object[] _parameters = new Object[]{id, styles, savedMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "applyWithStyles", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.QueryInfoDTO calculateQueryInfo()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "calculateQueryInfo", _declaredTypes);
    return (dss.vector.solutions.query.QueryInfoDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.QueryInfoDTO calculateQueryInfo(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "calculateQueryInfo", _declaredTypes);
    return (dss.vector.solutions.query.QueryInfoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.AbstractCategoryDTO[] generateCategories(dss.vector.solutions.query.CategoryGenDTO categoryGen, dss.vector.solutions.query.LayerDTO currentLayer)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.CategoryGen", "dss.vector.solutions.query.Layer"};
    Object[] _parameters = new Object[]{categoryGen, currentLayer};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "generateCategories", _declaredTypes);
    return (dss.vector.solutions.query.AbstractCategoryDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.AbstractCategoryDTO[] generateCategories(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.query.CategoryGenDTO categoryGen, dss.vector.solutions.query.LayerDTO currentLayer)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.query.CategoryGen", "dss.vector.solutions.query.Layer"};
    Object[] _parameters = new Object[]{id, categoryGen, currentLayer};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "generateCategories", _declaredTypes);
    return (dss.vector.solutions.query.AbstractCategoryDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.AttributeGeoHierarchyDTO[] getAttributeGeoHierarchies()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "getAttributeGeoHierarchies", _declaredTypes);
    return (dss.vector.solutions.query.AttributeGeoHierarchyDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.AttributeGeoHierarchyDTO[] getAttributeGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "getAttributeGeoHierarchies", _declaredTypes);
    return (dss.vector.solutions.query.AttributeGeoHierarchyDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateSLDFile(java.lang.String fileId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{fileId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "updateSLDFile", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateSLDFile(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String fileId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, fileId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "updateSLDFile", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllHasCategory()
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllHasCategory(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO> getAllHasCategoryRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO> getAllHasCategoryRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasCategoriesDTO addHasCategory(dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.HasCategoriesDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasCategoriesDTO addHasCategory(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.HasCategoriesDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public void removeHasCategory(dss.vector.solutions.query.HasCategoriesDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasCategory(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasCategoriesDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasCategory()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public static void removeAllHasCategory(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllMap()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllMapRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllMapRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasLayersDTO addMap(dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasLayersDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasLayersDTO addMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasLayersDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public void removeMap(dss.vector.solutions.query.HasLayersDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasLayersDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllMap()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static void removeAllMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.LayerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.LayerDTO) dto;
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
  
  public static dss.vector.solutions.query.LayerQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.LayerQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.LayerDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.LayerDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.LayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.LayerDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.LayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
