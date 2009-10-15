package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class BrowserRoot extends BrowserRootBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959715750L;
  
  public BrowserRoot()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    Term term = this.getTerm();
    if(term == null)
    {
      return ""; // object not properly constructed.
    }
    
    return "Root__"+term.getKeyName();
  }
  
  public static BrowserRootViewQuery getAsViews()
  {
    QueryFactory f = new QueryFactory();
    BrowserRootViewQuery q = new BrowserRootViewQuery(f);
    return q;
  }
  
  @Override
  public BrowserRootView update(BrowserRoot browserRoot)
  {
    this.setTerm(browserRoot.getTerm());
    this.setSelectable(browserRoot.getSelectable());
    
    this.apply();
    
    return this.toView();
  }
  
  /**
   * Fetches the default root, which is the Term
   * without a parent.
   * 
   * @return
   */
  public static BrowserRootView[] getDefaultRoot()
  {
    TermViewQuery query = Term.getDefaultRoots();

    OIterator<? extends TermView> iter = query.getIterator();
    
    List<BrowserRootView> views = new LinkedList<BrowserRootView>();
    
    try
    {
      while(iter.hasNext())
      {
        views.add(toView(iter.next()));
      }
    }
    finally
    {
      iter.close();
    }
    
    return views.toArray(new BrowserRootView[views.size()]);
  }
  
  /**
   * Gets all roots for the given class name and attribute name.
   * 
   * @param className
   * @param attributeName
   * @return
   */
  public static BrowserRootView[] getAttributeRoots(String className, String attributeName)
  {
    BrowserField field = BrowserField.getFieldForAttribute(className, attributeName);
    
    List<BrowserRootView> views = new LinkedList<BrowserRootView>();
    
    OIterator<? extends BrowserRoot> iter = field.getAllroot();
    try
    {
      while(iter.hasNext())
      {
        views.add(iter.next().toView());
      }
    }
    finally
    {
      iter.close();
    }
    
    return views.toArray(new BrowserRootView[views.size()]);
  }
  
  private static BrowserRootView toView(TermView termView)
  {
    BrowserRootView view = new BrowserRootView();
    view.setTermId(termView.getTermId());
    view.setTermName(termView.getTermName());
    view.setSelectable(true);
    view.setTermOntologyId(termView.getTermOntologyId());
    
    return view;
  }
  
  /**
   * Converts this BrowserRoot object into a BrowserRootView.
   * 
   * @return
   */
  @AbortIfProblem
  public BrowserRootView toView()
  {
    Term term = this.getTerm();
    
    BrowserRootView view = new BrowserRootView();
    view.setTermId(term.getId());
    view.setTermName(term.getTermName());
    view.setSelectable(this.getSelectable());
    view.setBrowserRootId(this.getId());
    view.setTermOntologyId(term.getTermId());
    
    return view;
  }
  
}
