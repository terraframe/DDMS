package dss.vector.solutions.sld;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LineStyleDTO;
import dss.vector.solutions.query.AbstractCategoryDTO;
import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.NonRangeCategoryDTO;
import dss.vector.solutions.query.RangeCategoryDTO;
import dss.vector.solutions.query.ThematicLayerDTO;

public class LineSymbolizer extends Symbolizer implements Reloadable
{
  protected LineSymbolizer(LayerDTO layerDTO, LineStyleDTO styleRule)
  {
    super(layerDTO, styleRule);
  }

  @Override
  protected LineStyleDTO getStyleRule()
  {
    return (LineStyleDTO) super.getStyleRule();
  }

  @Override
  protected void write(SLDWriter writer)
  {
    LineStyleDTO style = this.getStyleRule();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();

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
        writeSymbolizer(writer, strokeWidth, thematicColor);
        writer.writeln("</Rule>");
      }

      // write default style (with Else Filter if other filters exist).
      writer.writeln("<Rule>");

      if(categories.size() > 0)
      {
        ElseFilter elseFilter = new ElseFilter();
        elseFilter.write(writer);
      }

      writeSymbolizer(writer, strokeWidth, stroke);
      writer.writeln("</Rule>");
    }
    else
    {
      // write default style
      writer.writeln("<Rule>");
      writeSymbolizer(writer, strokeWidth, stroke);
      writer.writeln("</Rule>");
    }
  }

  private void writeSymbolizer(SLDWriter writer, String strokeWidth, String stroke)
  {

    writer.writeln("<LineSymbolizer>");
    writer.writeln("<Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</LineSymbolizer>");
  }
}
