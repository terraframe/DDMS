package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.OptionIF;

public class Net extends NetBase implements com.terraframe.mojo.generation.loader.Reloadable, OptionIF
{
  private static final long serialVersionUID = 1239641263464L;

  public Net()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getMdClass().getTypeName() + "_" + this.getNetName();
  }
  
  public String getOptionName()
  {
    return this.getNetName();
  }

  public static Net[] getAll()
  {
    NetQuery query = new NetQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Net> it = query.getIterator();

    try
    {
      List<Net> nets = new LinkedList<Net>();

      while (it.hasNext())
      {
        nets.add(it.next());
      }

      return nets.toArray(new Net[nets.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static Net[] getAllLeafs()
  {
    NetQuery query = new NetQuery(new QueryFactory());
    query.WHERE(query.NOT_IN_childNets());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Net> it = query.getIterator();

    try
    {
      List<Net> nets = new LinkedList<Net>();

      while (it.hasNext())
      {
        nets.add(it.next());
      }

      return nets.toArray(new Net[nets.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static List<Net> getRoots()
  {
    List<Net> list = new LinkedList<Net>();
    NetQuery query = new NetQuery(new QueryFactory());
    query.WHERE(query.NOT_IN_parentNets());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Net> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }

      return list;
    }
    finally
    {
      it.close();
    }
  }
}
