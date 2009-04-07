package dss.vector.solutions.mo;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.AbstractTermBase;
import dss.vector.solutions.mo.AbstractTermQuery;


public abstract class AbstractTerm extends AbstractTermBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234811656578L;
  
  public AbstractTerm()
  {
    super();
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
}
