package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

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
    String fontStyle = style.getFontStyle();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();
    String anchorX = style.getAnchorPointX().toString();
    String anchorY = style.getAnchorPointY().toString();
    String disX = style.getDisplacementX().toString();
    String disY = style.getDisplacementY().toString();
    String rotation = style.getLabelRotation().toString();
    
    writer.writeln("<Font>");
    writer.writeln("<CssParameter name=\"font-family\">"+fontFamily+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-style\">"+fontStyle+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-size\">"+fontSize+"</CssParameter>");
    writer.writeln("</Font>");
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
    writer.writeln("<VendorOption name=\"spaceAround\">-100</VendorOption>");
//    writer.writeln("<VendorOption name=\"conflictResolution\">false</VendorOption>");
    writer.writeln("<VendorOption name=\"goodnessOfFit\">0</VendorOption>");
    writer.writeln("<VendorOption name=\"group\">On</VendorOption>");

  }

}
