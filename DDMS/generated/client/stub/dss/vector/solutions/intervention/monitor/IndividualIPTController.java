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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualIPTController extends IndividualIPTControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualIPT/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1253643992094L;

  public IndividualIPTController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTViewQueryDTO query = IndividualIPTViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTViewQueryDTO query = IndividualIPTViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(IndividualIPTDTO.getView(this.getClientRequest(), id));
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

  private void view(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    new IndividualIPTCaseController(req, resp, isAsynchronous).search();
  }

  @Override
  public void newInstance(String caseId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = super.getClientRequest();

      // Ensure the user has the ability to create new Individual IPT instances
      new IndividualIPTDTO(request);

      IndividualIPTCaseViewDTO view = IndividualIPTCaseDTO.getView(request, caseId);

      IndividualIPTViewDTO dto = new IndividualIPTViewDTO(request);
      dto.setValue(IndividualIPTViewDTO.IPTCASE, view.getConcreteId());

      this.setupRequest(dto);

      String serviceDate = req.getParameter("serviceDate");

      if (serviceDate != null && !serviceDate.equals(""))
      {
        Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
        dto.setServiceDate(f.parse(serviceDate, req.getLocale()));
      }

      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(caseId);
      }
    }
  }

  @Override
  public void failNewInstance(String caseId) throws IOException, ServletException
  {
    new IndividualIPTCaseController(req, resp, isAsynchronous).view(caseId);
  }

  public void create(IndividualIPTViewDTO dto) throws IOException, ServletException
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

  public void failCreate(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      IndividualIPTViewDTO dto = IndividualIPTDTO.lockView(super.getClientRequest(), id);

      this.setupRequest(dto);

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

  public void update(IndividualIPTViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    try
    {
      IndividualIPTViewDTO view = IndividualIPTDTO.unlockView(this.getClientRequest(), dto.getConcreteId());

      this.view(view);
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

  public void failCancel(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    try
    {
      String caseId = dto.getIptCase().getId();

      dto.deleteConcrete();

      new IndividualIPTCaseController(req, resp, isAsynchronous).view(caseId);
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

  public void failDelete(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void setupRequest(IndividualIPTViewDTO dto)
  {
    ClientRequestIF request = this.getClientRequest();

    IndividualIPTCaseViewDTO c = IndividualIPTCaseDTO.getView(request, dto.getValue(IndividualIPTViewDTO.IPTCASE));
    PersonViewDTO person = c.getPatientView();

    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("person", person);
    req.setAttribute("patientType", AttributeUtil.getValue(IndividualIPTViewDTO.PATIENTTYPE, dto));
    req.setAttribute("doseNumber", AttributeUtil.getValue(IndividualIPTViewDTO.DOSENUMBER, dto));
    req.setAttribute("doseType", AttributeUtil.getValue(IndividualIPTViewDTO.DOSETYPE, dto));
    req.setAttribute("visitNumber", AttributeUtil.getValue(IndividualIPTViewDTO.VISITNUMBER, dto));
    req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);

    if (dto.getConcreteId() != null && !dto.getConcreteId().equals(""))
    {
      req.setAttribute("facility", AttributeUtil.getGeoEntityFromGeoId(IndividualIPTViewDTO.FACILITY, dto));
    }
  }
}
