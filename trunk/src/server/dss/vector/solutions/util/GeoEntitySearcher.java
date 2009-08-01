package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.business.Business;
import com.terraframe.mojo.business.BusinessQuery;
import com.terraframe.mojo.query.ColumnInfo;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoEntitySearcher
{
  private static class GeoHeaderInfo
  {
    private String excelColumnName;
    private int columnIndex;
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

  private String geoUniversalPackage;

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

    HSSFSheet sheet;
    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      sheet = workbook.getSheetAt(0);
    }
    catch (IOException e)
    {
      // If we've encountered an exception already, there's no point in proceeding
      throw new SystemException(e);
    }

    // list of potential synonym matches, if any
    List<UnknownGeoEntity> unknownEntityList = new LinkedList<UnknownGeoEntity>();

    Iterator<HSSFRow> rowIterator = sheet.rowIterator();

    // Parse the header rows, which builds up our list of ColumnInfos
    readHeaders(rowIterator);

    // Iterate over the geo attributes
    for (String attributeName : this.geoColumnInfoMap.keySet())
    {
      // Errors occured on this column.  Stop processing additional columns.
      if (unknownEntityList.size() > 0)
      {
        break;
      }

      List<GeoHeaderInfo> geoHeaderInfoList = this.geoColumnInfoMap.get(attributeName);

      GeoHeaderInfo[] geoHeaderInfoArray = new GeoHeaderInfo[geoHeaderInfoList.size()];
      geoHeaderInfoList.toArray(geoHeaderInfoArray);

      for (int i=0; i<geoHeaderInfoArray.length; i++)
      {
        // Errors occured on this column.  Stop processing additional columns.
        if (unknownEntityList.size() > 0)
        {
          break;
        }

        rowIterator = getRowIteratorAdvancedToContent(sheet);
        while (rowIterator.hasNext())
        {
          HSSFRow contentRow = rowIterator.next();

          String endPointEntityName = "";
          String endPointEntityType = "";
          Map<String, String> parentGeoEntityMap = new LinkedHashMap<String, String>();

          for (int j=0; j<=i; j++)
          {
            GeoHeaderInfo geoHeaderInfo = geoHeaderInfoArray[j];

            int columnIndex = geoHeaderInfo.getColumnIndex();

            HSSFCell cell = contentRow.getCell(columnIndex);

            if (cell != null)
            {
              String geoEntityName = cell.getRichStringCellValue().getString();

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
              // Unable to find a match
              // Look up synonyms
              List<GeoEntity> possibleMatchEntityList = GeoEntitySearcher.search(true, parentGeoEntityMap, endPointEntityType, endPointEntityName);

              // Create a delimited list of synonyms
              String delimitedSynonymList = "";
              boolean firstIteration = true;
              for (GeoEntity synonymEntity: possibleMatchEntityList)
              {
                if (!firstIteration)
                {
                  delimitedSynonymList+=":";
                }
                else
                {
                  firstIteration = false;
                }

                delimitedSynonymList+=synonymEntity.getGeoId()+";"+synonymEntity.getEntityName();
              }

              List<GeoEntity> siblingGeoEntityList = GeoEntitySearcher.searchChildren(parentGeoEntityMap, endPointEntityType, possibleMatchEntityList);

              // Create a delimited list of childeren of the same type from the same parent
              String delimitedSiblingList = "";
              firstIteration = true;
              for (GeoEntity siblingEntity: siblingGeoEntityList)
              {
                if (!firstIteration)
                {
                  delimitedSiblingList+=":";
                }
                else
                {
                  firstIteration = false;
                }

                delimitedSiblingList+=siblingEntity.getGeoId()+";"+siblingEntity.getEntityName();
              }

              String knownHierarchy = "";
              firstIteration = true;
              for (String geoType : parentGeoEntityMap.keySet())
              {
                if (geoType != endPointEntityType)
                {
                  if (!firstIteration)
                  {
                    knownHierarchy+=", ";
                  }
                  else
                  {
                    firstIteration = false;
                  }

                  String entityName = parentGeoEntityMap.get(geoType);
                  String geoTypeDisplayLabel = MdBusiness.getMdBusiness(geoType).getDisplayLabel().getValue();
                  knownHierarchy += entityName +" ("+geoTypeDisplayLabel+")";
                }
              }


              UnknownGeoEntity unknownGeoEntity = new UnknownGeoEntity();
              unknownGeoEntity.setEntityType(endPointEntityType);
              unknownGeoEntity.setEntityName(endPointEntityName);
              unknownGeoEntity.setSynonyms(delimitedSynonymList);
              unknownGeoEntity.setKnownHierarchy(knownHierarchy);
              unknownGeoEntity.setSiblings(delimitedSiblingList);
              unknownGeoEntity.applyNoPersist();

              unknownEntityList.add(unknownGeoEntity);

            }
            else if (geoEntityList.size() == 1)
            {
              // do nothing.  We found an exact match, which is what we wanted
            }
            else // geoEntityList.size() > 1
            {
              String msg = "Geo Entity ending with [" + endPointEntityName + "] is ambiguous (It has more than one possible solution)";
              AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(msg);
              e.setEntityName(endPointEntityName);
              e.apply();
              throw e;
            }

          } // if (!endPointEntityName.trim().equals(""))

        } // while (rowIterator.hasNext())

      } // for (int i=0; i<geoHeaderInfoArray.length; i++)

    } // for (String attributeName : this.geoColumnInfoMap.keySet())

    return unknownEntityList;
  }

  /**
   * Returns a row iterator that is advanced passed the headers to the first row of content.
   * @return iterator that is advanced passed the headers to the first row of content.
   */
  @SuppressWarnings("unchecked")
  private Iterator<HSSFRow> getRowIteratorAdvancedToContent(HSSFSheet sheet)
  {
    // Open the stream
    Iterator<HSSFRow> rowIterator = sheet.rowIterator();
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
  @SuppressWarnings("unchecked")
  private void readHeaders(Iterator<HSSFRow> rowIterator)
  {
    // Ignore type row
    rowIterator.next();
    HSSFRow nameRow = rowIterator.next();
    // Ignore label row
    rowIterator.next();

    Iterator<HSSFCell> nameIterator = nameRow.cellIterator();

    while (nameIterator.hasNext())
    {
      HSSFCell nameCell = nameIterator.next();

      String nameValue = nameCell.getRichStringCellValue().getString();
      if (nameValue.startsWith(DynamicGeoColumnListener.PREFIX))
      {
        String[] nameComponents = nameValue.split(" ");
        String attributeName = nameComponents[1];
        String geoTypeName = nameComponents[2];


        // Record information about this column header
        GeoHeaderInfo geoHeaderInfo = new GeoHeaderInfo(nameValue, nameCell.getColumnIndex(), attributeName, this.geoUniversalPackage+"."+geoTypeName);
        this.getGeoHeaderInfoList(attributeName).add(geoHeaderInfo);
      }
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
   * @param soundsLikeMatch if true then a sounds like match will be made with the given child entity name.
   *   if false, then an exact string match will be used.
   * @param parentGeoEntityMap Key is the geo type, value is the geo name.
   * @param childGeoEntityType geo entity type of the child.
   * @param childGeoEntityName geo entity name of the child.
   */
  public static List<GeoEntity> search(boolean soundsLikeMatch, Map<String, String> parentGeoEntityMap, String childGeoEntityType, String childGeoEntityName)
  {
    MdBusiness childMdBusiness = MdBusiness.getMdBusiness(childGeoEntityType);

    QueryFactory qf = new QueryFactory();

    ValueQuery geoEntityIdQuery = new ValueQuery(qf);

    GeoEntityQuery childGeoEntityQuery = new GeoEntityQuery(qf);

    geoEntityIdQuery.SELECT(childGeoEntityQuery.getId("child_id"));

    if (soundsLikeMatch)
    {
      geoEntityIdQuery.
      WHERE(geoEntityIdQuery.aSQLCharacter("entityMetaphone", "metaphone("+childGeoEntityQuery.getEntityName().getQualifiedName()+", 255)", "entityMetaphone").
          EQ(geoEntityIdQuery.aSQLCharacter("unknownMetaphone", "metaphone('"+childGeoEntityName+"', 255)", "unknownMetaphone")));
    }
    else
    {
      GeoSynonymQuery geoSynonymQuery = new GeoSynonymQuery(qf);
      geoSynonymQuery.WHERE(geoSynonymQuery.getEntityName().EQ(childGeoEntityName));

      geoEntityIdQuery.
      WHERE(
          OR.get(childGeoEntityQuery.getEntityName().EQ(childGeoEntityName),
          childGeoEntityQuery.synonyms(geoSynonymQuery)));
    }

    for (String parentEntityType : parentGeoEntityMap.keySet())
    {
      if (parentEntityType.equals(childGeoEntityType))
      {
        continue;
      }

      AllPathsQuery allPathsQuery = new AllPathsQuery(qf);
      MdBusiness parentMdBusiness = MdBusiness.getMdBusiness(parentEntityType);

      GeoEntityQuery parentGeoEntityQuery = new GeoEntityQuery(qf);

      parentGeoEntityQuery.
      WHERE(parentGeoEntityQuery.getEntityName().EQ(parentGeoEntityMap.get(parentEntityType)));

      geoEntityIdQuery.
        AND(allPathsQuery.getParentUniversal().EQ(parentMdBusiness).
        AND(allPathsQuery.getParentGeoEntity().EQ(parentGeoEntityQuery.getId())).
        AND(allPathsQuery.getChildUniversal().EQ(childMdBusiness)).
        AND(allPathsQuery.getChildGeoEntity().EQ(childGeoEntityQuery.getId())));
    }


    BusinessQuery resultQuery = qf.businessQuery(childGeoEntityType);
    resultQuery.WHERE(resultQuery.IN(resultQuery.id(), geoEntityIdQuery));
    resultQuery.ORDER_BY_ASC(F.UPPER(resultQuery.aCharacter(GeoEntity.ENTITYNAME)));

    OIterator<Business> iterator = resultQuery.getIterator();

    List<GeoEntity> returnGeoEntityList = new LinkedList<GeoEntity>();

    try
    {
      for (Business business : iterator)
      {
        returnGeoEntityList.add((GeoEntity)business);
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
   * @param soundsLikeMatch if true then a sounds like match will be made with the given child entity name.
   *   if false, then an exact string match will be used.
   * @param parentGeoEntityMap Key is the geo type, value is the geo name.
   * @param childGeoEntityType geo entity type of the child.
   * @param excludeGeoEntityList geo entities to exclude from the result set.
   */
  public static List<GeoEntity> searchChildren(Map<String, String> parentGeoEntityMap, String childGeoEntityType, List<GeoEntity> excludeGeoEntityList)
  {
    MdBusiness childMdBusiness = MdBusiness.getMdBusiness(childGeoEntityType);

    QueryFactory qf = new QueryFactory();

    ValueQuery geoEntityIdQuery = new ValueQuery(qf);

    GeoEntityQuery childGeoEntityQuery = new GeoEntityQuery(qf);


    String[] idArray = new String[excludeGeoEntityList.size()];

    int i = 0;
    for (GeoEntity geoEntity : excludeGeoEntityList)
    {
      idArray[i] = geoEntity.getId();
      i++;
    }

    childGeoEntityQuery.WHERE(childGeoEntityQuery.getId().NI(idArray));

    geoEntityIdQuery.SELECT(childGeoEntityQuery.getId("child_id"));

    for (String parentEntityType : parentGeoEntityMap.keySet())
    {
      if (parentEntityType.equals(childGeoEntityType))
      {
        continue;
      }

      AllPathsQuery allPathsQuery = new AllPathsQuery(qf);
      MdBusiness parentMdBusiness = MdBusiness.getMdBusiness(parentEntityType);

      GeoEntityQuery parentGeoEntityQuery = new GeoEntityQuery(qf);

      parentGeoEntityQuery.
      WHERE(parentGeoEntityQuery.getEntityName().EQ(parentGeoEntityMap.get(parentEntityType)));

      geoEntityIdQuery.
        AND(allPathsQuery.getParentUniversal().EQ(parentMdBusiness).
        AND(allPathsQuery.getParentGeoEntity().EQ(parentGeoEntityQuery.getId())).
        AND(allPathsQuery.getChildUniversal().EQ(childMdBusiness)).
        AND(allPathsQuery.getChildGeoEntity().EQ(childGeoEntityQuery.getId())));
    }


    BusinessQuery resultQuery = qf.businessQuery(childGeoEntityType);
    resultQuery.WHERE(resultQuery.IN(resultQuery.id(), geoEntityIdQuery));
    resultQuery.ORDER_BY_ASC(F.UPPER(resultQuery.aCharacter(GeoEntity.ENTITYNAME)));

    OIterator<Business> iterator = resultQuery.getIterator();

    List<GeoEntity> returnGeoEntityList = new LinkedList<GeoEntity>();

    try
    {
      for (Business business : iterator)
      {
        returnGeoEntityList.add((GeoEntity)business);
      }
    }
    finally
    {
      iterator.close();
    }
    return returnGeoEntityList;
  }

}
