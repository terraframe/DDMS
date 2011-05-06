package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1158018292)
public abstract class UnknownGeoParentWarningDTOBase extends com.runwaysdk.business.WarningDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.UnknownGeoParentWarning";
  private static final long serialVersionUID = 1158018292;
  
  public UnknownGeoParentWarningDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHILDNAME = "childName";
  public static java.lang.String DEFAULTPARENT = "defaultParent";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTNAME = "parentName";
  public static java.lang.String PARENTTYPE = "parentType";
  public String getChildName()
  {
    return getValue(CHILDNAME);
  }
  
  public void setChildName(String value)
  {
    if(value == null)
    {
      setValue(CHILDNAME, "");
    }
    else
    {
      setValue(CHILDNAME, value);
    }
  }
  
  public boolean isChildNameWritable()
  {
    return isWritable(CHILDNAME);
  }
  
  public boolean isChildNameReadable()
  {
    return isReadable(CHILDNAME);
  }
  
  public boolean isChildNameModified()
  {
    return isModified(CHILDNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getChildNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDNAME).getAttributeMdDTO();
  }
  
  public String getDefaultParent()
  {
    return getValue(DEFAULTPARENT);
  }
  
  public void setDefaultParent(String value)
  {
    if(value == null)
    {
      setValue(DEFAULTPARENT, "");
    }
    else
    {
      setValue(DEFAULTPARENT, value);
    }
  }
  
  public boolean isDefaultParentWritable()
  {
    return isWritable(DEFAULTPARENT);
  }
  
  public boolean isDefaultParentReadable()
  {
    return isReadable(DEFAULTPARENT);
  }
  
  public boolean isDefaultParentModified()
  {
    return isModified(DEFAULTPARENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDefaultParentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DEFAULTPARENT).getAttributeMdDTO();
  }
  
  public String getParentName()
  {
    return getValue(PARENTNAME);
  }
  
  public void setParentName(String value)
  {
    if(value == null)
    {
      setValue(PARENTNAME, "");
    }
    else
    {
      setValue(PARENTNAME, value);
    }
  }
  
  public boolean isParentNameWritable()
  {
    return isWritable(PARENTNAME);
  }
  
  public boolean isParentNameReadable()
  {
    return isReadable(PARENTNAME);
  }
  
  public boolean isParentNameModified()
  {
    return isModified(PARENTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTNAME).getAttributeMdDTO();
  }
  
  public String getParentType()
  {
    return getValue(PARENTTYPE);
  }
  
  public void setParentType(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPE, "");
    }
    else
    {
      setValue(PARENTTYPE, value);
    }
  }
  
  public boolean isParentTypeWritable()
  {
    return isWritable(PARENTTYPE);
  }
  
  public boolean isParentTypeReadable()
  {
    return isReadable(PARENTTYPE);
  }
  
  public boolean isParentTypeModified()
  {
    return isModified(PARENTTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTYPE).getAttributeMdDTO();
  }
  
}
