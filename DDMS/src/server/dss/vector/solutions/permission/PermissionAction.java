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
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdDimension;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.permissions.RolePropertyQuery;

public abstract class PermissionAction implements Reloadable
{
  private SystemURL url;

  protected RoleDAO role;

  private Disease   disease;

  protected PermissionAction(SystemURL url, Disease disease)
  {
    this.url = url;
    this.disease = disease;

    this.initializeRole();
  }

  protected PermissionAction(RoleDAO role, Disease disease)
  {
    this.disease = disease;
    this.role = role;
  }

  public SystemURL getUrl()
  {
    return url;
  }

  public Roles getRole()
  {
    return Roles.get(role.getId());
  }

  public void assign(MetadataDAOIF metadata)
  {
    MdDimension dimension = disease.getDimension();
    MdDimensionDAOIF mdDimension = MdDimensionDAO.get(dimension.getId());

    this.doIt(mdDimension, metadata);
  }

  private final void initializeRole()
  {
    String name = url.getKey().replace(" ", "");
    String _roleName = MDSSRoleInfo.MDSS_PREFIX + "." + disease.getKey() + "." + name + "." + this.getActionName();

    try
    {
      role = RoleDAO.findRole(_roleName).getBusinessDAO();
    }
    catch (DataNotFoundException e)
    {
      role = RoleDAO.createRole(_roleName, _roleName);
      role.apply();
    }

    RoleProperty property = this.getRoleProperty(role);

    if (!url.hasRole(property))
    {
      property.removeExistingURLs();

      url.addRoles(property).apply();
    }
  }

  private RoleProperty getRoleProperty(RoleDAO role)
  {
    PermissionOption roleType = this.getRoleType();

    RolePropertyQuery query = new RolePropertyQuery(new QueryFactory());
    query.WHERE(query.getRole().EQ(role.getId()));
    query.AND(query.getDisease().EQ(disease));
    query.AND(query.getRoleType().containsExactly(roleType));

    OIterator<? extends RoleProperty> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      RoleProperty property = new RoleProperty();
      property.setRole(Roles.get(role.getId()));
      property.setDisease(disease);
      property.addRoleType(roleType);
      property.apply();

      return property;
    }
    finally
    {
      it.close();
    }
  }

  protected abstract void doIt(MdDimensionDAOIF mdDimension, MetadataDAOIF metadata);

  protected abstract String getActionName();

  protected abstract PermissionOption getRoleType();
}
