package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollection.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MosquitoCollectionQuery extends dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237311439937L;

  public MosquitoCollectionQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.MosquitoCollection";
  }
  public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("collectionMethod");

    return (dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.entomology.MosquitoCollection.COLLECTIONMETHOD, mdAttributeIF, this, alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("collectionMethod")) 
    {
       return new dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends MosquitoCollection> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<MosquitoCollection>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MosquitoCollectionQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReferenceIF
  {

    public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod();
    public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.MosquitoCollection mosquitoCollection);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.MosquitoCollection mosquitoCollection);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MosquitoCollectionQueryReference extends dss.vector.solutions.entomology.ConcreteMosquitoCollectionQuery.ConcreteMosquitoCollectionQueryReference
 implements MosquitoCollectionQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237311440043L;

  public MosquitoCollectionQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.MosquitoCollection mosquitoCollection)
    {
      return this.EQ(mosquitoCollection.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.MosquitoCollection mosquitoCollection)
    {
      return this.NE(mosquitoCollection.getId());
    }

  public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF getCollectionMethod(String alias)
  {
    return (dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReferenceIF)this.attributeFactory("collectionMethod", "com.terraframe.mojo.system.metadata.MdAttributeReference", alias);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("collectionMethod")) 
    {
       return new dss.vector.solutions.mo.CollectionMethodQuery.CollectionMethodQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias);
    }
  }

  }
}
