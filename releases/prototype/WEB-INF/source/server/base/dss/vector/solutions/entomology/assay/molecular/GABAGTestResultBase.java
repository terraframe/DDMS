package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GABAGTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GABAGTestResultBase extends dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.GABAGTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1238826371752L;
  
  public GABAGTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.molecular.GABAGTestResult.CLASS);
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
  
  public static GABAGTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GABAGTestResultQuery query = new GABAGTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static GABAGTestResult get(String id)
  {
    return (GABAGTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static GABAGTestResult lock(java.lang.String id)
  {
    GABAGTestResult _instance = GABAGTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GABAGTestResult unlock(java.lang.String id)
  {
    GABAGTestResult _instance = GABAGTestResult.get(id);
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
