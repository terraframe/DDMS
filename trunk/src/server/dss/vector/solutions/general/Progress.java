package dss.vector.solutions.general;

import com.runwaysdk.generation.loader.Reloadable;

public class Progress implements Reloadable
{
  private String  viewName;

  private Integer total;

  private Integer current;

  public Progress(Integer total)
  {
    this.viewName = new String();
    this.current = 0;
    this.total = total;
  }

  public String getViewName()
  {
    return viewName;
  }

  public Integer getTotal()
  {
    return total;
  }

  public Integer getCurrent()
  {
    return current;
  }

  public void setViewName(String viewName)
  {
    this.viewName = viewName;
  }

  public void setTotal(Integer total)
  {
    this.total = total;
  }

  public void setCurrent(Integer current)
  {
    this.current = current;
  }

  public void increment(String viewName)
  {
    this.setViewName(viewName);
    this.current++;
  }

  public Integer getProgress()
  {
    return ( ( 100 * this.current ) / this.total );
  }
}
