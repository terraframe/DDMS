package dss.vector.solutions.ontology;

public class FieldRoot extends FieldRootBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713186L;

  public FieldRoot(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public FieldRoot(dss.vector.solutions.ontology.BrowserField parent, dss.vector.solutions.ontology.BrowserRoot child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    if (this.getParentId() != null && this.getChildId() != null)
    {
      BrowserRoot child = BrowserRoot.get(this.getChildId());

      return child.getKey();
    }

    return super.buildKey();
  }

}
