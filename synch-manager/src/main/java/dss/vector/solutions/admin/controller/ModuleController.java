package dss.vector.solutions.admin.controller;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.admin.MDSSModule;
import dss.vector.solutions.geo.generated.GeoEntity;
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
      GeoEntity.buildAllPathsFastInner();
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
      AllPaths.rebuildAllPathsInner();
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
      return AllPaths.containsValues();
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
      return dss.vector.solutions.geo.AllPaths.containsValues();
    }
    catch (Exception e)
    {
      module.asyncError(e);
    }

    return false;
  }
}
