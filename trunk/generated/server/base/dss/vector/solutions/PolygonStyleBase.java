package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 1560172117)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PolygonStyle.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PolygonStyleBase extends dss.vector.solutions.query.GeometryStyle implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PolygonStyle";
  public static java.lang.String FILL = "fill";
  private static final long serialVersionUID = 1560172117;
  
  public PolygonStyleBase()
  {
    super();
  }
  
  public String getFill()
  {
    return getValue(FILL);
  }
  
  public void validateFill()
  {
    this.validateAttribute(FILL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFillMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.PolygonStyle.CLASS);
    return mdClassIF.definesAttribute(FILL);
  }
  
  public void setFill(String value)
  {
    if(value == null)
    {
      setValue(FILL, "");
    }
    else
    {
      setValue(FILL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PolygonStyleQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PolygonStyleQuery query = new PolygonStyleQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PolygonStyle get(String id)
  {
    return (PolygonStyle) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PolygonStyle getByKey(String key)
  {
    return (PolygonStyle) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static PolygonStyle lock(java.lang.String id)
  {
    PolygonStyle _instance = PolygonStyle.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PolygonStyle unlock(java.lang.String id)
  {
    PolygonStyle _instance = PolygonStyle.get(id);
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
