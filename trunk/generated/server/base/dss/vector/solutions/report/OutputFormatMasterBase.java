package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 1366199910)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OutputFormatMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class OutputFormatMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.OutputFormatMaster";
  private static final long serialVersionUID = 1366199910;
  
  public OutputFormatMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static OutputFormatMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    OutputFormatMasterQuery query = new OutputFormatMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static OutputFormatMaster get(String id)
  {
    return (OutputFormatMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static OutputFormatMaster getByKey(String key)
  {
    return (OutputFormatMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static OutputFormatMaster getEnumeration(String enumName)
  {
    return (OutputFormatMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.report.OutputFormatMaster.CLASS ,enumName);
  }
  
  public static OutputFormatMaster lock(java.lang.String id)
  {
    OutputFormatMaster _instance = OutputFormatMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static OutputFormatMaster unlock(java.lang.String id)
  {
    OutputFormatMaster _instance = OutputFormatMaster.get(id);
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