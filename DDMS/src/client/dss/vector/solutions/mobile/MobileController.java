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
package dss.vector.solutions.mobile;

import org.json.JSONException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.ServletMethod;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.mvc.Controller;
import com.runwaysdk.mvc.Endpoint;
import com.runwaysdk.mvc.ErrorSerialization;
import com.runwaysdk.mvc.RedirectResponse;
import com.runwaysdk.mvc.RequestParamter;
import com.runwaysdk.mvc.ResponseIF;
import com.runwaysdk.mvc.RestBodyResponse;

import dss.vector.solutions.util.ExcelImportServlet;

@Controller(url = "mobile")
public class MobileController implements Reloadable
{
  @Endpoint(method = ServletMethod.GET, error = ErrorSerialization.JSON)
  public ResponseIF export(ClientRequestIF request, @RequestParamter(name = "type") String type) throws JSONException
  {
    String html;
    try
    {
      html = MobileUtilDTO.export(request, type);
      
      if (html.contains("Successful form upload"))
      {
        return new RedirectResponse("excelImportDone?mobileExportSuccess=true");
      }
      else
      {
        ExcelImportServlet.addImportDoneMessage(request.getSessionId(), html);
        return new RedirectResponse("excelImportDone?mobileExportFail=true");
      }
    }
    catch (Throwable t)
    {
      String msg = t.getLocalizedMessage();
      ExcelImportServlet.addImportDoneMessage(request.getSessionId(), msg);
      return new RedirectResponse("excelImportDone?mobileExportFail=true");
    }
  }
}
