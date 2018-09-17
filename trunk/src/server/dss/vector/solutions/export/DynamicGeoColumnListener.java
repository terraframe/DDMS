/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.HierarchyBuilder;

public class DynamicGeoColumnListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private String             attributeName;

  private String             excelType;

  private List<GeoHierarchy> hierarchyList;

  private ExcelImportManager importer;

  public static final String PREFIX = "Geo ";

  /**
   * 
   * 
   * @param excelType
   * @param attributeName
   * @param mainHierarchyBuilder
   * @param importer
   *          May be null, if used for an export.
   */
  public DynamicGeoColumnListener(String excelType, String attributeName, HierarchyBuilder mainHierarchyBuilder, ExcelImportManager importer)
  {
    this.attributeName = attributeName;
    this.excelType = excelType;
    this.hierarchyList = mainHierarchyBuilder.getHierarchy();
    this.importer = importer;
  }
  
  public List<GeoHierarchy> getHierarchyList()
  {
    return hierarchyList;
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {

    for (GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String geoLabel = geoEntityClass.getDisplayLabel().getValue();
      String geoAttribute = getExcelAttribute(geoEntityClass);

      extraColumns.add(new GeoExcelColumn(geoEntityClass.getId(), this.attributeName, geoAttribute, geoLabel));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    List<String> geoEntityNames = new ArrayList<String>(hierarchyList.size());

    String endPointEntityName = "";
    String endPointEntityType = "";

    Map<String, String> parentGeoEntityMap = new HashMap<String, String>();
    for (GeoHierarchy hierarchy : hierarchyList)
    {
      MdBusiness geoEntityClass = hierarchy.getGeoEntityClass();
      String excelAttribute = getExcelAttribute(geoEntityClass);

      // Go find the expected column
      for (ExcelColumn column : extraColumns)
      {
        String entityName;
        Cell cell = row.getCell(column.getIndex());
        if (cell != null)
        {
          if (column.getAttributeName().equals(excelAttribute))
          {
            entityName = ExcelUtil.getString(cell).trim();

            if (entityName.length() > 0)
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
    
    GeoEntity geoEntity = matchGeoEntity(parentGeoEntityMap, endPointEntityName, endPointEntityType, importer);

    // Now use reflection to set the value
    Class<?> excelClass = LoaderDecorator.load(excelType);
    MdAttributeReferenceDAOIF mdAttributeDAO = (MdAttributeReferenceDAOIF) instance.getMdAttributeDAO(attributeName).getMdAttributeConcrete();
    Class<?> parameterClass = LoaderDecorator.load(mdAttributeDAO.getReferenceMdBusinessDAO().definesType());
    String accessorName = CommonGenerationUtil.upperFirstCharacter(mdAttributeDAO.definesAttribute());
    excelClass.getMethod("set" + accessorName, parameterClass).invoke(instance, geoEntity);
  }
  
  /**
   * Generic matching logic for converting a universal name and a geo entity name to a reference. May return null.
   * 
   * @param parentGeoEntityMap Key is the geo type, value is the geo name.
   * 
   * @return
   */
  public static GeoEntity matchGeoEntity(Map<String, String> parentGeoEntityMap, String endPointEntityName, String endPointEntityType, ExcelImportManager importer)
  {
    List<GeoEntity> entityList = new LinkedList<GeoEntity>();
    GeoEntity geoEntity = null;
    
    entityList = GeoEntitySearcher.search(false, parentGeoEntityMap, endPointEntityType, endPointEntityName);

    // The user may not specify a Geo Entity. If that is the case then the
    // endGeoEntityName will be "". If that is the case this should return
    // null instead of throwing an exception
    if (!endPointEntityName.equals("") && entityList.size() == 0)
    {
      if (importer != null && !importer.isGeoEntityNameUnknown(endPointEntityName))
      {
        // Unable to find a match look up synonyms
        List<GeoEntity> synonymEntityList = GeoEntitySearcher.search(true, parentGeoEntityMap, endPointEntityType, endPointEntityName);

        List<GeoEntity> siblingGeoEntityList = GeoEntitySearcher.searchChildren(parentGeoEntityMap, endPointEntityType, synonymEntityList);

        UnknownGeoEntity unknownGeoEntity = new UnknownGeoEntity();
        unknownGeoEntity.setEntityType(MdBusiness.getMdBusiness(endPointEntityType).getDisplayLabel().getValue());
        unknownGeoEntity.setEntityName(endPointEntityName);
        unknownGeoEntity.setSynonyms(GeoEntitySearcher.getDelimitedList(synonymEntityList));
        unknownGeoEntity.setSiblings(GeoEntitySearcher.getDelimitedList(siblingGeoEntityList));
        unknownGeoEntity.setKnownHierarchy(GeoEntitySearcher.getDelimitedHierarchy(parentGeoEntityMap, endPointEntityType));
        unknownGeoEntity.apply();

        importer.addUnknownEntity(unknownGeoEntity);
        importer.addUnknownGeoEntityName(endPointEntityName);
        importer.putGeoTypeInfo(unknownGeoEntity, endPointEntityType);
      }

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

    if (entityList.size() == 1)
    {
      geoEntity = entityList.get(0);
    }
    
    return geoEntity;
  }

  private String getExcelAttribute(MdBusiness geoEntityClass)
  {
    String geoTypeName = geoEntityClass.getTypeName();
    return PREFIX + this.attributeName + " " + geoTypeName;
  }

  @Override
  public void onFinishImport()
  {
    if (importer != null)
    {
      importer.onFinishImport();
    }
  }
}
