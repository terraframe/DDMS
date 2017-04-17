package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1412300316)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TimeResponseAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class TimeResponseAssayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public TimeResponseAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TimeResponseAssayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getActiveIngredient()
  {
    return getActiveIngredient(null);

  }
 
  public com.runwaysdk.query.SelectableChar getActiveIngredient(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ACTIVEINGREDIENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getActiveIngredient(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ACTIVEINGREDIENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getAssay()
  {
    return getAssay(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssay(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ASSAY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssay(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ASSAY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage()
  {
    return getLifeStage(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.LIFESTAGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.LIFESTAGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getReferenceStrainResult()
  {
    return getReferenceStrainResult(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getReferenceStrainResult(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.REFERENCESTRAINRESULT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getReferenceStrainResult(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.REFERENCESTRAINRESULT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies()
  {
    return getSpecies(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.SPECIES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSpecies(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.SPECIES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist()
  {
    return getSynergist(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.SYNERGIST, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSynergist(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.SYNERGIST, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getTestStrainResult()
  {
    return getTestStrainResult(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getTestStrainResult(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.TESTSTRAINRESULT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getTestStrainResult(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.TESTSTRAINRESULT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.TimeResponseAssayExcelView.UNIQUEASSAYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends TimeResponseAssayExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<TimeResponseAssayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
