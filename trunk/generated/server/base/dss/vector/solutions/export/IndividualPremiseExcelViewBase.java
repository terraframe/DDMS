package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 68565469)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualPremiseExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IndividualPremiseExcelViewBase extends dss.vector.solutions.export.ControlInterventionExcelView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualPremiseExcelView";
  public static java.lang.String PREMISEGEOENTITY = "premiseGeoEntity";
  public static java.lang.String REASONSFORNOTTREATED = "reasonsForNotTreated";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String VISITED = "visited";
  private static final long serialVersionUID = 68565469;
  
  public IndividualPremiseExcelViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getPremiseGeoEntity()
  {
    if (getValue(PREMISEGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(PREMISEGEOENTITY));
    }
  }
  
  public String getPremiseGeoEntityId()
  {
    return getValue(PREMISEGEOENTITY);
  }
  
  public void validatePremiseGeoEntity()
  {
    this.validateAttribute(PREMISEGEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getPremiseGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualPremiseExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(PREMISEGEOENTITY);
  }
  
  public void setPremiseGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(PREMISEGEOENTITY, "");
    }
    else
    {
      setValue(PREMISEGEOENTITY, value.getId());
    }
  }
  
  public String getReasonsForNotTreated()
  {
    return getValue(REASONSFORNOTTREATED);
  }
  
  public void validateReasonsForNotTreated()
  {
    this.validateAttribute(REASONSFORNOTTREATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getReasonsForNotTreatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualPremiseExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(REASONSFORNOTTREATED);
  }
  
  public void setReasonsForNotTreated(String value)
  {
    if(value == null)
    {
      setValue(REASONSFORNOTTREATED, "");
    }
    else
    {
      setValue(REASONSFORNOTTREATED, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualPremiseExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualPremiseExcelView.CLASS);
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
  
  public static IndividualPremiseExcelView get(String id)
  {
    return (IndividualPremiseExcelView) com.runwaysdk.business.View.get(id);
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
