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
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesIF;

public class ThematicTextSymbolizer extends Symbolizer implements Reloadable
{
  private LayerIF layer;

  protected ThematicTextSymbolizer(LayerIF layer, StylesIF style)
  {
    super(style);

    this.layer = layer;
  }

  @Override
  protected void write(SLDWriter writer)
  {
    String label = "<ogc:PropertyName>" + QueryConstants.ENTITY_NAME_COLUMN + "</ogc:PropertyName> (<ogc:PropertyName>" + layer.getThematicColumnAlias() + "</ogc:PropertyName>)";

    StylesIF style = this.getStyles();

    writer.openTag("TextSymbolizer");
    writer.writeEmptyTagWithValue("Label", label);

    TextSymbolizer.writeCommonStyle(writer, style);
    writer.closeTag();
  }

}
