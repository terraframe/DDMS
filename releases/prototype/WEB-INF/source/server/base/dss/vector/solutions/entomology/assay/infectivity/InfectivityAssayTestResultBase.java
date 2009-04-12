package dss.vector.solutions.entomology.assay.infectivity;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfectivityAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InfectivityAssayTestResultBase extends dss.vector.solutions.entomology.assay.AssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1239517568434L;
  
  public InfectivityAssayTestResultBase()
  {
    super();
  }
  
  public dss.vector.solutions.mo.InfectivityMethodology getTestMethod()
  {
    try
    {
      return dss.vector.solutions.mo.InfectivityMethodology.get(getValue(TESTMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.mo.InfectivityMethodology value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InfectivityAssayTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InfectivityAssayTestResultQuery query = new InfectivityAssayTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static InfectivityAssayTestResult get(String id)
  {
    return (InfectivityAssayTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult getInfectivityAssays()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static InfectivityAssayTestResult lock(java.lang.String id)
  {
    InfectivityAssayTestResult _instance = InfectivityAssayTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static InfectivityAssayTestResult unlock(java.lang.String id)
  {
    InfectivityAssayTestResult _instance = InfectivityAssayTestResult.get(id);
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
