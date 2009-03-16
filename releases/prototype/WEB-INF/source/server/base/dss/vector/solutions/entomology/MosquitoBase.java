package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Mosquito.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoBase extends dss.vector.solutions.entomology.TrueSpecieEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.Mosquito";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String SEX = "sex";
  public static java.lang.String TESTDATE = "testDate";
  private static final long serialVersionUID = 1237240910345L;
  
  public MosquitoBase()
  {
    super();
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.Mosquito.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.Mosquito.CLASS);
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.Sex> getSex()
  {
    return (java.util.List<dss.vector.solutions.entomology.Sex>) getEnumValues(SEX);
  }
  
  public void addSex(dss.vector.solutions.entomology.Sex value)
  {
    addEnumItem(SEX, value.getId());
  }
  
  public void removeSex(dss.vector.solutions.entomology.Sex value)
  {
    removeEnumItem(SEX, value.getId());
  }
  
  public void clearSex()
  {
    clearEnum(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.Mosquito.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void validateTestDate()
  {
    this.validateAttribute(TESTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.Mosquito.CLASS);
    return mdClassIF.definesAttribute(TESTDATE);
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MosquitoQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MosquitoQuery query = new MosquitoQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Mosquito get(String id)
  {
    return (Mosquito) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Mosquito lock(java.lang.String id)
  {
    Mosquito _instance = Mosquito.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Mosquito unlock(java.lang.String id)
  {
    Mosquito _instance = Mosquito.get(id);
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
