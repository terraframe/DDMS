package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerIF;
import dss.vector.solutions.query.NonRangeCategoryIF;

public class NonRangeFilter extends Filter implements Reloadable
{
  private NonRangeCategoryIF category;

  protected NonRangeFilter(LayerIF layer, NonRangeCategoryIF category)
  {
    super(layer);

    this.category = category;
  }

  protected void write(SLDWriter writer)
  {
    writer.openTag("ogc:PropertyIsEqualTo");
    writer.writeEmptyTagWithValue("ogc:PropertyName", this.layer.getThematicColumnAlias());
    writer.writeEmptyTagWithValue("ogc:Literal", category.getExactValueStr());
    writer.closeTag();
  }
}
