package dss.vector.solutions.query;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdFieldDTO;

public class FieldComparator implements Comparator<MdFieldDTO>, Reloadable
{

  @Override
  public int compare(MdFieldDTO field1, MdFieldDTO field2)
  {
    return field1.getFieldOrder().compareTo(field2.getFieldOrder());
  }

}
