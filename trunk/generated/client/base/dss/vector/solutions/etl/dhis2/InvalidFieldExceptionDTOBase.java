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
package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = -659029417)
public abstract class InvalidFieldExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.InvalidFieldException";
  private static final long serialVersionUID = -659029417;
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InvalidFieldExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATASET = "dataset";
  public static java.lang.String FIELD = "field";
  public static java.lang.String ID = "id";
  public String getDataset()
  {
    return getValue(DATASET);
  }
  
  public void setDataset(String value)
  {
    if(value == null)
    {
      setValue(DATASET, "");
    }
    else
    {
      setValue(DATASET, value);
    }
  }
  
  public boolean isDatasetWritable()
  {
    return isWritable(DATASET);
  }
  
  public boolean isDatasetReadable()
  {
    return isReadable(DATASET);
  }
  
  public boolean isDatasetModified()
  {
    return isModified(DATASET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDatasetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DATASET).getAttributeMdDTO();
  }
  
  public String getField()
  {
    return getValue(FIELD);
  }
  
  public void setField(String value)
  {
    if(value == null)
    {
      setValue(FIELD, "");
    }
    else
    {
      setValue(FIELD, value);
    }
  }
  
  public boolean isFieldWritable()
  {
    return isWritable(FIELD);
  }
  
  public boolean isFieldReadable()
  {
    return isReadable(FIELD);
  }
  
  public boolean isFieldModified()
  {
    return isModified(FIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIELD).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{dataset}", this.getDataset().toString());
    template = template.replace("{field}", this.getField().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
