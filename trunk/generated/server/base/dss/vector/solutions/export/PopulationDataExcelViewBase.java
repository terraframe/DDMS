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

@com.runwaysdk.business.ClassSignature(hash = -220576072)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PopulationDataExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PopulationDataExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PopulationDataExcelView";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GROWTHRATE = "growthRate";
  public static java.lang.String ID = "id";
  public static java.lang.String POPULATION = "population";
  public static java.lang.String YEAROFDATA = "yearOfData";
  private static final long serialVersionUID = -220576072;
  
  public PopulationDataExcelViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PopulationDataExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public Double getGrowthRate()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(GROWTHRATE));
  }
  
  public void validateGrowthRate()
  {
    this.validateAttribute(GROWTHRATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getGrowthRateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PopulationDataExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(GROWTHRATE);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PopulationDataExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Long getPopulation()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(POPULATION));
  }
  
  public void validatePopulation()
  {
    this.validateAttribute(POPULATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getPopulationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PopulationDataExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(POPULATION);
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
  
  public Integer getYearOfData()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATA));
  }
  
  public void validateYearOfData()
  {
    this.validateAttribute(YEAROFDATA);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getYearOfDataMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PopulationDataExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(YEAROFDATA);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PopulationDataExcelView get(String id)
  {
    return (PopulationDataExcelView) com.runwaysdk.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
