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

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.runwaysdk.ClientSession;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.BrowserRootDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.LocalizationFacadeDTO;

public abstract class AbstractTermTagSupport extends SimpleTagSupport implements Reloadable
{
  /**
   * Name of the controller parameter or attribute being inputed
   */
  private String  param;

  /**
   * Optional name of the browser field class
   */
  private String  browserClass;

  /**
   * Optional name of the browser field attribute
   */
  private String  browserAttribute;

  /**
   * Class attribute
   */
  private String  classes;

  /**
   * Id attribute
   */
  private String  id;

  /**
   * Flag indicating if javascript should be generated for this MO field
   */
  private Boolean script;

  /**
   * Flag indicating if this Term tag is enabled
   */
  private Boolean enabled;

  public AbstractTermTagSupport()
  {
    this.script = true;
  }

  @AttributeAnnotation(required = true, description = "The name of the controller parameter or attribute")
  public String getParam()
  {
    return param;
  }

  public void setParam(String param)
  {
    this.param = param;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "The browser field attribute of this MO Tag")
  public String getBrowserAttribute()
  {
    return browserAttribute;
  }

  public void setBrowserAttribute(String browserAttribute)
  {
    this.browserAttribute = browserAttribute;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "The browser field class of this MO Tag")
  public String getBrowserClass()
  {
    return browserClass;
  }

  public void setBrowserClass(String browserClass)
  {
    this.browserClass = browserClass;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Classes of the MO input")
  public String getClasses()
  {
    return classes;
  }

  public void setClasses(String classes)
  {
    this.classes = classes;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Id of the MO input")
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Flag indicating if javascript should be created for this MO select field")
  public Boolean getScript()
  {
    return script;
  }

  public void setScript(Boolean script)
  {
    this.script = script;
  }

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Flag indicating if Term select field is disabled")
  public Boolean getEnabled()
  {
    return enabled;
  }

  public void setEnabled(Boolean enabled)
  {
    this.enabled = enabled;
  }

  protected Object getValue(JspTag parent)
  {
    Object _value = this.getValue();

    if (_value == null && parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;
      MutableDTO item = component.getItem();

      if (item != null)
      {
        DTOFacade facade = new DTOFacade(this.getParam(), item);

        try
        {
          Object current = facade.getValue();
          if (current != null && current instanceof TermDTO)
          {
            _value = current;
          }

        }
        catch (Exception e)
        {
          // Do nothing the parameter does not exist on the MutableDTO so you
          // cannot retrieve it's current value.
        }
      }
    }
    return _value;
  }

  protected String getBrowserClass(JspTag parent)
  {
    String _browserClass = this.getBrowserClass();

    if (_browserClass == null && parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;
      MutableDTO item = component.getItem();

      if (item != null)
      {
        _browserClass = item.getType();
      }
    }

    return _browserClass;
  }

  protected Boolean getEnabled(JspTag parent, String _browserClass, String _browserAttribute)
  {
    Boolean _enabled = this.getEnabled();

    if (_enabled == null)
    {
      ClientSession session = (ClientSession) this.getJspContext().getAttribute(ClientConstants.CLIENTSESSION, PageContext.SESSION_SCOPE);
      ClientRequestIF request = session.getRequest();

      if (this.getScript())
      {
        _enabled = BrowserRootDTO.hasBrowserRoot(request, _browserClass, _browserAttribute);
      }
      else if (parent != null)
      {
        ComponentMarkerIF component = (ComponentMarkerIF) parent;
        MutableDTO item = component.getItem();

        if (item != null && item instanceof GeoEntityDTO)
        {
          _enabled = GeoHierarchyDTO.hasBrowserRoot(request, _browserClass);
        }
        else
        {
          _enabled = true;
        }
      }
      else
      {
        _enabled = true;
      }
    }

    return _enabled;
  }
  
  protected void writeClickableSpan(JspWriter out, String _id, Boolean _enabled) throws IOException
  {
    ClientSession session = (ClientSession) this.getJspContext().getAttribute(ClientConstants.CLIENTSESSION, PageContext.SESSION_SCOPE);
    ClientRequestIF request = session.getRequest();

    String title = LocalizationFacadeDTO.getFromBundles(request, "Browser");
    String iconClass = ( _enabled ? " class=\"ontologyOpener\"" : "" );
    String iconSpanClass = ( _enabled ? " class=\"clickable ontology-clickable\"" : "" );

    out.write("<span id=\"" + _id + "Btn\"" + iconSpanClass + ">\n");
    out.write("<img alt=\"" + title + "\" title=\"" + title + "\" src=\"./imgs/icons/term.png\"" + iconClass + ">\n");
    out.write("</span>\n");
  }

  public abstract Object getValue();
}
