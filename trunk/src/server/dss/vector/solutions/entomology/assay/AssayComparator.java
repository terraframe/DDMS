package dss.vector.solutions.entomology.assay;

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeVirtual;
import com.runwaysdk.system.metadata.MdClass;

public class AssayComparator implements Comparator<MdAttributeVirtual>, Reloadable
{

  public int compare(MdAttributeVirtual assay1, MdAttributeVirtual assay2)
  {
    MdClass mdClass1 = assay1.getMdAttributeConcrete().getDefiningMdClass();
    MdClass mdClass2 = assay2.getMdAttributeConcrete().getDefiningMdClass();
    
    String name1 = mdClass1.getTypeName();
    String name2 = mdClass2.getTypeName();
    
    return name1.compareTo(name2);
  }

}
