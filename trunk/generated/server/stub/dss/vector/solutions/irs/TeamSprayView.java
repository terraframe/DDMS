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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSprayView extends TeamSprayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676032L;

  public TeamSprayView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    TeamSpray concrete = this.getAndLockConcrete();

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private TeamSpray getAndLockConcrete()
  {
    if (this.hasConcrete())
    {
      TeamSpray concrete = TeamSpray.get(this.getConcreteId());
      concrete.appLock();

      validateSprayMethod(concrete.getSprayMethod());
      validateSprayTeam(concrete.getSprayTeam());

      return concrete;
    }
    else
    {
      return new TeamSpray();
    }
  }

  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
  }

  protected void validateSprayMethod(List<SprayMethod> existing)
  {
    List<SprayMethod> newMethod = this.getSprayMethod();

    // Determine if the spray method has changed. If a spray method has changed
    // and this spray already has a spray status then throw an exception,
    // because a the status may have values that are invalid with the new spray
    // method.
    if (!newMethod.containsAll(existing) && this.hasStatus())
    {
      String msg = "The spray method cannot be altered if status rows already exist";
      ModifiedSprayMethodException e = new ModifiedSprayMethodException(msg);
      e.apply();

      throw e;
    }
  }

  public void validateSprayTeam(SprayTeam existing)
  {
    if (existing != null && this.getSprayTeam() != null)
    {
      if (!existing.getId().equals(this.getSprayTeam().getId()) && this.hasStatus())
      {
        String msg = "The spray team cannot be altered if operator status rows already exist";
        ModifiedSprayTeamException e = new ModifiedSprayTeamException(msg);
        e.apply();

        throw e;
      }
    }
  }

  protected void populateMapping(TeamSpray spray)
  {
    new AttributeNotificationMap(spray, TeamSpray.BRAND, this, TeamSprayView.BRAND);
    new AttributeNotificationMap(spray, TeamSpray.GEOENTITY, this, TeamSprayView.GEOENTITY);
    new AttributeNotificationMap(spray, TeamSpray.SPRAYDATE, this, TeamSprayView.SPRAYDATE);
    new AttributeNotificationMap(spray, TeamSpray.SPRAYMETHOD, this, TeamSprayView.SPRAYMETHOD);
    new AttributeNotificationMap(spray, TeamSpray.SURFACETYPE, this, TeamSprayView.SURFACETYPE);
    new AttributeNotificationMap(spray, TeamSpray.TEAMLEADER, this, TeamSprayView.TEAMLEADER);
    new AttributeNotificationMap(spray, TeamSpray.TARGET, this, TeamSprayView.TARGET);
    new AttributeNotificationMap(spray, TeamSpray.SPRAYTEAM, this, TeamSprayView.SPRAYTEAM);
    new AttributeNotificationMap(spray, TeamSpray.SUPERVISOR, this, TeamSprayView.SUPERVISOR);
  }

  protected void populateConcrete(TeamSpray concrete)
  {
    concrete.setSprayDate(this.getSprayDate());
    concrete.setBrand(this.getBrand());
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setSurfaceType(this.getSurfaceType());
    concrete.setSupervisor(this.getSupervisor());
    concrete.setSprayTeam(this.getSprayTeam());
    concrete.setTarget(this.getTarget());
    concrete.setTeamLeader(this.getTeamLeader());
    concrete.clearSprayMethod();

    for (SprayMethod method : this.getSprayMethod())
    {
      concrete.addSprayMethod(method);
    }
  }

  public void populateView(TeamSpray concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setSprayDate(concrete.getSprayDate());
    this.setBrand(concrete.getBrand());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setSurfaceType(concrete.getSurfaceType());
    this.setSupervisor(concrete.getSupervisor());
    this.setSprayTeam(concrete.getSprayTeam());
    this.setTarget(concrete.getTarget());
    this.setTeamLeader(concrete.getTeamLeader());
    this.clearSprayMethod();

    for (SprayMethod method : concrete.getSprayMethod())
    {
      this.addSprayMethod(method);
    }
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      TeamSpray.get(this.getConcreteId()).delete();
    }
  }

  public OperatorSprayStatusView[] getStatus()
  {
    List<OperatorSprayStatusView> list = new LinkedList<OperatorSprayStatusView>();

    TeamSpray spray = TeamSpray.get(this.getConcreteId());

    OperatorSprayStatusQuery query = new OperatorSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().EQ(spray));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends OperatorSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add((OperatorSprayStatusView) it.next().getView());
      }

      return list.toArray(new OperatorSprayStatusView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static TeamSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, String teamId)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());

    query.WHERE(query.getBrand().EQ(brand));
    query.AND(query.getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayMethod().containsAny(sprayMethod));
    query.AND(query.getSprayTeam().getId().EQ(teamId));

    OIterator<? extends TeamSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      TeamSprayView view = new TeamSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);
      view.setSprayTeam(SprayTeam.get(teamId));

      return view;
    }
    finally
    {
      it.close();
    }
  }
}
