package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;

public class ResourceTarget extends ResourceTargetBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240257020374L;

  public ResourceTarget()
  {
    super();
  }

  public static String getTargeterName(Targeter targeter)
  {
    if(targeter instanceof SprayOperator)
    {
      SprayOperator so = (SprayOperator) targeter;
     return (so.getOperatorId() + " - " + so.getPerson().getLastName() +", "+ so.getPerson().getFirstName() );
    }

    if(targeter instanceof SprayTeam)
    {
      SprayTeam st = (SprayTeam) targeter;

      /*
      String leader_name = "";
      if(! st.getAllTeamLeader().getAll().isEmpty())
      {
        Person leader = st.getAllTeamLeader().getAll().get(0).getPerson();
        leader_name = leader.getLastName() + ", " + leader.getFirstName();
      }
      */
      return (st.getTeamId() );
    }
    return null;
  }

  public String getTargeterName()
  {
    return ResourceTarget.getTargeterName(this.getTargeter());
  }

  public ResourceTargetView getView()
  {
    ResourceTargetView view = new ResourceTargetView();
    view.setTargeter(this.getTargeter());
    view.setTargetYear(this.getTargetYear());
    view.setTargetId(this.getId());
    view.setTargeterName(this.getTargeterName());



    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = ResourceTargetView.class.getMethod(setterName, Integer.class);
        Method getter = ResourceTarget.class.getMethod(getterName);

        setter.invoke(view, (Integer) getter.invoke(this));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    return view;
  }

  public static ResourceTargetView getView(String id)
  {
    return ResourceTarget.get(id).getView();
  }

  public static ResourceTargetView searchByTargeterAndYear(Targeter resource, Integer year)
  {
    ResourceTargetQuery query = new ResourceTargetQuery(new QueryFactory());
    query.WHERE(query.getTargeter().EQ(resource));
    query.AND(query.getTargetYear().EQ(year));

    OIterator<? extends ResourceTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        ResourceTargetView view = new ResourceTargetView();
        view.setTargeter(resource);
        view.setTargetYear(year);
        view.setTargeterName(ResourceTarget.getTargeterName(resource));

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  public static ResourceTargetView searchByTargeterIdAndYear(String id, Integer year)
  {
    return searchByTargeterAndYear(Targeter.get(id), year);
  }

}
