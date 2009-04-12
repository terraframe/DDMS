package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityDefinition.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GeoEntityDefinitionBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityDefinition";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTGEOHIERARCHYID = "parentGeoHierarchyId";
  public static java.lang.String PARENTTYPEGEOHIERARCHYID = "parentTypeGeoHierarchyId";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String SPATIALTYPE = "spatialType";
  public static java.lang.String SPRAYTARGETALLOWED = "sprayTargetAllowed";
  public static java.lang.String TYPENAME = "typeName";
  private static final long serialVersionUID = 1239517511112L;
  
  public GeoEntityDefinitionBase()
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDescriptionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getParentGeoHierarchyId()
  {
    return getValue(PARENTGEOHIERARCHYID);
  }
  
  public void validateParentGeoHierarchyId()
  {
    this.validateAttribute(PARENTGEOHIERARCHYID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getParentGeoHierarchyIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
    return mdClassIF.definesAttribute(PARENTGEOHIERARCHYID);
  }
  
  public void setParentGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(PARENTGEOHIERARCHYID, "");
    }
    else
    {
      setValue(PARENTGEOHIERARCHYID, value);
    }
  }
  
  public String getParentTypeGeoHierarchyId()
  {
    return getValue(PARENTTYPEGEOHIERARCHYID);
  }
  
  public void validateParentTypeGeoHierarchyId()
  {
    this.validateAttribute(PARENTTYPEGEOHIERARCHYID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getParentTypeGeoHierarchyIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
    return mdClassIF.definesAttribute(PARENTTYPEGEOHIERARCHYID);
  }
  
  public void setParentTypeGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPEGEOHIERARCHYID, "");
    }
    else
    {
      setValue(PARENTTYPEGEOHIERARCHYID, value);
    }
  }
  
  public Boolean getPolitical()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void validatePolitical()
  {
    this.validateAttribute(POLITICAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPoliticalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.geo.SpatialTypes> getSpatialType()
  {
    return (java.util.List<dss.vector.solutions.geo.SpatialTypes>) getEnumValues(SPATIALTYPE);
  }
  
  public void addSpatialType(dss.vector.solutions.geo.SpatialTypes value)
  {
    addEnumItem(SPATIALTYPE, value.getId());
  }
  
  public void removeSpatialType(dss.vector.solutions.geo.SpatialTypes value)
  {
    removeEnumItem(SPATIALTYPE, value.getId());
  }
  
  public void clearSpatialType()
  {
    clearEnum(SPATIALTYPE);
  }
  
  public void validateSpatialType()
  {
    this.validateAttribute(SPATIALTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSpatialTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
    return mdClassIF.definesAttribute(SPATIALTYPE);
  }
  
  public Boolean getSprayTargetAllowed()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SPRAYTARGETALLOWED));
  }
  
  public void validateSprayTargetAllowed()
  {
    this.validateAttribute(SPRAYTARGETALLOWED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayTargetAllowedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
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
  
  public String getTypeName()
  {
    return getValue(TYPENAME);
  }
  
  public void validateTypeName()
  {
    this.validateAttribute(TYPENAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTypeNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoEntityDefinition.CLASS);
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
  
  public static GeoEntityDefinition get(String id)
  {
    return (GeoEntityDefinition) com.terraframe.mojo.business.View.get(id);
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
