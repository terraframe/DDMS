package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 1003142823)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BrowserRootView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class BrowserRootViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1003142823;

  public BrowserRootViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public BrowserRootViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.ontology.BrowserRootView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getBrowserRootId()
  {
    return getBrowserRootId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getBrowserRootId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.BROWSERROOTID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getBrowserRootId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.BROWSERROOTID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId()
  {
    return getMdAttributeId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.MDATTRIBUTEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.MDATTRIBUTEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable()
  {
    return getSelectable(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.SELECTABLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.SELECTABLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId()
  {
    return getTermId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName()
  {
    return getTermName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId()
  {
    return getTermOntologyId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMONTOLOGYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.BrowserRootView.TERMONTOLOGYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends BrowserRootView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<BrowserRootView>(this.getMdClassIF(), valueIterator);
  }

}
