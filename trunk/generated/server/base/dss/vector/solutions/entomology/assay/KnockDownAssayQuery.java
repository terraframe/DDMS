package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 1178899689)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class KnockDownAssayQuery extends dss.vector.solutions.entomology.assay.AdultAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1178899689;

  public KnockDownAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public KnockDownAssayQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10()
  {
    return getInterval10(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL10, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL10, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20()
  {
    return getInterval20(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL20, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL20, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30()
  {
    return getInterval30(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL30, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL30, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40()
  {
    return getInterval40(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL40, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL40, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50()
  {
    return getInterval50(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL50, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60()
  {
    return getInterval60(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL60, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL60, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD50, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD95, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD95, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends KnockDownAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<KnockDownAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface KnockDownAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReferenceIF
  {

    public com.terraframe.mojo.query.SelectableSingleInteger getInterval10();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval20();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval30();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval40();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval50();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval60();
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleDouble getKd50();
    public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias);
    public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleDouble getKd95();
    public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias);
    public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class KnockDownAssayQueryReference extends dss.vector.solutions.entomology.assay.AdultAssayQuery.AdultAssayQueryReference
 implements KnockDownAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 444597131;

  public KnockDownAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay)
    {
      return this.EQ(knockDownAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay)
    {
      return this.NE(knockDownAssay.getId());
    }

  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10()
  {
    return getInterval10(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL10, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval10(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL10, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20()
  {
    return getInterval20(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL20, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval20(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL20, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30()
  {
    return getInterval30(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL30, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval30(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL30, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40()
  {
    return getInterval40(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL40, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval40(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL40, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50()
  {
    return getInterval50(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL50, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60()
  {
    return getInterval60(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL60, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getInterval60(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.INTERVAL60, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50()
  {
    return getKd50(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD50, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd50(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD50, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95()
  {
    return getKd95(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD95, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getKd95(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.get(dss.vector.solutions.entomology.assay.KnockDownAssay.KD95, alias, displayLabel);

  }
  }
}
