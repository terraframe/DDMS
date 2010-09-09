package dss.vector.solutions.admin.controller;

import java.util.HashMap;

public class ControllerEvent implements IControllerEvent
{
  private HashMap<String, Object> data;

  private int                     type;
  
  public ControllerEvent(int type)
  {
    this.type = type;
    this.data = new HashMap<String, Object>();
  }

  @Override
  public Object getData(String key)
  {
    return this.data.get(key);
  }

  @Override
  public int getType()
  {
    return this.type;
  }

  @Override
  public void setData(String key, Object data)
  {
    this.data.put(key, data);
  }

}
