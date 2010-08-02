package dss.vector.solutions.irs;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.ConfigurationException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.general.MalariaSeason;

public class ResourceTargetView extends ResourceTargetViewBase implements Reloadable
{
  private static final long serialVersionUID = 1240257027793L;

  public ResourceTargetView()
  {
    super();
  }

  @Override
  public void apply()
  {
    ResourceTarget target = new ResourceTarget();

    if (this.getTargetId() != null && !this.getTargetId().equals(""))
    {
      target = ResourceTarget.lock(this.getTargetId());
    }

    target.setTargeter(this.getTargeter());
    target.setSeason(this.getSeason());

    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = ResourceTarget.class.getMethod(setterName, Integer.class);
        Method getter = ResourceTargetView.class.getMethod(getterName);

        setter.invoke(target, (Integer) getter.invoke(this));
      }
      catch (Exception e)
      {
        throw new ConfigurationException(e);
      }
    }

    target.apply();

    this.setTargetId(target.getId());
  }

  @Override
  public void deleteConcrete()
  {
    if (this.getTargetId() != null && !this.getTargetId().equals(""))
    {
      ResourceTarget.get(this.getTargetId()).delete();
    }
  }

  @Transaction
  public ResourceTargetView unlock()
  {
    if (this.getTargetId() == null || this.getTargetId().equals(""))
    {
      return this;
    }

    return ResourceTarget.unlock(this.getTargetId()).getView();
  }

  @Transaction
  public ResourceTargetView lock()
  {
    if (this.getTargetId() == null || this.getTargetId().equals(""))
    {
      return this;
    }

    return ResourceTarget.lock(this.getTargetId()).getView();
  }

  @Transaction
  public static ResourceTargetView[] lockAll(ResourceTargetView[] views)
  {
    ResourceTargetView[] locked = new ResourceTargetView[views.length];

    for (int i = 0; i < views.length; i++)
    {
      locked[i] = views[i].lock();
    }

    return locked;
  }

  @Transaction
  public static ResourceTargetView[] unlockAll(ResourceTargetView[] views)
  {
    ResourceTargetView[] unlocked = new ResourceTargetView[views.length];

    for (int i = 0; i < views.length; i++)
    {
      unlocked[i] = views[i].unlock();
    }

    return unlocked;
  }

  @Transaction
  public static ResourceTargetView[] applyAll(ResourceTargetView[] views)
  {
    for (ResourceTargetView view : views)
    {
      view.apply();
    }

    return views;
  }

  @Transaction
  public static ResourceTargetView[] getResourceTargets(String[] targeterIds, MalariaSeason season)
  {
    ResourceTargetView[] views = new ResourceTargetView[targeterIds.length];

    for (int i = 0; i < targeterIds.length; i++)
    {
      views[i] = ResourceTarget.searchByTargeterIdAndSeason(targeterIds[i], season);
    }

    return views;
  }
  
  @Transaction
  public static ResourceTargetView sum(Targeter resource, ResourceTargetView[] views)
  {
    ResourceTargetView newView = new ResourceTargetView();
    newView.setTargeter(resource);

    try
    {
      for (int i = 0; i < 53; i++)
      {
        String setterName = "setTarget_" + i;
        String getterName = "getTarget_" + i;
        Integer sum = 0;

        for (ResourceTargetView view : views)
        {
          sum += (Integer) ResourceTargetView.class.getMethod(getterName).invoke(view);
        }

        ResourceTargetView.class.getMethod(setterName, Integer.class).invoke(newView, sum);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return newView;
  }
  
  public static Integer[] getCalculatedTargets(Targeter targeter, MalariaSeason season)
  {
    Integer[] targets = new Integer[53];
    
    if(targeter instanceof SprayTeam)
    {
      SprayTeam team = (SprayTeam) targeter;

      List<String> memberIds = new LinkedList<String>();
      OIterator<? extends TeamMember> it = team.getAllSprayTeamMembers();
      
      try
      {
        while(it.hasNext())
        {
          memberIds.add(it.next().getId());
        }
      }
      finally
      {
        it.close();
      }
      
      String[] ids = memberIds.toArray(new String[memberIds.size()]);
      
      ResourceTargetView[] views = ResourceTargetView.getResourceTargets(ids, season);
      
      try
      {
        for (int i = 0; i < 53; i++)
        {
          String getterName = "getTarget_" + i;
          Integer sum = 0;

          for (ResourceTargetView view : views)
          {
            Integer value = (Integer) ResourceTargetView.class.getMethod(getterName).invoke(view);
            
            if(value != null)
            {
              sum += value;
            }
          }
          
          if(sum != 0)
          {
            targets[i] = sum;
          }
        }
        
      }
      catch (Exception e)
      {
        throw new ConfigurationException(e);
      }
      
    }
    
    return targets;
  }
  
  @Transaction
  public static Integer[][] getCalculatedTargetsFoViews(ResourceTargetView[] views)
  {
    Integer[][] targets = new Integer[views.length][];
    
    for(int i = 0; i < views.length; i++)
    {
      Targeter targeter = views[i].getTargeter();
      MalariaSeason season = views[i].getSeason();
      
      targets[i] = ResourceTargetView.getCalculatedTargets(targeter, season);
    }
    
    return targets;
  }

}
