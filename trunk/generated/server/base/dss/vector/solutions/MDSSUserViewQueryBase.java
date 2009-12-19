package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 429482201)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSUserView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class MDSSUserViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 429482201;

  public MDSSUserViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MDSSUserViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.MDSSUserView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.FIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.FIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.LASTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.LASTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPersonId()
  {
    return getPersonId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPersonId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.PERSONID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPersonId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.PERSONID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserId()
  {
    return getUserId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUserId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername()
  {
    return getUsername(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERNAME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends MDSSUserView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<MDSSUserView>(this.getMdClassIF(), valueIterator);
  }

}
