/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;

public class ResourceTarget extends ResourceTargetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240257020374L;

  public ResourceTarget()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }

  @Override
  protected String buildKey()
  {
    if (this.getTargeter() != null && this.getSeason() != null)
    {
      return this.getTargeterId() + "." + this.getSeason().getKey();
    }
    return this.getId();
  }

  public String getTargeterId()
  {
    if (this.getTargeter() instanceof TeamMember)
    {
      return ( (TeamMember) this.getTargeter() ).getMemberId();
    }

    return ( (SprayTeam) this.getTargeter() ).getTeamId();
  }

  public static String getTargeterName(Targeter targeter)
  {
    if (targeter instanceof TeamMember)
    {
      TeamMember so = (TeamMember) targeter;
      return ( so.getLabel() );
    }

    if (targeter instanceof SprayTeam)
    {
      SprayTeam st = (SprayTeam) targeter;

      return ( st.getLabel() );
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
    view.setSeason(this.getSeason());
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

  public static ResourceTargetView searchByTargeterAndSeason(Targeter resource, MalariaSeason season)
  {
    ResourceTargetQuery query = new ResourceTargetQuery(new QueryFactory());
    query.WHERE(query.getTargeter().EQ(resource));
    query.AND(query.getSeason().EQ(season));

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
        view.setSeason(season);
        view.setTargeterName(ResourceTarget.getTargeterName(resource));

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  public static ResourceTargetView searchByTargeterIdAndSeason(String id, MalariaSeason season)
  {
    return searchByTargeterAndSeason(Targeter.get(id), season);
  }


}
