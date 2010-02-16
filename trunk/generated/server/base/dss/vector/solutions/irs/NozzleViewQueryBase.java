package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1591487018)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NozzleView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class NozzleViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1591487018;

  public NozzleViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public NozzleViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.NozzleView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.DISPLAYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getEnabled()
  {
    return getEnabled(null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getEnabled(String alias)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.irs.NozzleView.ENABLED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getEnabled(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.irs.NozzleView.ENABLED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNozzleId()
  {
    return getNozzleId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNozzleId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.NOZZLEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNozzleId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.NozzleView.NOZZLEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getRatio()
  {
    return getRatio(null);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getRatio(String alias)
  {
    return (com.terraframe.mojo.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.irs.NozzleView.RATIO, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getRatio(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.irs.NozzleView.RATIO, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends NozzleView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<NozzleView>(this.getMdClassIF(), valueIterator);
  }

}
