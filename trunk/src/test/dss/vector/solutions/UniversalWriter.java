package dss.vector.solutions;

import java.util.LinkedList;
import java.util.List;

public class UniversalWriter
{
  private static List<String[]> existing = new LinkedList<String[]>();

  private static List<String[]> permissions = new LinkedList<String[]>();

  public static void main(String[] args)
  {
    test("");
  }

  private static void test(String sessionId)
  {
    String[] geoEntity = new String[] {"GeoEntity"};
    writeGeos(geoEntity, false, false, null);

    String[] earth = new String[] {"Earth"};
    writeGeos(earth, true, true, null);

    String[] country = new String[] {"Country"};
    writeGeos(country, true, true, earth);

    String[] nationalRoad = new String[] {"NationalRoad"};
    writeGeos(nationalRoad, false, false, country);

    String[] road = new String[] {"Road"};
    writeGeos(road, false, false, country);

    String[] railway = new String[] {"Railway"};
    writeGeos(railway, false, false, country);

    String[] river = new String[] {"River"};
    writeGeos(river, false, false, country);

    String[] province = new String[] {"Province"};
    writeGeos(province, true, true, country);

    String[] district = new String[] {"District"};
    writeGeos(district, true, true, province);

    String[] sprayZone = new String[] {"SprayZone"};
    writeGeos(sprayZone, false, true, district);

    String[] sprayArea = new String[] {"SprayArea"};
    writeGeos(sprayArea, false, true, sprayZone);

    String[] adminPost = new String[] {"AdminPost"};
    writeGeos(adminPost, false, false, district);

    String[] locality = new String[] {"Locality"};
    writeGeos(locality, false, false, adminPost);

    String[] populatedArea = new String[] { "PopulatedArea", "City", "Town", "Village", "Farm",
        "IndustrialSite", "Lodge" };
    writeGeos(populatedArea, false, false, locality);

    String[] reserve = new String[] {"Reserve"};
    writeGeos(reserve, false, false, populatedArea);

    String[] healthFacility = new String[] { "HealthFacility", "Hospital", "PrivateHospital",
        "PublicHospital", "Clinic", "PublicClinic", "PrivateClinic", "MobileClinic",
        "CommunityHealthCenter", "CommunityHealthWorker", "PrivatePractice" };
    writeGeos(healthFacility, false, false, populatedArea);

    String[] collectionSite = new String[] {"CollectionSite", "SentinelSite", "NonSentinelSite"};
    writeGeos(collectionSite, false, false, populatedArea);

    String[] pointCollection = new String[] {"PointCollection", "Trap", "PermanentWaterBody"};
    writeGeos(pointCollection, false, false, collectionSite);

    String[] breedingSite = new String[] {"BreedingSite", "NaturalHabitat","FreshwaterSwamp",
        "MangroveSwamp","Marsh","RiverBed","Seepage","AgriculturalArtificialHabitat","RidgeAndFurrow",
        "RicePaddy","OtherAgriculture","NonAgriculturalArtificialHabitat","Construction","Drain",
        "Puddle","TyreTrack","WaterStorageContainer"
    };
    writeGeos(breedingSite, false, false, populatedArea);

    String[] surface = new String[] {"Surface","Material","Net","ITN","LLITN",
        "Curtain","OtherMaterial","Wall","NaturalWall","RudimentaryWall","BambooWoodWithMudWall",
        "StoneWithMudWall","UncoveredAdobe","PlywoodWall","CartonWall","FinishedWall","CementWall",
        "StoneWithLimeWall", "BrickWall","CementBlockWall","CoveredAdobe","WoodPlansShingles",
        "OtherWall","Roof","NaturalRoof","ThatchLeafRoof","StickAndMudRoof","RudimentaryRoof",
        "RusticMatPlasticSheetRoof","ReedBambooRoof","WoodPlankRoof","FinishedRoof","CorrugatedIronRoof",
        "WoodRoof","CalamineCementFibreRoof","CementConcreteRoof","ShingleRoof","OtherRoof"
    };
    writeGeos(surface, false, false, new String[]{collectionSite[1]}); // Sentinel Site only

    for(String[] e : existing)
    {
      write(e);
    }

    for(String[] p : permissions)
    {
      write(p);
    }

  }

  private static void writeGeos(String[] geos, boolean political, boolean sprayTargetAllowed, String[] parents)
  {
    for (String geo : geos)
    {
      writeGeo(geo, political, sprayTargetAllowed);

      if (parents != null)
      {
        for (String parent : parents)
        {
          writeRel(geo, parent);
        }
      }
    }
  }

  private static void writeGeo(String geo, boolean political, boolean sprayTargetAllowed)
  {
    String[] s = new String[] { "",
        "<object",
        "  id=\"" + geo + "H\"",
        "  type=\"dss.vector.solutions.geo.GeoHierarchy\"",
        "  key=\"GeoHierarchy_"+geo+"\">",
        "<valueAttribute", "  name=\"political\"",
        "  value=\""+political+"\" />",
        "<valueAttribute",
        "  name=\"sprayTargetAllowed\"",
        "  value=\""+sprayTargetAllowed+"\" />",
        "<referenceAttribute",
        "  name=\"geoEntityClass\"",
        "  id=\"" + geo + "Md\" />",
        "</object>" };

    write(s);

    String[] e = new String[] {"",
        "<object",
        "  key=\"dss.vector.solutions.geo.generated."+geo+"\"",
        "  id=\""+geo+"Md\"",
        "  type=\"com.runwaysdk.system.metadata.MdBusiness\" />"};

    existing.add(e);

    String[] p = new String[] {"",
        "  <mdBusinessPermission type=\"dss.vector.solutions.geo.generated."+geo+"\">",
        "    <operation name=\"CREATE\" />",
        "    <operation name=\"WRITE\" />",
        "    <operation name=\"WRITE_ALL\" />",
        "    <operation name=\"DELETE\" />",
        "  </mdBusinessPermission>"
    };

    permissions.add(p);
  }

  private static void writeRel(String child, String parent)
  {
    String[] s = new String[] { "",
        "<relationship",
        "  type=\"dss.vector.solutions.geo.AllowedIn\"",
        "  childId=\"" + child + "H\"",
        "  parentId=\"" + parent + "H\"",
        "  id=\"" + child + "In" + parent + "\">",
        "</relationship>" };

    write(s);
  }

  private static void write(String[] s)
  {
    for (String l : s)
    {
      System.out.println("      "+l);
    }
  }
}
