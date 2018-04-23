package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ontology.Term;

public class ODKTermAttribute extends ODKAttribute
{
  public static final int  LIMIT = 100;

  private MdAttributeDAOIF mdAttribute;

  public ODKTermAttribute(MdAttributeDAOIF mdAttribute)
  {
    this(mdAttribute, 0);
  }

  public ODKTermAttribute(MdAttributeDAOIF mdAttribute, int index)
  {
    super(mdAttribute.definesAttribute(), mdAttribute.getDisplayLabel(Session.getCurrentLocale()), mdAttribute.getDescription(Session.getCurrentLocale()), index, mdAttribute.isRequired());

    this.mdAttribute = mdAttribute;
  }

  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);

    Term[] terms = Term.getRootChildren(this.mdAttribute);

    if (terms.length > LIMIT)
    {
      // Throw an exception
      throw new ProgrammingErrorException("Size limit");
    }
    else
    {
      for (Term term : terms)
      {
        Element text = document.createElement("text");

        text.setAttribute("id", "'" +  term.getTermId() + "'");

        Element value = document.createElement("value");
        value.setTextContent(term.getTermDisplayLabel().getValue());
        text.appendChild(value);

        parent.appendChild(text);
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

    Term[] terms = Term.getRootChildren(this.mdAttribute);

    if (terms.length > 100)
    {
      // Throw an exception
      throw new ProgrammingErrorException("Size limit");
    }
    else
    {
      for (Term term : terms)
      {
        Element item = document.createElement("item");
        select1.appendChild(item);

        Element itemLabel = document.createElement("label");
        itemLabel.setAttribute("ref", "jr:itext(''" + term.getTermId() + "'')");
        item.appendChild(itemLabel);

        Element itemValue = document.createElement("value");
        itemValue.appendChild(document.createTextNode(term.getTermId()));
        item.appendChild(itemValue);
      }
    }
  }

  @Override
  public String getODKType()
  {
    return "select1";
  }
}
