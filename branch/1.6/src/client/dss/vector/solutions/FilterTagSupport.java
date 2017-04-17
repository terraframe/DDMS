package dss.vector.solutions;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.runwaysdk.controller.tag.develop.AttributeAnnotation;
import com.runwaysdk.controller.tag.develop.TagAnnotation;
import com.runwaysdk.generation.loader.Reloadable;

@TagAnnotation(name = "filter", bodyContent = "scriptless", description = "Geo filter tag")
public class FilterTagSupport extends SimpleTagSupport implements Reloadable, Comparable<FilterTagSupport>
{
  private String  id;

  private String  universal;

  private Boolean checked;

  public FilterTagSupport()
  {
    this.checked = false;
  }

  public int compareTo(FilterTagSupport o)
  {
    return this.universal.compareTo(o.universal);
  }

  @AttributeAnnotation(required = true, rtexprvalue = true, description = "Id of the filter input")
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @AttributeAnnotation(required = true, rtexprvalue = true, description = "Fully qualified universals to filter on")
  public String getUniversal()
  {
    return universal;
  }

  public void setUniversal(String universal)
  {
    this.universal = universal;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Flag denoting if the filter is checked")
  public Boolean getChecked()
  {
    return checked;
  }

  public void setChecked(Boolean checked)
  {
    this.checked = checked;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();
    JspTag parent = this.getParent();

    if (parent != null)
    {
      GeoTagSupport geo = (GeoTagSupport) parent;

      if (!geo.hasFilter(this))
      {
        String name = "#" + geo.getParam() + "_Filter";
        String check = this.getChecked() ? " checked=\"checked\"" : "";

        out.write("<input type=\"radio\" name=\"" + name + "\" id=\"" + this.getId() + "\" value=\"" + this.getUniversal() + "\"" + check + "/>\n");

        if (this.getJspBody() != null)
        {
          this.getJspBody().invoke(null);
        }

        geo.addRadioFilter(this);
      }
    }
  }
}
