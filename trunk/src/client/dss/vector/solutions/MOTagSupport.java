package dss.vector.solutions;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.terraframe.mojo.business.MutableDTO;
import com.terraframe.mojo.controller.DTOFacade;
import com.terraframe.mojo.controller.tag.ComponentMarkerIF;
import com.terraframe.mojo.controller.tag.develop.AttributeAnnotation;
import com.terraframe.mojo.controller.tag.develop.TLDGenerator;
import com.terraframe.mojo.controller.tag.develop.TagAnnotation;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;

@TagAnnotation(name = "mo", bodyContent = "empty", description = "MO input tag")
public class MOTagSupport extends SimpleTagSupport implements Reloadable
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
   * Current value term
   */
  private TermDTO value;

  /**
   * Flag indicating if javascript should be generated for this MO field
   */
  private Boolean script;

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

  @AttributeAnnotation(required = false, rtexprvalue = true, description = "Current value for the MO input")
  public TermDTO getValue()
  {
    return value;
  }

  public void setValue(TermDTO value)
  {
    this.value = value;
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

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _param = this.getParam();
    TermDTO _value = this.getValue();

    String _Id = ( this.getId() != null ) ? this.getId() : this.getParam();
    String _browserClass = this.getBrowserClass();
    String _browserAttribute = ( this.getBrowserAttribute() != null ) ? this.getBrowserAttribute() : this.getParam();
    Boolean _script = ( this.getScript() != null ) ? this.getScript() : new Boolean(true);

    // If the input tag is in the context of a component then
    // load update the parameter name and display value
    if (parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;
      MutableDTO item = component.getItem();

      _param = component.getParam() + "." + this.getParam();

      if (_value == null && item != null)
      {
        DTOFacade facade = new DTOFacade(this.getParam(), item);
        try
        {
          Object current = facade.getValue();
          if (current != null && current instanceof TermDTO)
          {
            _value = (TermDTO) current;
          }

        }
        catch (Exception e)
        {
          // Do nothing the parameter does not exist on the MutableDTO so you
          // cannot retrieve it's current value.
        }
      }

      if (_browserClass == null)
      {
        _browserClass = item.getType();
      }
    }

    out.write("<div class=\"ontologyDisplay\" id=\"" + _Id + "Display\">");
    if (_value != null)
    {
      out.write(_value.getDisplayLabel());
    }
    else
    {
      out.write("No Value");
    }
    out.write("</div>\n");

    out.write("<input type=\"hidden\" name=\"" + _param + "\" id=\"" + _Id + "\"");
    if (_value != null)
    {
      out.write(" value=\"" + _value.getId() + "\"");
    }
    out.write(" />\n");

    if (_script)
    {
      out.write("<script type=\"text/javascript\">\n");
      out.write("(function(){\n");
      out.write("YAHOO.util.Event.onDOMReady(function(){\n");
      out.write("new MDSS.GenericOntologyBrowser('" + _browserClass + "', [{attributeName:'" + _browserAttribute + "'}]);\n");
      out.write("})\n");
      out.write("})();\n");
      out.write("</script>\n");
    }
  }

  public static void main(String[] args)
  {
    try
    {
      // Generate mojo form tags
      new TLDGenerator(new File(args[0]), new Class<?>[] { MOTagSupport.class, MultiMOTagSupport.class }, "MDSS lib").generate();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
