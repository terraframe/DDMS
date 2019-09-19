package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = -777832987)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardState.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardStateBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardState";
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
  private static final long serialVersionUID = -777832987;
  
  public DashboardStateBase()
  {
    super();
  }
  
  public Integer getArrowXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ARROWXPOSITION));
  }
  
  public void validateArrowXPosition()
  {
    this.validateAttribute(ARROWXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getArrowXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(ARROWXPOSITION);
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
  
  public Integer getArrowYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ARROWYPOSITION));
  }
  
  public void validateArrowYPosition()
  {
    this.validateAttribute(ARROWYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getArrowYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(ARROWYPOSITION);
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
  
  public String getConditions()
  {
    return getValue(CONDITIONS);
  }
  
  public void validateConditions()
  {
    this.validateAttribute(CONDITIONS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getConditionsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(CONDITIONS);
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
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.Dashboard getDashboard()
  {
    if (getValue(DASHBOARD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.Dashboard.get(getValue(DASHBOARD));
    }
  }
  
  public String getDashboardId()
  {
    return getValue(DASHBOARD);
  }
  
  public void validateDashboard()
  {
    this.validateAttribute(DASHBOARD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDashboardMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DASHBOARD);
  }
  
  public void setDashboard(dss.vector.solutions.kaleidoscope.dashboard.Dashboard value)
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
  
  public void setDashboardId(java.lang.String id)
  {
    if(id == null)
    {
      setValue(DASHBOARD, "");
    }
    else
    {
      setValue(DASHBOARD, id);
    }
  }
  
  public Boolean getEnableArrow()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLEARROW));
  }
  
  public void validateEnableArrow()
  {
    this.validateAttribute(ENABLEARROW);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getEnableArrowMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ENABLEARROW);
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
  
  public Boolean getEnableScale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLESCALE));
  }
  
  public void validateEnableScale()
  {
    this.validateAttribute(ENABLESCALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getEnableScaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ENABLESCALE);
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
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
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
  
  public void setEntityDomainId(java.lang.String id)
  {
    if(id == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, id);
    }
  }
  
  public com.runwaysdk.system.SingleActor getGeoprismUser()
  {
    if (getValue(GEOPRISMUSER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(GEOPRISMUSER));
    }
  }
  
  public String getGeoprismUserId()
  {
    return getValue(GEOPRISMUSER);
  }
  
  public void validateGeoprismUser()
  {
    this.validateAttribute(GEOPRISMUSER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeoprismUserMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOPRISMUSER);
  }
  
  public void setGeoprismUser(com.runwaysdk.system.SingleActor value)
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
  
  public void setGeoprismUserId(java.lang.String id)
  {
    if(id == null)
    {
      setValue(GEOPRISMUSER, "");
    }
    else
    {
      setValue(GEOPRISMUSER, id);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getIsExpandLeft()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEXPANDLEFT));
  }
  
  public void validateIsExpandLeft()
  {
    this.validateAttribute(ISEXPANDLEFT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getIsExpandLeftMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ISEXPANDLEFT);
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
  
  public Boolean getIsExpandRight()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEXPANDRIGHT));
  }
  
  public void validateIsExpandRight()
  {
    this.validateAttribute(ISEXPANDRIGHT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getIsExpandRightMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ISEXPANDRIGHT);
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
  
  public Boolean getIsReportOpaque()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREPORTOPAQUE));
  }
  
  public void validateIsReportOpaque()
  {
    this.validateAttribute(ISREPORTOPAQUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getIsReportOpaqueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ISREPORTOPAQUE);
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
  
  public Boolean getIsReportVertical()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREPORTVERTICAL));
  }
  
  public void validateIsReportVertical()
  {
    this.validateAttribute(ISREPORTVERTICAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getIsReportVerticalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ISREPORTVERTICAL);
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
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
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
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.SingleActor getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public String getMapExtent()
  {
    return getValue(MAPEXTENT);
  }
  
  public void validateMapExtent()
  {
    this.validateAttribute(MAPEXTENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getMapExtentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(MAPEXTENT);
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
  
  public byte[] getMapThumbnail()
  {
    return getBlob(MAPTHUMBNAIL);
  }
  
  public void validateMapThumbnail()
  {
    this.validateAttribute(MAPTHUMBNAIL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBlobDAOIF getMapThumbnailMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBlobDAOIF)mdClassIF.definesAttribute(MAPTHUMBNAIL);
  }
  
  public void setMapThumbnail(byte[] value)
  {
    if(value == null)
    {
      setValue(MAPTHUMBNAIL, "");
    }
    else
    {
      setBlob(MAPTHUMBNAIL, value);
    }
  }
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
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
  
  public void setOwnerId(java.lang.String id)
  {
    if(id == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, id);
    }
  }
  
  public String getReportSizeState()
  {
    return getValue(REPORTSIZESTATE);
  }
  
  public void validateReportSizeState()
  {
    this.validateAttribute(REPORTSIZESTATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getReportSizeStateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(REPORTSIZESTATE);
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
  
  public Integer getReportXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REPORTXPOSITION));
  }
  
  public void validateReportXPosition()
  {
    this.validateAttribute(REPORTXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getReportXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(REPORTXPOSITION);
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
  
  public Integer getReportYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REPORTYPOSITION));
  }
  
  public void validateReportYPosition()
  {
    this.validateAttribute(REPORTYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getReportYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(REPORTYPOSITION);
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
  
  public Integer getSavedHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SAVEDHEIGHT));
  }
  
  public void validateSavedHeight()
  {
    this.validateAttribute(SAVEDHEIGHT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getSavedHeightMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(SAVEDHEIGHT);
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
  
  public Integer getSavedWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SAVEDWIDTH));
  }
  
  public void validateSavedWidth()
  {
    this.validateAttribute(SAVEDWIDTH);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getSavedWidthMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(SAVEDWIDTH);
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
  
  public Integer getScaleXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEXPOSITION));
  }
  
  public void validateScaleXPosition()
  {
    this.validateAttribute(SCALEXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getScaleXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(SCALEXPOSITION);
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
  
  public Integer getScaleYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEYPOSITION));
  }
  
  public void validateScaleYPosition()
  {
    this.validateAttribute(SCALEYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getScaleYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(SCALEYPOSITION);
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
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardState.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardStateQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardStateQuery query = new DashboardStateQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DashboardState get(String id)
  {
    return (DashboardState) com.runwaysdk.business.Business.get(id);
  }
  
  public static DashboardState getByKey(String key)
  {
    return (DashboardState) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static DashboardState lock(java.lang.String id)
  {
    DashboardState _instance = DashboardState.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DashboardState unlock(java.lang.String id)
  {
    DashboardState _instance = DashboardState.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
