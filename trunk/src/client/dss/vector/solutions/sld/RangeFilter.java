package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;
import dss.vector.solutions.query.RangeCategoryDTO;

public class RangeFilter extends Filter implements Reloadable
{
  private RangeCategoryDTO category;

  protected RangeFilter(LayerDTO layer, RangeCategoryDTO category)
  {
    super(layer);
    
    this.category = category;
  }
  
  protected void write(SLDWriter writer)
  {
    String lower = category.getLowerBoundStr();
    String upper = category.getUpperBoundStr();
    
    if(lower != null && lower.length() > 0)
    {
      writer.openTag("ogc:PropertyIsGreaterThanOrEqualTo");
      writer.writeEmptyTagWithValue("ogc:PropertyName", this.layer.getThematicColumnAlias());
      writer.writeEmptyTagWithValue("ogc:Literal", lower);
      writer.closeTag();
    }
    
    if(upper != null && upper.length() > 0)
    {    
      writer.openTag("ogc:PropertyIsLessThan");
      writer.writeEmptyTagWithValue("ogc:PropertyName", this.layer.getThematicColumnAlias());
      writer.writeEmptyTagWithValue("ogc:Literal", upper);
      writer.closeTag();
    }   
  }
}
