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
package dss.vector.solutions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.ShapefileExporter;
import junit.framework.TestCase;

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
