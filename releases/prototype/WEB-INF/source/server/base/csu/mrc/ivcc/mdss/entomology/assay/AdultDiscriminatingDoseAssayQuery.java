package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultDiscriminatingDoseAssayQuery extends csu.mrc.ivcc.mdss.entomology.assay.AdultAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236360388831L;

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
    return "csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay";
  }
  public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CONTROLTESTMORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.HOLDINGTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.INTERVALTIME, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeFloatIF getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.MORTALITY, "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYDEAD, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.QUANTITYLIVE, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

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
  public interface AdultDiscriminatingDoseAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality();
    public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime();
    public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias);
    public com.terraframe.mojo.query.AttributeFloatIF getMortality();
    public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultDiscriminatingDoseAssayQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.AdultAssayQuery.AdultAssayQueryReference
 implements AdultDiscriminatingDoseAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236360388939L;

  public AdultDiscriminatingDoseAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      return this.EQ(adultDiscriminatingDoseAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay)
    {
      return this.NE(adultDiscriminatingDoseAssay.getId());
    }

  public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getControlTestMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.attributeFactory("controlTestMortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getHoldingTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("holdingTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime()
  {
    return getIntervalTime(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getIntervalTime(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("intervalTime", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeFloatIF getMortality()
  {
    return getMortality(null);

  }
 
  public com.terraframe.mojo.query.AttributeFloatIF getMortality(String alias)
  {
    return (com.terraframe.mojo.query.AttributeFloatIF)this.attributeFactory("mortality", "com.terraframe.mojo.system.metadata.MdAttributeFloat", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityDead(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityDead", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantityLive(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantityLive", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  }
}
