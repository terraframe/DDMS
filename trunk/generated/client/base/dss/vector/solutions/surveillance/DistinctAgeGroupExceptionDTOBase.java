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
package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -733153291)
public abstract class DistinctAgeGroupExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.DistinctAgeGroupException";
  private static final long serialVersionUID = -733153291;
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DistinctAgeGroupExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDAGE = "endAge";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTAGE = "startAge";
  public Integer getEndAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDAGE));
  }
  
  public void setEndAge(Integer value)
  {
    if(value == null)
    {
      setValue(ENDAGE, "");
    }
    else
    {
      setValue(ENDAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEndAgeWritable()
  {
    return isWritable(ENDAGE);
  }
  
  public boolean isEndAgeReadable()
  {
    return isReadable(ENDAGE);
  }
  
  public boolean isEndAgeModified()
  {
    return isModified(ENDAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEndAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDAGE).getAttributeMdDTO();
  }
  
  public Integer getStartAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTAGE));
  }
  
  public void setStartAge(Integer value)
  {
    if(value == null)
    {
      setValue(STARTAGE, "");
    }
    else
    {
      setValue(STARTAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStartAgeWritable()
  {
    return isWritable(STARTAGE);
  }
  
  public boolean isStartAgeReadable()
  {
    return isReadable(STARTAGE);
  }
  
  public boolean isStartAgeModified()
  {
    return isModified(STARTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStartAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTAGE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{endAge}", this.getEndAge().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{startAge}", this.getStartAge().toString());
    
    return template;
  }
  
}
