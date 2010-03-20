package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 28809685)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvacideInstanceView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LarvacideInstanceViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.LarvacideInstanceView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CONTROLID = "controlId";
  public static java.lang.String CONTROLMETHOD = "controlMethod";
  public static java.lang.String ID = "id";
  public static java.lang.String SUBSTANCE = "substance";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String UNIT = "unit";
  public static java.lang.String UNITSUSED = "unitsUsed";
  private static final long serialVersionUID = 28809685;
  
  public LarvacideInstanceViewBase()
  {
    super();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public String getControlId()
  {
    return getValue(CONTROLID);
  }
  
  public void validateControlId()
  {
    this.validateAttribute(CONTROLID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getControlIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(CONTROLID);
  }
  
  public void setControlId(String value)
  {
    if(value == null)
    {
      setValue(CONTROLID, "");
    }
    else
    {
      setValue(CONTROLID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getControlMethod()
  {
    if (getValue(CONTROLMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(CONTROLMETHOD));
    }
  }
  
  public void validateControlMethod()
  {
    this.validateAttribute(CONTROLMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getControlMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(CONTROLMETHOD);
  }
  
  public void setControlMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(CONTROLMETHOD, "");
    }
    else
    {
      setValue(CONTROLMETHOD, value.getId());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getSubstance()
  {
    if (getValue(SUBSTANCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SUBSTANCE));
    }
  }
  
  public void validateSubstance()
  {
    this.validateAttribute(SUBSTANCE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSubstanceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(SUBSTANCE);
  }
  
  public void setSubstance(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SUBSTANCE, "");
    }
    else
    {
      setValue(SUBSTANCE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getTarget()
  {
    if (getValue(TARGET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TARGET));
    }
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(TARGET);
  }
  
  public void setTarget(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value.getId());
    }
  }
  
  public Integer getTreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void validateTreated()
  {
    this.validateAttribute(TREATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTreatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(TREATED);
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getUnit()
  {
    if (getValue(UNIT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(UNIT));
    }
  }
  
  public void validateUnit()
  {
    this.validateAttribute(UNIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(UNIT);
  }
  
  public void setUnit(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value.getId());
    }
  }
  
  public Integer getUnitsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSUSED));
  }
  
  public void validateUnitsUsed()
  {
    this.validateAttribute(UNITSUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitsUsedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.LarvacideInstanceView.CLASS);
    return mdClassIF.definesAttribute(UNITSUSED);
  }
  
  public void setUnitsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSUSED, "");
    }
    else
    {
      setValue(UNITSUSED, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvacideInstanceView get(String id)
  {
    return (LarvacideInstanceView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideInstanceView[] applyAll(dss.vector.solutions.intervention.monitor.LarvacideInstanceView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.LarvacideInstanceView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.LarvacideInstanceView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    LarvacideInstanceView _instance = LarvacideInstanceView.get(id);
    _instance.deleteConcrete();
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
