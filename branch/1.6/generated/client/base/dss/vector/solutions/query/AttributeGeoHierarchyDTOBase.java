package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 310083601)
public abstract class AttributeGeoHierarchyDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.AttributeGeoHierarchy";
  private static final long serialVersionUID = 310083601;
  
  protected AttributeGeoHierarchyDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTEDISPLAYLABEL = "attributeDisplayLabel";
  public static java.lang.String GEOHIERARCHYDISPLAYLABEL = "geoHierarchyDisplayLabel";
  public static java.lang.String GEOHIERARCHYID = "geoHierarchyId";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public String getAttributeDisplayLabel()
  {
    return getValue(ATTRIBUTEDISPLAYLABEL);
  }
  
  public void setAttributeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDISPLAYLABEL, "");
    }
    else
    {
      setValue(ATTRIBUTEDISPLAYLABEL, value);
    }
  }
  
  public boolean isAttributeDisplayLabelWritable()
  {
    return isWritable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelReadable()
  {
    return isReadable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelModified()
  {
    return isModified(ATTRIBUTEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAttributeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getGeoHierarchyDisplayLabel()
  {
    return getValue(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public void setGeoHierarchyDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHYDISPLAYLABEL, "");
    }
    else
    {
      setValue(GEOHIERARCHYDISPLAYLABEL, value);
    }
  }
  
  public boolean isGeoHierarchyDisplayLabelWritable()
  {
    return isWritable(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public boolean isGeoHierarchyDisplayLabelReadable()
  {
    return isReadable(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public boolean isGeoHierarchyDisplayLabelModified()
  {
    return isModified(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoHierarchyDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOHIERARCHYDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getGeoHierarchyId()
  {
    return getValue(GEOHIERARCHYID);
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
  
  public boolean isGeoHierarchyIdWritable()
  {
    return isWritable(GEOHIERARCHYID);
  }
  
  public boolean isGeoHierarchyIdReadable()
  {
    return isReadable(GEOHIERARCHYID);
  }
  
  public boolean isGeoHierarchyIdModified()
  {
    return isModified(GEOHIERARCHYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoHierarchyIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOHIERARCHYID).getAttributeMdDTO();
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTEID);
  }
  
  public void setMdAttributeId(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTEID, "");
    }
    else
    {
      setValue(MDATTRIBUTEID, value);
    }
  }
  
  public boolean isMdAttributeIdWritable()
  {
    return isWritable(MDATTRIBUTEID);
  }
  
  public boolean isMdAttributeIdReadable()
  {
    return isReadable(MDATTRIBUTEID);
  }
  
  public boolean isMdAttributeIdModified()
  {
    return isModified(MDATTRIBUTEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdAttributeIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDATTRIBUTEID).getAttributeMdDTO();
  }
  
  public static AttributeGeoHierarchyDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AttributeGeoHierarchyDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
