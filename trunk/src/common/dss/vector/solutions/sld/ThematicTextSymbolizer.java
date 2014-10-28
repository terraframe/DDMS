package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerIF;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.StylesIF;

public class ThematicTextSymbolizer extends Symbolizer implements Reloadable
{
  private LayerIF layer;

  protected ThematicTextSymbolizer(LayerIF layer, StylesIF style)
  {
    super(style);

    this.layer = layer;
  }

  @Override
  protected void write(SLDWriter writer)
  {
    String label = "<ogc:PropertyName>" + QueryConstants.ENTITY_NAME_COLUMN + "</ogc:PropertyName> (<ogc:PropertyName>" + layer.getThematicColumnAlias() + "</ogc:PropertyName>)";

    StylesIF style = this.getStyles();

    writer.openTag("TextSymbolizer");
    writer.writeEmptyTagWithValue("Label", label);

    TextSymbolizer.writeCommonStyle(writer, style);
    writer.closeTag();
  }

}
