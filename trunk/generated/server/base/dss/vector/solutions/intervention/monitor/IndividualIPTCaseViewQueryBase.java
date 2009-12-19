package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 1844395679)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualIPTCaseView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class IndividualIPTCaseViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1844395679;

  public IndividualIPTCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public IndividualIPTCaseViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFacility()
  {
    return getFacility(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFacility(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.FACILITY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getFacility(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.FACILITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPatient()
  {
    return getPatient(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPatient(String alias)
  {

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.PATIENT, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPatient(String alias, String displayLabel)
  {

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.PATIENT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getResidentialLocation()
  {
    return getResidentialLocation(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getResidentialLocation(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.RESIDENTIALLOCATION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getResidentialLocation(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.RESIDENTIALLOCATION, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getServiceDate()
  {
    return getServiceDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getServiceDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.SERVICEDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getServiceDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualIPTCaseView.SERVICEDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends IndividualIPTCaseView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<IndividualIPTCaseView>(this.getMdClassIF(), valueIterator);
  }

}
