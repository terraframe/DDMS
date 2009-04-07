package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetSiteAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TargetSiteAssayTestResultBase extends dss.vector.solutions.entomology.assay.AssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1239075040416L;
  
  public TargetSiteAssayTestResultBase()
  {
    super();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodology getTestMethod()
  {
    try
    {
      return dss.vector.solutions.mo.InsecticideMethodology.get(getValue(TESTMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.mo.InsecticideMethodology value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TargetSiteAssayTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TargetSiteAssayTestResultQuery query = new TargetSiteAssayTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TargetSiteAssayTestResult get(String id)
  {
    return (TargetSiteAssayTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static TargetSiteAssayTestResult lock(java.lang.String id)
  {
    TargetSiteAssayTestResult _instance = TargetSiteAssayTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TargetSiteAssayTestResult unlock(java.lang.String id)
  {
    TargetSiteAssayTestResult _instance = TargetSiteAssayTestResult.get(id);
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
