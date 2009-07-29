package dss.vector.solutions.export;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.GenericHierarchySearcher;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.HierarchyBuilder;
import dss.vector.solutions.util.SearchableHierarchy;

public class DynamicGeoColumnListener implements ExcelExportListener, ImportListener, Reloadable
{
  private String attributeName;
  private String excelType;
  private List<GeoHierarchy> hierarchyList;
  
  public static final String PREFIX = "Geo ";
  
  public DynamicGeoColumnListener(String excelType, String attributeName, GeoHierarchy... endPoints)
  {
    this.attributeName = attributeName;
    this.excelType = excelType;
    HierarchyBuilder hierarchyBuilder = new HierarchyBuilder();
    for (GeoHierarchy endPoint : endPoints)
    {
      hierarchyBuilder.add(endPoint);
    }
    hierarchyList = hierarchyBuilder.getHierarchy();
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    
    for(GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String geoLabel = geoEntityClass.getDisplayLabel().getValue();
      String geoAttribute = getExcelAttribute(geoEntityClass);
      
      extraColumns.add(new ExcelColumn(geoAttribute, geoLabel));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    List<SearchableHierarchy> searchable = new LinkedList<SearchableHierarchy>();
    List<String> geoEntityNames = new ArrayList<String>(hierarchyList.size());
    
    // Iterate over every expected column
    for(GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      searchable.add(new GenericHierarchySearcher(geoEntityClass.definesType()));
      String excelAttribute = getExcelAttribute(geoEntityClass);
      
      // Go find the expected column
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(excelAttribute))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          geoEntityNames.add(cell.getRichStringCellValue().getString());
          continue;
        }
      }
    }
    
    GeoEntitySearcher searcher = new GeoEntitySearcher(searchable);
    GeoEntity entity = searcher.getGeoEntity(geoEntityNames);
    
    // Now use reflection to set the value
    Class<?> excelClass = LoaderDecorator.load(excelType);
    String accessorName = GenerationUtil.upperFirstCharacter(instance.getMdAttributeDAO(attributeName).getAccessorName());
    excelClass.getMethod("set" + accessorName, GeoEntity.class).invoke(instance, entity);
  }

  private String getExcelAttribute(MdBusiness geoEntityClass)
  {
    String geoType = geoEntityClass.getTypeName();
    return PREFIX + this.attributeName + " " + geoType;
  }

  public void preHeader(ExcelColumn columnInfo) { }

  public void preWrite(HSSFWorkbook workbook) { }
}
