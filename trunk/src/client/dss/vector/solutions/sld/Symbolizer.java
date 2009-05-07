package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LineStyleDTO;
import dss.vector.solutions.PointStyleDTO;
import dss.vector.solutions.PolygonStyleDTO;
import dss.vector.solutions.query.GeometryStyleDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.StyleRuleDTO;

public abstract class Symbolizer implements Reloadable
{
  private LayerDTO layer;
  private StyleRuleDTO styleRule;

  protected Symbolizer(LayerDTO layer, StyleRuleDTO styleRule)
  {
    this.layer = layer;
    this.styleRule = styleRule;
  }

  protected StyleRuleDTO getStyleRule()
  {
    return styleRule;
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

  /**
   * Returns the concrete Symbolizer subclass for use with the given
   * StyleRuleDTO.
   *
   * @param style
   * @return
   */
  protected static Symbolizer getGeometrySymbolizer(LayerDTO layer, GeometryStyleDTO style)
  {
    if(style instanceof PointStyleDTO)
    {
      return new PointSymbolizer(layer, (PointStyleDTO) style);
    }
    else if(style instanceof LineStyleDTO)
    {
      return new LineSymbolizer(layer, (LineStyleDTO) style);
    }
    else
    {
      return new PolygonSymbolizer(layer, (PolygonStyleDTO) style);
    }
  }
}
