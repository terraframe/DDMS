package dss.vector.solutions.generator;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;

public class TermComparator implements Comparator<TermDTO>, Reloadable
{

  @Override
  public int compare(TermDTO t1, TermDTO t2)
  {
    return t1.getTermDisplayLabel().getValue().compareTo(t2.getTermDisplayLabel().getValue());
  }

}
