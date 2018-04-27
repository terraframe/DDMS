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

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebBooleanDAOIF;
import com.runwaysdk.dataaccess.MdWebDateDAOIF;
import com.runwaysdk.dataaccess.MdWebDecimalDAOIF;
import com.runwaysdk.dataaccess.MdWebDoubleDAOIF;
import com.runwaysdk.dataaccess.MdWebFloatDAOIF;
import com.runwaysdk.dataaccess.MdWebIntegerDAOIF;
import com.runwaysdk.dataaccess.MdWebLongDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.FieldColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class ODKAttributeFactory implements Reloadable
{
  public static ODKAttribute getAttribute(MdAttributeDAOIF mdField)
  {
    if (mdField instanceof MdAttributeBooleanDAOIF)
    {
      return new BooleanAttribute((MdWebBooleanDAOIF) mdField);
    }
    else if (mdField instanceof MdWebFloatDAOIF)
    {
      return new FloatAttribute((MdWebFloatDAOIF) mdField);
    }
    else if (mdField instanceof MdWebDoubleDAOIF)
    {
      return new DoubleAttribute((MdWebDoubleDAOIF) mdField);
    }
    else if (mdField instanceof MdWebDecimalDAOIF)
    {
      return new DecimalAttribute((MdWebDecimalDAOIF) mdField);
    }
    else if (mdField instanceof MdWebLongDAOIF)
    {
      return new LongAttribute((MdWebLongDAOIF) mdField);
    }
    else if (mdField instanceof MdWebIntegerDAOIF)
    {
      return new IntegerAttribute((MdWebIntegerDAOIF) mdField);
    }
    else if (mdField instanceof MdWebDateDAOIF)
    {
      return new DateAttribute((MdWebDateDAOIF) mdField);
    }

    return new StringAttribute((MdWebAttributeDAOIF) mdField);
  }
  
  public static ODKAttribute convert(ExcelColumn excel)
  {
    if (excel instanceof FieldColumn)
    {
      return ODKAttributeFactory.getAttribute((MdAttributeDAOIF)((FieldColumn)excel).getMdField());
    }
    else
    {
      return new ODKAttribute(excel.getAttributeName(), excel.getDisplayLabel(), excel.getDescription(), excel.getIndex(), excel.isRequired());
    }
  }
}
