package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeTestIntervalView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LarvaeTestIntervalViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.LarvaeTestIntervalView";
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVALID = "intervalId";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  private static final long serialVersionUID = 1239074993689L;
  
  public LarvaeTestIntervalViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.assay.LarvaeAssay getAssay()
  {
    try
    {
      return dss.vector.solutions.entomology.assay.LarvaeAssay.get(getValue(ASSAY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateAssay()
  {
    this.validateAttribute(ASSAY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAssayMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(ASSAY);
  }
  
  public void setAssay(dss.vector.solutions.entomology.assay.LarvaeAssay value)
  {
    if(value == null)
    {
      setValue(ASSAY, "");
    }
    else
    {
      setValue(ASSAY, value.getId());
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getIntervalId()
  {
    return getValue(INTERVALID);
  }
  
  public void validateIntervalId()
  {
    this.validateAttribute(INTERVALID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIntervalIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(INTERVALID);
  }
  
  public void setIntervalId(String value)
  {
    if(value == null)
    {
      setValue(INTERVALID, "");
    }
    else
    {
      setValue(INTERVALID, value);
    }
  }
  
  public Integer getIntervalTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVALTIME));
  }
  
  public void validateIntervalTime()
  {
    this.validateAttribute(INTERVALTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIntervalTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(INTERVALTIME);
  }
  
  public void setIntervalTime(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVALTIME, "");
    }
    else
    {
      setValue(INTERVALTIME, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeriodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(PERIOD);
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityDead()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void validateQuantityDead()
  {
    this.validateAttribute(QUANTITYDEAD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityDeadMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.CLASS);
    return mdClassIF.definesAttribute(QUANTITYDEAD);
  }
  
  public void setQuantityDead(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYDEAD, "");
    }
    else
    {
      setValue(QUANTITYDEAD, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvaeTestIntervalView get(String id)
  {
    return (LarvaeTestIntervalView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] saveAll(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] array)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.LarvaeTestIntervalView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: Test Interval View";
    }
    else
    {
      return super.toString();
    }
  }
}
