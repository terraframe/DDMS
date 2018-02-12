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
package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 922635866)
public abstract class PoliticalHierarchyLengthExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.PoliticalHierarchyLengthException";
  private static final long serialVersionUID = 922635866;
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String HIERARCHYLENGTH = "hierarchyLength";
  public static java.lang.String ID = "id";
  public static java.lang.String SLOTS = "slots";
  public Integer getHierarchyLength()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HIERARCHYLENGTH));
  }
  
  public void setHierarchyLength(Integer value)
  {
    if(value == null)
    {
      setValue(HIERARCHYLENGTH, "");
    }
    else
    {
      setValue(HIERARCHYLENGTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHierarchyLengthWritable()
  {
    return isWritable(HIERARCHYLENGTH);
  }
  
  public boolean isHierarchyLengthReadable()
  {
    return isReadable(HIERARCHYLENGTH);
  }
  
  public boolean isHierarchyLengthModified()
  {
    return isModified(HIERARCHYLENGTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHierarchyLengthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HIERARCHYLENGTH).getAttributeMdDTO();
  }
  
  public Integer getSlots()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SLOTS));
  }
  
  public void setSlots(Integer value)
  {
    if(value == null)
    {
      setValue(SLOTS, "");
    }
    else
    {
      setValue(SLOTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSlotsWritable()
  {
    return isWritable(SLOTS);
  }
  
  public boolean isSlotsReadable()
  {
    return isReadable(SLOTS);
  }
  
  public boolean isSlotsModified()
  {
    return isModified(SLOTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSlotsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLOTS).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{hierarchyLength}", this.getHierarchyLength().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{slots}", this.getSlots().toString());
    
    return template;
  }
  
}
