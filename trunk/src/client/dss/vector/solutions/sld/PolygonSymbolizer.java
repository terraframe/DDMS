package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public class PolygonSymbolizer extends Symbolizer implements Reloadable
{
  protected PolygonSymbolizer(LayerDTO layer)
  {
    super(layer);
  }

  @Override
  protected void write(SLDWriter writer)
  {
    /*
    PolygonStyleDTO style = this.getStyleRule();
    String fill = style.getFill();
    String stroke = style.getStroke();
    String strokeWidth = style.getStrokeWidth().toString();

    LayerDTO layer = this.getLayer();
    
    List<? extends AbstractCategoryDTO> categories = layer.getAllDefinesCategory();

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
      writeSymbolizer(writer, thematicColor, strokeWidth, stroke);
      writer.writeln("</Rule>");
    }

    // write default style (with Else Filter if other filters exist).
    writer.writeln("<Rule>");

    if(categories.size() > 0)
    {
      ElseFilter elseFilter = new ElseFilter();
      elseFilter.write(writer);
    }

    writeSymbolizer(writer, fill, strokeWidth, stroke);
    writer.writeln("</Rule>");
    */
  }

  private void writeSymbolizer(SLDWriter writer, String fill, String strokeWidth, String stroke)
  {
    writer.writeln("<PolygonSymbolizer>");
    writer.writeln("<Fill>");
    writer.writeln("<CssParameter name=\"fill\">"+fill+"</CssParameter>");
    writer.writeln("</Fill>");
    writer.writeln("<Stroke>");
    writer.writeln("<CssParameter name=\"stroke\">"+stroke+"</CssParameter>");
    writer.writeln("<CssParameter name=\"stroke-width\">"+strokeWidth+"</CssParameter>");
    writer.writeln("</Stroke>");
    writer.writeln("</PolygonSymbolizer>");
  }
}
