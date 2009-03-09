package csu.mrc.ivcc.mdss.entomology.assay.infectivity;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PFalciparumTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class PFalciparumTestResultBase extends csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.infectivity.PFalciparumTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1236612267225L;
  
  public PFalciparumTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.infectivity.PFalciparumTestResult.CLASS);
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
  
  public static PFalciparumTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PFalciparumTestResultQuery query = new PFalciparumTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static PFalciparumTestResult get(String id)
  {
    return (PFalciparumTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static PFalciparumTestResult lock(java.lang.String id)
  {
    PFalciparumTestResult _instance = PFalciparumTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static PFalciparumTestResult unlock(java.lang.String id)
  {
    PFalciparumTestResult _instance = PFalciparumTestResult.get(id);
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
