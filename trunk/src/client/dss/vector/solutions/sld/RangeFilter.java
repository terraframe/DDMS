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
      writer.writeln("<ogc:PropertyIsGreaterThanOrEqualTo>");
      writer.writeln("<ogc:PropertyName>"+this.layer.getThematicColumnAlias()+"</ogc:PropertyName>");
      writer.writeln("<ogc:Literal>"+lower+"</ogc:Literal>");
      writer.writeln("</ogc:PropertyIsGreaterThanOrEqualTo>");
    }
    
    if(upper != null && upper.length() > 0)
    {    
      writer.writeln("<ogc:PropertyIsLessThan>");
      writer.writeln("<ogc:PropertyName>"+this.layer.getThematicColumnAlias()+"</ogc:PropertyName>");
      writer.writeln("<ogc:Literal>"+upper+"</ogc:Literal>");
      writer.writeln("</ogc:PropertyIsLessThan>");
    }
    
    
  }
}
