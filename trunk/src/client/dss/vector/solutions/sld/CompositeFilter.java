package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public class CompositeFilter extends Filter implements Reloadable
{
  private Filter[] filters;
  
  protected CompositeFilter(LayerDTO layer, Filter ... filters)
  {
    super(layer);
    
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
