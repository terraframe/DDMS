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
package dss.vector.solutions.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManagerDTO;
import dss.vector.solutions.form.business.FormSurveyDTO;

public class SurveyImportConfiguration implements ImportConfiguration, Reloadable
{

  @Override
  public InputStream excelImport(ClientRequestIF clientRequest, ByteArrayInputStream stream, String excelType, ExcelImportManagerDTO manager, String fileName)
  {
    return FormSurveyDTO.excelImport(clientRequest, stream, manager, fileName);
  }

  @Override
  public void redirectError(ExcelController controller, String type) throws IOException, ServletException
  {
    controller.surveyImportType();
  }

  @Override
  public String getFormUrl()
  {
    return "dss.vector.solutions.generator.ExcelController.surveyImportType.mojo";
  }

}
