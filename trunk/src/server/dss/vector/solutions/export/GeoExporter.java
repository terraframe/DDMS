package dss.vector.solutions.export;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.SystemException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;

public class GeoExporter implements Reloadable
{
  private int          rownum;

  private HSSFWorkbook workbook;

  private HSSFSheet    sheet;
  
  private Boolean      exportGIS;

  private GeoExporter(Boolean exportGIS)
  {
    this.exportGIS = exportGIS;
    rownum = 3;
  }

  public static InputStream exportGeo(GeoEntity geo, Boolean includeGIS)
  {
    GeoExporter geoExporter = new GeoExporter(includeGIS);
    geoExporter.newWorkbook();
    geoExporter.recursiveExport(null, geo);
    return geoExporter.getWorkbookStream();
  }
  
  public static InputStream exportUniversal(GeoHierarchy gh, Boolean includeGIS)
  {
    GeoExporter geoExporter = new GeoExporter(includeGIS);
    geoExporter.newWorkbook();
    geoExporter.exportType(gh);
    return geoExporter.getWorkbookStream();
  }

  private void exportType(GeoHierarchy hierarchy)
  {
    GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    query.WHERE(query.getType().EQ(hierarchy.getQualifiedType()));
    OIterator<? extends GeoEntity> iterator = query.getIterator();
    
    while (iterator.hasNext())
    {
      GeoEntity geo = iterator.next();
      for (GeoEntity parent : geo.getAllLocatedInGeoEntity())
      {
        writeRow(parent, geo);
      }
    }
  }

  private void recursiveExport(GeoEntity parent, GeoEntity geo)
  {
    writeRow(parent, geo);

    for (GeoEntity child : geo.getImmediateChildren())
    {
      recursiveExport(geo, child);
    }
  }

  private void writeRow(GeoEntity parent, GeoEntity geo)
  {
    // Don't export Earth or the parent itself (which will be null during
    // the first recursive call).
    if (geo.getType().equals(Earth.CLASS) || parent == null)
    {
      return;
    }
    
    HSSFRow row = sheet.createRow(rownum++);
    
    // Follows GeoEntityExcelView.customAttributeOrder()
    row.createCell(0).setCellValue(new HSSFRichTextString(geo.getEntityName()));
    row.createCell(1).setCellValue(new HSSFRichTextString(geo.getGeoId()));
    row.createCell(2).setCellValue(new HSSFRichTextString(geo.getType()));
    
    Term term = geo.getTerm();
    if (term!=null)
    {
      row.createCell(3).setCellValue(new HSSFRichTextString(term.getName()));
    }
    
    if (parent!=null)
    {
      row.createCell(4).setCellValue(new HSSFRichTextString(parent.getGeoId()));
      row.createCell(5).setCellValue(new HSSFRichTextString(parent.getType()));
    }
    
    row.createCell(6).setCellValue(geo.getActivated());
    
    String geoData = geo.getGeoData();
    if (geoData.length()>32767)
    {
      geoData = GeoEntityExcelView.LARGE_GEO_DATA;
    }
    else if (!exportGIS && geoData.length()>0)
    {
      geoData = GeoEntityExcelView.NOT_EXPORTED;
    }
    row.createCell(7).setCellValue(new HSSFRichTextString(geoData));
  }

  private void newWorkbook()
  {
    try
    {
      ExcelExporter exporter = new ExcelExporter();
      exporter.addTemplate(GeoEntityExcelView.CLASS);
      POIFSFileSystem fileSystem = new POIFSFileSystem(new ByteArrayInputStream(exporter.write()));
      workbook = new HSSFWorkbook(fileSystem);
      sheet = workbook.getSheetAt(0);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  private InputStream getWorkbookStream()
  {
    try
    {
      for (short c=0; c<GeoEntityExcelView.customAttributeOrder().size(); c++)
      {
        sheet.autoSizeColumn(c);
      }
      
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      BufferedOutputStream buffer = new BufferedOutputStream(bytes);
      workbook.write(buffer);
      buffer.flush();
      buffer.close();
      return new ByteArrayInputStream(bytes.toByteArray());
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }
}
