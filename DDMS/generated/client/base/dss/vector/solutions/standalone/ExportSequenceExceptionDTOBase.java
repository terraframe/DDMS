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
package dss.vector.solutions.standalone;

@com.runwaysdk.business.ClassSignature(hash = 649718156)
public abstract class ExportSequenceExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.standalone.ExportSequenceException";
  private static final long serialVersionUID = 649718156;
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ExportSequenceExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDSEQUENCE = "endSequence";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTSEQUENCE = "startSequence";
  public Long getEndSequence()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(ENDSEQUENCE));
  }
  
  public void setEndSequence(Long value)
  {
    if(value == null)
    {
      setValue(ENDSEQUENCE, "");
    }
    else
    {
      setValue(ENDSEQUENCE, java.lang.Long.toString(value));
    }
  }
  
  public boolean isEndSequenceWritable()
  {
    return isWritable(ENDSEQUENCE);
  }
  
  public boolean isEndSequenceReadable()
  {
    return isReadable(ENDSEQUENCE);
  }
  
  public boolean isEndSequenceModified()
  {
    return isModified(ENDSEQUENCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEndSequenceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDSEQUENCE).getAttributeMdDTO();
  }
  
  public Long getStartSequence()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(STARTSEQUENCE));
  }
  
  public void setStartSequence(Long value)
  {
    if(value == null)
    {
      setValue(STARTSEQUENCE, "");
    }
    else
    {
      setValue(STARTSEQUENCE, java.lang.Long.toString(value));
    }
  }
  
  public boolean isStartSequenceWritable()
  {
    return isWritable(STARTSEQUENCE);
  }
  
  public boolean isStartSequenceReadable()
  {
    return isReadable(STARTSEQUENCE);
  }
  
  public boolean isStartSequenceModified()
  {
    return isModified(STARTSEQUENCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStartSequenceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTSEQUENCE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{endSequence}", this.getEndSequence().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{startSequence}", this.getStartSequence().toString());
    
    return template;
  }
  
}
