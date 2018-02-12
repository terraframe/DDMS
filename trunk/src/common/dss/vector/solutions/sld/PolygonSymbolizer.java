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

import dss.vector.solutions.query.StylesIF;

public class PolygonSymbolizer extends Symbolizer implements Reloadable
{
  protected PolygonSymbolizer(StylesIF style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesIF style = this.getStyles();

    String fill = style.getPolygonFill();
    String stroke = style.getPolygonStroke();
    String strokeWidth = style.getPolygonWidth().toString();
    String strokeOpacity = style.getPolygonStrokeOpacity().toString();
    String fillOpacity = style.getPolygonFillOpacity().toString();

    writer.openTag("PolygonSymbolizer");

    writer.openTag("Fill");
    writer.writeTagWithValue("CssParameter", "name", "fill", fill);
    writer.writeTagWithValue("CssParameter", "name", "opacity", fillOpacity);
    writer.closeTag();

    writer.openTag("Stroke");
    writer.writeTagWithValue("CssParameter", "name", "stroke", stroke);
    writer.writeTagWithValue("CssParameter", "name", "stroke-width", strokeWidth);
    writer.writeTagWithValue("CssParameter", "name", "opacity", strokeOpacity);
    writer.closeTag();

    writer.closeTag();
  }
}
