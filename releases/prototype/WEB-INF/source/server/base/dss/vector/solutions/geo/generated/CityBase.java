package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to City.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class CityBase extends dss.vector.solutions.geo.generated.PopulatedArea implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.City";
  public static java.lang.String POINT = "point";
  private static final long serialVersionUID = 1239670212076L;
  
  public CityBase()
  {
    super();
  }
  
  public com.vividsolutions.jts.geom.Point getPoint()
  {
    return (com.vividsolutions.jts.geom.Point)getObjectValue(POINT);
  }
  
  public void validatePoint()
  {
    this.validateAttribute(POINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.City.CLASS);
    return mdClassIF.definesAttribute(POINT);
  }
  
  public void setPoint(com.vividsolutions.jts.geom.Point value)
  {
    if(value == null)
    {
      setValue(POINT, "");
    }
    else
    {
      setValue(POINT, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CityQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    CityQuery query = new CityQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static City get(String id)
  {
    return (City) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static City lock(java.lang.String id)
  {
    City _instance = City.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static City unlock(java.lang.String id)
  {
    City _instance = City.get(id);
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
