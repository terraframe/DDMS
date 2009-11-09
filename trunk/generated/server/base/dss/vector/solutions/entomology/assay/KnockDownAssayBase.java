package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 2007890233)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class KnockDownAssayBase extends dss.vector.solutions.entomology.assay.AdultAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.KnockDownAssay";
  public static java.lang.String KD50 = "kd50";
  public static java.lang.String KD95 = "kd95";
  private static final long serialVersionUID = 2007890233;
  
  public KnockDownAssayBase()
  {
    super();
  }
  
  public Double getKd50()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD50));
  }
  
  public void validateKd50()
  {
    this.validateAttribute(KD50);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKd50Md()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(KD50);
  }
  
  public void setKd50(Double value)
  {
    if(value == null)
    {
      setValue(KD50, "");
    }
    else
    {
      setValue(KD50, java.lang.Double.toString(value));
    }
  }
  
  public Double getKd95()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD95));
  }
  
  public void validateKd95()
  {
    this.validateAttribute(KD95);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKd95Md()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS);
    return mdClassIF.definesAttribute(KD95);
  }
  
  public void setKd95(Double value)
  {
    if(value == null)
    {
      setValue(KD95, "");
    }
    else
    {
      setValue(KD95, java.lang.Double.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static KnockDownAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayQuery query = new KnockDownAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static KnockDownAssay get(String id)
  {
    return (KnockDownAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static KnockDownAssay getByKey(String key)
  {
    return (KnockDownAssay) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static KnockDownAssay lock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static KnockDownAssay unlock(java.lang.String id)
  {
    KnockDownAssay _instance = KnockDownAssay.get(id);
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
