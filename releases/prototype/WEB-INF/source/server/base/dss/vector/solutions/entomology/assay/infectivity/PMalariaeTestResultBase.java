package dss.vector.solutions.entomology.assay.infectivity;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PMalariaeTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PMalariaeTestResultBase extends dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.infectivity.PMalariaeTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1237311456036L;
  
  public PMalariaeTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.infectivity.PMalariaeTestResult.CLASS);
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
  
  public static PMalariaeTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PMalariaeTestResultQuery query = new PMalariaeTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PMalariaeTestResult get(String id)
  {
    return (PMalariaeTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PMalariaeTestResult lock(java.lang.String id)
  {
    PMalariaeTestResult _instance = PMalariaeTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PMalariaeTestResult unlock(java.lang.String id)
  {
    PMalariaeTestResult _instance = PMalariaeTestResult.get(id);
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
