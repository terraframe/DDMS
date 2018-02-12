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
package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1277241346)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class LarvaeDiscriminatingDoseAssayQuery extends dss.vector.solutions.entomology.assay.LarvaeAssayQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public LarvaeDiscriminatingDoseAssayQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public LarvaeDiscriminatingDoseAssayQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS;
  }
  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt50()
  {
    return getLt50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt95()
  {
    return getLt95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends LarvaeDiscriminatingDoseAssay> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<LarvaeDiscriminatingDoseAssay>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LarvaeDiscriminatingDoseAssayQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryReferenceIF
  {

    public com.runwaysdk.query.SelectableFloat getControlTestMortality();
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getHoldingTime();
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias);
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getLt50();
    public com.runwaysdk.query.SelectableDouble getLt50(String alias);
    public com.runwaysdk.query.SelectableDouble getLt50(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getLt95();
    public com.runwaysdk.query.SelectableDouble getLt95(String alias);
    public com.runwaysdk.query.SelectableDouble getLt95(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableFloat getMortality();
    public com.runwaysdk.query.SelectableFloat getMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityDead();
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityLive();
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId();
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LarvaeDiscriminatingDoseAssayQueryReference extends dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryReference
 implements LarvaeDiscriminatingDoseAssayQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public LarvaeDiscriminatingDoseAssayQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      if(larvaeDiscriminatingDoseAssay == null) return this.EQ((java.lang.String)null);
      return this.EQ(larvaeDiscriminatingDoseAssay.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay larvaeDiscriminatingDoseAssay)
    {
      if(larvaeDiscriminatingDoseAssay == null) return this.NE((java.lang.String)null);
      return this.NE(larvaeDiscriminatingDoseAssay.getId());
    }

  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt50()
  {
    return getLt50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt95()
  {
    return getLt95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, displayLabel);

  }
  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LarvaeDiscriminatingDoseAssayQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryMultiReferenceIF
  {

    public com.runwaysdk.query.SelectableFloat getControlTestMortality();
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getHoldingTime();
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias);
    public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getLt50();
    public com.runwaysdk.query.SelectableDouble getLt50(String alias);
    public com.runwaysdk.query.SelectableDouble getLt50(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableDouble getLt95();
    public com.runwaysdk.query.SelectableDouble getLt95(String alias);
    public com.runwaysdk.query.SelectableDouble getLt95(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableFloat getMortality();
    public com.runwaysdk.query.SelectableFloat getMortality(String alias);
    public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityDead();
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getQuantityLive();
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias);
    public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId();
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias);
    public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LarvaeDiscriminatingDoseAssayQueryMultiReference extends dss.vector.solutions.entomology.assay.LarvaeAssayQuery.LarvaeAssayQueryMultiReference
 implements LarvaeDiscriminatingDoseAssayQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public LarvaeDiscriminatingDoseAssayQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay)  {

      String[] itemIdArray = new String[larvaeDiscriminatingDoseAssay.length]; 

      for (int i=0; i<larvaeDiscriminatingDoseAssay.length; i++)
      {
        itemIdArray[i] = larvaeDiscriminatingDoseAssay[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay)  {

      String[] itemIdArray = new String[larvaeDiscriminatingDoseAssay.length]; 

      for (int i=0; i<larvaeDiscriminatingDoseAssay.length; i++)
      {
        itemIdArray[i] = larvaeDiscriminatingDoseAssay[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay)  {

      String[] itemIdArray = new String[larvaeDiscriminatingDoseAssay.length]; 

      for (int i=0; i<larvaeDiscriminatingDoseAssay.length; i++)
      {
        itemIdArray[i] = larvaeDiscriminatingDoseAssay[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay)  {

      String[] itemIdArray = new String[larvaeDiscriminatingDoseAssay.length]; 

      for (int i=0; i<larvaeDiscriminatingDoseAssay.length; i++)
      {
        itemIdArray[i] = larvaeDiscriminatingDoseAssay[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay ... larvaeDiscriminatingDoseAssay)  {

      String[] itemIdArray = new String[larvaeDiscriminatingDoseAssay.length]; 

      for (int i=0; i<larvaeDiscriminatingDoseAssay.length; i++)
      {
        itemIdArray[i] = larvaeDiscriminatingDoseAssay[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.query.SelectableFloat getControlTestMortality()
  {
    return getControlTestMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getControlTestMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CONTROLTESTMORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHoldingTime()
  {
    return getHoldingTime(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHoldingTime(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.HOLDINGTIME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt50()
  {
    return getLt50(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt50(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT50, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDouble getLt95()
  {
    return getLt95(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getLt95(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.LT95, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getMortality()
  {
    return getMortality(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getMortality(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.MORTALITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityDead()
  {
    return getQuantityDead(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityDead(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYDEAD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getQuantityLive()
  {
    return getQuantityLive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getQuantityLive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.QUANTITYLIVE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.UNIQUEASSAYID, alias, displayLabel);

  }
  }
}
