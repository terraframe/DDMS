package dss.vector.solutions.migration;

import java.util.ArrayList;

public class PatchRegistry
{
  private static ArrayList<DDMSPatchIF> patches;
  
  static {
    patches = new ArrayList<DDMSPatchIF>();
    
    registerPatch(new Patcher2794());
    registerPatch(new Patcher3883());
    registerPatch(new Patcher3974());
    registerPatch(new Patcher4009());
  }
  
  public static void registerPatch(DDMSPatchIF patch)
  {
    patches.add(patch);
  }
  
  public static ArrayList<DDMSPatchIF> getPatchesInRange(Integer fromVersion, Integer toVersion)
  {
    ArrayList<DDMSPatchIF> range = new ArrayList<DDMSPatchIF>();
    
    for (DDMSPatchIF patch : patches)
    {
      if (patch.getPatchVersion() > fromVersion && patch.getPatchVersion() <= toVersion)
      {
        range.add(patch);
      }
    }
    
    return range;
  }
}
