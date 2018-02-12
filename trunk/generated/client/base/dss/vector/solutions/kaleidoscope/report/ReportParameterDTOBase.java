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
package dss.vector.solutions.kaleidoscope.report;

@com.runwaysdk.business.ClassSignature(hash = -562925006)
public abstract class ReportParameterDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.report.ReportParameter";
  private static final long serialVersionUID = -562925006;
  
  protected ReportParameterDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String PARAMETERNAME = "parameterName";
  public static java.lang.String PARAMETERVALUE = "parameterValue";
  public String getParameterName()
  {
    return getValue(PARAMETERNAME);
  }
  
  public void setParameterName(String value)
  {
    if(value == null)
    {
      setValue(PARAMETERNAME, "");
    }
    else
    {
      setValue(PARAMETERNAME, value);
    }
  }
  
  public boolean isParameterNameWritable()
  {
    return isWritable(PARAMETERNAME);
  }
  
  public boolean isParameterNameReadable()
  {
    return isReadable(PARAMETERNAME);
  }
  
  public boolean isParameterNameModified()
  {
    return isModified(PARAMETERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getParameterNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PARAMETERNAME).getAttributeMdDTO();
  }
  
  public String getParameterValue()
  {
    return getValue(PARAMETERVALUE);
  }
  
  public void setParameterValue(String value)
  {
    if(value == null)
    {
      setValue(PARAMETERVALUE, "");
    }
    else
    {
      setValue(PARAMETERVALUE, value);
    }
  }
  
  public boolean isParameterValueWritable()
  {
    return isWritable(PARAMETERVALUE);
  }
  
  public boolean isParameterValueReadable()
  {
    return isReadable(PARAMETERVALUE);
  }
  
  public boolean isParameterValueModified()
  {
    return isModified(PARAMETERVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getParameterValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PARAMETERVALUE).getAttributeMdDTO();
  }
  
  public static ReportParameterDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ReportParameterDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
