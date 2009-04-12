package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UninterestingSpecieGroup.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class UninterestingSpecieGroupBase extends dss.vector.solutions.entomology.TrueSpecieEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.UninterestingSpecieGroup";
  public static java.lang.String QUANTITY = "quantity";
  private static final long serialVersionUID = 1239572467601L;
  
  public UninterestingSpecieGroupBase()
  {
    super();
  }
  
  public Integer getQuantity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void validateQuantity()
  {
    this.validateAttribute(QUANTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.UninterestingSpecieGroup.CLASS);
    return mdClassIF.definesAttribute(QUANTITY);
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
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
