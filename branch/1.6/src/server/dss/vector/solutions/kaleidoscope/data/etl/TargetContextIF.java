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
import java.util.Map;
import java.util.Set;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.generation.loader.Reloadable;

public interface TargetContextIF extends Reloadable
{
  public Mutable newMutable(String sourceType);

  public String getType(String sourceType);

  public List<TargetFieldIF> getFields(String sourceType);

  public List<TargetDefinitionIF> getDefinitions();

  public TargetDefinitionIF getDefinition(String sourceType);

  public Map<String, Set<String>> getLocationExclusions();

  public Map<String, Set<String>> getCategoryExclusions();
}
