package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NationalRoad.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class NationalRoadBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.NationalRoad";
  public static java.lang.String LINESTRING = "lineString";
  private static final long serialVersionUID = 1238826382514L;
  
  public NationalRoadBase()
  {
    super();
  }
  
  public com.vividsolutions.jts.geom.LineString getLineString()
  {
    return (com.vividsolutions.jts.geom.LineString)getObjectValue(LINESTRING);
  }
  
  public void validateLineString()
  {
    this.validateAttribute(LINESTRING);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLineStringMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.NationalRoad.CLASS);
    return mdClassIF.definesAttribute(LINESTRING);
  }
  
  public void setLineString(com.vividsolutions.jts.geom.LineString value)
  {
    if(value == null)
    {
      setValue(LINESTRING, "");
    }
    else
    {
      setValue(LINESTRING, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static NationalRoadQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    NationalRoadQuery query = new NationalRoadQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static NationalRoad get(String id)
  {
    return (NationalRoad) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static NationalRoad lock(java.lang.String id)
  {
    NationalRoad _instance = NationalRoad.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static NationalRoad unlock(java.lang.String id)
  {
    NationalRoad _instance = NationalRoad.get(id);
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
