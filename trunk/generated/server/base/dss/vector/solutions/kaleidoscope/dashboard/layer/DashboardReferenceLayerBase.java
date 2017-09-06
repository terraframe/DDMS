package dss.vector.solutions.kaleidoscope.dashboard.layer;

@com.runwaysdk.business.ClassSignature(hash = 2000118215)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardReferenceLayer.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardReferenceLayerBase extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer";
  public static java.lang.String UNIVERSAL = "universal";
  private static final long serialVersionUID = 2000118215;
  
  public DashboardReferenceLayerBase()
  {
    super();
  }
  
  public dss.vector.solutions.geo.GeoHierarchy getUniversal()
  {
    if (getValue(UNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchy.get(getValue(UNIVERSAL));
    }
  }
  
  public String getUniversalId()
  {
    return getValue(UNIVERSAL);
  }
  
  public void validateUniversal()
  {
    this.validateAttribute(UNIVERSAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getUniversalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(UNIVERSAL);
  }
  
  public void setUniversal(dss.vector.solutions.geo.GeoHierarchy value)
  {
    if(value == null)
    {
      setValue(UNIVERSAL, "");
    }
    else
    {
      setValue(UNIVERSAL, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardReferenceLayerQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardReferenceLayerQuery query = new DashboardReferenceLayerQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DashboardReferenceLayer get(String id)
  {
    return (DashboardReferenceLayer) com.runwaysdk.business.Business.get(id);
  }
  
  public static DashboardReferenceLayer getByKey(String key)
  {
    return (DashboardReferenceLayer) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static java.lang.String getOptionsJSON(java.lang.String dashboardId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static DashboardReferenceLayer lock(java.lang.String id)
  {
    DashboardReferenceLayer _instance = DashboardReferenceLayer.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DashboardReferenceLayer unlock(java.lang.String id)
  {
    DashboardReferenceLayer _instance = DashboardReferenceLayer.get(id);
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
