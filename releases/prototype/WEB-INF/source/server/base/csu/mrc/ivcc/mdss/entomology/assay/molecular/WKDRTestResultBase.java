package csu.mrc.ivcc.mdss.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WKDRTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class WKDRTestResultBase extends csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.molecular.WKDRTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1237219390817L;
  
  public WKDRTestResultBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResult getTestResult()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.MolecularAssayResult.get(getValue(TESTRESULT));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateTestResult()
  {
    this.validateAttribute(TESTRESULT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestResultMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.molecular.WKDRTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTRESULT);
  }
  
  public void setTestResult(csu.mrc.ivcc.mdss.mo.MolecularAssayResult value)
  {
    if(value == null)
    {
      setValue(TESTRESULT, "");
    }
    else
    {
      setValue(TESTRESULT, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static WKDRTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    WKDRTestResultQuery query = new WKDRTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static WKDRTestResult get(String id)
  {
    return (WKDRTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static WKDRTestResult lock(java.lang.String id)
  {
    WKDRTestResult _instance = WKDRTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static WKDRTestResult unlock(java.lang.String id)
  {
    WKDRTestResult _instance = WKDRTestResult.get(id);
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
