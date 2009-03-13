package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LarvaeDiscriminatingDoseAssayBase extends csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssay";
  public static java.lang.String AGERANGE = "ageRange";
  private static final long serialVersionUID = 1236982476581L;
  
  public LarvaeDiscriminatingDoseAssayBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange getAgeRange()
  {
    try
    {
      return csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.get(getValue(AGERANGE));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateAgeRange()
  {
    this.validateAttribute(AGERANGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(AGERANGE);
  }
  
  public void setAgeRange(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange value)
  {
    if(value == null)
    {
      setValue(AGERANGE, "");
    }
    else
    {
      setValue(AGERANGE, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvaeDiscriminatingDoseAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LarvaeDiscriminatingDoseAssayQuery query = new LarvaeDiscriminatingDoseAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LarvaeDiscriminatingDoseAssay get(String id)
  {
    return (LarvaeDiscriminatingDoseAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static LarvaeDiscriminatingDoseAssay lock(java.lang.String id)
  {
    LarvaeDiscriminatingDoseAssay _instance = LarvaeDiscriminatingDoseAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LarvaeDiscriminatingDoseAssay unlock(java.lang.String id)
  {
    LarvaeDiscriminatingDoseAssay _instance = LarvaeDiscriminatingDoseAssay.get(id);
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
