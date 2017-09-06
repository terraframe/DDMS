package dss.vector.solutions.kaleidoscope.wrapper;

import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;


public interface Layer extends Component, Reloadable
{
  public String getName();
  
  public Boolean getDisplayInLegend();
  
  public List<? extends Style> getStyles();
  
  public Boolean getVirtual();
  
  public FeatureType getFeatureType();
  
  public FeatureStrategy getFeatureStrategy();
}
