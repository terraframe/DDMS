package dss.vector.solutions.ontology;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.query.OIterator;

import dss.vector.solutions.general.Disease;

public class ExportData
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

  public static List<ExportData> getRoots(BrowserField field)
  {
    List<ExportData> list = new ArrayList<ExportData>();
    OIterator<? extends BrowserRoot> roots = field.getAllroot();

    for (BrowserRoot root : roots)
    {
      String termId = root.getTerm().getTermId();
      Boolean selectable = root.getSelectable();
      Disease disease = root.getDisease();

      ExportData data = new ExportData(termId, selectable, disease);

      int index = list.indexOf(data);
      if (index == -1)
      {
        list.add(data);
      }
      else
      {
        ExportData _data = list.get(index);
        _data.setDisease(null);
      }
    }

    return list;
  }

}
