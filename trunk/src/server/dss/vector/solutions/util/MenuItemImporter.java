package dss.vector.solutions.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.ontology.Term;

public class MenuItemImporter {
	private String fileName = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	@StartSession
	public static void main(String[] args) throws Exception {
		switch (args.length) {
		case 1:
			String fileName = args[0];
			System.out.println("Start");
			MenuItemImporter i = new MenuItemImporter(fileName);
			i.importMenuItemsAndDiseaseRoots();
			System.out.println("End");
			break;
		default:
			System.out.println("Incorrect args!  Takes one argument: the filename");
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
	public void importMenuItemsAndDiseaseRoots() throws Exception {
		this.deleteAllTableRecords(MenuItem.CLASS);
		this.importMenuItems();
		this.importDiseaseRoots();
	}

	@Transaction
	private void deleteAllTableRecords(String className) {
		MdEntity biz = MdEntity.getMdEntity(className);
		biz.deleteAllTableRecords();
	}

	@Transaction
	private void importMenuItems() throws Exception {
		InputStream is = new FileInputStream(this.fileName);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(0); // Use first sheet
		int rowCount = 1; // Start at second row
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			String diseaseId = this.getCellValue(row, 0);
			String urlId = this.getCellValue(row, 1);
			String termId = this.getCellValue(row, 2);
			if (diseaseId != null && diseaseId.length() > 0) {
				Disease disease = Disease.valueOf(diseaseId);
				Term term = Term.getByTermId(termId);
				SystemURL systemUrl = SystemURL.getByKey(urlId);
				System.out.println("MenuItem: " + diseaseId + "|" + urlId + "|" + termId);
				MenuItem menuItem = new MenuItem();
				menuItem.setUrl(systemUrl);
				menuItem.setTerm(term);
				menuItem.addDisease(disease);
				menuItem.apply();
			}
			row = sheet.getRow(rowCount++);
		}
	}

	@Transaction
	private void importDiseaseRoots() throws Exception {
		InputStream is = new FileInputStream(this.fileName);
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(1); // Use second sheet
		int rowCount = 1; // Start at second row
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			String diseaseId = this.getCellValue(row, 0);
			String termId = this.getCellValue(row, 1);
			if (diseaseId != null && diseaseId.length() > 0) {
				Disease disease = Disease.valueOf(diseaseId);
				Term term = Term.getByTermId(termId);
				System.out.println("DiseaseRoot: " + diseaseId + "|" + termId);
				// disease.lock();
				// disease.setMenuRoot(term);
				// disease.apply();
			}
			row = sheet.getRow(rowCount++);
		}
	}

	private String getCellValue(HSSFRow row, int col) {
		return ExcelUtil.getString(row.getCell(col));
	}
}
