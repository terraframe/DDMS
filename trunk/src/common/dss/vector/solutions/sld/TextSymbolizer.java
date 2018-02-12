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

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesIF;

public class TextSymbolizer extends Symbolizer implements Reloadable
{

  protected TextSymbolizer(StylesIF style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesIF style = this.getStyles();

    writer.openTag("TextSymbolizer");
    writer.openTag("Label");

    writer.writeEmptyTagWithValue("ogc:PropertyName", QueryConstants.ENTITY_NAME_COLUMN);

    writer.closeTag();

    writeCommonStyle(writer, style);
    writer.closeTag();
  }

  protected static void writeCommonStyle(SLDWriter writer, StylesIF style)
  {
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStylesName();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();
    String anchorX = style.getAnchorPointX().toString();
    String anchorY = style.getAnchorPointY().toString();
    String disX = style.getDisplacementX().toString();
    String disY = style.getDisplacementY().toString();
    String rotation = style.getLabelRotation().toString();
    String haloRadius = style.getLabelHaloRadius().toString();
    String haloFill = style.getLabelHaloFill();
    String haloOpacity = style.getLabelHaloOpacity().toString();
    String goodnessOfFit = style.getGoodnessOfFit().toString();
    String conflictResolution = style.getConflictResolution().toString();
    String spaceAround = style.getSpaceAround().toString();

    writer.openTag("Font");
    writer.writeTagWithValue("CssParameter", "name", "font-family", fontFamily);
    writer.writeTagWithValue("CssParameter", "name", "font-style", fontStyle);
    writer.writeTagWithValue("CssParameter", "name", "font-size", fontSize);
    writer.closeTag();

    writer.openTag("LabelPlacement");
    writer.openTag("PointPlacement");

    writer.openTag("AnchorPoint");
    writer.writeEmptyTagWithValue("AnchorPointX", anchorX);
    writer.writeEmptyTagWithValue("AnchorPointY", anchorY);
    writer.closeTag();

    writer.openTag("Displacement");
    writer.writeEmptyTagWithValue("DisplacementX", disX);
    writer.writeEmptyTagWithValue("DisplacementY", disY);
    writer.closeTag();

    writer.writeEmptyTagWithValue("Rotation", rotation);

    writer.closeTag();
    writer.closeTag();

    writer.openTag("Halo");

    writer.openTag("Radius");
    writer.writeEmptyTagWithValue("ogc:Literal", haloRadius);
    writer.closeTag();

    writer.openTag("Fill");
    writer.writeTagWithValue("CssParameter", "name", "fill", haloFill);
    writer.writeTagWithValue("CssParameter", "name", "fill-opacity", haloOpacity);
    writer.closeTag();

    writer.closeTag();

    writer.openTag("Fill");
    writer.writeTagWithValue("CssParameter", "name", "fill", fill);
    writer.closeTag();

    // These vender options force all labels to be rendered, regardless of overlapping.
    // And polygons will be rendered with only one label (working in conjunction with OpenLayers singleTile=true.
    writer.writeTagWithValue("VendorOption", "name", "spaceAround", spaceAround);
    writer.writeTagWithValue("VendorOption", "name", "conflictResolution", conflictResolution);
    writer.writeTagWithValue("VendorOption", "name", "goodnessOfFit", goodnessOfFit);

    // writer.writeTagWithValue("VendorOption", "name", "spaceAround", "-100");
    // writer.writeTagWithValue("VendorOption", "name", "group", "true");
    // writer.writeln("<VendorOption name=\"conflictResolution\">true</VendorOption>");
    // writer.writeln("<VendorOption name=\"goodnessOfFit\">0</VendorOption>");
  }

}
