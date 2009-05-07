package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.TextStyleDTO;

public class TextSymbolizer extends Symbolizer implements Reloadable
{
  private boolean includeThematic;

  protected TextSymbolizer(LayerDTO layer, TextStyleDTO styleRule, boolean includeThematic)
  {
    super(layer, styleRule);

    this.includeThematic = includeThematic;
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
    writer.write("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
    if(includeThematic)
    {
      writer.writeln(" (<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>)");
    }
    else
    {
      writer.writeln("");
    }
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
