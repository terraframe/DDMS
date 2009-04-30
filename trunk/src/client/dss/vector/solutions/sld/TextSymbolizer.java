package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.TextStyleDTO;

public class TextSymbolizer extends Symbolizer implements Reloadable
{

  protected TextSymbolizer(TextStyleDTO styleRule)
  {
    super(styleRule);
  }

  @Override
  protected TextStyleDTO getStyleRule()
  {
    return (TextStyleDTO) super.getStyleRule();
  }

  @Override
  protected void write(SLDWriter writer)
  {
    TextStyleDTO style = this.getStyleRule();
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStyle();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();

    writer.writeln("<Rule>");
    writer.writeln("<TextSymbolizer>");
    writer.writeln("<Label>");
    writer.writeln("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
    writer.writeln("</Label>");
    writer.writeln("<Font>");
    writer.writeln("<CssParameter name=\"font-family\">"+fontFamily+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-style\">"+fontStyle+"</CssParameter>");
    writer.writeln("<CssParameter name=\"font-size\">"+fontSize+"</CssParameter>");
    writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
    writer.writeln("</Font>");
    writer.writeln("</TextSymbolizer>");
    writer.writeln("</Rule>");
  }

}
