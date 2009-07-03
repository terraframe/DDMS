package dss.vector.solutions.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.AttributeColumn;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.PoliticalHierarchyLengthException;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.Locality;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.geo.generated.Surface;

public class GeoColumnListener implements ExcelExportListener, Reloadable
{
  private Map<String, String> map;

  public GeoColumnListener(Map<String, String> map)
  {
    this.map = map;
  }
  
  public void preHeader(ExcelColumn columnInfo)
  {
    if (!(columnInfo instanceof AttributeColumn))
    {
      return;
    }
    
    AttributeColumn attributeColumn = (AttributeColumn)columnInfo;
    String key = attributeColumn.getMdAttribute().getId();

    if(map.containsKey(key))
    {
      String displayLabel = map.get(key);

      attributeColumn.setDisplayLabel(displayLabel);
    }
  }
  
  public static List<SearchableHierarchy> getSprayHierarchy()
  {
    List<GeoHierarchyView> spray = Arrays.asList(GeoHierarchy.getSprayHierarchiesByType(Earth.CLASS));    
    
    //Ensure that the Spray Hierarhcy length is 10 or less
    if(spray.size() > 10)
    {
      String msg = "The spray hierarchy is longer than the number of allocated spots for the geo entity attribute.";
      
      PoliticalHierarchyLengthException e = new PoliticalHierarchyLengthException(msg);
      e.setSlots(10);
      e.setHierarchyLength(spray.size());
      e.apply();
      
      throw e;
    }
    
    return new LinkedList<SearchableHierarchy>(spray);
  }
  
  public static List<GeoHierarchyView> getPoliticalHierarchy()
  {
    List<GeoHierarchyView> political = Arrays.asList(GeoHierarchy.getPoliticalGeoHierarchiesByType(Earth.CLASS));    

    //Ensure that the Political Hierarhcy length is 10 or less
    if(political.size() > 10)
    {
      String msg = "The political hierarchy is longer than the number of allocated spots for the geo entity attribute.";
      
      PoliticalHierarchyLengthException e = new PoliticalHierarchyLengthException(msg);
      e.setSlots(10);
      e.setHierarchyLength(political.size());
      e.apply();
      
      throw e;
    }
    
    return political;
  }
  
  public static List<SearchableHierarchy> getLocalityHierarchy()
  {
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(getPoliticalHierarchy());
    
    // Append Locality to the Political Hierarchy
    hierarchy.add(new GenericHierarchySearcher(Locality.CLASS));
    
    return hierarchy;
  }
  
  public static List<SearchableHierarchy> getSentinelSiteHierarchy()
  {
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(getPoliticalHierarchy());
    
    // Append Sentinal Site to the Political Hierarchy
    hierarchy.add(new GenericHierarchySearcher(SentinelSite.CLASS));
    
    return hierarchy;
  }
  
  public static List<SearchableHierarchy> getSurfaceHierarchy()
  {
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(getSentinelSiteHierarchy());
    
    // Append Surface to the Sentinel Site Hierarchy
    hierarchy.add(new GenericHierarchySearcher(Surface.CLASS));
    
    return hierarchy;
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {
  }
}