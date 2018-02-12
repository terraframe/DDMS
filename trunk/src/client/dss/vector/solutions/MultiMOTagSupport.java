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
package dss.vector.solutions;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;

import com.runwaysdk.ClientSession;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.InputTagSupport;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.BrowserRootDTO;
import dss.vector.solutions.ontology.BrowserRootViewDTO;
import dss.vector.solutions.ontology.TermDTO;

@TagAnnotation(name = "multimo", bodyContent = "empty", description = "Multiple select MO input tag")
public class MultiMOTagSupport extends AbstractTermTagSupport implements Reloadable
{
  /**
   * Current value term
   */
  private List<TermDTO> value;

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Current value for the MO input")
  public List<TermDTO> getValue()
  {
    return value;
  }

  public void setValue(List<TermDTO> value)
  {
    this.value = value;
  }
  
  @SuppressWarnings("unchecked")
  public void setValue(Object object)
  {
    if(object instanceof List<?>)
    {
      this.value = (List<TermDTO>) object;
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _id = ( this.getId() != null ) ? this.getId() : this.getParam();
    String _browserClass = this.getBrowserClass(parent);
    String _browserAttribute = ( this.getBrowserAttribute() != null ) ? this.getBrowserAttribute() : this.getParam();
    Boolean _script = ( this.getScript() != null ) ? this.getScript() : new Boolean(true);
    Boolean _enabled = this.getEnabled(parent, _browserClass, _browserAttribute);
    List<TermDTO> _value = (List<TermDTO>) this.getValue(parent);

    // <mjl:input id="collectionMethodDisplay" param="#_collectionMethodDisplay"
    // type="text"/>
    InputTagSupport displayInput = new InputTagSupport();
    displayInput.setJspBody(this.getJspBody());
    displayInput.setJspContext(this.getJspContext());
    displayInput.setId(_id);
    displayInput.setType("text");
    displayInput.setParam("#_" + _id);
    
    if (!_enabled)
    {
      displayInput.setDisabled("disabled");
    }

    displayInput.doTag();
    
    this.writeClickableSpan(out, _id, _enabled);

    // Generate the div for listing the selected items
    out.write("<div id=\"" + _id + "Results\">\n");
    out.write("<ul id=\"" + _id + "ResultList\">\n");

    out.write("</ul>\n");
    out.write("</div>\n");

    if (_script)
    {
      ClientSession clientSession = (ClientSession) this.getJspContext().findAttribute(ClientConstants.CLIENTSESSION);
      ClientRequestIF request = clientSession.getRequest();
      BrowserRootViewDTO[] roots = BrowserRootDTO.getAttributeRoots(request, _browserClass, _browserAttribute);

      out.write("<script type=\"text/javascript\">\n");
      out.write("(function(){\n");
      out.write("  YAHOO.util.Event.onDOMReady(function(){\n");
      out.write("    var browser = new MDSS.GenericMultiOntologyBrowser('" + _browserClass + "', {attributeName:'" + _id + "', browserField:'" + _browserAttribute + "', multipleSelect:true, enabled:" + _enabled + "});\n");
      
      for (BrowserRootViewDTO root : roots)
      {
        out.write("    browser.addRoot(['" + root.getTermId() + "','" + root.getSelectable() + "']);\n");
      }
      
      if (_value != null)
      {
        for (TermDTO term : _value)
        {
          String label = term.getDisplayLabel();
          
          out.write("    browser.addSelection('" + label + "', '" + term.getId() + "');\n");
        }
      }
           
      out.write("})\n");
      out.write("})();\n");
      out.write("</script>\n");
    }
  }
}
