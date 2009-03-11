package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ADDATestIntervalView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ADDATestIntervalViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView";
  public static java.lang.String ASSAYID = "assayId";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVALID = "intervalId";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public static java.lang.String KNOCKEDDOWN = "knockedDown";
  public static java.lang.String PERIOD = "period";
  private static final long serialVersionUID = 1236803176710L;
  
  public ADDATestIntervalViewBase()
  {
    super();
  }
  
  public String getAssayId()
  {
    return getValue(ASSAYID);
  }
  
  public void validateAssayId()
  {
    this.validateAttribute(ASSAYID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAssayIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
    return mdClassIF.definesAttribute(ASSAYID);
  }
  
  public void setAssayId(String value)
  {
    if(value == null)
    {
      setValue(ASSAYID, "");
    }
    else
    {
      setValue(ASSAYID, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
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
  
  public Integer getKnockedDown()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(KNOCKEDDOWN));
  }
  
  public void validateKnockedDown()
  {
    this.validateAttribute(KNOCKEDDOWN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKnockedDownMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
    return mdClassIF.definesAttribute(KNOCKEDDOWN);
  }
  
  public void setKnockedDown(Integer value)
  {
    if(value == null)
    {
      setValue(KNOCKEDDOWN, "");
    }
    else
    {
      setValue(KNOCKEDDOWN, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView.CLASS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ADDATestIntervalView get(String id)
  {
    return (ADDATestIntervalView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] saveAll(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] array)
  {
    return null;
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
