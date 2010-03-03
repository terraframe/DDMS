package dss.vector.solutions;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.terraframe.mojo.business.MutableDTO;
import com.terraframe.mojo.controller.DTOFacade;
import com.terraframe.mojo.controller.tag.ComponentMarkerIF;
import com.terraframe.mojo.controller.tag.InputTagSupport;
import com.terraframe.mojo.controller.tag.develop.AttributeAnnotation;
import com.terraframe.mojo.controller.tag.develop.TagAnnotation;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;

@TagAnnotation(name = "geo", bodyContent = "scriptless", description = "Geo input tag")
public class GeoTagSupport extends SimpleTagSupport implements Reloadable
{
  /**
   * Name of the controller parameter or attribute being inputed
   */
  private String                 param;

  /**
   * Flag indication if this tag should generate a hidden field for the selected
   * id
   */
  private Boolean                concrete;

  /**
   * Flag indicating if this fields should restrict by political universals
   */
  private Boolean                political;

  /**
   * Flag indicating if this fields should restrict by populated universals
   */
  private Boolean                populated;

  /**
   * Flag indicating if this fields should restrict by spray target allowed
   * universals
   */
  private Boolean                spray;

  /**
   * List of additional accepted universals
   */
  private List<String>           universals;

  /**
   * Class attribute
   */
  private String                 classes;

  /**
   * Id attribute
   */
  private String                 id;

  /**
   * Current value term
   */
  private Object                 value;

  private String                 filter;

  private List<FilterTagSupport> radioFilters;
  
  /**
   * Flag denoting if searching on this geo tag should enforce the system geo root
   */
  private Boolean                enforceRoot;

