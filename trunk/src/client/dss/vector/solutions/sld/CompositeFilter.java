package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

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
    writer.openTag("ogc:Filter");
    
    if(filters.length > 1)
    {
      writer.openTag("ogc:And");
    }
    
    for(Filter filter : filters)
    {
      filter.write(writer);
    }
    
    if(filters.length > 1)
    {
      writer.closeTag();
    }
    
    writer.closeTag();
  }

}
