package dss.vector.solutions.util;

import java.util.LinkedList;
import java.util.List;

public class Restriction
{
  private String       attributeName;

  private List<String> restrictions;

  private Boolean      aggregate;

  public Restriction(String attributeName)
  {
    this.attributeName = attributeName;
    this.restrictions = new LinkedList<String>();
    this.aggregate = false;
  }

  public String getAttributeName()
  {
    return attributeName;
  }

  public List<String> getRestrictions()
  {
    return restrictions;
  }

  public Boolean getAggregate()
  {
    return aggregate;
  }

  public void setAggregate(Boolean aggregate)
  {
    this.aggregate = aggregate;
  }
}
