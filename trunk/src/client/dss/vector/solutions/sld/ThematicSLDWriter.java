package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.TextStyleDTO;
import dss.vector.solutions.query.ThematicLayerDTO;

public class ThematicSLDWriter extends SLDWriter implements Reloadable
{

  protected ThematicSLDWriter(ThematicLayerDTO layer)
  {
    super(layer);
  }

  protected ThematicLayerDTO getLayer()
  {
    return (ThematicLayerDTO) super.getLayer();
  }

  /**
   * Writes the text style for both the entity name
   * and thematic variable.
   */
  @Override
  protected void writeTextStyle(TextStyleDTO textStyle)
  {
    // thematic variable value (use text style for entire layer)
    ThematicLayerDTO layer = getLayer();
    String variable = layer.getThematicVariable();

    boolean writeThematic = false;
    if(variable != null && variable.trim().length() > 0)
    {
      writeThematic = true;
    }

    TextSymbolizer textSymbolizer = new TextSymbolizer(getLayer(), textStyle, writeThematic);
    textSymbolizer.write(this);
  }
}
