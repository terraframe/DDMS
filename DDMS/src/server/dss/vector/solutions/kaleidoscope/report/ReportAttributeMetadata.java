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
package dss.vector.solutions.kaleidoscope.report;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;

public class ReportAttributeMetadata implements Reloadable
{
  private String             attributeName;

  private AllAggregationType aggregation;

  private Boolean            orderBy;

  public ReportAttributeMetadata(String attributeName)
  {
    this(null, attributeName, false);
  }

  public ReportAttributeMetadata(AllAggregationType aggregation, String attributeName)
  {
    this(aggregation, attributeName, false);
  }

  public ReportAttributeMetadata(AllAggregationType aggregation, String attributeName, Boolean orderBy)
  {
    super();
    this.aggregation = aggregation;
    this.attributeName = attributeName;
    this.orderBy = orderBy;
  }

  public AllAggregationType getAggregation()
  {
    return aggregation;
  }

  public void setAggregation(AllAggregationType aggregation)
  {
    this.aggregation = aggregation;
  }

  public String getAttributeName()
  {
    return attributeName;
  }

  public void setAttributeName(String attributeName)
  {
    this.attributeName = attributeName;
  }

  public void setOrderBy(Boolean orderBy)
  {
    this.orderBy = orderBy;
  }

  public Boolean isOrderBy()
  {
    return orderBy;
  }
}
