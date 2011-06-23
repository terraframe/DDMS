package dss.vector.solutions.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dss.vector.solutions.manager.server.ServerStatus;

public class ServerContextBeanTest
{
  @Test
  public void testLoad()
  {
    ManagerContextBean context = new ManagerContextBean();
    context.setApplication(new LabeledBean("", "DDMS"));
    context.setProcessRunning(false);
    context.setServerStatus(ServerStatus.STOPPED);

    ServerContextBean bean = new ServerContextBean(context);

    assertEquals(86400, bean.getTimeout());
    assertEquals(LogLevel.ERROR, bean.getLogLevel());
  }

  @Test
  public void testSave()
  {
    ManagerContextBean context = new ManagerContextBean();
    context.setApplication(new LabeledBean("", "DDMS"));
    context.setProcessRunning(false);
    context.setServerStatus(ServerStatus.STOPPED);

    ServerContextBean original = new ServerContextBean(context);

    try
    {
      ServerContextBean update = new ServerContextBean(context);
      update.setLogLevel(LogLevel.DEBUG);
      update.setTimeout(23400);
      update.save();

      ServerContextBean test = new ServerContextBean(context);

      assertEquals(update.getTimeout(), test.getTimeout());
      assertEquals(update.getLogLevel(), test.getLogLevel());
    }
    finally
    {
      original.save();
    }

  }
}
