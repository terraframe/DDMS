package dss.vector.solutions.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.SystemException;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdViewInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdClassQuery;
import com.runwaysdk.system.metadata.MdElementQuery;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.export.AggregatedCaseReferralsExcelView;
import dss.vector.solutions.export.AggregatedCaseTreatmentsExcelView;
import dss.vector.solutions.export.AggregatedPremiseExcelView;
import dss.vector.solutions.export.CaseDiagnosisTypeExcelView;
import dss.vector.solutions.export.CaseDiseaseManifestationExcelView;
import dss.vector.solutions.export.CasePatientTypeExcelView;
import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.export.GeoExporter;
import dss.vector.solutions.export.IndividualPremiseExcelView;
import dss.vector.solutions.export.InsecticideInterventionExcelView;
import dss.vector.solutions.export.PersonInterventionExcelView;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.ontology.UnknownTerm;

public abstract class Facade extends FacadeBase implements Reloadable
{
  private static final long serialVersionUID = 1236374191440L;

  public Facade()
  {
    super();
  }

  public static MdClassQuery getMDSSClasses()
  {
    QueryFactory queryFactory = new QueryFactory();
    MdElementQuery elementQuery = new MdElementQuery(queryFactory);
    elementQuery.WHERE(elementQuery.getIsAbstract().EQ(true));

    ValueQuery valueQuery = queryFactory.valueQuery();
    valueQuery.SELECT(elementQuery.getId("id"));
    valueQuery.WHERE(elementQuery.getIsAbstract().EQ(true));

    MdClassQuery classQuery = new MdClassQuery(queryFactory);
    classQuery.WHERE(classQuery.getPackageName().LIKEi("dss.vector.solutions.%"));
    Condition or = OR.get(classQuery.getType().EQ(MdBusinessInfo.CLASS), classQuery.getType().EQ(MdViewInfo.CLASS));
    classQuery.WHERE(or);
    classQuery.WHERE(classQuery.getId().SUBSELECT_NOT_IN(valueQuery.get("id")));
    classQuery.ORDER_BY_ASC(classQuery.getDisplayLabel().localize());

    return classQuery;
  }

  public static Roles[] getMDSSRoles()
  {
    return new Roles[] { Roles.findRoleByName(MDSSRoleInfo.GUI_VISIBILITY), Roles.findRoleByName(MDSSRoleInfo.SYSTEM) };
  }

  /**
   * Ticket 3378
   * 
   * Row by row the geography is checked. If there is a match, the row is
   * imported (if all other validation also passes). If other validation fails,
   * the row is written to the exception spreadsheet, as is currently the case.
   * If the geography is not recognised, the row is also written to the
   * exception spreadsheet and the entry is added to the geo synonym page, which
   * I guess is being built in the background.A meaningful error message is
   * written in the error tab of the exception spreadsheet. At the end of the
   * process, all importable rows have been imported, all other rows have been
   * written to the exception spreadsheet and if there were geo issues, the
   * synonym page is called as is currently the case.
   * 
   * @return
   */
  public static java.io.OutputStream importExcelWithSynonyms()
  {
    return null;
    // Start caching Broswer Roots for this Thread.
    // TermRootCache.start();
    // EpiCache.start();
    //
    // try
    // {
    // ContextBuilderFacade builder = new ContextBuilderFacade(new
    // DefaultContextBuilder(listenerMethod, params));
    //
    // ExcelImporter importer = new ExcelImporter(inputStream, builder);
    //
    // try
    // {
    // importer.read
    // return new ByteArrayInputStream(importer.read());
    // }
    // catch (RuntimeException e)
    // {
    /*
     * Ticket #2663: Errors from reading external sheet should have a better
     * error message. Unfortunately, the HSSF API doesn't throw a specific
     * exception for external sheet errors, but throws a RuntimeException. As
     * such the only way to tell if the exception is an external sheet error is
     * by reading the message.
     */
    // Throwable cause = e.getCause();
    //
    // if (cause.getMessage().startsWith("No external workbook with name"))
    // {
    // throw new ExcelReadException();
    // }
    //
    // throw e;
    // }
    // }
    // finally
    // {
    // TermRootCache.stop();
    // EpiCache.stop();
    // }
  }

