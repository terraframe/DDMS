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
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -682813550)
public abstract class InvalidDiagnosisTypeProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InvalidDiagnosisTypeProblem";
  private static final long serialVersionUID = -682813550;
  
  public InvalidDiagnosisTypeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidDiagnosisTypeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DIAGNOSISTYPE = "diagnosisType";
  public static java.lang.String ID = "id";
  public String getDiagnosisType()
  {
    return getValue(DIAGNOSISTYPE);
  }
  
  public void setDiagnosisType(String value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISTYPE, "");
    }
    else
    {
      setValue(DIAGNOSISTYPE, value);
    }
  }
  
  public boolean isDiagnosisTypeWritable()
  {
    return isWritable(DIAGNOSISTYPE);
  }
  
  public boolean isDiagnosisTypeReadable()
  {
    return isReadable(DIAGNOSISTYPE);
  }
  
  public boolean isDiagnosisTypeModified()
  {
    return isModified(DIAGNOSISTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDiagnosisTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DIAGNOSISTYPE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{diagnosisType}", this.getDiagnosisType().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
