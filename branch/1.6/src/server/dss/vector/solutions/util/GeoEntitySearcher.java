package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ColumnInfo;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityEntityLabelQuery;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoEntitySearcher implements Reloadable
{
  private static class GeoHeaderInfo implements Reloadable
  {
    private String excelColumnName;

    private int    columnIndex;

    private String attributeName;

    private String geoType;

    private GeoHeaderInfo(String excelColumnName, int columnIndex, String attributeName, String geoType)
    {
      this.excelColumnName = excelColumnName;
      this.columnIndex = columnIndex;
      this.attributeName = attributeName;
      this.geoType = geoType;
    }

    public String getExcelColumnName()
    {
      return this.excelColumnName;
    }

    public int getColumnIndex()
    {
      return this.columnIndex;
    }

    public String getAttributeName()
    {
      return this.attributeName;
    }

    public String getGeoType()
    {
      return this.geoType;
    }
  }

  private Map<String, List<GeoHeaderInfo>> geoColumnInfoMap;

  private String                           geoUniversalPackage;

  public GeoEntitySearcher()
  {
    super();

    this.init();

    String geoEntityClass = GeoEntity.CLASS;
    this.geoUniversalPackage = geoEntityClass.substring(0, geoEntityClass.lastIndexOf("."));
  }

  private void init()
  {
    this.geoColumnInfoMap = new HashMap<String, List<GeoHeaderInfo>>();
  }

  @SuppressWarnings("unchecked")
  public List<UnknownGeoEntity> checkExcelGeoHierarchy(InputStream inputStream)
  {
    this.init();

    // list of potential synonym matches, if any
    List<UnknownGeoEntity> unknownEntityList = new LinkedList<UnknownGeoEntity>();
    Set<String> unknownGeoEntityNameSet = new HashSet<String>();

    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);

      int numberOfSheets = workbook.getNumberOfSheets();

      for (int sheetNumber = 0; sheetNumber < numberOfSheets; sheetNumber++)
      {
        this.geoColumnInfoMap.clear();

        Sheet sheet = workbook.getSheetAt(sheetNumber);

        if (this.isValidSheet(sheet))
        {
          Iterator<Row> rowIterator = sheet.rowIterator();

          // Parse the header rows, which builds up our list of ColumnInfos
          readHeaders(rowIterator);

          // Iterate over the geo attributes
          for (String attributeName : this.geoColumnInfoMap.keySet())
          {
            // Errors occured on this column. Stop processing additional
            // columns.
            if (unknownEntityList.size() > 0)
            {
              break;
            }

            List<GeoHeaderInfo> geoHeaderInfoList = this.geoColumnInfoMap.get(attributeName);

            GeoHeaderInfo[] geoHeaderInfoArray = new GeoHeaderInfo[geoHeaderInfoList.size()];
            geoHeaderInfoList.toArray(geoHeaderInfoArray);

            for (int i = 0; i < geoHeaderInfoArray.length; i++)
            {
              // Errors occured on this column. Stop processing additional
              // columns.
              if (unknownEntityList.size() > 0)
              {
                break;
              }

              rowIterator = getRowIteratorAdvancedToContent(sheet);
              while (rowIterator.hasNext())
              {
                Row contentRow = rowIterator.next();

                String endPointEntityName = "";
                String endPointEntityType = "";
                Map<String, String> parentGeoEntityMap = new LinkedHashMap<String, String>();

                for (int j = 0; j <= i; j++)
                {
                  GeoHeaderInfo geoHeaderInfo = geoHeaderInfoArray[j];

                  int columnIndex = geoHeaderInfo.getColumnIndex();

                  Cell cell = contentRow.getCell(columnIndex);

                  if (cell != null)
                  {
                    String geoEntityName = ExcelUtil.getString(cell);

                    if (!geoEntityName.trim().equals(""))
                    {
                      String geoType = geoHeaderInfo.getGeoType();
                      if (j == i)
                      {
                        endPointEntityType = geoType;
                        endPointEntityName = geoEntityName;
                      }
                      else
                      {
                        parentGeoEntityMap.put(geoType, geoEntityName);
                      }
                    }
                  }
                } // for (int j=0; j<=i; j++)

                List<GeoEntity> geoEntityList = null;

                if (!endPointEntityName.trim().equals(""))
                {
                  geoEntityList = GeoEntitySearcher.search(false, parentGeoEntityMap, endPointEntityType, endPointEntityName);

                  if (geoEntityList.size() == 0)
                  {
                    if (unknownGeoEntityNameSet.contains(endPointEntityName))
                    {
                      continue;
                    }

                    // Unable to find a match look up synonyms
                    List<GeoEntity> synonymEntityList = GeoEntitySearcher.search(true, parentGeoEntityMap, endPointEntityType, endPointEntityName);

                    List<GeoEntity> siblingGeoEntityList = GeoEntitySearcher.searchChildren(parentGeoEntityMap, endPointEntityType, synonymEntityList);

                    UnknownGeoEntity unknownGeoEntity = new UnknownGeoEntity();
                    unknownGeoEntity.setEntityType(MdBusiness.getMdBusiness(endPointEntityType).getDisplayLabel().getValue());
                    unknownGeoEntity.setEntityName(endPointEntityName);
                    unknownGeoEntity.setSynonyms(this.getDelimitedList(synonymEntityList));
                    unknownGeoEntity.setSiblings(this.getDelimitedList(siblingGeoEntityList));
                    unknownGeoEntity.setKnownHierarchy(this.getDelimitedHierarchy(parentGeoEntityMap, endPointEntityType));
                    unknownGeoEntity.applyNoPersist();

                    unknownEntityList.add(unknownGeoEntity);
                    unknownGeoEntityNameSet.add(endPointEntityName);

                  }
                  else if (geoEntityList.size() == 1)
                  {
                    // do nothing. We found an exact match, which is what we
                    // wanted
                  }
                } // if (!endPointEntityName.trim().equals(""))

              } // while (rowIterator.hasNext())

            } // for (int i=0; i<geoHeaderInfoArray.length; i++)

          } // for (String attributeName : this.geoColumnInfoMap.keySet())
        }
      }
    }
    catch (OfficeXmlFileException e)
    {
      throw new ExcelVersionException(e);
    }
    catch (IOException e)
    {
      // If we've encountered an exception already, there's no point in
      // proceeding
      throw new ExcelReadException(e);
    }
    catch (RuntimeException e)
    {
      if (e.getMessage().contains("Expected an EXTERNSHEET"))
      {
        throw new ExcelReadException(e);
      }
      else
      {
        throw e;
      }
    }

    return unknownEntityList;
  }

  private boolean isValidSheet(Sheet sheet)
  {
    try
    {
      Row row = sheet.getRow(0);

      if (row != null)
      {
        Cell cell = row.getCell(0);
        String type = ExcelUtil.getString(cell);

        return ( type != null && MdTypeDAO.getMdTypeDAO(type) != null );
      }
    }
    catch (Exception e)
    {
      return false;
    }

    return false;
  }

  public static String getDelimitedList(List<GeoEntity> entities)
  {
    StringBuffer buffer = new StringBuffer();
    boolean firstIteration = true;
    for (GeoEntity entity : entities)
    {
      if (!firstIteration)
      {
        buffer.append(":");
      }
      else
      {
        firstIteration = false;
      }

      buffer.append(entity.getGeoId() + ";" + entity.getEntityLabel().getValue());
    }

    return buffer.toString();
  }

  public static String getDelimitedHierarchy(Map<String, String> parentGeoEntityMap, String endPointEntityType)
  {
    StringBuffer buffer = new StringBuffer();

    boolean firstIteration = true;
    for (String geoType : parentGeoEntityMap.keySet())
    {
      if (geoType != endPointEntityType)
      {
        if (!firstIteration)
        {
          buffer.append(", ");
        }
        else
        {
          firstIteration = false;
        }

        String entityName = parentGeoEntityMap.get(geoType);
        String geoTypeDisplayLabel = MdBusiness.getMdBusiness(geoType).getDisplayLabel().getValue();
        buffer.append(entityName + " (" + geoTypeDisplayLabel + ")");
      }
    }

    return buffer.toString();
  }

  /**
   * Returns a row iterator that is advanced passed the headers to the first row
   * of content.
   * 
   * @return iterator that is advanced passed the headers to the first row of
   *         content.
   */
  private Iterator<Row> getRowIteratorAdvancedToContent(Sheet sheet)
  {
    // Open the stream
    Iterator<Row> rowIterator = sheet.rowIterator();
    // Ignore type row
    rowIterator.next();
    // Ignore the attribute name row
    rowIterator.next();
    // Ignore label row
    rowIterator.next();

    return rowIterator;
  }

  /**
   * Reads the first two rows, which represent the attribute names and attribute
   * display labels respectively. Creates the list of {@link ColumnInfo}s that
   * is referenced when importing row data.
   * 
   * @param rowIterator
   */
  private void readHeaders(Iterator<Row> rowIterator)
  {
    // Ignore type row
    try
    {
      rowIterator.next();
      Row nameRow = rowIterator.next();
      // Ignore label row
      rowIterator.next();

      Iterator<Cell> nameIterator = nameRow.cellIterator();

      while (nameIterator.hasNext())
      {
        Cell nameCell = nameIterator.next();

        String nameValue = ExcelUtil.getString(nameCell).trim();
        if (nameValue.startsWith(DynamicGeoColumnListener.PREFIX))
        {
          String[] nameComponents = nameValue.split(" ");
          String attributeName = nameComponents[1];
          String geoTypeName = nameComponents[2];

          // Record information about this column header
          GeoHeaderInfo geoHeaderInfo = new GeoHeaderInfo(nameValue, nameCell.getColumnIndex(), attributeName, this.geoUniversalPackage + "." + geoTypeName);
          this.getGeoHeaderInfoList(attributeName).add(geoHeaderInfo);
        }
      }
    }
    catch (NoSuchElementException e)
    {
      throw new ExcelReadException(e);
    }
  }

  private List<GeoHeaderInfo> getGeoHeaderInfoList(String attributeName)
  {
    List<GeoHeaderInfo> geoHeaderInfoList = this.geoColumnInfoMap.get(attributeName);

    if (geoHeaderInfoList == null)
    {
      geoHeaderInfoList = new LinkedList<GeoHeaderInfo>();
      this.geoColumnInfoMap.put(attributeName, geoHeaderInfoList);
    }

    return geoHeaderInfoList;
  }

  /**
   * Finds a geo entity match.
   * 
   * @param soundsLikeMatch
   *          if true then a sounds like match will be made with the given child
   *          entity name. if false, then an exact string match will be used.
   * @param parentGeoEntityMap
   *          Key is the geo type, value is the geo name.
   * @param childGeoEntityType
   *          geo entity type of the child.
   * @param childGeoEntityName
   *          geo entity name of the child.
   */
  public static List<GeoEntity> search(boolean soundsLikeMatch, Map<String, String> parentGeoEntityMap, String childGeoEntityType, String childGeoEntityName)
  {
    if (childGeoEntityType.equals("") && childGeoEntityName.equals(""))
    {
      return new LinkedList<GeoEntity>();
    }

    MdBusiness childMdBusiness = MdBusiness.getMdBusiness(childGeoEntityType);

    QueryFactory qf = new QueryFactory();

    ValueQuery geoEntityIdQuery = new ValueQuery(qf);

    GeoEntityQuery childGeoEntityQuery = new GeoEntityQuery(qf);

    if (soundsLikeMatch)
    {
      GeoEntityEntityLabelQuery labelQuery = new GeoEntityEntityLabelQuery(qf);
      String qualifiedName = childGeoEntityQuery.getEntityLabel().localize().getDbQualifiedName();

      Integer max = 4;

      /*
       *  A sql pass through character for the levenshtein of existing entity labels
       */
      SelectableSQLInteger entityMetaphone = geoEntityIdQuery.aSQLInteger("entityMetaphone", "levenshtein_less_equal(" + qualifiedName + ", '" + childGeoEntityName + "', " + max + ")", "entityMetaphone");

      geoEntityIdQuery.WHERE(childGeoEntityQuery.getEntityLabel().getId().EQ(labelQuery.getId()));
      geoEntityIdQuery.AND(entityMetaphone.LT(max));
    }
    else
    {
      GeoSynonymQuery geoSynonymQuery = new GeoSynonymQuery(qf);
      geoSynonymQuery.WHERE(geoSynonymQuery.getEntityName().EQi(childGeoEntityName));

      geoEntityIdQuery.WHERE(OR.get(
      // Check for a name match
          childGeoEntityQuery.getEntityLabel().localize().EQi(childGeoEntityName),
          // OR check for a GeoId Match
          childGeoEntityQuery.getGeoId().EQ(childGeoEntityName),
          // Or check for a synonym match
          childGeoEntityQuery.synonyms(geoSynonymQuery)));
    }

    // This select clause must be located here and not above the if block,
    // otherwise the
    // joins above will not be proper subselects
    geoEntityIdQuery.SELECT(childGeoEntityQuery.getId("child_id", "child_id"));

    for (String parentEntityType : parentGeoEntityMap.keySet())
    {
      if (parentEntityType.equals(childGeoEntityType))
      {
        continue;
      }

      AllPathsQuery allPathsQuery = new AllPathsQuery(qf);
      MdBusiness parentMdBusiness = MdBusiness.getMdBusiness(parentEntityType);

      String parentGeoEntityName = parentGeoEntityMap.get(parentEntityType);

      GeoSynonymQuery geoSynonymQuery = new GeoSynonymQuery(qf);
      geoSynonymQuery.WHERE(geoSynonymQuery.getEntityName().EQi(parentGeoEntityName));

      GeoEntityQuery parentGeoEntityQuery = new GeoEntityQuery(qf);

      parentGeoEntityQuery.WHERE(OR.get(parentGeoEntityQuery.getEntityLabel().localize().EQi(parentGeoEntityName), parentGeoEntityQuery.getGeoId().EQi(parentGeoEntityName), parentGeoEntityQuery.synonyms(geoSynonymQuery)));

      geoEntityIdQuery.AND(allPathsQuery.getParentUniversal().EQ(parentMdBusiness).AND(allPathsQuery.getParentGeoEntity().EQ(parentGeoEntityQuery.getId())).AND(allPathsQuery.getChildUniversal().EQ(childMdBusiness)).AND(allPathsQuery.getChildGeoEntity().EQ(childGeoEntityQuery.getId())));
    }

    BusinessQuery resultQuery = qf.businessQuery(childGeoEntityType);
    resultQuery.WHERE(resultQuery.id().SUBSELECT_IN(geoEntityIdQuery.get("child_id")));
    resultQuery.ORDER_BY_ASC(F.UPPER(resultQuery.aLocalCharacter(GeoEntity.ENTITYLABEL).localize()));

    OIterator<Business> iterator = resultQuery.getIterator();

    List<GeoEntity> returnGeoEntityList = new LinkedList<GeoEntity>();

    try
    {
      for (Business business : iterator)
      {
        returnGeoEntityList.add((GeoEntity) business);
      }
    }
    finally
    {
      iterator.close();
    }
    return returnGeoEntityList;
  }

  /**
   * Finds a geo entity match for all children of the given type.
   * 
   * @param soundsLikeMatch
   *          if true then a sounds like match will be made with the given child
   *          entity name. if false, then an exact string match will be used.
   * @param parentGeoEntityMap
   *          Key is the geo type, value is the geo name.
   * @param childGeoEntityType
   *          geo entity type of the child.
   * @param excludeGeoEntityList
   *          geo entities to exclude from the result set.
   */
  public static List<GeoEntity> searchChildren(Map<String, String> parentGeoEntityMap, String childGeoEntityType, List<GeoEntity> excludeGeoEntityList)
  {
    MdBusiness childMdBusiness = MdBusiness.getMdBusiness(childGeoEntityType);

    QueryFactory qf = new QueryFactory();

    ValueQuery geoEntityIdQuery = new ValueQuery(qf);

    GeoEntityQuery childGeoEntityQuery = new GeoEntityQuery(qf);

    if (excludeGeoEntityList.size() > 0)
    {
      String[] idArray = new String[excludeGeoEntityList.size()];

      int i = 0;
      for (GeoEntity geoEntity : excludeGeoEntityList)
      {
        idArray[i] = geoEntity.getId();
        i++;
      }

      childGeoEntityQuery.WHERE(childGeoEntityQuery.getId().NI(idArray));
    }

    geoEntityIdQuery.SELECT(childGeoEntityQuery.getId("child_id", "child_id"));

    for (String parentEntityType : parentGeoEntityMap.keySet())
    {
      if (parentEntityType.equals(childGeoEntityType))
      {
        continue;
      }

      String parentGeoEntityName = parentGeoEntityMap.get(parentEntityType);
      MdBusiness parentMdBusiness = MdBusiness.getMdBusiness(parentEntityType);

      AllPathsQuery allPathsQuery = new AllPathsQuery(qf);
      GeoSynonymQuery geoSynonymQuery = new GeoSynonymQuery(qf);
      GeoEntityQuery parentGeoEntityQuery = new GeoEntityQuery(qf);

      geoSynonymQuery.WHERE(geoSynonymQuery.getEntityName().EQ(parentGeoEntityName));
      parentGeoEntityQuery.WHERE(OR.get(parentGeoEntityQuery.getEntityLabel().localize().EQ(parentGeoEntityName), parentGeoEntityQuery.synonyms(geoSynonymQuery)));
      geoEntityIdQuery.AND(allPathsQuery.getParentUniversal().EQ(parentMdBusiness).AND(allPathsQuery.getParentGeoEntity().EQ(parentGeoEntityQuery.getId())).AND(allPathsQuery.getChildUniversal().EQ(childMdBusiness)).AND(allPathsQuery.getChildGeoEntity().EQ(childGeoEntityQuery.getId())));
    }

    BusinessQuery resultQuery = qf.businessQuery(childGeoEntityType);
    resultQuery.WHERE(resultQuery.id().SUBSELECT_IN(geoEntityIdQuery.get("child_id")));

    resultQuery.ORDER_BY_ASC(F.UPPER(resultQuery.aLocalCharacter(GeoEntity.ENTITYLABEL).localize()));

    OIterator<Business> iterator = resultQuery.getIterator();

    List<GeoEntity> returnGeoEntityList = new LinkedList<GeoEntity>();

    try
    {
      for (Business business : iterator)
      {
        returnGeoEntityList.add((GeoEntity) business);
      }
    }
    finally
    {
      iterator.close();
    }
    return returnGeoEntityList;
  }

}
