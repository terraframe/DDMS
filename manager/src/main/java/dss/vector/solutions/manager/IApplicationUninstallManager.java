package dss.vector.solutions.manager;

public interface IApplicationUninstallManager
{
  public void onUninstall(String application);

  public int getApplicationCount();

  public String[] getApplications();
}
