package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;

public interface InfectionAssayIF extends Reloadable
{
  public TermDTO getSex();
  
  public Boolean getInfected(); 
}
