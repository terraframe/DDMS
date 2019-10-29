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
package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 146106593)
public abstract class DuplicateParentExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateParentException";
  private static final long serialVersionUID = 146106593;
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateParentExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHILDTERM = "childTerm";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTERM = "parentTerm";
  public String getChildTerm()
  {
    return getValue(CHILDTERM);
  }
  
  public void setChildTerm(String value)
  {
    if(value == null)
    {
      setValue(CHILDTERM, "");
    }
    else
    {
      setValue(CHILDTERM, value);
    }
  }
  
  public boolean isChildTermWritable()
  {
    return isWritable(CHILDTERM);
  }
  
  public boolean isChildTermReadable()
  {
    return isReadable(CHILDTERM);
  }
  
  public boolean isChildTermModified()
  {
    return isModified(CHILDTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getChildTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDTERM).getAttributeMdDTO();
  }
  
  public String getParentTerm()
  {
    return getValue(PARENTTERM);
  }
  
  public void setParentTerm(String value)
  {
    if(value == null)
    {
      setValue(PARENTTERM, "");
    }
    else
    {
      setValue(PARENTTERM, value);
    }
  }
  
  public boolean isParentTermWritable()
  {
    return isWritable(PARENTTERM);
  }
  
  public boolean isParentTermReadable()
  {
    return isReadable(PARENTTERM);
  }
  
  public boolean isParentTermModified()
  {
    return isModified(PARENTTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTERM).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{childTerm}", this.getChildTerm().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{parentTerm}", this.getParentTerm().toString());
    
    return template;
  }
  
}
