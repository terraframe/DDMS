package dss.vector.solutions.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.RunwayExceptionIF;
import com.runwaysdk.SystemException;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdViewInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
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
import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.export.GeoExporter;
import dss.vector.solutions.export.IndividualPremiseExcelView;
import dss.vector.solutions.export.InsecticideInterventionExcelView;
import dss.vector.solutions.export.PersonInterventionExcelView;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.TermRootCache;

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
    return new Roles[]
    {
      Roles.findRoleByName(MDSSRoleInfo.GUI_VISIBILITY),
      Roles.findRoleByName(MDSSRoleInfo.SYSTEM)
    };
  }

  /**
   * Checks the geo entity hierarchy in the excel file and tries to find synonym matches.  Each geo universal column is checked
   * in order of depth, starting from lowest to highest.
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

  /**
   * Preprocesses the geo entity names to verify they are recognized.  If they are not recognized and no synonyms have been assigned,
   * then synonyms are looked up and the user is prompeted with a list of possible synonym matches.
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
    
    // Assume we get an error.  Set to false only after we don't.
    boolean error = true;
    
//    GeoSynonym.checkExcelGeoHierarchy(inputStream);

    ExcelImporter importer = new ExcelImporter(inputStream);
    for (ImportContext context : importer.getContexts())
    {
      try
      {
        String definesType = context.getMdClass().definesType();
        // Load the type which is being exported
        Class<?> c = LoaderDecorator.load(definesType);
        
        // Get the listener method
        Method method = c.getMethod(listenerMethod, ImportContext.class, String[].class);
        
        // Invoke the method
        method.invoke(null, context, (Object)params);
        
        // Success, no exceptions, set error = false
        error = false;
      }
      catch (NoSuchMethodException e)
      {
        // If the method doesn't exist then do nothing
      }
      catch (InvocationTargetException e)
      {
        Throwable targetException = e.getTargetException();
        if (targetException instanceof RunwayExceptionIF)
        {
          throw (RuntimeException) targetException;
        }
        else
        {
          throw new ProgrammingErrorException(e);
        }
      }
      catch (Exception e)
      {
        throw new ProgrammingErrorException(e);
      }
      finally
      {
        // We don't want to exit this block without stopping caching
        if (error)
        {
          TermRootCache.stop();
          EpiCache.stop();
        }
      }
    }
    
    ByteArrayInputStream stream = new ByteArrayInputStream(importer.read());
    TermRootCache.stop();
    EpiCache.stop();
    return stream;
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
      HSSFSheet referralSheet = workbook.getSheetAt(0);
      String referralSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      AggregatedCaseTreatmentsExcelView.setupExportListener(exporter);
      exporter.addTemplate(AggregatedCaseTreatmentsExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet treatmentSheet = workbook.getSheetAt(0);
      String treatmentSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      CaseDiagnosisTypeExcelView.setupExportListener(exporter);
      exporter.addTemplate(CaseDiagnosisTypeExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet diagnosisSheet = workbook.getSheetAt(0);
      String diagnosisSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      CaseDiseaseManifestationExcelView.setupExportListener(exporter);
      exporter.addTemplate(CaseDiseaseManifestationExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet diseaseSheet = workbook.getSheetAt(0);
      String diseaseSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      CasePatientTypeExcelView.setupExportListener(exporter);
      exporter.addTemplate(CasePatientTypeExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet patientSheet = workbook.getSheetAt(0);
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
      HSSFSheet premiseSheet = workbook.getSheetAt(0);
      String premiseSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      IndividualPremiseExcelView.setupExportListener(exporter);
      exporter.addTemplate(IndividualPremiseExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet individualSheet = workbook.getSheetAt(0);
      String individualSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      InsecticideInterventionExcelView.setupExportListener(exporter);
      exporter.addTemplate(InsecticideInterventionExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet insecticideSheet = workbook.getSheetAt(0);
      String insecticideSheetName = workbook.getSheetName(0);
      
      exporter = new ExcelExporter();
      PersonInterventionExcelView.setupExportListener(exporter);
      exporter.addTemplate(PersonInterventionExcelView.CLASS);
      fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet personSheet = workbook.getSheetAt(0);
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
  private static void copySheetIntoWorkbook(HSSFWorkbook workbook, HSSFSheet oldSheet, String name)
  {
    HSSFSheet newSheet = workbook.createSheet(name);
    Iterator<HSSFRow> rowIterator = oldSheet.rowIterator();
    while (rowIterator.hasNext())
    {
      HSSFRow oldRow = rowIterator.next();
      int rowNum = oldRow.getRowNum();
      HSSFRow newRow = newSheet.createRow(rowNum);
      
      Iterator<HSSFCell> cellIterator = oldRow.cellIterator();
      while (cellIterator.hasNext())
      {
        HSSFCell oldCell = cellIterator.next();
        int columnIndex = oldCell.getColumnIndex();
        HSSFCell newCell = newRow.createCell(columnIndex);
        switch(oldCell.getCellType())
        {
          case HSSFCell.CELL_TYPE_BOOLEAN:
            newCell.setCellValue(oldCell.getBooleanCellValue());
            break;
          case HSSFCell.CELL_TYPE_FORMULA:
            newCell.setCellFormula(oldCell.getCellFormula());
            break;
          case HSSFCell.CELL_TYPE_NUMERIC:
            newCell.setCellValue(oldCell.getNumericCellValue());
            break;
          case HSSFCell.CELL_TYPE_STRING:
            newCell.setCellValue(oldCell.getRichStringCellValue());
            break;
          case HSSFCell.CELL_TYPE_ERROR:
            newCell.setCellType(HSSFCell.CELL_TYPE_ERROR);
            newCell.setCellValue(oldCell.getErrorCellValue());
            break;
        }
        
        if (rowNum==2)
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
