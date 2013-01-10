package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

public class DefaultImportConfiguration implements Reloadable, ImportConfiguration
{

  @Override
  public InputStream excelImport(ClientRequestIF clientRequest, ByteArrayInputStream stream, String excelType)
  {
    return MdFormUtilDTO.excelImport(clientRequest, stream, excelType);
  }

  @Override
  public void redirectError(ExcelController controller, String type) throws IOException, ServletException
  {
    controller.importType(type);
  }

  @Override
  public String getFormUrl()
  {
    return "dss.vector.solutions.generator.ExcelController.importType.mojo";
  }
}
