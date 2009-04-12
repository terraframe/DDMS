package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultAgeRange.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class AdultAgeRangeQuery extends com.terraframe.mojo.query.GeneratedStructQuery implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239517559432L;

  public AdultAgeRangeQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.StructQuery structQuery = componentQueryFactory.structQuery(this.getClassType());

       this.setStructQuery(structQuery);
    }
  }

  public String getClassType()
  {
    return "dss.vector.solutions.entomology.assay.AdultAgeRange";
  }
  public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultAgeRange.ENDPOINT, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultAgeRange.ID, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultAgeRange.KEYNAME, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultAgeRange.SITEMASTER, "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.entomology.assay.AdultAgeRange.STARTPOINT, "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends AdultAgeRange> getIterator()
  {
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
  public interface AdultAgeRangeQueryStructIF extends com.terraframe.mojo.query.AttributeStructIF, com.terraframe.mojo.generation.loader.Reloadable
  {

    public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint();
    public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getId();
    public com.terraframe.mojo.query.AttributeCharIF getId(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getKeyName();
    public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster();
    public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint();
    public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint(String alias);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a struct attribute.
 **/
  public static class AdultAgeRangeQueryStruct extends com.terraframe.mojo.query.AttributeStruct implements AdultAgeRangeQueryStructIF, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239517559458L;

  public AdultAgeRangeQueryStruct(com.terraframe.mojo.dataaccess.MdAttributeStructDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdStructDAOIF mdStructIF, String structTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdStructIF, structTableAlias, rootQuery, tableJoinSet, alias);

  }

  public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint()
  {
    return getEndPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getEndPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("endPoint", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("id", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("keyName", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("siteMaster", "com.terraframe.mojo.system.metadata.MdAttributeCharacter", alias);

  }
  public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint()
  {
    return getStartPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeIntegerIF getStartPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeIntegerIF)this.attributeFactory("startPoint", "com.terraframe.mojo.system.metadata.MdAttributeInteger", alias);

  }
  }
}
