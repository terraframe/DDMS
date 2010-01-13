package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1661973803)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Layer.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LayerBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Layer";
  public static java.lang.String CLIPTOBASELAYER = "clipToBaseLayer";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEFAULTSTYLES = "defaultStyles";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOHIERARCHY = "geoHierarchy";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MDATTRIBUTE = "mdAttribute";
  public static java.lang.String OPACITY = "opacity";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String RENDERAS = "renderAs";
  public static java.lang.String SAVEDSEARCH = "savedSearch";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SHOWTHEMATICVALUE = "showThematicValue";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLDFILE = "sldFile";
  public static java.lang.String THEMATICUSERALIAS = "thematicUserAlias";
  public static java.lang.String THEMATICVARIABLE = "thematicVariable";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VIEWCREATED = "viewCreated";
  public static java.lang.String VIEWNAME = "viewName";
  private static final long serialVersionUID = 1661973803;
  
  public LayerBase()
  {
    super();
  }
  
  public Boolean getClipToBaseLayer()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CLIPTOBASELAYER));
  }
  
  public void validateClipToBaseLayer()
  {
    this.validateAttribute(CLIPTOBASELAYER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getClipToBaseLayerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(CLIPTOBASELAYER);
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
  
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.query.Styles getDefaultStyles()
  {
    if (getValue(DEFAULTSTYLES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.Styles.get(getValue(DEFAULTSTYLES));
    }
  }
  
  public void validateDefaultStyles()
  {
    this.validateAttribute(DEFAULTSTYLES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDefaultStylesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(DEFAULTSTYLES);
  }
  
  public void setDefaultStyles(dss.vector.solutions.query.Styles value)
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
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomain value)
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
  
  public dss.vector.solutions.geo.GeoHierarchy getGeoHierarchy()
  {
    if (getValue(GEOHIERARCHY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchy.get(getValue(GEOHIERARCHY));
    }
  }
  
  public void validateGeoHierarchy()
  {
    this.validateAttribute(GEOHIERARCHY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoHierarchyMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(GEOHIERARCHY);
  }
  
  public void setGeoHierarchy(dss.vector.solutions.geo.GeoHierarchy value)
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
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
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void validateLayerName()
  {
    this.validateAttribute(LAYERNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLayerNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(LAYERNAME);
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
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public com.terraframe.mojo.system.metadata.MdAttribute getMdAttribute()
  {
    if (getValue(MDATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdAttribute.get(getValue(MDATTRIBUTE));
    }
  }
  
  public void validateMdAttribute()
  {
    this.validateAttribute(MDATTRIBUTE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMdAttributeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(MDATTRIBUTE);
  }
  
  public void setMdAttribute(com.terraframe.mojo.system.metadata.MdAttribute value)
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
  
  public java.math.BigDecimal getOpacity()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPACITY));
  }
  
  public void validateOpacity()
  {
    this.validateAttribute(OPACITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOpacityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(OPACITY);
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
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.terraframe.mojo.system.Actor value)
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.AllRenderTypes> getRenderAs()
  {
    return (java.util.List<dss.vector.solutions.query.AllRenderTypes>) getEnumValues(RENDERAS);
  }
  
  public void addRenderAs(dss.vector.solutions.query.AllRenderTypes value)
  {
    if(value != null)
    {
      addEnumItem(RENDERAS, value.getId());
    }
  }
  
  public void removeRenderAs(dss.vector.solutions.query.AllRenderTypes value)
  {
    if(value != null)
    {
      removeEnumItem(RENDERAS, value.getId());
    }
  }
  
  public void clearRenderAs()
  {
    clearEnum(RENDERAS);
  }
  
  public void validateRenderAs()
  {
    this.validateAttribute(RENDERAS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRenderAsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(RENDERAS);
  }
  
  public dss.vector.solutions.query.SavedSearch getSavedSearch()
  {
    if (getValue(SAVEDSEARCH).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedSearch.get(getValue(SAVEDSEARCH));
    }
  }
  
  public void validateSavedSearch()
  {
    this.validateAttribute(SAVEDSEARCH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSavedSearchMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(SAVEDSEARCH);
  }
  
  public void setSavedSearch(dss.vector.solutions.query.SavedSearch value)
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
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public Boolean getShowThematicValue()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SHOWTHEMATICVALUE));
  }
  
  public void validateShowThematicValue()
  {
    this.validateAttribute(SHOWTHEMATICVALUE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getShowThematicValueMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(SHOWTHEMATICVALUE);
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
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getSldFile()
  {
    return getValue(SLDFILE);
  }
  
  public void validateSldFile()
  {
    this.validateAttribute(SLDFILE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSldFileMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(SLDFILE);
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
  
  public String getThematicUserAlias()
  {
    return getValue(THEMATICUSERALIAS);
  }
  
  public void validateThematicUserAlias()
  {
    this.validateAttribute(THEMATICUSERALIAS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getThematicUserAliasMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(THEMATICUSERALIAS);
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
  
  public dss.vector.solutions.query.ThematicVariable getThematicVariable()
  {
    if (getValue(THEMATICVARIABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.ThematicVariable.get(getValue(THEMATICVARIABLE));
    }
  }
  
  public void validateThematicVariable()
  {
    this.validateAttribute(THEMATICVARIABLE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getThematicVariableMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(THEMATICVARIABLE);
  }
  
  public void setThematicVariable(dss.vector.solutions.query.ThematicVariable value)
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
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public Boolean getViewCreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIEWCREATED));
  }
  
  public void validateViewCreated()
  {
    this.validateAttribute(VIEWCREATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getViewCreatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(VIEWCREATED);
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
  
  public String getViewName()
  {
    return getValue(VIEWNAME);
  }
  
  public void validateViewName()
  {
    this.validateAttribute(VIEWNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getViewNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.Layer.CLASS);
    return mdClassIF.definesAttribute(VIEWNAME);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LayerQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LayerQuery query = new LayerQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.query.HasCategories addHasCategory(dss.vector.solutions.query.AbstractCategory abstractCategory)
  {
    return (dss.vector.solutions.query.HasCategories) addChild(abstractCategory, dss.vector.solutions.query.HasCategories.CLASS);
  }
  
  public void removeHasCategory(dss.vector.solutions.query.AbstractCategory abstractCategory)
  {
    removeAllChildren(abstractCategory, dss.vector.solutions.query.HasCategories.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.AbstractCategory> getAllHasCategory()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.AbstractCategory>) getChildren(dss.vector.solutions.query.HasCategories.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasCategories> getAllHasCategoryRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasCategories>) getChildRelationships(dss.vector.solutions.query.HasCategories.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasCategories> getHasCategoryRel(dss.vector.solutions.query.AbstractCategory abstractCategory)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasCategories>) getRelationshipsWithChild(abstractCategory, dss.vector.solutions.query.HasCategories.CLASS);
  }
  
  public dss.vector.solutions.query.HasLayers addMap(dss.vector.solutions.query.SavedMap savedMap)
  {
    return (dss.vector.solutions.query.HasLayers) addParent(savedMap, dss.vector.solutions.query.HasLayers.CLASS);
  }
  
  public void removeMap(dss.vector.solutions.query.SavedMap savedMap)
  {
    removeAllParents(savedMap, dss.vector.solutions.query.HasLayers.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.SavedMap> getAllMap()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.SavedMap>) getParents(dss.vector.solutions.query.HasLayers.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasLayers> getAllMapRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasLayers>) getParentRelationships(dss.vector.solutions.query.HasLayers.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasLayers> getMapRel(dss.vector.solutions.query.SavedMap savedMap)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.query.HasLayers>) getRelationshipsWithParent(savedMap, dss.vector.solutions.query.HasLayers.CLASS);
  }
  
  public static Layer get(String id)
  {
    return (Layer) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Layer getByKey(String key)
  {
    return (Layer) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public void applyWithStyles(dss.vector.solutions.query.Styles styles, java.lang.String savedMapId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.Layer.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyWithStyles(java.lang.String id, dss.vector.solutions.query.Styles styles, java.lang.String savedMapId)
  {
    Layer _instance = Layer.get(id);
    _instance.applyWithStyles(styles, savedMapId);
  }
  
  public dss.vector.solutions.query.AttributeGeoHierarchy[] getAttributeGeoHierarchies()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.Layer.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.query.AttributeGeoHierarchy[] getAttributeGeoHierarchies(java.lang.String id)
  {
    Layer _instance = Layer.get(id);
    return _instance.getAttributeGeoHierarchies();
  }
  
  public static Layer lock(java.lang.String id)
  {
    Layer _instance = Layer.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Layer unlock(java.lang.String id)
  {
    Layer _instance = Layer.get(id);
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
