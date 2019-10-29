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
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1540163640)
public abstract class PopulationDataExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PopulationDataExcelView";
  private static final long serialVersionUID = 1540163640;
  
  protected PopulationDataExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GROWTHRATE = "growthRate";
  public static java.lang.String ID = "id";
  public static java.lang.String POPULATION = "population";
  public static java.lang.String YEAROFDATA = "yearOfData";
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Double getGrowthRate()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(GROWTHRATE));
  }
  
  public void setGrowthRate(Double value)
  {
    if(value == null)
    {
      setValue(GROWTHRATE, "");
    }
    else
    {
      setValue(GROWTHRATE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isGrowthRateWritable()
  {
    return isWritable(GROWTHRATE);
  }
  
  public boolean isGrowthRateReadable()
  {
    return isReadable(GROWTHRATE);
  }
  
  public boolean isGrowthRateModified()
  {
    return isModified(GROWTHRATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getGrowthRateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(GROWTHRATE).getAttributeMdDTO();
  }
  
  public Long getPopulation()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(POPULATION));
  }
  
  public void setPopulation(Long value)
  {
    if(value == null)
    {
      setValue(POPULATION, "");
    }
    else
    {
      setValue(POPULATION, java.lang.Long.toString(value));
    }
  }
  
  public boolean isPopulationWritable()
  {
    return isWritable(POPULATION);
  }
  
  public boolean isPopulationReadable()
  {
    return isReadable(POPULATION);
  }
  
  public boolean isPopulationModified()
  {
    return isModified(POPULATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPopulationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POPULATION).getAttributeMdDTO();
  }
  
  public Integer getYearOfData()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATA));
  }
  
  public void setYearOfData(Integer value)
  {
    if(value == null)
    {
      setValue(YEAROFDATA, "");
    }
    else
    {
      setValue(YEAROFDATA, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearOfDataWritable()
  {
    return isWritable(YEAROFDATA);
  }
  
  public boolean isYearOfDataReadable()
  {
    return isReadable(YEAROFDATA);
  }
  
  public boolean isYearOfDataModified()
  {
    return isModified(YEAROFDATA);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearOfDataMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFDATA).getAttributeMdDTO();
  }
  
  public static PopulationDataExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PopulationDataExcelViewDTO) dto;
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
