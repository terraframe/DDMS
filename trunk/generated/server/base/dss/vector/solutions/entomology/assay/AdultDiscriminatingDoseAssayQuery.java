package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1980873294)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class AdultDiscriminatingDoseAssayQuery extends dss.vector.solutions.entomology.assay.AdultAssayQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public AdultDiscriminatingDoseAssayQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public AdultDiscriminatingDoseAssayQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;
  }
  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.UNIQUEASSAYID, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends AdultDiscriminatingDoseAssay> getIterator()
  {
    this.checkNotUsedInValueQuery();
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<AdultDiscriminatingDoseAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AdultDiscriminatingDoseAssayQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF
  {

    public com.runwaysdk.query.SelectableFloat getControlTestMortality();
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getHoldingTime();
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias);
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getKd50();
    public com.runwaysdk.query.SelectableDouble getKd50(String alias);
    public com.runwaysdk.query.SelectableDouble getKd50(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getKd95();
    public com.runwaysdk.query.SelectableDouble getKd95(String alias);
    public com.runwaysdk.query.SelectableDouble getKd95(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableFloat getMortality();
    public com.runwaysdk.query.SelectableFloat getMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityDead();
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityLive();
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId();
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultDiscriminatingDoseAssayQueryReference extends dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReference
 implements AdultDiscriminatingDoseAssayQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public AdultDiscriminatingDoseAssayQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      if(adultDiscriminatingDoseAssay == null) return this.EQ((java.lang.String)null);
      return this.EQ(adultDiscriminatingDoseAssay.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      if(adultDiscriminatingDoseAssay == null) return this.NE((java.lang.String)null);
      return this.NE(adultDiscriminatingDoseAssay.getId());
    }

  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getKd95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.UNIQUEASSAYID, alias, displayLabel);

  }
  }
}
