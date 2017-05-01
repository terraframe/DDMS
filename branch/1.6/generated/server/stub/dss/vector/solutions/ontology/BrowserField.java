package dss.vector.solutions.ontology;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeVirtual;
import com.runwaysdk.system.metadata.MdAttributeVirtualQuery;

import dss.vector.solutions.kaleidoscope.dashboard.MdAttributeViewQuery;

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
   * Returns the BrowserField associated with the given class name and attribute. This method should
   * only be called if a BrowserField exists that matches the criteria. Otherwise, this method will
   * error out with a generic exception.
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
    root.setBrowserField(this);
    root.apply();

    return root.toView();
  }

  public static BrowserField getBrowserField(String mdAttributeId)
  {
    QueryFactory factory = new QueryFactory();
    
    MdAttributeVirtualQuery mavQuery = new MdAttributeVirtualQuery(factory);
    mavQuery.WHERE(mavQuery.getMdAttributeConcrete().EQ(mdAttributeId));
    
    BrowserFieldQuery query = new BrowserFieldQuery(factory);
    query.WHERE(query.getMdAttribute().EQ(mdAttributeId));
    query.OR(query.getMdAttribute().EQ(mavQuery));    
    
    OIterator<? extends BrowserField> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public static BrowserField getBrowserField(MdAttributeDAOIF mdAttribute)
  {
    return BrowserField.getBrowserField(mdAttribute.getId());
  }

  public static BrowserField getBrowserField(MdAttribute mdAttribute)
  {
    return BrowserField.getBrowserField(mdAttribute.getId());
  }

  public BrowserRootView[] getRoots()
  {
    List<BrowserRootView> list = new LinkedList<BrowserRootView>();

    BrowserRootViewQuery query = BrowserRootViewQuery.getRootsFromField(this);
    OIterator<? extends BrowserRootView> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list.toArray(new BrowserRootView[list.size()]);
  }

  @Override
  public BrowserFieldView getView()
  {
    MdAttributeDAOIF _mdAttribute = (MdAttributeDAOIF) MdAttributeDAO.get(this.getMdAttribute().getId());
    MdDimensionDAOIF mdDimension = Session.getCurrentDimension();

    MdAttributeDimensionDAOIF mdAttributeDimension = _mdAttribute.getMdAttributeConcrete().getMdAttributeDimension(mdDimension);
    MdClassDAOIF _mdClass = _mdAttribute.definedByClass();
    String _defaultValue = mdAttributeDimension.getDefaultValue();

    BrowserFieldView view = new BrowserFieldView();
    view.setBrowserFieldId(this.getId());
    view.setMdClassId(_mdClass.getId());
    view.setMdClassLabel(_mdClass.getDisplayLabel(Session.getCurrentLocale()));
    view.setMdAttributeId(_mdAttribute.getId());
    view.setMdAttributeLabel(_mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

    if (_defaultValue != null && _defaultValue.length() > 0)
    {
      view.setDefaultValue(Term.get(_defaultValue));
    }

    return view;
  }
}
