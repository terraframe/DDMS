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
package dss.vector.solutions.general;

import com.runwaysdk.generation.loader.Reloadable;

public class Progress implements Reloadable
{
  private String  viewName;

  private Integer total;

  private Integer current;

  public Progress(Integer total)
  {
    this.viewName = new String();
    this.current = 0;
    this.total = total;
  }

  public String getViewName()
  {
    return viewName;
  }

  public Integer getTotal()
  {
    return total;
  }

  public Integer getCurrent()
  {
    return current;
  }

  public void setViewName(String viewName)
  {
    this.viewName = viewName;
  }

  public void setTotal(Integer total)
  {
    this.total = total;
  }

  public void setCurrent(Integer current)
  {
    this.current = current;
  }

  public void increment(String viewName)
  {
    this.setViewName(viewName);
    this.current++;
  }

  public Integer getProgress()
  {
    return ( ( 100 * this.current ) / this.total );
  }
}
