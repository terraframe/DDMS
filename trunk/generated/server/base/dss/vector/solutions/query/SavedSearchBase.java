package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1665010690)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SavedSearch.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SavedSearchBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedSearch";
  public static java.lang.String CONFIG = "config";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CSVFILE = "csvFile";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPPABLE = "mappable";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String QUERYNAME = "queryName";
  public static java.lang.String QUERYTYPE = "queryType";
  public static java.lang.String QUERYXML = "queryXml";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TEMPLATEFILE = "templateFile";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1665010690;
  
  public SavedSearchBase()
  {
    super();
  }
  
  public String getConfig()
  {
    return getValue(CONFIG);
  }
  
  public void validateConfig()
  {
    this.validateAttribute(CONFIG);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConfigMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(CONFIG);
  }
  
  public void setConfig(String value)
  {
    if(value == null)
    {
      setValue(CONFIG, "");
    }
    else
    {
      setValue(CONFIG, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  protected String getCsvFile()
  {
    return getValue(CSVFILE);
  }
  
  public void validateCsvFile()
  {
    this.validateAttribute(CSVFILE);
  }
  
  protected static com.runwaysdk.dataaccess.MdAttributeDAOIF getCsvFileMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(CSVFILE);
  }
  
  public void setCsvFile(String value)
  {
    if(value == null)
    {
      setValue(CSVFILE, "");
    }
    else
    {
      setValue(CSVFILE, value);
    }
  }
  
  public dss.vector.solutions.general.Disease getDisease()
  {
    if (getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Disease.get(getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.Disease value)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Users.get(getValue(LOCKEDBY));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Boolean getMappable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(MAPPABLE));
  }
  
  public void validateMappable()
  {
    this.validateAttribute(MAPPABLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMappableMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(MAPPABLE);
  }
  
  public void setMappable(Boolean value)
  {
    if(value == null)
    {
      setValue(MAPPABLE, "");
    }
    else
    {
      setValue(MAPPABLE, java.lang.Boolean.toString(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(OWNER);
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
  
  public String getQueryName()
  {
    return getValue(QUERYNAME);
  }
  
  public void validateQueryName()
  {
    this.validateAttribute(QUERYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQueryNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(QUERYNAME);
  }
  
  public void setQueryName(String value)
  {
    if(value == null)
    {
      setValue(QUERYNAME, "");
    }
    else
    {
      setValue(QUERYNAME, value);
    }
  }
  
  public String getQueryType()
  {
    return getValue(QUERYTYPE);
  }
  
  public void validateQueryType()
  {
    this.validateAttribute(QUERYTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQueryTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(QUERYTYPE);
  }
  
  public void setQueryType(String value)
  {
    if(value == null)
    {
      setValue(QUERYTYPE, "");
    }
    else
    {
      setValue(QUERYTYPE, value);
    }
  }
  
  public String getQueryXml()
  {
    return getValue(QUERYXML);
  }
  
  public void validateQueryXml()
  {
    this.validateAttribute(QUERYXML);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQueryXmlMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(QUERYXML);
  }
  
  public void setQueryXml(String value)
  {
    if(value == null)
    {
      setValue(QUERYXML, "");
    }
    else
    {
      setValue(QUERYXML, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getTemplateFile()
  {
    return getValue(TEMPLATEFILE);
  }
  
  public void validateTemplateFile()
  {
    this.validateAttribute(TEMPLATEFILE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTemplateFileMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(TEMPLATEFILE);
  }
  
  public void setTemplateFile(String value)
  {
    if(value == null)
    {
      setValue(TEMPLATEFILE, "");
    }
    else
    {
      setValue(TEMPLATEFILE, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearch.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SavedSearchQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SavedSearchQuery query = new SavedSearchQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.query.PersistsSearch addPersistedBy(dss.vector.solutions.MDSSUser mDSSUser)
  {
    return (dss.vector.solutions.query.PersistsSearch) addParent(mDSSUser, dss.vector.solutions.query.PersistsSearch.CLASS);
  }
  
  public void removePersistedBy(dss.vector.solutions.MDSSUser mDSSUser)
  {
    removeAllParents(mDSSUser, dss.vector.solutions.query.PersistsSearch.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.MDSSUser> getAllPersistedBy()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.MDSSUser>) getParents(dss.vector.solutions.query.PersistsSearch.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.PersistsSearch> getAllPersistedByRel()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.PersistsSearch>) getParentRelationships(dss.vector.solutions.query.PersistsSearch.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.PersistsSearch> getPersistedByRel(dss.vector.solutions.MDSSUser mDSSUser)
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.PersistsSearch>) getRelationshipsWithParent(mDSSUser, dss.vector.solutions.query.PersistsSearch.CLASS);
  }
  
  public static SavedSearch get(String id)
  {
    return (SavedSearch) com.runwaysdk.business.Business.get(id);
  }
  
  public static SavedSearch getByKey(String key)
  {
    return (SavedSearch) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.query.SavedSearchView getAsView(java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.query.SavedSearchView getAsView(java.lang.String id, java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    SavedSearch _instance = SavedSearch.get(id);
    return _instance.getAsView(includeXML, includeConfig);
  }
  
  public dss.vector.solutions.query.AttributeGeoHierarchy[] getAttributeGeoHierarchies()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.query.AttributeGeoHierarchy[] getAttributeGeoHierarchies(java.lang.String id)
  {
    SavedSearch _instance = SavedSearch.get(id);
    return _instance.getAttributeGeoHierarchies();
  }
  
  public static dss.vector.solutions.query.SavedSearchViewQuery getMappableSearches()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.query.SavedSearchViewQuery getSearchesForType(java.lang.String searchType)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public java.io.InputStream getTemplateStream()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.io.InputStream getTemplateStream(java.lang.String id)
  {
    SavedSearch _instance = SavedSearch.get(id);
    return _instance.getTemplateStream();
  }
  
  public dss.vector.solutions.query.ThematicVariable[] getThematicVariables()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.query.ThematicVariable[] getThematicVariables(java.lang.String id)
  {
    SavedSearch _instance = SavedSearch.get(id);
    return _instance.getThematicVariables();
  }
  
  public static dss.vector.solutions.query.SavedSearchView loadDefaultSearch(dss.vector.solutions.query.SavedSearchView savedSearchView)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.query.SavedSearchView loadSearch(java.lang.String searchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.query.SavedSearchView saveSearch(dss.vector.solutions.query.SavedSearchView savedSearchView)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.query.SavedSearchView updateSearch(dss.vector.solutions.query.SavedSearchView savedSearchView)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.SavedSearch.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static SavedSearch lock(java.lang.String id)
  {
    SavedSearch _instance = SavedSearch.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SavedSearch unlock(java.lang.String id)
  {
    SavedSearch _instance = SavedSearch.get(id);
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
