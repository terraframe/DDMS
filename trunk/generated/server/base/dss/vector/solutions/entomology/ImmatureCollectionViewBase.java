package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -2052281682)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ImmatureCollectionView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ImmatureCollectionViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ImmatureCollectionView";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CONTAINERGRID = "containerGrid";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NOTES = "notes";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String NUMBERWITHIMMATURES = "numberWithImmatures";
  public static java.lang.String NUMBERWITHLARVAE = "numberWithLarvae";
  public static java.lang.String NUMBERWITHPUPAE = "numberWithPupae";
  public static java.lang.String PREMISEID = "premiseId";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TAXONID = "taxonId";
  private static final long serialVersionUID = -2052281682;
  
  public ImmatureCollectionViewBase()
  {
    super();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void validateCollectionId()
  {
    this.validateAttribute(COLLECTIONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getContainerGrid()
  {
    if (getValue(CONTAINERGRID).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CONTAINERGRID));
    }
  }
  
  public void validateContainerGrid()
  {
    this.validateAttribute(CONTAINERGRID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getContainerGridMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(CONTAINERGRID);
  }
  
  public void setContainerGrid(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CONTAINERGRID, "");
    }
    else
    {
      setValue(CONTAINERGRID, value.getId());
    }
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEndDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
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
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getNotes()
  {
    return getValue(NOTES);
  }
  
  public void validateNotes()
  {
    this.validateAttribute(NOTES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNotesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NOTES);
  }
  
  public void setNotes(String value)
  {
    if(value == null)
    {
      setValue(NOTES, "");
    }
    else
    {
      setValue(NOTES, value);
    }
  }
  
  public Integer getNumberExamined()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEREXAMINED));
  }
  
  public void validateNumberExamined()
  {
    this.validateAttribute(NUMBEREXAMINED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberExaminedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NUMBEREXAMINED);
  }
  
  public void setNumberExamined(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEREXAMINED, "");
    }
    else
    {
      setValue(NUMBEREXAMINED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberInhabitants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERINHABITANTS));
  }
  
  public void validateNumberInhabitants()
  {
    this.validateAttribute(NUMBERINHABITANTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberInhabitantsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NUMBERINHABITANTS);
  }
  
  public void setNumberInhabitants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERINHABITANTS, "");
    }
    else
    {
      setValue(NUMBERINHABITANTS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberWithImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHIMMATURES));
  }
  
  public void validateNumberWithImmatures()
  {
    this.validateAttribute(NUMBERWITHIMMATURES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberWithImmaturesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NUMBERWITHIMMATURES);
  }
  
  public void setNumberWithImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHIMMATURES, "");
    }
    else
    {
      setValue(NUMBERWITHIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberWithLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVAE));
  }
  
  public void validateNumberWithLarvae()
  {
    this.validateAttribute(NUMBERWITHLARVAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberWithLarvaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NUMBERWITHLARVAE);
  }
  
  public void setNumberWithLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVAE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberWithPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHPUPAE));
  }
  
  public void validateNumberWithPupae()
  {
    this.validateAttribute(NUMBERWITHPUPAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberWithPupaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(NUMBERWITHPUPAE);
  }
  
  public void setNumberWithPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHPUPAE, "");
    }
    else
    {
      setValue(NUMBERWITHPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public String getPremiseId()
  {
    return getValue(PREMISEID);
  }
  
  public void validatePremiseId()
  {
    this.validateAttribute(PREMISEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPremiseIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(PREMISEID);
  }
  
  public void setPremiseId(String value)
  {
    if(value == null)
    {
      setValue(PREMISEID, "");
    }
    else
    {
      setValue(PREMISEID, value);
    }
  }
  
  public java.math.BigDecimal getPremiseSize()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PREMISESIZE));
  }
  
  public void validatePremiseSize()
  {
    this.validateAttribute(PREMISESIZE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPremiseSizeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(PREMISESIZE);
  }
  
  public void setPremiseSize(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PREMISESIZE, "");
    }
    else
    {
      setValue(PREMISESIZE, value.toString());
    }
  }
  
  public dss.vector.solutions.ontology.Term getPremiseType()
  {
    if (getValue(PREMISETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(PREMISETYPE));
    }
  }
  
  public void validatePremiseType()
  {
    this.validateAttribute(PREMISETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPremiseTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(PREMISETYPE);
  }
  
  public void setPremiseType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value.getId());
    }
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getStartDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
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
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public void validateTaxon()
  {
    this.validateAttribute(TAXON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTaxonMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(TAXON);
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
  
  public String getTaxonId()
  {
    return getValue(TAXONID);
  }
  
  public void validateTaxonId()
  {
    this.validateAttribute(TAXONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTaxonIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ImmatureCollectionView.CLASS);
    return mdClassIF.definesAttribute(TAXONID);
  }
  
  public void setTaxonId(String value)
  {
    if(value == null)
    {
      setValue(TAXONID, "");
    }
    else
    {
      setValue(TAXONID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ImmatureCollectionView get(String id)
  {
    return (ImmatureCollectionView) com.runwaysdk.business.View.get(id);
  }
  
  public dss.vector.solutions.entomology.CollectionContainerView[] applyWithContainers(dss.vector.solutions.entomology.CollectionContainerView[] containers)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.CollectionContainerView[] applyWithContainers(java.lang.String id, dss.vector.solutions.entomology.CollectionContainerView[] containers)
  {
    ImmatureCollectionView _instance = ImmatureCollectionView.get(id);
    return _instance.applyWithContainers(containers);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    ImmatureCollectionView _instance = ImmatureCollectionView.get(id);
    _instance.deleteConcrete();
  }
  
  public void deletePremise()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deletePremise(java.lang.String id)
  {
    ImmatureCollectionView _instance = ImmatureCollectionView.get(id);
    _instance.deletePremise();
  }
  
  public void deleteTaxon()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteTaxon(java.lang.String id)
  {
    ImmatureCollectionView _instance = ImmatureCollectionView.get(id);
    _instance.deleteTaxon();
  }
  
  public static dss.vector.solutions.entomology.ImmatureCollectionView getCollection(dss.vector.solutions.entomology.ImmatureCollectionView collection)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.entomology.CollectionContainerView[] getContainers()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.CollectionContainerView[] getContainers(java.lang.String id)
  {
    ImmatureCollectionView _instance = ImmatureCollectionView.get(id);
    return _instance.getContainers();
  }
  
  public static dss.vector.solutions.entomology.ImmatureCollectionViewQuery getMostRecent()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.entomology.ImmatureCollectionViewQuery searchCollections(dss.vector.solutions.entomology.ImmatureCollectionView collection, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.ImmatureCollectionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
