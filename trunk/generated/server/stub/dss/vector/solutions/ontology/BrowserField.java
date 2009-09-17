package dss.vector.solutions.ontology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdAttribute;


public class BrowserField extends BrowserFieldBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713570L;
  
  public BrowserField()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    MdAttribute mdAttr = this.getMdAttribute();
    return "Ontology__"+mdAttr.getKeyName();
  }
  
  public static BrowserFieldViewQuery getAsViews()
  {
    QueryFactory f = new QueryFactory();
    BrowserFieldViewQuery q = new BrowserFieldViewQuery(f);
    
    return q;
  }
  
  @Transaction
  public BrowserRootView addBrowserRoot(BrowserRoot root)
  {
    root.apply();
    
    this.addroot(root).apply();
    
    return root.toView();
  }
}
