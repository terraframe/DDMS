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
package dss.vector.solutions.entomology;

import com.runwaysdk.session.Session;

import dss.vector.solutions.general.Disease;

public class ImmatureThreshold extends ImmatureThresholdBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -955212168;
  
  public static final String HOUSE_IMMATURES = "HOUSE_IMMATURES";
  public static final String HOUSE_LARVAE = "HOUSE_LARVAE";
  public static final String HOUSE_PUPAE = "HOUSE_PUPAE";
  public static final String CONTAINER_IMMATURES = "CONTAINER_IMMATURES";
  public static final String CONTAINER_LARVAE = "CONTAINER_LARVAE";
  public static final String CONTAINER_PUPAE = "CONTAINER_PUPAE";
  public static final String BRETEAU_IMMATURES = "BRETEAU_IMMATURES";
  public static final String BRETEAU_LARVAE = "BRETEAU_LARVAE";
  public static final String BRETEAU_PUPAE = "BRETEAU_PUPAE";
  public static final String PUPAL_INDEX = "PUPAL_INDEX";
  public static final String PUPAE_PREMISE = "PUPAE_PREMISE";
  public static final String PUPAE_HECTARE ="PUPAE_HECTARE";
  public static final String PUPAE_PERSON = "PUPAE_PERSON";

  public ImmatureThreshold()
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

    return this.getClassDisplayLabel() + ": (" + this.getDisplayLabel().getValue(Session.getCurrentLocale()) + ")";
  }

  @Override
  protected String buildKey()
  {
    return this.getDisease().getKey() + "." + this.getThresholdIndex();
  }

  @Override
  public ImmatureThresholdView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public ImmatureThresholdView unlockView()
  {
    this.unlock();

    return this.getView();
  }
  
  @Override
  public ImmatureThresholdView getView()
  {
    ImmatureThresholdView view = new ImmatureThresholdView();

    view.populateView(this);

    return view;
  }

  public static ImmatureThreshold getByDisease(String key)
  {
    Disease current = Disease.getCurrent();
        
    return ImmatureThreshold.getByKey(current.getKey() + "." + key);
  }

}
