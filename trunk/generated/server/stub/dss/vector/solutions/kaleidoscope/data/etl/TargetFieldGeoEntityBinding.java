package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class TargetFieldGeoEntityBinding extends TargetFieldGeoEntityBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static class UniversalAttributeBindingComparator implements Comparator<UniversalAttributeBinding>, Reloadable
  {
    private Map<String, Integer> indices;

    public UniversalAttributeBindingComparator(Collection<GeoHierarchy> terms)
    {
      this.indices = new HashMap<String, Integer>();

      int index = 0;

      for (GeoHierarchy term : terms)
      {
        this.indices.put(term.getId(), index++);
      }
    }

    @Override
    public int compare(UniversalAttributeBinding o1, UniversalAttributeBinding o2)
    {
      Integer i1 = this.indices.get(o1.getUniversalId());
      Integer i2 = this.indices.get(o2.getUniversalId());

      return i1.compareTo(i2);
    }

  }

  private static final long serialVersionUID = -2005836550;

  public TargetFieldGeoEntityBinding()
  {
    super();
  }

  @Override
  @Transaction
  public void delete()
  {
    List<UniversalAttributeBinding> attributes = this.getUniversalAttributes();

    for (UniversalAttributeBinding attribute : attributes)
    {
      attribute.delete();
    }

    super.delete();
  }

  public List<UniversalAttributeBinding> getUniversalAttributes()
  {
    List<UniversalAttributeBinding> list = new LinkedList<UniversalAttributeBinding>();

    UniversalAttributeBindingQuery query = new UniversalAttributeBindingQuery(new QueryFactory());
    query.WHERE(query.getField().EQ(this));

    OIterator<? extends UniversalAttributeBinding> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        UniversalAttributeBinding binding = iterator.next();

        list.add(binding);
      }

      return list;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  protected void populate(TargetField field)
  {
    super.populate(field);

    GeoEntity root = this.getGeoEntity();

    GeoHierarchy hierarchy = GeoHierarchy.getGeoHierarchyFromType(root.getMdClass().definesType());

    List<GeoHierarchy> descendants = hierarchy.getAllChildren();

    TargetFieldGeoEntity tField = (TargetFieldGeoEntity) field;
    tField.setRoot(root);

    List<UniversalAttributeBinding> attributes = this.getUniversalAttributes();

    Collections.sort(attributes, new UniversalAttributeBindingComparator(descendants));

    for (UniversalAttributeBinding attribute : attributes)
    {
      MdAttribute sourceAttribute = attribute.getSourceAttribute();

      String attributeName = sourceAttribute.getAttributeName();
      String label = sourceAttribute.getDisplayLabel().getValue();
      GeoHierarchy universal = attribute.getUniversal();

      tField.addUniversalAttribute(attributeName, label, universal);
    }
  }

  @Override
  public TargetFieldIF getTargetField()
  {
    TargetFieldGeoEntity field = new TargetFieldGeoEntity();

    populate(field);

    return field;
  }
}
