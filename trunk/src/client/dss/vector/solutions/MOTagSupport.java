package dss.vector.solutions;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;

import com.runwaysdk.ClientSession;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.InputTagSupport;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TLDGenerator;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.localization.LocalizedTagSupport;
import dss.vector.solutions.ontology.BrowserRootDTO;
import dss.vector.solutions.ontology.BrowserRootViewDTO;
import dss.vector.solutions.ontology.TermComponentIF;
import dss.vector.solutions.ontology.TermDTO;

@TagAnnotation(name = "mo", bodyContent = "empty", description = "MO input tag")
public class MOTagSupport extends AbstractTermTagSupport implements Reloadable
{
  /**
   * Current value term
   */
  private TermComponentIF value;

  private String          listener;

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Current value for the MO input")
  public TermComponentIF getValue()
  {
    return value;
  }

  public void setValue(Object object)
  {
    if (object instanceof TermComponentIF)
    {
      this.value = (TermComponentIF) object;
    }
  }

  public void setValue(TermComponentIF value)
  {
    this.value = value;
  }

  public void setValue(TermDTO value)
  {
    this.value = value;
  }

  @AttributeAnnotation(rtexprvalue = false, required = false, description = "Java-script selection listener function")
  public String getListener()
  {
    return listener;
  }

  public void setListener(String listener)
  {
    this.listener = listener;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _param = this.getParam(parent);
    String _id = ( this.getId() != null ) ? this.getId() : this.getParam();
    String _browserClass = this.getBrowserClass(parent);
    String _browserAttribute = ( this.getBrowserAttribute() != null ) ? this.getBrowserAttribute() : this.getParam();
    Boolean _script = ( this.getScript() != null ) ? this.getScript() : new Boolean(true);
    Boolean _enabled = this.getEnabled(parent, _browserClass, _browserAttribute);
    TermComponentIF _value = (TermComponentIF) this.getValue(parent);

    // <mjl:input id="collectionMethod" param="collectionMethod.componentId"
    // type="hidden"/>
    InputTagSupport attributeInput = new InputTagSupport();
    attributeInput.setJspBody(this.getJspBody());
    attributeInput.setJspContext(this.getJspContext());
    attributeInput.setId(_id);
    attributeInput.setType("hidden");
    attributeInput.setParam(_param);
    attributeInput.setClasses(this.getClasses());

    if (_value != null)
    {
      // attributeInput.setValue(_value.getId());
      attributeInput.setValue(_value.getTermComponentId());
    }

    attributeInput.doTag();

    // <mjl:input id="collectionMethodDisplay" param="#_collectionMethodDisplay"
    // type="text"/>
    InputTagSupport displayInput = new InputTagSupport();
    displayInput.setJspBody(this.getJspBody());
    displayInput.setJspContext(this.getJspContext());
    displayInput.setId(_id + "Display");
    displayInput.setType("text");
    displayInput.setParam("#_" + _id);

    if (!_enabled)
    {
      displayInput.setDisabled("disabled");
    }
    if (_value != null)
    {
      displayInput.setValue(_value.getTermComponentDisplay());
    }

    displayInput.doTag();

    // <span id="collectionMethodBtn" class="clickable">
    // <img alt="Browser" src="./imgs/icons/tree.png" class="ontologyOpener">
    // </span>

    writeClickableSpan(out, _id, _enabled);

    if (_script)
    {
      ClientSession clientSession = (ClientSession) this.getJspContext().findAttribute(ClientConstants.CLIENTSESSION);
      ClientRequestIF request = clientSession.getRequest();
      BrowserRootViewDTO[] roots = BrowserRootDTO.getAttributeRoots(request, _browserClass, _browserAttribute);

      out.write("<script type=\"text/javascript\">\n");
      out.write("(function(){\n");
      out.write("  YAHOO.util.Event.onDOMReady(function(){\n");
      out.write("    var browser = new MDSS.GenericOntologyBrowser('" + _browserClass + "', [{attributeName:'" + _browserAttribute + "', enabled:" + _enabled + "}]);\n");

      for (BrowserRootViewDTO root : roots)
      {
        out.write("    browser.addRoot(['" + root.getTermId() + "','" + root.getSelectable() + "']);\n");
      }
      
      if(this.getListener() != null)
      {
        out.write("    browser.addTermSelectedListener(" + this.getListener() + ");\n");        
      }

      out.write("  })\n");
      out.write("})();\n");
      out.write("</script>\n");
    }
  }

  private String getParam(JspTag parent)
  {
    String _param = this.getParam();

    if (parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;

      _param = component.getParam() + "." + this.getParam();
    }

    return _param;
  }

  public static void main(String[] args)
  {
    try
    {
      // Generate mojo form tags
      new TLDGenerator(new File(args[0]), new Class<?>[] { MOTagSupport.class, MultiMOTagSupport.class, BooleanTagSupport.class, BooleanListTagSupport.class, BooleanCheckTagSupport.class, GeoTagSupport.class, FilterTagSupport.class, LocalizedTagSupport.class }, "MDSS").generate();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
