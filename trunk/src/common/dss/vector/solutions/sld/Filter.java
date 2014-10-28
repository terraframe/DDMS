package dss.vector.solutions.sld;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.LayerIF;

public abstract class Filter implements Reloadable
{
  protected LayerIF layer;
  
  protected Filter(LayerIF layer)
  {
    this.layer = layer;
  }
  
  protected abstract void write(SLDWriter writer);
}
