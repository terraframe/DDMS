package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesDTO;

public class ThematicTextSymbolizer extends Symbolizer implements Reloadable
{

  protected ThematicTextSymbolizer(StylesDTO style)
  {
    super(style);
  }
  
  @Override
  protected void write(SLDWriter writer)
  {
    StylesDTO style = this.getStyles();
    
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStyle();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();
    
    writer.writeln("<TextSymbolizer>");
    writer.writeln("<Label>");
    writer.write("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
    writer.writeln(" (<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>)");
    writer.writeln("</Label>");
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
    writer.writeln("<Displacement>");
    writer.writeln("<DisplacementX>10</DisplacementX>");
    writer.writeln("<DisplacementY>0</DisplacementY>");
    writer.writeln("</Displacement>");
    writer.writeln("</PointPlacement>");
    writer.writeln("</LabelPlacement>");
    writer.writeln("<VendorOption name=\"spaceAround\">-100</VendorOption>");
    writer.writeln("</TextSymbolizer>");
  }

}
