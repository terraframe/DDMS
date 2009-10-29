package dss.vector.solutions.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.HierarchyBuilder;

public class DynamicGeoColumnListener implements ExcelExportListener, ImportListener, Reloadable
{
  private String             attributeName;

  private String             excelType;

  private List<GeoHierarchy> hierarchyList;

  public static final String PREFIX = "Geo ";

  public DynamicGeoColumnListener(String excelType, String attributeName, HierarchyBuilder mainHierarchyBuilder)
  {
    this.attributeName = attributeName;
    this.excelType = excelType;
    hierarchyList = mainHierarchyBuilder.getHierarchy();
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {

    for (GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String geoLabel = geoEntityClass.getDisplayLabel().getValue();
      String geoAttribute = getExcelAttribute(geoEntityClass);

      extraColumns.add(new ExcelColumn(geoAttribute, geoLabel));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row)
      throws Exception
  {
    List<String> geoEntityNames = new ArrayList<String>(hierarchyList.size());

    String endPointEntityName = "";
    String endPointEntityType = "";

    List<GeoEntity> entityList = new LinkedList<GeoEntity>();
    Map<String, String> parentGeoEntityMap = new HashMap<String, String>();
    for (GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String excelAttribute = getExcelAttribute(geoEntityClass);

      // Go find the expected column
      for (ExcelColumn column : extraColumns)
      {
        HSSFCell cell = row.getCell(column.getIndex());
        String entityName;
        if (cell != null)
        {
          if (column.getAttributeName().equals(excelAttribute))
          {
            entityName = cell.getRichStringCellValue().getString().trim();

            if (entityName.length()>0)
            {
              geoEntityNames.add(entityName);
              parentGeoEntityMap.put(geoEntityClass.definesType(), entityName);
              
              endPointEntityName = entityName;
              endPointEntityType = geoEntityClass.definesType();
            }
          }
        }
      }
    }
    entityList = GeoEntitySearcher.search(false, parentGeoEntityMap, endPointEntityType, endPointEntityName);

    // The user may not specify a Geo Entity. If that is the case then the
    // endGeoEntityName will be "". If that is the case this should return
    // null instead of throwing an exception
    if (!endPointEntityName.equals("") && entityList.size() == 0)
    {
      String msg = "Unknown Geo Entity [" + endPointEntityName + "]";
      UnknownGeoEntityException e = new UnknownGeoEntityException(msg);
      e.setEntityName(endPointEntityName);
      e.apply();
      throw e;
    }

    if (entityList.size() > 1)
    {
      String msg = "Geo Entity ending with [" + endPointEntityName + "] is ambiguous (It has more than one possible solution)";
      AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(msg);
      e.setEntityName(endPointEntityName);
      e.apply();
      throw e;
    }

    // Now use reflection to set the value
    GeoEntity geoEntity = null;

    if (entityList.size() == 1)
    {
      geoEntity = entityList.get(0);
    }

    Class<?> excelClass = LoaderDecorator.load(excelType);

    String accessorName = GenerationUtil.upperFirstCharacter(instance.getMdAttributeDAO(attributeName).getAccessorName());
    excelClass.getMethod("set" + accessorName, GeoEntity.class).invoke(instance, geoEntity);
  }

  private String getExcelAttribute(MdBusiness geoEntityClass)
  {
    String geoTypeName = geoEntityClass.getTypeName();
    return PREFIX + this.attributeName + " " + geoTypeName;
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }
}
