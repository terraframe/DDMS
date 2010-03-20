package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 1198236938)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TermView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class TermViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1198236938;

  public TermViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TermViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.ontology.TermView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable()
  {
    return getSelectable(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.ontology.TermView.SELECTABLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSelectable(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.ontology.TermView.SELECTABLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId()
  {
    return getTermId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName()
  {
    return getTermName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId()
  {
    return getTermOntologyId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMONTOLOGYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTermOntologyId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMONTOLOGYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends TermView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<TermView>(this.getMdClassIF(), valueIterator);
  }

}
