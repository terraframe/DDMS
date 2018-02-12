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

@com.runwaysdk.business.ClassSignature(hash = 1126843823)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PopulationDataExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class PopulationDataExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public PopulationDataExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PopulationDataExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.PopulationDataExcelView.CLASS;
  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getGrowthRate()
  {
    return getGrowthRate(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getGrowthRate(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.GROWTHRATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getGrowthRate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.GROWTHRATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableLong getPopulation()
  {
    return getPopulation(null);

  }
 
  public com.runwaysdk.query.SelectableLong getPopulation(String alias)
  {
    return (com.runwaysdk.query.SelectableLong)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.POPULATION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableLong getPopulation(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableLong)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.POPULATION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getYearOfData()
  {
    return getYearOfData(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getYearOfData(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.YEAROFDATA, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getYearOfData(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PopulationDataExcelView.YEAROFDATA, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends PopulationDataExcelView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<PopulationDataExcelView>(this.getMdClassIF(), valueIterator);
  }

}
