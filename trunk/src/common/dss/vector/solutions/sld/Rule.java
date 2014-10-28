package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

public class Rule implements Reloadable
{
  private Filter filter;
  private Symbolizer[] symbolizers;
  
  public Rule(Filter filter, Symbolizer ... symbolizers)
  {
    this.filter = filter;
    this.symbolizers = symbolizers;
  }
  
  public void write(SLDWriter writer)
  {
    writer.openTag("Rule");
    
    if(filter != null)
    {
      filter.write(writer);
    }
    
    for(Symbolizer symbolizer : symbolizers)
    {
      symbolizer.write(writer);
    }
    
    writer.closeTag();
  }
}
