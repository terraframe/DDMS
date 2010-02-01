package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.RangeCategoryDTO;

public class RangeFilter extends Filter implements Reloadable
{
  private RangeCategoryDTO category;

  protected RangeFilter(RangeCategoryDTO category)
  {
    this.category = category;
  }
  
  protected void write(SLDWriter writer)
  {
    String lower = category.getLowerBoundStr();
    String upper = category.getUpperBoundStr();
    
    if(lower != null && lower.length() > 0)
    {
      writer.writeln("<ogc:PropertyIsGreaterThanOrEqualTo>");
      writer.writeln("<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>");
      writer.writeln("<ogc:Literal>"+lower+"</ogc:Literal>");
      writer.writeln("</ogc:PropertyIsGreaterThanOrEqualTo>");
    }
    
    if(upper != null && upper.length() > 0)
    {    
      writer.writeln("<ogc:PropertyIsLessThan>");
      writer.writeln("<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>");
      writer.writeln("<ogc:Literal>"+upper+"</ogc:Literal>");
      writer.writeln("</ogc:PropertyIsLessThan>");
    }
    
    
  }
}
