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
package dss.vector.solutions.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.general.SystemURLQuery;
import dss.vector.solutions.ontology.Term;

public class MenuItemImporter
{
  private static final int DISEASE_SHEET   = 0;

  private static final int SYSTEMURL_SHEET = 1;

  private static final int MENUITEM_SHEET  = 2;

  private String           fileName        = null;

  private boolean          updateDiseaseRoots;

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception
  {

    try
    {
      switch (args.length)
      {
        case 1:
        {
          String fileName = args[0];
          System.out.println("Start");
          MenuItemImporter i = new MenuItemImporter(fileName);
          i.importAll();
          System.out.println("End");
        }
          break;
        case 2:
        {
          String fileName = args[0];
          boolean updateDiseaseRoots = Boolean.parseBoolean(args[1]);
          System.out.println("Start");
          MenuItemImporter i = new MenuItemImporter(fileName, updateDiseaseRoots);
          i.importAll();
          System.out.println("End");
        }
          break;
        default:
          System.out.println("Incorrect args!  Required: the filename. Optional: update disease roots");
      }
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  private MenuItemImporter()
  {
    super();
  }

  public MenuItemImporter(String fileName)
  {
    this(fileName, true);
  }

  public MenuItemImporter(String fileName, boolean updateDiseaseRoots)
  {
    this();

    this.fileName = fileName;
    this.updateDiseaseRoots = updateDiseaseRoots;
  }

  @Request
  public void importAll() throws Exception
  {
    this.importAllInTransaction();
  }

  @Transaction
  private void importAllInTransaction() throws Exception
  {
    if (this.updateDiseaseRoots)
    {
      this.importDiseaseRoots();
    }

    this.importSystemUrls();
    this.importMenuItems();

    this.setURLName();
  }

  @Transaction
  private void deleteMenuItems()
  {
    QueryFactory qf = new QueryFactory();
    MenuItemQuery query = new MenuItemQuery(qf);
    OIterator<? extends MenuItem> iterator = query.getIterator();
    try
    {
      while (iterator.hasNext())
      {
        iterator.next().delete();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  private void deleteSystemURLs()
  {
    QueryFactory qf = new QueryFactory();
    SystemURLQuery query = new SystemURLQuery(qf);
    OIterator<? extends SystemURL> iterator = query.getIterator();
    try
    {
      while (iterator.hasNext())
      {
        iterator.next().delete();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  private void importDiseaseRoots() throws Exception
  {
    InputStream is = new FileInputStream(this.fileName);
    HSSFWorkbook wb = new HSSFWorkbook(is);
    Sheet sheet = wb.getSheetAt(DISEASE_SHEET);
    int rowCount = 1; // Start at second row
    Row row = sheet.getRow(rowCount++);
    while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)
    {
      String diseaseKey = this.getCellValue(row, 0);
      String termId = this.getCellValue(row, 1);
      if (diseaseKey != null && diseaseKey.length() > 0)
      {
        Disease disease = Disease.getByKey(diseaseKey);
        Term term = Term.getByTermId(termId);

        disease.appLock();
        disease.setMenuRoot(term);
        disease.apply();
      }
      row = sheet.getRow(rowCount++);
    }
  }

  @Transaction
  private void importSystemUrls() throws Exception
  {
    InputStream is = new FileInputStream(this.fileName);
    HSSFWorkbook wb = new HSSFWorkbook(is);
    Sheet sheet = wb.getSheetAt(SYSTEMURL_SHEET); // Use first sheet
    int rowCount = 1; // Start at second row
    Row row = sheet.getRow(rowCount++);
    while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)
    {
      String urlId = this.getCellValue(row, 0);
      String url = this.getCellValue(row, 1);
      if (urlId != null && urlId.length() > 0)
      {
        SystemURL systemUrl = SystemURL.getByURL(url);

        if (systemUrl == null)
        {
          systemUrl = new SystemURL();
          systemUrl.setUrl(url);
        }

        systemUrl.setUrlName(urlId);
        systemUrl.getDisplayLabel().setValue("defaultLocale", urlId);
        systemUrl.apply();
      }
      row = sheet.getRow(rowCount++);
    }
  }

  @Transaction
  private void importMenuItems() throws Exception
  {
    InputStream is = new FileInputStream(this.fileName);
    HSSFWorkbook wb = new HSSFWorkbook(is);
    Sheet sheet = wb.getSheetAt(MENUITEM_SHEET); // Use first sheet
    int rowCount = 1; // Start at second row
    Row row = sheet.getRow(rowCount++);
    while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)
    {
      String diseaseKey = this.getCellValue(row, 0);
      String urlId = this.getCellValue(row, 1);
      String termId = this.getCellValue(row, 2);
      if (diseaseKey != null && diseaseKey.length() > 0)
      {
        Disease disease = Disease.getByKey(diseaseKey);
        Term term = Term.getByTermId(termId);
        SystemURL systemUrl = SystemURL.getByName(urlId);

        MenuItem menuItem = MenuItem.findMenuItem(systemUrl, disease);

        if (menuItem == null)
        {
          menuItem = new MenuItem();
          menuItem.setUrl(systemUrl);
          menuItem.setDisease(disease);
        }

        menuItem.setTerm(term);
        menuItem.apply();
      }
      row = sheet.getRow(rowCount++);
    }
  }

  private void setURLName()
  {
    SystemURLQuery query = new SystemURLQuery(new QueryFactory());
    query.WHERE(query.getUrlName().EQ(""));

    OIterator<? extends SystemURL> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        SystemURL systemURL = it.next();
        systemURL.setUrlName(systemURL.getDisplayLabel().getValue());
        systemURL.apply();
      }
    }
    finally
    {
      it.close();
    }
  }

  private String getCellValue(Row row, int col)
  {
    return ExcelUtil.getString(row.getCell(col));
  }
}
