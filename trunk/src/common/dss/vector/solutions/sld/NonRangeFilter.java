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
package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerIF;
import dss.vector.solutions.query.NonRangeCategoryIF;

public class NonRangeFilter extends Filter implements Reloadable
{
  private NonRangeCategoryIF category;

  protected NonRangeFilter(LayerIF layer, NonRangeCategoryIF category)
  {
    super(layer);

    this.category = category;
  }

  protected void write(SLDWriter writer)
  {
    writer.openTag("ogc:PropertyIsEqualTo");
    writer.writeEmptyTagWithValue("ogc:PropertyName", this.layer.getThematicColumnAlias());
    writer.writeEmptyTagWithValue("ogc:Literal", category.getExactValueStr());
    writer.closeTag();
  }
}
