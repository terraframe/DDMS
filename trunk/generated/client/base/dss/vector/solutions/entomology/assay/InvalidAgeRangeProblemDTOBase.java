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
package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1150624228)
public abstract class InvalidAgeRangeProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidAgeRangeProblem";
  private static final long serialVersionUID = -1150624228;
  
  public InvalidAgeRangeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidAgeRangeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String STARTPOINT = "startPoint";
  public Integer getEndPoint()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDPOINT));
  }
  
  public void setEndPoint(Integer value)
  {
    if(value == null)
    {
      setValue(ENDPOINT, "");
    }
    else
    {
      setValue(ENDPOINT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEndPointWritable()
  {
    return isWritable(ENDPOINT);
  }
  
  public boolean isEndPointReadable()
  {
    return isReadable(ENDPOINT);
  }
  
  public boolean isEndPointModified()
  {
    return isModified(ENDPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEndPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDPOINT).getAttributeMdDTO();
  }
  
  public Integer getStartPoint()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTPOINT));
  }
  
  public void setStartPoint(Integer value)
  {
    if(value == null)
    {
      setValue(STARTPOINT, "");
    }
    else
    {
      setValue(STARTPOINT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStartPointWritable()
  {
    return isWritable(STARTPOINT);
  }
  
  public boolean isStartPointReadable()
  {
    return isReadable(STARTPOINT);
  }
  
  public boolean isStartPointModified()
  {
    return isModified(STARTPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStartPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTPOINT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{endPoint}", this.getEndPoint().toString());
    template = template.replace("{startPoint}", this.getStartPoint().toString());
    
    return template;
  }
  
}
