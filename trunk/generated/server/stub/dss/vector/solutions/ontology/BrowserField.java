package dss.vector.solutions.ontology;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdAttribute;


public class BrowserField extends BrowserFieldBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713570L;
  
  private static final String KEY_PREFIX = "Ontology__";
  
  public BrowserField()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    MdAttribute mdAttr = this.getMdAttribute();
    return KEY_PREFIX+mdAttr.getKeyName();
  }

  /**
   * Returns the BrowserField associated with the given class name and attribute. This
   * method should only be called if a BrowserField exists that matches the criteria. Otherwise,
   * this method will error out with a generic exception.
   * 
   * @param className
   * @param attribute
   * @return
   */
  public static BrowserField getFieldForAttribute(String className, String attribute)
  {
    QueryFactory f = new QueryFactory();
    BrowserFieldQuery q = new BrowserFieldQuery(f);
    
    // reconstruct the keyname (assumes MdAttribute keyname is preserved).
    String keyName = KEY_PREFIX+className+"."+attribute;
    
    q.WHERE(q.getKeyName().EQ(keyName));
    OIterator<? extends BrowserField> iter = q.getIterator();
    
    try
    {
      return iter.next();  
    }
    catch(Throwable t)
    {
      String msg = "A BrowserField does not exist with key name ["+keyName+"].";
      throw new ProgrammingErrorException(msg, t);
    }
    finally
    {
      iter.close(); 
    }
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
    root.validateTerm(); // make sure a term value exists
    Term term = root.getTerm();
    
    OIterator<? extends BrowserRoot> roots = this.getAllroot();        
    try
    {
      while(roots.hasNext())
      {
        if(roots.next().getTerm().equals(term))
        {
          String display = this.getMdAttribute().getDisplayLabel().getDefaultLocale();
          
          String msg = "The field ["+display+"] already defines the root ["+term.getTermName()+"].";
          DuplicateRootException ex = new DuplicateRootException(msg);
          ex.setBrowserField(display);
          ex.setBrowserRoot(term.getTermName());
          
          throw ex;
        }
      }
    }
    finally
    {
      roots.close();
    }
    
    root.apply();
    
    this.addroot(root).apply();
    
    return root.toView();
  }
}
