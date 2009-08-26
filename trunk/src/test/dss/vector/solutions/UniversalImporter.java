package dss.vector.solutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.dataaccess.io.dataDefinition.XMLTags;
/*
SELECT DISTINCT
	universal,
	CASE
		WHEN (geom_multipolygon IS NOT NULL AND geom_point IS NULL and geom_multilinestring IS NULL) THEN 'MULTIPOLYGON'
		WHEN (geom_multipolygon IS NULL AND geom_point IS NOT NULL and geom_multilinestring IS NULL) THEN 'POINT'
		WHEN (geom_multipolygon IS NULL AND geom_point IS NULL and geom_multilinestring IS NOT NULL) THEN 'MULTILINESTRING'
		ELSE 'ERROR'
	END AS geometry
FROM geographic_entities_geometry
JOIN geographic_entities_relations USING (geo_id)
JOIN universal ON (instance_of = universal_id)
ORDER BY universal
 */
public class UniversalImporter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "";
		if (args.length > 0) {
			filename = args[0];
		}

		UniversalImporter ui = new UniversalImporter(filename);
		ui.importUniversals();
	}

	private UniversalImporter() {
		super();
		this.addUniversal(Universal.EARTH);
	}

	public UniversalImporter(String filename) {
		this();
		this.filename = filename;
	}

	private Map<String, Universal> universals = new LinkedHashMap<String, Universal>();
	private int errorCount = 0;
	private String filename;

	public void importUniversals() {
		this.importExcelFile(filename);
	}

	private void importExcelFile(String filename) {
		InputStream stream;
		try {
			stream = new FileInputStream(filename);
			this.importExcelStream(filename, stream);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void importExcelStream(String sourceName, InputStream stream) {
		HSSFWorkbook wb;
		try {
			wb = new HSSFWorkbook(stream);
			this.importWorkbook(wb);
			if (this.errorCount == 0) {
				this.processUniversals(sourceName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void importWorkbook(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0); // first sheet
		int rowCount = 1;
		HSSFRow row = sheet.getRow(rowCount++);
		while (row != null) {
			String universalName = this.getCellValue(row, 0);
			if (universalName != null && universalName.length() > 0) {
				String universalAllowedIn = this.getCellValue(row, 1);
				String universalIsA = this.getCellValue(row, 2);
				String geoType = this.getCellValue(row,3).toUpperCase();
				boolean political = this.getCellValue(row,4).toUpperCase().equals("Y");
				boolean sprayTarget = this.getCellValue(row,5).toUpperCase().equals("Y");
				this.processUniversal(universalName, universalIsA, universalAllowedIn, geoType, political, sprayTarget);
			}
			row = sheet.getRow(rowCount++);
		}

		if (errorCount > 0) {
			System.out.println("*** THERE WERE " + errorCount
					+ " ERROR(s) *** ");
		}
	}

	private String getCellValue(HSSFRow row, int col) {
		String value = null;
		HSSFCell cell = row.getCell(col);
		if (cell != null) {
			HSSFRichTextString cellValue = cell.getRichStringCellValue();
			if (cellValue != null) {
				value = cellValue.toString().trim();
			}
		}
		return value;
	}

	private void processUniversal(String description, String isA,
			String allowedIn, String geotype, boolean political, boolean sprayTarget) {

		Universal u = new Universal(description, geotype, political, sprayTarget);
		// System.out.println(u);

		if (this.getUniversal(description) != null) {
			System.out.println("*** ERROR:  " + description
					+ " is already defined ***");
			errorCount++;
		}
		if (isA != "") {
			u.setParent(this.getUniversal(isA));
			if (u.getParent() == null) {
				System.out.println("*** ERROR: is_a (" + isA
						+ ") is not defined ***");
				errorCount++;
			}
		}
		if (allowedIn != "") {
			u.setAllowedIn(this.getUniversal(allowedIn));
			if (u.getAllowedIn() == null) {
				System.out.println("*** ERROR: allowed_in (" + allowedIn
						+ ") is not defined ***");
				errorCount++;
			}
		}

		this.addUniversal(u);
	}

	private void addUniversal(Universal u) {
		universals.put(u.getDescription(), u);
	}

	private Universal getUniversal(String description) {
		return universals.get(description);
	}

	private void processUniversals(String sourceName) {
		System.out.println("<"+XMLTags.VERSION_TAG+" xsi:noNamespaceSchemaLocation=\"../profiles/version_gis.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		System.out.println("<!-- Created on " + new Date() + " from " + sourceName + " -->");
		System.out.println("<"+XMLTags.DO_IT+">");
// Heads up: clean up
//		System.out.println("<existing>");
//		System.out.println("   <object key=\"GeoHierarchy_Earth\"");
//		System.out.println("      id=\"EarthH\"");
//		System.out.println("      type=\"dss.vector.solutions.geo.GeoHierarchy\" />");
//		System.out.println("   <object key=\"dss.vector.solutions.geo.generated.Earth\"");
//		System.out.println("      id=\"EarthMd\"");
//		System.out.println("      type=\"com.terraframe.mojo.system.metadata.MdBusiness\" />");
//		System.out.println("</existing>");

		//1) define an <mdBusiness> to define the class that will store instances of the geo universal
		System.out.println("<"+XMLTags.CREATE_TAG+">");
		for (Universal u : universals.values()) {
			if (u != Universal.EARTH) {
				System.out.println(u.getMdBusinessTag());
			}
		}
		System.out.println("</"+XMLTags.CREATE_TAG+">\n");

//		//2) create an <existing> tag to create a pseudo ID for the <mdBusiness> definition
//		System.out.println("<existing>");
//		for (Universal u : universals.values()) {
//			if (u != Universal.EARTH) {
//				System.out.println(u.getMetadataTag());
//			}
//		}
//		System.out.println("</existing>\n");

		System.out.println("<"+XMLTags.CREATE_TAG+">");
		//3) create an instance of dss.vector.solutions.geo.GeoHierarchy using an <object> tag that references the <mdBusiness> using the pseudo ID
		for (Universal u : universals.values()) {
			if (u != Universal.EARTH) {
				System.out.println(u.getGeoHierarchyTag());
			}
		}
		System.out.println("</"+XMLTags.CREATE_TAG+">\n");

		//4) create instancees of the dss.vector.solutions.geo.AllowedIn relationship as necessary for the GeoHierarchy instance you just created
		System.out.println("<"+XMLTags.CREATE_TAG+">");
		for (Universal u : universals.values()) {
			if (u != Universal.EARTH) {
				if (u.getAllowedIn() != null) {
					Universal allowedIn = u.getAllowedIn();
					System.out.println(u.getRelationshipTag(allowedIn.getType(), allowedIn.getTypeName()));
					for (Universal allowedInDescendant: allowedIn.getIsADescendants()) {
						System.out.println(u.getRelationshipTag(allowedInDescendant.getType(), allowedInDescendant.getTypeName()));
					}


					for (Universal allowedInAncestor: allowedIn.getAllowedInAncestors()) {
						for (Universal allowedInDescendant: allowedInAncestor.getIsADescendants()) {
							System.out.println(u.getRelationshipTag(allowedInDescendant.getType(), allowedInDescendant.getTypeName()));
						}
					}
				}
			}
		}
		System.out.println("</"+XMLTags.CREATE_TAG+">\n");

		//5) Define all necessary permissions
		System.out.println("<"+XMLTags.PERMISSIONS_TAG+">");

		System.out.println("   <"+XMLTags.ROLE_TAG+" "+XMLTags.ROLENAME_ATTRIBUTE+"=\"GUIVisibility\">");
		System.out.println("   <"+XMLTags.GRANT_TAG + ">");
		for (Universal u : universals.values()) {
			if (u != Universal.EARTH) {
				System.out.println(u.getReadPermissionsTag());
			}
		}
		System.out.println("   </"+XMLTags.GRANT_TAG + ">");
		System.out.println("   </"+XMLTags.ROLE_TAG+">\n");

		System.out.println("   <"+XMLTags.ROLE_TAG+" "+XMLTags.ROLENAME_ATTRIBUTE+"=\"mdssCoordinator, entomologist\">");
		System.out.println("   <"+XMLTags.GRANT_TAG + ">");
		for (Universal u : universals.values()) {
			if (u != Universal.EARTH) {
				System.out.println(u.getUpdatePermissionsTag());
			}
		}
		System.out.println("   </"+XMLTags.GRANT_TAG + ">");
		System.out.println("   </"+XMLTags.ROLE_TAG+">\n");

//		System.out.println("   <"+XMLTags.ROLE_TAG+" "+XMLTags.ROLENAME_ATTRIBUTE+"=\"entomologist\">");
//		for (Universal u : universals.values()) {
//			if (u != Universal.EARTH) {
//				System.out.println(u.getUpdatePermissionsTag());
//			}
//		}
//		System.out.println("   </"+XMLTags.ROLE_TAG+">\n");

		System.out.println("</"+XMLTags.PERMISSIONS_TAG+">\n");

		System.out.println("</"+XMLTags.DO_IT+">");
		System.out.println("<"+XMLTags.UNDO_IT+">");
		System.out.println("</"+XMLTags.UNDO_IT+">");
		System.out.println("</"+XMLTags.VERSION_TAG+">");
	}
}
