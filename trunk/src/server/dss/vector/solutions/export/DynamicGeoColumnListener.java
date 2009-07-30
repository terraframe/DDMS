package dss.vector.solutions.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.HierarchyBuilder;

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
    HierarchyBuilder mainHierarchyBuilder = new HierarchyBuilder();
    for (GeoHierarchy endPoint : endPoints)
    {
      mainHierarchyBuilder.add(endPoint);
    }
    hierarchyList = mainHierarchyBuilder.getHierarchy();
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
    List<String> geoEntityNames = new ArrayList<String>(hierarchyList.size());

    String endPointEntityName = "";
    String endPointEntityType = "";

    GeoEntity entity = null;
    Map<String, String> parentGeoEntityMap = new HashMap<String, String>();
    for(GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String excelAttribute = getExcelAttribute(geoEntityClass);
      
      // Go find the expected column
      for (ExcelColumn column : extraColumns)
      {
        HSSFCell cell = row.getCell(column.getIndex());
        String entityName;
        if (cell==null)
        {
          entityName = "";
        }
        else
        {
          if (column.getAttributeName().equals(excelAttribute))
          {
            entityName = cell.getRichStringCellValue().getString();
            
            geoEntityNames.add(entityName);
            parentGeoEntityMap.put(geoEntityClass.definesType(), entityName);
            
            endPointEntityName = entityName;
            endPointEntityType = geoEntityClass.definesType();
          }
        }
      }
    }
    
    if (!endPointEntityName.equals(""))
    {
      entity = AllPaths.search(parentGeoEntityMap, endPointEntityType, endPointEntityName);
    }

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
