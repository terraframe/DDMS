package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;

import com.runwaysdk.ClientException;
import com.runwaysdk.business.ComponentDTOFacade;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.controller.tag.BooleanTagSupport;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.attributes.AttributeBooleanDTO;
import com.runwaysdk.transport.metadata.AttributeBooleanMdDTO;

@TagAnnotation(bodyContent = "empty", name = "checkBoolean", description = "Tag denoting a boolean checkbox")
public class BooleanCheckTagSupport extends BooleanTagSupport implements Reloadable
{
  private boolean showAttributeLabel;

  public BooleanCheckTagSupport()
  {
    this.showAttributeLabel = true;
  }

  @AttributeAnnotation(description = "Flag denoting if the attribute label should be displayed")
  public boolean getShowAttributeLabel()
  {
    return showAttributeLabel;
  }

  public void setShowAttributeLabel(boolean showAttributeLabel)
  {
    this.showAttributeLabel = showAttributeLabel;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _name = this.getParam();
    String _value = this.getValue();
    String _id = this.getId();
    String _disabled = this.getDisabled();

    // If the input tag is in the context of a component then
    // load update the parameter name and display value
    if (parent != null)
    {
      ComponentMarkerIF component = (ComponentMarkerIF) parent;
      MutableDTO item = component.getItem();

      _name = component.getParam() + "." + this.getParam();

      if (this.getValue() == null)
      {
        _value = item.getValue(this.getParam());
      }

      // set label defaults from metadata if no label is found
      try
      {
        AttributeBooleanDTO abDTO = ComponentDTOFacade.getAttributeBooleanDTO(item, this.getParam());
        AttributeBooleanMdDTO attributeMdDTO = abDTO.getAttributeMdDTO();

        String tag = "<input name=\"" + _name + "\" type=\"checkbox\"";

        if (_id != null)
        {
          tag += " id =\"" + _id + "\"";
        }

        if (_value != null && _value.equalsIgnoreCase(MdAttributeBooleanInfo.TRUE))
        {
          tag += " checked =\"checked\"";
        }

        if (_disabled != null && _disabled.equalsIgnoreCase(MdAttributeBooleanInfo.TRUE))
        {
          tag += " disabled =\"disabled\"";
        }

        tag += ">";

        if(this.showAttributeLabel)
        {
          tag += attributeMdDTO.getDisplayLabel();
        }

        out.write(tag);
      }
      catch (Exception e)
      {
        throw new ClientException(e);
      }
    }
  }
}
