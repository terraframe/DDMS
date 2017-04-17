package dss.vector.solutions.ontology;


public class BrowserFieldView extends BrowserFieldViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713136L;
  
  public BrowserFieldView()
  {
    super();
  }
  
  @Override
  public BrowserRootView[] getRoots()
  {
    BrowserField concrete = this.getConcrete();
    
    if(concrete != null)
    {
      return concrete.getRoots();
    }
    
    return new BrowserRootView[0];
  }

  private BrowserField getConcrete()
  {
    if(this.getBrowserFieldId() != null && this.getBrowserFieldId().length() > 0)
    {
      return BrowserField.get(this.getBrowserFieldId());
    }
    
    return null;
  }
}
