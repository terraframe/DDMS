package csu.mrc.ivcc.mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Mosquito.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MosquitoQuery extends csu.mrc.ivcc.mdss.entomology.TrueSpecieEntityQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236803163927L;

  public MosquitoQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.entomology.Mosquito";
  }
  public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration()
  {
    return getGeneration(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("generation");

    return (csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(csu.mrc.ivcc.mdss.entomology.Mosquito.GENERATION, mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.Mosquito.ISOFEMALE, "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("sex");

    return (csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF)this.getComponentQuery().internalAttributeFactory("sex", mdAttributeIF, this, alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.getComponentQuery().attributeFactory(csu.mrc.ivcc.mdss.entomology.Mosquito.TESTDATE, "com.terraframe.mojo.system.metadata.MdAttributeDate", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("generation")) 
    {
       return new csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
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
  public com.terraframe.mojo.query.OIterator<? extends Mosquito> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<Mosquito>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MosquitoQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.entomology.TrueSpecieEntityQuery.TrueSpecieEntityQueryReferenceIF
  {

    public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration();
    public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias);
    public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale();
    public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias);
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex();
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex(String alias);
    public com.terraframe.mojo.query.AttributeMomentIF getTestDate();
    public com.terraframe.mojo.query.AttributeMomentIF getTestDate(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.Mosquito mosquito);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.Mosquito mosquito);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MosquitoQueryReference extends csu.mrc.ivcc.mdss.entomology.TrueSpecieEntityQuery.TrueSpecieEntityQueryReference
 implements MosquitoQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1236803164026L;

  public MosquitoQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.entomology.Mosquito mosquito)
    {
      return this.EQ(mosquito.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.entomology.Mosquito mosquito)
    {
      return this.NE(mosquito.getId());
    }

  public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration()
  {
    return getGeneration(null);

  }
 
  public csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF getGeneration(String alias)
  {
    return (csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReferenceIF)this.attributeFactory("generation", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getIsofemale(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.attributeFactory("isofemale", "com.terraframe.mojo.system.metadata.MdAttributeBoolean", alias);

  }
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex()
  {
    return getSex(null);

  }
 
  public csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF getSex(String alias)
  {
    return (csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQueryIF)this.attributeFactory("sex", "com.terraframe.mojo.system.metadata.MdAttributeEnumeration", alias);

  }
  public com.terraframe.mojo.query.AttributeMomentIF getTestDate()
  {
    return getTestDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMomentIF getTestDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMomentIF)this.attributeFactory("testDate", "com.terraframe.mojo.system.metadata.MdAttributeDate", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("generation")) 
    {
       return new csu.mrc.ivcc.mdss.mo.GenerationQuery.GenerationQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  protected com.terraframe.mojo.query.AttributeEnumeration enumerationFactory( com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  String mdEnumerationTableName, com.terraframe.mojo.dataaccess.MdBusinessDAOIF masterListMdBusinessIF, String masterListTalbeAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("sex")) 
    {
       return new csu.mrc.ivcc.mdss.entomology.SexMasterQuery.SexQuery((com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.enumerationFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterListMdBusinessIF, masterListTalbeAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
