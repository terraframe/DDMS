package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1926956209)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownIntervalView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class KnockDownIntervalViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public KnockDownIntervalViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public KnockDownIntervalViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.KnockDownIntervalView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.AMOUNT, alias, displayLabel);

  }
 
  public dss.vector.solutions.entomology.assay.KnockDownAssayQuery.KnockDownAssayQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.entomology.assay.KnockDownAssayQuery.KnockDownAssayQueryReferenceIF getAssay(String alias)
  {

    return (dss.vector.solutions.entomology.assay.KnockDownAssayQuery.KnockDownAssayQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.ASSAY, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.KnockDownAssayQuery.KnockDownAssayQueryReferenceIF getAssay(String alias, String displayLabel)
  {

    return (dss.vector.solutions.entomology.assay.KnockDownAssayQuery.KnockDownAssayQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.ASSAY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.INTERVALTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getIntervalTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.assay.KnockDownIntervalView.INTERVALTIME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends KnockDownIntervalView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<KnockDownIntervalView>(this.getMdClassIF(), valueIterator);
  }

}
