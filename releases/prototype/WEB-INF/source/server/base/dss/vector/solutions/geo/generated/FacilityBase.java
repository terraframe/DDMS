package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Facility.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class FacilityBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Facility";
  public static java.lang.String POINT = "point";
  private static final long serialVersionUID = 1239658616535L;
  
  public FacilityBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.Facility.CLASS);
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
  
  public static FacilityQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    FacilityQuery query = new FacilityQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Facility get(String id)
  {
    return (Facility) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Facility lock(java.lang.String id)
  {
    Facility _instance = Facility.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Facility unlock(java.lang.String id)
  {
    Facility _instance = Facility.get(id);
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
