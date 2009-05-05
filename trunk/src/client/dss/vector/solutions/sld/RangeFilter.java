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
    writer.writeln("<Filter>");
    writer.writeln("<ogc:PropertyIsBetween>");
    writer.writeln("<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>");
    writer.writeln("<ogc:LowerBoundary>");
    writer.writeln("<ogc:Literal>"+category.getLowerBound().doubleValue()+"</ogc:Literal>");
    writer.writeln("</ogc:LowerBoundary>");
    writer.writeln("<ogc:UpperBoundary>");
    writer.writeln("<ogc:Literal>"+category.getUpperBound().doubleValue()+"</ogc:Literal>");
    writer.writeln("</ogc:UpperBoundary>");
    writer.writeln("</ogc:PropertyIsBetween>");
    writer.writeln("</Filter>");
  }
}
