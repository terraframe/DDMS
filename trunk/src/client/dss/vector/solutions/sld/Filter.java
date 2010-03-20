package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerDTO;

public abstract class Filter implements Reloadable
{
  protected LayerDTO layer;
  
  protected Filter(LayerDTO layer)
  {
    this.layer = layer;
  }
  
  protected abstract void write(SLDWriter writer);
}
