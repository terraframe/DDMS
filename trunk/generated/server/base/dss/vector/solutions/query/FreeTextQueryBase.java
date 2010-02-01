package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1979600108)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to FreeText.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class FreeTextQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1979600108;

  public FreeTextQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public FreeTextQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.query.FreeText.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getCustomText()
  {
    return getCustomText(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCustomText(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.CUSTOMTEXT, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCustomText(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.CUSTOMTEXT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFamily()
  {
    return getTextFontFamily(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFamily(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTFAMILY, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFamily(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTFAMILY, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFill()
  {
    return getTextFontFill(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFill(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTFILL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTextFontFill(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTFILL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTextFontSize()
  {
    return getTextFontSize(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTextFontSize(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTSIZE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTextFontSize(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTSIZE, alias, displayLabel);

  }
 
  public dss.vector.solutions.query.FontStyleQuery.FontStylesQueryIF getTextFontStyles()
  {
    return getTextFontStyles(null);

  }
 
  public dss.vector.solutions.query.FontStyleQuery.FontStylesQueryIF getTextFontStyles(String alias)
  {
    return (dss.vector.solutions.query.FontStyleQuery.FontStylesQueryIF)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTSTYLES, alias, null);

  }
 
  public dss.vector.solutions.query.FontStyleQuery.FontStylesQueryIF getTextFontStyles(String alias, String displayLabel)
  {
    return (dss.vector.solutions.query.FontStyleQuery.FontStylesQueryIF)this.getSelectable(dss.vector.solutions.query.FreeText.TEXTFONTSTYLES, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends FreeText> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<FreeText>(this.getMdClassIF(), valueIterator);
  }

}
