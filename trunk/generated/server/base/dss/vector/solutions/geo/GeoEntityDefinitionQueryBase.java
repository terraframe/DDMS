package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = -1245458674)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityDefinition.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class GeoEntityDefinitionQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1245458674;

  public GeoEntityDefinitionQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoEntityDefinitionQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.geo.GeoEntityDefinition.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getDescription()
  {
    return getDescription(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDescription(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DESCRIPTION, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDescription(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DESCRIPTION, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DISPLAYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getParentGeoHierarchyId()
  {
    return getParentGeoHierarchyId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getParentGeoHierarchyId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.PARENTGEOHIERARCHYID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getParentGeoHierarchyId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.PARENTGEOHIERARCHYID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPolitical()
  {
    return getPolitical(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPolitical(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.POLITICAL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPolitical(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.POLITICAL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPopulationAllowed()
  {
    return getPopulationAllowed(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPopulationAllowed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.POPULATIONALLOWED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPopulationAllowed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.POPULATIONALLOWED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSprayTargetAllowed()
  {
    return getSprayTargetAllowed(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSprayTargetAllowed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.SPRAYTARGETALLOWED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSprayTargetAllowed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.SPRAYTARGETALLOWED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.TERM, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getTypeName()
  {
    return getTypeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTypeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.TYPENAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTypeName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.TYPENAME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends GeoEntityDefinition> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<GeoEntityDefinition>(this.getMdClassIF(), valueIterator);
  }

}
