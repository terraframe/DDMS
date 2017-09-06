package dss.vector.solutions.kaleidoscope.wrapper;

import com.runwaysdk.generation.loader.Reloadable;

public interface MapVisitor extends Reloadable
{
  public void visit(Map component);

  public void visit(ThematicLayer component);

  public void visit(ReferenceLayer component);

  public void visit(Style style);

  public void visit(ThematicStyle component);
}
