package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1413263693)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NozzleView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class NozzleViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.NozzleView";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ID = "id";
  public static java.lang.String NOZZLEID = "nozzleId";
  public static java.lang.String RATIO = "ratio";
  private static final long serialVersionUID = 1413263693;
  
  public NozzleViewBase()
  {
    super();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NozzleView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public Boolean getEnabled()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
  }
  
  public void validateEnabled()
  {
    this.validateAttribute(ENABLED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEnabledMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NozzleView.CLASS);
    return mdClassIF.definesAttribute(ENABLED);
  }
  
  public void setEnabled(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLED, "");
    }
    else
    {
      setValue(ENABLED, java.lang.Boolean.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NozzleView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getNozzleId()
  {
    return getValue(NOZZLEID);
  }
  
  public void validateNozzleId()
  {
    this.validateAttribute(NOZZLEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNozzleIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NozzleView.CLASS);
    return mdClassIF.definesAttribute(NOZZLEID);
  }
  
  public void setNozzleId(String value)
  {
    if(value == null)
    {
      setValue(NOZZLEID, "");
    }
    else
    {
      setValue(NOZZLEID, value);
    }
  }
  
  public java.math.BigDecimal getRatio()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(RATIO));
  }
  
  public void validateRatio()
  {
    this.validateAttribute(RATIO);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRatioMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NozzleView.CLASS);
    return mdClassIF.definesAttribute(RATIO);
  }
  
  public void setRatio(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(RATIO, "");
    }
    else
    {
      setValue(RATIO, value.toString());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static NozzleView get(String id)
  {
    return (NozzleView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.NozzleView[] applyAll(dss.vector.solutions.irs.NozzleView[] nozzles)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.NozzleView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.NozzleView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    NozzleView _instance = NozzleView.get(id);
    _instance.deleteConcrete();
  }
  
  public static dss.vector.solutions.irs.NozzleView[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.NozzleView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.NozzleView[] getAllActive()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.NozzleView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
