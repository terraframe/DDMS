package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -163687316)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedIPT.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AggregatedIPTBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedIPT";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBERNATALCARE = "numberNatalCare";
  public static java.lang.String NUMBERPREGNANT = "numberPregnant";
  public static java.lang.String NUMBERPREGNANTITN = "numberPregnantITN";
  public static java.lang.String NUMBERPREGNANTIRON = "numberPregnantIron";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TOTALITN = "totalITN";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = -163687316;
  
  public AggregatedIPTBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEndDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(ENDDATE);
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getNumberNatalCare()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERNATALCARE));
  }
  
  public void validateNumberNatalCare()
  {
    this.validateAttribute(NUMBERNATALCARE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberNatalCareMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(NUMBERNATALCARE);
  }
  
  public void setNumberNatalCare(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERNATALCARE, "");
    }
    else
    {
      setValue(NUMBERNATALCARE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANT));
  }
  
  public void validateNumberPregnant()
  {
    this.validateAttribute(NUMBERPREGNANT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberPregnantMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(NUMBERPREGNANT);
  }
  
  public void setNumberPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANT, "");
    }
    else
    {
      setValue(NUMBERPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnantITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTITN));
  }
  
  public void validateNumberPregnantITN()
  {
    this.validateAttribute(NUMBERPREGNANTITN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberPregnantITNMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(NUMBERPREGNANTITN);
  }
  
  public void setNumberPregnantITN(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTITN, "");
    }
    else
    {
      setValue(NUMBERPREGNANTITN, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberPregnantIron()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTIRON));
  }
  
  public void validateNumberPregnantIron()
  {
    this.validateAttribute(NUMBERPREGNANTIRON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberPregnantIronMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(NUMBERPREGNANTIRON);
  }
  
  public void setNumberPregnantIron(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTIRON, "");
    }
    else
    {
      setValue(NUMBERPREGNANTIRON, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStartDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(STARTDATE);
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public Integer getTotalITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALITN));
  }
  
  public void validateTotalITN()
  {
    this.validateAttribute(TOTALITN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTotalITNMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(TOTALITN);
  }
  
  public void setTotalITN(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALITN, "");
    }
    else
    {
      setValue(TOTALITN, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregatedIPTQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AggregatedIPTQuery query = new AggregatedIPTQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.intervention.monitor.IPTANCVisit addANCVisits(dss.vector.solutions.ontology.Term term)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisit) addChild(term, dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS);
  }
  
  public void removeANCVisits(dss.vector.solutions.ontology.Term term)
  {
    removeAllChildren(term, dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term> getAllANCVisits()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term>) getChildren(dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTANCVisit> getAllANCVisitsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTANCVisit>) getChildRelationships(dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.IPTANCVisit getANCVisitsRel(dss.vector.solutions.ontology.Term term)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTANCVisit> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTANCVisit>) getRelationshipsWithChild(term, dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS);
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
  
  public dss.vector.solutions.intervention.monitor.IPTDose addDoses(dss.vector.solutions.ontology.Term term)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDose) addChild(term, dss.vector.solutions.intervention.monitor.IPTDose.CLASS);
  }
  
  public void removeDoses(dss.vector.solutions.ontology.Term term)
  {
    removeAllChildren(term, dss.vector.solutions.intervention.monitor.IPTDose.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term> getAllDoses()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term>) getChildren(dss.vector.solutions.intervention.monitor.IPTDose.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTDose> getAllDosesRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTDose>) getChildRelationships(dss.vector.solutions.intervention.monitor.IPTDose.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.IPTDose getDosesRel(dss.vector.solutions.ontology.Term term)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTDose> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTDose>) getRelationshipsWithChild(term, dss.vector.solutions.intervention.monitor.IPTDose.CLASS);
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
  
  public dss.vector.solutions.intervention.monitor.IPTPatients addPatients(dss.vector.solutions.ontology.Term term)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatients) addChild(term, dss.vector.solutions.intervention.monitor.IPTPatients.CLASS);
  }
  
  public void removePatients(dss.vector.solutions.ontology.Term term)
  {
    removeAllChildren(term, dss.vector.solutions.intervention.monitor.IPTPatients.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term> getAllPatients()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term>) getChildren(dss.vector.solutions.intervention.monitor.IPTPatients.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTPatients> getAllPatientsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTPatients>) getChildRelationships(dss.vector.solutions.intervention.monitor.IPTPatients.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.IPTPatients getPatientsRel(dss.vector.solutions.ontology.Term term)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTPatients> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTPatients>) getRelationshipsWithChild(term, dss.vector.solutions.intervention.monitor.IPTPatients.CLASS);
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
  
  public dss.vector.solutions.intervention.monitor.IPTTreatment addTreatments(dss.vector.solutions.ontology.Term term)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatment) addChild(term, dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS);
  }
  
  public void removeTreatments(dss.vector.solutions.ontology.Term term)
  {
    removeAllChildren(term, dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term> getAllTreatments()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.ontology.Term>) getChildren(dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTTreatment> getAllTreatmentsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTTreatment>) getChildRelationships(dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.IPTTreatment getTreatmentsRel(dss.vector.solutions.ontology.Term term)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTTreatment> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.IPTTreatment>) getRelationshipsWithChild(term, dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS);
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
  
  public static AggregatedIPT get(String id)
  {
    return (AggregatedIPT) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AggregatedIPT getByKey(String key)
  {
    return (AggregatedIPT) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.AggregatedIPTView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTView lockView(java.lang.String id)
  {
    AggregatedIPT _instance = AggregatedIPT.get(id);
    return _instance.lockView();
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTView searchByGeoEntityAndEpiDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, dss.vector.solutions.surveillance.PeriodType periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.AggregatedIPTView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.AggregatedIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTView unlockView(java.lang.String id)
  {
    AggregatedIPT _instance = AggregatedIPT.get(id);
    return _instance.unlockView();
  }
  
  public static AggregatedIPT lock(java.lang.String id)
  {
    AggregatedIPT _instance = AggregatedIPT.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AggregatedIPT unlock(java.lang.String id)
  {
    AggregatedIPT _instance = AggregatedIPT.get(id);
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
