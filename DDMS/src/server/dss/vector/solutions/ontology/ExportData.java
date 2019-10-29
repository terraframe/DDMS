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
package dss.vector.solutions.ontology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.query.OIterator;

import dss.vector.solutions.general.Disease;

public class ExportData implements Comparable<ExportData>
{
  private String  termId;

  private Boolean selectable;

  private Disease disease;

  public ExportData(String termId, Boolean selectable, Disease disease)
  {
    super();
    this.termId = termId;
    this.selectable = selectable;
    this.disease = disease;
  }

  public String getTermId()
  {
    return termId;
  }

  public boolean getSelectable()
  {
    return selectable.booleanValue();
  }

  public String getDisease()
  {
    if(disease == null)
    {
      return "";
    }
    
    return disease.getKey();
  }

  public void setDisease(Disease disease)
  {
    this.disease = disease;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof ExportData)
    {
      ExportData data = (ExportData) obj;

      return data.termId.equals(this.termId) && data.selectable.equals(this.selectable);
    }

    return false;
  }

  public int compareTo(ExportData o)
  {
    int compareTo = this.getTermId().compareTo(o.getTermId());
    if (compareTo==0)
    {
      compareTo = new Boolean(this.getSelectable()).compareTo(o.getSelectable());
    }
    return compareTo;
  }

  public static List<ExportData> getCommonRoots(BrowserField field)
  {
    List<ExportData> list = new ArrayList<ExportData>();
    OIterator<? extends BrowserRoot> roots = field.getAllroot();
    HashMap<String, List<ExportData>> map = new HashMap<String, List<ExportData>>();
    for (Disease d : Disease.getAllDiseases())
    {
      map.put(d.getKey(), new LinkedList<ExportData>());
    }
    
    Set<ExportData> allPossibleRoots = new TreeSet<ExportData>();
    
    for (BrowserRoot root : roots)
    {
      String termId = root.getTerm().getTermId();
      Boolean selectable = root.getSelectable();
      Disease disease = root.getDisease();
      
      ExportData data = new ExportData(termId, selectable, disease);
      
      allPossibleRoots.add(data);
      map.get(disease.getKey()).add(data);
    }
    
    for (ExportData possibleRoot : allPossibleRoots)
    {
      boolean common = true;
      for (List<ExportData> diseaseRoots : map.values())
      {
        // If at least one disease does NOT contain the root, it is not in common.
        if (!diseaseRoots.contains(possibleRoot))
        {
          common = false;
          break;
        }
      }
      if (common)
      {
        possibleRoot.setDisease(null);
        list.add(possibleRoot);
      }
    }
    
    return list;
  }
  
  public static List<ExportData> getRootsByDisease(BrowserField field, Disease desired, List<ExportData> commonRoots)
  {
    List<ExportData> list = new ArrayList<ExportData>();
    OIterator<? extends BrowserRoot> roots = field.getAllroot();

    for (BrowserRoot root : roots)
    {
      String termId = root.getTerm().getTermId();
      Boolean selectable = root.getSelectable();
      Disease disease = root.getDisease();

      ExportData data = new ExportData(termId, selectable, disease);
      if (commonRoots.contains(data))
      {
        continue;
      }
      
      if (disease.equals(desired))
      {
        list.add(data);
      }
//
//      int index = list.indexOf(data);
//      if (index == -1)
//      {
//        list.add(data);
//      }
//      else
//      {
//        ExportData _data = list.get(index);
//        _data.setDisease(null);
//      }
    }

    return list;
  }

}
