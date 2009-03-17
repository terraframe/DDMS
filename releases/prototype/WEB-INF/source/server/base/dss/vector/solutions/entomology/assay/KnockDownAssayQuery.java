package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to KnockDownAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class KnockDownAssayQuery extends dss.vector.solutions.entomology.assay.CollectionAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311443941L;

  public KnockDownAssayQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.assay.KnockDownAssay";
  }
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.entomology.assay.KnockDownAssay.AGERANGE);

    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.assay.KnockDownAssay.AGERANGE, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.KnockDownAssay.FED, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.KnockDownAssay.GRAVID, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF)this.getComponentQuery().internalAttributeFactory("sex", mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends KnockDownAssay> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<KnockDownAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface KnockDownAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReferenceIF
  {

    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange();
    public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getFed();
    public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid();
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias);
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex();
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class KnockDownAssayQueryReference extends dss.vector.solutions.entomology.assay.CollectionAssayQuery.CollectionAssayQueryReference
 implements KnockDownAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311444032L;

  public KnockDownAssayQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay)
    {
      return this.EQ(knockDownAssay.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.assay.KnockDownAssay knockDownAssay)
    {
      return this.NE(knockDownAssay.getId());
    }

  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory("ageRange", "com.terraframe.mojo.system.metadata.MdAttributeStruct", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("fed", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("gravid", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {
    return (dss.vector.solutions.entomology.SexMasterQuery.AssaySexQueryIF)this.attributeFactory("sex", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new dss.vector.solutions.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.structFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new dss.vector.solutions.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
