package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -2051347786)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSUserView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class MDSSUserViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -2051347786;

  public MDSSUserViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MDSSUserViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.MDSSUserView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.FIRSTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.FIRSTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.LASTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.LASTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getPersonId()
  {
    return getPersonId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPersonId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.PERSONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPersonId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.PERSONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles()
  {
    return getRoles(null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ROLES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.ROLES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUserId()
  {
    return getUserId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUserId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUserId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername()
  {
    return getUsername(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.MDSSUserView.USERNAME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends MDSSUserView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<MDSSUserView>(this.getMdClassIF(), valueIterator);
  }

}
