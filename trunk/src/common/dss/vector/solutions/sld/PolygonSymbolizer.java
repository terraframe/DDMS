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
