package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to P450TestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class P450TestResultBase extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.biochemical.P450TestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1238027420712L;
  
  public P450TestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.biochemical.P450TestResult.CLASS);
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
  
  public static P450TestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    P450TestResultQuery query = new P450TestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static P450TestResult get(String id)
  {
    return (P450TestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static P450TestResult lock(java.lang.String id)
  {
    P450TestResult _instance = P450TestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static P450TestResult unlock(java.lang.String id)
  {
    P450TestResult _instance = P450TestResult.get(id);
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
