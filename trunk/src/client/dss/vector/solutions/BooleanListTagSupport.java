package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;

import com.runwaysdk.business.ComponentDTOFacade;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.controller.tag.BooleanTagSupport;
import com.runwaysdk.controller.tag.ComponentMarkerIF;
import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.attributes.AttributeBooleanDTO;

@TagAnnotation(bodyContent = "empty", name = "selectBoolean", description = "Tag denoting a boolean drop down input")
public class BooleanListTagSupport extends BooleanTagSupport implements Reloadable
{
  private Boolean includeBlank;
  
  @AttributeAnnotation(description = "Flag denoting if an empty option should be generated.", rtexprvalue=true)
  public Boolean getIncludeBlank()
  {
    return includeBlank;
  }

  public void setIncludeBlank(Boolean includeBlank)
  {
    this.includeBlank = includeBlank;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _name = this.getParam();
    String _value = this.getValue();    
    String _id = this.getId();

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
        if (this.getTrueLabel() == null)
        {
          this.setTrueLabel(abDTO.getAttributeMdDTO().getPositiveDisplayLabel());
        }
        if (this.getFalseLabel() == null)
        {
          this.setFalseLabel(abDTO.getAttributeMdDTO().getNegativeDisplayLabel());
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }
    // set label defaults if no label is found
    if (this.getTrueLabel() == null)
    {
      this.setTrueLabel("true");
    }
    if (this.getFalseLabel() == null)
    {
      this.setFalseLabel("false");
    }
    
    this.writeTags(out, _name, _value, _id);    
  }

  private void writeTags(JspWriter out, String _name, String _value, String _id) throws IOException
  {
    String selectTag = this.getSelectTag(_name, _id);
    
    out.write(selectTag + "\n");
    
    if(this.getIncludeBlank() != null && this.getIncludeBlank())
    {
      writeOption(out, "", "", null, (_id != null ? _id + ".blank" : null));
    }
    
    this.writeOption(out, "true", this.getTrueLabel(), _value.equalsIgnoreCase("true"), (_id != null ? _id + ".positive" : null));
    this.writeOption(out, "false", this.getFalseLabel(), _value.equalsIgnoreCase("false"), (_id != null ? _id + ".negative" : null));
    
    out.write("</select>");
  }

  private String getSelectTag(String name, String id)
  {
    String tag =  "<select name=\"" + name + "\"";
    
    if(id != null)
    {
      tag += " id =\"" + id + "\"";
    }
    
    tag += ">";
    
    return tag;
  }

  private void writeOption(JspWriter out, String optionValue, String optionLabel, Boolean selected, String id) throws IOException
  {
    out.write("<option value=\"" + optionValue + "\"");

    if(selected != null && selected)
    {
      out.write(" selected=\"selected\"");      
    }
    
    if(id != null)
    {
      out.write(" id = \"" + id  + "\"");
    }
    
    out.write(">" + optionLabel + "</option>\n");
  }
}
