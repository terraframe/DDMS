package csu.mrc.ivcc.mdss.entomology.assay.molecular;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssayTestResult.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MolecularAssayTestResultBase extends csu.mrc.ivcc.mdss.entomology.assay.AssayTestResult implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1236382959011L;
  
  public MolecularAssayTestResultBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodology getTestMethod()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.InsecticideMethodology.get(getValue(TESTMETHOD));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(csu.mrc.ivcc.mdss.mo.InsecticideMethodology value)
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
  
  public static csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult getMolecularAssays()
  {
    return null;
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
