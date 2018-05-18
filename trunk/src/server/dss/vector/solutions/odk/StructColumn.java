/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.odk;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class StructColumn extends AttributeColumn implements Reloadable
{
  private MdAttributeStructDAOIF mdAttributeStruct;

  public StructColumn(MdAttributeStructDAOIF struct, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    this(struct, sourceMdAttr, viewMdAttr, 0);
  }

  public StructColumn(MdAttributeStructDAOIF struct, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, int index)
  {
    super(sourceMdAttr, viewMdAttr, index);

    this.mdAttributeStruct = struct;
    this.attributeName = mdAttributeStruct.definesAttribute() + '.' + attributeName;
    this.displayLabel = mdAttributeStruct.getDisplayLabel(Session.getCurrentLocale()) + ": " + displayLabel;
  }

}
