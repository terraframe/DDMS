package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -659552153)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonInterventionView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PersonInterventionViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.PersonInterventionView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVENTIONMETHOD = "interventionMethod";
  public static java.lang.String POINT = "point";
  public static java.lang.String VEHICLEDAYS = "vehicleDays";
  private static final long serialVersionUID = -659552153;
  
  public PersonInterventionViewBase()
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.PersonInterventionView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CONCRETEID);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.PersonInterventionView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getInterventionMethod()
  {
    if (getValue(INTERVENTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(INTERVENTIONMETHOD));
    }
  }
  
  public String getInterventionMethodId()
  {
    return getValue(INTERVENTIONMETHOD);
  }
  
  public void validateInterventionMethod()
  {
    this.validateAttribute(INTERVENTIONMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getInterventionMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.PersonInterventionView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(INTERVENTIONMETHOD);
  }
  
  public void setInterventionMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(INTERVENTIONMETHOD, "");
    }
    else
    {
      setValue(INTERVENTIONMETHOD, value.getId());
    }
  }
  
  public dss.vector.solutions.intervention.monitor.ControlIntervention getPoint()
  {
    if (getValue(POINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ControlIntervention.get(getValue(POINT));
    }
  }
  
  public String getPointId()
  {
    return getValue(POINT);
  }
  
  public void validatePoint()
  {
    this.validateAttribute(POINT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getPointMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.PersonInterventionView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(POINT);
  }
  
  public void setPoint(dss.vector.solutions.intervention.monitor.ControlIntervention value)
  {
    if(value == null)
    {
      setValue(POINT, "");
    }
    else
    {
      setValue(POINT, value.getId());
    }
  }
  
  public Integer getVehicleDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VEHICLEDAYS));
  }
  
  public void validateVehicleDays()
  {
    this.validateAttribute(VEHICLEDAYS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getVehicleDaysMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.PersonInterventionView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(VEHICLEDAYS);
  }
  
  public void setVehicleDays(Integer value)
  {
    if(value == null)
    {
      setValue(VEHICLEDAYS, "");
    }
    else
    {
      setValue(VEHICLEDAYS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PersonInterventionView get(String id)
  {
    return (PersonInterventionView) com.runwaysdk.business.View.get(id);
  }
  
  public dss.vector.solutions.intervention.monitor.PersonInterventionMethodView[] getInterventionMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.PersonInterventionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonInterventionMethodView[] getInterventionMethods(java.lang.String id)
  {
    PersonInterventionView _instance = PersonInterventionView.get(id);
    return _instance.getInterventionMethods();
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonInterventionMethodView[][] getInterventionMethodsForViews(dss.vector.solutions.intervention.monitor.PersonInterventionView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.PersonInterventionView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
