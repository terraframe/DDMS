package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UninterestingSpecieGroup.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class UninterestingSpecieGroupBase extends mdss.entomology.TrueSpecieEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.UninterestingSpecieGroup";
  public static java.lang.String QUANITY = "quanity";
  private static final long serialVersionUID = 1234203357538L;
  
  public UninterestingSpecieGroupBase()
  {
    super();
  }
  
  public Integer getQuanity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANITY));
  }
  
  public void validateQuanity()
  {
    this.validateAttribute(QUANITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuanityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(mdss.entomology.UninterestingSpecieGroup.CLASS);
    return mdClassIF.definesAttribute(QUANITY);
  }
  
  public void setQuanity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANITY, "");
    }
    else
    {
      setValue(QUANITY, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static UninterestingSpecieGroupQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    UninterestingSpecieGroupQuery query = new UninterestingSpecieGroupQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static UninterestingSpecieGroup get(String id)
  {
    return (UninterestingSpecieGroup) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static UninterestingSpecieGroup lock(java.lang.String id)
  {
    UninterestingSpecieGroup _instance = UninterestingSpecieGroup.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static UninterestingSpecieGroup unlock(java.lang.String id)
  {
    UninterestingSpecieGroup _instance = UninterestingSpecieGroup.get(id);
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
