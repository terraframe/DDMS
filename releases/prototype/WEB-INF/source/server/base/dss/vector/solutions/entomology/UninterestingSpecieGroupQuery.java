package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UninterestingSpecieGroup.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class UninterestingSpecieGroupQuery extends dss.vector.solutions.entomology.TrueSpecieEntityQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239517526068L;

  public UninterestingSpecieGroupQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.entomology.UninterestingSpecieGroup";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.UninterestingSpecieGroup.QUANTITY, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends UninterestingSpecieGroup> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<UninterestingSpecieGroup>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface UninterestingSpecieGroupQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.entomology.TrueSpecieEntityQuery.TrueSpecieEntityQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeIntegerIF getQuantity();
    public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.UninterestingSpecieGroup uninterestingSpecieGroup);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.UninterestingSpecieGroup uninterestingSpecieGroup);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class UninterestingSpecieGroupQueryReference extends dss.vector.solutions.entomology.TrueSpecieEntityQuery.TrueSpecieEntityQueryReference
 implements UninterestingSpecieGroupQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239517526389L;

  public UninterestingSpecieGroupQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.entomology.UninterestingSpecieGroup uninterestingSpecieGroup)
    {
      return this.EQ(uninterestingSpecieGroup.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.entomology.UninterestingSpecieGroup uninterestingSpecieGroup)
    {
      return this.NE(uninterestingSpecieGroup.getId());
    }

  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity()
  {
    return getQuantity(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getQuantity(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("quantity", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  }
}
