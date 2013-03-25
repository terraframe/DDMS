package dss.vector.solutions.admin.controller;

import java.io.File;

import com.runwaysdk.business.generation.LoaderDecoratorException;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.manager.controller.IConfiguration;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.admin.MDSSModule;
import dss.vector.solutions.ontology.AllPaths;

public class ModuleController implements IModuleController
{
  private MDSSModule module;

  public ModuleController(MDSSModule module)
  {
    this.module = module;
  }

  @Override
  @Request
  public void rebuildGeoPaths()
  {
    rebuildGeoPathsInTransaction();
  }

  @Transaction
  public void rebuildGeoPathsInTransaction()
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.geo.generated.GeoEntity");
      clazz.getMethod("buildAllPathsFastInner").invoke(null);

      // GeoEntity.buildAllPathsFastInner();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }
  }

  @Override
  @Request
  public void rebuildTermPaths()
  {
    rebuildTermPathsInTransaction();
  }

  @Transaction
  public void rebuildTermPathsInTransaction()
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.ontology.AllPaths");
      clazz.getMethod("rebuildAllPathsInner").invoke(null);
      // AllPaths.rebuildAllPathsInner();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }
  }

  @Override
  @Request
  public void deleteGeoPaths()
  {
    this.deleteGeoPathsInTransaction();
  }

  @Transaction
  public void deleteGeoPathsInTransaction()
  {
    try
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(dss.vector.solutions.geo.AllPaths.CLASS);
      mdBusiness.deleteAllTableRecords();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }

  }

  @Override
  @Request
  public void deleteTermPaths()
  {
    this.deleteTermPathsInTransaction();
  }

  @Transaction
  public void deleteTermPathsInTransaction()
  {
    try
    {
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);
      mdBusiness.deleteAllTableRecords();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }
  }

  @Override
  public void deletePermissionCache()
  {
    IConfiguration configuration = this.module.getConfiguration();

    if (configuration != null && configuration instanceof DependentConfiguration)
    {
      try
      {
        String cacheDirectory = LocalProperties.getPermissionCacheDirectory();

        FileIO.deleteDirectory(new File(cacheDirectory));
      }
      catch (Exception e)
      {
        module.asyncError(e);
      }
    }
  }

  @Request
  public boolean hasAllPathTables()
  {
    return ( geoPathsContainsValues() && termPathsContainsValues() );
  }

  private boolean termPathsContainsValues()
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.ontology.AllPaths");
      return (Boolean) clazz.getMethod("containsValues").invoke(null);
      // return AllPaths.containsValues();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }

    return false;
  }

  private boolean geoPathsContainsValues()
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.geo.AllPaths");
      return (Boolean) clazz.getMethod("containsValues").invoke(null);

      // return dss.vector.solutions.geo.AllPaths.containsValues();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }

    return false;
  }

  @Request
  public void updateSeasonLabels()
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.util.MalariaSeasonLabelUpdater");

      if (clazz != null)
      {
        Runnable runnable = (Runnable) clazz.newInstance();
        runnable.run();
      }
    }
    catch (LoaderDecoratorException e)
    {
      // Do nothing: The MalariaSeasonLabelUpdater class doesn't exist yet which
      // means the dependent node has yet to synch with the metadata definition
      // for the MalraiaSeason.seasonLabel attribute. As such there is nothing
      // to update.
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }
  }
}
