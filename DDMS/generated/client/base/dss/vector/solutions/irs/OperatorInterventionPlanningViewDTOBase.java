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

@com.runwaysdk.business.ClassSignature(hash = 1252655188)
public abstract class OperatorInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorInterventionPlanningView";
  private static final long serialVersionUID = 1252655188;
  
  protected OperatorInterventionPlanningViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String NUMBEROFDAYS = "numberofDays";
  public static java.lang.String REQUIREDOPERATORS = "requiredOperators";
  public static java.lang.String UNITSPERDAY = "unitsPerDay";
  public Integer getNumberofDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFDAYS));
  }
  
  public void setNumberofDays(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFDAYS, "");
    }
    else
    {
      setValue(NUMBEROFDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberofDaysWritable()
  {
    return isWritable(NUMBEROFDAYS);
  }
  
  public boolean isNumberofDaysReadable()
  {
    return isReadable(NUMBEROFDAYS);
  }
  
  public boolean isNumberofDaysModified()
  {
    return isModified(NUMBEROFDAYS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberofDaysMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFDAYS).getAttributeMdDTO();
  }
  
  public Integer getRequiredOperators()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDOPERATORS));
  }
  
  public void setRequiredOperators(Integer value)
  {
    if(value == null)
    {
      setValue(REQUIREDOPERATORS, "");
    }
    else
    {
      setValue(REQUIREDOPERATORS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRequiredOperatorsWritable()
  {
    return isWritable(REQUIREDOPERATORS);
  }
  
  public boolean isRequiredOperatorsReadable()
  {
    return isReadable(REQUIREDOPERATORS);
  }
  
  public boolean isRequiredOperatorsModified()
  {
    return isModified(REQUIREDOPERATORS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRequiredOperatorsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REQUIREDOPERATORS).getAttributeMdDTO();
  }
  
  public Integer getUnitsPerDay()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERDAY));
  }
  
  public void setUnitsPerDay(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSPERDAY, "");
    }
    else
    {
      setValue(UNITSPERDAY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsPerDayWritable()
  {
    return isWritable(UNITSPERDAY);
  }
  
  public boolean isUnitsPerDayReadable()
  {
    return isReadable(UNITSPERDAY);
  }
  
  public boolean isUnitsPerDayModified()
  {
    return isModified(UNITSPERDAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnitsPerDayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSPERDAY).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] calculate(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.OperatorInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.OperatorInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static OperatorInterventionPlanningViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorInterventionPlanningViewDTO) dto;
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
