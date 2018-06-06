package dss.vector.solutions.odk;

import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.BusinessDAOIF;
import com.runwaysdk.dataaccess.EnumerationItemDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdEnumerationDAOIF;
import com.runwaysdk.dataaccess.attributes.entity.AttributeLocal;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class ODKEnumAttribute extends ODKMetadataAttribute implements Reloadable
{
  private MdAttributeEnumerationDAOIF mdAttribute;

  private Set<String>                 exported;

  public ODKEnumAttribute(ODKForm containingForm, MdAttributeDAOIF mdAttribute, MdAttributeDAOIF viewAttr, Set<String> exported)
  {
    super(containingForm, mdAttribute, viewAttr);

    this.mdAttribute = (MdAttributeEnumerationDAOIF) mdAttribute.getMdAttributeConcrete();
    this.exported = exported;
  }

  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);

    List<BusinessDAOIF> items = this.getItems();

    for (BusinessDAOIF item : items)
    {
      EnumerationItemDAOIF i = (EnumerationItemDAOIF) item;

      String termId = i.getName();
      String label = ( (AttributeLocal) i.getAttributeIF("displayLabel") ).getValue(Session.getCurrentLocale());
      String id = i.getId();

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

  private List<BusinessDAOIF> getItems()
  {
    MdEnumerationDAOIF mdEnum = this.mdAttribute.getMdEnumerationDAO();

    return mdEnum.getAllEnumItems();
  }

  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element eSelect1 = document.createElement(this.getODKType());
    parent.appendChild(eSelect1);
    eSelect1.setAttribute("appearance", "search");
    eSelect1.setAttribute("ref", "/" + title + "/" + attributeName);

    Element eLabel = document.createElement("label");
    eSelect1.appendChild(eLabel);
    eLabel.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");

    List<BusinessDAOIF> items = this.getItems();

    for (BusinessDAOIF item : items)
    {
      EnumerationItemDAOIF i = (EnumerationItemDAOIF) item;
      String termId = i.getName();

      Element eItem = document.createElement("item");
      eSelect1.appendChild(eItem);

      Element eItemLabel = document.createElement("label");
      eItemLabel.setAttribute("ref", "jr:itext(''" + termId + "'')");
      eItem.appendChild(eItemLabel);

      Element eItemValue = document.createElement("value");
      eItemValue.appendChild(document.createTextNode(termId));
      eItem.appendChild(eItemValue);
    }
  }

  @Override
  public String getODKType()
  {
    if (this.mdAttribute.selectMultiple())
    {
      return "select";
    }

    return "select1";
  }
}
