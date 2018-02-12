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

@com.runwaysdk.business.ClassSignature(hash = 2072311383)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BiochemicalAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class BiochemicalAssayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public BiochemicalAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public BiochemicalAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.BiochemicalAssayExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAssay()
  {
    return getAssay(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssay(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ASSAY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssay(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ASSAY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration()
  {
    return getGeneration(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.GENERATION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeneration(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.GENERATION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod()
  {
    return getIdentMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.IDENTMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.IDENTMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ISOFEMALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId()
  {
    return getMosquitoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.MOSQUITOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.MOSQUITOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated()
  {
    return getNumberElevated(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.NUMBERELEVATED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.NUMBERELEVATED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested()
  {
    return getNumberTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.NUMBERTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.NUMBERTESTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSex()
  {
    return getSex(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.SEX, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSex(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.SEX, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies()
  {
    return getSpecies(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.SPECIES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.SPECIES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.BiochemicalAssayExcelView.UNIQUEASSAYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends BiochemicalAssayExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<BiochemicalAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
