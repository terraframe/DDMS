package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

public interface ImportConfiguration extends Reloadable
{

  public InputStream excelImport(ClientRequestIF clientRequest, ByteArrayInputStream byteArrayInputStream, String excelType);

  public void redirectError(ExcelController controller, String type) throws IOException, ServletException;

  public String getFormUrl();

}
