package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AcHEWTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AcHEWTestResultBase extends dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.AcHEWTestResult";
  public static java.lang.String TESTRESULT = "testResult";
  private static final long serialVersionUID = 1239658627755L;
  
  public AcHEWTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.molecular.AcHEWTestResult.CLASS);
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
  
  public static AcHEWTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AcHEWTestResultQuery query = new AcHEWTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AcHEWTestResult get(String id)
  {
    return (AcHEWTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AcHEWTestResult lock(java.lang.String id)
  {
    AcHEWTestResult _instance = AcHEWTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AcHEWTestResult unlock(java.lang.String id)
  {
    AcHEWTestResult _instance = AcHEWTestResult.get(id);
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
