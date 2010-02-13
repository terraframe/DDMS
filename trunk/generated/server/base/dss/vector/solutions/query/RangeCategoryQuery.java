package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1756970942)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RangeCategory.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class RangeCategoryQuery extends dss.vector.solutions.query.AbstractCategoryQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1756970942;

  public RangeCategoryQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public RangeCategoryQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.RangeCategory.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr()
  {
    return getLowerBoundStr(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.query.RangeCategory.LOWERBOUNDSTR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.query.RangeCategory.LOWERBOUNDSTR, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr()
  {
    return getUpperBoundStr(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.query.RangeCategory.UPPERBOUNDSTR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getComponentQuery().get(dss.vector.solutions.query.RangeCategory.UPPERBOUNDSTR, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends RangeCategory> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<RangeCategory>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface RangeCategoryQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.query.AbstractCategoryQuery.AbstractCategoryQueryReferenceIF
  {

    public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr();
    public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias, String displayLabel);
    public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr();
    public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias);
    public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.RangeCategory rangeCategory);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.RangeCategory rangeCategory);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class RangeCategoryQueryReference extends dss.vector.solutions.query.AbstractCategoryQuery.AbstractCategoryQueryReference
 implements RangeCategoryQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = -947697308;

  public RangeCategoryQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.RangeCategory rangeCategory)
    {
      return this.EQ(rangeCategory.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.RangeCategory rangeCategory)
    {
      return this.NE(rangeCategory.getId());
    }

  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr()
  {
    return getLowerBoundStr(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.query.RangeCategory.LOWERBOUNDSTR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getLowerBoundStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.query.RangeCategory.LOWERBOUNDSTR, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr()
  {
    return getUpperBoundStr(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.query.RangeCategory.UPPERBOUNDSTR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUpperBoundStr(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.get(dss.vector.solutions.query.RangeCategory.UPPERBOUNDSTR, alias, displayLabel);

  }
  }
}
