package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 962022065)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LocalPropertyPropertyLabel.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class LocalPropertyPropertyLabelQuery extends com.runwaysdk.query.GeneratedStructQuery implements com.runwaysdk.generation.loader.Reloadable
{

  public LocalPropertyPropertyLabelQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.StructQuery structQuery = componentQueryFactory.structQuery(this.getClassType());

       this.setStructQuery(structQuery);
    }
  }

  public LocalPropertyPropertyLabelQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.StructQuery structQuery = new com.runwaysdk.business.StructQuery(valueQuery, this.getClassType());

       this.setStructQuery(structQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.LocalPropertyPropertyLabel.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale()
  {
    return getDENGUE_defaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.DENGUE_DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.DENGUE_DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getDefaultLocale()
  {
    return getDefaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale()
  {
    return getMALARIA_defaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.MALARIA_DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.MALARIA_DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getComponentQuery().get(dss.vector.solutions.LocalPropertyPropertyLabel.SITEMASTER, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends LocalPropertyPropertyLabel> getIterator()
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
    return new com.runwaysdk.business.StructIterator<LocalPropertyPropertyLabel>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a struct attribute.
 **/
  public interface LocalPropertyPropertyLabelQueryStructIF extends com.runwaysdk.query.AttributeLocalIF, com.runwaysdk.generation.loader.Reloadable
  {

    public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale();
    public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias);
    public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getDefaultLocale();
    public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias);
    public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getId();
    public com.runwaysdk.query.SelectableChar getId(String alias);
    public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getKeyName();
    public com.runwaysdk.query.SelectableChar getKeyName(String alias);
    public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale();
    public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias);
    public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias, String displayLabel);
    public com.runwaysdk.query.SelectableChar getSiteMaster();
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias);
    public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a struct attribute.
 **/
  public static class LocalPropertyPropertyLabelQueryStruct extends com.runwaysdk.query.AttributeLocal implements LocalPropertyPropertyLabelQueryStructIF, com.runwaysdk.generation.loader.Reloadable
  {

  public LocalPropertyPropertyLabelQueryStruct(com.runwaysdk.dataaccess.MdAttributeLocalDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdLocalStructDAOIF mdStructIF, String structTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale()
  {
    return getDENGUE_defaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.DENGUE_DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDENGUE_defaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.DENGUE_DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getDefaultLocale()
  {
    return getDefaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDefaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.ID, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.KEYNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getKeyName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.KEYNAME, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale()
  {
    return getMALARIA_defaultLocale(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.MALARIA_DEFAULTLOCALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMALARIA_defaultLocale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.MALARIA_DEFAULTLOCALE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.SITEMASTER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.get(dss.vector.solutions.LocalPropertyPropertyLabel.SITEMASTER, alias, displayLabel);

  }
  }
}
