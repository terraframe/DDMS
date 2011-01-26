package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public class ElseFilter extends Filter implements Reloadable
{
  public ElseFilter(LayerDTO layer)
  {
    super(layer);
  }

  protected void write(SLDWriter writer)
  {
    writer.writeEmptyTag("ElseFilter");
  }

}
