/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
