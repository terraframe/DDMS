package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

public class CompositeFilter extends Filter implements Reloadable
{
  private Filter[] filters;
  
  public CompositeFilter(Filter ... filters)
  {
    this.filters = filters;  
  }

  @Override
  protected void write(SLDWriter writer)
  {
    writer.writeln("<Filter>");
    
    if(filters.length > 1)
    {
      writer.writeln("<ogc:And>");
    }
    
    for(Filter filter : filters)
    {
      filter.write(writer);
    }
    
    if(filters.length > 1)
    {
      writer.writeln("</ogc:And>");
    }
    writer.writeln("</Filter>");
  }

}
