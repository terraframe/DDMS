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
package dss.vector.solutions.kaleidoscope.dashboard.layer;

@com.runwaysdk.business.ClassSignature(hash = 338437922)
public abstract class DashboardThematicLayerDTOBase extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer";
  private static final long serialVersionUID = 338437922;
  
  protected DashboardThematicLayerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardThematicLayerDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGGREGATIONSTRATEGY = "aggregationStrategy";
  public static java.lang.String AGGREGATIONTYPE = "aggregationType";
  public static java.lang.String GEONODE = "geoNode";
  public static java.lang.String MDATTRIBUTE = "mdAttribute";
  public dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO getAggregationStrategy()
  {
    if(getValue(AGGREGATIONSTRATEGY) == null || getValue(AGGREGATIONSTRATEGY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO.get(getRequest(), getValue(AGGREGATIONSTRATEGY));
    }
  }
  
  public String getAggregationStrategyId()
  {
    return getValue(AGGREGATIONSTRATEGY);
  }
  
  public void setAggregationStrategy(dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO value)
  {
    if(value == null)
    {
      setValue(AGGREGATIONSTRATEGY, "");
    }
    else
    {
      setValue(AGGREGATIONSTRATEGY, value.getId());
    }
  }
  
  public boolean isAggregationStrategyWritable()
  {
    return isWritable(AGGREGATIONSTRATEGY);
  }
  
  public boolean isAggregationStrategyReadable()
  {
    return isReadable(AGGREGATIONSTRATEGY);
  }
  
  public boolean isAggregationStrategyModified()
  {
    return isModified(AGGREGATIONSTRATEGY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAggregationStrategyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGGREGATIONSTRATEGY).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO> getAggregationType()
  {
    return (java.util.List<dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO.CLASS, getEnumNames(AGGREGATIONTYPE));
  }
  
  public java.util.List<String> getAggregationTypeEnumNames()
  {
    return getEnumNames(AGGREGATIONTYPE);
  }
  
  public void addAggregationType(dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO enumDTO)
  {
    addEnumItem(AGGREGATIONTYPE, enumDTO.toString());
  }
  
  public void removeAggregationType(dss.vector.solutions.kaleidoscope.dashboard.AllAggregationTypeDTO enumDTO)
  {
    removeEnumItem(AGGREGATIONTYPE, enumDTO.toString());
  }
  
  public void clearAggregationType()
  {
    clearEnum(AGGREGATIONTYPE);
  }
  
  public boolean isAggregationTypeWritable()
  {
    return isWritable(AGGREGATIONTYPE);
  }
  
  public boolean isAggregationTypeReadable()
  {
    return isReadable(AGGREGATIONTYPE);
  }
  
  public boolean isAggregationTypeModified()
  {
    return isModified(AGGREGATIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getAggregationTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(AGGREGATIONTYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO getGeoNode()
  {
    if(getValue(GEONODE) == null || getValue(GEONODE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO.get(getRequest(), getValue(GEONODE));
    }
  }
  
  public String getGeoNodeId()
  {
    return getValue(GEONODE);
  }
  
  public void setGeoNode(dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO value)
  {
    if(value == null)
    {
      setValue(GEONODE, "");
    }
    else
    {
      setValue(GEONODE, value.getId());
    }
  }
  
  public boolean isGeoNodeWritable()
  {
    return isWritable(GEONODE);
  }
  
  public boolean isGeoNodeReadable()
  {
    return isReadable(GEONODE);
  }
  
  public boolean isGeoNodeModified()
  {
    return isModified(GEONODE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoNodeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEONODE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdAttributeDTO getMdAttribute()
  {
    if(getValue(MDATTRIBUTE) == null || getValue(MDATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(MDATTRIBUTE));
    }
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTE);
  }
  
  public void setMdAttribute(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTE, "");
    }
    else
    {
      setValue(MDATTRIBUTE, value.getId());
    }
  }
  
  public boolean isMdAttributeWritable()
  {
    return isWritable(MDATTRIBUTE);
  }
  
  public boolean isMdAttributeReadable()
  {
    return isReadable(MDATTRIBUTE);
  }
  
  public boolean isMdAttributeModified()
  {
    return isModified(MDATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMdAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MDATTRIBUTE).getAttributeMdDTO();
  }
  
  public final java.lang.String applyWithStyleAndStrategy(dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO strategy, java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle", "java.lang.String", "dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategy", "java.lang.String"};
    Object[] _parameters = new Object[]{style, mapId, strategy, state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "applyWithStyleAndStrategy", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String applyWithStyleAndStrategy(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyDTO strategy, java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle", "java.lang.String", "dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategy", "java.lang.String"};
    Object[] _parameters = new Object[]{id, style, mapId, strategy, state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "applyWithStyleAndStrategy", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getGeoNodeGeometryTypesJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoNodeId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoNodeId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "getGeoNodeGeometryTypesJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getOptionsJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String thematicAttributeId, java.lang.String dashboardId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{thematicAttributeId, dashboardId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "getOptionsJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
