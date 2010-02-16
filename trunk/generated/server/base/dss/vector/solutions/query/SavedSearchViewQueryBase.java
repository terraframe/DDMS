package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 144960577)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SavedSearchView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class SavedSearchViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 144960577;

  public SavedSearchViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SavedSearchViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.query.SavedSearchView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getConfig()
  {
    return getConfig(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConfig(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.CONFIG, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConfig(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.CONFIG, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryName()
  {
    return getQueryName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryType()
  {
    return getQueryType(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYTYPE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryXml()
  {
    return getQueryXml(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryXml(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYXML, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getQueryXml(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.QUERYXML, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSavedQueryId()
  {
    return getSavedQueryId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSavedQueryId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.SAVEDQUERYID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSavedQueryId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.query.SavedSearchView.SAVEDQUERYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends SavedSearchView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<SavedSearchView>(this.getMdClassIF(), valueIterator);
  }

}
