package dss.vector.solutions.manager.server;

public interface IServer
{
  public void refresh();

  public void startServer();
  
  public void stopServer();
  
  public ServerStatus getServerStatus();
  
  public void close();
  
  public void addListener(IServerListener listener);
  
  public void removeListener(IServerListener listener);
}
