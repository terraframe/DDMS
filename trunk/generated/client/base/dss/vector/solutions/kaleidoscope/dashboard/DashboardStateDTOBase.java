package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = 1396709221)
public abstract class DashboardStateDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardState";
  private static final long serialVersionUID = 1396709221;
  
  protected DashboardStateDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardStateDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ARROWXPOSITION = "arrowXPosition";
  public static java.lang.String ARROWYPOSITION = "arrowYPosition";
  public static java.lang.String CONDITIONS = "conditions";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DASHBOARD = "dashboard";
  public static java.lang.String ENABLEARROW = "enableArrow";
  public static java.lang.String ENABLESCALE = "enableScale";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOPRISMUSER = "geoprismUser";
  public static java.lang.String ID = "id";
  public static java.lang.String ISEXPANDLEFT = "isExpandLeft";
  public static java.lang.String ISEXPANDRIGHT = "isExpandRight";
  public static java.lang.String ISREPORTOPAQUE = "isReportOpaque";
  public static java.lang.String ISREPORTVERTICAL = "isReportVertical";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPEXTENT = "mapExtent";
  public static java.lang.String MAPTHUMBNAIL = "mapThumbnail";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String REPORTSIZESTATE = "reportSizeState";
  public static java.lang.String REPORTXPOSITION = "reportXPosition";
  public static java.lang.String REPORTYPOSITION = "reportYPosition";
  public static java.lang.String SAVEDHEIGHT = "savedHeight";
  public static java.lang.String SAVEDWIDTH = "savedWidth";
  public static java.lang.String SCALEXPOSITION = "scaleXPosition";
  public static java.lang.String SCALEYPOSITION = "scaleYPosition";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public Integer getArrowXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ARROWXPOSITION));
  }
  
  public void setArrowXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(ARROWXPOSITION, "");
    }
    else
    {
      setValue(ARROWXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isArrowXPositionWritable()
  {
    return isWritable(ARROWXPOSITION);
  }
  
  public boolean isArrowXPositionReadable()
  {
    return isReadable(ARROWXPOSITION);
  }
  
  public boolean isArrowXPositionModified()
  {
    return isModified(ARROWXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getArrowXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ARROWXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getArrowYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ARROWYPOSITION));
  }
  
  public void setArrowYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(ARROWYPOSITION, "");
    }
    else
    {
      setValue(ARROWYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isArrowYPositionWritable()
  {
    return isWritable(ARROWYPOSITION);
  }
  
  public boolean isArrowYPositionReadable()
  {
    return isReadable(ARROWYPOSITION);
  }
  
  public boolean isArrowYPositionModified()
  {
    return isModified(ARROWYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getArrowYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ARROWYPOSITION).getAttributeMdDTO();
  }
  
  public String getConditions()
  {
    return getValue(CONDITIONS);
  }
  
  public void setConditions(String value)
  {
    if(value == null)
    {
      setValue(CONDITIONS, "");
    }
    else
    {
      setValue(CONDITIONS, value);
    }
  }
  
  public boolean isConditionsWritable()
  {
    return isWritable(CONDITIONS);
  }
  
  public boolean isConditionsReadable()
  {
    return isReadable(CONDITIONS);
  }
  
  public boolean isConditionsModified()
  {
    return isModified(CONDITIONS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getConditionsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CONDITIONS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO getDashboard()
  {
    if(getValue(DASHBOARD) == null || getValue(DASHBOARD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.get(getRequest(), getValue(DASHBOARD));
    }
  }
  
  public String getDashboardId()
  {
    return getValue(DASHBOARD);
  }
  
  public void setDashboard(dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO value)
  {
    if(value == null)
    {
      setValue(DASHBOARD, "");
    }
    else
    {
      setValue(DASHBOARD, value.getId());
    }
  }
  
  public boolean isDashboardWritable()
  {
    return isWritable(DASHBOARD);
  }
  
  public boolean isDashboardReadable()
  {
    return isReadable(DASHBOARD);
  }
  
  public boolean isDashboardModified()
  {
    return isModified(DASHBOARD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDashboardMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DASHBOARD).getAttributeMdDTO();
  }
  
  public Boolean getEnableArrow()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLEARROW));
  }
  
  public void setEnableArrow(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLEARROW, "");
    }
    else
    {
      setValue(ENABLEARROW, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnableArrowWritable()
  {
    return isWritable(ENABLEARROW);
  }
  
  public boolean isEnableArrowReadable()
  {
    return isReadable(ENABLEARROW);
  }
  
  public boolean isEnableArrowModified()
  {
    return isModified(ENABLEARROW);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnableArrowMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLEARROW).getAttributeMdDTO();
  }
  
  public Boolean getEnableScale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLESCALE));
  }
  
  public void setEnableScale(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLESCALE, "");
    }
    else
    {
      setValue(ENABLESCALE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnableScaleWritable()
  {
    return isWritable(ENABLESCALE);
  }
  
  public boolean isEnableScaleReadable()
  {
    return isReadable(ENABLESCALE);
  }
  
  public boolean isEnableScaleModified()
  {
    return isModified(ENABLESCALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnableScaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLESCALE).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.SingleActorDTO getGeoprismUser()
  {
    if(getValue(GEOPRISMUSER) == null || getValue(GEOPRISMUSER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(GEOPRISMUSER));
    }
  }
  
  public String getGeoprismUserId()
  {
    return getValue(GEOPRISMUSER);
  }
  
  public void setGeoprismUser(com.runwaysdk.system.SingleActorDTO value)
  {
    if(value == null)
    {
      setValue(GEOPRISMUSER, "");
    }
    else
    {
      setValue(GEOPRISMUSER, value.getId());
    }
  }
  
  public boolean isGeoprismUserWritable()
  {
    return isWritable(GEOPRISMUSER);
  }
  
  public boolean isGeoprismUserReadable()
  {
    return isReadable(GEOPRISMUSER);
  }
  
  public boolean isGeoprismUserModified()
  {
    return isModified(GEOPRISMUSER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoprismUserMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOPRISMUSER).getAttributeMdDTO();
  }
  
  public Boolean getIsExpandLeft()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEXPANDLEFT));
  }
  
  public void setIsExpandLeft(Boolean value)
  {
    if(value == null)
    {
      setValue(ISEXPANDLEFT, "");
    }
    else
    {
      setValue(ISEXPANDLEFT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsExpandLeftWritable()
  {
    return isWritable(ISEXPANDLEFT);
  }
  
  public boolean isIsExpandLeftReadable()
  {
    return isReadable(ISEXPANDLEFT);
  }
  
  public boolean isIsExpandLeftModified()
  {
    return isModified(ISEXPANDLEFT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsExpandLeftMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISEXPANDLEFT).getAttributeMdDTO();
  }
  
  public Boolean getIsExpandRight()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEXPANDRIGHT));
  }
  
  public void setIsExpandRight(Boolean value)
  {
    if(value == null)
    {
      setValue(ISEXPANDRIGHT, "");
    }
    else
    {
      setValue(ISEXPANDRIGHT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsExpandRightWritable()
  {
    return isWritable(ISEXPANDRIGHT);
  }
  
  public boolean isIsExpandRightReadable()
  {
    return isReadable(ISEXPANDRIGHT);
  }
  
  public boolean isIsExpandRightModified()
  {
    return isModified(ISEXPANDRIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsExpandRightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISEXPANDRIGHT).getAttributeMdDTO();
  }
  
  public Boolean getIsReportOpaque()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREPORTOPAQUE));
  }
  
  public void setIsReportOpaque(Boolean value)
  {
    if(value == null)
    {
      setValue(ISREPORTOPAQUE, "");
    }
    else
    {
      setValue(ISREPORTOPAQUE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsReportOpaqueWritable()
  {
    return isWritable(ISREPORTOPAQUE);
  }
  
  public boolean isIsReportOpaqueReadable()
  {
    return isReadable(ISREPORTOPAQUE);
  }
  
  public boolean isIsReportOpaqueModified()
  {
    return isModified(ISREPORTOPAQUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsReportOpaqueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISREPORTOPAQUE).getAttributeMdDTO();
  }
  
  public Boolean getIsReportVertical()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREPORTVERTICAL));
  }
  
  public void setIsReportVertical(Boolean value)
  {
    if(value == null)
    {
      setValue(ISREPORTVERTICAL, "");
    }
    else
    {
      setValue(ISREPORTVERTICAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsReportVerticalWritable()
  {
    return isWritable(ISREPORTVERTICAL);
  }
  
  public boolean isIsReportVerticalReadable()
  {
    return isReadable(ISREPORTVERTICAL);
  }
  
  public boolean isIsReportVerticalModified()
  {
    return isModified(ISREPORTVERTICAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsReportVerticalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISREPORTVERTICAL).getAttributeMdDTO();
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
  
  public String getMapExtent()
  {
    return getValue(MAPEXTENT);
  }
  
  public void setMapExtent(String value)
  {
    if(value == null)
    {
      setValue(MAPEXTENT, "");
    }
    else
    {
      setValue(MAPEXTENT, value);
    }
  }
  
  public boolean isMapExtentWritable()
  {
    return isWritable(MAPEXTENT);
  }
  
  public boolean isMapExtentReadable()
  {
    return isReadable(MAPEXTENT);
  }
  
  public boolean isMapExtentModified()
  {
    return isModified(MAPEXTENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMapExtentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MAPEXTENT).getAttributeMdDTO();
  }
  
  public byte[] getMapThumbnail()
  {
    return super.getBlob(MAPTHUMBNAIL);
  }
  
  public void setMapThumbnail(byte[] bytes)
  {
    super.setBlob(MAPTHUMBNAIL, bytes);
  }
  
  public boolean isMapThumbnailWritable()
  {
    return isWritable(MAPTHUMBNAIL);
  }
  
  public boolean isMapThumbnailReadable()
  {
    return isReadable(MAPTHUMBNAIL);
  }
  
  public boolean isMapThumbnailModified()
  {
    return isModified(MAPTHUMBNAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBlobMdDTO getMapThumbnailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBlobMdDTO) getAttributeDTO(MAPTHUMBNAIL).getAttributeMdDTO();
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
  
  public String getReportSizeState()
  {
    return getValue(REPORTSIZESTATE);
  }
  
  public void setReportSizeState(String value)
  {
    if(value == null)
    {
      setValue(REPORTSIZESTATE, "");
    }
    else
    {
      setValue(REPORTSIZESTATE, value);
    }
  }
  
  public boolean isReportSizeStateWritable()
  {
    return isWritable(REPORTSIZESTATE);
  }
  
  public boolean isReportSizeStateReadable()
  {
    return isReadable(REPORTSIZESTATE);
  }
  
  public boolean isReportSizeStateModified()
  {
    return isModified(REPORTSIZESTATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getReportSizeStateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(REPORTSIZESTATE).getAttributeMdDTO();
  }
  
  public Integer getReportXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REPORTXPOSITION));
  }
  
  public void setReportXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(REPORTXPOSITION, "");
    }
    else
    {
      setValue(REPORTXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReportXPositionWritable()
  {
    return isWritable(REPORTXPOSITION);
  }
  
  public boolean isReportXPositionReadable()
  {
    return isReadable(REPORTXPOSITION);
  }
  
  public boolean isReportXPositionModified()
  {
    return isModified(REPORTXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReportXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REPORTXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getReportYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REPORTYPOSITION));
  }
  
  public void setReportYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(REPORTYPOSITION, "");
    }
    else
    {
      setValue(REPORTYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReportYPositionWritable()
  {
    return isWritable(REPORTYPOSITION);
  }
  
  public boolean isReportYPositionReadable()
  {
    return isReadable(REPORTYPOSITION);
  }
  
  public boolean isReportYPositionModified()
  {
    return isModified(REPORTYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReportYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REPORTYPOSITION).getAttributeMdDTO();
  }
  
  public Integer getSavedHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SAVEDHEIGHT));
  }
  
  public void setSavedHeight(Integer value)
  {
    if(value == null)
    {
      setValue(SAVEDHEIGHT, "");
    }
    else
    {
      setValue(SAVEDHEIGHT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSavedHeightWritable()
  {
    return isWritable(SAVEDHEIGHT);
  }
  
  public boolean isSavedHeightReadable()
  {
    return isReadable(SAVEDHEIGHT);
  }
  
  public boolean isSavedHeightModified()
  {
    return isModified(SAVEDHEIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSavedHeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SAVEDHEIGHT).getAttributeMdDTO();
  }
  
  public Integer getSavedWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SAVEDWIDTH));
  }
  
  public void setSavedWidth(Integer value)
  {
    if(value == null)
    {
      setValue(SAVEDWIDTH, "");
    }
    else
    {
      setValue(SAVEDWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSavedWidthWritable()
  {
    return isWritable(SAVEDWIDTH);
  }
  
  public boolean isSavedWidthReadable()
  {
    return isReadable(SAVEDWIDTH);
  }
  
  public boolean isSavedWidthModified()
  {
    return isModified(SAVEDWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSavedWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SAVEDWIDTH).getAttributeMdDTO();
  }
  
  public Integer getScaleXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEXPOSITION));
  }
  
  public void setScaleXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(SCALEXPOSITION, "");
    }
    else
    {
      setValue(SCALEXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isScaleXPositionWritable()
  {
    return isWritable(SCALEXPOSITION);
  }
  
  public boolean isScaleXPositionReadable()
  {
    return isReadable(SCALEXPOSITION);
  }
  
  public boolean isScaleXPositionModified()
  {
    return isModified(SCALEXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getScaleXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SCALEXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getScaleYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEYPOSITION));
  }
  
  public void setScaleYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(SCALEYPOSITION, "");
    }
    else
    {
      setValue(SCALEYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isScaleYPositionWritable()
  {
    return isWritable(SCALEYPOSITION);
  }
  
  public boolean isScaleYPositionReadable()
  {
    return isReadable(SCALEYPOSITION);
  }
  
  public boolean isScaleYPositionModified()
  {
    return isModified(SCALEYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getScaleYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SCALEYPOSITION).getAttributeMdDTO();
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardStateQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardStateQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardStateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
