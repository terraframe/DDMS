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
package dss.vector.solutions.intervention.monitor;

public class LarvacideInstance extends LarvacideInstanceBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372023824L;
  
  public LarvacideInstance()
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
  public LarvacideInstanceView getView()
  {
    LarvacideInstanceView view = new LarvacideInstanceView();
    view.populateView(this);
    
    return view;
  }
  
  @Override
  public LarvacideInstanceView lockView()
  {
    this.lock();

    return this.getView();
  }
  
  @Override
  public LarvacideInstanceView unlockView()
  {
    this.unlock();

    return this.getView();
  }
}
