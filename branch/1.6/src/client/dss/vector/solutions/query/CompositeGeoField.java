package dss.vector.solutions.query;

import java.util.Arrays;
import java.util.TreeSet;

import com.runwaysdk.generation.loader.Reloadable;

public class CompositeGeoField extends SimpleGeoField implements Reloadable, GeoFieldIF
{
  private TreeSet<String> filterSet;

  public CompositeGeoField()
  {
    super();

    this.filterSet = new TreeSet<String>();
  }

  public void addField(GeoFieldIF field)
  {
    String filterType = field.getFilterType();

    if (filterType != null && filterType.length() > 0)
    {
      this.setFilterType(filterType);
    }

    if (field.getIsSprayHierarchy())
    {
      this.setSprayHierarchy(true);
    }

    if (field.getIsPopulationHierarchy())
    {
      this.setPopulationHierarchy(true);
    }

    if (field.getIsPoliticalHierarchy())
    {
      this.setPoliticalHierarchy(true);
    }

    if (field.getIsUrbanHierarchy())
    {
      this.setUrbanHierarchy(true);
    }

    String[] extra = field.getExtraUniversals();

    this.addExtraUniversals(Arrays.asList(extra));
  }

  @Override
  public void setFilterType(String filter)
  {
    this.filterSet.add(filter);
  }

  @Override
  public String getFilterType()
  {
    StringBuffer buffer = new StringBuffer();

    for (String filter : this.filterSet)
    {
      buffer.append("," + "'" + filter + "'");
    }

    String filterType = buffer.toString().replaceFirst(",", "");

    return "[" + filterType + "]";
  }
}