  /**
   * @deprecated in favor of importExcelWithSynonyms
   * 
   *             Checks the geo entity hierarchy in the excel file and tries to
   *             find synonym matches. Each geo universal column is checked in
   *             order of depth, starting from lowest to highest.
   * 
   * 
   * @param inputStream
   * @param type
   * @return
   */
  public static dss.vector.solutions.geo.UnknownGeoEntity[] checkSynonyms(InputStream inputStream, String type)
  {
    GeoEntitySearcher geoEntitySearcher = new GeoEntitySearcher();

    List<UnknownGeoEntity> unknownGeoEntityList = geoEntitySearcher.checkExcelGeoHierarchy(inputStream);

    UnknownGeoEntity[] unknownGeoEntityArray = new UnknownGeoEntity[unknownGeoEntityList.size()];

    unknownGeoEntityList.toArray(unknownGeoEntityArray);

    return unknownGeoEntityArray;
  }

  public static UnknownTerm[] checkTermSynonyms(InputStream inputStream, String type)
  {
    TermSearcher searcher = new TermSearcher();

    List<UnknownTerm> unknownTerms = searcher.checkExcelTerms(inputStream);

    return unknownTerms.toArray(new UnknownTerm[unknownTerms.size()]);
  }

  /**
   * Preprocesses the geo entity names to verify they are recognized. If they
   * are not recognized and no synonyms have been assigned, then synonyms are
   * looked up and the user is prompeted with a list of possible synonym
   * matches.
   * 
   * @param inputStream
   * @param type
   * @param listenerMethod
   * @param params
   * @return
   */
  @Request
  public static InputStream importExcelFile(InputStream inputStream, String type, String listenerMethod, String[] params)
  {
    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(listenerMethod, params), null);

      ExcelImporter importer = new ExcelImporter(inputStream, builder);

