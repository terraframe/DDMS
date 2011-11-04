package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormSurveyDTO;

public class SurveyImportConfiguration implements ImportConfiguration, Reloadable
{

  @Override
  public InputStream excelImport(ClientRequestIF clientRequest, ByteArrayInputStream stream, String excelType)
  {
    return FormSurveyDTO.excelImport(clientRequest, stream);
  }

  @Override
  public void redirectError(ExcelController controller, String type) throws IOException, ServletException
  {
    controller.surveyImportType();
  }

}
