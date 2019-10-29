/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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

  private Workbook workbook;

  private Sheet    sheet;

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

    Row row = sheet.createRow(rownum++);

    // Follows GeoEntityExcelView.customAttributeOrder()
    row.createCell(0).setCellValue(workbook.getCreationHelper().createRichTextString(geo.getEntityLabel().getValue()));
    row.createCell(1).setCellValue(workbook.getCreationHelper().createRichTextString(geo.getGeoId()));
    row.createCell(2).setCellValue(workbook.getCreationHelper().createRichTextString(geo.getType()));

    Term term = geo.getTerm();
    if (term != null)
    {
      row.createCell(3).setCellValue(workbook.getCreationHelper().createRichTextString(term.getName()));
    }

    if (parent != null)
    {
      row.createCell(4).setCellValue(workbook.getCreationHelper().createRichTextString(parent.getGeoId()));
      row.createCell(5).setCellValue(workbook.getCreationHelper().createRichTextString(parent.getType()));
    }

    row.createCell(6).setCellValue(geo.getActivated());

    String geoData = geo.getGeoData();
    if (geoData.length() > 32767)
    {
      geoData = GeoEntityExcelView.LARGE_GEO_DATA;
    }
    else if (!exportGIS && geoData.length() > 0)
    {
      geoData = GeoEntityExcelView.NOT_EXPORTED;
    }
    row.createCell(7).setCellValue(workbook.getCreationHelper().createRichTextString(geoData));
  }

  private void newWorkbook()
  {
    try
    {
      ExcelExporter exporter = new ExcelExporter();
      exporter.addTemplate(GeoEntityExcelView.CLASS);
      workbook = WorkbookFactory.create(new ByteArrayInputStream(exporter.write()));
      sheet = workbook.getSheetAt(0);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
    catch (InvalidFormatException e)
    {
      throw new SystemException(e);
    }
  }

  private InputStream getWorkbookStream()
  {
    try
    {
      for (short c = 0; c < GeoEntityExcelView.customAttributeOrder().size(); c++)
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
