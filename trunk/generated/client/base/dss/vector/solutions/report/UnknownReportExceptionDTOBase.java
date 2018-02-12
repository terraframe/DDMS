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
package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 2132573203)
public abstract class UnknownReportExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.UnknownReportException";
  private static final long serialVersionUID = 2132573203;
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnknownReportExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnknownReportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FORMAT = "format";
  public static java.lang.String ID = "id";
  public static java.lang.String REPORTNAME = "reportName";
  public String getFormat()
  {
    return getValue(FORMAT);
  }
  
  public void setFormat(String value)
  {
    if(value == null)
    {
      setValue(FORMAT, "");
    }
    else
    {
      setValue(FORMAT, value);
    }
  }
  
  public boolean isFormatWritable()
  {
    return isWritable(FORMAT);
  }
  
  public boolean isFormatReadable()
  {
    return isReadable(FORMAT);
  }
  
  public boolean isFormatModified()
  {
    return isModified(FORMAT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getFormatMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(FORMAT).getAttributeMdDTO();
  }
  
  public String getReportName()
  {
    return getValue(REPORTNAME);
  }
  
  public void setReportName(String value)
  {
    if(value == null)
    {
      setValue(REPORTNAME, "");
    }
    else
    {
      setValue(REPORTNAME, value);
    }
  }
  
  public boolean isReportNameWritable()
  {
    return isWritable(REPORTNAME);
  }
  
  public boolean isReportNameReadable()
  {
    return isReadable(REPORTNAME);
  }
  
  public boolean isReportNameModified()
  {
    return isModified(REPORTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getReportNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(REPORTNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{format}", this.getFormat().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{reportName}", this.getReportName().toString());
    
    return template;
  }
  
}
