package dss.vector.solutions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.ShapefileExporter;

public class ShapefileExportTest extends TestCase implements Reloadable {
	private class LayerMock extends Layer {

		@Override
		public String getLayerName() {
			return "testLayer";
		}

		@Override
		public String getViewName() {
			return "geo$1264007443258";
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExport() {
		ShapefileExporter exporter = new ShapefileExporter();
		ArrayList<Layer> layers = new ArrayList<Layer>();

		layers.add(new LayerMock());
		FileOutputStream output;
		try {
			output = new FileOutputStream("test.zip");
			exporter.export(layers, output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}