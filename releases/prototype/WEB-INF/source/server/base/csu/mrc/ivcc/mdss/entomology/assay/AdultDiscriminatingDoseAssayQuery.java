package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultDiscriminatingDoseAssayQuery extends csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236982479453L;

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
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.AGERANGE);

    return (csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.AGERANGE, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getFed()
  {
    return getFed(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.FED, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid()
  {
    return getGravid(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.GRAVID, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF)this.getComponentQuery().internalAttributeFactory("sex", mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
       return new csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public interface AdultDiscriminatingDoseAssayQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayQuery.DiscriminatingDoseAssayQueryReferenceIF
  {

    public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange();
    public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getFed();
    public com.terraframe.mojo.query.AttributeIntegerIF getFed(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid();
    public com.terraframe.mojo.query.AttributeIntegerIF getGravid(String alias);
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex();
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay adultDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class AdultDiscriminatingDoseAssayQueryReference extends csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayQuery.DiscriminatingDoseAssayQueryReference
 implements AdultDiscriminatingDoseAssayQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236982479668L;

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

  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange()
  {
    return getAgeRange(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF getAgeRange(String alias)
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStructIF)this.attributeFactory("ageRange", "com.terraframe.mojo.system.metadata.MdAttributeStruct", alias);

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
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF getSex(String alias)
  {
    return (csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQueryIF)this.attributeFactory("sex", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  protected com.terraframe.mojo.query.AttributeStruct structFactory( com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("ageRange")) 
    {
       return new csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRangeQuery.AdultAgeRangeQueryStruct((com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
       return new csu.mrc.ivcc.mdss.entomology.SexMasterQuery.AssaySexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
