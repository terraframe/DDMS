package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1347548548)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class PersonExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1347548548;

  public PersonExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PersonExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.PersonExcelView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth()
  {
    return getDateOfBirth(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.PersonExcelView.DATEOFBIRTH, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDateOfBirth(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.PersonExcelView.DATEOFBIRTH, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.FIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.FIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsIPTRecipient()
  {
    return getIsIPTRecipient(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsIPTRecipient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISIPTRECIPIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsIPTRecipient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISIPTRECIPIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsITNRecipient()
  {
    return getIsITNRecipient(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsITNRecipient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISITNRECIPIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsITNRecipient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISITNRECIPIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsMDSSUser()
  {
    return getIsMDSSUser(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsMDSSUser(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISMDSSUSER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsMDSSUser(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISMDSSUSER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsPatient()
  {
    return getIsPatient(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsPatient(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISPATIENT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsPatient(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISPATIENT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayLeader()
  {
    return getIsSprayLeader(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayLeader(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISSPRAYLEADER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayLeader(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISSPRAYLEADER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayOperator()
  {
    return getIsSprayOperator(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayOperator(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISSPRAYOPERATOR, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getIsSprayOperator(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.PersonExcelView.ISSPRAYOPERATOR, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.LASTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLastName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.LASTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLeaderId()
  {
    return getLeaderId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLeaderId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.LEADERID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getLeaderId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.LEADERID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getOperatorId()
  {
    return getOperatorId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getOperatorId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.OPERATORID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getOperatorId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.OPERATORID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPassword()
  {
    return getPassword(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPassword(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.PASSWORD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getPassword(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.PASSWORD, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidentialGeoEntity()
  {
    return getResidentialGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidentialGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PersonExcelView.RESIDENTIALGEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getResidentialGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PersonExcelView.RESIDENTIALGEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getSex()
  {
    return getSex(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.SEX, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getSex(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.SEX, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername()
  {
    return getUsername(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.USERNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getUsername(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.PersonExcelView.USERNAME, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkGeoEntity()
  {
    return getWorkGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PersonExcelView.WORKGEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getWorkGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PersonExcelView.WORKGEOENTITY, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends PersonExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<PersonExcelView>(this.getMdClassIF(), valueIterator);
  }

}
