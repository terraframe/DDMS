package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.StylesIF;

public abstract class Symbolizer implements Reloadable
{
  private StylesIF styles;

  protected Symbolizer(StylesIF style)
  {
    this.styles = style;
  }

  protected StylesIF getStyles()
  {
    return styles;
  }

  /**
   * Writes the XML snippet for the style.
   * 
   * @param writer
   */
  protected abstract void write(SLDWriter writer);
}
