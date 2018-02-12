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
package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = -1027615442)
public abstract class TargetFieldCoordinateBindingDTOBase extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBinding";
  private static final long serialVersionUID = -1027615442;
  
  protected TargetFieldCoordinateBindingDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TargetFieldCoordinateBindingDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String LATITUDEATTRIBUTE = "latitudeAttribute";
  public static java.lang.String LONGITUDEATTRIBUTE = "longitudeAttribute";
  public com.runwaysdk.system.metadata.MdAttributeDTO getLatitudeAttribute()
  {
    if(getValue(LATITUDEATTRIBUTE) == null || getValue(LATITUDEATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(LATITUDEATTRIBUTE));
    }
  }
  
  public String getLatitudeAttributeId()
  {
    return getValue(LATITUDEATTRIBUTE);
  }
  
  public void setLatitudeAttribute(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(LATITUDEATTRIBUTE, "");
    }
    else
    {
      setValue(LATITUDEATTRIBUTE, value.getId());
    }
  }
  
  public boolean isLatitudeAttributeWritable()
  {
    return isWritable(LATITUDEATTRIBUTE);
  }
  
  public boolean isLatitudeAttributeReadable()
  {
    return isReadable(LATITUDEATTRIBUTE);
  }
  
  public boolean isLatitudeAttributeModified()
  {
    return isModified(LATITUDEATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLatitudeAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LATITUDEATTRIBUTE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdAttributeDTO getLongitudeAttribute()
  {
    if(getValue(LONGITUDEATTRIBUTE) == null || getValue(LONGITUDEATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(LONGITUDEATTRIBUTE));
    }
  }
  
  public String getLongitudeAttributeId()
  {
    return getValue(LONGITUDEATTRIBUTE);
  }
  
  public void setLongitudeAttribute(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(LONGITUDEATTRIBUTE, "");
    }
    else
    {
      setValue(LONGITUDEATTRIBUTE, value.getId());
    }
  }
  
  public boolean isLongitudeAttributeWritable()
  {
    return isWritable(LONGITUDEATTRIBUTE);
  }
  
  public boolean isLongitudeAttributeReadable()
  {
    return isReadable(LONGITUDEATTRIBUTE);
  }
  
  public boolean isLongitudeAttributeModified()
  {
    return isModified(LONGITUDEATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLongitudeAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LONGITUDEATTRIBUTE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldCoordinateBindingDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
