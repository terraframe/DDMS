package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.StylesDTO;

public class PolygonSymbolizer extends Symbolizer implements Reloadable
{
  protected PolygonSymbolizer(StylesDTO style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesDTO style = this.getStyles();
    
    String fill = style.getPolygonFill();
    String stroke = style.getPolygonStroke();
    String strokeWidth = style.getPolygonWidth().toString();
    String strokeOpacity = style.getPolygonStrokeOpacity().toString();
    String fillOpacity = style.getPolygonFillOpacity().toString();
    
    writer.writeln("<PolygonSymbolizer>");
    writer.writeln("<Fill>");
    writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
    writer.writeln("<CssParameter name=\"opacity\">"+fillOpacity+"</CssParameter>");
    writer.writeln("</Fill>");
    writer.writeln("<Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("<CssParameter name=\"opacity\">"+strokeOpacity+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</PolygonSymbolizer>");
  }
}
