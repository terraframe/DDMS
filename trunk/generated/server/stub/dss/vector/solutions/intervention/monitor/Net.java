package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.surveillance.OptionIF;

public class Net extends NetBase implements com.terraframe.mojo.generation.loader.Reloadable, OptionIF
{
  private static final long serialVersionUID = 1239641263464L;

  public Net()
  {
    super();
  }

  @Transaction
  public void apply()
  {
    super.apply();

    List<? extends Net> parents = this.getAllParentNets().getAll();

    if(this.getParentNet() == null)
    {
      if(parents.size() > 0)
      {
        this.deleteAllParents();
      }
    }
    else if(!parents.contains(this.getParentNet()))
    {
      deleteAllParents();

      Net parent = this.getParentNet();
      parent.lock();

      NetHeiarchy heiarchy = new NetHeiarchy(parent, this);
      heiarchy.apply();

      if(!parent.getIsAbstract())
      {
        parent.setIsAbstract(true);
        parent.apply();
      }
    }
  }

  @Transaction
  private void deleteAllParents()
  {
    List<? extends NetHeiarchy> hierarchy = this.getAllParentNetsRel().getAll();

    for(NetHeiarchy h : hierarchy)
    {
      h.delete();
    }
  }

  @Override
  protected String buildKey()
  {
    return this.getNetName();
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

  public static ValueQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory factory = new QueryFactory();

    NetQuery netQuery = new NetQuery(factory);
    ValueQuery valueQuery = new ValueQuery(factory);

    Selectable[] selectables = new Selectable[] {
        netQuery.getId(),
        netQuery.getDisplayLabel(),
        netQuery.getEnabled(),
        netQuery.getIsAbstract(),
        netQuery.getParentNet().getDisplayLabel(Net.PARENTNET, Net.PARENTNET)
    };

    valueQuery.SELECT(selectables);

    SelectablePrimitive selectablePrimitive = (SelectablePrimitive) netQuery.aAttributePrimitive("id");

    if(sortAttribute.equals(Net.PARENTNET))
    {
      selectablePrimitive = netQuery.getParentNet(Net.PARENTNET, Net.PARENTNET).getDisplayLabel().currentLocale();
    }
    else
    {
      selectablePrimitive = (SelectablePrimitive) netQuery.aAttributePrimitive(sortAttribute);
    }

    if (isAscending)
    {
      valueQuery.ORDER_BY_ASC(selectablePrimitive);
    }
    else
    {
      valueQuery.ORDER_BY_DESC(selectablePrimitive);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      valueQuery.restrictRows(pageSize, pageNumber);
    }
    return valueQuery;

  }

}
