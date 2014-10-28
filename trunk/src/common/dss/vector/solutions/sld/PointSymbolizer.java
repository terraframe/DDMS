package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.StylesIF;

public class PointSymbolizer extends Symbolizer implements Reloadable
{

  protected PointSymbolizer(StylesIF style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesIF style = this.getStyles();

    String stroke = style.getPointStroke();
    String strokeWidth = style.getPointWidth().toString();
    String wkn = style.getPointMarkerName();
    String strokeOpacity = style.getPointStrokeOpacity().toString();
    String pointSize = style.getPointSize().toString();
    String rotation = style.getPointRotation().toString();

    writer.openTag("PointSymbolizer");
    writer.openTag("Graphic");
    writer.openTag("Mark");

    writer.writeEmptyTagWithValue("WellKnownName", wkn);

    writer.openTag("Stroke");
    writer.writeTagWithValue("CssParameter", "name", "stroke", stroke);
    writer.writeTagWithValue("CssParameter", "name", "stroke-width", strokeWidth);
    writer.writeTagWithValue("CssParameter", "name", "opacity", strokeOpacity);
    writer.closeTag();

    writer.closeTag();

    writer.writeEmptyTagWithValue("Size", pointSize);
    writer.writeEmptyTagWithValue("Rotation", rotation);

    writer.closeTag();
    writer.closeTag();
  }
}
