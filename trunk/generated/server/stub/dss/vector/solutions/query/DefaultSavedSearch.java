package dss.vector.solutions.query;

public class DefaultSavedSearch extends DefaultSavedSearchBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248211167569L;
    
  static final String DEFAULT = "__DEFAULT__";
  
  public DefaultSavedSearch()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    this.setQueryName(DEFAULT);
//    this.setQueryType(DEFAULT); This was clobbering the [query class:query type] namespace.
    
    super.apply();
  }
  
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

}
