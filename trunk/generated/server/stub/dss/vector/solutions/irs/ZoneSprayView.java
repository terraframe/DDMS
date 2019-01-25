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
import dss.vector.solutions.ontology.Term;

public class ZoneSprayView extends ZoneSprayViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860657649L;

  public ZoneSprayView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    ZoneSpray concrete = this.getAndLockConcrete();

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private ZoneSpray getAndLockConcrete()
  {
    if (this.hasConcrete())
    {
      ZoneSpray concrete = ZoneSpray.get(this.getConcreteId());
      concrete.appLock();

      validateSprayMethod(concrete.getSprayMethod());
      validateGeoEntity(concrete.getGeoEntity());

      return concrete;
    }
    else
    {
      return new ZoneSpray();
    }
  }

  private void validateGeoEntity(GeoEntity geoEntity)
  {
    GeoEntity newGeoEntity = this.getGeoEntity();

    if (geoEntity != null && newGeoEntity != null)
    {
      if (!geoEntity.getId().equals(newGeoEntity.getId()) && this.hasStatus())
      {
        String msg = "The geo entity cannot be altered if team status rows already exist";
        ModifiedSprayZoneException e = new ModifiedSprayZoneException(msg);
        e.apply();

        throw e;
      }
    }
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

  protected void populateMapping(ZoneSpray spray)
  {
    new AttributeNotificationMap(spray, ZoneSpray.BRAND, this, ZoneSprayView.BRAND);
    new AttributeNotificationMap(spray, ZoneSpray.GEOENTITY, this, ZoneSprayView.GEOENTITY);
    new AttributeNotificationMap(spray, ZoneSpray.SPRAYDATE, this, ZoneSprayView.SPRAYDATE);
    new AttributeNotificationMap(spray, ZoneSpray.SPRAYMETHOD, this, ZoneSprayView.SPRAYMETHOD);
    new AttributeNotificationMap(spray, ZoneSpray.SURFACETYPE, this, ZoneSprayView.SURFACETYPE);
    new AttributeNotificationMap(spray, ZoneSpray.SUPERVISOR, this, ZoneSprayView.SUPERVISOR);
  }

  protected void populateConcrete(ZoneSpray concrete)
  {
    concrete.setSprayDate(this.getSprayDate());
    concrete.setBrand(this.getBrand());
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setSurfaceType(this.getSurfaceType());
    concrete.setSupervisor(this.getSupervisor());
    concrete.clearSprayMethod();

    for (SprayMethod method : this.getSprayMethod())
    {
      concrete.addSprayMethod(method);
    }
  }

  public void populateView(ZoneSpray concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setSprayDate(concrete.getSprayDate());
    this.setBrand(concrete.getBrand());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setSurfaceType(concrete.getSurfaceType());
    this.setSupervisor(concrete.getSupervisor());

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
      ZoneSpray.get(this.getConcreteId()).delete();
    }
  }

  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public TeamSprayStatusView[] getStatus()
  {
    List<TeamSprayStatusView> list = new LinkedList<TeamSprayStatusView>();

    ZoneSpray spray = ZoneSpray.get(this.getConcreteId());

    TeamSprayStatusQuery query = new TeamSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().EQ(spray));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends TeamSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }

      return list.toArray(new TeamSprayStatusView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
  }

  public static ZoneSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand)
  {
    return ZoneSprayView.searchBySprayData(geoId, sprayDate, sprayMethod, brand, null);
  }
  
  public static ZoneSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, Term surfaceType)
  {
    ZoneSprayQuery query = new ZoneSprayQuery(new QueryFactory());

    query.WHERE(query.getBrand().EQ(brand));
    query.AND(query.getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayMethod().containsAny(sprayMethod));

    if (surfaceType != null)
    {
      query.AND(query.getSurfaceType().EQ(surfaceType));
    }
    
    OIterator<? extends ZoneSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      ZoneSprayView view = new ZoneSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);

      return view;
    }
    finally
    {
      it.close();
    }
  }

}
