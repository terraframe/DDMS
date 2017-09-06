/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Runway SDK(tm). If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebAttribute;

public abstract class TargetFieldBinding extends TargetFieldBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1647156669;

  public TargetFieldBinding()
  {
    super();
  }

  public abstract TargetFieldIF getTargetField();

  protected void populate(TargetField field)
  {
    MdWebAttribute targetAttribute = this.getTargetAttribute();
    MdAttribute mdAttribute = targetAttribute.getDefiningMdAttribute();
    String key = targetAttribute.getKey();
    String attributeName = mdAttribute.getAttributeName();

    field.setKey(key);
    field.setLabel(targetAttribute.getDisplayLabel().getValue());
    field.setName(attributeName);
  }
}
