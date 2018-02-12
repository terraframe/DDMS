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
package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -984481832)
public abstract class RangeValueProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.RangeValueProblem";
  private static final long serialVersionUID = -984481832;
  
  public RangeValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public RangeValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INVALIDVALUE = "invalidValue";
  public static java.lang.String LOWERLIMIT = "lowerLimit";
  public static java.lang.String UPPERLIMIT = "upperLimit";
  public Integer getInvalidValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDVALUE));
  }
  
  public void setInvalidValue(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDVALUE, "");
    }
    else
    {
      setValue(INVALIDVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInvalidValueWritable()
  {
    return isWritable(INVALIDVALUE);
  }
  
  public boolean isInvalidValueReadable()
  {
    return isReadable(INVALIDVALUE);
  }
  
  public boolean isInvalidValueModified()
  {
    return isModified(INVALIDVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getInvalidValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INVALIDVALUE).getAttributeMdDTO();
  }
  
  public Integer getLowerLimit()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERLIMIT));
  }
  
  public void setLowerLimit(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERLIMIT, "");
    }
    else
    {
      setValue(LOWERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLowerLimitWritable()
  {
    return isWritable(LOWERLIMIT);
  }
  
  public boolean isLowerLimitReadable()
  {
    return isReadable(LOWERLIMIT);
  }
  
  public boolean isLowerLimitModified()
  {
    return isModified(LOWERLIMIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLowerLimitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOWERLIMIT).getAttributeMdDTO();
  }
  
  public Integer getUpperLimit()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERLIMIT));
  }
  
  public void setUpperLimit(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERLIMIT, "");
    }
    else
    {
      setValue(UPPERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUpperLimitWritable()
  {
    return isWritable(UPPERLIMIT);
  }
  
  public boolean isUpperLimitReadable()
  {
    return isReadable(UPPERLIMIT);
  }
  
  public boolean isUpperLimitModified()
  {
    return isModified(UPPERLIMIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUpperLimitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UPPERLIMIT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{invalidValue}", this.getInvalidValue().toString());
    template = template.replace("{lowerLimit}", this.getLowerLimit().toString());
    template = template.replace("{upperLimit}", this.getUpperLimit().toString());
    
    return template;
  }
  
}
