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

import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.session.Session;

public abstract class ODKPrimitiveAttribute extends ODKAttribute
{
  private MdWebAttributeDAOIF mdField;

  public ODKPrimitiveAttribute(MdWebAttributeDAOIF mdField)
  {
    this(mdField, 0);
  }

  public ODKPrimitiveAttribute(MdWebAttributeDAOIF mdField, int index)
  {
    super(mdField.getDefiningMdAttribute().getValue(MdAttributeConcreteInfo.NAME), mdField.getDisplayLabel(Session.getCurrentLocale()), mdField.getDescription(Session.getCurrentLocale()), index, Boolean.parseBoolean(mdField.isRequired()));

    this.mdField = mdField;
  }

  /**
   * @return The name of the setter method (in the generated source) for this
   *         column
   */
  public String getSetterMethodName()
  {
    MdAttributeDAOIF mdAttribute = mdField.getDefiningMdAttribute();
    String name = mdAttribute.getMdAttributeConcrete().getValue(MdAttributeConcreteInfo.NAME);

    return CommonGenerationUtil.SET + CommonGenerationUtil.upperFirstCharacter(name);
  }

  public MdFieldDAOIF getMdField()
  {
    return mdField;
  }
}
