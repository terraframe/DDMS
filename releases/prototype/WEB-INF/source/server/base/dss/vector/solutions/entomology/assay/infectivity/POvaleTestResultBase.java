package dss.vector.solutions.entomology.assay.infectivity;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to POvaleTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class POvaleTestResultBase extends dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1238027425883L;
  
  public POvaleTestResultBase()
  {
    super();
  }
  
  public Boolean getTestResult()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(TESTRESULT));
  }
  
  public void validateTestResult()
  {
    this.validateAttribute(TESTRESULT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestResultMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTRESULT);
  }
  
  public void setTestResult(Boolean value)
  {
    if(value == null)
    {
      setValue(TESTRESULT, "");
    }
    else
    {
      setValue(TESTRESULT, java.lang.Boolean.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static POvaleTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    POvaleTestResultQuery query = new POvaleTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static POvaleTestResult get(String id)
  {
    return (POvaleTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static POvaleTestResult lock(java.lang.String id)
  {
    POvaleTestResult _instance = POvaleTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static POvaleTestResult unlock(java.lang.String id)
  {
    POvaleTestResult _instance = POvaleTestResult.get(id);
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
