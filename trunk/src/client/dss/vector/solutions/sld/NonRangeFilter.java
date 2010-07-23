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
    writer.writeln("<ogc:PropertyIsEqualTo>");
    writer.writeln("<ogc:PropertyName>"+this.layer.getThematicColumnAlias()+"</ogc:PropertyName>");
    writer.writeln("<ogc:Literal>"+category.getExactValueStr()+"</ogc:Literal>");
    writer.writeln("</ogc:PropertyIsEqualTo>");
  }
}
