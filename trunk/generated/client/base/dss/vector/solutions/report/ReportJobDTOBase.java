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

@com.runwaysdk.business.ClassSignature(hash = 1092836607)
public abstract class ReportJobDTOBase extends com.runwaysdk.system.scheduler.ExecutableJobDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.ReportJob";
  private static final long serialVersionUID = 1092836607;
  
  protected ReportJobDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ReportJobDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String REPORTITEM = "reportItem";
  public dss.vector.solutions.report.ReportItemDTO getReportItem()
  {
    if(getValue(REPORTITEM) == null || getValue(REPORTITEM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.report.ReportItemDTO.get(getRequest(), getValue(REPORTITEM));
    }
  }
  
  public String getReportItemId()
  {
    return getValue(REPORTITEM);
  }
  
  public void setReportItem(dss.vector.solutions.report.ReportItemDTO value)
  {
    if(value == null)
    {
      setValue(REPORTITEM, "");
    }
    else
    {
      setValue(REPORTITEM, value.getId());
    }
  }
  
  public boolean isReportItemWritable()
  {
    return isWritable(REPORTITEM);
  }
  
  public boolean isReportItemReadable()
  {
    return isReadable(REPORTITEM);
  }
  
  public boolean isReportItemModified()
  {
    return isModified(REPORTITEM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getReportItemMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(REPORTITEM).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.report.ReportJobDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.report.ReportJobDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.report.ReportJobQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.report.ReportJobQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.report.ReportJobDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.report.ReportJobDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.report.ReportJobDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.report.ReportJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.report.ReportJobDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.report.ReportJobDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.report.ReportJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
