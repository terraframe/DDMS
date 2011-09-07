package dss.vector.solutions.generator;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebField;

public class FieldComparator implements Reloadable, Comparator<MdWebField>
{
  @Override
  public int compare(MdWebField field1, MdWebField field2)
  {
    Integer order1 = field1.getFieldOrder();
    Integer order2 = field2.getFieldOrder();

    return order1.compareTo(order2);
  }
}