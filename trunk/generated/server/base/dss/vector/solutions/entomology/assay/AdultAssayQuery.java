package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -819535147)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultAssayQuery extends dss.vector.solutions.entomology.assay.CollectionAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -819535147;

  public AdultAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public AdultAssayQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.entomology.assay.AdultAssay.CLASS;
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAssay.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAssay.FED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAssay.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAssay.GRAVID, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.SEX, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.SEX, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AdultAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<AdultAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface AdultAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReferenceIF
  {

    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange();
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias);
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getFed();
    public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleInteger getGravid();
    public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias);
    public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias, String displayLabel);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex();
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias);
    public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultAssay adultAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultAssay adultAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultAssayQueryReference extends dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReference
 implements AdultAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1210646413;

  public AdultAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.AdultAssay adultAssay)
    {
      return this.EQ(adultAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.AdultAssay adultAssay)
    {
      return this.NE(adultAssay.getId());
    }

  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE, com.terraframe.mojo.system.metadata.MdAttributeStruct.CLASS, alias, null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE, com.terraframe.mojo.system.metadata.MdAttributeStruct.CLASS, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.AdultAssay.FED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getFed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.AdultAssay.FED, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.AdultAssay.GRAVID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getGravid(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.get(dss.vector.solutions.entomology.assay.AdultAssay.GRAVID, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultAssay.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {
    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.get(dss.vector.solutions.entomology.assay.AdultAssay.SEX,  alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.ontology.TermQuery.TermQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.entomology.assay.AdultAssay.AGERANGE)) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
