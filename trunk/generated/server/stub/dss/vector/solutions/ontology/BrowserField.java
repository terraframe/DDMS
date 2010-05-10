package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeVirtual;

import dss.vector.solutions.OverlappingTermRootException;

public class BrowserField extends BrowserFieldBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 1252959713570L;

  private static final String KEY_PREFIX       = "Ontology__";

  public BrowserField()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdAttribute mdAttr = this.getMdAttribute();
    return KEY_PREFIX + mdAttr.getKeyName();
  }
  
  

  public static BrowserFieldQuery getFieldForAttribute(String className, String attribute, QueryFactory factory)
  {
    BrowserFieldQuery q = new BrowserFieldQuery(factory);

    // reconstruct the keyname (assumes MdAttribute keyname is preserved).
    String keyName = BrowserField.buildKey(className, attribute);

    Condition or = OR.get(q.getKeyName().EQ(keyName));

    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(className);

    for (MdClassDAOIF superClass : mdClass.getSuperClasses())
    {
      String key = buildKey(superClass.definesType(), attribute);

      or = OR.get(or, q.getKeyName().EQ(key));
    }

    q.WHERE(or);

    return q;
  }

  /**
   * Returns the BrowserField associated with the given class name and
   * attribute. This method should only be called if a BrowserField exists that
   * matches the criteria. Otherwise, this method will error out with a generic
   * exception.
   * 
   * @param className
   * @param attribute
   * @return
   */
  public static BrowserField getFieldForAttribute(String className, String attribute)
  {
    BrowserFieldQuery q = BrowserField.getFieldForAttribute(className, attribute, new QueryFactory());
    OIterator<? extends BrowserField> iter = q.getIterator();

    try
    {
      return iter.next();
    }
    catch (Throwable t)
    {
      String keyName = BrowserField.buildKey(className, attribute);
      String msg = "A BrowserField does not exist with key name [" + keyName + "].";
      throw new ProgrammingErrorException(msg, t);
    }
    finally
    {
      iter.close();
    }
  }

  public static String buildKey(String className, String attribute)
  {
    return KEY_PREFIX + className + "." + attribute;
  }

  public static BrowserFieldView[] getAsViews()
  {
    QueryFactory f = new QueryFactory();
    BrowserFieldViewQuery q = new BrowserFieldViewQuery(f);

    List<BrowserFieldView> views = new LinkedList<BrowserFieldView>();
    OIterator<? extends BrowserFieldView> iter = q.getIterator();

    try
    {
      while (iter.hasNext())
      {
        BrowserFieldView view = iter.next();
        if (view.getMdAttributeLabel().length() == 0)
        {
          MdAttributeVirtual mdAttr = (MdAttributeVirtual) MdAttribute.get(view.getMdAttributeId());
         
          String display = mdAttr.getMdAttributeConcrete().getDisplayLabel().getValue(Session.getCurrentLocale());

          view.setMdAttributeLabel(display);

        }

        views.add(view);
      }
    }
    finally
    {
      iter.close();
    }

    return views.toArray(new BrowserFieldView[views.size()]);
  }

  @Transaction
  public BrowserRootView addBrowserRoot(BrowserRoot root)
  {
    root.validateTerm(); // make sure a term value exists

    this.validateRoot(root);

    root.apply();

    this.addroot(root).apply();

    return root.toView();
  }

  public void validateRoot(BrowserRoot root) {
	OIterator<? extends BrowserRoot> roots = this.getAllroot();
    try
    {
      while (roots.hasNext())
      {
    	  BrowserRoot existingRoot = roots.next();
    	  // Don't compare the root to itself, which BrowserRoot.equals() does not account for
        if(!existingRoot.getId().equals(root.getId()) && existingRoot.equals(root))
        {
          String display = this.getMdAttribute().getDisplayLabel().getValue(Session.getCurrentLocale());

          Term term = root.getTerm();
          String msg = "The field [" + display + "] already defines the root [" + term.getName() + "].";
          DuplicateRootException ex = new DuplicateRootException(msg);
          ex.setBrowserField(display);
          ex.setBrowserRoot(term.getName());

          throw ex;
        }
        /*
        AllPathsQuery q = new AllPathsQuery(new QueryFactory());
        Condition parent = AND.get(q.getParentTerm().EQ(root.getTerm()), q.getChildTerm().EQ(existingRoot.getTerm()));
        Condition child = AND.get(q.getParentTerm().EQ(existingRoot.getTerm()), q.getChildTerm().EQ(root.getTerm()));
        q.WHERE(OR.get(parent,child));
        if (q.getCount() > 0) {
            String display = this.getMdAttribute().getDisplayLabel().getValue(Session.getCurrentLocale());

            String msg = "The root overlaps with the existing root[" + existingRoot.getTerm().getName() + "].";
            OverlappingTermRootException ex = new OverlappingTermRootException(msg);
            ex.setBrowserField(display);
            ex.setBrowserRoot(existingRoot.getTerm().getName());

            throw ex;
        }
        */
      }
    }
    finally
    {
      roots.close();
    }
  }
}
