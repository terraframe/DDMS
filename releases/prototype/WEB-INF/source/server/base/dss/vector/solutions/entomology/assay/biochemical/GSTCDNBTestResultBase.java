package dss.vector.solutions.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GSTCDNBTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GSTCDNBTestResultBase extends dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.biochemical.GSTCDNBTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1238826359067L;
  
  public GSTCDNBTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.biochemical.GSTCDNBTestResult.CLASS);
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
  
  public static GSTCDNBTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GSTCDNBTestResultQuery query = new GSTCDNBTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static GSTCDNBTestResult get(String id)
  {
    return (GSTCDNBTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static GSTCDNBTestResult lock(java.lang.String id)
  {
    GSTCDNBTestResult _instance = GSTCDNBTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GSTCDNBTestResult unlock(java.lang.String id)
  {
    GSTCDNBTestResult _instance = GSTCDNBTestResult.get(id);
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
