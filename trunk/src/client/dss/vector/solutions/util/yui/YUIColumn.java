package dss.vector.solutions.util.yui;

import com.runwaysdk.ApplicationException;
import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

public class YUIColumn implements Reloadable
{
  private Boolean   writable;

  private String    attributeName;

  private String    key;

  private YUIEditor editor;

  /**
   * If this column is hidden
   */
  private boolean   hidden;

  /**
   * If this column is editable
   */
  private boolean   editable;

  /**
   * If the column should sum its values onto the last row
   */
  private boolean   sum;

  /**
   * Validator to use in the column
   */
  private String    validator;

  private String    title;

  private String    label;

  private String    getter;

  private String    defaultValue;

  private String    postfix;

  private String    options;

  private Integer   width;

  public YUIColumn(ColumnSetup setup, ViewDTO view, String attribute, String postfix)
  {
    try
    {
      DTOFacade facade = new DTOFacade(attribute, view);

      AttributeMdDTO attributeMd = facade.getAttributeMdDTO();

      this.postfix = postfix;
      this.writable = view.isWritable(attributeMd.getName());
      this.attributeName = attributeMd.getName();
      this.key = GenerationUtil.upperFirstCharacter(attributeMd.getName());
      this.hidden = setup.isHidden();
      this.editable = setup.isEditable();
      this.sum = setup.isSum();
      this.validator = setup.getValidator();
      this.title = setup.getTitle();
      this.label = setup.getLabel() != null ? setup.getLabel() : attributeMd.getDisplayLabel();
      this.getter = setup.getGetter();
      this.defaultValue = null;
      this.options = null;
      this.editor = YUIEditor.getEditor(attributeMd, setup, view, key + postfix);
      this.width = setup.getWidth();

      this.initDefaultValue(facade);
      this.initOptions(view);
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }
  }

  private final void initOptions(ViewDTO view)
  {
    if (this.editor instanceof YUILabeledEditor)
    {
      ClientRequestIF request = view.getRequest();
      YUILabeledEditor _editor = (YUILabeledEditor) this.editor;

      this.options = _editor.getDropdownOptions(request);
    }
  }

  private final void initDefaultValue(DTOFacade facade) throws Exception
  {
    Object value = facade.getValue();

    if (value != null)
    {
      String _defaultValue = this.editor.getDefaultValue(value);

      if (_defaultValue != null && _defaultValue.length() > 0)
      {
        this.defaultValue = "'" + key + this.postfix + "':" + _defaultValue;
      }
    }
  }

  public Boolean getWritable()
  {
    return writable;
  }

  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getKey()
  {
    return key;
  }

  public String getColumnKey()
  {
    return key + postfix;
  }

  public YUIEditor getEditor()
  {
    return this.editor;
  }

  public boolean isSum()
  {
    return this.sum;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isHidden()
  {
    return this.hidden;
  }

  public boolean isEditable()
  {
    return this.editable;
  }

  public String getValidator()
  {
    return this.validator;
  }

  public String getDropDownOptions()
  {
    return this.options;
  }

  public Integer getWidth()
  {
    return width;
  }

  public String getValue(ViewDTO view)
  {
    try
    {

      DTOFacade facade = new DTOFacade(key, view);

      Object object = null;

      if (this.getter != null && this.getter.length() > 0)
      {
        Class<? extends ViewDTO> klass = view.getClass();
        object = klass.getMethod(getter).invoke(view);
      }
      else
      {
        if (this.isHidden())
        {
          object = view.getValue(attributeName);

          if (object != null)
          {
            return object.toString();
          }
        }
        else
        {
          object = facade.getValue();

          if (object != null)
          {
            return this.editor.getValue(object);
          }
        }
      }

      return null;
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }

  }

  public String getDefaultValue()
  {
    return defaultValue;
  }

  public static String getDefaultValue(String objectValue, String objectLabel)
  {
    return "{'value':'" + objectValue + "','label':'" + objectLabel + "'}";
  }
}
