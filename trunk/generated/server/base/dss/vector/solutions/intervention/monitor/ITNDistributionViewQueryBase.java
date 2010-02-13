package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 2026132179)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNDistributionView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ITNDistributionViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 2026132179;

  public ITNDistributionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNDistributionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.ITNDistributionView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDouble getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDouble)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDistributionDate()
  {
    return getDistributionDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDistributionDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getDistributionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorName()
  {
    return getDistributorName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTORNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTORNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorSurname()
  {
    return getDistributorSurname(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorSurname(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTORSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getDistributorSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.DISTRIBUTORSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getFacility()
  {
    return getFacility(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getFacility(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.FACILITY, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getFacility(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.FACILITY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getNet()
  {
    return getNet(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getNet(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.NET, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getNet(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.NET, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberSold()
  {
    return getNumberSold(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberSold(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.NUMBERSOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.NUMBERSOLD, alias, displayLabel);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson()
  {
    return getPerson(null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias)
  {

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.PERSON, alias, null);

  }
 
  public dss.vector.solutions.PersonQuery.PersonQueryReferenceIF getPerson(String alias, String displayLabel)
  {

    return (dss.vector.solutions.PersonQuery.PersonQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.PERSON, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getService()
  {
    return getService(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getService(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.SERVICE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getService(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.SERVICE, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTargetGroups()
  {
    return getTargetGroups(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTargetGroups(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.TARGETGROUPS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTargetGroups(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDistributionView.TARGETGROUPS, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ITNDistributionView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ITNDistributionView>(this.getMdClassIF(), valueIterator);
  }

}
