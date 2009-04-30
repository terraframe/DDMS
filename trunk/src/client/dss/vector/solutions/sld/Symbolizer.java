package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LineStyleDTO;
import dss.vector.solutions.PointStyleDTO;
import dss.vector.solutions.PolygonStyleDTO;
import dss.vector.solutions.query.StyleRuleDTO;
import dss.vector.solutions.query.TextStyleDTO;

public abstract class Symbolizer implements Reloadable
{
  private StyleRuleDTO styleRule;

  protected Symbolizer(StyleRuleDTO styleRule)
  {
    this.styleRule = styleRule;
  }

  protected StyleRuleDTO getStyleRule()
  {
    return styleRule;
  }

  /**
   * Writes the XML snippet for the style.
   * @param writer
   */
  protected abstract void write(SLDWriter writer);

  /**
   * Returns the concrete Symbolizer subclass for use with the given
   * StyleRuleDTO.
   *
   * @param style
   * @return
   */
  protected static Symbolizer getSymbolizer(StyleRuleDTO style)
  {
    if(style instanceof PointStyleDTO)
    {
      return new PointSymbolizer((PointStyleDTO) style);
    }
    else if(style instanceof LineStyleDTO)
    {
      return new LineSymbolizer((LineStyleDTO) style);
    }
    else if(style instanceof PolygonStyleDTO)
    {
      return new PolygonSymbolizer((PolygonStyleDTO) style);
    }
    else
    {
      return new TextSymbolizer((TextStyleDTO) style);
    }
  }
}
