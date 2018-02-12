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
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 2141624839)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SavedSearchView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class SavedSearchViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public SavedSearchViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SavedSearchViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.query.SavedSearchView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAdditiveSelectables()
  {
    return getAdditiveSelectables(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAdditiveSelectables(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ADDITIVESELECTABLES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAdditiveSelectables(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ADDITIVESELECTABLES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConfig()
  {
    return getConfig(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConfig(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.CONFIG, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConfig(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.CONFIG, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDeleteSelectables()
  {
    return getDeleteSelectables(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDeleteSelectables(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.DELETESELECTABLES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDeleteSelectables(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.DELETESELECTABLES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsMaterialized()
  {
    return getIsMaterialized(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsMaterialized(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ISMATERIALIZED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsMaterialized(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ISMATERIALIZED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getKaleidoscopes()
  {
    return getKaleidoscopes(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKaleidoscopes(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.KALEIDOSCOPES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKaleidoscopes(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.KALEIDOSCOPES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryName()
  {
    return getQueryName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryType()
  {
    return getQueryType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryXml()
  {
    return getQueryXml(null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryXml(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYXML, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getQueryXml(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYXML, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSavedQueryId()
  {
    return getSavedQueryId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSavedQueryId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.SAVEDQUERYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSavedQueryId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.SAVEDQUERYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends SavedSearchView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<SavedSearchView>(this.getMdClassIF(), valueIterator);
  }

}
