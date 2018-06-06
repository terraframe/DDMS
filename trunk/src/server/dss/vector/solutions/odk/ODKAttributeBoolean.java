package dss.vector.solutions.odk;

import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.session.Session;

import dss.vector.solutions.util.LocalizationFacade;

/**
 * At the time of writing this, Booleans were claimed to be supported in ODK, however we found
 * that when the form was submitted to aggregate, the value was always "false" regardless of what
 * options were selected in ODK Collect. Thus we decided to implement booleans as select lists.
 * 
 * @author rrowlands
 */
public class ODKAttributeBoolean extends ODKAttribute
{
  public static final String BOOLEAN_OPTIONS_ID = "ddms_boolean_options";
  
  public static final String BOOLEAN_OPTIONS_PATH = "instance('" + BOOLEAN_OPTIONS_ID + "')/root/item";
  
  public ODKAttributeBoolean(ODKForm containingForm, String attributeName, String displayLabel, String description, boolean required)
  {
    super(containingForm, "select1", attributeName, displayLabel, description, required);
  }

  public ODKAttributeBoolean(ODKForm containingForm, String attributeName, String displayLabel)
  {
    this(containingForm, attributeName, displayLabel, null, false);
  }

  public ODKAttributeBoolean(ODKForm containingForm, String attributeName, String displayLabel, boolean required)
  {
    this(containingForm, attributeName, displayLabel, null, required);
  }
  
  public ODKAttributeBoolean(ODKForm containingForm, MdAttributeDAOIF source, MdAttributeDAOIF viewAttr, Set<String> exportedTerms)
  {
    super(containingForm, viewAttr.definesAttribute(), viewAttr.getDisplayLabel(Session.getCurrentLocale()), viewAttr.getDescription(Session.getCurrentLocale()), viewAttr.isRequired());
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
//    select1.setAttribute("appearance", "search");
    select1.setAttribute("ref", "/" + title + "/" + attributeName);
    
    Element label = document.createElement("label");
    select1.appendChild(label);
    label.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");
    
    Element itemset = document.createElement("itemset");
    select1.appendChild(itemset);
    itemset.setAttribute("nodeset", BOOLEAN_OPTIONS_PATH);
    
    Element value = document.createElement("value");
    itemset.appendChild(value);
    value.setAttribute("ref", "value");
    
    Element itemsetLabel = document.createElement("label");
    itemset.appendChild(itemsetLabel);
    itemsetLabel.setAttribute("ref", "label");
  }
  
//  public static void writeBooleanOptionsTranslation(Element parent, Document document, String title, int maxDepth)
//  {
//    Element text = document.createElement("text");
//
//    text.setAttribute("id", "/" + BOOLEAN_OPTIONS_ID + "/optionTrue");
//
//    Element value = document.createElement("value");
//    value.setTextContent(LocalizationFacade.getFromBundles("odk_boolean_true"));
//    text.appendChild(value);
//
//    parent.appendChild(text);
//    
//    
//    
//    Element textFalse = document.createElement("text");
//
//    textFalse.setAttribute("id", "/" + BOOLEAN_OPTIONS_ID + "/optionFalse");
//
//    Element valueFalse = document.createElement("value");
//    valueFalse.setTextContent(LocalizationFacade.getFromBundles("odk_boolean_false"));
//    textFalse.appendChild(valueFalse);
//
//    parent.appendChild(textFalse);
//  }
  
  public static void writeBooleanOptionsInstance(Element model, Document document, String title, int maxDepth)
  {
    Element instBool = document.createElement("instance");
    model.appendChild(instBool);
    instBool.setAttribute("id", BOOLEAN_OPTIONS_ID);
    
    Element root = document.createElement("root");
    instBool.appendChild(root);
    
    Element optTrue = document.createElement("item");
    root.appendChild(optTrue);
    Element optTrueValue = document.createElement("value");
    optTrue.appendChild(optTrueValue);
    optTrueValue.setTextContent("true");
    Element optTrueLabel = document.createElement("label");
    optTrue.appendChild(optTrueLabel);
//    optTrueLabel.setAttribute("ref", "jr:itext('/" + BOOLEAN_OPTIONS_ID + "/optionTrue')");
    optTrueLabel.setTextContent(LocalizationFacade.getFromBundles("odk_boolean_true"));
    
    Element optFalse = document.createElement("item");
    root.appendChild(optFalse);
    Element optFalseValue = document.createElement("value");
    optFalse.appendChild(optFalseValue);
    optFalseValue.setTextContent("false");
    Element optFalseLabel = document.createElement("label");
    optFalse.appendChild(optFalseLabel);
//    optFalseLabel.setAttribute("ref", "jr:itext('/" + BOOLEAN_OPTIONS_ID + "/optionFalse')");
    optFalseLabel.setTextContent(LocalizationFacade.getFromBundles("odk_boolean_false"));
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
