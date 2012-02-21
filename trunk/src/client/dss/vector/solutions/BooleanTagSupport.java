package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;

import com.runwaysdk.business.ComponentDTOFacade;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.InputElementTagSupport;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.attributes.AttributeBooleanDTO;
import com.runwaysdk.transport.metadata.AttributeBooleanMdDTO;

@TagAnnotation(bodyContent = "empty", name = "boolean", description = "Tag denoting a boolean input")
public class BooleanTagSupport extends InputElementTagSupport  implements Reloadable
{
  /**
   * Name of the controller parameter or attribute being inputed
   */
  private String param;

  private String positiveLabel;

  private String negativeLabel;

  private String noneLabel;

  public BooleanTagSupport()
  {
    super();

    this.addAttribute("type", "radio");

  }

  public void setParam(String param)
  {
    this.param = param;
  }

  @AttributeAnnotation(required = true, description = "The name of the controller parameter or attribute")
  public String getParam()
  {
    return param;
  }

  @AttributeAnnotation(description = "The display label for the true option", rtexprvalue = true)
  public String getPositiveLabel()
  {
    return positiveLabel;
  }

  public void setPositiveLabel(String positiveLabel)
  {
    this.positiveLabel = positiveLabel;
  }

  @AttributeAnnotation(description = "The display label for the false option", rtexprvalue = true)
  public String getNegativeLabel()
  {
    return negativeLabel;
  }

  public void setNegativeLabel(String negativeLabel)
  {
    this.negativeLabel = negativeLabel;
  }

  @AttributeAnnotation(description = "The display label for the none option", rtexprvalue = true, required = true)
  public String getNoneLabel()
  {
    return noneLabel;
  }

  public void setNoneLabel(String noneLabel)
  {
    this.noneLabel = noneLabel;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String name = this.getParam();
    String value = this.getValue();
    boolean required = false;

    // If the input tag is in the context of a component then
    // load update the parameter name and display value
    if (parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;
      MutableDTO item = component.getItem();

      name = component.getParam() + "." + this.getParam();

      if (this.getValue() == null)
      {
        value = item.getValue(this.getParam());
      }

      // set label defaults from metadata if no label is found
      try
      {
        AttributeBooleanDTO attributeBoolean = ComponentDTOFacade.getAttributeBooleanDTO(item, this.getParam());
        AttributeBooleanMdDTO attributeMd = attributeBoolean.getAttributeMdDTO();

        if (this.getPositiveLabel() == null)
        {
          this.setPositiveLabel(attributeMd.getPositiveDisplayLabel());
        }

        if (this.getNegativeLabel() == null)
        {
          this.setNegativeLabel(attributeMd.getNegativeDisplayLabel());
        }

        required = attributeMd.isRequired();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }

    // set label defaults if no label is found
    if (this.getPositiveLabel() == null)
    {
      this.setPositiveLabel(MdAttributeBooleanInfo.TRUE);
    }

    if (this.getNegativeLabel() == null)
    {
      this.setNegativeLabel(MdAttributeBooleanInfo.FALSE);
    }

    this.addAttribute("name", name);

    this.writeOption(out, "true", this.getId(), "positive", this.getPositiveLabel(), ( value != null && value.equalsIgnoreCase("true") ));
    this.writeOption(out, "false", this.getId(), "negative", this.getNegativeLabel(), ( value != null && value.equalsIgnoreCase("false") ));

    if (!required)
    {
      this.writeOption(out, "", this.getId(), "none", this.getNoneLabel(), ( value == null || value.length() == 0 ));
    }

    this.addAttribute("id", this.getId());
  }

  private void writeOption(JspWriter out, String value, String id, String postfix, String label, boolean checked) throws IOException
  {
    if (id != null)
    {
      this.addAttribute("id", id + "." + postfix);
    }

    if (checked)
    {
      this.addAttribute("checked", "checked");
    }
    else
    {
      this.removeAttribute("checked");
    }

    this.addAttribute("value", value);

    this.writeEmptyTag("input", out);

    out.write(label + " ");
  }
}