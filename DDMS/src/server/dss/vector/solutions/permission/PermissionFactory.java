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
package dss.vector.solutions.permission;

import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;

public class PermissionFactory implements Reloadable
{
  private String key;
  
  public PermissionFactory(String key)
  {
    this.key = key;
  }
  
  public PermissionAction getAction(SystemURL url, Disease disease)
  {
    if (key.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(url, disease);
    }

    return new ReadAction(url, disease);
  }

  public PermissionAction getAction(RoleDAO role, Disease disease)
  {
    if (key.equalsIgnoreCase("WRITE"))
    {
      return new WriteAction(role, disease);
    }
    
    return new ReadAction(role, disease);
  }
}
