package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -1733658108)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ExcelImportHistory.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class ExcelImportHistoryQuery extends com.runwaysdk.system.scheduler.JobHistoryQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public ExcelImportHistoryQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public ExcelImportHistoryQuery(com.runwaysdk.query.ValueQuery valueQuery)
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
    return dss.vector.solutions.ExcelImportHistory.CLASS;
  }
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile()
  {
    return getErrorFile(null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.ExcelImportHistory.ERRORFILE);

    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ExcelImportHistory.ERRORFILE, mdAttributeIF, this, alias, null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias, String displayLabel)
  {

    com.runwaysdk.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap(dss.vector.solutions.ExcelImportHistory.ERRORFILE);

    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.ExcelImportHistory.ERRORFILE, mdAttributeIF, this, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getFileName()
  {
    return getFileName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getHasError()
  {
    return getHasError(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getImportCount()
  {
    return getImportCount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos()
  {
    return getSerializedUnknownGeos(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms()
  {
    return getSerializedUnknownTerms(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTotalRecords()
  {
    return getTotalRecords(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getComponentQuery().get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.ExcelImportHistory.ERRORFILE)) 
    {
       return new com.runwaysdk.system.VaultFileQuery.VaultFileQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public com.runwaysdk.query.OIterator<? extends ExcelImportHistory> getIterator()
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
    return new com.runwaysdk.business.BusinessIterator<ExcelImportHistory>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ExcelImportHistoryQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.scheduler.JobHistoryQuery.JobHistoryQueryReferenceIF
  {

    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile();
    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias);
    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getFileName();
    public com.runwaysdk.query.SelectableChar getFileName(String alias);
    public com.runwaysdk.query.SelectableChar getFileName(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableBoolean getHasError();
    public com.runwaysdk.query.SelectableBoolean getHasError(String alias);
    public com.runwaysdk.query.SelectableBoolean getHasError(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getImportCount();
    public com.runwaysdk.query.SelectableInteger getImportCount(String alias);
    public com.runwaysdk.query.SelectableInteger getImportCount(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos();
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms();
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getTotalRecords();
    public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias);
    public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias, String displayLabel);

    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.ExcelImportHistory excelImportHistory);

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.ExcelImportHistory excelImportHistory);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ExcelImportHistoryQueryReference extends com.runwaysdk.system.scheduler.JobHistoryQuery.JobHistoryQueryReference
 implements ExcelImportHistoryQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public ExcelImportHistoryQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(dss.vector.solutions.ExcelImportHistory excelImportHistory)
    {
      if(excelImportHistory == null) return this.EQ((java.lang.String)null);
      return this.EQ(excelImportHistory.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(dss.vector.solutions.ExcelImportHistory excelImportHistory)
    {
      if(excelImportHistory == null) return this.NE((java.lang.String)null);
      return this.NE(excelImportHistory.getId());
    }

  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile()
  {
    return getErrorFile(null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias)
  {
    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.get(dss.vector.solutions.ExcelImportHistory.ERRORFILE, alias, null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.get(dss.vector.solutions.ExcelImportHistory.ERRORFILE,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getFileName()
  {
    return getFileName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getHasError()
  {
    return getHasError(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getImportCount()
  {
    return getImportCount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos()
  {
    return getSerializedUnknownGeos(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms()
  {
    return getSerializedUnknownTerms(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTotalRecords()
  {
    return getTotalRecords(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.ExcelImportHistory.ERRORFILE)) 
    {
       return new com.runwaysdk.system.VaultFileQuery.VaultFileQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
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
  public interface ExcelImportHistoryQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.system.scheduler.JobHistoryQuery.JobHistoryQueryMultiReferenceIF
  {

    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile();
    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias);
    public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getFileName();
    public com.runwaysdk.query.SelectableChar getFileName(String alias);
    public com.runwaysdk.query.SelectableChar getFileName(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableBoolean getHasError();
    public com.runwaysdk.query.SelectableBoolean getHasError(String alias);
    public com.runwaysdk.query.SelectableBoolean getHasError(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getImportCount();
    public com.runwaysdk.query.SelectableInteger getImportCount(String alias);
    public com.runwaysdk.query.SelectableInteger getImportCount(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos();
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms();
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias);
    public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableInteger getTotalRecords();
    public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias);
    public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias, String displayLabel);

    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.ExcelImportHistory ... excelImportHistory);
    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.ExcelImportHistory ... excelImportHistory);
    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.ExcelImportHistory ... excelImportHistory);
    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.ExcelImportHistory ... excelImportHistory);
    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.ExcelImportHistory ... excelImportHistory);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ExcelImportHistoryQueryMultiReference extends com.runwaysdk.system.scheduler.JobHistoryQuery.JobHistoryQueryMultiReference
 implements ExcelImportHistoryQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public ExcelImportHistoryQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(dss.vector.solutions.ExcelImportHistory ... excelImportHistory)  {

      String[] itemIdArray = new String[excelImportHistory.length]; 

      for (int i=0; i<excelImportHistory.length; i++)
      {
        itemIdArray[i] = excelImportHistory[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(dss.vector.solutions.ExcelImportHistory ... excelImportHistory)  {

      String[] itemIdArray = new String[excelImportHistory.length]; 

      for (int i=0; i<excelImportHistory.length; i++)
      {
        itemIdArray[i] = excelImportHistory[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(dss.vector.solutions.ExcelImportHistory ... excelImportHistory)  {

      String[] itemIdArray = new String[excelImportHistory.length]; 

      for (int i=0; i<excelImportHistory.length; i++)
      {
        itemIdArray[i] = excelImportHistory[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(dss.vector.solutions.ExcelImportHistory ... excelImportHistory)  {

      String[] itemIdArray = new String[excelImportHistory.length]; 

      for (int i=0; i<excelImportHistory.length; i++)
      {
        itemIdArray[i] = excelImportHistory[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(dss.vector.solutions.ExcelImportHistory ... excelImportHistory)  {

      String[] itemIdArray = new String[excelImportHistory.length]; 

      for (int i=0; i<excelImportHistory.length; i++)
      {
        itemIdArray[i] = excelImportHistory[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile()
  {
    return getErrorFile(null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias)
  {
    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.get(dss.vector.solutions.ExcelImportHistory.ERRORFILE, alias, null);

  }
 
  public com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF getErrorFile(String alias, String displayLabel)
  {
    return (com.runwaysdk.system.VaultFileQuery.VaultFileQueryReferenceIF)this.get(dss.vector.solutions.ExcelImportHistory.ERRORFILE,  alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getFileName()
  {
    return getFileName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFileName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.FILENAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getHasError()
  {
    return getHasError(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getHasError(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.get(dss.vector.solutions.ExcelImportHistory.HASERROR, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getImportCount()
  {
    return getImportCount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getImportCount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.IMPORTCOUNT, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos()
  {
    return getSerializedUnknownGeos(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownGeos(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNGEOS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms()
  {
    return getSerializedUnknownTerms(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSerializedUnknownTerms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.ExcelImportHistory.SERIALIZEDUNKNOWNTERMS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getTotalRecords()
  {
    return getTotalRecords(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTotalRecords(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.get(dss.vector.solutions.ExcelImportHistory.TOTALRECORDS, alias, displayLabel);

  }
  protected com.runwaysdk.query.AttributeReference referenceFactory( com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals(dss.vector.solutions.ExcelImportHistory.ERRORFILE)) 
    {
       return new com.runwaysdk.system.VaultFileQuery.VaultFileQueryReference((com.runwaysdk.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      return super.referenceFactory(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
  }

  }
}
