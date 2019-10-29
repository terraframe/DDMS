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

@com.runwaysdk.business.ClassSignature(hash = -1076350511)
public abstract class DuplicateRootExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateRootException";
  private static final long serialVersionUID = -1076350511;
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateRootExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BROWSERFIELD = "browserField";
  public static java.lang.String BROWSERROOT = "browserRoot";
  public static java.lang.String ID = "id";
  public String getBrowserField()
  {
    return getValue(BROWSERFIELD);
  }
  
  public void setBrowserField(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELD, "");
    }
    else
    {
      setValue(BROWSERFIELD, value);
    }
  }
  
  public boolean isBrowserFieldWritable()
  {
    return isWritable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldReadable()
  {
    return isReadable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldModified()
  {
    return isModified(BROWSERFIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERFIELD).getAttributeMdDTO();
  }
  
  public String getBrowserRoot()
  {
    return getValue(BROWSERROOT);
  }
  
  public void setBrowserRoot(String value)
  {
    if(value == null)
    {
      setValue(BROWSERROOT, "");
    }
    else
    {
      setValue(BROWSERROOT, value);
    }
  }
  
  public boolean isBrowserRootWritable()
  {
    return isWritable(BROWSERROOT);
  }
  
  public boolean isBrowserRootReadable()
  {
    return isReadable(BROWSERROOT);
  }
  
  public boolean isBrowserRootModified()
  {
    return isModified(BROWSERROOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserRootMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERROOT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{browserField}", this.getBrowserField().toString());
    template = template.replace("{browserRoot}", this.getBrowserRoot().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
