package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.NonRangeCategoryDTO;

public class NonRangeFilter extends Filter implements Reloadable
{
  private NonRangeCategoryDTO category;

  protected NonRangeFilter(LayerDTO layer, NonRangeCategoryDTO category)
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
