package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -210231331)
public abstract class FreeTextDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.FreeText";
  private static final long serialVersionUID = -210231331;
  
  protected FreeTextDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CUSTOMTEXT = "customText";
  public static java.lang.String ID = "id";
  public static java.lang.String TEXTFONTFAMILY = "textFontFamily";
  public static java.lang.String TEXTFONTFILL = "textFontFill";
  public static java.lang.String TEXTFONTSIZE = "textFontSize";
  public static java.lang.String TEXTFONTSTYLES = "textFontStyles";
  public String getCustomText()
  {
    return getValue(CUSTOMTEXT);
  }
  
  public void setCustomText(String value)
  {
    if(value == null)
    {
      setValue(CUSTOMTEXT, "");
    }
    else
    {
      setValue(CUSTOMTEXT, value);
    }
  }
  
  public boolean isCustomTextWritable()
  {
    return isWritable(CUSTOMTEXT);
  }
  
  public boolean isCustomTextReadable()
  {
    return isReadable(CUSTOMTEXT);
  }
  
  public boolean isCustomTextModified()
  {
    return isModified(CUSTOMTEXT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCustomTextMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CUSTOMTEXT).getAttributeMdDTO();
  }
  
  public String getTextFontFamily()
  {
    return getValue(TEXTFONTFAMILY);
  }
  
  public void setTextFontFamily(String value)
  {
    if(value == null)
    {
      setValue(TEXTFONTFAMILY, "");
    }
    else
    {
      setValue(TEXTFONTFAMILY, value);
    }
  }
  
  public boolean isTextFontFamilyWritable()
  {
    return isWritable(TEXTFONTFAMILY);
  }
  
  public boolean isTextFontFamilyReadable()
  {
    return isReadable(TEXTFONTFAMILY);
  }
  
  public boolean isTextFontFamilyModified()
  {
    return isModified(TEXTFONTFAMILY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTextFontFamilyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEXTFONTFAMILY).getAttributeMdDTO();
  }
  
  public String getTextFontFill()
  {
    return getValue(TEXTFONTFILL);
  }
  
  public void setTextFontFill(String value)
  {
    if(value == null)
    {
      setValue(TEXTFONTFILL, "");
    }
    else
    {
      setValue(TEXTFONTFILL, value);
    }
  }
  
  public boolean isTextFontFillWritable()
  {
    return isWritable(TEXTFONTFILL);
  }
  
  public boolean isTextFontFillReadable()
  {
    return isReadable(TEXTFONTFILL);
  }
  
  public boolean isTextFontFillModified()
  {
    return isModified(TEXTFONTFILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTextFontFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEXTFONTFILL).getAttributeMdDTO();
  }
  
  public Integer getTextFontSize()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTFONTSIZE));
  }
  
  public void setTextFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(TEXTFONTSIZE, "");
    }
    else
    {
      setValue(TEXTFONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTextFontSizeWritable()
  {
    return isWritable(TEXTFONTSIZE);
  }
  
  public boolean isTextFontSizeReadable()
  {
    return isReadable(TEXTFONTSIZE);
  }
  
  public boolean isTextFontSizeModified()
  {
    return isModified(TEXTFONTSIZE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTextFontSizeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEXTFONTSIZE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getTextFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(TEXTFONTSTYLES));
  }
  
  public java.util.List<String> getTextFontStylesEnumNames()
  {
    return getEnumNames(TEXTFONTSTYLES);
  }
  
  public void addTextFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    addEnumItem(TEXTFONTSTYLES, enumDTO.toString());
  }
  
  public void removeTextFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    removeEnumItem(TEXTFONTSTYLES, enumDTO.toString());
  }
  
  public void clearTextFontStyles()
  {
    clearEnum(TEXTFONTSTYLES);
  }
  
  public boolean isTextFontStylesWritable()
  {
    return isWritable(TEXTFONTSTYLES);
  }
  
  public boolean isTextFontStylesReadable()
  {
    return isReadable(TEXTFONTSTYLES);
  }
  
  public boolean isTextFontStylesModified()
  {
    return isModified(TEXTFONTSTYLES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getTextFontStylesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TEXTFONTSTYLES).getAttributeMdDTO();
  }
  
  public static FreeTextDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (FreeTextDTO) dto;
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
