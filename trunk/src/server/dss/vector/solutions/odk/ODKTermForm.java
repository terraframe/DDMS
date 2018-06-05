package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class ODKTermForm extends ODKForm implements Reloadable
{
  private Term[]           terms;

  private MdAttributeDAOIF mdAttribute;

  private boolean          locked;

  public ODKTermForm(String viewMdType, MdAttributeDAOIF mdAttribute, Term[] terms)
  {
    super(viewMdType);

    this.mdAttribute = mdAttribute;
    this.terms = terms;
    this.locked = false;
  }

  @Override
  public void writeBind(Element parent, Document document, String context, int maxDepth)
  {
    for (Term term : this.terms)
    {
      super.writeBind(parent, document, context + "/" + ODKTermAttribute.sanitizeTermId(term.getTermId()), maxDepth);
    }
  }

  @Override
  public void writeBody(Element parent, Document document, String context, int maxDepth)
  {
    for (Term term : this.terms)
    {
      String groupId = ODKTermAttribute.sanitizeTermId(term.getTermId());

      Element group = document.createElement("group");
      parent.appendChild(group);
      group.setAttribute("ref", "/" + context + "/" + groupId);

      Element label = document.createElement("label");
      group.appendChild(label);
      label.setTextContent(term.getTermDisplayLabel().getValue());

      super.writeBody(group, document, context + "/" + groupId, maxDepth);
    }
  }

  @Override
  public void writeInstance(Element parent, Document document, String context, int maxDepth)
  {

    for (Term term : this.terms)
    {
      String groupId = ODKTermAttribute.sanitizeTermId(term.getTermId());

      Element group = document.createElement(groupId);
      group.setAttribute("id", groupId);
      parent.appendChild(group);

      super.writeInstance(group, document, groupId, maxDepth);
    }
  }

  @Override
  public void writeTranslation(Element parent, Document document, String context, int maxDepth)
  {
    for (Term term : this.terms)
    {
      super.writeTranslation(parent, document, context + "/" + ODKTermAttribute.sanitizeTermId(term.getTermId()), maxDepth);
    }
  }

  public MdAttributeDAOIF getMdAttribute()
  {
    return mdAttribute;
  }

  public synchronized boolean isLocked()
  {
    return this.locked;
  }

  public synchronized void lock()
  {
    this.locked = true;
  }

  public void unlock()
  {
    this.locked = false;
  }
}
