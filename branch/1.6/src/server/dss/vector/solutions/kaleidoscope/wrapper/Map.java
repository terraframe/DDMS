package dss.vector.solutions.kaleidoscope.wrapper;

import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public interface Map extends Component, Reloadable
{

  public List<? extends Layer> getLayers();
  
}
