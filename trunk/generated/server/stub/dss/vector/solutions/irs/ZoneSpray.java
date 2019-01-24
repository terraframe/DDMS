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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.ontology.Term;

public class ZoneSpray extends ZoneSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      String key = this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName;
      
      Term surfaceType = this.getSurfaceType();
      if (surfaceType != null)
      {
        key = key + "." + surfaceType.getKey();
      }
      
      return key;
    }

    return this.getId();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if (_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());
    }

    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());

    super.apply();
  }

  public ZoneSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ZoneSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public ZoneSprayView getView()
  {
    ZoneSprayView view = new ZoneSprayView();

    view.populateView(this);

    return view;
  }


}
