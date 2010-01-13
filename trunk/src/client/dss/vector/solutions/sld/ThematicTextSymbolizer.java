package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesDTO;

public class ThematicTextSymbolizer extends Symbolizer implements Reloadable
{

  protected ThematicTextSymbolizer(StylesDTO style)
  {
    super(style);
  }
  
  @Override
  protected void write(SLDWriter writer)
  {
    StylesDTO style = this.getStyles();
    
    writer.writeln("<TextSymbolizer>");
    writer.writeln("<Label>");
    writer.write("<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName>");
    writer.writeln(" (<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>)");
    writer.writeln("</Label>");
    TextSymbolizer.writeCommonStyle(writer, style);
    writer.writeln("</TextSymbolizer>");
  }

}
