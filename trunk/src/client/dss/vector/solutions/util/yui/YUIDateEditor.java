package dss.vector.solutions.util.yui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.Constants;
import com.runwaysdk.generation.loader.Reloadable;

public class YUIDateEditor extends YUIEditor implements Reloadable
{
  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    options.add("calendar:MDSS.Calendar.init()");

    return options;
  }

  @Override
  public String getType()
  {
    return DATE_EDITOR;
  }

  @Override
  public String getValue(Object object)
  {
    SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT);

    return df.format((Date) object);
  }

  @Override
  public String getDefaultValue(Object value)
  {
    return "'" + this.getValue(value) + "'"; 
  }
}