      try
      {
        return new ByteArrayInputStream(importer.read());
      }
      catch (RuntimeException e)
      {
        /*
         * Ticket #2663: Errors from reading external sheet should have a better
         * error message. Unfortunately, the HSSF API doesn't throw a specific
         * exception for external sheet errors, but throws a RuntimeException.
         * As such the only way to tell if the exception is an external sheet
         * error is by reading the message.
         */
        Throwable cause = e.getCause();

        if (cause.getMessage().startsWith("No external workbook with name"))
        {
          throw new ExcelReadException();
        }

        throw e;
      }
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }
  }

  public static InputStream exportAggregatedCases()
  {
    try
    {
      ExcelExporter exporter = new ExcelExporter();
      AggregatedCaseReferralsExcelView.setupExportListener(exporter);
      exporter.addTemplate(AggregatedCaseReferralsExcelView.CLASS);
      POIFSFileSystem fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      Sheet referralSheet = workbook.getSheetAt(0);
      String referralSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      AggregatedCaseTreatmentsExcelView.setupExportListener(exporter);
      exporter.addTemplate(AggregatedCaseTreatmentsExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet treatmentSheet = workbook.getSheetAt(0);
      String treatmentSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      CaseDiagnosisTypeExcelView.setupExportListener(exporter);
      exporter.addTemplate(CaseDiagnosisTypeExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet diagnosisSheet = workbook.getSheetAt(0);
      String diagnosisSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      CaseDiseaseManifestationExcelView.setupExportListener(exporter);
      exporter.addTemplate(CaseDiseaseManifestationExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet diseaseSheet = workbook.getSheetAt(0);
      String diseaseSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      CasePatientTypeExcelView.setupExportListener(exporter);
      exporter.addTemplate(CasePatientTypeExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet patientSheet = workbook.getSheetAt(0);
      String patientSheetName = workbook.getSheetName(0);

      workbook = new HSSFWorkbook();
      copySheetIntoWorkbook(workbook, referralSheet, referralSheetName);
      copySheetIntoWorkbook(workbook, treatmentSheet, treatmentSheetName);
      copySheetIntoWorkbook(workbook, diagnosisSheet, diagnosisSheetName);
      copySheetIntoWorkbook(workbook, diseaseSheet, diseaseSheetName);
      copySheetIntoWorkbook(workbook, patientSheet, patientSheetName);

      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      BufferedOutputStream buffer = new BufferedOutputStream(bytes);
      workbook.write(buffer);
      buffer.flush();
      buffer.close();
      return new ByteArrayInputStream(bytes.toByteArray());
    }
    catch (OfficeXmlFileException e)
    {
      throw new ExcelVersionException(e);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  public static InputStream exportControlIntervention()
  {
    try
    {
      ExcelExporter exporter = new ExcelExporter();
      AggregatedPremiseExcelView.setupExportListener(exporter);
      exporter.addTemplate(AggregatedPremiseExcelView.CLASS);
      POIFSFileSystem fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      Sheet premiseSheet = workbook.getSheetAt(0);
      String premiseSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      IndividualPremiseExcelView.setupExportListener(exporter);
      exporter.addTemplate(IndividualPremiseExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet individualSheet = workbook.getSheetAt(0);
      String individualSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      InsecticideInterventionExcelView.setupExportListener(exporter);
      exporter.addTemplate(InsecticideInterventionExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet insecticideSheet = workbook.getSheetAt(0);
      String insecticideSheetName = workbook.getSheetName(0);

      exporter = new ExcelExporter();
      PersonInterventionExcelView.setupExportListener(exporter);
      exporter.addTemplate(PersonInterventionExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      Sheet personSheet = workbook.getSheetAt(0);
      String personSheetName = workbook.getSheetName(0);

      workbook = new HSSFWorkbook();
      copySheetIntoWorkbook(workbook, premiseSheet, premiseSheetName);
      copySheetIntoWorkbook(workbook, individualSheet, individualSheetName);
      copySheetIntoWorkbook(workbook, insecticideSheet, insecticideSheetName);
      copySheetIntoWorkbook(workbook, personSheet, personSheetName);

      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      BufferedOutputStream buffer = new BufferedOutputStream(bytes);
      workbook.write(buffer);
      buffer.flush();
      buffer.close();
      return new ByteArrayInputStream(bytes.toByteArray());
    }
    catch (OfficeXmlFileException e)
    {
      throw new ExcelVersionException(e);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  @SuppressWarnings("unchecked")
  private static void copySheetIntoWorkbook(HSSFWorkbook workbook, Sheet oldSheet, String name)
  {
    Sheet newSheet = workbook.createSheet(name);
    Iterator<Row> rowIterator = oldSheet.rowIterator();
    while (rowIterator.hasNext())
    {
      Row oldRow = rowIterator.next();
      int rowNum = oldRow.getRowNum();
      Row newRow = newSheet.createRow(rowNum);

      Iterator<Cell> cellIterator = oldRow.cellIterator();
      while (cellIterator.hasNext())
      {
        Cell oldCell = cellIterator.next();
        int columnIndex = oldCell.getColumnIndex();
        Cell newCell = newRow.createCell(columnIndex);
        switch (oldCell.getCellType())
        {
          case Cell.CELL_TYPE_BOOLEAN:
            newCell.setCellValue(oldCell.getBooleanCellValue());
            break;
          case Cell.CELL_TYPE_FORMULA:
            newCell.setCellFormula(oldCell.getCellFormula());
            break;
          case Cell.CELL_TYPE_NUMERIC:
            newCell.setCellValue(oldCell.getNumericCellValue());
            break;
          case Cell.CELL_TYPE_STRING:
            newCell.setCellValue(oldCell.getRichStringCellValue());
            break;
          case Cell.CELL_TYPE_ERROR:
            newCell.setCellType(Cell.CELL_TYPE_ERROR);
            newCell.setCellValue(oldCell.getErrorCellValue());
            break;
        }

        if (rowNum == 2)
        {
          newSheet.setColumnWidth(columnIndex, oldSheet.getColumnWidth(columnIndex));
        }
      }
    }
  }

  public static String getAttributeDisplayLabel(String className, String attributeName)
  {
    MdClassDAOIF mdClassDAO = MdClassDAO.getMdClassDAO(className);
    MdAttributeDAOIF mdAttributeDAO = mdClassDAO.definesAttribute(attributeName);
    MdAttribute mdAttribute = MdAttribute.get(mdAttributeDAO.getId());

    return mdAttribute.getDisplayLabel().getValue();
  }

  public static InputStream exportGeoChildren(String parentId, Boolean includeGeoData)
  {
    return GeoExporter.exportGeo(GeoEntity.get(parentId), includeGeoData);
  }

  public static InputStream exportGeosByType(String hierarchyId, Boolean includeGeoData)
  {
    return GeoExporter.exportUniversal(GeoHierarchy.get(hierarchyId), includeGeoData);
  }
}
