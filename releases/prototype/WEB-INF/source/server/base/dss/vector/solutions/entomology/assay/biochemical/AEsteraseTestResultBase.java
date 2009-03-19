package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AEsteraseTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AEsteraseTestResultBase extends dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.biochemical.AEsteraseTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1237423102127L;
  
  public AEsteraseTestResultBase()
  {
    super();
  }
  
  public Integer getTestResult()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TESTRESULT));
  }
  
  public void validateTestResult()
  {
    this.validateAttribute(TESTRESULT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestResultMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.biochemical.AEsteraseTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTRESULT);
  }
  
  public void setTestResult(Integer value)
  {
    if(value == null)
    {
      setValue(TESTRESULT, "");
    }
    else
    {
      setValue(TESTRESULT, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AEsteraseTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AEsteraseTestResultQuery query = new AEsteraseTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AEsteraseTestResult get(String id)
  {
    return (AEsteraseTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AEsteraseTestResult lock(java.lang.String id)
  {
    AEsteraseTestResult _instance = AEsteraseTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AEsteraseTestResult unlock(java.lang.String id)
  {
    AEsteraseTestResult _instance = AEsteraseTestResult.get(id);
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
