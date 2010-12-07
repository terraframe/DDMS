package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.geo.GeoHierarchy;

public class BrowserRoot extends BrowserRootBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String ROOT_PREFIX      = "Root__";

  private static final long  serialVersionUID = 1252959715750L;

  public BrowserRoot()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getTerm() != null)
    {
      return this.getTerm().getTermDisplayLabel().getValue();
    }

    return super.toString();
  }
  
  @Override
  public void apply()
  {
    boolean applied = (!this.isNew() || this.isAppliedToDB());

    if(this.getDisease() == null && this.isNew())
    {
      this.setDisease(Disease.getCurrent());
    }
    
    super.apply();

    // We must apply the browser root before we can create the relationship
    if(!applied)
    {
      this.addfield(this.getBrowserField()).apply();
    }    
  }

  @Override
  protected String buildKey()
  {
    if (this.getTerm() == null || this.getDisease() == null || this.getBrowserField() == null)
    {
      return this.getId(); // object not properly constructed.
    }
    
    return this.getDisease().getKey() + ":" + this.getBrowserField().getKey() + "." + this.getTerm().getKey();
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
   * Fetches the default root, which is the Term without a parent. The query
   * WILL NOT include terms that are marked as obsolete.
   * 
   * @return
   */
  public static BrowserRootView[] getDefaultRoot()
  {
    TermViewQuery query = Term.getDefaultRoots(true);

    OIterator<? extends TermView> iter = query.getIterator();

    List<BrowserRootView> views = new LinkedList<BrowserRootView>();

    try
    {
      while (iter.hasNext())
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

  public static BrowserRootView[] getMenuItemRoot()
  {
    Disease disease = Disease.getCurrent();

    TermViewQuery q = Term.getByIds(new String[] { disease.getMenuRoot().getId() });
    OIterator<? extends TermView> iter = q.getIterator();

    try
    {
      BrowserRootView view = toView(iter.next());
      return new BrowserRootView[] { view };
    }
    finally
    {
      iter.close();
    }
  }

  public static BrowserRootView[] getDefaultGeoRoots(String universalType)
  {
    GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(universalType);
    String termId = geoH.getValue(GeoHierarchy.TERM);
    if (termId != null && termId.length() > 0)
    {
      TermViewQuery q = Term.getByIds(new String[] { termId });
      OIterator<? extends TermView> iter = q.getIterator();
      try
      {
        BrowserRootView view = toView(iter.next());
        return new BrowserRootView[] { view };
      }
      finally
      {
        iter.close();
      }
    }
    else
    {
      return new BrowserRootView[0];
    }
  }

  public static BrowserRootQuery getAttributeRoots(String className, String attribute, QueryFactory factory)
  {
    BrowserRootQuery rootQuery = new BrowserRootQuery(factory);
    BrowserFieldQuery fieldQuery;

    // Default search: everything included
    if (className == null && attribute == null)
    {
      fieldQuery = new BrowserFieldQuery(factory);
    }

    // Geo subtype searching
    if (attribute == null || attribute.length() == 0)
    {
      fieldQuery = new BrowserFieldQuery(factory);
      BrowserRootView[] views = getDefaultGeoRoots(className);

      if (views.length == 1)
      {
        rootQuery.WHERE(rootQuery.getTerm().EQ(views[0].getTermId()));
      }
      else
      {
        rootQuery.WHERE(rootQuery.getTerm().EQ(""));
      }      
    }
    // restricted by MdAttributeId
    else if (className == null || className.length() == 0)
    {
      fieldQuery = new BrowserFieldQuery(factory);

      fieldQuery.WHERE(fieldQuery.getMdAttribute().EQ(attribute));
    }
    // restricted by list of parents term ids
    else
    {
      fieldQuery = BrowserField.getFieldForAttribute(className, attribute, factory);
    }

    rootQuery.WHERE(Disease.getInactiveCriteria(factory, rootQuery.getTerm(), false));
    rootQuery.AND(rootQuery.getDisease().EQ(Disease.getCurrent()));
    rootQuery.AND(rootQuery.field(fieldQuery));

    return rootQuery;
  }

  /**
   * Gets all roots for the given class name and attribute name. Because
   * overloading isn't supported with MdMethods, this method can also take an
   * empty string as the class name which means the attribute param is the id of
   * an MdAttribute.
   * 
   * @param className
   * @param attributeName
   * @return
   */
  public static BrowserRootView[] getAttributeRoots(String className, String attribute)
  {
    List<BrowserRootView> views = new LinkedList<BrowserRootView>();

    // MenuItem searching
    if (className.equals(MenuItem.CLASS) && attribute.equals(MenuItem.TERM))
    {
      return getMenuItemRoot();
    }

    BrowserRootQuery rootQ = BrowserRoot.getAttributeRoots(className, attribute, new QueryFactory());

    OIterator<? extends BrowserRoot> iter = rootQ.getIterator();

    try
    {
      while (iter.hasNext())
      {
        BrowserRoot root = iter.next();
        views.add(root.toView());
      }
    }
    finally
    {
      iter.close();
    }
    // Ticket #848: Return a roots children if only one root
    // exists that is not selectable.
    if (views.size() == 1 && !views.get(0).getSelectable())
    {
      TermViewQuery query = Term.getOntologyChildren(views.get(0).getTermId(), true);

      views = new LinkedList<BrowserRootView>();
      OIterator<? extends TermView> iter2 = query.getIterator();
      try
      {
        while (iter2.hasNext())
        {
          views.add(toView(iter2.next()));
        }
      }
      finally
      {
        iter2.close();
      }
    }

    return views.toArray(new BrowserRootView[views.size()]);
  }

  private static BrowserRootView toView(TermView termView)
  {
    BrowserRootView view = new BrowserRootView();
    view.setTermId(termView.getTermId());
    view.setTermName(termView.getTermName());
    // The view's term name is the display
    view.setSelectable(termView.getSelectable());
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
    // view.setTermName(term.getName());
    view.setTermName(term.getTermDisplayLabel().getValue());
    view.setSelectable(this.getSelectable());
    view.setBrowserRootId(this.getId());
    view.setTermOntologyId(term.getTermId());

    return view;
  }

  public static Boolean hasBrowserRoot(String className, String attributeName)
  {
    if (className == null || attributeName == null)
    {
      return false;
    }

    // MenuItems always have roots as given by DiseaseMaster.getMenuTerm()
    if (className.equals(MenuItem.CLASS) && attributeName.equals(MenuItem.TERM))
    {
      return true;
    }

    QueryFactory factory = new QueryFactory();

    BrowserFieldQuery fieldQuery = BrowserField.getFieldForAttribute(className, attributeName, factory);

    BrowserRootQuery rootQuery = new BrowserRootQuery(factory);

    rootQuery.WHERE(Disease.getInactiveCriteria(factory, rootQuery.getTerm(), false));
    // rootQuery.WHERE(DiseaseWrapper.getInactive(rootQuery.getTerm()).EQ(false));
    rootQuery.AND(rootQuery.field(fieldQuery));

    return ( rootQuery.getCount() > 0 );
  }

  @Override
  public boolean equals(Object obj)
  {
    if (! ( obj instanceof BrowserRoot ))
    {
      return false;
    }

    BrowserRoot other = (BrowserRoot) obj;
    Term otherTerm = other.getTerm();
    Term thisTerm = this.getTerm();
    if (otherTerm == null || thisTerm == null)
    {
      return false;
    }

    Disease thisDisease = this.getDisease();
    Disease otherDisease = other.getDisease();

    return otherTerm.getTermId().equals(thisTerm.getTermId()) && thisDisease.getId().equals(otherDisease.getId());
  }
  
  
}
