package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManagerDTO;

public class DefaultImportConfiguration implements Reloadable, ImportConfiguration
{

  @Override
  public InputStream excelImport(ClientRequestIF clientRequest, ByteArrayInputStream stream, String excelType, ExcelImportManagerDTO manager, String fileName)
  {
    return manager.importWhatYouCan(stream, null, fileName);
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
