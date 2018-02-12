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
package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = 1571160521)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldGeoEntityBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class TargetFieldGeoEntityBindingQuery extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public TargetFieldGeoEntityBindingQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public TargetFieldGeoEntityBindingQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.CLASS;
  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY);

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY);

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends TargetFieldGeoEntityBinding> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<TargetFieldGeoEntityBinding>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargetFieldGeoEntityBindingQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingQuery.TargetFieldBindingQueryReferenceIF
  {

    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding targetFieldGeoEntityBinding);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding targetFieldGeoEntityBinding);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargetFieldGeoEntityBindingQueryReference extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingQuery.TargetFieldBindingQueryReference
 implements TargetFieldGeoEntityBindingQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public TargetFieldGeoEntityBindingQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding targetFieldGeoEntityBinding)
    {
      if(targetFieldGeoEntityBinding == null) return this.EQ((java.lang.String)null);
      return this.EQ(targetFieldGeoEntityBinding.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding targetFieldGeoEntityBinding)
    {
      if(targetFieldGeoEntityBinding == null) return this.NE((java.lang.String)null);
      return this.NE(targetFieldGeoEntityBinding.getId());
    }

  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface TargetFieldGeoEntityBindingQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingQuery.TargetFieldBindingQueryMultiReferenceIF
  {

    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity();
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias);
    public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class TargetFieldGeoEntityBindingQueryMultiReference extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingQuery.TargetFieldBindingQueryMultiReference
 implements TargetFieldGeoEntityBindingQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public TargetFieldGeoEntityBindingQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding)  {

      String[] itemIdArray = new String[targetFieldGeoEntityBinding.length]; 

      for (int i=0; i<targetFieldGeoEntityBinding.length; i++)
      {
        itemIdArray[i] = targetFieldGeoEntityBinding[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding)  {

      String[] itemIdArray = new String[targetFieldGeoEntityBinding.length]; 

      for (int i=0; i<targetFieldGeoEntityBinding.length; i++)
      {
        itemIdArray[i] = targetFieldGeoEntityBinding[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding)  {

      String[] itemIdArray = new String[targetFieldGeoEntityBinding.length]; 

      for (int i=0; i<targetFieldGeoEntityBinding.length; i++)
      {
        itemIdArray[i] = targetFieldGeoEntityBinding[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding)  {

      String[] itemIdArray = new String[targetFieldGeoEntityBinding.length]; 

      for (int i=0; i<targetFieldGeoEntityBinding.length; i++)
      {
        itemIdArray[i] = targetFieldGeoEntityBinding[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding ... targetFieldGeoEntityBinding)  {

      String[] itemIdArray = new String[targetFieldGeoEntityBinding.length]; 

      for (int i=0; i<targetFieldGeoEntityBinding.length; i++)
      {
        itemIdArray[i] = targetFieldGeoEntityBinding[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.get(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY,  alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBinding.GEOENTITY)) 
    {
       return new dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
