package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.StylesDTO;
import dss.vector.solutions.query.WellKnownNamesDTO;

public class PointSymbolizer extends Symbolizer implements Reloadable
{

  protected PointSymbolizer(StylesDTO style)
  {
    super(style);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    StylesDTO style = this.getStyles();
    
    String stroke = style.getPointStroke();
    String strokeWidth = style.getPointWidth().toString();
    WellKnownNamesDTO wknDTO = style.getPointMarker().get(0);
    String wkn = wknDTO.name().toLowerCase();
    String strokeOpacity = style.getPointStrokeOpacity().toString();
    String pointSize = style.getPointSize().toString();
    String rotation = style.getPointRotation().toString();

    writer.writeln("<PointSymbolizer>");
    writer.writeln("<Graphic>");
    writer.writeln("<Mark>");
    writer.writeln("<WellKnownName>" + wkn + "</WellKnownName><Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">" + stroke + "</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">" + strokeWidth + "</CssParameter>");
    writer.writeln("<CssParameter name=\"opacity\">"+strokeOpacity+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</Mark><Size>"+pointSize+"</Size><Rotation>"+rotation+"</Rotation>");
    writer.writeln("</Graphic>");
    writer.writeln("</PointSymbolizer>");
  }
}
