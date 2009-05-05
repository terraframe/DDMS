package dss.vector.solutions.sld;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PointStyleDTO;
import dss.vector.solutions.query.AbstractCategoryDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.NonRangeCategoryDTO;
import dss.vector.solutions.query.RangeCategoryDTO;
import dss.vector.solutions.query.ThematicLayerDTO;
import dss.vector.solutions.query.WellKnownNamesDTO;

public class PointSymbolizer extends Symbolizer implements Reloadable
{

  protected PointSymbolizer(LayerDTO layer, PointStyleDTO styleRule)
  {
    super(layer, styleRule);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    PointStyleDTO style = this.getStyleRule();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();
    WellKnownNamesDTO wknDTO = style.getWellKnownName().get(0);
    String wkn = wknDTO.name().toLowerCase();


    LayerDTO layer = this.getLayer();
    if (layer instanceof ThematicLayerDTO)
    {
      ThematicLayerDTO tLayer = (ThematicLayerDTO) layer;
      List<? extends AbstractCategoryDTO> categories = tLayer.getAllDefinesCategory();

      for (AbstractCategoryDTO category : categories)
      {
        writer.writeln("<Rule>");

        Filter filter;
        if (category instanceof RangeCategoryDTO)
        {
          filter = new RangeFilter((RangeCategoryDTO) category);
        }
        else
        {
          filter = new NonRangeFilter((NonRangeCategoryDTO) category);
        }

        filter.write(writer);

        // The stroke color becomes the thematic color
        String thematicColor = category.getThematicColor();
        writeSymbolizer(writer, wkn, strokeWidth, thematicColor);
        writer.writeln("</Rule>");
      }

      // write default style (with Else Filter if other filters exist).
      writer.writeln("<Rule>");

      if(categories.size() > 0)
      {
        ElseFilter elseFilter = new ElseFilter();
        elseFilter.write(writer);
      }

      writeSymbolizer(writer, wkn, strokeWidth, stroke);
      writer.writeln("</Rule>");
    }
    else
    {
      // write default style
      writer.writeln("<Rule>");
      writeSymbolizer(writer, wkn, strokeWidth, stroke);
      writer.writeln("</Rule>");
    }
  }

  private void writeSymbolizer(SLDWriter writer, String wkn, String strokeWidth, String stroke)
  {
    writer.writeln("<PointSymbolizer>");
    writer.writeln("<Graphic>");
    writer.writeln("<Mark>");
    writer.writeln("<WellKnownName>" + wkn + "</WellKnownName><Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">" + stroke + "</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">" + strokeWidth + "</CssParameter>");
    writer.writeln("</Stroke>");
    // TODO style mark size and rotation
    writer.writeln("</Mark><Size>12</Size><Rotation>0</Rotation>");
    writer.writeln("</Graphic>");
    writer.writeln("</PointSymbolizer>");
  }

  @Override
  protected PointStyleDTO getStyleRule()
  {
    return (PointStyleDTO) super.getStyleRule();
  }

}
