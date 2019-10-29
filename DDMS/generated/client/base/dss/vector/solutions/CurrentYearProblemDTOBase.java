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

@com.runwaysdk.business.ClassSignature(hash = 949872121)
public abstract class CurrentYearProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.CurrentYearProblem";
  private static final long serialVersionUID = 949872121;
  
  public CurrentYearProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public CurrentYearProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String YEAROFDATE = "yearOfDate";
  public Integer getYearOfDate()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATE));
  }
  
  public void setYearOfDate(Integer value)
  {
    if(value == null)
    {
      setValue(YEAROFDATE, "");
    }
    else
    {
      setValue(YEAROFDATE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearOfDateWritable()
  {
    return isWritable(YEAROFDATE);
  }
  
  public boolean isYearOfDateReadable()
  {
    return isReadable(YEAROFDATE);
  }
  
  public boolean isYearOfDateModified()
  {
    return isModified(YEAROFDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearOfDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFDATE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{yearOfDate}", this.getYearOfDate().toString());
    
    return template;
  }
  
}
