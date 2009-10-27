package dss.vector.solutions.mo;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.UnknownTermProblem;


public abstract class AbstractTerm extends AbstractTermBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234811656578L;

  public AbstractTerm()
  {
    super();
  }

  protected String buildKey()
  {
    return this.getTermId();
  }


  public static AbstractTerm searchByTermId(java.lang.String moId)
  {
    QueryFactory factory = new QueryFactory();
    AbstractTermQuery query = new AbstractTermQuery(factory);

    query.WHERE(query.getTermId().EQ(moId));
    query.AND(query.getEnabled().EQ(true));

    OIterator<? extends AbstractTerm> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static AbstractTerm searchByTermName(java.lang.String termName)
  {
    QueryFactory factory = new QueryFactory();
    AbstractTermQuery query = new AbstractTermQuery(factory);

    query.WHERE(query.getTermName().EQ(termName));
    query.AND(query.getEnabled().EQ(true));

    OIterator<? extends AbstractTerm> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public static AbstractTerm validateByDisplayLabel(String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    QueryFactory factory = new QueryFactory();
    AbstractTermQuery query = new AbstractTermQuery(factory);

    query.WHERE(query.getDisplayLabel().currentLocale().EQ(displayLabel));
    query.AND(query.getEnabled().EQ(true));

    OIterator<? extends AbstractTerm> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      String attributeLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
      String msg = "Unknown " + attributeLabel + " with the given name [" + displayLabel + "]";

      UnknownTermProblem e = new UnknownTermProblem(msg);
      e.setTermName(displayLabel);
      e.setAttributeLabel(attributeLabel);
      e.throwIt();
      
      // We expect to return nothing, as we're throwing a problem, but include this to satisfy the compile time requirement
      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public static AbstractTerm validateByTermName(String termName)
  {
    AbstractTerm term = searchByTermName(termName);

    if(term == null)
    {
      String msg = "Unknown term with the given name [" + termName + "]";

      UnknownTermProblem e = new UnknownTermProblem(msg);
      e.setTermName(termName);
      e.throwIt();
    }

    return term;
  }

  @SuppressWarnings("unchecked")
  public static <T> T searchByTermName(Class<T> c, String termName)
  {
    AbstractTerm term = searchByTermName(termName);

    if(c.isInstance(term))
    {
      return (T) term;
    }
    else
    {
      //TODO fix exception type
      String msg = "Abstract term of wrong type";
      throw new RuntimeException(msg);
    }
  }

  protected static <T> List<T> getAll(AbstractTermQuery query, Class<T> c)
  {
    query.ORDER_BY_ASC(query.getTermName());

    return convertQueryToList(query, c);
  }

  public static <T> List<T> getAllActive(AbstractTermQuery query, Class<T> c)
  {
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getTermName());

    return convertQueryToList(query, c);
  }

  @SuppressWarnings("unchecked")
  private static <T> List<T> convertQueryToList(AbstractTermQuery query, Class<T> c)
  {
    List<T> list = new LinkedList<T>();
    OIterator<? extends AbstractTerm> it = query.getIterator();

    while(it.hasNext())
    {
      list.add((T) it.next());
    }

    it.close();

    return list;
  }
}
