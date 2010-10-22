package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -238076461)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SystemAlertTypeMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class SystemAlertTypeMasterQuery extends com.runwaysdk.system.EnumerationMasterQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -238076461;

  public SystemAlertTypeMasterQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public SystemAlertTypeMasterQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.general.SystemAlertTypeMaster.CLASS;
  }
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables()
  {
    return getEmailTemplateVariables(null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES);

    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, mdAttributeIF, this, alias, null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES);

    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, mdAttributeIF, this, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeLocal localFactory( com.runwaysdk.dataaccess.MdAttributeLocalDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdLocalStructDAOIF mdLocalStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES)) 
    {
       return new dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStruct((com.runwaysdk.dataaccess.MdAttributeLocalDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.localFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends SystemAlertTypeMaster> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<SystemAlertTypeMaster>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition enum_SystemAlertType()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.general.SystemAlertType.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isChildIn(relationshipQuery);
  }


/**
 * 
 **/

  public com.runwaysdk.query.Condition notEnum_SystemAlertType()
  {
    com.runwaysdk.query.QueryFactory queryFactory = this.getQueryFactory();
    com.runwaysdk.business.RelationshipQuery relationshipQuery = queryFactory.relationshipQuery(com.runwaysdk.system.metadata.EnumerationAttributeItem.CLASS);

    com.runwaysdk.business.BusinessQuery businessQuery = queryFactory.businessQuery(com.runwaysdk.system.metadata.MdEnumeration.CLASS);
    com.runwaysdk.dataaccess.MdEnumerationDAOIF mdEnumerationIF = com.runwaysdk.dataaccess.metadata.MdEnumerationDAO.getMdEnumerationDAO(dss.vector.solutions.general.SystemAlertType.CLASS); 
    businessQuery.WHERE(businessQuery.id().EQ(mdEnumerationIF.getId()));

    relationshipQuery.WHERE(relationshipQuery.hasParent(businessQuery));

    return this.getBusinessQuery().isNotChildIn(relationshipQuery);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SystemAlertTypeMasterQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReferenceIF
  {

    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables();
    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias);
    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.general.SystemAlertTypeMaster systemAlertTypeMaster);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.general.SystemAlertTypeMaster systemAlertTypeMaster);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SystemAlertTypeMasterQueryReference extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryReference
 implements SystemAlertTypeMasterQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1659640971;

  public SystemAlertTypeMasterQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.general.SystemAlertTypeMaster systemAlertTypeMaster)
    {
      if(systemAlertTypeMaster == null) return this.EQ((java.lang.String)null);
      return this.EQ(systemAlertTypeMaster.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.general.SystemAlertTypeMaster systemAlertTypeMaster)
    {
      if(systemAlertTypeMaster == null) return this.NE((java.lang.String)null);
      return this.NE(systemAlertTypeMaster.getId());
    }

  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables()
  {
    return getEmailTemplateVariables(null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias)
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.attributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, com.runwaysdk.system.metadata.MdAttributeLocalText.CLASS, alias, null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.attributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, com.runwaysdk.system.metadata.MdAttributeLocalText.CLASS, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeLocal localFactory( com.runwaysdk.dataaccess.MdAttributeLocalDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdLocalStructDAOIF mdLocalStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES)) 
    {
       return new dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStruct((com.runwaysdk.dataaccess.MdAttributeLocalDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.localFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface SystemAlertTypeMasterQueryEnumerationIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumerationIF
  {

    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables();
    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias);
    public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class SystemAlertTypeMasterQueryEnumeration extends com.runwaysdk.system.EnumerationMasterQuery.EnumerationMasterQueryEnumeration
 implements SystemAlertTypeMasterQueryEnumerationIF, com.runwaysdk.generation.loader.Reloadable
  {
private static final long serialVersionUID = -1699312637;

  public SystemAlertTypeMasterQueryEnumeration(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables()
  {
    return getEmailTemplateVariables(null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias)
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.attributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, com.runwaysdk.system.metadata.MdAttributeLocalText.CLASS, alias, null);

  }
 
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF getEmailTemplateVariables(String alias, String displayLabel)
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStructIF)this.attributeFactory(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES, com.runwaysdk.system.metadata.MdAttributeLocalText.CLASS, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeLocal localFactory( com.runwaysdk.dataaccess.MdAttributeLocalDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdLocalStructDAOIF mdLocalStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.general.SystemAlertTypeMaster.EMAILTEMPLATEVARIABLES)) 
    {
       return new dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesQuery.SystemAlertTypeMasterEmailTemplateVariablesQueryStruct((com.runwaysdk.dataaccess.MdAttributeLocalDAOIF)mdAttributeIF,  attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.localFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdLocalStructIF, structTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }

/**
 * Specifies type safe query methods for the enumeration dss.vector.solutions.general.SystemAlertType.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public interface SystemAlertTypeQueryIF extends com.runwaysdk.generation.loader.Reloadable, SystemAlertTypeMasterQueryEnumerationIF  {

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.general.SystemAlertType ... systemAlertType);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.general.SystemAlertType ... systemAlertType);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.general.SystemAlertType ... systemAlertType);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.general.SystemAlertType ... systemAlertType);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.general.SystemAlertType ... systemAlertType);
  }

/**
 * Implements type safe query methods for the enumeration dss.vector.solutions.general.SystemAlertType.
 * This type is used when a join is performed on this class as an enumeration.
 **/
  public static class SystemAlertTypeQuery extends SystemAlertTypeMasterQueryEnumeration implements  SystemAlertTypeQueryIF, com.runwaysdk.generation.loader.Reloadable
  {
  public SystemAlertTypeQuery(com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdEnumerationTableName,com.runwaysdk.dataaccess.MdBusinessDAOIF masterMdBusinessIF, String masterTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdEnumerationTableName, masterMdBusinessIF, masterTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.general.SystemAlertType ... systemAlertType)  {

      String[] enumIdArray = new String[systemAlertType.length]; 

      for (int i=0; i<systemAlertType.length; i++)
      {
        enumIdArray[i] = systemAlertType[i].getId();
      }

      return this.containsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.general.SystemAlertType ... systemAlertType)  {

      String[] enumIdArray = new String[systemAlertType.length]; 

      for (int i=0; i<systemAlertType.length; i++)
      {
        enumIdArray[i] = systemAlertType[i].getId();
      }

      return this.notContainsAny(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.general.SystemAlertType ... systemAlertType)  {

      String[] enumIdArray = new String[systemAlertType.length]; 

      for (int i=0; i<systemAlertType.length; i++)
      {
        enumIdArray[i] = systemAlertType[i].getId();
      }

      return this.containsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.general.SystemAlertType ... systemAlertType)  {

      String[] enumIdArray = new String[systemAlertType.length]; 

      for (int i=0; i<systemAlertType.length; i++)
      {
        enumIdArray[i] = systemAlertType[i].getId();
      }

      return this.notContainsAll(enumIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.general.SystemAlertType ... systemAlertType)  {

      String[] enumIdArray = new String[systemAlertType.length]; 

      for (int i=0; i<systemAlertType.length; i++)
      {
        enumIdArray[i] = systemAlertType[i].getId();
      }

      return this.containsExactly(enumIdArray);
  }
  }}
