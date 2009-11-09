package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -711529897)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultDiscriminatingDoseAssayQuery extends dss.vector.solutions.entomology.assay.AdultAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -711529897;

  public AdultDiscriminatingDoseAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;
  }
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd50(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD50, "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd95(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.KD95, "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AdultDiscriminatingDoseAssay> getIterator()
  {
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
    return new com.terraframe.mojo.business.BusinessIterator<AdultDiscriminatingDoseAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AdultDiscriminatingDoseAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality();
    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias);
    public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime();
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias);
    public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeDouble getKd50();
    public com.terraframe.mojo.query.AttributeDouble getKd50(String alias);
    public com.terraframe.mojo.query.AttributeDouble getKd50(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeDouble getKd95();
    public com.terraframe.mojo.query.AttributeDouble getKd95(String alias);
    public com.terraframe.mojo.query.AttributeDouble getKd95(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeFloat getMortality();
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias);
    public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead();
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive();
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias);
    public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultDiscriminatingDoseAssayQueryReference extends dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReference
 implements AdultDiscriminatingDoseAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -443759883;

  public AdultDiscriminatingDoseAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      return this.EQ(adultDiscriminatingDoseAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      return this.NE(adultDiscriminatingDoseAssay.getId());
    }

  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.attributeFactory("controlTestMortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.attributeFactory("controlTestMortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("holdingTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("holdingTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd50(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.attributeFactory("kd50", "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.attributeFactory("kd50", "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd95(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.attributeFactory("kd95", "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getKd95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.attributeFactory("kd95", "com.terraframe.mojo.system.metadata.MdAttributeDouble", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.attributeFactory("mortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeFloat getMortality(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeFloat)this.attributeFactory("mortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("quantityDead", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("quantityDead", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("quantityLive", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.attributeFactory("quantityLive", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias, displayLabel);

  }
  }
}
