package dss.vector.solutions.manager;

public class LabeledBean
{
  private String label;

  private String value;

  public LabeledBean(String label, String value)
  {
    this.label = label;
    this.value = value;
  }

  public String getLabel()
  {
    return label;
  }

  public String getValue()
  {
    return value;
  }
}
