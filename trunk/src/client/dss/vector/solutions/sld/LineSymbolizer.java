package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LineStyleDTO;

public class LineSymbolizer extends Symbolizer implements Reloadable
{
  protected LineSymbolizer(LineStyleDTO styleRule)
  {
    super(styleRule);
  }

  @Override
  protected LineStyleDTO getStyleRule()
  {
    return (LineStyleDTO) super.getStyleRule();
  }

  @Override
  protected void write(SLDWriter writer)
  {
    LineStyleDTO style = this.getStyleRule();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();

    writer.writeln("<Rule>");
    writer.writeln("<LineSymbolizer>");
    writer.writeln("<Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</LineSymbolizer>");
    writer.writeln("</Rule>");
  }
}
