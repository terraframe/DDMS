package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LarvaeAssayBase extends dss.vector.solutions.entomology.assay.CollectionAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.LarvaeAssay";
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String STARTPOINT = "startPoint";
  private static final long serialVersionUID = 1239670225155L;
  
  public LarvaeAssayBase()
  {
    super();
  }
  
  public dss.vector.solutions.mo.LarvaeAge getEndPoint()
  {
    try
    {
      return dss.vector.solutions.mo.LarvaeAge.get(getValue(ENDPOINT));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateEndPoint()
  {
    this.validateAttribute(ENDPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEndPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeAssay.CLASS);
    return mdClassIF.definesAttribute(ENDPOINT);
  }
  
  public void setEndPoint(dss.vector.solutions.mo.LarvaeAge value)
  {
    if(value == null)
    {
      setValue(ENDPOINT, "");
    }
    else
    {
      setValue(ENDPOINT, value.getId());
    }
  }
  
  public dss.vector.solutions.mo.LarvaeAge getStartPoint()
  {
    try
    {
      return dss.vector.solutions.mo.LarvaeAge.get(getValue(STARTPOINT));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateStartPoint()
  {
    this.validateAttribute(STARTPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStartPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeAssay.CLASS);
    return mdClassIF.definesAttribute(STARTPOINT);
  }
  
  public void setStartPoint(dss.vector.solutions.mo.LarvaeAge value)
  {
    if(value == null)
    {
      setValue(STARTPOINT, "");
    }
    else
    {
      setValue(STARTPOINT, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvaeAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LarvaeAssayQuery query = new LarvaeAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LarvaeAssay get(String id)
  {
    return (LarvaeAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] getTestIntervals()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.LarvaeAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] getTestIntervals(java.lang.String id)
  {
    LarvaeAssay _instance = LarvaeAssay.get(id);
    return _instance.getTestIntervals();
  }
  
  public static LarvaeAssay lock(java.lang.String id)
  {
    LarvaeAssay _instance = LarvaeAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LarvaeAssay unlock(java.lang.String id)
  {
    LarvaeAssay _instance = LarvaeAssay.get(id);
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
