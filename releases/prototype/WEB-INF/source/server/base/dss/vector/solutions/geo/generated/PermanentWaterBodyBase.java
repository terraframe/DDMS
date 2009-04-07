package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PermanentWaterBody.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PermanentWaterBodyBase extends dss.vector.solutions.geo.generated.BreedingSite implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.PermanentWaterBody";
  public static java.lang.String POLYGON = "polygon";
  private static final long serialVersionUID = 1239075035255L;
  
  public PermanentWaterBodyBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.generated.PermanentWaterBody.CLASS);
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
  
  public static PermanentWaterBodyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PermanentWaterBodyQuery query = new PermanentWaterBodyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PermanentWaterBody get(String id)
  {
    return (PermanentWaterBody) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PermanentWaterBody lock(java.lang.String id)
  {
    PermanentWaterBody _instance = PermanentWaterBody.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PermanentWaterBody unlock(java.lang.String id)
  {
    PermanentWaterBody _instance = PermanentWaterBody.get(id);
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
