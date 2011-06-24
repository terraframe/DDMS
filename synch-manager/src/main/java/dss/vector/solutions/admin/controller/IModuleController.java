package dss.vector.solutions.admin.controller;


public interface IModuleController
{
  public void rebuildGeoPaths();

  public void rebuildTermPaths();

  public void deleteGeoPaths();

  public void deleteTermPaths();

  public boolean hasAllPathTables();
}
