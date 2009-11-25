package dss.vector.solutions.geo.generated;

@com.terraframe.mojo.business.ClassSignature(hash = -1983243765)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntity.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GeoEntityBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.GeoEntity";
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String GEODATA = "geoData";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOMULTIPOLYGON = "geoMultiPolygon";
  public static java.lang.String GEOPOINT = "geoPoint";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TERM = "term";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = -1983243765;
  
  public GeoEntityBase()
  {
    super();
  }
  
  public Boolean getActivated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
  }
  
  public void validateActivated()
  {
    this.validateAttribute(ACTIVATED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getActivatedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(ACTIVATED);
  }
  
  public void setActivated(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVATED, "");
    }
    else
    {
      setValue(ACTIVATED, java.lang.Boolean.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
  
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void validateEntityName()
  {
    this.validateAttribute(ENTITYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
    }
  }
  
  public String getGeoData()
  {
    return getValue(GEODATA);
  }
  
  public void validateGeoData()
  {
    this.validateAttribute(GEODATA);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoDataMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(GEODATA);
  }
  
  public void setGeoData(String value)
  {
    if(value == null)
    {
      setValue(GEODATA, "");
    }
    else
    {
      setValue(GEODATA, value);
    }
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public com.vividsolutions.jts.geom.MultiPolygon getGeoMultiPolygon()
  {
    return (com.vividsolutions.jts.geom.MultiPolygon)getObjectValue(GEOMULTIPOLYGON);
  }
  
  public void validateGeoMultiPolygon()
  {
    this.validateAttribute(GEOMULTIPOLYGON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoMultiPolygonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(GEOMULTIPOLYGON);
  }
  
  public void setGeoMultiPolygon(com.vividsolutions.jts.geom.MultiPolygon value)
  {
    if(value == null)
    {
      setValue(GEOMULTIPOLYGON, "");
    }
    else
    {
      setValue(GEOMULTIPOLYGON, value);
    }
  }
  
  public com.vividsolutions.jts.geom.Point getGeoPoint()
  {
    return (com.vividsolutions.jts.geom.Point)getObjectValue(GEOPOINT);
  }
  
  public void validateGeoPoint()
  {
    this.validateAttribute(GEOPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(GEOPOINT);
  }
  
  public void setGeoPoint(com.vividsolutions.jts.geom.Point value)
  {
    if(value == null)
    {
      setValue(GEOPOINT, "");
    }
    else
    {
      setValue(GEOPOINT, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public dss.vector.solutions.ontology.Term getTerm()
  {
    if (getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TERM));
    }
  }
  
  public void validateTerm()
  {
    this.validateAttribute(TERM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.GeoEntity.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoEntityQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GeoEntityQuery query = new GeoEntityQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.geo.LocatedIn addContainsGeoEntity(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    return (dss.vector.solutions.geo.LocatedIn) addChild(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  public void removeContainsGeoEntity(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    removeAllChildren(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.generated.GeoEntity> getAllContainsGeoEntity()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.generated.GeoEntity>) getChildren(dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn> getAllContainsGeoEntityRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn>) getChildRelationships(dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.geo.LocatedIn getContainsGeoEntityRel(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn>) getRelationshipsWithChild(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public dss.vector.solutions.geo.HasSynonym addSynonyms(dss.vector.solutions.geo.GeoSynonym geoSynonym)
  {
    return (dss.vector.solutions.geo.HasSynonym) addChild(geoSynonym, dss.vector.solutions.geo.HasSynonym.CLASS);
  }
  
  public void removeSynonyms(dss.vector.solutions.geo.GeoSynonym geoSynonym)
  {
    removeAllChildren(geoSynonym, dss.vector.solutions.geo.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.GeoSynonym> getAllSynonyms()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.GeoSynonym>) getChildren(dss.vector.solutions.geo.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.HasSynonym> getAllSynonymsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.HasSynonym>) getChildRelationships(dss.vector.solutions.geo.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.geo.HasSynonym getSynonymsRel(dss.vector.solutions.geo.GeoSynonym geoSynonym)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.HasSynonym> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.HasSynonym>) getRelationshipsWithChild(geoSynonym, dss.vector.solutions.geo.HasSynonym.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public dss.vector.solutions.geo.LocatedIn addLocatedInGeoEntity(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    return (dss.vector.solutions.geo.LocatedIn) addParent(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  public void removeLocatedInGeoEntity(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    removeAllParents(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.generated.GeoEntity> getAllLocatedInGeoEntity()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.generated.GeoEntity>) getParents(dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn> getAllLocatedInGeoEntityRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn>) getParentRelationships(dss.vector.solutions.geo.LocatedIn.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.geo.LocatedIn getLocatedInGeoEntityRel(dss.vector.solutions.geo.generated.GeoEntity geoEntity)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.geo.LocatedIn>) getRelationshipsWithParent(geoEntity, dss.vector.solutions.geo.LocatedIn.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static GeoEntity get(String id)
  {
    return (GeoEntity) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static GeoEntity getByKey(String key)
  {
    return (GeoEntity) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public java.lang.String[] applyWithParent(java.lang.String parentGeoEntityId, java.lang.Boolean cloneOperation)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String[] applyWithParent(java.lang.String id, java.lang.String parentGeoEntityId, java.lang.Boolean cloneOperation)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.applyWithParent(parentGeoEntityId, cloneOperation);
  }
  
  public dss.vector.solutions.geo.GeoEntityView changeUniversalType(java.lang.String newType)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityView changeUniversalType(java.lang.String id, java.lang.String newType)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.changeUniversalType(newType);
  }
  
  public dss.vector.solutions.geo.GeoEntityView[] collectAllLocatedIn(java.lang.Boolean includeParents, java.lang.String filter)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityView[] collectAllLocatedIn(java.lang.String id, java.lang.Boolean includeParents, java.lang.String filter)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.collectAllLocatedIn(includeParents, filter);
  }
  
  public void confirmChangeParent(java.lang.String parentId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void confirmChangeParent(java.lang.String id, java.lang.String parentId)
  {
    GeoEntity _instance = GeoEntity.get(id);
    _instance.confirmChangeParent(parentId);
  }
  
  public void confirmDeleteEntity(java.lang.String parentId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void confirmDeleteEntity(java.lang.String id, java.lang.String parentId)
  {
    GeoEntity _instance = GeoEntity.get(id);
    _instance.confirmDeleteEntity(parentId);
  }
  
  public void deleteRelationship(java.lang.String parentId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteRelationship(java.lang.String id, java.lang.String parentId)
  {
    GeoEntity _instance = GeoEntity.get(id);
    _instance.deleteRelationship(parentId);
  }
  
  public java.lang.String[] getAllChildIds(java.lang.String typeFilter)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String[] getAllChildIds(java.lang.String id, java.lang.String typeFilter)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.getAllChildIds(typeFilter);
  }
  
  public static dss.vector.solutions.geo.GeoEntityViewQuery getAsViews(java.lang.String[] entities)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public java.lang.String[] getCompatibleTypes()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String[] getCompatibleTypes(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.getCompatibleTypes();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity[] getImmediateSprayChildren()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.geo.generated.GeoEntity[] getImmediateSprayChildren(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.getImmediateSprayChildren();
  }
  
  public dss.vector.solutions.geo.GeoEntityViewQuery getOrderedChildren(java.lang.String typeFilter)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewQuery getOrderedChildren(java.lang.String id, java.lang.String typeFilter)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.getOrderedChildren(typeFilter);
  }
  
  public java.lang.String getTypeDisplayLabel()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getTypeDisplayLabel(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.getTypeDisplayLabel();
  }
  
  public static dss.vector.solutions.geo.GeoEntityView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.geo.GeoEntityView getViewByGeoId(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.geo.GeoEntityView[] searchAndCollectByGeoId(java.lang.String geoId, java.lang.String filter)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.terraframe.mojo.query.ValueQuery searchByEntityName(java.lang.String entityType, java.lang.String entityName)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.terraframe.mojo.query.ValueQuery searchByEntityNameOrGeoId(java.lang.String entityType, java.lang.String entityName)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.geo.generated.GeoEntity searchByGeoId(java.lang.String geoId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public java.lang.String[] updateFromTree()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.generated.GeoEntity.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String[] updateFromTree(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
    return _instance.updateFromTree();
  }
  
  public static GeoEntity lock(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GeoEntity unlock(java.lang.String id)
  {
    GeoEntity _instance = GeoEntity.get(id);
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
