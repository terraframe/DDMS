package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AcHESTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AcHESTestResultBase extends dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.AcHESTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1239075031043L;
  
  public AcHESTestResultBase()
  {
    super();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResult getTestResult()
  {
    try
    {
      return dss.vector.solutions.mo.MolecularAssayResult.get(getValue(TESTRESULT));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.molecular.AcHESTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTRESULT);
  }
  
  public void setTestResult(dss.vector.solutions.mo.MolecularAssayResult value)
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
  
  public static AcHESTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AcHESTestResultQuery query = new AcHESTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AcHESTestResult get(String id)
  {
    return (AcHESTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AcHESTestResult lock(java.lang.String id)
  {
    AcHESTestResult _instance = AcHESTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AcHESTestResult unlock(java.lang.String id)
  {
    AcHESTestResult _instance = AcHESTestResult.get(id);
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
