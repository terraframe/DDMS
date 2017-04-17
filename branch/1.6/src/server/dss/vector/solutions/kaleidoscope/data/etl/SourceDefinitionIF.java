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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;

public interface SourceDefinitionIF
{
  public String getType();

  public String getName();

  public String getLabel();

  public String getId();

  public String getCountry();

  public boolean isNew();

  public SourceFieldIF getFieldByName(String columnName);

  public SourceFieldIF getFieldByLabel(String label);

  public List<SourceFieldIF> getFields();

  public void persist();

  public boolean isApplied();
}
