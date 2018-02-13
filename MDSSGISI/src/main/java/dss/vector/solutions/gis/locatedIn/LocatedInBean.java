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
package dss.vector.solutions.gis.locatedIn;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * Bean which holds all data required for rebuilding the located in table;
 * 
 * @author Justin Smethie
 */
public class LocatedInBean implements Reloadable
{
  public enum BuildTypes implements Reloadable {

    REBUILD_ALL(1), ADDITIVE(2), ORPHANED_ONLY(3);

    private int code;

    private BuildTypes(int code)
    {
      this.code = code;
    }

    int getCode()
    {
      return code;
    }
  }

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private BuildTypes            option;

  /**
   * Percent of area two entites must over lap before one is considered located
   * in the other.
   */
  private int                   overlapPercent;

  public LocatedInBean()
  {
    this.option = null;
    this.overlapPercent = 80;

    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public BuildTypes getOption()
  {
    return option;
  }

  public void setOption(BuildTypes option)
  {
    propertyChangeSupport.firePropertyChange("option", this.option, this.option = option);
  }

  public int getOverlapPercent()
  {
    return overlapPercent;
  }

  public void setOverlapPercent(int overlapPercent)
  {
    propertyChangeSupport.firePropertyChange("overlapPercent", this.overlapPercent, this.overlapPercent = overlapPercent);
  }
}
