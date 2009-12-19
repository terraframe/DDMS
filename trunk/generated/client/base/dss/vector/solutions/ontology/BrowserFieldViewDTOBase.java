package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 654032779)
public abstract class BrowserFieldViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserFieldView";
  private static final long serialVersionUID = 654032779;
  
  protected BrowserFieldViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BROWSERFIELDID = "browserFieldId";
  public static java.lang.String DEFAULTVALUE = "defaultValue";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public static java.lang.String MDATTRIBUTELABEL = "mdAttributeLabel";
  public static java.lang.String MDCLASSID = "mdClassId";
  public static java.lang.String MDCLASSLABEL = "mdClassLabel";
  public String getBrowserFieldId()
  {
    return getValue(BROWSERFIELDID);
  }
  
  public void setBrowserFieldId(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELDID, "");
    }
    else
    {
      setValue(BROWSERFIELDID, value);
    }
  }
  
  public boolean isBrowserFieldIdWritable()
  {
    return isWritable(BROWSERFIELDID);
  }
  
  public boolean isBrowserFieldIdReadable()
  {
    return isReadable(BROWSERFIELDID);
  }
  
  public boolean isBrowserFieldIdModified()
  {
    return isModified(BROWSERFIELDID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrowserFieldIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERFIELDID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDefaultValue()
  {
    if(getValue(DEFAULTVALUE) == null || getValue(DEFAULTVALUE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DEFAULTVALUE));
    }
  }
  
  public void setDefaultValue(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTVALUE, "");
    }
    else
    {
      setValue(DEFAULTVALUE, value.getId());
    }
  }
  
  public boolean isDefaultValueWritable()
  {
    return isWritable(DEFAULTVALUE);
  }
  
  public boolean isDefaultValueReadable()
  {
    return isReadable(DEFAULTVALUE);
  }
  
  public boolean isDefaultValueModified()
  {
    return isModified(DEFAULTVALUE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDefaultValueMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTVALUE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMdAttributeIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDATTRIBUTEID).getAttributeMdDTO();
  }
  
  public String getMdAttributeLabel()
  {
    return getValue(MDATTRIBUTELABEL);
  }
  
  public void setMdAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTELABEL, "");
    }
    else
    {
      setValue(MDATTRIBUTELABEL, value);
    }
  }
  
  public boolean isMdAttributeLabelWritable()
  {
    return isWritable(MDATTRIBUTELABEL);
  }
  
  public boolean isMdAttributeLabelReadable()
  {
    return isReadable(MDATTRIBUTELABEL);
  }
  
  public boolean isMdAttributeLabelModified()
  {
    return isModified(MDATTRIBUTELABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMdAttributeLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getMdClassId()
  {
    return getValue(MDCLASSID);
  }
  
  public void setMdClassId(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSID, "");
    }
    else
    {
      setValue(MDCLASSID, value);
    }
  }
  
  public boolean isMdClassIdWritable()
  {
    return isWritable(MDCLASSID);
  }
  
  public boolean isMdClassIdReadable()
  {
    return isReadable(MDCLASSID);
  }
  
  public boolean isMdClassIdModified()
  {
    return isModified(MDCLASSID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMdClassIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDCLASSID).getAttributeMdDTO();
  }
  
  public String getMdClassLabel()
  {
    return getValue(MDCLASSLABEL);
  }
  
  public void setMdClassLabel(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSLABEL, "");
    }
    else
    {
      setValue(MDCLASSLABEL, value);
    }
  }
  
  public boolean isMdClassLabelWritable()
  {
    return isWritable(MDCLASSLABEL);
  }
  
  public boolean isMdClassLabelReadable()
  {
    return isReadable(MDCLASSLABEL);
  }
  
  public boolean isMdClassLabelModified()
  {
    return isModified(MDCLASSLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMdClassLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDCLASSLABEL).getAttributeMdDTO();
  }
  
  public static BrowserFieldViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (BrowserFieldViewDTO) dto;
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
