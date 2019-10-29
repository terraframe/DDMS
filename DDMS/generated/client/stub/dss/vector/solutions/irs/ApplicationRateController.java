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
package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.PropertyDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ApplicationRateController extends ApplicationRateControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1240976699468L;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ApplicationRate/";

  public static final String LAYOUT           = "/layout.jsp";

  public ApplicationRateController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void view() throws IOException, ServletException
  {
    this.view(Boolean.FALSE);
  }
  private void view(Boolean showCheckmark) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    AreaStandardsViewDTO dto = AreaStandardsViewDTO.getMostRecent(clientRequest);

    if (dto == null)
    {
      dto = new AreaStandardsViewDTO(clientRequest);
    }

    view(dto, showCheckmark);
  }
  private void view(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto, Boolean.FALSE);
  }
  private void view(AreaStandardsViewDTO dto, Boolean showCheckmark) throws IOException, ServletException
  {
    // if this method is being accessed from create or edit, redirect so the url
    // will be correct and refresh will
    // not create a new object
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupNozzleGrid();
    this.setupConfigurationGrid();
    
    PropertyDTO prop = PropertyDTO.getByPackageAndName(getClientRequest(), "dss.vector.solutions.irs", "irsValidateMultipleStructures");
    Boolean allowMultipleStructures = Boolean.parseBoolean(prop.getPropertyValue());
    
    req.setAttribute("targetUnits", TargetUnitDTO.allItems(this.getClientRequest()));
    req.setAttribute("dto", dto);
    req.setAttribute("property", prop);
    req.setAttribute("allowMultipleStructures", allowMultipleStructures);
    req.setAttribute("showCheckmark", showCheckmark);
    render("viewComponent.jsp");
  }

  private void setupNozzleGrid()
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    NozzleViewDTO view = new NozzleViewDTO(clientRequest);
    NozzleViewDTO[] data = NozzleViewDTO.getAll(clientRequest);
    String[] keys = { "NozzleId", "DisplayLabel", "Ratio", "Enabled" };

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("NozzleId", new ColumnSetup(true, false));
    map.put("DisplayLabel", new ColumnSetup(false, true));
    map.put("Ratio", new ColumnSetup(false, true));
    map.put("Enabled", new ColumnSetup(false, true));

    req.setAttribute("nozzleGrid", new ViewDataGrid(view, map, keys, data));
  }

  private void setupConfigurationGrid()
  {
    req.setAttribute("configurationGrid", new ConfigurationGridBuilder(getClientRequest()).build());
  }

  @Override
  public void failView() throws IOException, ServletException
  {
    super.failView();
  }

  @Override
  public void create(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.applyClone();

      view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  @Override
  public void failCreate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }

  @Override
  public void update(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  @Override
  public void failUpdate(AreaStandardsViewDTO dto) throws IOException, ServletException
  {
    this.view(dto);
  }
  
  public void validationSubmit(Boolean multipleStructuresPerRow) throws java.io.IOException, javax.servlet.ServletException
  {
    try {
      if (multipleStructuresPerRow == null) { multipleStructuresPerRow = Boolean.FALSE; }
      
      HouseholdSprayStatusDTO.setProperty(this.getClientRequest(), "allow_multiple_structures", String.valueOf(multipleStructuresPerRow));
      
      this.view(true);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failValidationSubmit(null);
      }
    }
  }
  
  public void failValidationSubmit(java.lang.String multiple_structures_per_row) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view();
  }
}
