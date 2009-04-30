package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PolygonStyleDTO;

public class PolygonSymbolizer extends Symbolizer implements Reloadable
{
  protected PolygonSymbolizer(PolygonStyleDTO styleRule)
  {
    super(styleRule);
  }

  @Override
  protected PolygonStyleDTO getStyleRule()
  {
    return (PolygonStyleDTO) super.getStyleRule();
  }

  @Override
  protected void write(SLDWriter writer)
  {
    PolygonStyleDTO style = this.getStyleRule();
    String fill = style.getFill();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();

    writer.writeln("<Rule>");
    writer.writeln("<PolygonSymbolizer>");
    writer.writeln("<Fill>");
    writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
    writer.writeln("</Fill>");
    writer.writeln("<Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</PolygonSymbolizer>");
    writer.writeln("</Rule>");
  }
}
