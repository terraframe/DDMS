package dss.vector.solutions.ontology;

import com.terraframe.mojo.generation.loader.Reloadable;

public interface TermComponentIF extends Reloadable
{
  public String getTermComponentId();
  
  public String getTermComponentDisplay();
}
