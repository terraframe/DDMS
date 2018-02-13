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
package dss.vector.solutions.kaleidoscope.oda.driver;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;

public class StringParameter implements IParameter
{
  private String parameterName;

  private int    isNullable;

  public StringParameter(String parameterName, int isNullable)
  {
    this.parameterName = parameterName;
    this.isNullable = isNullable;
  }

  @Override
  public int getParameterType()
  {
    return MetaDataTypeInfo.STRING_PARAMETER;
  }

  @Override
  public int getPrecision()
  {
    return -1;
  }

  @Override
  public int getScale()
  {
    return -1;
  }

  @Override
  public int isNullable()
  {
    return this.isNullable;
  }

  @Override
  public String getParameterName()
  {
    return this.parameterName;
  }

  @Override
  public int getParameterMode()
  {
    return IParameterMetaData.parameterModeIn;
  }

}
