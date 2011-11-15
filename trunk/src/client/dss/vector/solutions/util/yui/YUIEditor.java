package dss.vector.solutions.util.yui;

import java.util.List;

import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeBooleanMdDTO;
import com.runwaysdk.transport.metadata.AttributeDateMdDTO;
import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;
import com.runwaysdk.transport.metadata.AttributeMdDTO;
import com.runwaysdk.transport.metadata.AttributeNumberMdDTO;
import com.runwaysdk.transport.metadata.AttributeReferenceMdDTO;

import dss.vector.solutions.LabeledDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class YUIEditor implements Reloadable
{
  public static final String DROPDOWN_EDITOR = "new YAHOO.widget.DropdownCellEditor";

  public static final String TEXTBOX_EDITOR  = "new YAHOO.widget.TextboxCellEditor";

  public static final String DATE_EDITOR     = "new YAHOO.widget.DateCellEditor";

  public static final String TERM_EDITOR     = "new YAHOO.widget.OntologyTermEditor";

  public static final String NUMBER_EDITOR   = "new YAHOO.widget.NumberCellEditor";

  public abstract String getType();

  public abstract List<String> getOptions();

  public abstract String getDefaultValue(Object value);

  public String getValue(Object object)
  {
    return object.toString();
  }

  public static YUIEditor getEditor(AttributeMdDTO attribute, ColumnSetup setup, MutableDTO view, String key) throws Exception
  {
    if (attribute instanceof AttributeBooleanMdDTO)
    {
      return new YUIBooleanEditor((AttributeBooleanMdDTO) attribute, setup);
    }
    else if (attribute instanceof AttributeDateMdDTO)
    {
      return new YUIDateEditor();
    }
    else if (attribute instanceof AttributeNumberMdDTO)
    {
      return new YUINumberEditor();
    }
    else if (attribute instanceof AttributeEnumerationMdDTO)
    {
      return new YUIEnumerationEditor((AttributeEnumerationMdDTO) attribute, setup);
    }
    else if (attribute instanceof AttributeReferenceMdDTO)
    {
      Class<?> javaType = attribute.getJavaType();

      if (setup.getType() != null)
      {
        javaType = LoaderDecorator.load(setup.getType());
      }

      if (LabeledDTO.class.isAssignableFrom(javaType))
      {
        return new YUILabeledEditor(attribute, setup, key);
      }
      else if (TermDTO.class.isAssignableFrom(javaType))
      {
        return new YUITermEditor(attribute, view.getType());
      }

      return new YUIReferenceEditor();
    }

    return new YUITextEditor();
  }
}
