package dss.vector.solutions.query;

import java.io.InputStream;

import com.runwaysdk.generation.loader.Reloadable;

public interface RequestFacadeIF extends Reloadable
{

  public String newFile(String path, String filename, String extension, InputStream stream);

  public void delete(String id);

}
