package dss.vector.solutions;

import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.ShapefileExporter;

public class ShapefileExportTest extends TestCase implements Reloadable {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}



	public void testExport() {
	  
	  /* 
	  Layer layer = new Layer();
	  
		ShapefileExporter.Layer[] layers = new ShapefileExporter.Layer[1];
		ShapefileExporter exporter = new ShapefileExporter();
		layers[0] = exporter.new Layer(
				"p",
				"SELECT      ValueQuery_2.entityName_4 AS entityname_v,     ValueQuery_2.geoId_5 AS geoid_v, aggregatedcase_17.cases AS data, ValueQuery_2.geoMultiPolygon_9 AS geometry_v FROM allpaths_geo allpaths_geo_22 LEFT JOIN (SELECT       geoentity_3.entityName AS entityName_4,      geoentity_3.geoId AS geoId_5,      allpaths_geo_6.childGeoEntity AS childGeoEntity_8,      geoentity_3.geoMultiPolygon AS geoMultiPolygon_9 FROM allpaths_geo allpaths_geo_6,      geoentity geoentity_3  WHERE (allpaths_geo_6.parentUniversal = 'zms3y1lj9nszhh0g9ijc6rygv4msuk4o00000000000000000000000000000001' AND allpaths_geo_6.parentGeoEntity = geoentity_3.id) ) ValueQuery_2 ON allpaths_geo_22.childGeoEntity = ValueQuery_2.childGeoEntity_8  ,     aggregatedcase aggregatedcase_17  WHERE ((((((((aggregatedcase_17.startAge >= 0 AND aggregatedcase_17.endAge <= 1) OR (aggregatedcase_17.startAge >= 1 AND aggregatedcase_17.endAge <= 5)) OR (aggregatedcase_17.startAge >= 5 AND aggregatedcase_17.endAge <= 14)) OR (aggregatedcase_17.startAge >= 14 AND aggregatedcase_17.endAge <= 200)) OR (aggregatedcase_17.startAge >= 0 AND aggregatedcase_17.endAge <= 200)) AND ValueQuery_2.geoMultiPolygon_9 IS NOT NULL) AND allpaths_geo_22.parentUniversal = 'qr33hkr19cv4qh05hz0q5nve5y5q4oas00000000000000000000000000000001') AND aggregatedcase_17.geoEntity = allpaths_geo_22.childGeoEntity)",
				"geometry_v");
		FileOutputStream output;
		try {
			output = new FileOutputStream("test.zip");
			exporter.export(layers, output);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(output.size() + " byte(s)");
		 * 
		 */
	}


}