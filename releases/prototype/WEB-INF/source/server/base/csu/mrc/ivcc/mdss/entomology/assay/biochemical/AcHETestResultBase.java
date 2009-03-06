package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AcHETestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AcHETestResultBase extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1236382964045L;
  
  public AcHETestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AcHETestResult.CLASS);
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
  
  public static AcHETestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AcHETestResultQuery query = new AcHETestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AcHETestResult get(String id)
  {
    return (AcHETestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AcHETestResult lock(java.lang.String id)
  {
    AcHETestResult _instance = AcHETestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AcHETestResult unlock(java.lang.String id)
  {
    AcHETestResult _instance = AcHETestResult.get(id);
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
