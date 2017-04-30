package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -2001641267)
public abstract class SavedSearchViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedSearchView";
  private static final long serialVersionUID = -2001641267;
  
  protected SavedSearchViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONFIG = "config";
  public static java.lang.String ID = "id";
  public static java.lang.String ISMATERIALIZED = "isMaterialized";
  public static java.lang.String KALEIDOSCOPES = "kaleidoscopes";
  public static java.lang.String OVERWRITE = "overwrite";
  public static java.lang.String QUERYNAME = "queryName";
  public static java.lang.String QUERYTYPE = "queryType";
  public static java.lang.String QUERYXML = "queryXml";
  public static java.lang.String SAVEDQUERYID = "savedQueryId";
  public String getConfig()
  {
    return getValue(CONFIG);
  }
  
  public void setConfig(String value)
  {
    if(value == null)
    {
      setValue(CONFIG, "");
    }
    else
    {
      setValue(CONFIG, value);
    }
  }
  
  public boolean isConfigWritable()
  {
    return isWritable(CONFIG);
  }
  
  public boolean isConfigReadable()
  {
    return isReadable(CONFIG);
  }
  
  public boolean isConfigModified()
  {
    return isModified(CONFIG);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getConfigMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CONFIG).getAttributeMdDTO();
  }
  
  public Boolean getIsMaterialized()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMATERIALIZED));
  }
  
  public void setIsMaterialized(Boolean value)
  {
    if(value == null)
    {
      setValue(ISMATERIALIZED, "");
    }
    else
    {
      setValue(ISMATERIALIZED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsMaterializedWritable()
  {
    return isWritable(ISMATERIALIZED);
  }
  
  public boolean isIsMaterializedReadable()
  {
    return isReadable(ISMATERIALIZED);
  }
  
  public boolean isIsMaterializedModified()
  {
    return isModified(ISMATERIALIZED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsMaterializedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISMATERIALIZED).getAttributeMdDTO();
  }
  
  public String getKaleidoscopes()
  {
    return getValue(KALEIDOSCOPES);
  }
  
  public void setKaleidoscopes(String value)
  {
    if(value == null)
    {
      setValue(KALEIDOSCOPES, "");
    }
    else
    {
      setValue(KALEIDOSCOPES, value);
    }
  }
  
  public boolean isKaleidoscopesWritable()
  {
    return isWritable(KALEIDOSCOPES);
  }
  
  public boolean isKaleidoscopesReadable()
  {
    return isReadable(KALEIDOSCOPES);
  }
  
  public boolean isKaleidoscopesModified()
  {
    return isModified(KALEIDOSCOPES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getKaleidoscopesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(KALEIDOSCOPES).getAttributeMdDTO();
  }
  
  public Boolean getOverwrite()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(OVERWRITE));
  }
  
  public void setOverwrite(Boolean value)
  {
    if(value == null)
    {
      setValue(OVERWRITE, "");
    }
    else
    {
      setValue(OVERWRITE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isOverwriteWritable()
  {
    return isWritable(OVERWRITE);
  }
  
  public boolean isOverwriteReadable()
  {
    return isReadable(OVERWRITE);
  }
  
  public boolean isOverwriteModified()
  {
    return isModified(OVERWRITE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getOverwriteMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(OVERWRITE).getAttributeMdDTO();
  }
  
  public String getQueryName()
  {
    return getValue(QUERYNAME);
  }
  
  public void setQueryName(String value)
  {
    if(value == null)
    {
      setValue(QUERYNAME, "");
    }
    else
    {
      setValue(QUERYNAME, value);
    }
  }
  
  public boolean isQueryNameWritable()
  {
    return isWritable(QUERYNAME);
  }
  
  public boolean isQueryNameReadable()
  {
    return isReadable(QUERYNAME);
  }
  
  public boolean isQueryNameModified()
  {
    return isModified(QUERYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getQueryNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYNAME).getAttributeMdDTO();
  }
  
  public String getQueryType()
  {
    return getValue(QUERYTYPE);
  }
  
  public void setQueryType(String value)
  {
    if(value == null)
    {
      setValue(QUERYTYPE, "");
    }
    else
    {
      setValue(QUERYTYPE, value);
    }
  }
  
  public boolean isQueryTypeWritable()
  {
    return isWritable(QUERYTYPE);
  }
  
  public boolean isQueryTypeReadable()
  {
    return isReadable(QUERYTYPE);
  }
  
  public boolean isQueryTypeModified()
  {
    return isModified(QUERYTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getQueryTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYTYPE).getAttributeMdDTO();
  }
  
  public String getQueryXml()
  {
    return getValue(QUERYXML);
  }
  
  public void setQueryXml(String value)
  {
    if(value == null)
    {
      setValue(QUERYXML, "");
    }
    else
    {
      setValue(QUERYXML, value);
    }
  }
  
  public boolean isQueryXmlWritable()
  {
    return isWritable(QUERYXML);
  }
  
  public boolean isQueryXmlReadable()
  {
    return isReadable(QUERYXML);
  }
  
  public boolean isQueryXmlModified()
  {
    return isModified(QUERYXML);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getQueryXmlMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(QUERYXML).getAttributeMdDTO();
  }
  
  public String getSavedQueryId()
  {
    return getValue(SAVEDQUERYID);
  }
  
  public void setSavedQueryId(String value)
  {
    if(value == null)
    {
      setValue(SAVEDQUERYID, "");
    }
    else
    {
      setValue(SAVEDQUERYID, value);
    }
  }
  
  public boolean isSavedQueryIdWritable()
  {
    return isWritable(SAVEDQUERYID);
  }
  
  public boolean isSavedQueryIdReadable()
  {
    return isReadable(SAVEDQUERYID);
  }
  
  public boolean isSavedQueryIdModified()
  {
    return isModified(SAVEDQUERYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSavedQueryIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SAVEDQUERYID).getAttributeMdDTO();
  }
  
  public static SavedSearchViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SavedSearchViewDTO) dto;
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
