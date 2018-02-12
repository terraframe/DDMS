/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 508312716)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DefaultSavedMap.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DefaultSavedMapQuery extends dss.vector.solutions.query.SavedMapQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DefaultSavedMapQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DefaultSavedMapQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.DefaultSavedMap.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends DefaultSavedMap> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<DefaultSavedMap>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DefaultSavedMapQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.query.SavedMapQuery.SavedMapQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.DefaultSavedMap defaultSavedMap);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.DefaultSavedMap defaultSavedMap);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DefaultSavedMapQueryReference extends dss.vector.solutions.query.SavedMapQuery.SavedMapQueryReference
 implements DefaultSavedMapQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DefaultSavedMapQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.DefaultSavedMap defaultSavedMap)
    {
      if(defaultSavedMap == null) return this.EQ((java.lang.String)null);
      return this.EQ(defaultSavedMap.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.DefaultSavedMap defaultSavedMap)
    {
      if(defaultSavedMap == null) return this.NE((java.lang.String)null);
      return this.NE(defaultSavedMap.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DefaultSavedMapQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.query.SavedMapQuery.SavedMapQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DefaultSavedMapQueryMultiReference extends dss.vector.solutions.query.SavedMapQuery.SavedMapQueryMultiReference
 implements DefaultSavedMapQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DefaultSavedMapQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap)  {

      String[] itemIdArray = new String[defaultSavedMap.length]; 

      for (int i=0; i<defaultSavedMap.length; i++)
      {
        itemIdArray[i] = defaultSavedMap[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap)  {

      String[] itemIdArray = new String[defaultSavedMap.length]; 

      for (int i=0; i<defaultSavedMap.length; i++)
      {
        itemIdArray[i] = defaultSavedMap[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap)  {

      String[] itemIdArray = new String[defaultSavedMap.length]; 

      for (int i=0; i<defaultSavedMap.length; i++)
      {
        itemIdArray[i] = defaultSavedMap[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap)  {

      String[] itemIdArray = new String[defaultSavedMap.length]; 

      for (int i=0; i<defaultSavedMap.length; i++)
      {
        itemIdArray[i] = defaultSavedMap[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.DefaultSavedMap ... defaultSavedMap)  {

      String[] itemIdArray = new String[defaultSavedMap.length]; 

      for (int i=0; i<defaultSavedMap.length; i++)
      {
        itemIdArray[i] = defaultSavedMap[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
