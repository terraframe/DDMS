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

@com.runwaysdk.business.ClassSignature(hash = -873005744)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WellKnownNamesMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class WellKnownNamesMasterQuery extends com.runwaysdk.system.EnumerationMasterQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public WellKnownNamesMasterQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public WellKnownNamesMasterQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.query.WellKnownNamesMaster.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends WellKnownNamesMaster> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<WellKnownNamesMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition enum_WellKnownNames()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.WellKnownNames.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition notEnum_WellKnownNames()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.query.WellKnownNames.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface WellKnownNamesMasterQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.WellKnownNamesMaster wellKnownNamesMaster);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.WellKnownNamesMaster wellKnownNamesMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class WellKnownNamesMasterQueryReference extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements WellKnownNamesMasterQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public WellKnownNamesMasterQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.query.WellKnownNamesMaster wellKnownNamesMaster)
    {
      if(wellKnownNamesMaster == null) return this.EQ((java.lang.String)null);
      return this.EQ(wellKnownNamesMaster.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.query.WellKnownNamesMaster wellKnownNamesMaster)
    {
      if(wellKnownNamesMaster == null) return this.NE((java.lang.String)null);
      return this.NE(wellKnownNamesMaster.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface WellKnownNamesMasterQueryEnumerationIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {


  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class WellKnownNamesMasterQueryEnumeration extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements WellKnownNamesMasterQueryEnumerationIF, com.runwaysdk.generation.loader.Reloadable
  {

  public WellKnownNamesMasterQueryEnumeration(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.query.WellKnownNames.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface WellKnownNamesQueryIF extends com.runwaysdk.generation.loader.Reloadable, WellKnownNamesMasterQueryEnumerationIF  {

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.WellKnownNames ... wellKnownNames);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.WellKnownNames ... wellKnownNames);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.WellKnownNames ... wellKnownNames);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.WellKnownNames ... wellKnownNames);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.WellKnownNames ... wellKnownNames);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.query.WellKnownNames.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class WellKnownNamesQuery extends WellKnownNamesMasterQueryEnumeration implements  WellKnownNamesQueryIF, com.runwaysdk.generation.loader.Reloadable
  {
  public WellKnownNamesQuery(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.WellKnownNames ... wellKnownNames)  {

      String[] enumIdArray = new String[wellKnownNames.length]; 

      for (int i=0; i<wellKnownNames.length; i++)
      {
        enumIdArray[i] = wellKnownNames[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.WellKnownNames ... wellKnownNames)  {

      String[] enumIdArray = new String[wellKnownNames.length]; 

      for (int i=0; i<wellKnownNames.length; i++)
      {
        enumIdArray[i] = wellKnownNames[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.WellKnownNames ... wellKnownNames)  {

      String[] enumIdArray = new String[wellKnownNames.length]; 

      for (int i=0; i<wellKnownNames.length; i++)
      {
        enumIdArray[i] = wellKnownNames[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.WellKnownNames ... wellKnownNames)  {

      String[] enumIdArray = new String[wellKnownNames.length]; 

      for (int i=0; i<wellKnownNames.length; i++)
      {
        enumIdArray[i] = wellKnownNames[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.WellKnownNames ... wellKnownNames)  {

      String[] enumIdArray = new String[wellKnownNames.length]; 

      for (int i=0; i<wellKnownNames.length; i++)
      {
        enumIdArray[i] = wellKnownNames[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }
/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface WellKnownNamesMasterQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class WellKnownNamesMasterQueryMultiReference extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryMultiReference
 implements WellKnownNamesMasterQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public WellKnownNamesMasterQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster)  {

      String[] itemIdArray = new String[wellKnownNamesMaster.length]; 

      for (int i=0; i<wellKnownNamesMaster.length; i++)
      {
        itemIdArray[i] = wellKnownNamesMaster[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster)  {

      String[] itemIdArray = new String[wellKnownNamesMaster.length]; 

      for (int i=0; i<wellKnownNamesMaster.length; i++)
      {
        itemIdArray[i] = wellKnownNamesMaster[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster)  {

      String[] itemIdArray = new String[wellKnownNamesMaster.length]; 

      for (int i=0; i<wellKnownNamesMaster.length; i++)
      {
        itemIdArray[i] = wellKnownNamesMaster[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster)  {

      String[] itemIdArray = new String[wellKnownNamesMaster.length]; 

      for (int i=0; i<wellKnownNamesMaster.length; i++)
      {
        itemIdArray[i] = wellKnownNamesMaster[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.query.WellKnownNamesMaster ... wellKnownNamesMaster)  {

      String[] itemIdArray = new String[wellKnownNamesMaster.length]; 

      for (int i=0; i<wellKnownNamesMaster.length; i++)
      {
        itemIdArray[i] = wellKnownNamesMaster[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
