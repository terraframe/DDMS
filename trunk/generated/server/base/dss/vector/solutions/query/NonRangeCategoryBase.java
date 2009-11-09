package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1855600883)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NonRangeCategory.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class NonRangeCategoryBase extends dss.vector.solutions.query.AbstractCategory implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.NonRangeCategory";
  public static java.lang.String EXACTVALUE = "exactValue";
  private static final long serialVersionUID = -1855600883;
  
  public NonRangeCategoryBase()
  {
    super();
  }
  
  public Double getExactValue()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(EXACTVALUE));
  }
  
  public void validateExactValue()
  {
    this.validateAttribute(EXACTVALUE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getExactValueMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.NonRangeCategory.CLASS);
    return mdClassIF.definesAttribute(EXACTVALUE);
  }
  
  public void setExactValue(Double value)
  {
    if(value == null)
    {
      setValue(EXACTVALUE, "");
    }
    else
    {
      setValue(EXACTVALUE, java.lang.Double.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static NonRangeCategoryQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    NonRangeCategoryQuery query = new NonRangeCategoryQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static NonRangeCategory get(String id)
  {
    return (NonRangeCategory) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static NonRangeCategory getByKey(String key)
  {
    return (NonRangeCategory) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static NonRangeCategory lock(java.lang.String id)
  {
    NonRangeCategory _instance = NonRangeCategory.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static NonRangeCategory unlock(java.lang.String id)
  {
    NonRangeCategory _instance = NonRangeCategory.get(id);
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
