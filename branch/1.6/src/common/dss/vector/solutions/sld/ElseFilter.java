package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerIF;

public class ElseFilter extends Filter implements Reloadable
{
  public ElseFilter(LayerIF layer)
  {
    super(layer);
  }

  protected void write(SLDWriter writer)
  {
    writer.writeEmptyTag("ElseFilter");
  }

}