  public GeoTagSupport()
  {
    this.political = true;
    this.populated = false;
    this.spray = false;
    this.concrete = true;
    this.enforceRoot = true;
    this.universals = new LinkedList<String>();
    this.radioFilters = new LinkedList<FilterTagSupport>();
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

  @AttributeAnnotation(rtexprvalue = true, description = "Flag indicating if this fields should restrict by political universals")
  public Boolean getPolitical()
  {
    return political;
  }

  public void setPolitical(Boolean political)
  {
    this.political = political;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Flag indicating if this fields should restrict by populated universals")
  public Boolean getPopulated()
  {
    return populated;
  }

  public void setPopulated(Boolean populated)
  {
    this.populated = populated;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Flag indicating if this fields should restrict by spray target allowed universals")
  public Boolean getSpray()
  {
    return spray;
  }

  public void setSpray(Boolean spray)
  {
    this.spray = spray;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Flag indicating if this fields should restrict by populated universals")
  public List<String> getUniversals()
  {
    return universals;
  }

  public void setUniversals(List<String> universals)
  {
    this.universals = universals;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Classes of the tag")
  public String getClasses()
  {
    return classes;
  }

  public void setClasses(String classes)
  {
    this.classes = classes;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Id of tag")
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Current value of the tag this can be either a String with the geoId or a GeoEntityDTO")
  public Object getValue()
  {
    return value;
  }

  public void setValue(Object value)
  {
    this.value = value;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Flag indicating if this tag should generate a hidden field for the concrete id")
  public Boolean getConcrete()
  {
    return concrete;
  }

  public void setConcrete(Boolean concrete)
  {
    this.concrete = concrete;
  }

  @AttributeAnnotation(rtexprvalue = true, description = "Fully qualified universals to filter on")
  public String getFilter()
  {
    return filter;
  }

  public void setFilter(String filter)
  {
    this.filter = filter;
  }

  protected void addRadioFilter(FilterTagSupport tag)
  {
    this.radioFilters.add(tag);
  }
  
  @AttributeAnnotation(rtexprvalue = true, required = false, description = "Flag denoting if the searching on this geo tag should use the global geo root")
  public Boolean getEnforceRoot()
  {
    return enforceRoot;
  }

  public void setEnforceRoot(Boolean enforceRoot)
  {
    this.enforceRoot = enforceRoot;
  }

  private Set<String> getExtraUniversals()
  {
    Set<String> set = new LinkedHashSet<String>();
    List<String> list = this.getUniversals();
    String _filter = this.getFilter();

    if (list != null)
    {
      set.addAll(list);
    }

    if (_filter != null && !_filter.equals(""))
    {
      set.add(_filter);
    }

    return set;
  }

  @Override
  public void doTag() throws JspException, IOException
  {
    JspWriter out = this.getJspContext().getOut();

    // Invoke the body first so that the filters are generated
    // and populated before the actual search tag
    if (this.getJspBody() != null)
    {
      this.getJspBody().invoke(out);
    }

    JspTag parent = findAncestorWithClass(this, ComponentMarkerIF.class);

    String _param = this.getParam();
    Object _value = this.getValue();

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

          if (current != null)
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

    String _id = ( this.getId() != null ) ? this.getId() : this.getParam();
    String _inputParam = ( this.getConcrete() ? "#" + _param + "_GeoId" : _param );

    InputTagSupport attributeInput = new InputTagSupport();
    attributeInput.setJspBody(this.getJspBody());
    attributeInput.setJspContext(this.getJspContext());
    attributeInput.setId(_id);
    attributeInput.setType("text");
    attributeInput.setParam(_inputParam);
    attributeInput.setClasses(this.getClasses());

    if (_value != null && _value instanceof GeoEntityDTO)
    {
      GeoEntityDTO entity = (GeoEntityDTO) _value;
      attributeInput.setValue(entity.getGeoId());
    }
    else if (_value != null && _value instanceof String)
    {
      attributeInput.setValue((String) _value);
    }

    attributeInput.doTag();

    if (this.getConcrete())
    {
      InputTagSupport concreteInput = new InputTagSupport();
      concreteInput.setJspBody(this.getJspBody());
      concreteInput.setJspContext(this.getJspContext());
      concreteInput.setId(_id + "_geoEntityId");
      concreteInput.setType("hidden");
      concreteInput.setParam(_param);

      if (_value != null && _value instanceof GeoEntityDTO)
      {
        GeoEntityDTO entity = (GeoEntityDTO) _value;
        concreteInput.setValue(entity.getId());
      }

      concreteInput.doTag();
    }

    out.write("<script type=\"text/javascript\">\n");
    out.write("(function(){\n");
    out.write("  YAHOO.util.Event.onDOMReady(function(){\n");
    out.write("    var geoInput = document.getElementById('" + _id + "');\n");
    out.write("    var selectSearch = new MDSS.SingleSelectSearch(" + this.getEnforceRoot() + ");\n");

    this.writeFilterScript(out);

    out.write("    var geoSearch = new MDSS.GeoSearch(geoInput, selectSearch);\n");

    this.writeFilterTags(out);

    out.write("  })\n");
    out.write("})();\n");
    out.write("</script>\n");
  }

  private void writeFilterScript(JspWriter out) throws IOException
  {
    Set<String> _universals = this.getExtraUniversals();

    if (this.getFilter() != null)
    {
      out.write("    selectSearch.setFilter('" + this.getFilter() + "');\n");
    }

    out.write("    selectSearch.setPolitical(" + this.getPolitical() + ");\n");
    out.write("    selectSearch.setPopulated(" + this.getPopulated() + ");\n");
    out.write("    selectSearch.setSprayTargetAllowed(" + this.getSpray() + ");\n");

    for (String universal : _universals)
    {
      out.write("    selectSearch.addExtraUniversal('" + universal + "');\n");
    }
  }

  private void writeFilterTags(JspWriter out) throws IOException
  {
    for (FilterTagSupport tag : this.radioFilters)
    {
      out.write("\n");
      out.write("    if(document.getElementById('" + tag.getId() + "').checked === true){\n");
      out.write("      geoSearch.setFilter('" + tag.getUniversal() + "');\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    YAHOO.util.Event.on('" + tag.getId() + "', 'click', function(e, obj){\n");
      out.write("      var radio = e.target;\n");
      out.write("      if(radio.checked)  {\n");
      out.write("        var filter = e.target.value;\n");
      out.write("        this.setFilter('" + tag.getUniversal() + "');\n");
      out.write("      }\n");
      out.write("     }, null, geoSearch);\n");
    }
  }
}
