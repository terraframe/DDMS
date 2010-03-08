package dss.vector.solutions.synchronization;

@com.terraframe.mojo.business.ClassSignature(hash = -1564630609)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ImportLogView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ImportLogViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1564630609;

  public ImportLogViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ImportLogViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.synchronization.ImportLogView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLastExportSeq()
  {
    return getLastExportSeq(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLastExportSeq(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.LASTEXPORTSEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLastExportSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.LASTEXPORTSEQ, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSourceSite()
  {
    return getSourceSite(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSourceSite(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.SOURCESITE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSourceSite(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.synchronization.ImportLogView.SOURCESITE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ImportLogView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ImportLogView>(this.getMdClassIF(), valueIterator);
  }

}
