package dss.vector.solutions.manager.server;

public interface IServerListener
{

  public void error(Throwable t);

  public void error(String msg);

  public void serverStateChange(ServerStatus status);

}
