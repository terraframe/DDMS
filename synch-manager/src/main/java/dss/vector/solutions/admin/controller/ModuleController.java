package dss.vector.solutions.admin.controller;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

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

//      GeoEntity.buildAllPathsFastInner();
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
//      AllPaths.rebuildAllPathsInner();
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
//      return AllPaths.containsValues();
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

//      return dss.vector.solutions.geo.AllPaths.containsValues();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }

    return false;
  }
}
