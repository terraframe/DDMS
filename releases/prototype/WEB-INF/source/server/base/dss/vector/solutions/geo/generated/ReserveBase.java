package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Reserve.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ReserveBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Reserve";
  public static java.lang.String POLYGON = "polygon";
  private static final long serialVersionUID = 1239075019732L;
  
  public ReserveBase()
  {
    super();
  }
  
  public com.vividsolutions.jts.geom.Polygon getPolygon()
  {
    return (com.vividsolutions.jts.geom.Polygon)getObjectValue(POLYGON);
  }
  
  public void validatePolygon()
  {
    this.validateAttribute(POLYGON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPolygonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.Reserve.CLASS);
    return mdClassIF.definesAttribute(POLYGON);
  }
  
  public void setPolygon(com.vividsolutions.jts.geom.Polygon value)
  {
    if(value == null)
    {
      setValue(POLYGON, "");
    }
    else
    {
      setValue(POLYGON, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ReserveQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ReserveQuery query = new ReserveQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Reserve get(String id)
  {
    return (Reserve) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Reserve lock(java.lang.String id)
  {
    Reserve _instance = Reserve.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Reserve unlock(java.lang.String id)
  {
    Reserve _instance = Reserve.get(id);
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
