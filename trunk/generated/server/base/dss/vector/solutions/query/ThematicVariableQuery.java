package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1487323092)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThematicVariable.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class ThematicVariableQuery extends com.terraframe.mojo.query.GeneratedBusinessQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1487323092;

  public ThematicVariableQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public ThematicVariableQuery(com.terraframe.mojo.query.ValueQuery valueQuery)
  {
     super();
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = new com.terraframe.mojo.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.query.ThematicVariable.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getAttributeName()
  {
    return getAttributeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ATTRIBUTENAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ATTRIBUTENAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.CREATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("createdBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.CREATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.DISPLAYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.DISPLAYLABEL, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getEntityAlias()
  {
    return getEntityAlias(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ENTITYALIAS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ENTITYALIAS, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.ENTITYDOMAIN, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("entityDomain");

    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.ENTITYDOMAIN, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lastUpdatedBy");

    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.LOCKEDBY, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("lockedBy");

    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.LOCKEDBY, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.OWNER, mdAttributeIF, this, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {

    com.terraframe.mojo.dataaccess.MdAttributeDAOIF mdAttributeIF = this.getComponentQuery().getMdAttributeROfromMap("owner");

    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.getComponentQuery().internalAttributeFactory(dss.vector.solutions.query.ThematicVariable.OWNER, mdAttributeIF, this, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.TYPE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getUserAlias()
  {
    return getUserAlias(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.USERALIAS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getComponentQuery().get(dss.vector.solutions.query.ThematicVariable.USERALIAS, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.terraframe.mojo.system.UsersQuery.UsersQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends ThematicVariable> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<ThematicVariable>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface ThematicVariableQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, com.terraframe.mojo.query.SelectableReference
  {

    public com.terraframe.mojo.query.AttributeChar getAttributeName();
    public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias);
    public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeMoment getCreateDate();
    public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias);
    public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getDisplayLabel();
    public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias);
    public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getEntityAlias();
    public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias);
    public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias, String displayLabel);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain();
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias);
    public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getId();
    public com.terraframe.mojo.query.AttributeChar getId(String alias);
    public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getKeyName();
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias);
    public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate();
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias);
    public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy();
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias);
    public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy();
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias);
    public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner();
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias);
    public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeLong getSeq();
    public com.terraframe.mojo.query.AttributeLong getSeq(String alias);
    public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster();
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias);
    public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getType();
    public com.terraframe.mojo.query.AttributeChar getType(String alias);
    public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel);
    public com.terraframe.mojo.query.AttributeChar getUserAlias();
    public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias);
    public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.ThematicVariable thematicVariable);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.ThematicVariable thematicVariable);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class ThematicVariableQueryReference extends com.terraframe.mojo.query.AttributeReference
 implements ThematicVariableQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1209196658;

  public ThematicVariableQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.query.ThematicVariable thematicVariable)
    {
      return this.EQ(thematicVariable.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.query.ThematicVariable thematicVariable)
    {
      return this.NE(thematicVariable.getId());
    }

  public com.terraframe.mojo.query.AttributeChar getAttributeName()
  {
    return getAttributeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ATTRIBUTENAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAttributeName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ATTRIBUTENAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getCreateDate()
  {
    return getCreateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.query.ThematicVariable.CREATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCreateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.query.ThematicVariable.CREATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy()
  {
    return getCreatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.CREATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getCreatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.CREATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.DISPLAYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.DISPLAYLABEL, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getEntityAlias()
  {
    return getEntityAlias(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ENTITYALIAS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getEntityAlias(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ENTITYALIAS, alias, displayLabel);

  }
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain()
  {
    return getEntityDomain(null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.ENTITYDOMAIN, alias, null);

  }
 
  public com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF getEntityDomain(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.ENTITYDOMAIN,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.ID, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getKeyName()
  {
    return getKeyName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.KEYNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getKeyName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.KEYNAME, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate()
  {
    return getLastUpdateDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getLastUpdateDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDATE, alias, displayLabel);

  }
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy()
  {
    return getLastUpdatedBy(null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF getLastUpdatedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.LASTUPDATEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy()
  {
    return getLockedBy(null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.LOCKEDBY, alias, null);

  }
 
  public com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF getLockedBy(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.UsersQuery.UsersQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.LOCKEDBY,  alias, displayLabel);

  }
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner()
  {
    return getOwner(null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.OWNER, alias, null);

  }
 
  public com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF getOwner(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.system.ActorQuery.ActorQueryReferenceIF)this.get(dss.vector.solutions.query.ThematicVariable.OWNER,  alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeLong getSeq()
  {
    return getSeq(null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.get(dss.vector.solutions.query.ThematicVariable.SEQ, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeLong getSeq(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeLong)this.get(dss.vector.solutions.query.ThematicVariable.SEQ, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSiteMaster()
  {
    return getSiteMaster(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.SITEMASTER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSiteMaster(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.SITEMASTER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getType()
  {
    return getType(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.TYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.TYPE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getUserAlias()
  {
    return getUserAlias(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.USERALIAS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserAlias(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.get(dss.vector.solutions.query.ThematicVariable.USERALIAS, alias, displayLabel);

  }
  protected com.terraframe.mojo.query.AttributeReference referenceFactory( com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias,  com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    String name = mdAttributeIF.definesAttribute();
    
    if (name.equals("createdBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("entityDomain")) 
    {
       return new com.terraframe.mojo.system.metadata.MdDomainQuery.MdDomainQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lastUpdatedBy")) 
    {
       return new com.terraframe.mojo.system.SingleActorQuery.SingleActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("lockedBy")) 
    {
       return new com.terraframe.mojo.system.UsersQuery.UsersQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else if (name.equals("owner")) 
    {
       return new com.terraframe.mojo.system.ActorQuery.ActorQueryReference((com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF)mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, userDefinedAlias, userDefinedDisplayLabel);
    }
    else 
    {
      String error = "Attribute type ["+mdAttributeIF.getType()+"] is invalid.";
      throw new com.terraframe.mojo.query.QueryException(error);
    }
  }

  }
}
