package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PopulatedArea.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PopulatedAreaBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.PopulatedArea";
  private static final long serialVersionUID = 1238826346119L;
  
  public PopulatedAreaBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PopulatedAreaQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PopulatedAreaQuery query = new PopulatedAreaQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PopulatedArea get(String id)
  {
    return (PopulatedArea) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PopulatedArea lock(java.lang.String id)
  {
    PopulatedArea _instance = PopulatedArea.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PopulatedArea unlock(java.lang.String id)
  {
    PopulatedArea _instance = PopulatedArea.get(id);
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
