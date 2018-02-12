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
package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 77531623)
public abstract class PersonInterventionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.PersonInterventionView";
  private static final long serialVersionUID = 77531623;
  
  protected PersonInterventionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVENTIONMETHOD = "interventionMethod";
  public static java.lang.String POINT = "point";
  public static java.lang.String VEHICLEDAYS = "vehicleDays";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getInterventionMethod()
  {
    if(getValue(INTERVENTIONMETHOD) == null || getValue(INTERVENTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(INTERVENTIONMETHOD));
    }
  }
  
  public String getInterventionMethodId()
  {
    return getValue(INTERVENTIONMETHOD);
  }
  
  public void setInterventionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(INTERVENTIONMETHOD, "");
    }
    else
    {
      setValue(INTERVENTIONMETHOD, value.getId());
    }
  }
  
  public boolean isInterventionMethodWritable()
  {
    return isWritable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodReadable()
  {
    return isReadable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodModified()
  {
    return isModified(INTERVENTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInterventionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INTERVENTIONMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ControlInterventionDTO getPoint()
  {
    if(getValue(POINT) == null || getValue(POINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ControlInterventionDTO.get(getRequest(), getValue(POINT));
    }
  }
  
  public String getPointId()
  {
    return getValue(POINT);
  }
  
  public void setPoint(dss.vector.solutions.intervention.monitor.ControlInterventionDTO value)
  {
    if(value == null)
    {
      setValue(POINT, "");
    }
    else
    {
      setValue(POINT, value.getId());
    }
  }
  
  public boolean isPointWritable()
  {
    return isWritable(POINT);
  }
  
  public boolean isPointReadable()
  {
    return isReadable(POINT);
  }
  
  public boolean isPointModified()
  {
    return isModified(POINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(POINT).getAttributeMdDTO();
  }
  
  public Integer getVehicleDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VEHICLEDAYS));
  }
  
  public void setVehicleDays(Integer value)
  {
    if(value == null)
    {
      setValue(VEHICLEDAYS, "");
    }
    else
    {
      setValue(VEHICLEDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVehicleDaysWritable()
  {
    return isWritable(VEHICLEDAYS);
  }
  
  public boolean isVehicleDaysReadable()
  {
    return isReadable(VEHICLEDAYS);
  }
  
  public boolean isVehicleDaysModified()
  {
    return isModified(VEHICLEDAYS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVehicleDaysMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VEHICLEDAYS).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[] getInterventionMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[] getInterventionMethods(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[][] getInterventionMethodsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.PersonInterventionView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO.CLASS, "getInterventionMethodsForViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonInterventionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PersonInterventionViewDTO) dto;
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
