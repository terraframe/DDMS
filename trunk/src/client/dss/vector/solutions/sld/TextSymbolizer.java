package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public class TextSymbolizer extends Symbolizer implements Reloadable
{

  protected TextSymbolizer(LayerDTO layer)
  {
    super(layer);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    /*
    LayerDTO layer = this.getLayer();

    TextStyleDTO style = this.getStyleRule();
    String fontFamily = style.getFontFamily();
    String fontStyle = style.getFontStyle();
    String fontSize = style.getFontSize().toString();
    String fill = style.getFill();



    String thematicVar = layer.getValue(LayerDTO.THEMATICVARIABLE);
    if(thematicVar != null && thematicVar.length() > 0)
    {
      writer.writeln("<Rule>");
      writer.writeln("<Filter>");
      writer.writeln("<PropertyIsEqualTo>");
      writer.writeln("<Function name=\"isNull\">");
      writer.writeln("<PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</PropertyName>");
      writer.writeln("</Function>");
      writer.writeln("<Literal>false</Literal>");
      writer.writeln("</PropertyIsEqualTo>");
      writer.writeln("</Filter>");
      writer.writeln("<TextSymbolizer>");
      writer.writeln("<Label>");
      writer.write("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
      writer.writeln(" (<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>)");
      writer.writeln("</Label>");
      writer.writeln("<Font>");
      writer.writeln("<CssParameter name=\"font-family\">"+fontFamily+"</CssParameter>");
      writer.writeln("<CssParameter name=\"font-style\">"+fontStyle+"</CssParameter>");
      writer.writeln("<CssParameter name=\"font-size\">"+fontSize+"</CssParameter>");
      writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
      writer.writeln("</Font>");
      writer.writeln("<LabelPlacement>");
      writer.writeln("<PointPlacement>");
      writer.writeln("<Displacement>");
      writer.writeln("<DisplacementX>10</DisplacementX>");
      writer.writeln("<DisplacementY>0</DisplacementY>");
      writer.writeln("</Displacement>");
      writer.writeln("</PointPlacement>");
      writer.writeln("</LabelPlacement>");
      writer.writeln("<Opacity>1.0</Opacity>");
      writer.writeln("</TextSymbolizer>");
      writer.writeln("</Rule>");
      writer.writeln("<Rule>");
      writer.writeln("<Filter>");
      writer.writeln("<PropertyIsEqualTo>");
      writer.writeln("<Function name=\"isNull\">");
      writer.writeln("<PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</PropertyName>");
      writer.writeln("</Function>");
      writer.writeln("<Literal>true</Literal>");
      writer.writeln("</PropertyIsEqualTo>");
      writer.writeln("</Filter>");
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
      writer.writeln("<LabelPlacement>");
      writer.writeln("<PointPlacement>");
      writer.writeln("<Displacement>");
      writer.writeln("<DisplacementX>10</DisplacementX>");
      writer.writeln("<DisplacementY>0</DisplacementY>");
      writer.writeln("</Displacement>");
      writer.writeln("</PointPlacement>");
      writer.writeln("</LabelPlacement>");
      writer.writeln("<VendorOption name=\"spaceAround\">-100</VendorOption>");
      writer.writeln("</TextSymbolizer>");
      writer.writeln("</Rule>");
    }
    else
    {
      writer.writeln("<Rule>");
      writer.writeln("<TextSymbolizer>");
      writer.writeln("<Label>");
      writer.writeln("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
      writer.writeln("</Label>");
      writer.writeln("<Font>");
      writer.writeln("<CssParameter name=\"font-family\">"+fontFamily+"</CssParameter>");
      writer.writeln("<CssParameter name=\"font-style\">"+fontStyle+"</CssParameter>");
      writer.writeln("<CssParameter name=\"font-size\">"+fontSize+"</CssParameter>");
      writer.writeln("</Font>");
      writer.writeln("<Fill>");
      writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
      writer.writeln("</Fill>");
      writer.writeln("<LabelPlacement>");
      writer.writeln("<PointPlacement>");
      writer.writeln("<Displacement>");
      writer.writeln("<DisplacementX>10</DisplacementX>");
      writer.writeln("<DisplacementY>0</DisplacementY>");
      writer.writeln("</Displacement>");
      writer.writeln("</PointPlacement>");
      writer.writeln("</LabelPlacement>");
      writer.writeln("<VendorOption name=\"spaceAround\">-100</VendorOption>");
      writer.writeln("</TextSymbolizer>");
      writer.writeln("</Rule>");

    }
    */
  }

}
