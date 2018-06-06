package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.session.Session;

/**
 * At the time of writing this, Booleans were claimed to be supported in ODK, however we found
 * that when the form was submitted to aggregate, the value was always "false" regardless of what
 * options were selected in ODK Collect. Thus we decided to implement booleans as select lists.
 * 
 * @author rrowlands
 */
public class ODKAttributeBoolean extends ODKMetadataAttribute
{
  public ODKAttributeBoolean(ODKForm containingForm, MdAttributeBooleanDAOIF source, MdAttributeDAOIF viewAttr)
  {
    super(containingForm, source, viewAttr);
  }
  
  public ODKAttributeBoolean(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type, String attributeName, String displayLabel, String description, boolean required)
  {
    super(containingForm, sourceMdAttr, viewMdAttr, type, attributeName, displayLabel, description, required);
  }
  
  @Override
  public MdAttributeBooleanDAOIF getSourceMdAttribute()
  {
    return (MdAttributeBooleanDAOIF) this.sourceMdAttr;
  }

  @Override
  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    super.writeBind(parent, document, title, maxDepth);
  }
  
  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element select1 = document.createElement("select1");
    parent.appendChild(select1);
    select1.setAttribute("ref", "/" + title + "/" + attributeName);
    
    Element label = document.createElement("label");
    select1.appendChild(label);
    label.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");
    
    Element optTrue = document.createElement("item");
    select1.appendChild(optTrue);
    Element optTrueValue = document.createElement("value");
    optTrue.appendChild(optTrueValue);
    optTrueValue.setTextContent("true");
    Element optTrueLabel = document.createElement("label");
    optTrue.appendChild(optTrueLabel);
    optTrueLabel.setTextContent(this.getSourceMdAttribute().getPositiveDisplayLabel(Session.getCurrentLocale()));
    
    Element optFalse = document.createElement("item");
    select1.appendChild(optFalse);
    Element optFalseValue = document.createElement("value");
    optFalse.appendChild(optFalseValue);
    optFalseValue.setTextContent("false");
    Element optFalseLabel = document.createElement("label");
    optFalse.appendChild(optFalseLabel);
    optFalseLabel.setTextContent(this.getSourceMdAttribute().getNegativeDisplayLabel(Session.getCurrentLocale()));
  }
  
  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);
  }
  
  public String getODKType()
  {
    return "select1";
  }
}
