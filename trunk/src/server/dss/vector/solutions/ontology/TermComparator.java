package dss.vector.solutions.ontology;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

public class TermComparator implements Comparator<Term>, Reloadable
{
  public int compare(Term arg0, Term arg1)
  {
    return arg0.getId().compareTo(arg1.getId());
  }

}
