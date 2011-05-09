package dss.vector.solutions.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import net.sf.ehcache.CacheManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.general.SystemURLQuery;
import dss.vector.solutions.ontology.Term;

public class MenuItemImporter {
	private static final int DISEASE_SHEET = 0;
	private static final int SYSTEMURL_SHEET = 1;
	private static final int MENUITEM_SHEET = 2;
	private String fileName = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	@Request
	public static void main(String[] args) throws Exception {
		switch (args.length) {
		case 1:
			String fileName = args[0];
			System.out.println("Start");
			MenuItemImporter i = new MenuItemImporter(fileName);
			i.importAll();
			System.out.println("End");
			break;
		default:
			System.out.println("Incorrect args!  Takes one argument: the filename");
		}
		

	    List<CacheManager> knownCacheManagers = CacheManager.ALL_CACHE_MANAGERS;

	    while (!knownCacheManagers.isEmpty())
	    {
	      ( (CacheManager) CacheManager.ALL_CACHE_MANAGERS.get(0) ).shutdown();
	    }

	}

	private MenuItemImporter() {
		super();
	}

	public MenuItemImporter(String fileName) {
		this();
		this.fileName = fileName;
	}

	@Transaction
	public void importAll() throws Exception {
		this.deleteMenuItems();
		this.deleteSystemURLs();
		this.importDiseaseRoots();
		this.importSystemUrls();
		this.importMenuItems();
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
	private void importDiseaseRoots() throws Exception {
		InputStream is = new FileInputStream(this.fileName);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(DISEASE_SHEET);
		int rowCount = 1; // Start at second row
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			String diseaseKey = this.getCellValue(row, 0);
			String termId = this.getCellValue(row, 1);
			if (diseaseKey != null && diseaseKey.length() > 0) {
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
	private void importSystemUrls() throws Exception {
		InputStream is = new FileInputStream(this.fileName);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(SYSTEMURL_SHEET); // Use first sheet
		int rowCount = 1; // Start at second row
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			String urlId = this.getCellValue(row, 0);
			String url = this.getCellValue(row, 1);
			if (urlId != null && urlId.length() > 0) {
				SystemURL systemUrl = new SystemURL();
				systemUrl.setUrl(url);
				systemUrl.getDisplayLabel().setValue("defaultLocale", urlId);
				systemUrl.apply();
			}
			row = sheet.getRow(rowCount++);
		}
	}

	@Transaction
	private void importMenuItems() throws Exception {
		InputStream is = new FileInputStream(this.fileName);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(MENUITEM_SHEET); // Use first sheet
		int rowCount = 1; // Start at second row
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			String diseaseKey = this.getCellValue(row, 0);
			String urlId = this.getCellValue(row, 1);
			String termId = this.getCellValue(row, 2);
			if (diseaseKey != null && diseaseKey.length() > 0) {
			  Disease disease = Disease.getByKey(diseaseKey);
				Term term = Term.getByTermId(termId);

				SystemURL systemUrl = SystemURL.getByKey(urlId);
				MenuItem menuItem = new MenuItem();
				menuItem.setUrl(systemUrl);
				menuItem.setTerm(term);
				menuItem.setDisease(disease);
				menuItem.apply();
			}
			row = sheet.getRow(rowCount++);
		}
	}


	private String getCellValue(HSSFRow row, int col) {
		return ExcelUtil.getString(row.getCell(col));
	}
}
