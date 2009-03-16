package csu.mrc.ivcc.mdss.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoHierarchy.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GeoHierarchyBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.GeoHierarchy";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOENTITYCLASS = "geoEntityClass";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1237219382770L;
  
  public GeoHierarchyBase()
  {
    super();
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    try
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    try
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
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
  
  public com.terraframe.mojo.system.metadata.MdBusiness getGeoEntityClass()
  {
    try
    {
      return com.terraframe.mojo.system.metadata.MdBusiness.get(getValue(GEOENTITYCLASS));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateGeoEntityClass()
  {
    this.validateAttribute(GEOENTITYCLASS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityClassMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(GEOENTITYCLASS);
  }
  
  public void setGeoEntityClass(com.terraframe.mojo.system.metadata.MdBusiness value)
  {
    if(value == null)
    {
      setValue(GEOENTITYCLASS, "");
    }
    else
    {
      setValue(GEOENTITYCLASS, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    try
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    try
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    try
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
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
  
  public Boolean getPolitical()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void validatePolitical()
  {
    this.validateAttribute(POLITICAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPoliticalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(POLITICAL);
  }
  
  public void setPolitical(Boolean value)
  {
    if(value == null)
    {
      setValue(POLITICAL, "");
    }
    else
    {
      setValue(POLITICAL, java.lang.Boolean.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(SEQ);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.geo.GeoHierarchy.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoHierarchyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GeoHierarchyQuery query = new GeoHierarchyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public csu.mrc.ivcc.mdss.geo.AllowedIn addAcceptsGeoEntity(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    return (csu.mrc.ivcc.mdss.geo.AllowedIn) addChild(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  public void removeAcceptsGeoEntity(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    removeAllChildren(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.GeoHierarchy> getAllAcceptsGeoEntity()
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.GeoHierarchy>) getChildren(csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn> getAllAcceptsGeoEntityRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn>) getChildRelationships(csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn> getAcceptsGeoEntityRel(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn>) getRelationshipsWithChild(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  public csu.mrc.ivcc.mdss.geo.AllowedIn addAllowedInGeoEntity(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    return (csu.mrc.ivcc.mdss.geo.AllowedIn) addParent(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  public void removeAllowedInGeoEntity(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    removeAllParents(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.GeoHierarchy> getAllAllowedInGeoEntity()
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.GeoHierarchy>) getParents(csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn> getAllAllowedInGeoEntityRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn>) getParentRelationships(csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn> getAllowedInGeoEntityRel(csu.mrc.ivcc.mdss.geo.GeoHierarchy geoHierarchy)
  {
    return (com.terraframe.mojo.query.OIterator<? extends csu.mrc.ivcc.mdss.geo.AllowedIn>) getRelationshipsWithParent(geoHierarchy, csu.mrc.ivcc.mdss.geo.AllowedIn.CLASS);
  }
  
  public static GeoHierarchy get(String id)
  {
    return (GeoHierarchy) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static java.lang.String defineAllowedTree(java.lang.String geoEntityId)
  {
    return null;
  }
  
  public static csu.mrc.ivcc.mdss.geo.GeoHierarchyView defineGeoEntity(csu.mrc.ivcc.mdss.geo.GeoEntityDefinition definition)
  {
    return null;
  }
  
  public static csu.mrc.ivcc.mdss.geo.GeoHierarchyViewQuery getGeoEntityHierarchyViews(java.lang.String sortAttribute, java.lang.Boolean ascending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    return null;
  }
  
  public static void deleteGeoHierarchy(java.lang.String geoHierarchyId)
  {
  }
  
  public csu.mrc.ivcc.mdss.geo.GeoHierarchyView getViewForGeoHierarchy()
  {
    return null;
  }
  
  public static final csu.mrc.ivcc.mdss.geo.GeoHierarchyView getViewForGeoHierarchy(java.lang.String id)
  {
    GeoHierarchy _instance = GeoHierarchy.get(id);
    return _instance.getViewForGeoHierarchy();
  }
  
  public csu.mrc.ivcc.mdss.geo.GeoHierarchyViewQuery getOrderedChildren()
  {
    return null;
  }
  
  public static final csu.mrc.ivcc.mdss.geo.GeoHierarchyViewQuery getOrderedChildren(java.lang.String id)
  {
    GeoHierarchy _instance = GeoHierarchy.get(id);
    return _instance.getOrderedChildren();
  }
  
  public static void applyExistingWithParent(java.lang.String childGeoHierarchyId, java.lang.String parentGeoHierarchyId, java.lang.Boolean cloneOperation)
  {
  }
  
  public static GeoHierarchy lock(java.lang.String id)
  {
    GeoHierarchy _instance = GeoHierarchy.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GeoHierarchy unlock(java.lang.String id)
  {
    GeoHierarchy _instance = GeoHierarchy.get(id);
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
