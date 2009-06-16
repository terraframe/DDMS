package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.TextStyleDTO;
import dss.vector.solutions.query.ThematicLayerDTO;

public class TextSymbolizer extends Symbolizer implements Reloadable
{

  protected TextSymbolizer(LayerDTO layer, TextStyleDTO styleRule)
  {
    super(layer, styleRule);
  }

  @Override
  protected TextStyleDTO getStyleRule()
  {
    return (TextStyleDTO) super.getStyleRule();
  }

  @Override
  protected void write(SLDWriter writer)
  {
    LayerDTO layer = this.getLayer();

    TextStyleDTO style = this.getStyleRule();
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStyle();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();

    writer.writeln("<Rule>");
    writer.writeln("<TextSymbolizer>");
    writer.writeln("<Label>");


    if(layer instanceof ThematicLayerDTO
        && ((ThematicLayerDTO)layer).getThematicVariable() != null)
    {
      writer.write("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
      writer.writeln(" (<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>)");
    }
    else
    {
      writer.writeln("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
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
