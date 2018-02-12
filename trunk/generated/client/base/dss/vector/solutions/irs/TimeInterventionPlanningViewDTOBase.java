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
package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -760636466)
public abstract class TimeInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TimeInterventionPlanningView";
  private static final long serialVersionUID = -760636466;
  
  protected TimeInterventionPlanningViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OPERATORS = "operators";
  public static java.lang.String REQUIREDDAYS = "requiredDays";
  public Integer getOperators()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORS));
  }
  
  public void setOperators(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORS, "");
    }
    else
    {
      setValue(OPERATORS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorsWritable()
  {
    return isWritable(OPERATORS);
  }
  
  public boolean isOperatorsReadable()
  {
    return isReadable(OPERATORS);
  }
  
  public boolean isOperatorsModified()
  {
    return isModified(OPERATORS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOperatorsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORS).getAttributeMdDTO();
  }
  
  public Integer getRequiredDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDDAYS));
  }
  
  public void setRequiredDays(Integer value)
  {
    if(value == null)
    {
      setValue(REQUIREDDAYS, "");
    }
    else
    {
      setValue(REQUIREDDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRequiredDaysWritable()
  {
    return isWritable(REQUIREDDAYS);
  }
  
  public boolean isRequiredDaysReadable()
  {
    return isReadable(REQUIREDDAYS);
  }
  
  public boolean isRequiredDaysModified()
  {
    return isModified(REQUIREDDAYS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRequiredDaysMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REQUIREDDAYS).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] calculate(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views, java.lang.Integer unitsPerDay)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;", "java.lang.Integer"};
    Object[] _parameters = new Object[]{views, unitsPerDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] calculateDefault(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "calculateDefault", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TimeInterventionPlanningViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TimeInterventionPlanningViewDTO) dto;
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
