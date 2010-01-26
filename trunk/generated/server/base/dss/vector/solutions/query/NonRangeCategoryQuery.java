package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 887251959)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NonRangeCategory.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class NonRangeCategoryQuery extends dss.vector.solutions.query.AbstractCategoryQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 887251959;

  public NonRangeCategoryQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public NonRangeCategoryQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.NonRangeCategory.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getExactValueStr()
  {
    return getExactValueStr(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.NonRangeCategory.EXACTVALUESTR, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.NonRangeCategory.EXACTVALUESTR, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends NonRangeCategory> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<NonRangeCategory>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface NonRangeCategoryQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.query.AbstractCategoryQuery.AbstractCategoryQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeChar getExactValueStr();
    public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias);
    public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.NonRangeCategory nonRangeCategory);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.NonRangeCategory nonRangeCategory);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class NonRangeCategoryQueryReference extends dss.vector.solutions.query.AbstractCategoryQuery.AbstractCategoryQueryReference
 implements NonRangeCategoryQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1670896619;

  public NonRangeCategoryQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.NonRangeCategory nonRangeCategory)
    {
      return this.EQ(nonRangeCategory.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.NonRangeCategory nonRangeCategory)
    {
      return this.NE(nonRangeCategory.getId());
    }

  public com.terraframe.mojo.query.AttributeChar getExactValueStr()
  {
    return getExactValueStr(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.NonRangeCategory.EXACTVALUESTR, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getExactValueStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.NonRangeCategory.EXACTVALUESTR, alias, displayLabel);

  }
  }
}
