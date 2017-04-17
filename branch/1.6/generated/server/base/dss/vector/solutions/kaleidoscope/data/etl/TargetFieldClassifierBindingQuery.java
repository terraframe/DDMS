package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = 796958541)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldClassifierBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class TargetFieldClassifierBindingQuery extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public TargetFieldClassifierBindingQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public TargetFieldClassifierBindingQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends TargetFieldClassifierBinding> getIterator()
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
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<TargetFieldClassifierBinding>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargetFieldClassifierBindingQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQuery.TargetFieldBasicBindingQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding targetFieldClassifierBinding);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding targetFieldClassifierBinding);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargetFieldClassifierBindingQueryReference extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQuery.TargetFieldBasicBindingQueryReference
 implements TargetFieldClassifierBindingQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public TargetFieldClassifierBindingQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding targetFieldClassifierBinding)
    {
      if(targetFieldClassifierBinding == null) return this.EQ((java.lang.String)null);
      return this.EQ(targetFieldClassifierBinding.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding targetFieldClassifierBinding)
    {
      if(targetFieldClassifierBinding == null) return this.NE((java.lang.String)null);
      return this.NE(targetFieldClassifierBinding.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargetFieldClassifierBindingQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQuery.TargetFieldBasicBindingQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargetFieldClassifierBindingQueryMultiReference extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQuery.TargetFieldBasicBindingQueryMultiReference
 implements TargetFieldClassifierBindingQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public TargetFieldClassifierBindingQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding)  {

      String[] itemIdArray = new String[targetFieldClassifierBinding.length]; 

      for (int i=0; i<targetFieldClassifierBinding.length; i++)
      {
        itemIdArray[i] = targetFieldClassifierBinding[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding)  {

      String[] itemIdArray = new String[targetFieldClassifierBinding.length]; 

      for (int i=0; i<targetFieldClassifierBinding.length; i++)
      {
        itemIdArray[i] = targetFieldClassifierBinding[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding)  {

      String[] itemIdArray = new String[targetFieldClassifierBinding.length]; 

      for (int i=0; i<targetFieldClassifierBinding.length; i++)
      {
        itemIdArray[i] = targetFieldClassifierBinding[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding)  {

      String[] itemIdArray = new String[targetFieldClassifierBinding.length]; 

      for (int i=0; i<targetFieldClassifierBinding.length; i++)
      {
        itemIdArray[i] = targetFieldClassifierBinding[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldClassifierBinding ... targetFieldClassifierBinding)  {

      String[] itemIdArray = new String[targetFieldClassifierBinding.length]; 

      for (int i=0; i<targetFieldClassifierBinding.length; i++)
      {
        itemIdArray[i] = targetFieldClassifierBinding[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
