package dss.vector.solutions.odk;

import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.mobile.MobileUtil;

public class ODKAttributeInsecticide extends ODKMetadataAttribute implements Reloadable
{
  private Set<String> exported;
  
  private MdAttributeDAOIF insecticideMdAttr;
  
  private MdAttributeDAOIF unitsMdAttr;
  
  private MdAttributeDAOIF amountMdAttr;

  public ODKAttributeInsecticide(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, Set<String> exported, MdAttributeDAOIF insecticideMdAttr, MdAttributeDAOIF unitsMdAttr, MdAttributeDAOIF amountMdAttr)
  {
    super(containingForm, sourceMdAttr, viewMdAttr);

    this.exported = exported;
    this.insecticideMdAttr = insecticideMdAttr;
    this.unitsMdAttr = unitsMdAttr;
    this.amountMdAttr = amountMdAttr;
  }
  
  public MdAttributeDAOIF getInsecticideMdAttr()
  {
    return insecticideMdAttr;
  }

  public MdAttributeDAOIF getUnitsMdAttr()
  {
    return unitsMdAttr;
  }

  public MdAttributeDAOIF getAmountMdAttr()
  {
    return amountMdAttr;
  }

  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);

    List<? extends Insecticide> insecticides = Insecticide.getAllInstances("keyName", true, 0, 0).getIterator().getAll();

    for (Insecticide insecticide : insecticides)
    {
      String termId = insecticide.getKeyName();
      String label = MobileUtil.sanitizeLabel(insecticide.getActiveIngredient().getTermDisplayLabel().getValue() + " - " + insecticide.getAmount() + " " + insecticide.getUnits().getTermDisplayLabel().getValue());
      String id = insecticide.getId();

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

    List<? extends Insecticide> insecticides = Insecticide.getAllInstances("keyName", true, 0, 0).getIterator().getAll();

    for (Insecticide insecticide : insecticides)
    {
      String termId = insecticide.getKeyName();
      String value = insecticide.getKey();

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
