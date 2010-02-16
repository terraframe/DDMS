package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = 911271642)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedCaseSearchView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AggregatedCaseSearchViewQueryBase extends dss.vector.solutions.surveillance.AggregatedCaseViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 911271642;

  public AggregatedCaseSearchViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedCaseSearchViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.AggregatedCaseSearchView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableBoolean getSearchType()
  {
    return getSearchType(null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getSearchType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.surveillance.AggregatedCaseSearchView.SEARCHTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getSearchType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.surveillance.AggregatedCaseSearchView.SEARCHTYPE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AggregatedCaseSearchView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<AggregatedCaseSearchView>(this.getMdClassIF(), valueIterator);
  }

}
