package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesDTO;

public class TextSymbolizer extends Symbolizer implements Reloadable
{

  protected TextSymbolizer(StylesDTO style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesDTO style = this.getStyles();
    
    writer.writeln("<TextSymbolizer>");
    writer.writeln("<Label>");
    writer.writeln("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
    writer.writeln("</Label>");
    writeCommonStyle(writer, style);
    writer.writeln("</TextSymbolizer>");
  }
  
  protected static void writeCommonStyle(SLDWriter writer, StylesDTO style)
  {
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStyles().get(0).name().toLowerCase();
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
    
    writer.writeln("<Font>");
    writer.writeln("<CssParameter name=\"font-family\">"+fontFamily+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-style\">"+fontStyle+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-size\">"+fontSize+"</CssParameter>");
    writer.writeln("</Font>");
    writer.writeln("<Halo>");
    writer.writeln("<Radius>");
    writer.writeln("<ogc:Literal>"+haloRadius+"</ogc:Literal>");
    writer.writeln("</Radius>");
    writer.writeln("<Fill>");
    writer.writeln("<CssParameter name=\"fill\">"+haloFill+"</CssParameter>");
    writer.writeln("<CssParameter name=\"fill-opacity\">"+haloOpacity+"</CssParameter>");    
    writer.writeln("</Fill>");
    writer.writeln("</Halo>");
    writer.writeln("<Fill>");
    writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
    writer.writeln("</Fill>");
    writer.writeln("<LabelPlacement>");
    writer.writeln("<PointPlacement>");
    writer.writeln("<AnchorPoint>");
    writer.writeln("<AnchorPointX>"+anchorX+"</AnchorPointX>");
    writer.writeln("<AnchorPointY>"+anchorY+"</AnchorPointY>");
    writer.writeln("</AnchorPoint>");
    writer.writeln("<Displacement>");
    writer.writeln("<DisplacementX>"+disX+"</DisplacementX>");
    writer.writeln("<DisplacementY>"+disY+"</DisplacementY>");
    writer.writeln("</Displacement>");
    writer.writeln("<Rotation>"+rotation+"</Rotation>");
    writer.writeln("</PointPlacement>");
    writer.writeln("</LabelPlacement>");
    // These vender options force all labels to be rendered, regardless of overlapping.
    // And polygons will be rendered with only one label (working in conjunction with OpenLayers singleTile=true.
    writer.writeln("<VendorOption name=\"spaceAround\">-100</VendorOption>");
    writer.writeln("<VendorOption name=\"group\">true</VendorOption>");
//    writer.writeln("<VendorOption name=\"conflictResolution\">true</VendorOption>");
//    writer.writeln("<VendorOption name=\"goodnessOfFit\">0</VendorOption>");

  }

}
