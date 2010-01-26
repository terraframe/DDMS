package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 59976421)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultAgeRange.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultAgeRangeQuery extends com.terraframe.mojo.query.GeneratedStructQuery implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 59976421;

  public AdultAgeRangeQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.StructQuery structQuery = componentQueryFactory.structQuery(this.getClassType());

       this.setStructQuery(structQuery);
    }
  }

  public AdultAgeRangeQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.StructQuery structQuery = new com.terraframe.mojo.business.StructQuery(valueQuery, this.getClassType());

       this.setStructQuery(structQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.entomology.assay.AdultAgeRange.CLASS;
  }
  public com.terraframe.mojo.query.AttributeInteger getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.ENDPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.ENDPOINT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.STARTPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getComponentQuery().get(dss.vector.solutions.entomology.assay.AdultAgeRange.STARTPOINT, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AdultAgeRange> getIterator()
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
    return new com.terraframe.mojo.business.StructIterator<AdultAgeRange>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a struct attribute.
 **/
  public interface AdultAgeRangeQueryStructIF extends com.terraframe.mojo.query.SelectableStruct, com.terraframe.mojo.generation.loader.Reloadable
  {

    public com.terraframe.mojo.query.AttributeInteger getEndPoint();
    public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias);
    public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getId();
    public com.terraframe.mojo.query.AttributeChar getId(String alias);
    public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getKeyName();
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster();
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeInteger getStartPoint();
    public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias);
    public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias, String displayLabel);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a struct attribute.
 **/
  public static class AdultAgeRangeQueryStruct extends com.terraframe.mojo.query.AttributeStruct implements AdultAgeRangeQueryStructIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 650720865;

  public AdultAgeRangeQueryStruct(com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }

  public com.terraframe.mojo.query.AttributeInteger getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.ENDPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getEndPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.ENDPOINT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.STARTPOINT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStartPoint(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.get(dss.vector.solutions.entomology.assay.AdultAgeRange.STARTPOINT, alias, displayLabel);

  }
  }
}
