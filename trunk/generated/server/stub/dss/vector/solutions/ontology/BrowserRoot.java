package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ViewQueryBuilder;

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
    QueryFactory f = new QueryFactory(); 

    // FIXME pass in the ontology and rel and switch on that
    TermQuery termQuery;
    TermRelationshipQuery termRelQuery;
    if(true /* || Ontology === MO */)
    {
      termQuery = new MOQuery(f);
      termRelQuery = new IsAQuery(f);
    }
    
    DefaultRootQueryBuilder builder = new DefaultRootQueryBuilder(f, termQuery, termRelQuery);
    TermViewQuery query = new TermViewQuery(f, builder);

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
  
  private static BrowserRootView toView(TermView termView)
  {
    BrowserRootView view = new BrowserRootView();
    view.setTermId(termView.getTermId());
    view.setTermName(termView.getTermName());
    view.setSelectable(true);
    
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
    BrowserRootView view = new BrowserRootView();
    view.setTermName(this.getTerm().getTermName());
    view.setSelectable(this.getSelectable());
    view.setBrowserRootId(this.getId());
    return view;
  }
  
  /**
   * Queries for the root Term of a given ontology.
   */
  public static class DefaultRootQueryBuilder extends ViewQueryBuilder implements Reloadable
  {
    private TermQuery termQuery;
    private ValueQuery valueQuery;
    private TermRelationshipQuery termRelQuery;
    
    protected DefaultRootQueryBuilder(QueryFactory queryFactory, TermQuery termQuery, TermRelationshipQuery termRelQuery)
    {
      super(queryFactory);
      
      this.termQuery = termQuery;
      this.valueQuery = queryFactory.valueQuery();
      this.termRelQuery = termRelQuery;
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery query = this.getViewQuery();  
      
      query.map(TermView.TERMID, this.termQuery.getId());
      query.map(TermView.TERMNAME, this.termQuery.getTermName());
    }
    
    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery query = this.getViewQuery();  
      
      // the root is not a child of any other term
      Selectable childId = this.termRelQuery.childId();
      this.valueQuery.SELECT(childId);
      
      query.WHERE(this.termQuery.NOT_IN(this.termQuery.getId(), this.valueQuery));
    }
  }
  
}
