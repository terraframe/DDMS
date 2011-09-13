package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 1623897642)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MdFieldType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class MdFieldTypeQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1623897642;

  public MdFieldTypeQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MdFieldTypeQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.form.MdFieldType.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getDescription()
  {
    return getDescription(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDescription(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.DESCRIPTION, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDescription(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.DESCRIPTION, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.DISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMdFieldId()
  {
    return getMdFieldId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdFieldId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.MDFIELDID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdFieldId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.form.MdFieldType.MDFIELDID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends MdFieldType> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<MdFieldType>(this.getMdClassIF(), valueIterator);
  }

}