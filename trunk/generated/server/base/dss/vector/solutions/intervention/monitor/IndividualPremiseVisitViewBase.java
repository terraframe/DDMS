package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1612489309)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualPremiseVisitView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IndividualPremiseVisitViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVENTIONMETHOD = "interventionMethod";
  public static java.lang.String POINT = "point";
  public static java.lang.String REASONSFORNOTTREATED = "reasonsForNotTreated";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String VISITED = "visited";
  private static final long serialVersionUID = -1612489309;
  
  public IndividualPremiseVisitViewBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
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
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void validateEntityLabel()
  {
    this.validateAttribute(ENTITYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getEntityLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
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
  
  public dss.vector.solutions.ontology.Term getReasonsForNotTreated()
  {
    if (getValue(REASONSFORNOTTREATED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(REASONSFORNOTTREATED));
    }
  }
  
  public String getReasonsForNotTreatedId()
  {
    return getValue(REASONSFORNOTTREATED);
  }
  
  public void validateReasonsForNotTreated()
  {
    this.validateAttribute(REASONSFORNOTTREATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getReasonsForNotTreatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(REASONSFORNOTTREATED);
  }
  
  public void setReasonsForNotTreated(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(REASONSFORNOTTREATED, "");
    }
    else
    {
      setValue(REASONSFORNOTTREATED, value.getId());
    }
  }
  
  public Boolean getTreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void validateTreated()
  {
    this.validateAttribute(TREATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getTreatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(TREATED);
  }
  
  public void setTreated(Boolean value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getVisited()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VISITED));
  }
  
  public void validateVisited()
  {
    this.validateAttribute(VISITED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getVisitedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(VISITED);
  }
  
  public void setVisited(Boolean value)
  {
    if(value == null)
    {
      setValue(VISITED, "");
    }
    else
    {
      setValue(VISITED, java.lang.Boolean.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualPremiseVisitView get(String id)
  {
    return (IndividualPremiseVisitView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView[] applyAll(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView[] views, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView[][] methods)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView[] getInterventionMethods()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView[] getInterventionMethods(java.lang.String id)
  {
    IndividualPremiseVisitView _instance = IndividualPremiseVisitView.get(id);
    return _instance.getInterventionMethods();
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView[][] getInterventionMethodsForViews(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView[] getViews(dss.vector.solutions.intervention.monitor.ControlInterventionView criteria)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView.java";
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
