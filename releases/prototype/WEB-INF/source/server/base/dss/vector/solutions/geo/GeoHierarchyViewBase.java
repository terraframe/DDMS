package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoHierarchyView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class GeoHierarchyViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoHierarchyView";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String GEOHIERARCHYID = "geoHierarchyId";
  public static java.lang.String ID = "id";
  public static java.lang.String ISADISPLAYLABEL = "isADisplayLabel";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String REFERENCEID = "referenceId";
  public static java.lang.String SPRAYTARGETALLOWED = "sprayTargetAllowed";
  public static java.lang.String TYPENAME = "typeName";
  private static final long serialVersionUID = 1239517514907L;
  
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDescriptionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoHierarchyIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsADisplayLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void validatePolitical()
  {
    this.validateAttribute(POLITICAL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPoliticalMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
  
  public String getReferenceId()
  {
    return getValue(REFERENCEID);
  }
  
  public void validateReferenceId()
  {
    this.validateAttribute(REFERENCEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferenceIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SPRAYTARGETALLOWED));
  }
  
  public void validateSprayTargetAllowed()
  {
    this.validateAttribute(SPRAYTARGETALLOWED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayTargetAllowedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoHierarchyView.CLASS);
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
    return (GeoHierarchyView) com.terraframe.mojo.business.View.get(id);
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
