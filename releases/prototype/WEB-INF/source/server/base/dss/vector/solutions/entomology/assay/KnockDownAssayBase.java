package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class KnockDownAssayBase extends dss.vector.solutions.entomology.assay.CollectionAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.KnockDownAssay";
  public static java.lang.String AGERANGE = "ageRange";
  private com.terraframe.mojo.business.Struct ageRange = null;
  
  public static java.lang.String FED = "fed";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String SEX = "sex";
  private static final long serialVersionUID = 1237311443868L;
  
  public KnockDownAssayBase()
  {
    super();
    ageRange = super.getStruct("ageRange");
  }
  
  public dss.vector.solutions.entomology.assay.AdultAgeRange getAgeRange()
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRange) ageRange;
  }
  
  public void validateAgeRange()
  {
    this.validateAttribute(AGERANGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(AGERANGE);
  }
  
  public Integer getFed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
  }
  
  public void validateFed()
  {
    this.validateAttribute(FED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(FED);
  }
  
  public void setFed(Integer value)
  {
    if(value == null)
    {
      setValue(FED, "");
    }
    else
    {
      setValue(FED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getGravid()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GRAVID));
  }
  
  public void validateGravid()
  {
    this.validateAttribute(GRAVID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGravidMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(GRAVID);
  }
  
  public void setGravid(Integer value)
  {
    if(value == null)
    {
      setValue(GRAVID, "");
    }
    else
    {
      setValue(GRAVID, java.lang.Integer.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.AssaySex> getSex()
  {
    return (java.util.List<dss.vector.solutions.entomology.AssaySex>) getEnumValues(SEX);
  }
  
  public void addSex(dss.vector.solutions.entomology.AssaySex value)
  {
    addEnumItem(SEX, value.getId());
  }
  
  public void removeSex(dss.vector.solutions.entomology.AssaySex value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static KnockDownAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayQuery query = new KnockDownAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static KnockDownAssay get(String id)
  {
    return (KnockDownAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static KnockDownAssay lock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static KnockDownAssay unlock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
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
