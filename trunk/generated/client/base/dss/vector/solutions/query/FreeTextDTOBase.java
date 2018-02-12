/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 2119844573)
public abstract class FreeTextDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.FreeText";
  private static final long serialVersionUID = 2119844573;
  
  protected FreeTextDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCustomTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CUSTOMTEXT).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTextFontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEXTFONTFAMILY).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTextFontFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEXTFONTFILL).getAttributeMdDTO();
  }
  
  public Integer getTextFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTFONTSIZE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTextFontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEXTFONTSIZE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getTextFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(TEXTFONTSTYLES));
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getTextFontStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TEXTFONTSTYLES).getAttributeMdDTO();
  }
  
  public static FreeTextDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
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
