package dss.vector.solutions.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MolecularAssayTestResultBase extends dss.vector.solutions.entomology.assay.AssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1237423120602L;
  
  public MolecularAssayTestResultBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.CLASS);
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
  
  public static MolecularAssayTestResultQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    MolecularAssayTestResultQuery query = new MolecularAssayTestResultQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static MolecularAssayTestResult get(String id)
  {
    return (MolecularAssayTestResult) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult getMolecularAssays()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static MolecularAssayTestResult lock(java.lang.String id)
  {
    MolecularAssayTestResult _instance = MolecularAssayTestResult.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static MolecularAssayTestResult unlock(java.lang.String id)
  {
    MolecularAssayTestResult _instance = MolecularAssayTestResult.get(id);
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
