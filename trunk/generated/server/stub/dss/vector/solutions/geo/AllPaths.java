package dss.vector.solutions.geo;

import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.util.GeoEntityAllPathBuilder;

public class AllPaths extends AllPathsBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245657919728L;

  public AllPaths()
  {
    super();
  }

  public static void deleteAllTableRecords()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();
  }

  public static void regeneratePaths()
  {
    GeoEntityAllPathBuilder.main(new String[] {});
  }

  @Override
  protected String buildKey()
  {
    // TODO: Naifeh needs to define this key
    return this.getId();
  }
}
