package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.StylesDTO;

public abstract class Symbolizer implements Reloadable
{
  private StylesDTO styles;

  protected Symbolizer(StylesDTO style)
  {
    this.styles = style;
  }

  protected StylesDTO getStyles()
  {
    return styles;
  }

  /**
   * Writes the XML snippet for the style.
   * @param writer
   */
  protected abstract void write(SLDWriter writer);
}
