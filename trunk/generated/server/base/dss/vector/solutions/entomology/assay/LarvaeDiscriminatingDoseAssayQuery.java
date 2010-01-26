package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -2007014995)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class LarvaeDiscriminatingDoseAssayQuery extends dss.vector.solutions.entomology.assay.LarvaeAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -2007014995;

  public LarvaeDiscriminatingDoseAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public LarvaeDiscriminatingDoseAssayQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS;
  }
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getLt50()
  {
    return getLt50(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt50(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getLt95()
  {
    return getLt95(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt95(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends LarvaeDiscriminatingDoseAssay> getIterator()
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
    java.util.Map<String, com.terraframe.mojo.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.query(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<LarvaeDiscriminatingDoseAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LarvaeDiscriminatingDoseAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality();
    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias);
    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime();
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeDouble getLt50();
    public com.terraframe.mojo.query.AttributeDouble getLt50(String alias);
    public com.terraframe.mojo.query.AttributeDouble getLt50(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeDouble getLt95();
    public com.terraframe.mojo.query.AttributeDouble getLt95(String alias);
    public com.terraframe.mojo.query.AttributeDouble getLt95(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeFloat getMortality();
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias);
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead();
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive();
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LarvaeDiscriminatingDoseAssayQueryReference extends dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryReference
 implements LarvaeDiscriminatingDoseAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -2094635953;

  public LarvaeDiscriminatingDoseAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      return this.EQ(larvaeDiscriminatingDoseAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      return this.NE(larvaeDiscriminatingDoseAssay.getId());
    }

  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getLt50()
  {
    return getLt50(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt50(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getLt95()
  {
    return getLt95(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt95(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getLt95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  }
}
