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
package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.ColumnInfo;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityEntityLabelQuery;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.UnknownTerm;

public class TermSearcher implements Reloadable
{
  private static class TermHeaderInfo implements Reloadable
  {
    private int                       columnIndex;

    private MdAttributeReferenceDAOIF mdAttribute;

    private Set<String>               unknownTerms;

    private TermHeaderInfo(int columnIndex, MdAttributeReferenceDAOIF mdAttribute)
    {
      this.columnIndex = columnIndex;
      this.mdAttribute = mdAttribute;

      this.unknownTerms = new TreeSet<String>();
    }

    public int getColumnIndex()
    {
      return this.columnIndex;
    }

    public MdAttributeReferenceDAOIF getMdAttribute()
    {
      return this.mdAttribute;
    }

    public void addUnknownTerm(String termName)
    {
      this.unknownTerms.add(termName);
    }

    public boolean hasUnknownTerm(String termName)
    {
      return this.unknownTerms.contains(termName);
    }

  }

  private Map<String, List<TermHeaderInfo>> columnInfoMap;

  public TermSearcher()
  {
    super();

    this.init();
  }

  private void init()
  {
    this.columnInfoMap = new HashMap<String, List<TermHeaderInfo>>();
  }

  public List<UnknownTerm> checkExcelTerms(InputStream inputStream)
  {
    this.init();

    // list of potential synonym matches, if any
    List<UnknownTerm> unknownTerms = new LinkedList<UnknownTerm>();

    try
    {
      Workbook workbook = WorkbookFactory.create(inputStream);

      int numberOfSheets = workbook.getNumberOfSheets();

      for (int sheetNumber = 0; sheetNumber < numberOfSheets; sheetNumber++)
      {
        this.columnInfoMap.clear();

        Sheet sheet = workbook.getSheetAt(sheetNumber);

        if (this.isValidSheet(sheet))
        {
          Iterator<Row> rowIterator = sheet.rowIterator();

          // Parse the header rows, which builds up our list of ColumnInfos
          readHeaders(rowIterator);

          // Iterate over the geo attributes
          for (String attributeName : this.columnInfoMap.keySet())
          {
            // Errors occured on this column. Stop processing additional
            // columns.
            if (unknownTerms.size() > 0)
            {
              break;
            }

            List<TermHeaderInfo> headerInfoList = this.columnInfoMap.get(attributeName);
            TermHeaderInfo[] headerInfoArray = headerInfoList.toArray(new TermHeaderInfo[headerInfoList.size()]);

            for (int i = 0; i < headerInfoArray.length; i++)
            {
              // Errors occured on this column. Stop processing additional
              // columns.
              if (unknownTerms.size() > 0)
              {
                break;
              }

              rowIterator = getRowIteratorAdvancedToContent(sheet);
              while (rowIterator.hasNext())
              {
                Row contentRow = rowIterator.next();

                for (int j = 0; j <= i; j++)
                {
                  TermHeaderInfo headerInfo = headerInfoArray[j];

                  int columnIndex = headerInfo.getColumnIndex();

                  Cell cell = contentRow.getCell(columnIndex);

                  if (cell != null)
                  {
                    String termName = ExcelUtil.getString(cell);

                    if (!termName.trim().equals(""))
                    {
                      MdAttributeReferenceDAOIF mdAttribute = headerInfo.getMdAttribute();
                      Term term = Term.findByDisplayLabel(termName, mdAttribute);

                      if (term == null && !headerInfo.hasUnknownTerm(termName))
                      {
                        MdClassDAOIF mdClass = mdAttribute.definedByClass();

                        UnknownTerm unknownTerm = new UnknownTerm();
                        unknownTerm.setTermName(termName);
                        unknownTerm.setBrowserAttribute(mdAttribute.definesAttribute());
                        unknownTerm.setBrowserClass(mdClass.definesType());
                        unknownTerm.setAttributeLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
                        unknownTerm.applyNoPersist();

                        unknownTerms.add(unknownTerm);

                        headerInfo.addUnknownTerm(termName);
                      }
                    }
                  }
                } // for (int j=0; j<=i; j++)

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
    catch (InvalidFormatException e)
    {
      throw new ExcelVersionException(e);
    }

    return unknownTerms;
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
      Row typeRow = rowIterator.next();
      Cell typeCell = typeRow.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

      if (typeCell != null)
      {
        String type = typeCell.getStringCellValue();

        MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);

        Row nameRow = rowIterator.next();
        // Ignore label row
        rowIterator.next();

        Iterator<Cell> nameIterator = nameRow.cellIterator();

        while (nameIterator.hasNext())
        {
          Cell nameCell = nameIterator.next();

          String nameValue = ExcelUtil.getString(nameCell).trim();

          if (!nameValue.startsWith(DynamicGeoColumnListener.PREFIX))
          {
            MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(nameValue).getMdAttributeConcrete();

            if (mdAttribute instanceof MdAttributeReferenceDAOIF)
            {
              MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
              MdBusinessDAOIF referenceMdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

              if (referenceMdBusiness.definesType().equals(Term.CLASS))
              {
                // Record information about this column header
                TermHeaderInfo headerInfo = new TermHeaderInfo(nameCell.getColumnIndex(), mdAttributeReference);

                this.getHeaderInfoList(mdAttribute.definesAttribute()).add(headerInfo);
              }
            }
          }
        }
      }
    }
    catch (NoSuchElementException e)
    {
      throw new ExcelReadException(e);
    }
  }

  private List<TermHeaderInfo> getHeaderInfoList(String attributeName)
  {
    List<TermHeaderInfo> headerInfoList = this.columnInfoMap.get(attributeName);

    if (headerInfoList == null)
    {
      headerInfoList = new LinkedList<TermHeaderInfo>();

      this.columnInfoMap.put(attributeName, headerInfoList);
    }

    return headerInfoList;
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
       * A sql pass through character for the levenshtein of existing entity
       * labels
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
