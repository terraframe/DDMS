package dss.vector.solutions.kaleidoscope.wrapper;

public interface MapVisitor
{
  public void visit(Map component);

  public void visit(ThematicLayer component);

  public void visit(ReferenceLayer component);

  public void visit(Style style);

  public void visit(ThematicStyle component);
}
