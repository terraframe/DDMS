package dss.vector.solutions.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ShapefileExporter {
	private static final int BUFFER_SIZE = 4096;

	public class Layer {
		String sql;
		String name;
		String geoField;

		public Layer(String name, String sqlTableOrView, String geoField) {
			this.name = name;
			this.sql = sql;
			this.geoField = geoField;
		}

		public String getSql() {
			return sql;
		}

		public String getName() {
			return name;
		}

		public String getGeoField() {
			return geoField;
		}

	}
	
	public void export(Layer[] layers, OutputStream output) {
		File dir = this.createTempDir("shapefile");
		if (dir != null) {
			for (int i = 0; i < layers.length; i++) {
				this.pgsql2shp(dir, layers[i].getName(), layers[i].getGeoField(), layers[i].getSql());
			}
			this.zipDir(dir, output);
		}
	}

	private void zipDir(File dir, OutputStream output) {
		ZipOutputStream zos = new ZipOutputStream(output);
		
		byte[] buffer = new byte[BUFFER_SIZE];

		String[] dirList = dir.list();

		int count = 0;
		try {
			for (int i = 0; i < dirList.length; i++) {
				File f = new File(dir, dirList[i]);
				FileInputStream fis = new FileInputStream(f);
				zos.putNextEntry(new ZipEntry(f.getName()));
				while ((count = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, count);
				}
			}
			zos.finish();
		} catch (FileNotFoundException e) {
			// This should be impossible, since we're iterating over a directory listing
		} catch (IOException e) {
			e.printStackTrace();
		} finally {}
	}

	private synchronized File createTempDir(String prefix) {
		File dir = null;

		try {
			dir = File.createTempFile(prefix, "");
			dir.delete(); // Delete the tempfile
			dir.mkdir(); // Recreate it as a directory
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dir;
	}

	private void pgsql2shp(File dir, String name, String geoField, String sql) {
		String cmd[] = new String[13];
		cmd[0] = "/usr/bin/pgsql2shp";
		cmd[1] = "-f";
		cmd[2] = name;
		cmd[3] = "-g";
		cmd[4] = geoField;
		cmd[5] = "-h";
		cmd[6] = "localhost";
		cmd[7] = "-u";
		cmd[8] = "mdssdeploy";
		cmd[9] = "-P";
		cmd[10] = "mdssdeploy";
		cmd[11] = "mdssdeploy";
		cmd[12] = sql;

		String output = this.run(cmd, dir);
		System.out.println(output);
	}

	private String run(String[] command, File dir) {
		Runtime rt = Runtime.getRuntime();
		StringBuilder sb = new StringBuilder();

		String line = null;
		int exitVal = 0;
		try {
			Process pr = rt.exec(command, new String[0], dir);
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while ((line = input.readLine()) != null) {
				sb.append(line + "\n");
			}
			exitVal = pr.waitFor();
		} catch (IOException e) {
			sb.append(e.getStackTrace());
			System.out.println(e);
		} catch (InterruptedException e) {
			// Do nothing);
		}

		if (exitVal != 0) {
			return sb.toString();
		} else {
			return null;
		}
	}

}
