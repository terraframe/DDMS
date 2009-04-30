package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PointStyleDTO;
import dss.vector.solutions.query.WellKnownNamesDTO;

public class PointSymbolizer extends Symbolizer implements Reloadable
{

  protected PointSymbolizer(PointStyleDTO styleRule)
  {
    super(styleRule);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    PointStyleDTO style = this.getStyleRule();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();
    WellKnownNamesDTO wknDTO = style.getWellKnownName().get(0);
    String wkn = wknDTO.name().toLowerCase();

    writer.writeln("<Rule>");
    writer.writeln("<PointSymbolizer>");
    writer.writeln("<Graphic>");
    writer.writeln("<Mark>");
    writer.writeln("<WellKnownName>"+wkn+"</WellKnownName><Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("</Stroke>");
    // TODO style mark size and rotation
    writer.writeln("</Mark><Size>12</Size><Rotation>0</Rotation>");
    writer.writeln("</Graphic>");
    writer.writeln("</PointSymbolizer>");
    writer.writeln("</Rule>");
  }

  @Override
  protected PointStyleDTO getStyleRule()
  {
    return (PointStyleDTO) super.getStyleRule();
  }

}
