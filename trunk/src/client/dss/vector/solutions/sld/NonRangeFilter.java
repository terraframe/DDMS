package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.NonRangeCategoryDTO;
import dss.vector.solutions.query.QueryConstants;

public class NonRangeFilter extends Filter implements Reloadable
{
  private NonRangeCategoryDTO category;

  protected NonRangeFilter(NonRangeCategoryDTO category)
  {
    this.category = category;
  }

  protected void write(SLDWriter writer)
  {
    writer.writeln("<ogc:PropertyIsEqualTo>");
    writer.writeln("<ogc:PropertyName>"+QueryConstants.THEMATIC_DATA_COLUMN+"</ogc:PropertyName>");
    writer.writeln("<ogc:Literal>"+category.getExactValueStr()+"</ogc:Literal>");
    writer.writeln("</ogc:PropertyIsEqualTo>");
  }
}
