package dss.vector.solutions.surveillance;

import com.terraframe.mojo.generation.loader.Reloadable;

public interface CaseGrid extends Reloadable
{
  public AbstractGrid getChild();
}
