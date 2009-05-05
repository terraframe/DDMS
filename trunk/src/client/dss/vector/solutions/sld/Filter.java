package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

public abstract class Filter implements Reloadable
{
  protected abstract void write(SLDWriter writer);
}
