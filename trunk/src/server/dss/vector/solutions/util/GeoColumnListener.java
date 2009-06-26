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
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;

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
  
  public static List<SearchableHierarchy> getHierarchy()
  {
    List<GeoHierarchyView> political = Arrays.asList(GeoHierarchy.getPoliticalGeoHierarchiesByType(Country.CLASS));    

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

    
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(political);

    //Sentinel and Non-Sentinel Sites are not part of the political hierarchy so I must add them manually,
    //This only works if Sentinel and Non-Sentinel Sites are not part of the political hierarchy.
    hierarchy.add(new GenericHierarchySearcher(new String[]{SentinelSite.CLASS, NonSentinelSite.CLASS}));
    
    return hierarchy;
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {
  }
}