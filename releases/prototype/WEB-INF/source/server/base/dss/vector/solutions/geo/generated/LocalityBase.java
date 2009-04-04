package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Locality.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LocalityBase extends dss.vector.solutions.geo.generated.GeoEntity implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.Locality";
  public static java.lang.String MULTIPOLYGON = "multiPolygon";
  private static final long serialVersionUID = 1238826361644L;
  
  public LocalityBase()
  {
    super();
  }
  
  public com.vividsolutions.jts.geom.MultiPolygon getMultiPolygon()
  {
    return (com.vividsolutions.jts.geom.MultiPolygon)getObjectValue(MULTIPOLYGON);
  }
  
  public void validateMultiPolygon()
  {
    this.validateAttribute(MULTIPOLYGON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMultiPolygonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.Locality.CLASS);
    return mdClassIF.definesAttribute(MULTIPOLYGON);
  }
  
  public void setMultiPolygon(com.vividsolutions.jts.geom.MultiPolygon value)
  {
    if(value == null)
    {
      setValue(MULTIPOLYGON, "");
    }
    else
    {
      setValue(MULTIPOLYGON, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LocalityQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LocalityQuery query = new LocalityQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Locality get(String id)
  {
    return (Locality) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Locality lock(java.lang.String id)
  {
    Locality _instance = Locality.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Locality unlock(java.lang.String id)
  {
    Locality _instance = Locality.get(id);
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
