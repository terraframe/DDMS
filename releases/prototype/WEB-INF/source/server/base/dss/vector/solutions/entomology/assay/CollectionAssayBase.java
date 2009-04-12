package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class CollectionAssayBase extends dss.vector.solutions.entomology.assay.AbstractAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.CollectionAssay";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1239572445133L;
  
  public CollectionAssayBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.MosquitoCollection getCollection()
  {
    try
    {
      return dss.vector.solutions.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
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
  
  public Integer getExposureTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPOSURETIME));
  }
  
  public void validateExposureTime()
  {
    this.validateAttribute(EXPOSURETIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getExposureTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(EXPOSURETIME);
  }
  
  public void setExposureTime(Integer value)
  {
    if(value == null)
    {
      setValue(EXPOSURETIME, "");
    }
    else
    {
      setValue(EXPOSURETIME, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.mo.Generation getGeneration()
  {
    try
    {
      return dss.vector.solutions.mo.Generation.get(getValue(GENERATION));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateGeneration()
  {
    this.validateAttribute(GENERATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(GENERATION);
  }
  
  public void setGeneration(dss.vector.solutions.mo.Generation value)
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
  
  public dss.vector.solutions.mo.IdentificationMethod getIdentificationMethod()
  {
    try
    {
      return dss.vector.solutions.mo.IdentificationMethod.get(getValue(IDENTIFICATIONMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateIdentificationMethod()
  {
    this.validateAttribute(IDENTIFICATIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentificationMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(IDENTIFICATIONMETHOD);
  }
  
  public void setIdentificationMethod(dss.vector.solutions.mo.IdentificationMethod value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value.getId());
    }
  }
  
  public Integer getIntervalTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVALTIME));
  }
  
  public void validateIntervalTime()
  {
    this.validateAttribute(INTERVALTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIntervalTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(INTERVALTIME);
  }
  
  public void setIntervalTime(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVALTIME, "");
    }
    else
    {
      setValue(INTERVALTIME, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
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
  
  public Integer getQuantityTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYTESTED);
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.mo.ResistanceMethodology getTestMethod()
  {
    try
    {
      return dss.vector.solutions.mo.ResistanceMethodology.get(getValue(TESTMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.CollectionAssay.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.mo.ResistanceMethodology value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CollectionAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    CollectionAssayQuery query = new CollectionAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static CollectionAssay get(String id)
  {
    return (CollectionAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public java.lang.Double getKD95()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.CollectionAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Double getKD95(java.lang.String id)
  {
    CollectionAssay _instance = CollectionAssay.get(id);
    return _instance.getKD95();
  }
  
  public java.lang.Double getKD50()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.CollectionAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Double getKD50(java.lang.String id)
  {
    CollectionAssay _instance = CollectionAssay.get(id);
    return _instance.getKD50();
  }
  
  public static CollectionAssay lock(java.lang.String id)
  {
    CollectionAssay _instance = CollectionAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static CollectionAssay unlock(java.lang.String id)
  {
    CollectionAssay _instance = CollectionAssay.get(id);
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
