package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = -373880340)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TermView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class TermViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -373880340;

  public TermViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TermViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.ontology.TermView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSelectable()
  {
    return getSelectable(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSelectable(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.ontology.TermView.SELECTABLE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSelectable(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.ontology.TermView.SELECTABLE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermId()
  {
    return getTermId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermName()
  {
    return getTermName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermOntologyId()
  {
    return getTermOntologyId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermOntologyId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMONTOLOGYID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTermOntologyId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.ontology.TermView.TERMONTOLOGYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends TermView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<TermView>(this.getMdClassIF(), valueIterator);
  }

}
