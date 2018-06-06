package dss.vector.solutions.odk;

import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.AggregatedAgeGroup;

public class ODKAgeGroupAttribute extends ODKMetadataAttribute implements Reloadable
{
  private Set<String> exported;

  public ODKAgeGroupAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, Set<String> exported)
  {
    super(containingForm, sourceMdAttr, viewMdAttr);

    this.exported = exported;
  }

  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);

    AggregatedAgeGroup[] ageGroups = AggregatedAgeGroup.getAll();

    for (AggregatedAgeGroup ageGroup : ageGroups)
    {
      String termId = ageGroup.getKeyName();
      String label = ageGroup.getDisplayLabel();
      String id = ageGroup.getId();

      if (!this.exported.contains(id))
      {
        Element text = document.createElement("text");

        text.setAttribute("id", "'" + termId + "'");

        Element value = document.createElement("value");
        value.setTextContent(label);
        text.appendChild(value);

        parent.appendChild(text);

        this.exported.add(id);
      }
    }
  }

  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element select1 = document.createElement("select1");
    parent.appendChild(select1);
    select1.setAttribute("appearance", "search");
    select1.setAttribute("ref", "/" + title + "/" + attributeName);

    Element label = document.createElement("label");
    select1.appendChild(label);
    label.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");

    AggregatedAgeGroup[] ageGroups = AggregatedAgeGroup.getAll();

    for (AggregatedAgeGroup ageGroup : ageGroups)
    {
      String termId = ageGroup.getKeyName();
      String value = ageGroup.getDisplayLabel();

      Element item = document.createElement("item");
      select1.appendChild(item);

      Element itemLabel = document.createElement("label");
      itemLabel.setAttribute("ref", "jr:itext(''" + termId + "'')");
      item.appendChild(itemLabel);

      Element itemValue = document.createElement("value");
      itemValue.appendChild(document.createTextNode(value));
      item.appendChild(itemValue);
    }
  }

  @Override
  public String getODKType()
  {
    return "select1";
  }
}
