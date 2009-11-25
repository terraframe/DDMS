package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.StylesDTO;

public abstract class Symbolizer implements Reloadable
{
  private LayerDTO layer;
  private StylesDTO styles;

  protected Symbolizer(LayerDTO layer)
  {
    this.layer = layer;
    this.styles = null;
  }

  protected StylesDTO getStyles()
  {
    return styles;
  }

  protected LayerDTO getLayer()
  {
    return layer;
  }

  /**
   * Writes the XML snippet for the style.
   * @param writer
   */
  protected abstract void write(SLDWriter writer);
}
