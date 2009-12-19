package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -799428061)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MolecularAssayBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MolecularAssay";
  public static java.lang.String ASSAYMETHOD = "assayMethod";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERRR = "numberRR";
  public static java.lang.String NUMBERRS = "numberRS";
  public static java.lang.String NUMBERSS = "numberSS";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = -799428061;
  
  public MolecularAssayBase()
  {
    super();
  }
  
  public dss.vector.solutions.ontology.Term getAssayMethod()
  {
    if (getValue(ASSAYMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ASSAYMETHOD));
    }
  }
  
  public void validateAssayMethod()
  {
    this.validateAttribute(ASSAYMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAssayMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(ASSAYMETHOD);
  }
  
  public void setAssayMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(ASSAYMETHOD, "");
    }
    else
    {
      setValue(ASSAYMETHOD, value.getId());
    }
  }
  
  public dss.vector.solutions.entomology.MosquitoCollection getCollection()
  {
    if (getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollection value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
  
  public dss.vector.solutions.ontology.Term getGeneration()
  {
    if (getValue(GENERATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(GENERATION));
    }
  }
  
  public void validateGeneration()
  {
    this.validateAttribute(GENERATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(GENERATION);
  }
  
  public void setGeneration(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getIdentMethod()
  {
    if (getValue(IDENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(IDENTMETHOD));
    }
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(IDENTMETHOD);
  }
  
  public void setIdentMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value.getId());
    }
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void validateIsofemale()
  {
    this.validateAttribute(ISOFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsofemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(ISOFEMALE);
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void validateMosquitoId()
  {
    this.validateAttribute(MOSQUITOID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMosquitoIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(MOSQUITOID);
  }
  
  public void setMosquitoId(String value)
  {
    if(value == null)
    {
      setValue(MOSQUITOID, "");
    }
    else
    {
      setValue(MOSQUITOID, value);
    }
  }
  
  public Integer getNumberRR()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRR));
  }
  
  public void validateNumberRR()
  {
    this.validateAttribute(NUMBERRR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberRRMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(NUMBERRR);
  }
  
  public void setNumberRR(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRR, "");
    }
    else
    {
      setValue(NUMBERRR, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberRS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRS));
  }
  
  public void validateNumberRS()
  {
    this.validateAttribute(NUMBERRS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberRSMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(NUMBERRS);
  }
  
  public void setNumberRS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRS, "");
    }
    else
    {
      setValue(NUMBERRS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberSS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSS));
  }
  
  public void validateNumberSS()
  {
    this.validateAttribute(NUMBERSS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberSSMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(NUMBERSS);
  }
  
  public void setNumberSS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSS, "");
    }
    else
    {
      setValue(NUMBERSS, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public dss.vector.solutions.ontology.Term getSex()
  {
    if (getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SEX));
    }
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public dss.vector.solutions.ontology.Term getSpecies()
  {
    if (getValue(SPECIES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SPECIES));
    }
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSpeciesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getTarget()
  {
    if (getValue(TARGET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TARGET));
    }
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(TARGET);
  }
  
  public void setTarget(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MolecularAssay.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MolecularAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MolecularAssayQuery query = new MolecularAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MolecularAssay get(String id)
  {
    return (MolecularAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static MolecularAssay getByKey(String key)
  {
    return (MolecularAssay) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.entomology.MolecularAssayView getView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MolecularAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.MolecularAssayView getView(java.lang.String id)
  {
    MolecularAssay _instance = MolecularAssay.get(id);
    return _instance.getView();
  }
  
  public dss.vector.solutions.entomology.MolecularAssayView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MolecularAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.MolecularAssayView lockView(java.lang.String id)
  {
    MolecularAssay _instance = MolecularAssay.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.entomology.MolecularAssayView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.MolecularAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.MolecularAssayView unlockView(java.lang.String id)
  {
    MolecularAssay _instance = MolecularAssay.get(id);
    return _instance.unlockView();
  }
  
  public static MolecularAssay lock(java.lang.String id)
  {
    MolecularAssay _instance = MolecularAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MolecularAssay unlock(java.lang.String id)
  {
    MolecularAssay _instance = MolecularAssay.get(id);
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
