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

@com.runwaysdk.business.ClassSignature(hash = 2100660731)
public abstract class KaleidoscopeReportViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportView";
  private static final long serialVersionUID = 2100660731;
  
  protected KaleidoscopeReportViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DASHBOARDLABEL = "dashboardLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String REPORTID = "reportId";
  public static java.lang.String REPORTLABEL = "reportLabel";
  public static java.lang.String REPORTNAME = "reportName";
  public String getDashboardLabel()
  {
    return getValue(DASHBOARDLABEL);
  }
  
  public void setDashboardLabel(String value)
  {
    if(value == null)
    {
      setValue(DASHBOARDLABEL, "");
    }
    else
    {
      setValue(DASHBOARDLABEL, value);
    }
  }
  
  public boolean isDashboardLabelWritable()
  {
    return isWritable(DASHBOARDLABEL);
  }
  
  public boolean isDashboardLabelReadable()
  {
    return isReadable(DASHBOARDLABEL);
  }
  
  public boolean isDashboardLabelModified()
  {
    return isModified(DASHBOARDLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getDashboardLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DASHBOARDLABEL).getAttributeMdDTO();
  }
  
  public String getReportId()
  {
    return getValue(REPORTID);
  }
  
  public void setReportId(String value)
  {
    if(value == null)
    {
      setValue(REPORTID, "");
    }
    else
    {
      setValue(REPORTID, value);
    }
  }
  
  public boolean isReportIdWritable()
  {
    return isWritable(REPORTID);
  }
  
  public boolean isReportIdReadable()
  {
    return isReadable(REPORTID);
  }
  
  public boolean isReportIdModified()
  {
    return isModified(REPORTID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getReportIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(REPORTID).getAttributeMdDTO();
  }
  
  public String getReportLabel()
  {
    return getValue(REPORTLABEL);
  }
  
  public void setReportLabel(String value)
  {
    if(value == null)
    {
      setValue(REPORTLABEL, "");
    }
    else
    {
      setValue(REPORTLABEL, value);
    }
  }
  
  public boolean isReportLabelWritable()
  {
    return isWritable(REPORTLABEL);
  }
  
  public boolean isReportLabelReadable()
  {
    return isReadable(REPORTLABEL);
  }
  
  public boolean isReportLabelModified()
  {
    return isModified(REPORTLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getReportLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(REPORTLABEL).getAttributeMdDTO();
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
  
  public final void remove()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportViewDTO.CLASS, "remove", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void remove(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportViewDTO.CLASS, "remove", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static KaleidoscopeReportViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (KaleidoscopeReportViewDTO) dto;
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
