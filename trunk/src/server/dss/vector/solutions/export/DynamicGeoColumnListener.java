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

import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.HierarchyBuilder;

public class DynamicGeoColumnListener implements ExcelExportListener, ImportListener, Reloadable
{
  private String attributeName;
  private String excelType;
  private GeoHierarchy[] endPoints;
  private List<GeoHierarchy> hierarchyList;
  private Map<String, List<GeoHierarchy>> endPointHierarchyMap;

  public static final String PREFIX = "Geo ";

  public DynamicGeoColumnListener(String excelType, String attributeName, GeoHierarchy... endPoints)
  {
    this.attributeName = attributeName;
    this.excelType = excelType;
    this.endPoints = endPoints;
    this.endPointHierarchyMap = new HashMap<String, List<GeoHierarchy>>();
    HierarchyBuilder mainHierarchyBuilder = new HierarchyBuilder();
    for (GeoHierarchy endPoint : endPoints)
    {
      mainHierarchyBuilder.add(endPoint);

      HierarchyBuilder endPointHierarchyBuilder = new HierarchyBuilder();
      endPointHierarchyBuilder.add(endPoint);
      this.endPointHierarchyMap.put(endPoint.getGeoEntityClass().definesType(), endPointHierarchyBuilder.getHierarchy());
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

    GeoEntity entity = null;
    for (GeoHierarchy endPoint : this.endPoints)
    {
      Map<String, String> parentGeoEntityMap = new HashMap<String, String>();

      MdBusiness endPointgeoEntityClass = endPoint.getGeoEntityClass();
      String endPointExcelAttribute = getExcelAttribute(endPointgeoEntityClass);

      for(GeoHierarchy hierarchy : this.endPointHierarchyMap.get(endPoint.getGeoEntityClass().definesType()))
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
            entityName = cell.getRichStringCellValue().getString();
          }

          if (column.getAttributeName().equals(endPointExcelAttribute))
          {
            endPointEntityName = entityName;
          }
          else if (column.getAttributeName().equals(excelAttribute))
          {
            geoEntityNames.add(entityName);
            parentGeoEntityMap.put(geoEntityClass.definesType(), entityName);
          }
        }
      }

      if (!endPointEntityName.equals(""))
      {
        try
        {
          entity = AllPaths.search(parentGeoEntityMap, endPointgeoEntityClass.definesType(), endPointEntityName);
        }
        catch(UnknownGeoEntityException e)
        {
          // This may happen if there are multiple endpoints and the current endpoint.
        }
      }
    }

    if (entity != null)
    {
      // Now use reflection to set the value
      Class<?> excelClass = LoaderDecorator.load(excelType);
      String accessorName = GenerationUtil.upperFirstCharacter(instance.getMdAttributeDAO(attributeName).getAccessorName());
      excelClass.getMethod("set" + accessorName, GeoEntity.class).invoke(instance, entity);
    }
    else
    {
      String msg = "Unknown Geo Entity [" + endPointEntityName + "]";
      UnknownGeoEntityException e =  new UnknownGeoEntityException(msg);
      e.setEntityName(endPointEntityName);
      e.apply();
    }
  }

  private String getExcelAttribute(MdBusiness geoEntityClass)
  {
    String geoType = geoEntityClass.getTypeName();
    return PREFIX + this.attributeName + " " + geoType;
  }

  public void preHeader(ExcelColumn columnInfo) { }

  public void preWrite(HSSFWorkbook workbook) { }
}
