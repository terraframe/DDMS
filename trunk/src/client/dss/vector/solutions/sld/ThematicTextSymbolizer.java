package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesDTO;

public class ThematicTextSymbolizer extends Symbolizer implements Reloadable
{
  private LayerDTO layer;

  protected ThematicTextSymbolizer(LayerDTO layer, StylesDTO style)
  {
    super(style);
    
    this.layer = layer;
  }
  
  @Override
  protected void write(SLDWriter writer)
  {
    String label = "<ogc:PropertyName>"+QueryConstants.ENTITY_NAME_COLUMN+"</ogc:PropertyName> (<ogc:PropertyName>"+layer.getThematicColumnAlias()+"</ogc:PropertyName>)";
    
    StylesDTO style = this.getStyles();
    
    writer.openTag("TextSymbolizer");
    writer.writeEmptyTagWithValue("Label", label);
    
    TextSymbolizer.writeCommonStyle(writer, style);
    writer.closeTag();
  }

}
