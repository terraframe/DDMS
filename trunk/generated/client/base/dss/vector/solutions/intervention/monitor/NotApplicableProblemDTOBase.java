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
package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1903388378)
public abstract class NotApplicableProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.NotApplicableProblem";
  private static final long serialVersionUID = 1903388378;
  
  public NotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public NotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INPUTATTRIBUTE = "inputAttribute";
  public static java.lang.String INPUTVALUE = "inputValue";
  public String getInputAttribute()
  {
    return getValue(INPUTATTRIBUTE);
  }
  
  public void setInputAttribute(String value)
  {
    if(value == null)
    {
      setValue(INPUTATTRIBUTE, "");
    }
    else
    {
      setValue(INPUTATTRIBUTE, value);
    }
  }
  
  public boolean isInputAttributeWritable()
  {
    return isWritable(INPUTATTRIBUTE);
  }
  
  public boolean isInputAttributeReadable()
  {
    return isReadable(INPUTATTRIBUTE);
  }
  
  public boolean isInputAttributeModified()
  {
    return isModified(INPUTATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInputAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INPUTATTRIBUTE).getAttributeMdDTO();
  }
  
  public String getInputValue()
  {
    return getValue(INPUTVALUE);
  }
  
  public void setInputValue(String value)
  {
    if(value == null)
    {
      setValue(INPUTVALUE, "");
    }
    else
    {
      setValue(INPUTVALUE, value);
    }
  }
  
  public boolean isInputValueWritable()
  {
    return isWritable(INPUTVALUE);
  }
  
  public boolean isInputValueReadable()
  {
    return isReadable(INPUTVALUE);
  }
  
  public boolean isInputValueModified()
  {
    return isModified(INPUTVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInputValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INPUTVALUE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{inputAttribute}", this.getInputAttribute().toString());
    template = template.replace("{inputValue}", this.getInputValue().toString());
    
    return template;
  }
  
}
