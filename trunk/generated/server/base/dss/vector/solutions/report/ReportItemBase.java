package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 808264590)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReportItem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ReportItemBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.ReportItem";
  public static java.lang.String CACHEDOCUMENT = "cacheDocument";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DESIGN = "design";
  public static java.lang.String DOCUMENT = "document";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OUTPUTFORMAT = "outputFormat";
  public static java.lang.String OUTPUTFORMATINDEX = "outputFormatIndex";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String REPORTLABEL = "reportLabel";
  private com.runwaysdk.business.Struct reportLabel = null;
  
  public static java.lang.String REPORTNAME = "reportName";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 808264590;
  
  public ReportItemBase()
  {
    super();
    reportLabel = super.getStruct("reportLabel");
  }
  
  public Boolean getCacheDocument()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CACHEDOCUMENT));
  }
  
  public void validateCacheDocument()
  {
    this.validateAttribute(CACHEDOCUMENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getCacheDocumentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(CACHEDOCUMENT);
  }
  
  public void setCacheDocument(Boolean value)
  {
    if(value == null)
    {
      setValue(CACHEDOCUMENT, "");
    }
    else
    {
      setValue(CACHEDOCUMENT, java.lang.Boolean.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public String getDesign()
  {
    return getValue(DESIGN);
  }
  
  public void validateDesign()
  {
    this.validateAttribute(DESIGN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeFileDAOIF getDesignMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeFileDAOIF)mdClassIF.definesAttribute(DESIGN);
  }
  
  public void setDesign(String value)
  {
    if(value == null)
    {
      setValue(DESIGN, "");
    }
    else
    {
      setValue(DESIGN, value);
    }
  }
  
  public String getDocument()
  {
    return getValue(DOCUMENT);
  }
  
  public void validateDocument()
  {
    this.validateAttribute(DOCUMENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeFileDAOIF getDocumentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeFileDAOIF)mdClassIF.definesAttribute(DOCUMENT);
  }
  
  public void setDocument(String value)
  {
    if(value == null)
    {
      setValue(DOCUMENT, "");
    }
    else
    {
      setValue(DOCUMENT, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.report.OutputFormat> getOutputFormat()
  {
    return (java.util.List<dss.vector.solutions.report.OutputFormat>) getEnumValues(OUTPUTFORMAT);
  }
  
  public void addOutputFormat(dss.vector.solutions.report.OutputFormat value)
  {
    if(value != null)
    {
      addEnumItem(OUTPUTFORMAT, value.getId());
    }
  }
  
  public void removeOutputFormat(dss.vector.solutions.report.OutputFormat value)
  {
    if(value != null)
    {
      removeEnumItem(OUTPUTFORMAT, value.getId());
    }
  }
  
  public void clearOutputFormat()
  {
    clearEnum(OUTPUTFORMAT);
  }
  
  public void validateOutputFormat()
  {
    this.validateAttribute(OUTPUTFORMAT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getOutputFormatMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(OUTPUTFORMAT);
  }
  
  public String getOutputFormatIndex()
  {
    return getValue(OUTPUTFORMATINDEX);
  }
  
  public void validateOutputFormatIndex()
  {
    this.validateAttribute(OUTPUTFORMATINDEX);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getOutputFormatIndexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OUTPUTFORMATINDEX);
  }
  
  public void setOutputFormatIndex(String value)
  {
    if(value == null)
    {
      setValue(OUTPUTFORMATINDEX, "");
    }
    else
    {
      setValue(OUTPUTFORMATINDEX, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
  
  public dss.vector.solutions.report.ReportItemReportLabel getReportLabel()
  {
    return (dss.vector.solutions.report.ReportItemReportLabel) reportLabel;
  }
  
  public void validateReportLabel()
  {
    this.validateAttribute(REPORTLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF getReportLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF)mdClassIF.definesAttribute(REPORTLABEL);
  }
  
  public String getReportName()
  {
    return getValue(REPORTNAME);
  }
  
  public void validateReportName()
  {
    this.validateAttribute(REPORTNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getReportNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(REPORTNAME);
  }
  
  public void setReportName(String value)
  {
    if(value == null)
    {
      setValue(REPORTNAME, "");
    }
    else
    {
      setValue(REPORTNAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportItem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ReportItemQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ReportItemQuery query = new ReportItemQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ReportItem get(String id)
  {
    return (ReportItem) com.runwaysdk.business.Business.get(id);
  }
  
  public static ReportItem getByKey(String key)
  {
    return (ReportItem) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public void applyWithFile(java.io.InputStream fileStream)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyWithFile(java.lang.String id, java.io.InputStream fileStream)
  {
    ReportItem _instance = ReportItem.get(id);
    _instance.applyWithFile(fileStream);
  }
  
  public java.io.InputStream getDesignAsStream()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.io.InputStream getDesignAsStream(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getDesignAsStream();
  }
  
  public java.io.InputStream getDocumentAsStream()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.io.InputStream getDocumentAsStream(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getDocumentAsStream();
  }
  
  public java.lang.String getParameterDefinitions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getParameterDefinitions(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getParameterDefinitions();
  }
  
  public java.lang.String getReportProblems()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getReportProblems(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getReportProblems();
  }
  
  public java.lang.String getSelectionListForCascadingGroup(java.lang.String paramName, java.lang.String groupName, java.lang.String[] cascadingValues)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getSelectionListForCascadingGroup(java.lang.String id, java.lang.String paramName, java.lang.String groupName, java.lang.String[] cascadingValues)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getSelectionListForCascadingGroup(paramName, groupName, cascadingValues);
  }
  
  public java.lang.String getURL()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getURL(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.getURL();
  }
  
  public java.lang.Boolean hasParameterDefinitions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Boolean hasParameterDefinitions(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.hasParameterDefinitions();
  }
  
  public java.lang.Long render(java.io.OutputStream outputStream, dss.vector.solutions.report.ReportParameter[] parameters, java.lang.String baseURL, java.lang.String reportURL)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Long render(java.lang.String id, java.io.OutputStream outputStream, dss.vector.solutions.report.ReportParameter[] parameters, java.lang.String baseURL, java.lang.String reportURL)
  {
    ReportItem _instance = ReportItem.get(id);
    return _instance.render(outputStream, parameters, baseURL, reportURL);
  }
  
  public static void uploadResources(java.io.InputStream resourcesIS, java.lang.String nameOfResource)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void validatePermissions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.ReportItem.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void validatePermissions(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    _instance.validatePermissions();
  }
  
  public static ReportItem lock(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ReportItem unlock(java.lang.String id)
  {
    ReportItem _instance = ReportItem.get(id);
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
