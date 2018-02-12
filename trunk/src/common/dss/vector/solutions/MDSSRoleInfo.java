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
package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public interface MDSSRoleInfo extends Reloadable
{
  public static final String MDSS_PREFIX    = "mdss";

  public static final String GUI_VISIBILITY = MDSS_PREFIX + ".GUIVisibility";

  public static final String SYSTEM         = MDSS_PREFIX + ".System";

  public static final String RUNWAY_ADMIN   = "Administrator";
}
