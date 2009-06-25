package dss.vector.solutions.geo;

import dss.vector.solutions.util.GeoEntityAllPathBuilder;

public class AllPaths extends AllPathsBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245657919728L;
  
  public AllPaths()
  {
    super();
  }
  
  public static void regeneratePaths()
  {
    GeoEntityAllPathBuilder.main(new String[]{});
  }
}
