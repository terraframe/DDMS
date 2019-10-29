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
package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MonthOfYearDTO;
import dss.vector.solutions.MonthOfYearMasterDTO;
import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.ResponseMasterDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.MonthComparator;
import dss.vector.solutions.util.RedirectUtility;

public class ITNInstanceController extends ITNInstanceControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNInstance/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -372158525;

  public ITNInstanceController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(ITNInstanceDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }

  }

  public void failCancel(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void create(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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

  public void failCreate(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void delete(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();
      HouseholdViewDTO view = HouseholdDTO.getView(request, dto.getValue(SurveyedPersonViewDTO.HOUSEHOLD));

      dto.deleteConcrete();

      new HouseholdController(req, resp, isAsynchronous).view(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ITNInstanceViewDTO dto = ITNInstanceDTO.lockView(super.getClientRequest(), id);

      this.setupReferences(dto);
      req.setAttribute("item", dto);

      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance(String householdId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a ITN Instance
      new ITNInstanceDTO(clientRequest);

      ITNInstanceViewDTO dto = new ITNInstanceViewDTO(clientRequest);
      dto.setValue(ITNInstanceViewDTO.HOUSEHOLD, householdId);

      this.setupReferences(dto);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(householdId);
      }
    }
  }

  public void failNewInstance(String householdId) throws IOException, ServletException
  {
    new HouseholdController(req, resp, isAsynchronous).view(householdId);
  }

  public void update(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
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

  public void failUpdate(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    this.setupReferences(dto);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(ITNInstanceDTO.getView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }

  }

  private void view(ITNInstanceViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    new SurveyPointController(req, resp, isAsynchronous).viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNInstanceQueryDTO query = ITNInstanceDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNInstanceQueryDTO query = ITNInstanceDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  private void setupReferences(ITNInstanceViewDTO dto)
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    List<MonthOfYearMasterDTO> months = MonthOfYearDTO.allItems(request);
    Collections.sort(months, new MonthComparator());

    req.setAttribute("monthReceived", months);
    req.setAttribute("monthRetreated", months);
    req.setAttribute("damaged", AttributeUtil.getValue(ITNInstanceViewDTO.DAMAGED, dto));
    req.setAttribute("hanging", AttributeUtil.getValue(ITNInstanceViewDTO.HANGING, dto));
    req.setAttribute("netBrand", AttributeUtil.getValue(ITNInstanceViewDTO.NETBRAND, dto));
    req.setAttribute("obtained", AttributeUtil.getValue(ITNInstanceViewDTO.OBTAINED, dto));
    req.setAttribute("purpose", AttributeUtil.getValue(ITNInstanceViewDTO.PURPOSE, dto));
    req.setAttribute("washPeriod", AttributeUtil.getValue(ITNInstanceViewDTO.WASHPERIOD, dto));
    req.setAttribute("washed", this.getResponses(request));
  }

  private List<ResponseMasterDTO> getResponses(ClientRequestIF request)
  {
    List<ResponseMasterDTO> responses = ResponseDTO.items(request, ResponseDTO.YES, ResponseDTO.NO);
    for (ResponseMasterDTO response : dss.vector.solutions.ResponseDTO.allItems(request))
    {
      if (!responses.contains(response))
      {
        responses.add(response);
      }
    }
    return responses;
  }
}
