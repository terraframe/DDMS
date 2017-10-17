package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -1463139324)
public abstract class UnknownTermDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.UnknownTerm";
  private static final long serialVersionUID = -1463139324;
  
  protected UnknownTermDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String BROWSERATTRIBUTE = "browserAttribute";
  public static java.lang.String BROWSERCLASS = "browserClass";
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMS = "synonyms";
  public static java.lang.String TERMNAME = "termName";
  public String getAttributeLabel()
  {
    return getValue(ATTRIBUTELABEL);
  }
  
  public void setAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTELABEL, "");
    }
    else
    {
      setValue(ATTRIBUTELABEL, value);
    }
  }
  
  public boolean isAttributeLabelWritable()
  {
    return isWritable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelReadable()
  {
    return isReadable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelModified()
  {
    return isModified(ATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getBrowserAttribute()
  {
    return getValue(BROWSERATTRIBUTE);
  }
  
  public void setBrowserAttribute(String value)
  {
    if(value == null)
    {
      setValue(BROWSERATTRIBUTE, "");
    }
    else
    {
      setValue(BROWSERATTRIBUTE, value);
    }
  }
  
  public boolean isBrowserAttributeWritable()
  {
    return isWritable(BROWSERATTRIBUTE);
  }
  
  public boolean isBrowserAttributeReadable()
  {
    return isReadable(BROWSERATTRIBUTE);
  }
  
  public boolean isBrowserAttributeModified()
  {
    return isModified(BROWSERATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERATTRIBUTE).getAttributeMdDTO();
  }
  
  public String getBrowserClass()
  {
    return getValue(BROWSERCLASS);
  }
  
  public void setBrowserClass(String value)
  {
    if(value == null)
    {
      setValue(BROWSERCLASS, "");
    }
    else
    {
      setValue(BROWSERCLASS, value);
    }
  }
  
  public boolean isBrowserClassWritable()
  {
    return isWritable(BROWSERCLASS);
  }
  
  public boolean isBrowserClassReadable()
  {
    return isReadable(BROWSERCLASS);
  }
  
  public boolean isBrowserClassModified()
  {
    return isModified(BROWSERCLASS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserClassMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERCLASS).getAttributeMdDTO();
  }
  
  public String getSynonyms()
  {
    return getValue(SYNONYMS);
  }
  
  public void setSynonyms(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMS, "");
    }
    else
    {
      setValue(SYNONYMS, value);
    }
  }
  
  public boolean isSynonymsWritable()
  {
    return isWritable(SYNONYMS);
  }
  
  public boolean isSynonymsReadable()
  {
    return isReadable(SYNONYMS);
  }
  
  public boolean isSynonymsModified()
  {
    return isModified(SYNONYMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSynonymsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SYNONYMS).getAttributeMdDTO();
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public boolean isTermNameWritable()
  {
    return isWritable(TERMNAME);
  }
  
  public boolean isTermNameReadable()
  {
    return isReadable(TERMNAME);
  }
  
  public boolean isTermNameModified()
  {
    return isModified(TERMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMNAME).getAttributeMdDTO();
  }
  
  public static UnknownTermDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (UnknownTermDTO) dto;
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
