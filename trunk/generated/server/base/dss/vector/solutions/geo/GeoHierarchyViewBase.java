package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -1722177261)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoHierarchyView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoHierarchyViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoHierarchyView";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String GEOHIERARCHYID = "geoHierarchyId";
  public static java.lang.String ID = "id";
  public static java.lang.String ISADISPLAYLABEL = "isADisplayLabel";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String POPULATIONALLOWED = "populationAllowed";
  public static java.lang.String REFERENCEID = "referenceId";
  public static java.lang.String SPRAYTARGETALLOWED = "sprayTargetAllowed";
  public static java.lang.String TERM = "term";
  public static java.lang.String TYPENAME = "typeName";
  private static final long serialVersionUID = -1722177261;
  
  public GeoHierarchyViewBase()
  {
    super();
  }
  
  public String getDescription()
  {
    return getValue(DESCRIPTION);
  }
  
  public void validateDescription()
  {
    this.validateAttribute(DESCRIPTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDescriptionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(DESCRIPTION);
  }
  
  public void setDescription(String value)
  {
    if(value == null)
    {
      setValue(DESCRIPTION, "");
    }
    else
    {
      setValue(DESCRIPTION, value);
    }
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
  
  public String getGeoHierarchyId()
  {
    return getValue(GEOHIERARCHYID);
  }
  
  public void validateGeoHierarchyId()
  {
    this.validateAttribute(GEOHIERARCHYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoHierarchyIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(GEOHIERARCHYID);
  }
  
  public void setGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHYID, "");
    }
    else
    {
      setValue(GEOHIERARCHYID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getIsADisplayLabel()
  {
    return getValue(ISADISPLAYLABEL);
  }
  
  public void validateIsADisplayLabel()
  {
    this.validateAttribute(ISADISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIsADisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(ISADISPLAYLABEL);
  }
  
  public void setIsADisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(ISADISPLAYLABEL, "");
    }
    else
    {
      setValue(ISADISPLAYLABEL, value);
    }
  }
  
  public Boolean getPolitical()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void validatePolitical()
  {
    this.validateAttribute(POLITICAL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPoliticalMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(POLITICAL);
  }
  
  public void setPolitical(Boolean value)
  {
    if(value == null)
    {
      setValue(POLITICAL, "");
    }
    else
    {
      setValue(POLITICAL, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getPopulationAllowed()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POPULATIONALLOWED));
  }
  
  public void validatePopulationAllowed()
  {
    this.validateAttribute(POPULATIONALLOWED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPopulationAllowedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(POPULATIONALLOWED);
  }
  
  public void setPopulationAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(POPULATIONALLOWED, "");
    }
    else
    {
      setValue(POPULATIONALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public String getReferenceId()
  {
    return getValue(REFERENCEID);
  }
  
  public void validateReferenceId()
  {
    this.validateAttribute(REFERENCEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReferenceIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(REFERENCEID);
  }
  
  public void setReferenceId(String value)
  {
    if(value == null)
    {
      setValue(REFERENCEID, "");
    }
    else
    {
      setValue(REFERENCEID, value);
    }
  }
  
  public Boolean getSprayTargetAllowed()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SPRAYTARGETALLOWED));
  }
  
  public void validateSprayTargetAllowed()
  {
    this.validateAttribute(SPRAYTARGETALLOWED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayTargetAllowedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(SPRAYTARGETALLOWED);
  }
  
  public void setSprayTargetAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(SPRAYTARGETALLOWED, "");
    }
    else
    {
      setValue(SPRAYTARGETALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getTerm()
  {
    if (getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TERM));
    }
  }
  
  public void validateTerm()
  {
    this.validateAttribute(TERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public String getTypeName()
  {
    return getValue(TYPENAME);
  }
  
  public void validateTypeName()
  {
    this.validateAttribute(TYPENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
    return mdClassIF.definesAttribute(TYPENAME);
  }
  
  public void setTypeName(String value)
  {
    if(value == null)
    {
      setValue(TYPENAME, "");
    }
    else
    {
      setValue(TYPENAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoHierarchyView get(String id)
  {
    return (GeoHierarchyView) com.runwaysdk.business.View.get(id);
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
