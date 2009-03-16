package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AdultDiscriminatingDoseAssayBase extends csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay";
  public static java.lang.String AGERANGE = "ageRange";
  private com.terraframe.mojo.business.Struct ageRange = null;
  
  public static java.lang.String FED = "fed";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String SEX = "sex";
  private static final long serialVersionUID = 1237219397048L;
  
  public AdultDiscriminatingDoseAssayBase()
  {
    super();
    ageRange = super.getStruct("ageRange");
  }
  
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRange getAgeRange()
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRange) ageRange;
  }
  
  public void validateAgeRange()
  {
    this.validateAttribute(AGERANGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
  public java.util.List<csu.mrc.ivcc.mdss.entomology.AssaySex> getSex()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.entomology.AssaySex>) getEnumValues(SEX);
  }
  
  public void addSex(csu.mrc.ivcc.mdss.entomology.AssaySex value)
  {
    addEnumItem(SEX, value.getId());
  }
  
  public void removeSex(csu.mrc.ivcc.mdss.entomology.AssaySex value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AdultDiscriminatingDoseAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AdultDiscriminatingDoseAssay get(String id)
  {
    return (AdultDiscriminatingDoseAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AdultDiscriminatingDoseAssay lock(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AdultDiscriminatingDoseAssay unlock(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
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
