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
package dss.vector.solutions.geo.generated;

@com.runwaysdk.business.ClassSignature(hash = 1963078719)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayZone.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class SprayZoneQuery extends dss.vector.solutions.geo.generated.GeoEntityQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public SprayZoneQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public SprayZoneQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.geo.generated.SprayZone.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends SprayZone> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<SprayZone>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SprayZoneQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.SprayZone sprayZone);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.SprayZone sprayZone);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SprayZoneQueryReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements SprayZoneQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public SprayZoneQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.geo.generated.SprayZone sprayZone)
    {
      if(sprayZone == null) return this.EQ((java.lang.String)null);
      return this.EQ(sprayZone.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.geo.generated.SprayZone sprayZone)
    {
      if(sprayZone == null) return this.NE((java.lang.String)null);
      return this.NE(sprayZone.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SprayZoneQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.geo.generated.SprayZone ... sprayZone);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.geo.generated.SprayZone ... sprayZone);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.geo.generated.SprayZone ... sprayZone);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.geo.generated.SprayZone ... sprayZone);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.geo.generated.SprayZone ... sprayZone);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SprayZoneQueryMultiReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryMultiReference
 implements SprayZoneQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public SprayZoneQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.geo.generated.SprayZone ... sprayZone)  {

      String[] itemIdArray = new String[sprayZone.length]; 

      for (int i=0; i<sprayZone.length; i++)
      {
        itemIdArray[i] = sprayZone[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.geo.generated.SprayZone ... sprayZone)  {

      String[] itemIdArray = new String[sprayZone.length]; 

      for (int i=0; i<sprayZone.length; i++)
      {
        itemIdArray[i] = sprayZone[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.geo.generated.SprayZone ... sprayZone)  {

      String[] itemIdArray = new String[sprayZone.length]; 

      for (int i=0; i<sprayZone.length; i++)
      {
        itemIdArray[i] = sprayZone[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.geo.generated.SprayZone ... sprayZone)  {

      String[] itemIdArray = new String[sprayZone.length]; 

      for (int i=0; i<sprayZone.length; i++)
      {
        itemIdArray[i] = sprayZone[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.geo.generated.SprayZone ... sprayZone)  {

      String[] itemIdArray = new String[sprayZone.length]; 

      for (int i=0; i<sprayZone.length; i++)
      {
        itemIdArray[i] = sprayZone[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}
