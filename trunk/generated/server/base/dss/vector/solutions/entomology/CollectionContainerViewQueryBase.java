package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -22583334)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CollectionContainerView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class CollectionContainerViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -22583334;

  public CollectionContainerViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public CollectionContainerViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.CollectionContainerView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers()
  {
    return getNumberContainers(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERCONTAINERS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERCONTAINERS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed()
  {
    return getNumberDestroyed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERDESTROYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERDESTROYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures()
  {
    return getNumberImmatures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERIMMATURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERIMMATURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae()
  {
    return getNumberLarvae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERLARVAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERLARVAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected()
  {
    return getNumberLarvaeCollected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERLARVAECOLLECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERLARVAECOLLECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae()
  {
    return getNumberPupae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERPUPAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERPUPAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected()
  {
    return getNumberPupaeCollected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERPUPAECOLLECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERPUPAECOLLECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide()
  {
    return getNumberWithLarvicide(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERWITHLARVICIDE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERWITHLARVICIDE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater()
  {
    return getNumberWithWater(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERWITHWATER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.NUMBERWITHWATER, alias, displayLabel);

  }
 
  public dss.vector.solutions.entomology.PremiseTaxonQuery.PremiseTaxonQueryReferenceIF getTaxon()
  {
    return getTaxon(null);

  }
 
  public dss.vector.solutions.entomology.PremiseTaxonQuery.PremiseTaxonQueryReferenceIF getTaxon(String alias)
  {

    return (dss.vector.solutions.entomology.PremiseTaxonQuery.PremiseTaxonQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.TAXON, alias, null);

  }
 
  public dss.vector.solutions.entomology.PremiseTaxonQuery.PremiseTaxonQueryReferenceIF getTaxon(String alias, String displayLabel)
  {

    return (dss.vector.solutions.entomology.PremiseTaxonQuery.PremiseTaxonQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.TAXON, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.CollectionContainerView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends CollectionContainerView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<CollectionContainerView>(this.getMdClassIF(), valueIterator);
  }

}
