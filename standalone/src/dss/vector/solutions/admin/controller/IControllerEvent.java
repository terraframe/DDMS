package dss.vector.solutions.admin.controller;

public interface IControllerEvent
{
  public static final int    CLOSE_TAB            = 0;

  public static final int    OPEN_TAB             = 1;

  public static final int    ERROR                = 3;

  public static final int    EXECUTE_TASK         = 4;

  public static final int    SERVER_STATUS_CHANGE = 5;

  public static final String KEY                  = "KEY";

  public static final String OBJECT               = "OBJECT";

  public static final String MESSAGE              = "MESSAGE";

  public int getType();

  public Object getData(String key);

  public void setData(String key, Object data);
}
