package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 79636260)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SubCollection.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SubCollectionBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.SubCollection";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISECTED = "disected";
  public static java.lang.String EGGS = "eggs";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FEMALESFED = "femalesFed";
  public static java.lang.String FEMALESGRAVID = "femalesGravid";
  public static java.lang.String FEMALESHALFGRAVID = "femalesHalfGravid";
  public static java.lang.String FEMALESTOTAL = "femalesTotal";
  public static java.lang.String FEMALESUNFED = "femalesUnfed";
  public static java.lang.String FEMALESUNKNOWN = "femalesUnknown";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MALE = "male";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PAROUS = "parous";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TOTAL = "total";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UNKNOWNS = "unknowns";
  private static final long serialVersionUID = 79636260;
  
  public SubCollectionBase()
  {
    super();
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
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCollectionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(COLLECTION);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public Integer getDisected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISECTED));
  }
  
  public void validateDisected()
  {
    this.validateAttribute(DISECTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getDisectedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(DISECTED);
  }
  
  public void setDisected(Integer value)
  {
    if(value == null)
    {
      setValue(DISECTED, "");
    }
    else
    {
      setValue(DISECTED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getEggs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
  }
  
  public void validateEggs()
  {
    this.validateAttribute(EGGS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getEggsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(EGGS);
  }
  
  public void setEggs(Integer value)
  {
    if(value == null)
    {
      setValue(EGGS, "");
    }
    else
    {
      setValue(EGGS, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
  
  public Integer getFemalesFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESFED));
  }
  
  public void validateFemalesFed()
  {
    this.validateAttribute(FEMALESFED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesFedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESFED);
  }
  
  public void setFemalesFed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESFED, "");
    }
    else
    {
      setValue(FEMALESFED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFemalesGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESGRAVID));
  }
  
  public void validateFemalesGravid()
  {
    this.validateAttribute(FEMALESGRAVID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesGravidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESGRAVID);
  }
  
  public void setFemalesGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESGRAVID, "");
    }
    else
    {
      setValue(FEMALESGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFemalesHalfGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESHALFGRAVID));
  }
  
  public void validateFemalesHalfGravid()
  {
    this.validateAttribute(FEMALESHALFGRAVID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesHalfGravidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESHALFGRAVID);
  }
  
  public void setFemalesHalfGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESHALFGRAVID, "");
    }
    else
    {
      setValue(FEMALESHALFGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFemalesTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESTOTAL));
  }
  
  public void validateFemalesTotal()
  {
    this.validateAttribute(FEMALESTOTAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesTotalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESTOTAL);
  }
  
  public void setFemalesTotal(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESTOTAL, "");
    }
    else
    {
      setValue(FEMALESTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFemalesUnfed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNFED));
  }
  
  public void validateFemalesUnfed()
  {
    this.validateAttribute(FEMALESUNFED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesUnfedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESUNFED);
  }
  
  public void setFemalesUnfed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNFED, "");
    }
    else
    {
      setValue(FEMALESUNFED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFemalesUnknown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNKNOWN));
  }
  
  public void validateFemalesUnknown()
  {
    this.validateAttribute(FEMALESUNKNOWN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFemalesUnknownMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FEMALESUNKNOWN);
  }
  
  public void setFemalesUnknown(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNKNOWN, "");
    }
    else
    {
      setValue(FEMALESUNKNOWN, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
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
  
  public String getIdentMethodId()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getIdentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(IDENTMETHOD);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
  
  public Integer getLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
  }
  
  public void validateLarvae()
  {
    this.validateAttribute(LARVAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getLarvaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(LARVAE);
  }
  
  public void setLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(LARVAE, "");
    }
    else
    {
      setValue(LARVAE, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getMale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
  }
  
  public void validateMale()
  {
    this.validateAttribute(MALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getMaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(MALE);
  }
  
  public void setMale(Integer value)
  {
    if(value == null)
    {
      setValue(MALE, "");
    }
    else
    {
      setValue(MALE, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
  
  public Integer getParous()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PAROUS));
  }
  
  public void validateParous()
  {
    this.validateAttribute(PAROUS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getParousMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PAROUS);
  }
  
  public void setParous(Integer value)
  {
    if(value == null)
    {
      setValue(PAROUS, "");
    }
    else
    {
      setValue(PAROUS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
  }
  
  public void validatePupae()
  {
    this.validateAttribute(PUPAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getPupaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PUPAE);
  }
  
  public void setPupae(Integer value)
  {
    if(value == null)
    {
      setValue(PUPAE, "");
    }
    else
    {
      setValue(PUPAE, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getSubCollectionId()
  {
    return getValue(SUBCOLLECTIONID);
  }
  
  public void validateSubCollectionId()
  {
    this.validateAttribute(SUBCOLLECTIONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSubCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SUBCOLLECTIONID);
  }
  
  public void setSubCollectionId(String value)
  {
    if(value == null)
    {
      setValue(SUBCOLLECTIONID, "");
    }
    else
    {
      setValue(SUBCOLLECTIONID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getTaxon()
  {
    if (getValue(TAXON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TAXON));
    }
  }
  
  public String getTaxonId()
  {
    return getValue(TAXON);
  }
  
  public void validateTaxon()
  {
    this.validateAttribute(TAXON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getTaxonMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(TAXON);
  }
  
  public void setTaxon(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value.getId());
    }
  }
  
  public Integer getTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTAL));
  }
  
  public void validateTotal()
  {
    this.validateAttribute(TOTAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getTotalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(TOTAL);
  }
  
  public void setTotal(Integer value)
  {
    if(value == null)
    {
      setValue(TOTAL, "");
    }
    else
    {
      setValue(TOTAL, java.lang.Integer.toString(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  public Integer getUnknowns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
  }
  
  public void validateUnknowns()
  {
    this.validateAttribute(UNKNOWNS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getUnknownsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.SubCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(UNKNOWNS);
  }
  
  public void setUnknowns(Integer value)
  {
    if(value == null)
    {
      setValue(UNKNOWNS, "");
    }
    else
    {
      setValue(UNKNOWNS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SubCollectionQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SubCollectionQuery query = new SubCollectionQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SubCollection get(String id)
  {
    return (SubCollection) com.runwaysdk.business.Business.get(id);
  }
  
  public static SubCollection getByKey(String key)
  {
    return (SubCollection) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.entomology.SubCollectionView getView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.SubCollection.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionView getView(java.lang.String id)
  {
    SubCollection _instance = SubCollection.get(id);
    return _instance.getView();
  }
  
  public dss.vector.solutions.entomology.SubCollectionView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.SubCollection.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionView lockView(java.lang.String id)
  {
    SubCollection _instance = SubCollection.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.entomology.SubCollectionView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.SubCollection.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionView unlockView(java.lang.String id)
  {
    SubCollection _instance = SubCollection.get(id);
    return _instance.unlockView();
  }
  
  public static SubCollection lock(java.lang.String id)
  {
    SubCollection _instance = SubCollection.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SubCollection unlock(java.lang.String id)
  {
    SubCollection _instance = SubCollection.get(id);
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
